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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.*;
import com.google.zxing.client.android.*;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import androidx.core.view.WindowCompat;

public class MainActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private String fontName = "";
	private String typeace = "";
	
	private LinearLayout main;
	private RelativeLayout relativelayout1;
	private ImageView background_banner;
	private LinearLayout top_content_layer;
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private ImageView app_logo;
	private TextView textview_R;
	private TextView textview_E;
	private TextView textview_E2;
	private TextView textview_L;
	private TextView textview_I;
	private TextView textview_X;
	
	private Intent intent = new Intent();
	private TimerTask timer;
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main = findViewById(R.id.main);
		relativelayout1 = findViewById(R.id.relativelayout1);
		background_banner = findViewById(R.id.background_banner);
		top_content_layer = findViewById(R.id.top_content_layer);
		linear1 = findViewById(R.id.linear1);
		linear3 = findViewById(R.id.linear3);
		linear2 = findViewById(R.id.linear2);
		linear4 = findViewById(R.id.linear4);
		app_logo = findViewById(R.id.app_logo);
		textview_R = findViewById(R.id.textview_R);
		textview_E = findViewById(R.id.textview_E);
		textview_E2 = findViewById(R.id.textview_E2);
		textview_L = findViewById(R.id.textview_L);
		textview_I = findViewById(R.id.textview_I);
		textview_X = findViewById(R.id.textview_X);
		auth = FirebaseAuth.getInstance();
		net = new RequestNetwork(this);
		
		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		getWindow().setStatusBarColor(Color.TRANSPARENT);
		
		// 1. Get the path to: /storage/emulated/0/Android/data/your.package.name/files/
		java.io.File baseDir = this.getExternalFilesDir(null);
		
		if (baseDir != null) {
			// 2. Define your subfolder name (e.g., "Videos")
			java.io.File appFolder = new java.io.File(baseDir, "Downloads");
			
			// 3. Create the directory if it doesn't exist yet
			if (!appFolder.exists()) {
				boolean isCreated = appFolder.mkdirs();
				if (isCreated) {
					android.util.Log.d("StorageInit", "App folder initialized: " + appFolder.getAbsolutePath());
				} else {
					android.util.Log.e("StorageInit", "Failed to initialize app folder.");
				}
			}
		}
		
		_changeActivityFont("ooo");
		Animation animationtextview_m = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
		animationtextview_m.setDuration(1000);
		textview_R.startAnimation(animationtextview_m);
		
		
		Animation animationtextview_o = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
		animationtextview_o.setDuration(1300);
		textview_E.startAnimation(animationtextview_o);
		
		
		Animation animationtextview_v = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
		animationtextview_v.setDuration(1600);
		textview_E2.startAnimation(animationtextview_v);
		
		
		Animation animationtextview_i = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
		animationtextview_i.setDuration(1900);
		textview_L.startAnimation(animationtextview_i);
		
		
		Animation animationtextview_e = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
		animationtextview_e.setDuration(2200);
		textview_I.startAnimation(animationtextview_e);
		
		
		Animation animationtextview_1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
		animationtextview_1.setDuration(2500);
		textview_X.startAnimation(animationtextview_1);
		
		if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
			timer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							intent.setClass(getApplicationContext(), FeedActivity.class);
							startActivity(intent);
							overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
						}
					});
				}
			};
			_timer.schedule(timer, (int)(3500));
		} else {
			timer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							intent.setClass(getApplicationContext(), WelcomeActivity.class);
							startActivity(intent);
							overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
						}
					});
				}
			};
			_timer.schedule(timer, (int)(3500));
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
	
}