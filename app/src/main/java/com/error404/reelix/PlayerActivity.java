package com.error404.reelix;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.*;
import com.google.android.material.*;
import com.google.firebase.FirebaseApp;
import com.google.zxing.*;
import com.google.zxing.client.android.*;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.Player.EventListener;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import android.net.Uri;
import java.io.File;
import android.os.Build;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.google.android.exoplayer2.C;
import android.content.pm.ActivityInfo;
import com.google.gson.reflect.TypeToken;

public class PlayerActivity extends AppCompatActivity {
	
	public final int REQ_CD_FP = 101;
	
	private Timer _timer = new Timer();
	
	private boolean loading = false;
	private boolean lock = false;
	private boolean orien = false;
	private double playbackState = 0;
	private double position = 0;
	private SimpleExoPlayer player;
	private TimerTask check;
	private String cacheDataSourceFactory = "";
	private com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory cacheFactory;
	private String picked_subtitle_path = "";
	private String current_video_id = "";
	private int maxVolume;
	private int currentVolume;
	private float currentBrightness;
	
	private LinearLayout linear1;
	private PlayerView player_view;
	
	private TimerTask timer;
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private SharedPreferences resume_prefs;
	private AudioManager audioManager;
	private SharedPreferences history_pref;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.player);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		player_view = findViewById(R.id.player_view);
		fp.setType("*/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		resume_prefs = getSharedPreferences("resume_prefs", Activity.MODE_PRIVATE);
		history_pref = getSharedPreferences("history_pref", Activity.MODE_PRIVATE);
	}
	
	private void initializeLogic() {
		//Code block 1
		// Transparent status bar + cutout support
		getWindow().setStatusBarColor(Color.TRANSPARENT);
		getWindow().setFlags(
		WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS,
		WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
		);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
			getWindow().getAttributes().layoutInDisplayCutoutMode =
			WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
		}
		
		hideSystemUI();
		getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(
		visibility -> {
			if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
				hideSystemUI();
			}
		}
		);
		
		SystemClock.sleep(400);
		
		// Online playback setup
		String onlineLink = getIntent().getStringExtra("link");
		String onlineTitle = getIntent().getStringExtra("title");
		
		if (onlineLink != null && !onlineLink.isEmpty()) {
			loading = true;
			
			_setupCache();
			DefaultTrackSelector trackSelector = new DefaultTrackSelector();
			player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
			
			// Copy the raw streaming link directly to the clipboard for easy debugging
			/*((android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE))
	.setPrimaryClip(ClipData.newPlainText("clipboard", onlineLink));*/
			
			Uri uri = Uri.parse(onlineLink);
			
			// Since both servers return direct downloadable MP4 streams, we use ExtractorMediaSource instead of HlsMediaSource
			com.google.android.exoplayer2.upstream.DataSource.Factory dataSourceFactory = 
			new com.google.android.exoplayer2.upstream.DefaultDataSourceFactory(this, "Reelix-Player");
			
			MediaSource mp4MediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
			.createMediaSource(uri);
			
			_HLS();
			player_view.setPlayer(player);
			player_view.setKeepScreenOn(true);
			player.prepare(mp4MediaSource);
			_Events();
			player.setPlayWhenReady(true);
			
			TextView topTitle = player_view.findViewById(R.id.topTitle);
			if (topTitle != null) {
				topTitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ooo.ttf"), Typeface.BOLD);
				topTitle.setText(onlineTitle != null && !onlineTitle.isEmpty() ? onlineTitle : "");
			}
		} else {
			// Offline playback
			String videoPath = getIntent().getStringExtra("video_path");
			String videoTitle = getIntent().getStringExtra("video_title");
			
			if (videoPath == null || videoPath.isEmpty()) {
				SketchwareUtil.showMessage(getApplicationContext(), "Invalid or empty media playback path parameters received.");
				finish();
				return;
			}
			
			File videoFile = new File(videoPath);
			if (videoFile.exists()) {
				DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
				AdaptiveTrackSelection.Factory trackSelectionFactory =
				new AdaptiveTrackSelection.Factory(bandwidthMeter);
				DefaultTrackSelector trackSelector = new DefaultTrackSelector(trackSelectionFactory);
				DefaultLoadControl loadControl = new DefaultLoadControl();
				
				player = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);
				
				Uri localUri = Uri.fromFile(videoFile);
				DataSource.Factory dataSourceFactory = new FileDataSourceFactory();
				MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
				.createMediaSource(localUri);
				
				player_view.setPlayer(player);
				player_view.setKeepScreenOn(true);
				player.prepare(mediaSource);
				_HLS();
				_Events();
				player.setPlayWhenReady(true);
				
				TextView topTitle = player_view.findViewById(R.id.topTitle);
				if (topTitle != null) {
					topTitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ooo.ttf"), Typeface.BOLD);
					// Process the formatted Title String extra directly
					if (videoTitle != null && !videoTitle.trim().isEmpty() && !"null".equals(videoTitle)) {
						topTitle.setText(videoTitle);
					} else {
						String fallbackName = videoFile.getName();
						if (fallbackName.contains(".")) {
							fallbackName = fallbackName.substring(0, fallbackName.lastIndexOf("."));
						}
						topTitle.setText(fallbackName);
					}
				}
			} else {
				SketchwareUtil.showMessage(getApplicationContext(), "Offline media file could not be discovered on disk storage.");
				finish();
			}
		}
		
		
		//Code block 2
		// --- SYSTEM SERVICES INITIALIZATION ---
		final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		final int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		
		// --- SETUP HUD DISPLAY OVERLAYS (BRIGHTNESS & VOLUME) ---
		final FrameLayout rootDecor = (FrameLayout) getWindow().getDecorView().findViewById(android.R.id.content);
		
		// Helper function to build a uniform, slim HUD capsule matching your design guidelines
		class HudViewBuilder {
			LinearLayout mainCapsule;
			ImageView icon;
			ProgressBar progress;
			
			HudViewBuilder(Context ctx) {
				mainCapsule = new LinearLayout(ctx);
				mainCapsule.setOrientation(LinearLayout.HORIZONTAL);
				mainCapsule.setGravity(Gravity.CENTER_VERTICAL);
				mainCapsule.setPadding(30, 15, 35, 15);
				mainCapsule.setVisibility(View.GONE);
				
				GradientDrawable bg = new GradientDrawable();
				bg.setColor(Color.parseColor("#CC000000")); // Solid semi-transparent dark background
				bg.setCornerRadius(30f);
				mainCapsule.setBackground(bg);
				
				icon = new ImageView(ctx);
				icon.setPadding(0, 0, 15, 0);
				int iconDim = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, ctx.getResources().getDisplayMetrics());
				icon.setLayoutParams(new LinearLayout.LayoutParams(iconDim, iconDim));
				
				progress = new ProgressBar(ctx, null, android.R.attr.progressBarStyleHorizontal);
				LinearLayout.LayoutParams pbParams = new LinearLayout.LayoutParams(
				(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, ctx.getResources().getDisplayMetrics()),
				(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, ctx.getResources().getDisplayMetrics())
				);
				progress.setLayoutParams(pbParams);
				progress.setMax(100);
				
				// Customize internal progress track colors: Unfilled background layer
				progress.getProgressDrawable().setColorFilter(Color.parseColor("#45424E"), PorterDuff.Mode.SRC_IN);
				
				mainCapsule.addView(icon);
				mainCapsule.addView(progress);
			}
		}
		
		final HudViewBuilder volumeHud = new HudViewBuilder(this);
		final HudViewBuilder brightnessHud = new HudViewBuilder(this);
		
		
		//Code block3
		// --- SLIM 2X SPEED INDICATOR OVERLAY ---
		final TextView speedIndicator = new TextView(this);
		speedIndicator.setText("2X");
		speedIndicator.setTextColor(Color.WHITE);
		speedIndicator.setTextSize(14f);
		speedIndicator.setGravity(Gravity.CENTER);
		speedIndicator.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ooo.ttf"), Typeface.BOLD);
		speedIndicator.setPadding(35, 12, 35, 12);
		speedIndicator.setVisibility(View.GONE);
		
		GradientDrawable speedBg = new GradientDrawable();
		speedBg.setColor(Color.parseColor("#CC000000")); // Match your custom dark seek theme
		speedBg.setCornerRadius(25f);
		speedIndicator.setBackground(speedBg);
		
		// Position layouts perfectly at the top-center quadrant of the player window
		FrameLayout.LayoutParams hudLayoutParams = new FrameLayout.LayoutParams(
		FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.TOP | Gravity.CENTER_HORIZONTAL
		);
		hudLayoutParams.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics());
		
		rootDecor.addView(volumeHud.mainCapsule, hudLayoutParams);
		rootDecor.addView(brightnessHud.mainCapsule, hudLayoutParams);
		rootDecor.addView(speedIndicator, hudLayoutParams); // Inject the speed capsule safely into hierarchy
		
		// --- TRACKING STATE VARIABLES ---
		final long[] seekStartPosition = {0};
		final boolean[] isSeeking = {false};
		final String[] activeGestureType = {"NONE"}; // "BRIGHTNESS", "VOLUME", "SEEK", or "SPEED_2X"
		final float[] baseGestureVal = {0f};
		final boolean[] isSpeedBoosted = {false}; // Safety latch for speed reset
		
		// Base seek indicator text element setup
		final TextView seekIndicator = new TextView(this);
		seekIndicator.setTextColor(Color.WHITE);
		seekIndicator.setTextSize(22f);
		seekIndicator.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ooo.ttf"), Typeface.BOLD);
		seekIndicator.setPadding(40, 20, 40, 20);
		seekIndicator.setVisibility(View.GONE);
		GradientDrawable seekBg = new GradientDrawable();
		seekBg.setColor(Color.parseColor("#CC000000"));
		seekBg.setCornerRadius(40f);
		seekIndicator.setBackground(seekBg);
		rootDecor.addView(seekIndicator, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
		
		// --- ADVANCED MULTI-AXIS GESTURE INTERCEPTOR ---
		GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
			
			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
				// Prevent scroll interference if user is currently utilizing the 2X speed boost feature
				if (activeGestureType[0].equals("SPEED_2X") || player == null || e1 == null || e2 == null) return false;
				
				float deltaX = e2.getX() - e1.getX();
				float deltaY = e1.getY() - e2.getY(); // Invert Y axis directionality (upwards = positive)
				float screenWidth = getResources().getDisplayMetrics().widthPixels;
				float screenHeight = getResources().getDisplayMetrics().heightPixels;
				
				// Establish operational locked axis state upon processing initial move threshold
				if (activeGestureType[0].equals("NONE")) {
					if (Math.abs(distanceX) > Math.abs(distanceY)) {
						activeGestureType[0] = "SEEK";
						seekStartPosition[0] = player.getCurrentPosition();
					} else {
						// Split interface: Left half controls Brightness, Right half controls System Media Stream Volume
						if (e1.getX() < (screenWidth / 2f)) {
							activeGestureType[0] = "BRIGHTNESS";
							float curBright = getWindow().getAttributes().screenBrightness;
							if (curBright < 0) curBright = 0.5f; // Fallback to safe system default medium
							baseGestureVal[0] = curBright;
						} else {
							activeGestureType[0] = "VOLUME";
							baseGestureVal[0] = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
						}
					}
				}
				
				// --- PIPELINE 1: TIME HORIZONTAL TIMELINE SEEK ---
				if (activeGestureType[0].equals("SEEK")) {
					long durationMs = player.getDuration();
					if (durationMs <= 0 || durationMs == com.google.android.exoplayer2.C.TIME_UNSET) return false;
					
					long seekDelta = (long) (deltaX * durationMs / screenWidth);
					long targetPosition = Math.max(0, Math.min(durationMs, seekStartPosition[0] + seekDelta));
					
					long minutes = (targetPosition / 1000) / 60;
					long seconds = (targetPosition / 1000) % 60;
					String timeStr = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
					
					seekIndicator.setText((deltaX > 0 ? ">> " : "<< ") + timeStr);
					seekIndicator.setVisibility(View.VISIBLE);
					player.seekTo(targetPosition);
					return true;
				}
				
				// --- PIPELINE 2: BRIGHTNESS ADJUSTMENT (LEFT 50%) ---
				if (activeGestureType[0].equals("BRIGHTNESS")) {
					float brightnessDelta = (deltaY / screenHeight); 
					float targetBrightness = Math.max(0.01f, Math.min(1.0f, baseGestureVal[0] + brightnessDelta));
					
					WindowManager.LayoutParams lp = getWindow().getAttributes();
					lp.screenBrightness = targetBrightness;
					getWindow().setAttributes(lp);
					
					int pct = (int) (targetBrightness * 100);
					brightnessHud.icon.setImageResource(R.drawable.brightness); 
					
					// Dynamic accent coloring logic (Red accent on adjustment, otherwise pristine white)
					brightnessHud.progress.getProgressDrawable().setColorFilter(Color.parseColor(brightnessDelta >= 0 ? "#E53935" : "#FFFFFF"), PorterDuff.Mode.SRC_IN);
					brightnessHud.progress.setProgress(pct);
					brightnessHud.mainCapsule.setVisibility(View.VISIBLE);
					return true;
				}
				
				// --- PIPELINE 3: VOLUME ADJUSTMENT (RIGHT 50%) ---
				if (activeGestureType[0].equals("VOLUME")) {
					float volumeDeltaPct = (deltaY / screenHeight);
					int totalStepsToChange = (int) (volumeDeltaPct * maxVolume * 1.5f); // 1.5x scaling parameter for precise swipe physics
					int targetVolume = Math.max(0, Math.min(maxVolume, (int) baseGestureVal[0] + totalStepsToChange));
					
					audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, targetVolume, 0);
					
					int currentPct = (int) (((float) targetVolume / maxVolume) * 100);
					
					// Contextual volume icon management
					if (targetVolume == 0) {
						volumeHud.icon.setImageResource(R.drawable.volume_off); // Mute fallback graphic
					} else {
						volumeHud.icon.setImageResource(R.drawable.volume); 
					}
					
					// Apply specific progress accents: Active increases tint to Red (#E53935), drops tint down to standard white
					volumeHud.progress.getProgressDrawable().setColorFilter(Color.parseColor(totalStepsToChange >= 0 ? "#E53935" : "#FFFFFF"), PorterDuff.Mode.SRC_IN);
					volumeHud.progress.setProgress(currentPct);
					volumeHud.mainCapsule.setVisibility(View.VISIBLE);
					return true;
				}
				
				return false;
			}
			
			// --- PIPELINE 4: LONG PRESS SPEED BOOST CONFIGURATION ---
			@Override
			public void onLongPress(MotionEvent e) {
				if (player == null || !activeGestureType[0].equals("NONE")) return;
				
				float screenWidth = getResources().getDisplayMetrics().widthPixels;
				
				// Execute speed modification exclusively if triggered on the right 50% section of the surface viewport
				if (e.getX() >= (screenWidth / 2f)) {
					activeGestureType[0] = "SPEED_2X";
					isSpeedBoosted[0] = true;
					
					// FIXED: Provided both speed (2.0f) and pitch (1.0f) constructor parameters 
					com.google.android.exoplayer2.PlaybackParameters param = new com.google.android.exoplayer2.PlaybackParameters(2.0f, 1.0f);
					player.setPlaybackParameters(param);
					
					// Render the small 2X speed pill overlay layout on-screen
					speedIndicator.setAlpha(0f);
					speedIndicator.setVisibility(View.VISIBLE);
					speedIndicator.animate().alpha(1f).setDuration(200).start();
				}
			}
			
			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				player_view.performClick();
				return true;
			}
			
			@Override
			public boolean onDoubleTap(MotionEvent e) {
				float x = e.getX();
				float screenWidth = player_view.getWidth();
				float middleStart = screenWidth * 0.33f;
				float middleEnd = screenWidth * 0.66f;
				
				if (x >= middleStart && x <= middleEnd) {
					if (player != null) {
						if (player.getPlayWhenReady()) {
							player.setPlayWhenReady(false);
							View pauseView = player_view.findViewById(R.id.exo_pause);
							if (pauseView != null) {
								pauseView.animate().cancel();
								pauseView.setAlpha(1f);
								pauseView.setScaleX(1.3f);
								pauseView.setScaleY(1.3f);
								pauseView.animate().scaleX(1f).scaleY(1f).alpha(0.5f).setDuration(400)
								.withEndAction(new Runnable() {
									@Override
									public void run() {
										pauseView.setAlpha(1f);
									}
								}).start();
							}
						} else {
							player.setPlayWhenReady(true);
							View playView = player_view.findViewById(R.id.exo_play);
							if (playView != null) {
								playView.animate().cancel();
								playView.setAlpha(1f);
								playView.setScaleX(1.3f);
								playView.setScaleY(1.3f);
								playView.animate().scaleX(1f).scaleY(1f).alpha(0.5f).setDuration(400)
								.withEndAction(new Runnable() {
									@Override
									public void run() {
										playView.setAlpha(1f);
									}
								}).start();
							}
						}
					}
					return true;
				} else if (x < middleStart) {
					long newPos = Math.max(0, player.getCurrentPosition() - 10000);
					player.seekTo(newPos);
					View rewView = player_view.findViewById(R.id.exo_rew);
					if (rewView != null) {
						rewView.animate().cancel();
						rewView.setAlpha(1f);
						rewView.setScaleX(1.3f);
						rewView.setScaleY(1.3f);
						rewView.animate().scaleX(1f).scaleY(1f).alpha(0.5f).setDuration(400)
						.withEndAction(new Runnable() {
							@Override
							public void run() {
								rewView.setAlpha(1f);
							}
						}).start();
					}
					return true;
				} else {
					long newPos = Math.min(player.getDuration(), player.getCurrentPosition() + 10000);
					player.seekTo(newPos);
					View ffwdView = player_view.findViewById(R.id.exo_ffwd);
					if (ffwdView != null) {
						ffwdView.animate().cancel();
						ffwdView.setAlpha(1f);
						ffwdView.setScaleX(1.3f);
						ffwdView.setScaleY(1.3f);
						ffwdView.animate().scaleX(1f).scaleY(1f).alpha(0.5f).setDuration(400)
						.withEndAction(new Runnable() {
							@Override
							public void run() {
								ffwdView.setAlpha(1f);
							}
						}).start();
					}
					return true;
				}
			}
		});
		
		
		//Code block 4
		// --- ATTACH DIRECTLY TO PLAYERVIEW TOUCH INTERFACE INTERCEPT ---
		player_view.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				gestureDetector.onTouchEvent(event);
				
				// Handle touch releases smoothly
				if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
					
					// RESET CONDITION: If the user was using the 2x speed boost feature, reset it to normal instantly on release
					if (isSpeedBoosted[0] && player != null) {
						isSpeedBoosted[0] = false;
						// FIXED: Provided both speed (1.0f) and pitch (1.0f) constructor parameters
						com.google.android.exoplayer2.PlaybackParameters normalParam = new com.google.android.exoplayer2.PlaybackParameters(1.0f, 1.0f);
						player.setPlaybackParameters(normalParam);
						
						// Animate out the speed capsule cleanly
						speedIndicator.animate().alpha(0f).setDuration(250)
						.withEndAction(new Runnable() {
							@Override
							public void run() {
								speedIndicator.setVisibility(View.GONE);
							}
						}).start();
					}
					
					activeGestureType[0] = "NONE";
					
					// Cleanly slide down or fade out indicators across 450ms
					if (seekIndicator.getVisibility() == View.VISIBLE) {
						seekIndicator.postDelayed(() -> seekIndicator.setVisibility(View.GONE), 400);
					}
					
					if (volumeHud.mainCapsule.getVisibility() == View.VISIBLE) {
						volumeHud.mainCapsule.animate().alpha(0f).setDuration(450)
						.withEndAction(() -> {
							volumeHud.mainCapsule.setVisibility(View.GONE);
							volumeHud.mainCapsule.setAlpha(1f);
						}).start();
					}
					
					if (brightnessHud.mainCapsule.getVisibility() == View.VISIBLE) {
						brightnessHud.mainCapsule.animate().alpha(0f).setDuration(450)
						.withEndAction(() -> {
							brightnessHud.mainCapsule.setVisibility(View.GONE);
							brightnessHud.mainCapsule.setAlpha(1f);
						}).start();
					}
				}
				return false;
			}
		});
		
		// --- SUBTITLE & NEXT EPISODE CONTROL INITIALIZATION ---
		View subtitleIcon = player_view.findViewById(R.id.subtitle);
		View nextEpisodeBtn = player_view.findViewById(R.id.next_img);
		
		String currentVideoPath = getIntent().getStringExtra("video_path");
		String onlineLinkCheck = getIntent().getStringExtra("link");
		
		if (nextEpisodeBtn != null) {
			boolean isTvShow = false;
			
			if (currentVideoPath != null && (currentVideoPath.contains("/Season ") || currentVideoPath.contains("/ep"))) {
				isTvShow = true;
			}
			
			if (isTvShow && (onlineLinkCheck == null || onlineLinkCheck.isEmpty())) {
				nextEpisodeBtn.setVisibility(View.VISIBLE);
				nextEpisodeBtn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						try {
							File currentFile = new File(currentVideoPath);
							File currentEpFolder = currentFile.getParentFile();       
							File currentSeasonFolder = currentEpFolder.getParentFile(); 
							File showRootFolder = currentSeasonFolder.getParentFile();  
							
							String epFolderName = currentEpFolder.getName(); 
							String seasonFolderName = currentSeasonFolder.getName(); 
							
							int currentEpNum = Integer.parseInt(epFolderName.replaceAll("[^0-9]", ""));
							int currentSeasonNum = Integer.parseInt(seasonFolderName.replaceAll("[^0-9]", ""));
							
							String targetNextVideoPath = "";
							
							// Blueprint 1: Check ep + 1 folder path targets
							int nextEpNum = currentEpNum + 1;
							File nextEpFolder = new File(currentSeasonFolder, "ep" + nextEpNum);
							
							if (nextEpFolder.exists() && nextEpFolder.isDirectory()) {
								_findMp4InFolder(nextEpFolder.getAbsolutePath());
								targetNextVideoPath = picked_subtitle_path; 
							}
							
							// Blueprint 2: Fall back to Next Season, Episode 1 if needed
							if (targetNextVideoPath.isEmpty()) {
								int nextSeasonNum = currentSeasonNum + 1;
								File nextSeasonFolder = new File(showRootFolder, "Season " + nextSeasonNum);
								if (nextSeasonFolder.exists() && nextSeasonFolder.isDirectory()) {
									File firstEpOfNextSeason = new File(nextSeasonFolder, "ep1");
									if (firstEpOfNextSeason.exists() && firstEpOfNextSeason.isDirectory()) {
										_findMp4InFolder(firstEpOfNextSeason.getAbsolutePath());
										targetNextVideoPath = picked_subtitle_path;
									}
								}
							}
							
							// Execute play sequence if matching file paths exist
							if (!targetNextVideoPath.isEmpty()) {
								
								// =========================================================
								// CRITICAL: STEP A - SAVE ONGOING EPISODE PROGRESS BEFORE SWITCHING
								// =========================================================
								String currentVideoTitle = getIntent().getStringExtra("video_title");
								if (player != null && currentVideoPath != null) {
									long currentPos = player.getCurrentPosition();
									long totalDuration = player.getDuration();
									
									// Save precise position to resume_prefs
									if (totalDuration > 0 && (totalDuration - currentPos) < 15000) {
										// If near the end (15 seconds left), consider it finished and remove progress
										resume_prefs.edit().remove(currentVideoPath).apply();
									} else if (currentPos > 5000) {
										// Save if played for more than 5 seconds
										resume_prefs.edit().putLong(currentVideoPath, currentPos).apply();
									}
									
									// Sync current position back to watch_history list as well
									try {
										android.content.SharedPreferences history_pref = getSharedPreferences("history_pref", android.content.Context.MODE_PRIVATE);
										String historyJson = history_pref.getString("watch_history", "");
										if (!historyJson.isEmpty()) {
											java.util.ArrayList<java.util.HashMap<String, Object>> currentHistoryList = 
											new com.google.gson.Gson().fromJson(historyJson, new com.google.gson.reflect.TypeToken<java.util.ArrayList<java.util.HashMap<String, Object>>>(){}.getType());
											
											for (java.util.HashMap<String, Object> item : currentHistoryList) {
												if (String.valueOf(item.get("video_path")).equals(currentVideoPath)) {
													item.put("progress", String.valueOf(currentPos));
													item.put("duration", String.valueOf(totalDuration));
													item.put("timestamp", String.valueOf(System.currentTimeMillis()));
													break;
												}
											}
											history_pref.edit().putString("watch_history", new com.google.gson.Gson().toJson(currentHistoryList)).apply();
										}
									} catch (Exception historySaveEx) {
										historySaveEx.printStackTrace();
									}
								}
								
								// =========================================================
								// STEP B - SET UP THE NEXT EPISODE DATA structures & RESORT
								// =========================================================
								File nextVideoFile = new File(targetNextVideoPath);
								
								// Default Fallback Title Layout Construction
								String targetSeasonString = nextVideoFile.getParentFile().getParentFile().getName().replaceAll("[^0-9]", "");
								String targetEpisodeString = nextVideoFile.getParentFile().getName().replaceAll("[^0-9]", "");
								String nextVideoTitle = showRootFolder.getName() + " S" + targetSeasonString + " E" + targetEpisodeString;
								
								String recoveredCoverUrl = "";
								
								File metaFile = new File(nextVideoFile.getParentFile(), "metadata.json");
								if (metaFile.exists()) {
									try {
										FileInputStream fis = new FileInputStream(metaFile);
										byte[] data = new byte[(int) metaFile.length()];
										fis.read(data);
										fis.close();
										JSONObject metaJson = new JSONObject(new String(data, "UTF-8"));
										
										String showName = metaJson.optString("title", showRootFolder.getName());
										String epTitle = metaJson.optString("episode_title", "");
										String sNum = metaJson.optString("season_number", targetSeasonString);
										String eNum = metaJson.optString("episode_number", targetEpisodeString);
										recoveredCoverUrl = metaJson.optString("cover_url", "");
										
										nextVideoTitle = showName + " S" + sNum + " E" + eNum;
										if (!epTitle.trim().isEmpty()) {
											nextVideoTitle += ": " + epTitle;
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
								
								// Inherit cover image from history records if metadata lacks it
								try {
									android.content.SharedPreferences history_pref = getSharedPreferences("history_pref", android.content.Context.MODE_PRIVATE);
									java.util.ArrayList<java.util.HashMap<String, Object>> historyList = new java.util.ArrayList<>();
									String historyJson = history_pref.getString("watch_history", "");
									
									if (!historyJson.isEmpty()) {
										historyList = new com.google.gson.Gson().fromJson(historyJson, new com.google.gson.reflect.TypeToken<java.util.ArrayList<java.util.HashMap<String, Object>>>(){}.getType());
									}
									
									if (recoveredCoverUrl.isEmpty() && !historyList.isEmpty()) {
										for (java.util.HashMap<String, Object> oldItem : historyList) {
											if (String.valueOf(oldItem.get("title")).startsWith(showRootFolder.getName())) {
												recoveredCoverUrl = oldItem.containsKey("cover_url") ? String.valueOf(oldItem.get("cover_url")) : "";
												break;
											}
										}
									}
									
									// Create the entry for the new upcoming episode
									java.util.HashMap<String, Object> historyItem = new java.util.HashMap<>();
									historyItem.put("title", nextVideoTitle);
									historyItem.put("cover_url", recoveredCoverUrl);
									historyItem.put("type", "tv");
									historyItem.put("video_path", targetNextVideoPath);
									historyItem.put("timestamp", String.valueOf(System.currentTimeMillis())); // Pulls it to the top chronologically
									historyItem.put("progress", "0");
									
									// CRITICAL FIX: Loop backwards to safely delete any old instances of this specific 
									// episode by checking both its title matching layout and local file path.
									for (int i = historyList.size() - 1; i >= 0; i--) {
										java.util.HashMap<String, Object> checkItem = historyList.get(i);
										String savedPath = checkItem.containsKey("video_path") ? String.valueOf(checkItem.get("video_path")) : "";
										String savedTitle = checkItem.containsKey("title") ? String.valueOf(checkItem.get("title")) : "";
										
										if (savedPath.equals(targetNextVideoPath) || savedTitle.equals(nextVideoTitle)) {
											historyList.remove(i);
										}
									}
									
									// Prepend new history record directly to index 0 (top-most element)
									historyList.add(0, historyItem);
									history_pref.edit().putString("watch_history", new com.google.gson.Gson().toJson(historyList)).apply();
									
								} catch (Exception historyEx) {
									historyEx.printStackTrace();
								}
								
								// =========================================================
								// STEP C - RELEASE PLAYER AND COMPONENT REBOOT
								// =========================================================
								if (player != null) {
									player.stop();
									player.release();
									player = null;
								}
								
								// Overwrite intent arguments for hot reload execution
								getIntent().putExtra("video_path", targetNextVideoPath);
								getIntent().putExtra("video_title", nextVideoTitle);
								getIntent().putExtra("title", nextVideoTitle);
								
								initializeLogic(); 
								
							} else {
								Toast.makeText(getApplicationContext(), "No next episode discovered locally.", Toast.LENGTH_SHORT).show();
							}
							
						} catch (Exception e) {
							Toast.makeText(getApplicationContext(), "Error looking up next resource path setup.", Toast.LENGTH_SHORT).show();
							e.printStackTrace();
						}
					}
				});
			} else {
				nextEpisodeBtn.setVisibility(View.GONE);
			}
		}
		
		
		
		//Code block 5
		// 2. Subtitle Side Panel Trigger Layer (Unchanged)
		if (subtitleIcon != null) {
			subtitleIcon.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					View overlayView = getLayoutInflater().inflate(R.layout.player_side_overlay, null);
					int subWidth = (int) (getResources().getDisplayMetrics().widthPixels * 0.45);
					int subHeight = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
					
					final android.widget.PopupWindow popupWindow = new android.widget.PopupWindow(overlayView, subWidth, subHeight, true);
					popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
					popupWindow.setOutsideTouchable(true);
					popupWindow.setFocusable(true);
					popupWindow.setClippingEnabled(false);
					
					LinearLayout selectSub = overlayView.findViewById(R.id.select_sub_linear);
					LinearLayout downloadSub = overlayView.findViewById(R.id.download_sub_linear);
					
					selectSub.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							popupWindow.dismiss();
							Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
							intent.setType("*/*");
							intent.addCategory(Intent.CATEGORY_OPENABLE);
							startActivityForResult(Intent.createChooser(intent, "Select Subtitle File"), REQ_CD_FP);
						}
					});
					
					downloadSub.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							SketchwareUtil.showMessage(getApplicationContext(), "Download Subtitle Context Layer");
							popupWindow.dismiss();
						}
					});
					
					View rootDecor = getWindow().getDecorView().findViewById(android.R.id.content);
					popupWindow.showAtLocation(rootDecor, android.view.Gravity.RIGHT, 0, 0);
					
					View container = (View) overlayView.getParent();
					if (container != null) {
						android.view.WindowManager wm = (android.view.WindowManager) getSystemService(WINDOW_SERVICE);
						android.view.WindowManager.LayoutParams lp = (android.view.WindowManager.LayoutParams) container.getLayoutParams();
						lp.flags |= android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
						wm.updateViewLayout(container, lp);
					}
				}
			});
		}
		
		// --- SCREEN ROTATION CONTROL INITIALIZATION ---
		final View rotateIcon = player_view.findViewById(R.id.rotate_img);
		
		if (rotateIcon != null) {
			// Check current orientation: 1 is PORTRAIT, 2 is LANDSCAPE
			int currentOrientation = getResources().getConfiguration().orientation;
			
			if (currentOrientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) {
				// Show the rotate button if we are starting in portrait mode
				rotateIcon.setVisibility(View.VISIBLE);
				
				rotateIcon.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// Force rotation to landscape layout
						setRequestedOrientation(android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
						
						// Immediately hide the button now that we are in landscape
						rotateIcon.setVisibility(View.GONE);
					}
				});
			} else {
				// Hide it immediately if the player is already launching into landscape mode
				rotateIcon.setVisibility(View.GONE);
			}
		}
		
		// --- AUTOMATIC DYNAMIC INTENT SUBTITLE LOADER ---
		String intentSubtitlePath = getIntent().getStringExtra("subtitle_data");
		if (intentSubtitlePath != null && !intentSubtitlePath.isEmpty() && player != null) {
			java.io.File subFile = new java.io.File(intentSubtitlePath);
			if (subFile.exists()) {
				try {
					long currentPlaybackPosition = player.getCurrentPosition();
					boolean playWhenReadyState = player.getPlayWhenReady();
					
					// 1. Rebuild base MediaSource track configurations matching your stream types securely
					MediaSource baseVideoSource;
					String onlineLinkForSubs = getIntent().getStringExtra("link");
					
					if (onlineLinkForSubs != null && !onlineLinkForSubs.isEmpty()) {
						String dynamicProxiedUrl;
						try {
							dynamicProxiedUrl = "https://movie-scraper-pied.vercel.app/api?url=" + java.net.URLEncoder.encode(onlineLinkForSubs, "UTF-8");
						} catch (Exception e) {
							dynamicProxiedUrl = "https://movie-scraper-pied.vercel.app/api?url=" + onlineLinkForSubs;
						}
						baseVideoSource = new com.google.android.exoplayer2.source.hls.HlsMediaSource.Factory(cacheFactory)
						.createMediaSource(Uri.parse(dynamicProxiedUrl));
					} else {
						String videoPath = getIntent().getStringExtra("video_path");
						baseVideoSource = new com.google.android.exoplayer2.source.ExtractorMediaSource.Factory(new com.google.android.exoplayer2.upstream.FileDataSourceFactory())
						.createMediaSource(Uri.fromFile(new java.io.File(videoPath)));
					}
					
					// 2. RENAME INTERCEPTOR: Automatically convert fallback .vtt formats directly to .srt
					if (intentSubtitlePath.toLowerCase().endsWith(".vtt")) {
						String srtPath = intentSubtitlePath.substring(0, intentSubtitlePath.length() - 4) + ".srt";
						java.io.File newSrtFile = new java.io.File(srtPath);
						if (newSrtFile.exists()) {
							newSrtFile.delete();
						}
						if (subFile.renameTo(newSrtFile)) {
							intentSubtitlePath = srtPath;
						}
					}
					
					// 3. Build text sample track descriptor constraints for SubRip format
					com.google.android.exoplayer2.Format subtitleFormat = com.google.android.exoplayer2.Format.createTextSampleFormat(
					null, 
					com.google.android.exoplayer2.util.MimeTypes.APPLICATION_SUBRIP, 
					com.google.android.exoplayer2.C.SELECTION_FLAG_DEFAULT, 
					"en"
					);
					
					// 4. Inject physical data file stream parameters
					Uri subtitleUri = Uri.fromFile(new java.io.File(intentSubtitlePath));
					com.google.android.exoplayer2.upstream.DataSource.Factory subtitleDataSourceFactory = new com.google.android.exoplayer2.upstream.DefaultDataSourceFactory(this, "Reelix-Subtitles");
					
					MediaSource subtitleMediaSource = new com.google.android.exoplayer2.source.SingleSampleMediaSource.Factory(subtitleDataSourceFactory)
					.createMediaSource(subtitleUri, subtitleFormat, com.google.android.exoplayer2.C.TIME_UNSET);
					
					// 5. Merge paths together securely into an active track array group
					com.google.android.exoplayer2.source.MergingMediaSource mergedMediaSource = 
					new com.google.android.exoplayer2.source.MergingMediaSource(baseVideoSource, subtitleMediaSource);
					
					// 6. Apply clean white-text, outline, boxless custom UI themes matching manual picker rules
					if (player_view != null) {
						com.google.android.exoplayer2.ui.SubtitleView internalSubView = 
						player_view.findViewById(com.google.android.exoplayer2.ui.R.id.exo_subtitles);
						
						if (internalSubView != null) {
							com.google.android.exoplayer2.text.CaptionStyleCompat boxlessStyle = 
							new com.google.android.exoplayer2.text.CaptionStyleCompat(
							Color.parseColor("#FFFFFF"), 
							Color.TRANSPARENT, 
							Color.TRANSPARENT, 
							com.google.android.exoplayer2.text.CaptionStyleCompat.EDGE_TYPE_OUTLINE, 
							Color.parseColor("#000000"), 
							Typeface.createFromAsset(getAssets(), "fonts/ooo.ttf")
							);
							
							internalSubView.setStyle(boxlessStyle);
							internalSubView.setFractionalTextSize(0.053f); 
						}
					}
					
					// 7. Push modifications directly to active player engine instance
					player.prepare(mergedMediaSource, false, false);
					player.seekTo(currentPlaybackPosition);
					player.setPlayWhenReady(playWhenReadyState);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		// Make sure your check banner runs LAST so it handles the resume position over the merged streams properly!
		_checkAndShowResumeBanner();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FP:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				if (FileUtil.isFile(_filePath.get((int)(0))) && Uri.parse(_filePath.get((int)(0))).getLastPathSegment().endsWith(".srt")) {
					if (_filePath != null && !_filePath.isEmpty()) {
						// 1. Extract the absolute storage file path from the file picker event
						String picked_subtitle_path = _filePath.get((int)(0));
						
						if (player != null && picked_subtitle_path != null && !picked_subtitle_path.isEmpty()) {
							try {
								// 2. Save the user's current watching timestamp location
								long currentPlaybackPosition = player.getCurrentPosition();
								boolean playWhenReadyState = player.getPlayWhenReady();
								
								// 3. Rebuild the video source based on your existing setup logic
								MediaSource baseVideoSource;
								String onlineLink = getIntent().getStringExtra("link");
								
								if (onlineLink != null && !onlineLink.isEmpty()) {
									// Online HLS stream restoration
									String proxiedUrl;
									try {
										proxiedUrl = "https://movie-scraper-pied.vercel.app/api?url=" + java.net.URLEncoder.encode(onlineLink, "UTF-8");
									} catch (Exception e) {
										proxiedUrl = "https://movie-scraper-pied.vercel.app/api?url=" + onlineLink;
									}
									baseVideoSource = new com.google.android.exoplayer2.source.hls.HlsMediaSource.Factory(cacheFactory).createMediaSource(Uri.parse(proxiedUrl));
								} else {
									// Offline Local MP4/MKV stream restoration
									String videoPath = getIntent().getStringExtra("video_path");
									baseVideoSource = new com.google.android.exoplayer2.source.ExtractorMediaSource.Factory(new com.google.android.exoplayer2.upstream.FileDataSourceFactory())
									.createMediaSource(Uri.fromFile(new java.io.File(videoPath)));
								}
								
								// RENAME INTERCEPTOR: Automatically renames .vtt files to .srt directly on disk
								if (picked_subtitle_path.toLowerCase().endsWith(".vtt")) {
									java.io.File oldVttFile = new java.io.File(picked_subtitle_path);
									if (oldVttFile.exists()) {
										// Create the companion .srt file path in the same directory
										String srtPath = picked_subtitle_path.substring(0, picked_subtitle_path.length() - 4) + ".srt";
										java.io.File newSrtFile = new java.io.File(srtPath);
										
										// If an old version exists from a prior selection, delete it to avoid collision
										if (newSrtFile.exists()) {
											newSrtFile.delete();
										}
										
										// Rename the file on storage
										if (oldVttFile.renameTo(newSrtFile)) {
											picked_subtitle_path = srtPath; // Update reference string to the new .srt file path
										}
									}
								}
								
								// 4. Safely enforce SubRip rules exclusively as the input is now verified SRT format
								com.google.android.exoplayer2.Format subtitleFormat = com.google.android.exoplayer2.Format.createTextSampleFormat(
								null, 
								com.google.android.exoplayer2.util.MimeTypes.APPLICATION_SUBRIP, 
								com.google.android.exoplayer2.C.SELECTION_FLAG_DEFAULT, 
								"en"
								);
								
								// 5. Build the text data stream source pointing directly to your converted cache path
								Uri subtitleUri = Uri.fromFile(new java.io.File(picked_subtitle_path));
								com.google.android.exoplayer2.upstream.DataSource.Factory subtitleDataSourceFactory = new com.google.android.exoplayer2.upstream.DefaultDataSourceFactory(this, "Reelix-Subtitles");
								
								MediaSource subtitleMediaSource = new com.google.android.exoplayer2.source.SingleSampleMediaSource.Factory(subtitleDataSourceFactory)
								.createMediaSource(subtitleUri, subtitleFormat, com.google.android.exoplayer2.C.TIME_UNSET);
								
								// 6. Merge the original video stream track with your converted layout stream track
								com.google.android.exoplayer2.source.MergingMediaSource mergedMediaSource = 
								new com.google.android.exoplayer2.source.MergingMediaSource(baseVideoSource, subtitleMediaSource);
								
								// Target the inner SubtitleView layout component inside PlayerView 
								if (player_view != null) {
									com.google.android.exoplayer2.ui.SubtitleView internalSubView = 
									player_view.findViewById(com.google.android.exoplayer2.ui.R.id.exo_subtitles);
									
									if (internalSubView != null) {
										int textColor = Color.parseColor("#FFFFFF");        // White text
										int backgroundColor = Color.TRANSPARENT;            // REMOVED BACKGROUND BOX
										int windowColor = Color.TRANSPARENT;                // Fully transparent window overlay frame
										int edgeColor = Color.parseColor("#000000");        // Sharp solid black text border outline
										
										int edgeType = com.google.android.exoplayer2.text.CaptionStyleCompat.EDGE_TYPE_OUTLINE;
										
										// Keep app font consistency
										Typeface subtitleFont = Typeface.createFromAsset(getAssets(), "fonts/ooo.ttf");
										
										com.google.android.exoplayer2.text.CaptionStyleCompat boxlessStyle = 
										new com.google.android.exoplayer2.text.CaptionStyleCompat(
										textColor, 
										backgroundColor, 
										windowColor, 
										edgeType, 
										edgeColor, 
										subtitleFont
										);
										
										// Apply directly to the extracted SubtitleView
										internalSubView.setStyle(boxlessStyle);
										internalSubView.setFractionalTextSize(0.053f); 
									}
								}
								
								// 7. Re-prepare ExoPlayer with the merged source, seek to saved spot, and keep playing
								player.prepare(mergedMediaSource, false, false);
								player.seekTo(currentPlaybackPosition);
								player.setPlayWhenReady(playWhenReadyState);
								
								SketchwareUtil.showMessage(getApplicationContext(), "Subtitle track processed & attached!");
								
							} catch (Exception e) {
								SketchwareUtil.showMessage(getApplicationContext(), "Failed to parse/inject subtitle: " + e.getMessage());
							}
						}
					}
					
				} else {
					com.google.android.material.snackbar.Snackbar.make(linear1, "Subtitle type not Surported", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
						@Override
						public void onClick(View _view) {
							
						}
					}).show();
				}
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (player != null) {
			long currentPos = player.getCurrentPosition();
			long totalDuration = player.getDuration();
			
			String onlineLink = getIntent().getStringExtra("link");
			String videoPath = getIntent().getStringExtra("video_path");
			current_video_id = (onlineLink != null && !onlineLink.isEmpty()) ? onlineLink : videoPath;
			
			if (current_video_id != null && !current_video_id.isEmpty()) {
				if (totalDuration > 0 && (totalDuration - currentPos) < 15000) {
					resume_prefs.edit().remove(current_video_id).apply();
				} else if (currentPos > 5000) {
					resume_prefs.edit().putLong(current_video_id, currentPos).apply();
				}
			}
			
			try {
				String videoTitle = getIntent().getStringExtra("video_title");
				if (videoTitle != null && !videoTitle.isEmpty() && totalDuration > 0) {
					
					int progressPercentage = (int) ((currentPos * 100) / totalDuration);
					if (progressPercentage > 100) progressPercentage = 100;
					if (progressPercentage < 0) progressPercentage = 0;
					
					if ((totalDuration - currentPos) < 15000) {
						progressPercentage = 100;
					}
					
					java.util.ArrayList<java.util.HashMap<String, Object>> historyList = new java.util.ArrayList<>();
					String historyJson = history_pref.getString("watch_history", "");
					
					if (!historyJson.isEmpty()) {
						historyList = new com.google.gson.Gson().fromJson(historyJson, new com.google.gson.reflect.TypeToken<java.util.ArrayList<java.util.HashMap<String, Object>>>(){}.getType());
					}
					
					for (int i = 0; i < historyList.size(); i++) {
						if (String.valueOf(historyList.get(i).get("title")).equals(videoTitle)) {
							historyList.get(i).put("progress", progressPercentage);
							break;
						}
					}
					
					history_pref.edit().putString("watch_history", new com.google.gson.Gson().toJson(historyList)).apply();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			player_view.setPlayer(null);
			player.release();
			player = null;
		}
		
		if (_timer != null) {
			_timer.cancel();
		}
		
	}
	
	@Override
	public void onBackPressed() {
		if (player != null) {
			long currentPos = player.getCurrentPosition();
			long totalDuration = player.getDuration();
			
			String onlineLink = getIntent().getStringExtra("link");
			String videoPath = getIntent().getStringExtra("video_path");
			current_video_id = (onlineLink != null && !onlineLink.isEmpty()) ? onlineLink : videoPath;
			
			if (current_video_id != null && !current_video_id.isEmpty()) {
				if (totalDuration > 0 && (totalDuration - currentPos) < 15000) {
					resume_prefs.edit().remove(current_video_id).apply();
				} else if (currentPos > 5000) {
					resume_prefs.edit().putLong(current_video_id, currentPos).apply();
				}
			}
			
			try {
				String videoTitle = getIntent().getStringExtra("video_title");
				if (videoTitle != null && !videoTitle.isEmpty() && totalDuration > 0) {
					
					int progressPercentage = (int) ((currentPos * 100) / totalDuration);
					if (progressPercentage > 100) progressPercentage = 100;
					if (progressPercentage < 0) progressPercentage = 0;
					
					if ((totalDuration - currentPos) < 15000) {
						progressPercentage = 100;
					}
					
					java.util.ArrayList<java.util.HashMap<String, Object>> historyList = new java.util.ArrayList<>();
					String historyJson = history_pref.getString("watch_history", "");
					
					if (!historyJson.isEmpty()) {
						historyList = new com.google.gson.Gson().fromJson(historyJson, new com.google.gson.reflect.TypeToken<java.util.ArrayList<java.util.HashMap<String, Object>>>(){}.getType());
					}
					
					for (int i = 0; i < historyList.size(); i++) {
						if (String.valueOf(historyList.get(i).get("title")).equals(videoTitle)) {
							historyList.get(i).put("progress", progressPercentage);
							break;
						}
					}
					
					history_pref.edit().putString("watch_history", new com.google.gson.Gson().toJson(historyList)).apply();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			player_view.setPlayer(null);
			player.release();
			player = null;
		}
		
		if (_timer != null) {
			_timer.cancel();
		}
		
		finish();
		overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
		
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if (player != null) {
			player.setPlayWhenReady(false); // pause the movie
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (player != null) {
			player.setPlayWhenReady(true); //Resume the movie
		}
	}
	public void _Extra() {
	}
	protected void onRestart() {
		super.onRestart();
		if (player != null) {
			player.setPlayWhenReady(true);
			player.getPlaybackState();
		}
	}
	{
	}
	
	
	public void _Events() {
		final ImageView btFullScreen = (ImageView)player_view.findViewById(R.id.bt_fullscreen);
		final ProgressBar prg = (ProgressBar)player_view.findViewById(R.id.progress);
		final ImageView play1 = (ImageView)player_view.findViewById(R.id.exo_play);
		final ImageView pause1 = (ImageView)player_view.findViewById(R.id.exo_pause);
		final ImageView stgs = (ImageView)player_view.findViewById(R.id.settings);
		final ImageView backbtn = (ImageView)player_view.findViewById(R.id.back);
		final TextView topTitle = (TextView)player_view.findViewById(R.id.topTitle);
		final LinearLayout linear3 = (LinearLayout)player_view.findViewById(R.id.linear3);
		final ImageView lock_image = (ImageView)player_view.findViewById(R.id.lock_image);
		
		String intentTitle = getIntent().getStringExtra("title");
		if (intentTitle != null && !intentTitle.isEmpty()) {
			topTitle.setText(intentTitle);
		}
		_Progress(prg, "#FFFFFF");
		
		player.addListener(new EventListener() {
			@Override
			public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
			}
			
			@Override
			public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
				loading = false;
				pause1.setVisibility(View.VISIBLE);
				prg.setVisibility(View.GONE);
			}
			
			@Override
			public void onLoadingChanged(boolean isLoading) {
			}
			
			@Override
			public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
				if (playbackState == 2) {
					loading = true;
					prg.setVisibility(View.VISIBLE);
				} else {
					if (playbackState == 3) {
						loading = false;
						prg.setVisibility(View.GONE);
					}
				}
				_updateBufferBar(player.getBufferedPercentage());
			}
			
			@Override
			public void onRepeatModeChanged(int repeatMode) {
			}
			
			@Override
			public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
			}
			
			@Override
			public void onPlayerError(ExoPlaybackException error) {
			}
			
			@Override
			public void onPositionDiscontinuity(int reason) {
			}
			
			@Override
			public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
			}
			
			@Override
			public void onSeekProcessed() {
			}
		});
		
		check = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (loading) {
							pause1.setVisibility(View.GONE);
							play1.setVisibility(View.GONE);
							play1.setAlpha((float)(0));
							pause1.setAlpha((float)(0));
						} else {
							play1.setAlpha((float)(1));
							pause1.setAlpha((float)(1));
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(check, (int)(0), (int)(1));
		
		stgs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				// FIXED: Replaced non-existent R.style.DarkDialogTheme with a platform-safe standard dark Material layout style link
				MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(PlayerActivity.this, com.google.android.material.R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Centered);
				builder.setTitle("Video Speed");
				builder.setItems(speed, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int position) {
						if ((position + 1) == 1) {
							try {
								PlaybackParams param = new PlaybackParams();
								param.setSpeed(0.25f);
								player.setPlaybackParams(param);
								SketchwareUtil.showMessage(getApplicationContext(), "x 0.25");
							} catch(Exception e) {
								SketchwareUtil.showMessage(getApplicationContext(), e.toString());
							}
						} else {
							if ((position + 1) == 2) {
								try {
									PlaybackParams param = new PlaybackParams();
									param.setSpeed(0.75f);
									player.setPlaybackParams(param);
									SketchwareUtil.showMessage(getApplicationContext(), "x 0.75");
								} catch(Exception e) {
									SketchwareUtil.showMessage(getApplicationContext(), e.toString());
								}
							} else {
								if ((position + 1) == 3) {
									try {
										PlaybackParams param = new PlaybackParams();
										param.setSpeed(1.0f);
										player.setPlaybackParams(param);
										SketchwareUtil.showMessage(getApplicationContext(), "Normal");
									} catch(Exception e) {
										SketchwareUtil.showMessage(getApplicationContext(), e.toString());
									}
								} else {
									if ((position + 1) == 4) {
										try {
											PlaybackParams param = new PlaybackParams();
											param.setSpeed(1.25f);
											player.setPlaybackParams(param);
											SketchwareUtil.showMessage(getApplicationContext(), "x 1.25");
										} catch(Exception e) {
											SketchwareUtil.showMessage(getApplicationContext(), e.toString());
										}
									} else {
										if ((position + 1) == 5) {
											try {
												PlaybackParams param = new PlaybackParams();
												param.setSpeed(1.5f);
												player.setPlaybackParams(param);
												SketchwareUtil.showMessage(getApplicationContext(), "x 1.5");
											} catch(Exception e) {
												SketchwareUtil.showMessage(getApplicationContext(), e.toString());
											}
										} else {
											if ((position + 1) == 6) {
												try {
													PlaybackParams param = new PlaybackParams();
													param.setSpeed(1.75f);
													player.setPlaybackParams(param);
													SketchwareUtil.showMessage(getApplicationContext(), "x 1.75");
												} catch(Exception e) {
													SketchwareUtil.showMessage(getApplicationContext(), e.toString());
												}
											} else {
												if ((position + 1) == 7) {
													try {
														PlaybackParams param = new PlaybackParams();
														param.setSpeed(2.0f);
														player.setPlaybackParams(param);
														SketchwareUtil.showMessage(getApplicationContext(), "x 2.0");
													} catch(Exception e) {
														SketchwareUtil.showMessage(getApplicationContext(), e.toString());
													}
												} else {
													if ((position + 1) == 8) {
														try {
															PlaybackParams param = new PlaybackParams();
															param.setSpeed(2.5f);
															player.setPlaybackParams(param);
															SketchwareUtil.showMessage(getApplicationContext(), "x 2.5");
														} catch(Exception e) {
															SketchwareUtil.showMessage(getApplicationContext(), e.toString());
														}
													} else {
														if ((position + 1) == 9) {
															try {
																PlaybackParams param = new PlaybackParams();
																param.setSpeed(3.0f);
																player.setPlaybackParams(param);
																SketchwareUtil.showMessage(getApplicationContext(), "x 3.0");
															} catch(Exception e) {
																SketchwareUtil.showMessage(getApplicationContext(), e.toString());
															}
														} else {
															if ((position + 1) == 10) {
																try {
																	PlaybackParams param = new PlaybackParams();
																	param.setSpeed(3.5f);
																	player.setPlaybackParams(param);
																	SketchwareUtil.showMessage(getApplicationContext(), "x 3.5");
																} catch(Exception e) {
																	SketchwareUtil.showMessage(getApplicationContext(), e.toString());
																}
															} else {
																if ((position + 1) == 11) {
																	try {
																		PlaybackParams param = new PlaybackParams();
																		param.setSpeed(4.0f);
																		player.setPlaybackParams(param);
																		SketchwareUtil.showMessage(getApplicationContext(), "x 4.0");
																	} catch(Exception e) {
																		SketchwareUtil.showMessage(getApplicationContext(), e.toString());
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				});
				builder.show();
			}
		});
		
		backbtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				onBackPressed();
			}
		});
		
		btFullScreen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (orien) {
					btFullScreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen_exit_white));
					player_view.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
					orien = false;
				} else {
					btFullScreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen_white_small));
					player_view.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
					orien = true;
				}
			}
		});
		
		lock_image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (lock) {
					lock = false;
					lock_image.setImageResource(R.drawable.ic_lock_open_white);
					linear3.setVisibility(View.VISIBLE);
					topTitle.setVisibility(View.VISIBLE);
					backbtn.setVisibility(View.VISIBLE);
				} else {
					lock = true;
					lock_image.setImageResource(R.drawable.ic_lock_white);
					linear3.setVisibility(View.INVISIBLE);
					topTitle.setVisibility(View.INVISIBLE);
					backbtn.setVisibility(View.INVISIBLE);
				}
			}
		});
		
		_setupDoubleTap();
	}
	
	
	public void _Progress(final ProgressBar _prgs, final String _color) {
		if
		(android.os.Build.VERSION.SDK_INT >= 21) {
			_prgs.getIndeterminateDrawable().setColorFilter(Color.parseColor(_color),
			PorterDuff.Mode.SRC_IN);
		}
	}
	
	
	public void _speed_types() {
	}
	String[] speed = {"0.25x","0.75x","Normal","1.25x","1.5x","1.75x","2.0x","2.5x","3.0x","3.5x","4.0x"};
	{
	}
	
	
	public void _extra() {
	}
	private static final int UI_OPTIONS = View.SYSTEM_UI_FLAG_LOW_PROFILE | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
	private void hideSystemUI() {
		ActionBar actionBar = getActionBar();
		if (actionBar != null) actionBar.hide();
		getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
	}
	{
	}
	
	
	public void _HLS() {
		
	}
	
	
	public void _setupCache() {
		com.google.android.exoplayer2.upstream.cache.SimpleCache simpleCache =
		new com.google.android.exoplayer2.upstream.cache.SimpleCache(
		new java.io.File(getCacheDir(), "reelix_stream_cache"),
		new com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor(512 * 1024 * 1024) // 512MB
		);
		
		com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory httpDataSourceFactory =
		new com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory(
		"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/150 Safari/537.36"
		);
		
		cacheFactory = new com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory(
		simpleCache,
		httpDataSourceFactory,
		com.google.android.exoplayer2.upstream.cache.CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR
		);
	}
	
	
	public void _updateBufferBar(final double _bufferedPercent) {
		com.google.android.exoplayer2.ui.DefaultTimeBar timeBar = 
		(com.google.android.exoplayer2.ui.DefaultTimeBar) player_view.findViewById(R.id.exo_progress);
		if (timeBar != null) {
			timeBar.setBufferedPosition((long)((_bufferedPercent / 100.0) * player.getDuration()));
		}
	}
	
	
	public void _setupDoubleTap() {
		final android.view.GestureDetector gestureDetector = new android.view.GestureDetector(PlayerActivity.this, new android.view.GestureDetector.SimpleOnGestureListener() {
			
			@Override
			public boolean onDoubleTap(android.view.MotionEvent e) {
				float x = e.getX();
				float screenWidth = player_view.getWidth();
				
				if (x < screenWidth / 2) {
					long newPos = Math.max(0, player.getCurrentPosition() - 10000);
					player.seekTo(newPos);
					final View rewView = player_view.findViewById(R.id.exo_rew);
					if (rewView != null) {
						rewView.animate().cancel();
						rewView.setAlpha(1f);
						rewView.setScaleX(1.3f);
						rewView.setScaleY(1.3f);
						rewView.animate().scaleX(1f).scaleY(1f).alpha(0.5f).setDuration(400).withEndAction(new Runnable() {
							@Override public void run() { rewView.setAlpha(1f); }
						}).start();
					}
				} else {
					long newPos = Math.min(player.getDuration(), player.getCurrentPosition() + 10000);
					player.seekTo(newPos);
					final View ffwdView = player_view.findViewById(R.id.exo_ffwd);
					if (ffwdView != null) {
						ffwdView.animate().cancel();
						ffwdView.setAlpha(1f);
						ffwdView.setScaleX(1.3f);
						ffwdView.setScaleY(1.3f);
						ffwdView.animate().scaleX(1f).scaleY(1f).alpha(0.5f).setDuration(400).withEndAction(new Runnable() {
							@Override public void run() { ffwdView.setAlpha(1f); }
						}).start();
					}
				}
				return true;
			}
			
			@Override
			public boolean onSingleTapConfirmed(android.view.MotionEvent e) {
				player_view.performClick();
				return true;
			}
		});
		
		player_view.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, android.view.MotionEvent event) {
				gestureDetector.onTouchEvent(event);
				return false;
			}
		});
	}
	
	
	public void _checkAndShowResumeBanner() {
		String onlineLink = getIntent().getStringExtra("link");
		String videoPath = getIntent().getStringExtra("video_path");
		current_video_id = (onlineLink != null && !onlineLink.isEmpty()) ? onlineLink : videoPath;
		
		if (current_video_id == null || current_video_id.isEmpty()) return;
		
		final long savedPosition = resume_prefs.getLong(current_video_id, 0);
		
		// Only trigger banner if saved position is valid (greater than 5 seconds)
		if (savedPosition > 5000 && player != null) {
			
			// 1. Build the dynamic parent container matching your exact layout schema
			final FrameLayout rootDecor = (FrameLayout) getWindow().getDecorView().findViewById(android.R.id.content);
			
			final LinearLayout bannerMain = new LinearLayout(this);
			bannerMain.setOrientation(LinearLayout.HORIZONTAL);
			bannerMain.setGravity(Gravity.CENTER_VERTICAL);
			
			// REDUCED MAIN BG PADDING: Making it look slim and compact
			bannerMain.setPadding(25, 12, 30, 12);
			
			// Apply styling matching your seek indicator: Dark semi-transparent (#CC000000) with rounded corners
			GradientDrawable bannerBg = new GradientDrawable();
			bannerBg.setColor(Color.parseColor("#CC000000"));
			bannerBg.setCornerRadius(40f);
			bannerMain.setBackground(bannerBg);
			
			// 2. Element: Close/Dismiss Icon (SHRANK SIZE & OPTIMIZED BOUNDS)
			ImageView dismissBtn = new ImageView(this);
			dismissBtn.setImageResource(R.drawable.close);
			dismissBtn.setScaleType(ImageView.ScaleType.FIT_CENTER); // Fit nicely inside explicit constraints
			dismissBtn.setPadding(4, 4, 4, 4); // Micro padding
			
			// Force tiny dimension boundaries on the close icon (approx 24dp)
			int iconSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
			LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(iconSize, iconSize);
			dismissBtn.setLayoutParams(iconParams);
			
			// 3. Element: Text Description (White/Gray accent text)
			TextView titleTxt = new TextView(this);
			titleTxt.setText("Continue from where you stopped?");
			titleTxt.setTextColor(Color.parseColor("#ECEFF1"));
			titleTxt.setTextSize(12f); // Slightly smaller font to look sleeker
			titleTxt.setPadding(15, 6, 20, 6); // Reduced padding to preserve horizontal space
			titleTxt.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ooo.ttf"), Typeface.NORMAL);
			
			// 4. Element: RESUME Text Button Container (Styled Red & Bold as requested)
			LinearLayout resumeActionContainer = new LinearLayout(this);
			resumeActionContainer.setGravity(Gravity.CENTER);
			
			TextView resumeBtnText = new TextView(this);
			resumeBtnText.setText("RESUME");
			resumeBtnText.setTextColor(Color.parseColor("#E53935")); // Sharp Material Red Color
			resumeBtnText.setTextSize(14f);
			resumeBtnText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ooo.ttf"), Typeface.BOLD);
			resumeBtnText.setPadding(10, 6, 10, 6); // Tight padding around action text
			resumeActionContainer.addView(resumeBtnText);
			
			// 5. Build hierarchy tree
			bannerMain.addView(dismissBtn);
			bannerMain.addView(titleTxt);
			bannerMain.addView(resumeActionContainer);
			
			// Align banner safely at the Bottom-Right quadrant of the display layout
			FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
			FrameLayout.LayoutParams.WRAP_CONTENT,
			FrameLayout.LayoutParams.WRAP_CONTENT,
			Gravity.BOTTOM | Gravity.RIGHT
			);
			layoutParams.setMargins(0, 0, 50, 60); // Clean floating spacing boundaries
			
			rootDecor.addView(bannerMain, layoutParams);
			
			// 6. Action Implementation: Dismiss
			dismissBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					bannerMain.animate()
					.translationX(bannerMain.getWidth() + 100)
					.alpha(0f)
					.setDuration(350)
					.withEndAction(new Runnable() {
						@Override
						public void run() {
							rootDecor.removeView(bannerMain);
						}
					}).start();
				}
			});
			
			// 7. Action Implementation: Seek to Saved Position
			resumeActionContainer.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (player != null) {
						player.seekTo(savedPosition);
						//SketchwareUtil.showMessage(getApplicationContext(), "Resumed playback state!");
					}
					// Animate banner safely away out of the screen layout constraints
					dismissBtn.performClick();
				}
			});
			
			// 8. ANIMATION LAYER: Slide into view smoothly from off-screen right limits
			bannerMain.setAlpha(0f);
			bannerMain.post(new Runnable() {
				@Override
				public void run() {
					bannerMain.setTranslationX(bannerMain.getWidth() + 100);
					bannerMain.setAlpha(1f);
					bannerMain.animate()
					.translationX(0)
					.setDuration(500)
					.setInterpolator(new android.view.animation.DecelerateInterpolator())
					.start();
				}
			});
			
			// Auto-dismiss the floating banner if left unattended for 8 seconds
			bannerMain.postDelayed(new Runnable() {
				@Override
				public void run() {
					if (bannerMain.getParent() != null) {
						dismissBtn.performClick();
					}
				}
			}, 8000);
		}
		
	}
	
	
	public void _findMp4InFolder(final String _folderPath) {
		// Inside More Block: findMp4InFolder
		java.io.File folder = new java.io.File(_folderPath); // Added underscore here
		String foundPath = "";
		
		if (folder.exists() && folder.isDirectory()) {
			java.io.File[] files = folder.listFiles();
			if (files != null) {
				for (java.io.File file : files) {
					if (file.isFile() && file.getName().toLowerCase().endsWith(".mp4")) {
						foundPath = file.getAbsolutePath();
						break;
					}
				}
			}
		}
		
		// Store the path back into our global parameter tracking variable
		picked_subtitle_path = foundPath; 
		
	}
	
}