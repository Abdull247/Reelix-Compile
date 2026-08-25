package com.error404.reelix;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
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
import android.widget.ProgressBar;
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
import com.google.firebase.FirebaseApp;
import com.google.zxing.*;
import com.google.zxing.client.android.*;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pGroup;

public class TransferSendActivity extends AppCompatActivity {
	
	private String fontName = "";
	private String typeace = "";
	
	private LinearLayout main;
	private LinearLayout top_bar;
	private LinearLayout linear3;
	private LinearLayout qr_code_holder;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout bottom_no_app_holder;
	private LinearLayout linear11;
	private ImageView imageview1;
	private TextView textview1;
	private LinearLayout inner_qr_code_holder;
	private RelativeLayout relativelayout1;
	private ImageView iv_qr_code;
	private LinearLayout top_qr_code_loading_holder;
	private ProgressBar progressbar1;
	private TextView wifi_ssid_txt;
	private TextView waiting_txt;
	private TextView textview6;
	private LinearLayout share_apk_qr_btn;
	private LinearLayout share_apk_link_btn;
	private TextView textview2;
	private TextView textview7;
	
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.transfer_send);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main = findViewById(R.id.main);
		top_bar = findViewById(R.id.top_bar);
		linear3 = findViewById(R.id.linear3);
		qr_code_holder = findViewById(R.id.qr_code_holder);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		bottom_no_app_holder = findViewById(R.id.bottom_no_app_holder);
		linear11 = findViewById(R.id.linear11);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		inner_qr_code_holder = findViewById(R.id.inner_qr_code_holder);
		relativelayout1 = findViewById(R.id.relativelayout1);
		iv_qr_code = findViewById(R.id.iv_qr_code);
		top_qr_code_loading_holder = findViewById(R.id.top_qr_code_loading_holder);
		progressbar1 = findViewById(R.id.progressbar1);
		wifi_ssid_txt = findViewById(R.id.wifi_ssid_txt);
		waiting_txt = findViewById(R.id.waiting_txt);
		textview6 = findViewById(R.id.textview6);
		share_apk_qr_btn = findViewById(R.id.share_apk_qr_btn);
		share_apk_link_btn = findViewById(R.id.share_apk_link_btn);
		textview2 = findViewById(R.id.textview2);
		textview7 = findViewById(R.id.textview7);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				onBackPressed();
			}
		});
		
		share_apk_qr_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		share_apk_link_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			final Window window = TransferSendActivity.this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(0xFF0B0D0F);
		}
		_changeActivityFont("ooo");
		bottom_no_app_holder.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF141414));
		_rippleRoundStroke(share_apk_link_btn, "#0B0D0F", "#EEEEEE", 30, 0, "#FFFFFF");
		_rippleRoundStroke(share_apk_qr_btn, "#0B0D0F", "#EEEEEE", 30, 0, "#FFFFFF");
		// Inside onCreate() method, after successfully initializing WifiDirectManager
		
		// 1. Retrieve incoming intent payloads
		final String selectedFilesJson = getIntent().getStringExtra("selected_files_json");
		
		// 2. Initialize and retain static P2P references
		WifiDirectInstance.activeP2pManager = new WifiDirectManager(this);
		
		// 3. Staging loading states
		iv_qr_code.setVisibility(android.view.View.GONE);
		top_qr_code_loading_holder.setVisibility(android.view.View.VISIBLE);
		wifi_ssid_txt.setText("Generating P2P Network Link...");
		
		// 4. Set up client connection listener for auto-navigation
		WifiDirectInstance.activeP2pManager.setClientConnectedListener(new WifiDirectManager.OnClientConnectedListener() {
			@Override
			public void onClientConnected(WifiP2pInfo connectionInfo, WifiP2pGroup groupInfo) {
				// Client successfully connected! Navigate to progress page
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// Prevent multiple navigations
						if (isFinishing() || isDestroyed()) return;
						
						// Show success toast for user feedback
						android.widget.Toast.makeText(getApplicationContext(), 
						"Client connected! Starting transfer...", 
						android.widget.Toast.LENGTH_SHORT).show();
						
						// Resolve the receiver's IP (the client that just connected)
						String receiverIp = "192.168.49.2"; // Default fallback for first client
						
						// Try to get the actual client IP from group info
						if (groupInfo != null && groupInfo.getClientList() != null && groupInfo.getClientList().size() > 0) {
							// Wi-Fi Direct clients are typically on 192.168.49.x
							// The first client is usually .2, second is .3, etc.
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
												// We are the GO at .1, so client is at .2
												String subnet = myIp.substring(0, myIp.lastIndexOf("."));
												receiverIp = subnet + ".2";
												break;
											}
										}
										break;
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						
						// Create intent for TransferProgressActivity
						android.content.Intent progressIntent = new android.content.Intent(
						TransferSendActivity.this, 
						TransferProgressActivity.class
						);
						
						// Add transfer type as "send"
						progressIntent.putExtra("transferType", "send");
						
						// Pass the selected files JSON to progress activity
						if (selectedFilesJson != null) {
							progressIntent.putExtra("selected_files_json", selectedFilesJson);
						}
						
						// CRITICAL: Pass the receiver's IP so sender knows where to connect
						progressIntent.putExtra("receiver_ip", receiverIp);
						
						// Pass group info for potential debugging/display
						if (groupInfo != null) {
							progressIntent.putExtra("group_ssid", groupInfo.getNetworkName());
							progressIntent.putExtra("client_count", groupInfo.getClientList().size());
						}
						
						if (connectionInfo != null) {
							progressIntent.putExtra("is_group_owner", connectionInfo.isGroupOwner);
						}
						
						// Start progress activity with clear top flag to prevent back-stack issues
						progressIntent.setFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP | 
						android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(progressIntent);
						
						// Optional: finish current activity to prevent returning to QR screen
						finish();
					}
				});
			}
			
			@Override
			public void onClientDisconnected() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// Optional: Show reconnect UI or error message
						android.widget.Toast.makeText(getApplicationContext(), 
						"Client disconnected unexpectedly", 
						android.widget.Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
		
		// 5. Initialize Wi-Fi Direct group creation
		WifiDirectInstance.activeP2pManager.createServerGroup(new WifiDirectManager.OnGroupReadyListener() {
			@Override
			public void onGroupCreated(final String ssid, final String passphrase) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						wifi_ssid_txt.setText(ssid);
						android.widget.Toast.makeText(getApplicationContext(), 
						"P2P Server Active: " + ssid, android.widget.Toast.LENGTH_SHORT).show();
						
						// Resolve the sender's IP to include in QR payload
						String senderIp = "192.168.49.1"; // Default Group Owner IP
						try {
							java.util.Enumeration<java.net.NetworkInterface> interfaces = java.net.NetworkInterface.getNetworkInterfaces();
							while (interfaces.hasMoreElements()) {
								java.net.NetworkInterface iface = interfaces.nextElement();
								if (iface.getName().contains("p2p")) {
									java.util.Enumeration<java.net.InetAddress> addresses = iface.getInetAddresses();
									while (addresses.hasMoreElements()) {
										java.net.InetAddress addr = addresses.nextElement();
										if (addr instanceof java.net.Inet4Address && !addr.isLoopbackAddress()) {
											senderIp = addr.getHostAddress();
											break;
										}
									}
									break;
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						// Include the sender's IP in the QR payload so receiver knows where to target
						final String qrStringPayload = "REELIX_CONNECT;SSID:" + ssid + ";PASS:" + passphrase + ";IP:" + senderIp + ";";
						
						new Thread(new Runnable() {
							@Override
							public void run() {
								final android.graphics.Bitmap qrBitmap = 
								com.error404.reelix.QRCodeHelper.generateQRCodeBitmap(qrStringPayload, 400, 400);
								
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										androidx.transition.TransitionManager.beginDelayedTransition(
										inner_qr_code_holder, 
										new androidx.transition.AutoTransition()
										);
										
										if (qrBitmap != null) {
											iv_qr_code.setImageBitmap(qrBitmap);
										} else {
											iv_qr_code.setImageResource(R.drawable.qr_example);
										}
										
										top_qr_code_loading_holder.setVisibility(android.view.View.GONE);
										iv_qr_code.setVisibility(android.view.View.VISIBLE);
									}
								});
							}
						}).start();
					}
				});
			}
			
			@Override
			public void onGroupFailed(int reason) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						top_qr_code_loading_holder.setVisibility(android.view.View.GONE);
						wifi_ssid_txt.setText("P2P Engine Failed");
						android.widget.Toast.makeText(getApplicationContext(), 
						"Failed to generate local link group. Reason: " + reason, 
						android.widget.Toast.LENGTH_LONG).show();
					}
				});
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		if (com.error404.reelix.WifiDirectInstance.activeP2pManager != null) {
			com.error404.reelix.WifiDirectInstance.activeP2pManager.teardownGroup();
			com.error404.reelix.WifiDirectInstance.activeP2pManager = null;
		}
		finish();
		overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (com.error404.reelix.WifiDirectInstance.activeP2pManager != null) {
			com.error404.reelix.WifiDirectInstance.activeP2pManager.teardownGroup();
			com.error404.reelix.WifiDirectInstance.activeP2pManager = null;
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