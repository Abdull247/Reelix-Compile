package com.error404.reelix;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.WifiNetworkSpecifier;
import android.os.Build;
import android.widget.Toast;

public class WifiConnector {

    public interface OnConnectionListener {
        void onConnected();
        void onFailed();
    }

    public static void connectToHotspot(final Context context, String ssid, String passphrase, final OnConnectionListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            
            // Build the modern specifier injecting the password credential smoothly
            WifiNetworkSpecifier specifier = new WifiNetworkSpecifier.Builder()
                    .setSsid(ssid)
                    .setWpa2Passphrase(passphrase) // Force secure authentication keys
                    .build();

            NetworkRequest request = new NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .setNetworkSpecifier(specifier)
                    .build();

            final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            
            ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    connectivityManager.bindProcessToNetwork(network);
                    
                    if (context instanceof android.app.Activity) {
                        ((android.app.Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (listener != null) listener.onConnected();
                            }
                        });
                    }
                }

                @Override
                public void onUnavailable() {
                    super.onUnavailable();
                    if (context instanceof android.app.Activity) {
                        ((android.app.Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (listener != null) listener.onFailed();
                            }
                        });
                    }
                }
            };

            connectivityManager.requestNetwork(request, networkCallback);

        } else {
            Toast.makeText(context, "Unsupported Android SDK version context", Toast.LENGTH_SHORT).show();
        }
    }
}