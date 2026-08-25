package com.error404.reelix;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
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
import android.widget.HorizontalScrollView;
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
import androidx.core.widget.NestedScrollView;
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
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.slidingpanelayout.*;
import androidx.swiperefreshlayout.*;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;
import androidx.vectordrawable.*;
import androidx.versionedparcelable.*;
import androidx.viewpager.*;
import com.budiyev.android.codescanner.*;
import com.bumptech.glide.*;
import com.bumptech.glide.gifdecoder.*;
import com.error404.reelix.NotchedBottomNav;
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
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import com.google.gson.reflect.TypeToken;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DownloadsActivity extends AppCompatActivity {
	
	private String fontName = "";
	private String typeace = "";
	
	private ArrayList<HashMap<String, Object>> downloading_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> downloaded_list = new ArrayList<>();
	
	private LinearLayout linear1;
	private RelativeLayout back_relative;
	private SwipeRefreshLayout swiperefreshlayout1;
	private NotchedBottomNav notchedBottomNav;
	private NestedScrollView nestedScrollView1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout downloading_holder;
	private LinearLayout linear7;
	private LinearLayout lower_holder;
	private ImageView imageview1;
	private TextView app_name;
	private TextView textview1;
	private BlurView transfer_button;
	private LinearLayout linear5;
	private ImageView imageview2;
	private TextView textview2;
	private LinearLayout linear6;
	private RecyclerView downloading_rec_list;
	private TextView textview3;
	private TextView textview4;
	private TextView textview5;
	private HorizontalScrollView hscroll2;
	private RecyclerView downloaded_rec_list;
	private LinearLayout linear8;
	private LinearLayout cat_chip1;
	private LinearLayout cat_chip2;
	private LinearLayout cat_chip3;
	private TextView cat_txt1;
	private TextView cat_txt2;
	private TextView cat_txt3;
	private LinearLayout main_back;
	private LinearLayout other_back;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private ImageView imageview3;
	private ImageView imageview4;
	private ImageView imageview5;
	private ImageView imageview6;
	
	private Intent intent = new Intent();
	private SharedPreferences history_pref;
	private AlertDialog.Builder dialog;
	private com.google.android.material.bottomsheet.BottomSheetDialog sheet;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.downloads);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		back_relative = findViewById(R.id.back_relative);
		swiperefreshlayout1 = findViewById(R.id.swiperefreshlayout1);
		notchedBottomNav = findViewById(R.id.notchedBottomNav);
		nestedScrollView1 = findViewById(R.id.nestedScrollView1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		downloading_holder = findViewById(R.id.downloading_holder);
		linear7 = findViewById(R.id.linear7);
		lower_holder = findViewById(R.id.lower_holder);
		imageview1 = findViewById(R.id.imageview1);
		app_name = findViewById(R.id.app_name);
		textview1 = findViewById(R.id.textview1);
		transfer_button = findViewById(R.id.transfer_button);
		linear5 = findViewById(R.id.linear5);
		imageview2 = findViewById(R.id.imageview2);
		textview2 = findViewById(R.id.textview2);
		linear6 = findViewById(R.id.linear6);
		downloading_rec_list = findViewById(R.id.downloading_rec_list);
		textview3 = findViewById(R.id.textview3);
		textview4 = findViewById(R.id.textview4);
		textview5 = findViewById(R.id.textview5);
		hscroll2 = findViewById(R.id.hscroll2);
		downloaded_rec_list = findViewById(R.id.downloaded_rec_list);
		linear8 = findViewById(R.id.linear8);
		cat_chip1 = findViewById(R.id.cat_chip1);
		cat_chip2 = findViewById(R.id.cat_chip2);
		cat_chip3 = findViewById(R.id.cat_chip3);
		cat_txt1 = findViewById(R.id.cat_txt1);
		cat_txt2 = findViewById(R.id.cat_txt2);
		cat_txt3 = findViewById(R.id.cat_txt3);
		main_back = findViewById(R.id.main_back);
		other_back = findViewById(R.id.other_back);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		linear11 = findViewById(R.id.linear11);
		linear12 = findViewById(R.id.linear12);
		imageview3 = findViewById(R.id.imageview3);
		imageview4 = findViewById(R.id.imageview4);
		imageview5 = findViewById(R.id.imageview5);
		imageview6 = findViewById(R.id.imageview6);
		history_pref = getSharedPreferences("history_pref", Activity.MODE_PRIVATE);
		dialog = new AlertDialog.Builder(this);
		
		transfer_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), TransferActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
			}
		});
		
		cat_chip2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), LocalMediaActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
			}
		});
		
		linear9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(imageview3);
				intent.setClass(getApplicationContext(), FeedActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		
		linear10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(imageview4);
				intent.setClass(getApplicationContext(), SearchActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		
		linear12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(imageview6);
				
				intent.setClass(getApplicationContext(), ProfileActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				
				// Apply smooth custom transition
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			final Window window = DownloadsActivity.this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(0xFF0B0D0F);
		}
		_changeActivityFont("ooo");
		hscroll2.setHorizontalScrollBarEnabled(false);
		hscroll2.setVerticalScrollBarEnabled(false);
		hscroll2.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
		cat_chip1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)2, 0xFF212121, Color.TRANSPARENT));
		cat_chip2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)2, 0xFF212121, Color.TRANSPARENT));
		cat_chip3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)2, 0xFF212121, Color.TRANSPARENT));
		
		
		// Add this at the VERY BEGINNING of your onCreate, before anything else
		ReelixDownloadHelper.resumeAllDownloads(this);
		
		// Initialize lists if needed
		if (downloading_list == null) {
			downloading_list = new java.util.ArrayList<>();
		}
		if (downloaded_list == null) {
			downloaded_list = new java.util.ArrayList<>();
		}
		
		// Load active downloads
		ReelixDownloadHelper.loadActiveMetadata(this, downloading_list);
		
		// 1. Link the CORRECT custom adapter for DOWNLOADING
		Downloading_rec_listAdapter customDownloadingAdapter = new Downloading_rec_listAdapter(downloading_list);
		ReelixDownloadHelper.downloading_adapter = customDownloadingAdapter;
		downloading_rec_list.setAdapter(customDownloadingAdapter);
		downloading_rec_list.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));
		
		// Link the adapter for COMPLETED downloads
		Downloaded_rec_listAdapter downloadedAdapter = new Downloaded_rec_listAdapter(downloaded_list);
		downloaded_rec_list.setAdapter(downloadedAdapter);
		downloaded_rec_list.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));
		
		// Prevent lists from fighting the NestedScrollView
		downloading_rec_list.setNestedScrollingEnabled(false);
		downloaded_rec_list.setNestedScrollingEnabled(false);
		
		// 2. Setup Pull-to-Refresh
		swiperefreshlayout1.setOnRefreshListener(new androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				ReelixDownloadHelper.loadActiveMetadata(getApplicationContext(), downloading_list);
				ReelixDownloadHelper.loadCompletedDownloads(getApplicationContext(), downloaded_list);
				
				if (ReelixDownloadHelper.downloading_adapter != null) {
					ReelixDownloadHelper.downloading_adapter.notifyDataSetChanged();
				}
				if (downloaded_rec_list.getAdapter() != null) {
					downloaded_rec_list.getAdapter().notifyDataSetChanged();
				}
				swiperefreshlayout1.setRefreshing(false);
			}
		});
		
		// 3. The 1-second Loop
		ReelixDownloadHelper.progressRunnable = new Runnable() {
			int previousSize = -1;
			
			@Override
			public void run() {
				if (previousSize == -1) {
					previousSize = downloading_list.size();
				}
				
				ReelixDownloadHelper.updateLiveProgress(getApplicationContext(), downloading_list);
				
				boolean hasFinished = downloading_list.size() < previousSize;
				previousSize = downloading_list.size();
				
				if (hasFinished) {
					ReelixDownloadHelper.loadCompletedDownloads(getApplicationContext(), downloaded_list);
					if (downloaded_rec_list.getAdapter() != null) {
						downloaded_rec_list.getAdapter().notifyDataSetChanged();
					}
				}
				
				textview3.setText("Downloading (" + downloading_list.size() + ")");
				if (downloading_list.isEmpty()) {
					downloading_holder.setVisibility(android.view.View.GONE);
				} else {
					downloading_holder.setVisibility(android.view.View.VISIBLE);
				}
				
				if (ReelixDownloadHelper.downloading_adapter != null) {
					ReelixDownloadHelper.downloading_adapter.notifyDataSetChanged();
				}
				
				ReelixDownloadHelper.progressHandler.postDelayed(this, 1000);
			}
		};
		
		// 4. Start the progress updates
		ReelixDownloadHelper.progressHandler.post(ReelixDownloadHelper.progressRunnable);
		
		// 5. Initial load of completed downloads
		ReelixDownloadHelper.loadCompletedDownloads(getApplicationContext(), downloaded_list);
		imageview5.setColorFilter(0xFFCE0002, PorterDuff.Mode.MULTIPLY);
		notchedBottomNav.setTopCornerRadius(13 * getResources().getDisplayMetrics().density);
		notchedBottomNav.setBottomCornerRadius(20 * getResources().getDisplayMetrics().density);
		
		float density = getApplicationContext().getResources().getDisplayMetrics().density;
		
		notchedBottomNav.setNotchRadius(36 * density);
		notchedBottomNav.setNotchDepth(28 * density);
		notchedBottomNav.setCurveSpanMultiplier(2.0f);
		notchedBottomNav.setNotchGeometry(40 * density, 24 * density);
		notchedBottomNav.setTopCornerRadius(16 * density);
		notchedBottomNav.setBottomCornerRadius(20 * density);
		notchedBottomNav.setBarColors(0xFF2A1618, 0xFF1F2024);
		
		ViewGroup activityContainer = findViewById(android.R.id.content);
		float blurRadius = 25f;
		Drawable windowBackground = getWindow().getDecorView().getBackground();
		
		final TextView textView = (TextView) findViewById(R.id.app_name);
		
		ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
		animator.setDuration(3000); 
		animator.setRepeatCount(ValueAnimator.INFINITE);
		animator.setRepeatMode(ValueAnimator.REVERSE); 
		
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float animatedValue = (float) animation.getAnimatedValue();
				float width = textView.getWidth();
				float shift = width * animatedValue;
				
				int[] colors = {
					Color.parseColor("#000000"), 
					Color.parseColor("#E50914"), 
					Color.parseColor("#000000")  
				};
				
				android.graphics.LinearGradient shader = new android.graphics.LinearGradient(
				0 + shift, 0, 
				width + shift, 0, 
				colors, 
				null, 
				android.graphics.Shader.TileMode.MIRROR
				);
				
				textView.getPaint().setShader(shader);
				textView.invalidate();
			}
		});
		
		textView.post(new Runnable() {
			@Override
			public void run() {
				animator.start();
			}
		});
		
		// Now setup transfer_button (activityContainer and windowBackground are already declared above)
		int[] attrs = new int[]{android.R.attr.selectableItemBackgroundBorderless};
		android.content.res.TypedArray typedArray = obtainStyledAttributes(attrs);
		android.graphics.drawable.Drawable ripple = typedArray.getDrawable(0);
		typedArray.recycle();
		
		transfer_button.setupWith(activityContainer)
		.setFrameClearDrawable(windowBackground)
		.setBlurRadius(25f)
		.setBlurAutoUpdate(true)
		.setOverlayColor(Color.argb(60, 255, 255, 255));
		
		transfer_button.setOutlineProvider(new android.view.ViewOutlineProvider() {
			@Override
			public void getOutline(android.view.View view, android.graphics.Outline outline) {
				outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 40);
			}
		});
		transfer_button.setClipToOutline(true);
		transfer_button.setForeground(ripple);
		transfer_button.setClickable(true);
		
	}
	
	
	// MOVE THIS ENTIRE @Override METHOD TO THE END, OUTSIDE ANY OTHER METHODS
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		overridePendingTransition(com.error404.reelix.R.anim.fade_in, com.error404.reelix.R.anim.fade_out);
		
	}
	
	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(0, 0);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		ReelixDownloadHelper.resumeAllDownloads(this);
		if (ReelixDownloadHelper.progressRunnable != null) {
			ReelixDownloadHelper.progressHandler.removeCallbacks(ReelixDownloadHelper.progressRunnable);
			ReelixDownloadHelper.progressHandler.post(ReelixDownloadHelper.progressRunnable);
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		ReelixDownloadHelper.progressHandler.removeCallbacks(ReelixDownloadHelper.progressRunnable);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (ReelixDownloadHelper.progressHandler != null && ReelixDownloadHelper.progressRunnable != null) {
			ReelixDownloadHelper.progressHandler.removeCallbacks(ReelixDownloadHelper.progressRunnable);
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
	
	
	public void _clickAnimation(final View _view) {
		ScaleAnimation fade_in = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
		fade_in.setDuration(300);
		fade_in.setFillAfter(true);
		_view.startAnimation(fade_in);
	}
	
	
	public void _customLoading(final boolean _visibility) {
		try {
			// Get the global root container of the current activity
			android.view.ViewGroup rootContainer = (android.view.ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
			
			// Attempt to find an already inflated instance using a unique tag string
			View inflatedLoadingView = rootContainer.findViewWithTag("built_in_loading_overlay");
			
			if (_visibility) {
				if (inflatedLoadingView == null) {
					// Inflate directly since it doesn't exist yet
					inflatedLoadingView = getLayoutInflater().inflate(R.layout.custom_loading, null);
					inflatedLoadingView.setTag("built_in_loading_overlay");
					
					// Block touches behind the overlay layer
					inflatedLoadingView.setClickable(true);
					inflatedLoadingView.setFocusable(true);
					
					// Inject it globally over the activity layout
					rootContainer.addView(inflatedLoadingView);
				}
				
				// Target layouts inside custom_loading.xml
				LinearLayout mainLayout = inflatedLoadingView.findViewById(R.id.main);
				com.airbnb.lottie.LottieAnimationView lottieView = inflatedLoadingView.findViewById(R.id.lottie1);
				
				mainLayout.setBackgroundColor(Color.parseColor("#80000000")); 
				inflatedLoadingView.setVisibility(View.VISIBLE);
				lottieView.playAnimation();
			} else {
				// If false is called and the view exists, clean it up
				if (inflatedLoadingView != null) {
					com.airbnb.lottie.LottieAnimationView lottieView = inflatedLoadingView.findViewById(R.id.lottie1);
					if (lottieView != null) {
						lottieView.cancelAnimation();
					}
					inflatedLoadingView.setVisibility(View.GONE);
					
					// Completely detach it from the view hierarchy to free up RAM
					rootContainer.removeView(inflatedLoadingView);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public class Downloading_rec_listAdapter extends RecyclerView.Adapter<Downloading_rec_listAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Downloading_rec_listAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.downloading, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final ImageView movie_image = _view.findViewById(R.id.movie_image);
			final TextView file_name_txt = _view.findViewById(R.id.file_name_txt);
			final ProgressBar download_progress = _view.findViewById(R.id.download_progress);
			final TextView bytes_downloaded_txt = _view.findViewById(R.id.bytes_downloaded_txt);
			final ImageView more_btn = _view.findViewById(R.id.more_btn);
			final eightbitlab.com.blurview.BlurView pause_download_btn = _view.findViewById(R.id.pause_download_btn);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final ImageView pause_download_indicator_img = _view.findViewById(R.id.pause_download_indicator_img);
			
			int[] attrs = new int[]{android.R.attr.selectableItemBackgroundBorderless};
			android.content.res.TypedArray typedArray = obtainStyledAttributes(attrs);
			android.graphics.drawable.Drawable ripple = typedArray.getDrawable(0);
			typedArray.recycle();
			
			// ---- ADD THESE TWO LINES TO FIX THE ERRORS ----
			android.view.ViewGroup activityContainer = (android.view.ViewGroup) ((android.app.Activity) _view.getContext()).findViewById(android.R.id.content);
			android.graphics.drawable.Drawable windowBackground = ((android.app.Activity) _view.getContext()).getWindow().getDecorView().getBackground();
			// -----------------------------------------------
			
			pause_download_btn.setupWith(activityContainer)
			.setFrameClearDrawable(windowBackground)
			.setBlurRadius(25f)
			.setBlurAutoUpdate(true)
			.setOverlayColor(Color.argb(60, 255, 255, 255));
			
			pause_download_btn.setOutlineProvider(new android.view.ViewOutlineProvider() {
				@Override
				public void getOutline(android.view.View view, android.graphics.Outline outline) {
					outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 40);
				}
			});
			pause_download_btn.setClipToOutline(true);
			pause_download_btn.setForeground(ripple);
			pause_download_btn.setClickable(true);
			
			file_name_txt.setSingleLine(true);
			file_name_txt.setMaxLines(1);
			
			// Put ellipsis at the start
			file_name_txt.setEllipsize(android.text.TextUtils.TruncateAt.END);
			
			// Make sure it actually truncates instead of scrolling
			file_name_txt.setHorizontallyScrolling(false);
			// Guard clause
			if (downloading_list == null || _position >= downloading_list.size()) return;
			java.util.HashMap<String, Object> map = downloading_list.get((int)_position);
			
			// 1. Text and Image
			file_name_txt.setText(String.valueOf(map.get("title")));
			com.bumptech.glide.Glide.with(_view.getContext()).load(String.valueOf(map.get("cover_url"))).into(movie_image);
			
			// 2. Extract live metrics
			int progress = (int) map.get("progress");
			long downloaded = (long) map.get("bytes_so_far");
			long total = (long) map.get("bytes_total");
			
			// 3. Update UI
			download_progress.setProgress(progress);
			String readable = String.format(java.util.Locale.US, "%.1f MB / %.1f MB (%d%%)", 
			downloaded / (1024.0 * 1024.0), 
			total / (1024.0 * 1024.0), 
			progress);
			bytes_downloaded_txt.setText(readable);
			
			// 4. Get the current download ID and its live status using getter
			String dId = String.valueOf(map.get("download_id"));
			int currentStatus = ReelixDownloadHelper.getStatus(dId); // 2 = Running, 4 = Paused
			
			// 5. Set the initial image state when the row loads/recycles
			if (currentStatus == 4) {
				// It's paused, so show the play/download button to resume
				pause_download_indicator_img.setImageResource(R.drawable.slim_download);
			} else {
				// It's actively downloading, so show the pause button
				pause_download_indicator_img.setImageResource(R.drawable.pause);
			}
			
			// 6. Click Listener for the Pause/Resume Action
			pause_download_btn.setOnClickListener(new android.view.View.OnClickListener() {
				@Override
				public void onClick(android.view.View _v) {
					// Use the context-aware toggle
					ReelixDownloadHelper.togglePauseDownload(_view.getContext(), dId);
					
					// Update UI immediately using getter
					int newStatus = ReelixDownloadHelper.getStatus(dId);
					if (newStatus == 4) {
						pause_download_indicator_img.setImageResource(R.drawable.slim_download);
					} else {
						pause_download_indicator_img.setImageResource(R.drawable.pause);
					}
					
					// Refresh adapter
					if (ReelixDownloadHelper.downloading_adapter != null) {
						ReelixDownloadHelper.downloading_adapter.notifyItemChanged(_position);
					}
				}
			});
			cardview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					
				}
			});
			more_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					final HashMap<String, Object> item = (HashMap<String, Object>) _data.get(_position);
					
					final String dId        = String.valueOf(item.get("download_id"));
					final String folderPath = new File(String.valueOf(item.get("meta_file_path")))
					.getParent();
					
					new MaterialAlertDialogBuilder(DownloadsActivity.this)
					.setTitle("Delete download?")
					.setMessage("Are you sure you wanna delete this ongoing download?\n\nNote: This action is permanent.")
					.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							ReelixDownloadHelper.deleteDownload(
							DownloadsActivity.this,
							dId,
							folderPath
							);
							downloading_list.remove(item);
							if (ReelixDownloadHelper.downloading_adapter != null) {
								ReelixDownloadHelper.downloading_adapter.notifyDataSetChanged();
							}
						}
					})
					.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							_dialog.dismiss();
						}
					})
					.show();
				}
			});
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}
	
	public class Downloaded_rec_listAdapter extends RecyclerView.Adapter<Downloaded_rec_listAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Downloaded_rec_listAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.downloaded, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final LinearLayout hamburger_menu_holder = _view.findViewById(R.id.hamburger_menu_holder);
			final ImageView movie_image = _view.findViewById(R.id.movie_image);
			final TextView file_name_txt = _view.findViewById(R.id.file_name_txt);
			final TextView episodes_txt = _view.findViewById(R.id.episodes_txt);
			final TextView size_txt = _view.findViewById(R.id.size_txt);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			
			file_name_txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 0);
			episodes_txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 0);
			size_txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 0);
			// Force single line so it doesn't wrap to the bottom
			file_name_txt.setSingleLine(true);
			file_name_txt.setMaxLines(1);
			
			// Put ellipsis at the start
			file_name_txt.setEllipsize(android.text.TextUtils.TruncateAt.END);
			
			// Make sure it actually truncates instead of scrolling
			file_name_txt.setHorizontallyScrolling(false);
			// Guard clause: Avoid out-of-bounds array tracking crashes
			if (downloaded_list == null || _position >= downloaded_list.size()) return;
			
			java.util.HashMap<String, Object> map = downloaded_list.get((int)_position);
			
			file_name_txt.setText(String.valueOf(map.get("title")));
			
			String itemType = String.valueOf(map.get("type"));
			String episodesText = map.containsKey("episodes") ? String.valueOf(map.get("episodes")) : "";
			
			if (itemType.equals("tv") && !episodesText.isEmpty()) {
				episodes_txt.setVisibility(android.view.View.VISIBLE);
				episodes_txt.setText(episodesText);
			} else {
				episodes_txt.setVisibility(android.view.View.GONE);
			}
			
			size_txt.setText(String.valueOf(map.get("size")));
			
			String coverUrl = String.valueOf(map.get("cover_url"));
			if (coverUrl != null && !coverUrl.trim().isEmpty()) {
				com.bumptech.glide.Glide.with(_view.getContext())
				.load(coverUrl)
				.placeholder(R.drawable.shadow)
				.error(R.drawable.shadow)
				.into(movie_image);
			} else {
				movie_image.setImageResource(R.drawable.shadow);
			}
			
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (itemType.equals("tv")) {
						android.os.Bundle bundle = new android.os.Bundle();
						bundle.putString("title", String.valueOf(map.get("title")));
						bundle.putString("folder_path", String.valueOf(map.get("folder_path")));
						bundle.putString("cover_url", String.valueOf(map.get("cover_url")));
						
						DownloadedSeriesEpListBottomdialogFragmentActivity bottomSheet = new DownloadedSeriesEpListBottomdialogFragmentActivity();
						bottomSheet.setArguments(bundle);
						bottomSheet.setStyle(androidx.fragment.app.DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme);
						bottomSheet.show(((androidx.fragment.app.FragmentActivity)_view.getContext()).getSupportFragmentManager(), "series_ep_list");
					} else {
						try {
							java.util.ArrayList<java.util.HashMap<String, Object>> historyList = new java.util.ArrayList<>();
							String historyJson = history_pref.getString("watch_history", "");
							
							if (!historyJson.isEmpty()) {
								historyList = new com.google.gson.Gson().fromJson(historyJson, new com.google.gson.reflect.TypeToken<java.util.ArrayList<java.util.HashMap<String, Object>>>(){}.getType());
							}
							
							java.util.HashMap<String, Object> historyItem = new java.util.HashMap<>();
							String currentTitle = String.valueOf(map.get("title"));
							
							historyItem.put("title", currentTitle);
							historyItem.put("cover_url", String.valueOf(map.get("cover_url")));
							historyItem.put("type", "movie");
							historyItem.put("video_path", String.valueOf(map.get("video_path")));
							historyItem.put("timestamp", String.valueOf(System.currentTimeMillis()));
							
							for (int i = 0; i < historyList.size(); i++) {
								if (String.valueOf(historyList.get(i).get("title")).equals(currentTitle)) {
									historyList.remove(i);
									break;
								}
							}
							
							historyList.add(0, historyItem);
							history_pref.edit().putString("watch_history", new com.google.gson.Gson().toJson(historyList)).apply();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						android.content.Intent customPlayerIntent = new android.content.Intent();
						customPlayerIntent.setClass(_view.getContext().getApplicationContext(), PlayerActivity.class);
						customPlayerIntent.putExtra("video_path", String.valueOf(map.get("video_path")));
						customPlayerIntent.putExtra("video_title", String.valueOf(map.get("title")));
						_view.getContext().startActivity(customPlayerIntent);
						((android.app.Activity)_view.getContext()).overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
					}
				}
			});
			
			hamburger_menu_holder.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					// Pass your style theme directly into the Dialog constructor
					sheet = new com.google.android.material.bottomsheet.BottomSheetDialog(DownloadsActivity.this, R.style.BottomSheetDialogTheme);
					View sheetV;
					sheetV = getLayoutInflater().inflate(R.layout.download_options_bottom_sheet, null);
					sheet.setContentView(sheetV);
					
					// Ensure the container background is fully stripped out
					View bottomSheetInternal = sheet.getWindow().findViewById(com.google.android.material.R.id.design_bottom_sheet);
					if (bottomSheetInternal != null) {
						bottomSheetInternal.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
					}
					
					final LinearLayout linear1 = (LinearLayout) sheetV.findViewById(R.id.linear1);
					final com.google.android.material.card.MaterialCardView it_cd1 = (com.google.android.material.card.MaterialCardView) sheetV.findViewById(R.id.it_cd1);
					final TextView it_txt1 = (TextView) sheetV.findViewById(R.id.it_txt1);
					final com.google.android.material.card.MaterialCardView it_cd2 = (com.google.android.material.card.MaterialCardView) sheetV.findViewById(R.id.it_cd2);
					final TextView it_txt2 = (TextView) sheetV.findViewById(R.id.it_txt2);
					
					linear1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, getResources().getColor(R.color.md_theme_dark_surface)));
					it_txt1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ints.ttf"), 0);
					it_txt2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ints.ttf"), 0);
					
					// Set dynamic text based on whether it's a TV show or a Movie
					if (itemType.equals("tv")) {
						it_txt1.setText("Export TV Show");
						it_txt2.setText("Delete TV Show");
					} else {
						it_txt1.setText("Export Movie");
						it_txt2.setText("Delete Movie");
					}
					
					it_cd1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							sheet.dismiss();
							
							// 1. Show the loading spinner before starting the thread
							_customLoading(true);
							
							// Extract variables into final references for background thread visibility
							final String currentType = itemType;
							final String titleStr = String.valueOf(map.get("title"));
							final String videoPathStr = map.containsKey("video_path") ? String.valueOf(map.get("video_path")) : "";
							final String folderPathStr = map.containsKey("folder_path") ? String.valueOf(map.get("folder_path")) : "";
							
							new Thread(new Runnable() {
								@Override
								public void run() {
									boolean exportSuccess = false;
									String targetFileName = "";
									java.io.File sourceVideoFile = null;
									
									try {
										if (currentType.equals("tv")) {
											// --- TV Show Export Logic ---
											// For TV shows, the adapter map passes the show root folder path.
											// We need to look inside its nested season structure to find files.
											java.io.File showFolder = new java.io.File(folderPathStr);
											if (showFolder.exists() && showFolder.isDirectory()) {
												
												// Find the master_metadata.json file
												java.io.File masterMetaFile = new java.io.File(showFolder, "master_metadata.json");
												if (masterMetaFile.exists()) {
													// Read and parse the master metadata to locate the video file name
													java.io.FileReader reader = new java.io.FileReader(masterMetaFile);
													java.io.BufferedReader bufferedReader = new java.io.BufferedReader(reader);
													StringBuilder sb = new StringBuilder();
													String line;
													while ((line = bufferedReader.readLine()) != null) {
														sb.append(line);
													}
													bufferedReader.close();
													
													org.json.JSONObject masterJson = new org.json.JSONObject(sb.toString());
													
													// Check if there are any episodes indexed in this series folder
													if (masterJson.has("episodes")) {
														org.json.JSONArray episodesArray = masterJson.getJSONArray("episodes");
														if (episodesArray.length() > 0) {
															// Export the first available episode found in the folder registry
															org.json.JSONObject targetEp = episodesArray.getJSONObject(0);
															String sNum = targetEp.optString("season_number", "1");
															String eNum = targetEp.optString("episode_number", "1");
															String fName = targetEp.optString("video_file_name", "");
															
															// Reconstruct path: folder_path/Season {n}/ep{n}/{video_file_name}
															String relativeEpPath = "Season " + sNum + "/ep" + eNum + "/" + fName;
															sourceVideoFile = new java.io.File(showFolder, relativeEpPath);
															targetFileName = titleStr.replaceAll("[\\\\/:*?\"<>|]", "").trim() + "_S" + sNum + "E" + eNum + ".mp4";
														}
													}
												}
											}
										} else {
											// --- Movie Export Logic ---
											// Movies directly provide the exact absolute path to the video file in the map
											if (!videoPathStr.isEmpty()) {
												sourceVideoFile = new java.io.File(videoPathStr);
												
												// Strip out unlawful characters from the movie title to format a beautiful export file name
												String safeTitle = titleStr.replaceAll("[\\\\/:*?\"<>|]", "").trim();
												targetFileName = safeTitle.isEmpty() ? "Movie_" + System.currentTimeMillis() + ".mp4" : safeTitle + ".mp4";
											}
										}
										
										// --- Binary Stream Copy Core Engine ---
										if (sourceVideoFile != null && sourceVideoFile.exists() && !targetFileName.isEmpty()) {
											// Define target output layout: /storage/emulated/0/Download/Reelix/Exports
											java.io.File exportDir = new java.io.File(android.os.Environment.getExternalStorageDirectory(), "Download/Reelix/Exports");
											if (!exportDir.exists()) {
												exportDir.mkdirs();
											}
											
											java.io.File destinationFile = new java.io.File(exportDir, targetFileName);
											
											java.io.FileInputStream inStream = new java.io.FileInputStream(sourceVideoFile);
											java.io.FileOutputStream outStream = new java.io.FileOutputStream(destinationFile);
											
											byte[] buffer = new byte[8192]; // 8KB Chunk Blocks
											int length;
											while ((length = inStream.read(buffer)) > 0) {
												outStream.write(buffer, 0, length);
											}
											
											outStream.flush();
											outStream.close();
											inStream.close();
											
											// Make file instantly visible in Gallery/File Managers
											android.media.MediaScannerConnection.scanFile(
											DownloadsActivity.this,
											new String[]{destinationFile.getAbsolutePath()},
											new String[]{"video/mp4"},
											null
											);
											
											exportSuccess = true;
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
									
									// 3. Switch execution back to Main UI Thread to update widgets
									final boolean finalSuccess = exportSuccess;
									final String finalName = targetFileName;
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											// Dismiss the loading dialog layout
											_customLoading(false);
											
											if (finalSuccess) {
												Toast.makeText(DownloadsActivity.this, "Exported successfully: " + finalName, Toast.LENGTH_LONG).show();
											} else {
												Toast.makeText(DownloadsActivity.this, "Export failed. File not found or storage error.", Toast.LENGTH_SHORT).show();
											}
										}
									});
								}
							}).start();
						}
					});
					
					it_cd2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							sheet.dismiss();
							// TODO: Add your file deletion and list refresh logic here
						}
					});
					
					sheet.show();
				}
			});
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}
}