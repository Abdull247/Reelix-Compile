package com.error404.reelix;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.annotation.experimental.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.*;
import androidx.asynclayoutinflater.*;
import androidx.coordinatorlayout.*;
import androidx.core.*;
import androidx.cursoradapter.*;
import androidx.customview.*;
import androidx.documentfile.*;
import androidx.drawerlayout.*;
import androidx.exifinterface.*;
import androidx.fragment.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.interpolator.*;
import androidx.legacy.coreui.*;
import androidx.legacy.coreutils.*;
import androidx.lifecycle.*;
import androidx.lifecycle.livedata.*;
import androidx.lifecycle.livedata.core.*;
import androidx.lifecycle.runtime.*;
import androidx.lifecycle.viewmodel.*;
import androidx.loader.*;
import androidx.localbroadcastmanager.*;
import androidx.print.*;
import androidx.slidingpanelayout.*;
import androidx.swiperefreshlayout.*;
import androidx.vectordrawable.*;
import androidx.versionedparcelable.*;
import androidx.viewpager.*;
import com.budiyev.android.codescanner.*;
import com.bumptech.glide.*;
import com.bumptech.glide.gifdecoder.*;
import com.facebook.shimmer.*;
import com.google.android.exoplayer2.common.*;
import com.google.android.exoplayer2.database.*;
import com.google.android.exoplayer2.decoder.*;
import com.google.android.exoplayer2.ext.workmanager.*;
import com.google.android.exoplayer2.extractor.*;
import com.google.android.exoplayer2.source.hls.*;
import com.google.android.exoplayer2.ui.*;
import com.google.android.exoplayer2.upstream.*;
import com.google.android.material.*;
import com.google.android.material.card.*;
import com.google.firebase.FirebaseApp;
import com.google.zxing.*;
import com.google.zxing.client.android.*;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class TransferProgressActivity extends AppCompatActivity {
	
	private String fontName = "";
	private String typeace = "";
	
	private LinearLayout main;
	private LinearLayout top_bar;
	private LinearLayout linear5;
	private FrameLayout mainFrame_layout;
	private LinearLayout topper_bottom_content_holder;
	private ImageView imageview1;
	private TextView textview1;
	private LinearLayout cat_chip1;
	private LinearLayout cat_chip_2;
	private TextView cat_txt1;
	private TextView textview2;
	private MaterialCardView materialCardView1;
	private LinearLayout button_send_more;
	private LinearLayout linear10;
	private ImageView imageview2;
	private TextView wifi_user_ssid;
	private TextView disconnect_txt;
	private TextView textview3;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.transfer_progress);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main = findViewById(R.id.main);
		top_bar = findViewById(R.id.top_bar);
		linear5 = findViewById(R.id.linear5);
		mainFrame_layout = findViewById(R.id.mainFrame_layout);
		topper_bottom_content_holder = findViewById(R.id.topper_bottom_content_holder);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		cat_chip1 = findViewById(R.id.cat_chip1);
		cat_chip_2 = findViewById(R.id.cat_chip_2);
		cat_txt1 = findViewById(R.id.cat_txt1);
		textview2 = findViewById(R.id.textview2);
		materialCardView1 = findViewById(R.id.materialCardView1);
		button_send_more = findViewById(R.id.button_send_more);
		linear10 = findViewById(R.id.linear10);
		imageview2 = findViewById(R.id.imageview2);
		wifi_user_ssid = findViewById(R.id.wifi_user_ssid);
		disconnect_txt = findViewById(R.id.disconnect_txt);
		textview3 = findViewById(R.id.textview3);
		
		button_send_more.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			final Window window = TransferProgressActivity.this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(0xFF0B0D0F);
		}
		_changeActivityFont("ooo");
		_rippleRoundStroke(cat_chip1, "#172944", "#E0E0E0", 60, 0, "#000000");
		_rippleRoundStroke(cat_chip_2, "#141414", "#E0E0E0", 30, 0, "#000000");
		_rippleRoundStroke(button_send_more, "#172944", "#E0E0E0", 60, 0, "#000000");
		// 1. Retrieve incoming intent payloads to check the role
		final String transferType = getIntent().getStringExtra("transferType");
		final String jsonPayload = getIntent().getStringExtra("selected_files_json");
		
		// 2. Initialize the Sender List in the State Manager if this device is sending
		if ("send".equals(transferType) && jsonPayload != null) {
			com.error404.reelix.TransferStateManager.send_trans_list.clear();
			try {
				org.json.JSONArray array = new org.json.JSONArray(jsonPayload);
				for (int i = 0; i < array.length(); i++) {
					org.json.JSONObject obj = array.getJSONObject(i);
					
					java.util.HashMap<String, Object> map = new java.util.HashMap<>();
					map.put("title", obj.optString("movieTitle", "Unknown Title"));
					map.put("video_path", obj.optString("filePath", ""));
					map.put("size", obj.optString("size", "0MB"));
					map.put("cover_url", obj.optString("imageUrl", ""));
					
					// Set initial transfer tracking states
					map.put("progress", "0"); // Percentage (0-100)
					map.put("status", "Waiting to send..."); 
					
					com.error404.reelix.TransferStateManager.send_trans_list.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("receive".equals(transferType)) {
			// If receiving, ensure the map is cleared so it's ready for incoming data streams
			com.error404.reelix.TransferStateManager.receive_trans_map.clear();
		}
		
		// 3. Evaluate and initialize default fragment states dynamically
		if (transferType != null && "receive".equals(transferType)) {
			// Active UI: Received Fragment Focus State
			_rippleRoundStroke(cat_chip1, "#141414", "#E0E0E0", 30, 0, "#000000");
			_rippleRoundStroke(cat_chip_2, "#172944", "#E0E0E0", 60, 0, "#000000");
			
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.mainFrame_layout, new TransferRecieveFragmentFragmentActivity())
			.commit();
		} else {
			// Default Fallback / Active UI: Sent Fragment Focus State
			_rippleRoundStroke(cat_chip1, "#172944", "#E0E0E0", 60, 0, "#000000");
			_rippleRoundStroke(cat_chip_2, "#141414", "#E0E0E0", 30, 0, "#000000");
			
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.mainFrame_layout, new TransferSendFragmentFragmentActivity())
			.commit();
		}
		
		// 4. Click Listener for cat_chip1 (Sent)
		cat_chip1.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				_rippleRoundStroke(cat_chip1, "#172944", "#E0E0E0", 60, 0, "#000000");
				_rippleRoundStroke(cat_chip_2, "#141414", "#E0E0E0", 30, 0, "#000000");
				
				getSupportFragmentManager().beginTransaction()
				.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
				.replace(R.id.mainFrame_layout, new TransferSendFragmentFragmentActivity())
				.commit();
			}
		});
		
		// 5. Click Listener for cat_chip_2 (Received)
		cat_chip_2.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				_rippleRoundStroke(cat_chip1, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_2, "#172944", "#E0E0E0", 60, 0, "#000000");
				
				getSupportFragmentManager().beginTransaction()
				.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
				.replace(R.id.mainFrame_layout, new TransferRecieveFragmentFragmentActivity())
				.commit();
			}
		});
		
		// ─── PART 2: Wi-Fi SSID Display ───────────────────────────────────────────
		android.net.wifi.WifiManager wifiManager =
		(android.net.wifi.WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
		android.net.wifi.p2p.WifiP2pManager p2pManager =
		(android.net.wifi.p2p.WifiP2pManager) getSystemService(WIFI_P2P_SERVICE);
		android.net.wifi.p2p.WifiP2pManager.Channel p2pChannel =
		p2pManager.initialize(this, getMainLooper(), null);
		
		// Try to get P2P group SSID first, fall back to regular Wi-Fi SSID
		p2pManager.requestGroupInfo(p2pChannel, new android.net.wifi.p2p.WifiP2pManager.GroupInfoListener() {
			@Override
			public void onGroupInfoAvailable(android.net.wifi.p2p.WifiP2pGroup group) {
				if (group != null && group.getNetworkName() != null && !group.getNetworkName().isEmpty()) {
					// We're in a Wi-Fi Direct group — use the P2P network name
					final String networkName = group.getNetworkName();
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							wifi_user_ssid.setText(networkName);
						}
					});
				} else {
					// Not in a P2P group — check intent for SSID passed from previous activity
					String intentSsid = getIntent().getStringExtra("group_ssid");
					if (intentSsid != null && !intentSsid.isEmpty()) {
						final String finalSsid = intentSsid;
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								wifi_user_ssid.setText(finalSsid);
							}
						});
					} else {
						// Fallback to regular Wi-Fi SSID
						if (android.os.Build.VERSION.SDK_INT >= 26) {
							if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
								android.net.wifi.WifiInfo info = wifiManager.getConnectionInfo();
								String ssid = info.getSSID();
								if (ssid != null && !ssid.equals("<unknown ssid>")) {
									ssid = ssid.replace("\"", "");
									final String finalSsid = ssid;
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											wifi_user_ssid.setText(finalSsid);
										}
									});
								} else {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											wifi_user_ssid.setText("Connected (SSID hidden)");
										}
									});
								}
							} else {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										wifi_user_ssid.setText("Permission needed for SSID");
									}
								});
								requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
							}
						} else {
							android.net.wifi.WifiInfo info = wifiManager.getConnectionInfo();
							final String ssid = info.getSSID().replace("\"", "");
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									wifi_user_ssid.setText(ssid);
								}
							});
						}
					}
				}
			}
		});
		
		// ─── PART 1: Trigger Network Engine ───────────────────────────────────────
		if ("receive".equals(transferType)) {
			// RECEIVER: Start server socket FIRST — always bind before sender connects
			String saveDir = getExternalFilesDir(null) != null
			? getExternalFilesDir(null).getAbsolutePath()
			: android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Reelix/Downloads";
			
			// Show status that we're ready
			android.widget.Toast.makeText(TransferProgressActivity.this, 
			"Listening for incoming transfer...", 
			android.widget.Toast.LENGTH_SHORT).show();
			
			com.error404.reelix.TransferNetworkEngine.startReceiving(saveDir, TransferProgressActivity.this);
			
		} else if ("send".equals(transferType) && jsonPayload != null) {
			// SENDER: Get target IP from intent — passed by TransferSendActivity
			final String receiverIpFromIntent = getIntent().getStringExtra("receiver_ip");
			
			if (receiverIpFromIntent != null && !receiverIpFromIntent.isEmpty()) {
				// Direct IP passed from TransferSendActivity's onClientConnected callback
				android.widget.Toast.makeText(TransferProgressActivity.this, 
				"Connecting to receiver at " + receiverIpFromIntent + "...", 
				android.widget.Toast.LENGTH_SHORT).show();
				
				com.error404.reelix.TransferNetworkEngine.startSending(receiverIpFromIntent, jsonPayload, TransferProgressActivity.this);
				
			} else {
				// No IP in intent — try to resolve from P2P group info
				p2pManager.requestGroupInfo(p2pChannel, new android.net.wifi.p2p.WifiP2pManager.GroupInfoListener() {
					@Override
					public void onGroupInfoAvailable(android.net.wifi.p2p.WifiP2pGroup group) {
						String targetIp = null;
						
						if (group != null) {
							if (group.isGroupOwner()) {
								// We are the Group Owner, receiver is a client
								// Client IPs are typically 192.168.49.2
								targetIp = "192.168.49.2";
								
								// Try to resolve actual IP from p2p interface
								try {
									java.util.Enumeration<java.net.NetworkInterface> interfaces = java.net.NetworkInterface.getNetworkInterfaces();
									while (interfaces.hasMoreElements()) {
										java.net.NetworkInterface iface = interfaces.nextElement();
										if (iface.getName().contains("p2p")) {
											java.util.Enumeration<java.net.InetAddress> addresses = iface.getInetAddresses();
											while (addresses.hasMoreElements()) {
												java.net.InetAddress addr = addresses.nextElement();
												if (addr instanceof java.net.Inet4Address && !addr.isLoopbackAddress()) {
													String myIp = addr.getHostAddress();
													String subnet = myIp.substring(0, myIp.lastIndexOf("."));
													targetIp = subnet + ".2";
													break;
												}
											}
											break;
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							} else {
								// We are a client, Group Owner (receiver) is at 192.168.49.1
								targetIp = "192.168.49.1";
							}
						}
						
						if (targetIp != null && !targetIp.isEmpty()) {
							final String finalIp = targetIp;
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									android.widget.Toast.makeText(TransferProgressActivity.this, 
									"Connecting to receiver at " + finalIp + "...", 
									android.widget.Toast.LENGTH_SHORT).show();
								}
							});
							com.error404.reelix.TransferNetworkEngine.startSending(finalIp, jsonPayload, TransferProgressActivity.this);
						} else {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									android.widget.Toast.makeText(TransferProgressActivity.this, 
									"Could not resolve receiver IP. Ensure both devices are connected.", 
									android.widget.Toast.LENGTH_LONG).show();
									// Update all send items to show failure
									for (java.util.HashMap<String, Object> map : com.error404.reelix.TransferStateManager.send_trans_list) {
										map.put("status", "Failed: No receiver IP");
									}
									com.error404.reelix.TransferStateManager.notifySendUpdated();
								}
							});
						}
					}
				});
			}
		}
	}
	
	public void _changeActivityFont(final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".ttf"));
		overrideFonts(this,getWindow().getDecorView()); 
	} 
	private void overrideFonts(final android.content.Context context, final View v) {
		
		try {
			Typeface 
			typeace = Typeface.createFromAsset(getAssets(), fontName);;
			if ((v instanceof ViewGroup)) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0;
				i < vg.getChildCount();
				i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			} else {
				if ((v instanceof TextView)) {
					((TextView) v).setTypeface(typeace);
				} else {
					if ((v instanceof EditText )) {
						((EditText) v).setTypeface(typeace);
					} else {
						if ((v instanceof Button)) {
							((Button) v).setTypeface(typeace);
						}
					}
				}
			}
		}
		catch(Exception e)
		
		{
			SketchwareUtil.showMessage(getApplicationContext(), "Error Loading Font");
		};
	}
	
	
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float) _round);
		GG.setStroke((int) _stroke, Color.parseColor("#" + _strokeclr.replace("#", "")));
		_view.setElevation(0f);
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(
		new android.content.res.ColorStateList(
		new int[][]{ new int[]{} }, 
		new int[]{ Color.parseColor("#22000000") }
		), 
		GG,  
		null 
		);
		_view.setBackground(RE);
	}
	
}