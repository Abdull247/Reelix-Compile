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
import android.webkit.WebSettings;
import android.webkit.WebView;
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
import eightbitlab.com.blurview.BlurView;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class MoviePlayerPageActivity extends AppCompatActivity {
	
	private String fontName = "";
	private String typeace = "";
	
	private LinearLayout main;
	private RelativeLayout relativelayout2;
	private WebView webview_player;
	private BlurView top_overlay;
	private LinearLayout linear2;
	private LinearLayout linear1;
	private LinearLayout top_bar;
	private LinearLayout back_hold;
	private LinearLayout linear3;
	private ImageView imageview1;
	private TextView movieName_txt;
	
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.movie_player_page);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main = findViewById(R.id.main);
		relativelayout2 = findViewById(R.id.relativelayout2);
		webview_player = findViewById(R.id.webview_player);
		webview_player.getSettings().setJavaScriptEnabled(true);
		webview_player.getSettings().setSupportZoom(true);
		top_overlay = findViewById(R.id.top_overlay);
		linear2 = findViewById(R.id.linear2);
		linear1 = findViewById(R.id.linear1);
		top_bar = findViewById(R.id.top_bar);
		back_hold = findViewById(R.id.back_hold);
		linear3 = findViewById(R.id.linear3);
		imageview1 = findViewById(R.id.imageview1);
		movieName_txt = findViewById(R.id.movieName_txt);
		net = new RequestNetwork(this);
		
		webview_player.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		back_hold.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				onBackPressed();
			}
		});
		
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
	}
	
	private void initializeLogic() {
		// 1. Hide the Action Bar if your theme includes one
		if (getSupportActionBar() != null) {
			getSupportActionBar().hide();
		}
		
		// 2. Apply Immersive Fullscreen Mode
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
			// Modern Android 11 (API 30) to Android 14+ approach
			final android.view.WindowInsetsController controller = getWindow().getInsetsController();
			if (controller != null) {
				// Hide both Status Bar and Navigation Bar (system bars)
				controller.hide(android.view.WindowInsets.Type.systemBars());
				// Make bars reappear temporarily if user swipes from the edge, then auto-hide again
				controller.setSystemBarsBehavior(android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
			}
		} else {
			// Legacy fallback for older Android versions (API 29 and below)
			getWindow().getDecorView().setSystemUiVisibility(
			android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
			| android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
			| android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
			| android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
			| android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
			| android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
			);
		}
		
		top_bar.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)60, 0xFF212121));
		_changeActivityFont("ooo");
		// =================================================================
		// 1. IMMERSIVE FULLSCREEN & WAKE LOCK CONFIGURATION
		// =================================================================
		if (getSupportActionBar() != null) {
			getSupportActionBar().hide();
		}
		
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
			final android.view.WindowInsetsController controller = getWindow().getInsetsController();
			if (controller != null) {
				controller.hide(android.view.WindowInsets.Type.systemBars());
				controller.setSystemBarsBehavior(android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
			}
		} else {
			getWindow().getDecorView().setSystemUiVisibility(
			android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
			| android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
			| android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
			| android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
			| android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
			| android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
			);
		}
		
		getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		
		// =================================================================
		// 2. BACK BUTTON STYLING (ISOLATED SCOPE)
		// =================================================================
		{
			android.graphics.drawable.GradientDrawable btnNormalShape = new android.graphics.drawable.GradientDrawable();
			btnNormalShape.setShape(android.graphics.drawable.GradientDrawable.OVAL);
			btnNormalShape.setColor(android.graphics.Color.parseColor("#E61A1A1A")); 
			
			android.graphics.drawable.GradientDrawable btnMaskShape = new android.graphics.drawable.GradientDrawable();
			btnMaskShape.setShape(android.graphics.drawable.GradientDrawable.OVAL);
			btnMaskShape.setColor(android.graphics.Color.WHITE); 
			
			int btnRippleColor = android.graphics.Color.parseColor("#40FFFFFF");
			
			android.graphics.drawable.RippleDrawable btnRippleDrawable = new android.graphics.drawable.RippleDrawable(
			android.content.res.ColorStateList.valueOf(btnRippleColor),
			btnNormalShape,
			btnMaskShape
			);
			
			back_hold.setBackground(btnRippleDrawable);
			back_hold.setClickable(true);
			back_hold.setFocusable(true);
		}
		
		
		// =================================================================
		// 3. EIGHTBITLAB BLURVIEW GREY FROSTED GLASS SETUP (ISOLATED SCOPE)
		// =================================================================
		{
			android.view.ViewGroup playerRootView = (android.view.ViewGroup) findViewById(R.id.relativelayout2);
			android.graphics.drawable.Drawable playerWindowBackground = getWindow().getDecorView().getBackground();
			
			top_overlay.setupWith(playerRootView)
			.setFrameClearDrawable(playerWindowBackground)
			.setBlurAlgorithm(new eightbitlab.com.blurview.RenderScriptBlur(this))
			.setBlurRadius(10f)
			.setBlurAutoUpdate(false);
			
			top_overlay.setOverlayColor(android.graphics.Color.parseColor("#A9A9A9")); 
		}
		
		
		
		// =================================================================
		// 4. OVERLAY AUTOMATIC DISMISS & MANUAL SLIDE TOGGLE LOGIC
		// =================================================================
		top_overlay.post(new Runnable() {
			@Override
			public void run() {
				final int overlayHeight = top_overlay.getHeight();
				final int handleBarHeight = top_bar.getHeight();
				int extraVisiblePixels = 30; 
				
				final float hiddenY = -(overlayHeight - handleBarHeight - extraVisiblePixels);
				
				top_overlay.setTranslationY(hiddenY);
				final boolean[] isMenuHidden = {true};
				
				top_bar.setOnClickListener(new android.view.View.OnClickListener() {
					@Override
					public void onClick(android.view.View v) {
						if (isMenuHidden[0]) {
							top_overlay.animate()
							.translationY(0f)
							.setDuration(350)
							.setInterpolator(new android.view.animation.DecelerateInterpolator())
							.start();
							isMenuHidden[0] = false;
						} else {
							top_overlay.animate()
							.translationY(hiddenY)
							.setDuration(350)
							.setInterpolator(new android.view.animation.AccelerateInterpolator())
							.start();
							isMenuHidden[0] = true;
						}
					}
				});
			}
		});
		
		
		// =================================================================
		// 5. ANTI-AD FIREWALL & OPTIMIZED WEBVIEW CLIENT ENGINE
		// =================================================================
		webview_player.getSettings().setJavaScriptEnabled(true);
		webview_player.getSettings().setAllowFileAccess(true);
		webview_player.getSettings().setDomStorageEnabled(true); 
		webview_player.getSettings().setDatabaseEnabled(true);
		webview_player.getSettings().setMediaPlaybackRequiresUserGesture(false);
		
		webview_player.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
		webview_player.getSettings().setSupportMultipleWindows(false);
		
		webview_player.setLayerType(android.view.View.LAYER_TYPE_HARDWARE, null);
		webview_player.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Mobile Safari/537.36");
		
		webview_player.setWebViewClient(new android.webkit.WebViewClient() {
			
			// PART A: Strict Redirect Filter (Kills the ad popup intent instantly)
			@Override
			public boolean shouldOverrideUrlLoading(android.webkit.WebView view, android.webkit.WebResourceRequest request) {
				String url = request.getUrl().toString().toLowerCase();
				
				// STAGE 1: Explicit Whitelist — Included general "embed/" to accept both movie and tv layouts
				if (url.contains("vidsrc") || url.contains("vsembed") || url.contains("embed/") || url.contains("vidlink")) {
					return false; 
				}
				
				// STAGE 2: Explicit Blacklist — Block reported domains immediately 
				if (url.contains("betway") || url.contains("snowysnet") || url.contains("mavisnaives") 
				|| url.contains("zeloru") || url.contains("click?key=") || url.contains("zone=")) {
					return true; 
				}
				
				// STAGE 3: Default Guard — If it's a completely foreign url, block it
				return true; 
			}
			
			// PART B: Network Resource Interceptor (Prevents lag/freezing by blocking requests)
			@Override
			public android.webkit.WebResourceResponse shouldInterceptRequest(android.webkit.WebView view, android.webkit.WebResourceRequest request) {
				String url = request.getUrl().toString().toLowerCase();
				
				if (url.contains("ads") || url.contains("adsystem") || url.contains("popads") 
				|| url.contains("doubleclick") || url.contains("adsterra") || url.contains("onclick")
				|| url.contains("betway") || url.contains("snowysnet") || url.contains("mavisnaives") 
				|| url.contains("zeloru") || url.contains("/click?") || url.contains("zone=") 
				|| url.contains("campaign=") || url.contains("coin") || url.contains("miner")) {
					
					return new android.webkit.WebResourceResponse("text/plain", "UTF-8", 
					new java.io.ByteArrayInputStream("".getBytes()));
				}
				return super.shouldInterceptRequest(view, request);
			}
		});
		
		webview_player.setWebChromeClient(new android.webkit.WebChromeClient());
		
		
		// =================================================================
		// 6. PIPELINE DATA TRANSFER FETCH & LAUNCH (ISOLATED SCOPE)
		// =================================================================
		{
			String absolutePlaybackUrl = "";
			
			// Check if a ready-to-play explicit episode URL was passed down from the TV list
			if (getIntent() != null && getIntent().hasExtra("episode_video_url")) {
				absolutePlaybackUrl = getIntent().getStringExtra("episode_video_url");
			} else if (getIntent() != null) {
				// Fallback: If no episode payload exists, process as standard movie layout via ID
				String incomingId = getIntent().getStringExtra("id");
				if (incomingId != null && !incomingId.trim().isEmpty()) {
					absolutePlaybackUrl = com.error404.reelix.StreamUrlHelper.generateMovieStreamUrl(incomingId);
				}
			}
			
			// Boot stream delivery layer if url resolution path checks out
			if (absolutePlaybackUrl != null && !absolutePlaybackUrl.trim().isEmpty()) {
				webview_player.loadUrl(absolutePlaybackUrl);
			} else {
				SketchwareUtil.showMessage(getApplicationContext(), "Invalid video stream reference configuration.");
			}
		}
		movieName_txt.setElevation((float)15);
		top_bar.setElevation((float)15);
		if (getIntent().hasExtra("name")) {
			movieName_txt.setText(getIntent().getStringExtra("name"));
		} else {
			movieName_txt.setText("Unknown");
		}
	}
	
	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if (webview_player != null) {
			webview_player.onPause();
			webview_player.pauseTimers(); // Freezes active JavaScript loops
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (webview_player != null) {
			webview_player.onResume();
			webview_player.resumeTimers(); // Restarts rendering tasks smoothly
		}
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