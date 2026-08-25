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
import com.google.firebase.FirebaseApp;
import com.google.zxing.*;
import com.google.zxing.client.android.*;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class TransferProcessPreparetionsActivity extends AppCompatActivity {
	
	private String fontName = "";
	private String typeace = "";
	
	private LinearLayout main;
	private LinearLayout top_bar;
	private LinearLayout bottom;
	private LinearLayout body;
	private ImageView imageview1;
	private TextView textview1;
	private ImageView imageview2;
	private LinearLayout linear6;
	private ImageView imageview3;
	private LinearLayout linear1;
	private LinearLayout wifi_holder;
	private LinearLayout location_holder;
	private LinearLayout turn_on_hotspot;
	private LinearLayout all_enabled_txt_holder;
	private LinearLayout linear5;
	private LinearLayout next_btn;
	private LinearLayout icon_holder1;
	private TextView textview2;
	private LinearLayout turn_wifi_btn;
	private ImageView imageview4;
	private TextView textview3;
	private LinearLayout loc_icon_holder;
	private TextView textview4;
	private LinearLayout location_turn_btn;
	private ImageView imageview6;
	private TextView textview5;
	private LinearLayout linear8;
	private TextView textview10;
	private LinearLayout turn_hotspot_btn;
	private ImageView imageview7;
	private TextView textview11;
	private TextView textview9;
	private TextView textview8;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.transfer_process_preparetions);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main = findViewById(R.id.main);
		top_bar = findViewById(R.id.top_bar);
		bottom = findViewById(R.id.bottom);
		body = findViewById(R.id.body);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		imageview2 = findViewById(R.id.imageview2);
		linear6 = findViewById(R.id.linear6);
		imageview3 = findViewById(R.id.imageview3);
		linear1 = findViewById(R.id.linear1);
		wifi_holder = findViewById(R.id.wifi_holder);
		location_holder = findViewById(R.id.location_holder);
		turn_on_hotspot = findViewById(R.id.turn_on_hotspot);
		all_enabled_txt_holder = findViewById(R.id.all_enabled_txt_holder);
		linear5 = findViewById(R.id.linear5);
		next_btn = findViewById(R.id.next_btn);
		icon_holder1 = findViewById(R.id.icon_holder1);
		textview2 = findViewById(R.id.textview2);
		turn_wifi_btn = findViewById(R.id.turn_wifi_btn);
		imageview4 = findViewById(R.id.imageview4);
		textview3 = findViewById(R.id.textview3);
		loc_icon_holder = findViewById(R.id.loc_icon_holder);
		textview4 = findViewById(R.id.textview4);
		location_turn_btn = findViewById(R.id.location_turn_btn);
		imageview6 = findViewById(R.id.imageview6);
		textview5 = findViewById(R.id.textview5);
		linear8 = findViewById(R.id.linear8);
		textview10 = findViewById(R.id.textview10);
		turn_hotspot_btn = findViewById(R.id.turn_hotspot_btn);
		imageview7 = findViewById(R.id.imageview7);
		textview11 = findViewById(R.id.textview11);
		textview9 = findViewById(R.id.textview9);
		textview8 = findViewById(R.id.textview8);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				onBackPressed();
			}
		});
		
		next_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		turn_wifi_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		location_turn_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			final Window window = TransferProcessPreparetionsActivity.this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(0xFF0B0D0F);
		}
		_changeActivityFont("ooo");
		_rippleRoundStroke(turn_wifi_btn, "#141414", "#E0E0E0", 30, 0, "#000000");
		_rippleRoundStroke(location_turn_btn, "#141414", "#E0E0E0", 30, 0, "#000000");
		_rippleRoundStroke(next_btn, "#141414", "#E0E0E0", 30, 0, "#000000");
		// 1. Retrieve the selected files payload from the previous screen
		final String jsonPayload = getIntent().getStringExtra("selected_files_json");
		final String transferType = getIntent().getStringExtra("transferType");
		
		// 2. Disable next_btn by default on launch until requirements are satisfied
		next_btn.setEnabled(false);
		
		// 3. Dynamic UI Visibility Setup based on Transfer Role
		if (transferType != null && "receive".equals(transferType)) {
			// Receiver needs Wi-Fi and Location
			wifi_holder.setVisibility(android.view.View.VISIBLE);
			turn_on_hotspot.setVisibility(android.view.View.GONE);
		} else {
			// Sender needs Hotspot and Location
			wifi_holder.setVisibility(android.view.View.GONE);
			turn_on_hotspot.setVisibility(android.view.View.VISIBLE);
		}
		// Location is always visible initially for both roles
		location_holder.setVisibility(android.view.View.VISIBLE);
		
		// 4. Automatically trigger the runtime permission request prompt on launch if missing
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
			if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
				requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1002);
			}
		}
		
		// 5. Set up click listener for the Wi-Fi button
		turn_wifi_btn.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View _view) {
				android.content.Intent wifiIntent = new android.content.Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
				startActivity(wifiIntent);
			}
		});
		
		// 6. Set up click listener for the Hotspot button (Uses hardcoded raw system action string to prevent compilation failure)
		turn_hotspot_btn.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View _view) {
				android.content.Intent hotspotIntent = new android.content.Intent();
				
				// Use the explicit string signature to avoid compile errors across SDK variations
				if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
					hotspotIntent = new android.content.Intent("android.settings.TETHER_SETTINGS");
				} else {
					hotspotIntent.setClassName("com.android.settings", "com.android.settings.TetherSettings");
				}
				try {
					startActivity(hotspotIntent);
				} catch (Exception e) {
					// Ultimate fallback to generic wireless configuration if OEM uses a highly customized architecture
					try {
						startActivity(new android.content.Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
					} catch (Exception ex) {
						android.widget.Toast.makeText(getApplicationContext(), "Unable to open tethering settings automatically.", android.widget.Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		// 7. Set up click listener for the Location button
		location_turn_btn.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View _view) {
				if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && 
				checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
					requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1002);
				} else {
					android.content.Intent locIntent = new android.content.Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(locIntent);
				}
			}
		});
		
		// 8. Set up click listener for the Next Button with dynamic routing logic
		next_btn.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View _view) {
				android.content.Intent nextIntent = new android.content.Intent();
				
				// Pass the transfer type key to both execution environments
				nextIntent.putExtra("transferType", transferType);
				
				if (transferType != null && "receive".equals(transferType)) {
					nextIntent.setClass(getApplicationContext(), TransferRecieverActivity.class);
				} else {
					nextIntent.setClass(getApplicationContext(), TransferSendActivity.class);
					// Attach payload for the sender pipeline
					nextIntent.putExtra("selected_files_json", jsonPayload);
				}
				
				startActivity(nextIntent);
				overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
				finish();
			}
		});
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		// 1. Re-verify hardware statuses
		final String currentRole = getIntent().getStringExtra("transferType");
		
		boolean isWifiOn = false;
		android.net.wifi.WifiManager wm = (android.net.wifi.WifiManager) getApplicationContext().getSystemService(android.content.Context.WIFI_SERVICE);
		if (wm != null && wm.isWifiEnabled()) {
			isWifiOn = true;
		}
		
		// For Senders: Hotspot is assumed ready if Wi-Fi is disabled (as hotspot forces Wi-Fi off)
		boolean isHotspotOn = (wm != null && !wm.isWifiEnabled());
		
		boolean isLocationHardwareOn = false;
		android.location.LocationManager lm = (android.location.LocationManager) getApplicationContext().getSystemService(android.content.Context.LOCATION_SERVICE);
		if (lm != null) {
			boolean gps = lm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
			boolean network = lm.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER);
			if (gps || network) {
				isLocationHardwareOn = true;
			}
		}
		
		// 2. Verify runtime permission status
		boolean isPermissionGranted = true;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
			isPermissionGranted = checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED;
		}
		
		// 3. Animate layout changes smoothly
		androidx.transition.TransitionManager.beginDelayedTransition((android.view.ViewGroup) location_holder.getParent(), new androidx.transition.AutoTransition());
		
		// 4. Role-Based Evaluation Pipeline Management
		boolean canProceed = false;
		
		if (currentRole != null && "receive".equals(currentRole)) {
			// RECEIVER EVALUATION LOOP
			turn_on_hotspot.setVisibility(android.view.View.GONE);
			
			if (isWifiOn) {
				wifi_holder.setVisibility(android.view.View.GONE);
			} else {
				wifi_holder.setVisibility(android.view.View.VISIBLE);
			}
			
			if (isWifiOn && isLocationHardwareOn && isPermissionGranted) {
				canProceed = true;
			}
		} else {
			// SENDER EVALUATION LOOP
			wifi_holder.setVisibility(android.view.View.GONE);
			
			if (isHotspotOn) {
				turn_on_hotspot.setVisibility(android.view.View.GONE);
			} else {
				turn_on_hotspot.setVisibility(android.view.View.VISIBLE);
			}
			
			if (isHotspotOn && isLocationHardwareOn && isPermissionGranted) {
				canProceed = true;
			}
		}
		
		// Handle generic shared location card element state
		if (isLocationHardwareOn && isPermissionGranted) {
			location_holder.setVisibility(android.view.View.GONE);
		} else {
			location_holder.setVisibility(android.view.View.VISIBLE);
		}
		
		// 5. Final UI pipeline validation state adjustments
		if (canProceed) {
			all_enabled_txt_holder.setVisibility(android.view.View.VISIBLE);
			next_btn.setEnabled(true);
		} else {
			all_enabled_txt_holder.setVisibility(android.view.View.GONE);
			next_btn.setEnabled(false);
		}
	}
	
	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
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