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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
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
import com.budiyev.android.codescanner.CodeScannerView;
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
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class TransferRecieverActivity extends AppCompatActivity {
	
	private LinearLayout main;
	private CodeScannerView scannerview;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.transfer_reciever);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main = findViewById(R.id.main);
		scannerview = findViewById(R.id.scannerview);
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			final Window window = TransferRecieverActivity.this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(0xFF0B0D0F);
		}
		// 1. Find the Scanner View layout container component
		com.budiyev.android.codescanner.CodeScannerView scannerView = findViewById(R.id.scannerview);
		
		// 2. Initialize the centralized code scanner instance safely
		com.error404.reelix.ScannerInstance.mCodeScanner = new com.budiyev.android.codescanner.CodeScanner(this, scannerView);
		
		// 3. Define execution callbacks for matrix code data detection
		com.error404.reelix.ScannerInstance.mCodeScanner.setDecodeCallback(new com.budiyev.android.codescanner.DecodeCallback() {
			@Override
			public void onDecoded(@androidx.annotation.NonNull final com.google.zxing.Result result) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						String rawResult = result.getText();
						
						// Expected payload structure: REELIX_CONNECT;SSID:DIRECT-xx-Name;PASS:xyz123;IP:192.168.49.x;
						if (rawResult.startsWith("REELIX_CONNECT")) {
							try {
								// 1. Extract the SSID parameter string
								String ssidPart = rawResult.substring(rawResult.indexOf("SSID:") + 5);
								final String targetSsid = ssidPart.substring(0, ssidPart.indexOf(";"));
								
								// 2. Extract the WPA2 Password parameter string 
								String passPart = rawResult.substring(rawResult.indexOf("PASS:") + 5);
								final String targetPassword = passPart.substring(0, passPart.indexOf(";"));
								
								// 3. Extract the sender's IP address for return communication
								String senderIpString = "192.168.49.1"; // Default GO IP
								if (rawResult.contains("IP:")) {
									String ipPart = rawResult.substring(rawResult.indexOf("IP:") + 3);
									int endIndex = ipPart.indexOf(";");
									if (endIndex > 0) {
										senderIpString = ipPart.substring(0, endIndex);
									} else {
										senderIpString = ipPart.trim();
									}
								}
								final String senderIp = senderIpString;
								
								android.widget.Toast.makeText(getApplicationContext(), "P2P Target Identified: " + targetSsid, android.widget.Toast.LENGTH_SHORT).show();
								
								// 4. Inject parameters into our connection class helper
								com.error404.reelix.WifiConnector.connectToHotspot(TransferRecieverActivity.this, targetSsid, targetPassword, new com.error404.reelix.WifiConnector.OnConnectionListener() {
									@Override
									public void onConnected() {
										android.widget.Toast.makeText(getApplicationContext(), "P2P Group Connected Successfully!", android.widget.Toast.LENGTH_SHORT).show();
										
										// Determine our own IP to know if we're the Group Owner
										String myIp = "192.168.49.1";
										try {
											java.util.Enumeration<java.net.NetworkInterface> interfaces = java.net.NetworkInterface.getNetworkInterfaces();
											while (interfaces.hasMoreElements()) {
												java.net.NetworkInterface iface = interfaces.nextElement();
												if (iface.getName().contains("p2p")) {
													java.util.Enumeration<java.net.InetAddress> addresses = iface.getInetAddresses();
													while (addresses.hasMoreElements()) {
														java.net.InetAddress addr = addresses.nextElement();
														if (addr instanceof java.net.Inet4Address && !addr.isLoopbackAddress()) {
															myIp = addr.getHostAddress();
															break;
														}
													}
													break;
												}
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
										
										// Route downstream into your background progress view layer
										android.content.Intent progressIntent = new android.content.Intent();
										progressIntent.setClass(getApplicationContext(), TransferProgressActivity.class);
										progressIntent.putExtra("transferType", "receive");
										
										// Pass the sender's IP so receiver can also send confirmations back if needed
										// AND pass our own IP in case the receiver is actually the Group Owner
										progressIntent.putExtra("sender_ip", senderIp);
										progressIntent.putExtra("my_ip", myIp);
										progressIntent.putExtra("receiver_ip", senderIp); // The sender becomes our target for ACKs
										
										startActivity(progressIntent);
										overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
										finish();
									}
									
									@Override
									public void onFailed() {
										android.widget.Toast.makeText(getApplicationContext(), "Connection rejected or timed out.", android.widget.Toast.LENGTH_SHORT).show();
										if (com.error404.reelix.ScannerInstance.mCodeScanner != null) {
											com.error404.reelix.ScannerInstance.mCodeScanner.startPreview();
										}
									}
								});
								
							} catch (Exception e) {
								android.widget.Toast.makeText(getApplicationContext(), "Error decoding encrypted pairing payload", android.widget.Toast.LENGTH_SHORT).show();
								if (com.error404.reelix.ScannerInstance.mCodeScanner != null) {
									com.error404.reelix.ScannerInstance.mCodeScanner.startPreview();
								}
							}
						} else {
							android.widget.Toast.makeText(getApplicationContext(), "Invalid Reelix QR signature", android.widget.Toast.LENGTH_SHORT).show();
							if (com.error404.reelix.ScannerInstance.mCodeScanner != null) {
								com.error404.reelix.ScannerInstance.mCodeScanner.startPreview();
							}
						}
					}
				});
			}
		});
		
		// 4. Tap scanning surface container to enforce dynamic camera focus reset
		scannerView.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View view) {
				if (com.error404.reelix.ScannerInstance.mCodeScanner != null) {
					com.error404.reelix.ScannerInstance.mCodeScanner.startPreview();
				}
			}
		});
		
		// 5. Native runtime environment security camera checks
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
			if (checkSelfPermission(android.Manifest.permission.CAMERA) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
				requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 1005);
			} else {
				if (com.error404.reelix.ScannerInstance.mCodeScanner != null) {
					com.error404.reelix.ScannerInstance.mCodeScanner.startPreview();
				}
			}
		} else {
			if (com.error404.reelix.ScannerInstance.mCodeScanner != null) {
				com.error404.reelix.ScannerInstance.mCodeScanner.startPreview();
			}
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
			if (checkSelfPermission(android.Manifest.permission.CAMERA) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
				if (com.error404.reelix.ScannerInstance.mCodeScanner != null) {
					com.error404.reelix.ScannerInstance.mCodeScanner.startPreview();
				}
			}
		} else {
			if (com.error404.reelix.ScannerInstance.mCodeScanner != null) {
				com.error404.reelix.ScannerInstance.mCodeScanner.startPreview();
			}
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if (com.error404.reelix.ScannerInstance.mCodeScanner != null) {
			com.error404.reelix.ScannerInstance.mCodeScanner.releaseResources();
		}
	}
}