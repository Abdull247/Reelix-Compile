package com.error404.reelix;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;
import android.os.Looper;

public class WifiDirectManager {
    private WifiP2pManager p2pManager;
    private WifiP2pManager.Channel p2pChannel;
    private Context context;
    private OnGroupReadyListener listener;
    private OnClientConnectedListener clientListener;
    private BroadcastReceiver p2pReceiver;
    private boolean isGroupForming = false;
    private boolean isGroupReady = false;
    private Handler timeoutHandler = new Handler(Looper.getMainLooper());
    private Runnable timeoutRunnable;
    private static final int MAX_RETRIES = 8;
    private int retryCount = 0;
    private String currentGroupSsid = null;
    private String currentGroupPassphrase = null;
    private boolean hasNotifiedClientConnection = false;
    private Handler clientCheckHandler = new Handler(Looper.getMainLooper());
    private Runnable clientCheckRunnable;
    
    public interface OnGroupReadyListener {
        void onGroupCreated(String ssid, String passphrase);
        void onGroupFailed(int reason);
    }
    
    public interface OnClientConnectedListener {
        void onClientConnected(WifiP2pInfo connectionInfo, WifiP2pGroup groupInfo);
        void onClientDisconnected();
    }
    
    public WifiDirectManager(Context context) {
        this.context = context.getApplicationContext();
        this.p2pManager = (WifiP2pManager) context.getSystemService(Context.WIFI_P2P_SERVICE);
        if (p2pManager != null) {
            this.p2pChannel = p2pManager.initialize(context, Looper.getMainLooper(), null);
        }
    }
    
    public void createServerGroup(final OnGroupReadyListener listener) {
        this.listener = listener;
        
        if (p2pManager == null || p2pChannel == null) {
            if (listener != null) listener.onGroupFailed(-1);
            return;
        }
        
        registerP2pReceiver();
        
        p2pManager.removeGroup(p2pChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                createNewGroup();
            }
            
            @Override
            public void onFailure(int reason) {
                createNewGroup();
            }
        });
    }
    
    public void setClientConnectedListener(OnClientConnectedListener clientListener) {
        this.clientListener = clientListener;
    }
    
    private void createNewGroup() {
        isGroupForming = true;
        isGroupReady = false;
        hasNotifiedClientConnection = false;
        retryCount = 0;
        
        p2pManager.createGroup(p2pChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                pollGroupInfo();
                
                timeoutRunnable = new Runnable() {
                    @Override
                    public void run() {
                        if (isGroupForming && listener != null) {
                            unregisterP2pReceiver();
                            listener.onGroupFailed(-2);
                        }
                    }
                };
                timeoutHandler.postDelayed(timeoutRunnable, 5000);
            }
            
            @Override
            public void onFailure(int reason) {
                isGroupForming = false;
                unregisterP2pReceiver();
                if (listener != null) listener.onGroupFailed(reason);
            }
        });
    }
    
    private void pollGroupInfo() {
        p2pManager.requestGroupInfo(p2pChannel, new WifiP2pManager.GroupInfoListener() {
            @Override
            public void onGroupInfoAvailable(WifiP2pGroup group) {
                if (group != null && group.getNetworkName() != null && 
                    group.getPassphrase() != null && !group.getPassphrase().isEmpty()) {
                    
                    isGroupForming = false;
                    isGroupReady = true;
                    currentGroupSsid = group.getNetworkName();
                    currentGroupPassphrase = group.getPassphrase();
                    
                    timeoutHandler.removeCallbacks(timeoutRunnable);
                    
                    if (listener != null) {
                        listener.onGroupCreated(currentGroupSsid, currentGroupPassphrase);
                    }
                    
                    // Start periodic client checking
                    startClientMonitoring();
                    
                } else if (retryCount < MAX_RETRIES) {
                    int delay = 200 + (retryCount * 200);
                    retryCount++;
                    
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (isGroupForming) {
                                pollGroupInfo();
                            }
                        }
                    }, delay);
                } else {
                    isGroupForming = false;
                    timeoutHandler.removeCallbacks(timeoutRunnable);
                    unregisterP2pReceiver();
                    if (listener != null) listener.onGroupFailed(-2);
                }
            }
        });
    }
    
    private void startClientMonitoring() {
        // Periodically check for connected clients (every 2 seconds)
        if (clientCheckRunnable != null) {
            clientCheckHandler.removeCallbacks(clientCheckRunnable);
        }
        
        clientCheckRunnable = new Runnable() {
            @Override
            public void run() {
                if (isGroupReady && !hasNotifiedClientConnection) {
                    checkForConnectedClients();
                }
                if (isGroupReady && !hasNotifiedClientConnection) {
                    clientCheckHandler.postDelayed(this, 2000);
                }
            }
        };
        clientCheckHandler.postDelayed(clientCheckRunnable, 1000);
    }
    
    private void checkForConnectedClients() {
        p2pManager.requestGroupInfo(p2pChannel, new WifiP2pManager.GroupInfoListener() {
            @Override
            public void onGroupInfoAvailable(WifiP2pGroup group) {
                if (group != null && group.getClientList() != null && !group.getClientList().isEmpty()) {
                    // Client(s) connected!
                    p2pManager.requestConnectionInfo(p2pChannel, new WifiP2pManager.ConnectionInfoListener() {
                        @Override
                        public void onConnectionInfoAvailable(WifiP2pInfo info) {
                            if (!hasNotifiedClientConnection && clientListener != null) {
                                hasNotifiedClientConnection = true;
                                clientListener.onClientConnected(info, group);
                                
                                // Stop monitoring since we've notified
                                if (clientCheckRunnable != null) {
                                    clientCheckHandler.removeCallbacks(clientCheckRunnable);
                                }
                            }
                        }
                    });
                }
            }
        });
    }
    
    private void registerP2pReceiver() {
        if (p2pReceiver != null) return;
        
        p2pReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                
                if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
                    NetworkInfo networkInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);
                    WifiP2pInfo p2pInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_INFO);
                    
                    if (networkInfo != null && networkInfo.isConnected() && p2pInfo != null) {
                        if (isGroupReady && !p2pInfo.isGroupOwner) {
                            // Client connected - immediate response
                            handleClientConnected(p2pInfo);
                        } else if (isGroupForming) {
                            p2pManager.requestGroupInfo(p2pChannel, new WifiP2pManager.GroupInfoListener() {
                                @Override
                                public void onGroupInfoAvailable(WifiP2pGroup group) {
                                    if (group != null && group.getNetworkName() != null) {
                                        isGroupForming = false;
                                        isGroupReady = true;
                                        currentGroupSsid = group.getNetworkName();
                                        currentGroupPassphrase = group.getPassphrase();
                                        timeoutHandler.removeCallbacks(timeoutRunnable);
                                        
                                        if (listener != null) {
                                            listener.onGroupCreated(currentGroupSsid, currentGroupPassphrase);
                                        }
                                        startClientMonitoring();
                                    }
                                }
                            });
                        }
                    }
                } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
                    // Peers changed - could be a new client
                    if (isGroupReady && !hasNotifiedClientConnection) {
                        checkForConnectedClients();
                    }
                }
            }
        };
        
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        filter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        filter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        filter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        
        context.registerReceiver(p2pReceiver, filter);
    }
    
    private void handleClientConnected(WifiP2pInfo connectionInfo) {
        if (hasNotifiedClientConnection) return;
        
        p2pManager.requestGroupInfo(p2pChannel, new WifiP2pManager.GroupInfoListener() {
            @Override
            public void onGroupInfoAvailable(WifiP2pGroup group) {
                if (!hasNotifiedClientConnection && clientListener != null) {
                    hasNotifiedClientConnection = true;
                    clientListener.onClientConnected(connectionInfo, group);
                    
                    // Stop client monitoring
                    if (clientCheckRunnable != null) {
                        clientCheckHandler.removeCallbacks(clientCheckRunnable);
                    }
                }
            }
        });
    }
    
    private void unregisterP2pReceiver() {
        if (p2pReceiver != null && context != null) {
            try {
                context.unregisterReceiver(p2pReceiver);
            } catch (IllegalArgumentException e) {
                // Receiver not registered
            }
            p2pReceiver = null;
        }
        
        if (clientCheckRunnable != null) {
            clientCheckHandler.removeCallbacks(clientCheckRunnable);
        }
    }
    
    public void teardownGroup() {
        isGroupForming = false;
        isGroupReady = false;
        hasNotifiedClientConnection = false;
        timeoutHandler.removeCallbacks(timeoutRunnable);
        
        if (clientCheckRunnable != null) {
            clientCheckHandler.removeCallbacks(clientCheckRunnable);
        }
        
        unregisterP2pReceiver();
        
        if (p2pManager != null && p2pChannel != null) {
            p2pManager.removeGroup(p2pChannel, null);
        }
    }
    
    public boolean isGroupReady() {
        return isGroupReady;
    }
    
    public String getCurrentGroupSsid() {
        return currentGroupSsid;
    }
}