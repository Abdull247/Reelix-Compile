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
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import androidx.core.view.WindowCompat;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.content.pm.ActivityInfo;


public class WelcomeActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private String fontName = "";
	private String typeace = "";
	private boolean firstClick = false;
	private double click = 0;
	private double startX = 0;
	private double endX = 0;
	private double currentPage = 0;
	private View[] dots;
	private int[] currentIndex;
	private long[] lastClickTime;
	private LinearLayout mainLayout;
	private long lastGeneralClickTime;
	private Handler skipButtonHandler;
	private Runnable skipRunnable;
	private boolean isLongClick;
	private Handler handler;
	private Runnable runnable;
	
	private LinearLayout Background_intro_page;
	private LinearLayout Background_sikp_Language;
	private LinearLayout linear5;
	private ImageView img;
	private LinearLayout Background_text;
	private LinearLayout Background_transition_level;
	private LinearLayout Background_button;
	private LinearLayout linear4;
	private TextView Text_title;
	private TextView Text_info;
	private LinearLayout dot4;
	private LinearLayout linear1;
	private LinearLayout dot3;
	private LinearLayout linear2;
	private LinearLayout dot2;
	private LinearLayout linear3;
	private LinearLayout dot1;
	private LinearLayout next_button;
	private LinearLayout back_button;
	private TextView Text_next;
	private ImageView img_next;
	private TextView textview3;
	
	private TimerTask timer;
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.welcome);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		Background_intro_page = findViewById(R.id.Background_intro_page);
		Background_sikp_Language = findViewById(R.id.Background_sikp_Language);
		linear5 = findViewById(R.id.linear5);
		img = findViewById(R.id.img);
		Background_text = findViewById(R.id.Background_text);
		Background_transition_level = findViewById(R.id.Background_transition_level);
		Background_button = findViewById(R.id.Background_button);
		linear4 = findViewById(R.id.linear4);
		Text_title = findViewById(R.id.Text_title);
		Text_info = findViewById(R.id.Text_info);
		dot4 = findViewById(R.id.dot4);
		linear1 = findViewById(R.id.linear1);
		dot3 = findViewById(R.id.dot3);
		linear2 = findViewById(R.id.linear2);
		dot2 = findViewById(R.id.dot2);
		linear3 = findViewById(R.id.linear3);
		dot1 = findViewById(R.id.dot1);
		next_button = findViewById(R.id.next_button);
		back_button = findViewById(R.id.back_button);
		Text_next = findViewById(R.id.Text_next);
		img_next = findViewById(R.id.img_next);
		textview3 = findViewById(R.id.textview3);
		
		next_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		back_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
	}
	
	private void initializeLogic() {
		WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		getWindow().setStatusBarColor(Color.TRANSPARENT);
		_changeActivityFont("ooo");
		_rippleRoundStroke(next_button, "#E50914", "#000000", 100, 0, "#000000");
		_rippleRoundStroke(back_button, "#000000", "#E50914", 100, 2, "#E0E0E0");
		
		
		// Disable fullscreen mode if active
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
		// Load icons from Drawable
		Drawable drawableNext = getResources().getDrawable(R.drawable.next);
		Drawable drawableSkip = getResources().getDrawable(R.drawable.skip);
		Drawable drawableLang = getResources().getDrawable(R.drawable.Lang); // Verify file name is correct
		
		// Size in dp
		int sizeInDp = 18;
		float scale = getResources().getDisplayMetrics().density;
		int sizeInPx = (int) (sizeInDp * scale + 0.5f);
		
		// Apply icons, size, and color directly
		img_next.setImageDrawable(drawableNext);
		img_next.getLayoutParams().width = sizeInPx;
		img_next.getLayoutParams().height = sizeInPx;
		img_next.requestLayout(); // Apply new size
		img_next.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);
		
		
		// Prevent random screen rotation
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		// Initialize Handlers and memory management
		handler = new Handler(Looper.getMainLooper()); 
		skipButtonHandler = new Handler(Looper.getMainLooper());
		
		// Initialize the dots array FIRST
		dots = new View[4];
		
		// Assign global variables to element IDs
		dots[0] = findViewById(R.id.dot1);
		dots[1] = findViewById(R.id.dot2);
		dots[2] = findViewById(R.id.dot3);
		dots[3] = findViewById(R.id.dot4);
		img = findViewById(R.id.img);
		Text_title = findViewById(R.id.Text_title);
		Text_info = findViewById(R.id.Text_info);
		Text_next = findViewById(R.id.Text_next); 
		img_next = findViewById(R.id.img_next); 
		next_button = findViewById(R.id.next_button);
		back_button = findViewById(R.id.back_button); 
		
		mainLayout = findViewById(R.id.Background_intro_page);
		
		// Initialize currentIndex array
		currentIndex = new int[]{0};
		
		// Initialize lastGeneralClickTime
		lastGeneralClickTime = 0L;
		
		// Use Text Justify (requires import: android.text.Layout)
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
			Text_info.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD); 
		}
		
		// Enable long press prevention on buttons
		_blockLongPress(next_button);
		_blockLongPress(back_button);
		
		// Static content (texts adjusted to max 28 words)
		final int[] images = { R.drawable.welcome_pic, R.drawable.arningafiki, R.drawable.esearchapercuate, R.drawable.tutorialro };
		final String[] titles = { 
			"Heyy!!, Welcome to Reelix", 
			"Movies and TV shows for free", 
			"Huge movie database",
			"Comfortable video player" 
		};
		
		final String[] infos = { 
			// First text: Improved for more precision
			"Your one stop for streaming movies and TV shows, tap on next to view more.", 
			
			// Second text: More precise programming phrasing
			"Stream and even download any movie and TV shows episodes for free on Reelix without any charges.", 
			
			// Third text: Developer message in a respectful style appreciating the effort
			"Really hard amount of movie database, which enables users find almost any online available movies.",
			
			// Fourth text: Added to complete the count to 4 and avoid crashes
			"Super comfortable video player enabling users stream movies comfortably without any discomfort."
		};
		
		// Setup initial dots appearance
		for (int i = 0; i < dots.length; i++) {
			GradientDrawable gd = new GradientDrawable();
			gd.setShape(GradientDrawable.RECTANGLE);
			gd.setColor(i == 0 ? Color.parseColor("#B71C1C") : Color.parseColor("#E5F5F5"));
			gd.setCornerRadius(3 * getResources().getDisplayMetrics().density / 2f);
			dots[i].setBackground(gd);
			
			dots[i].getLayoutParams().width = (int) ((i == 0 ? 35 : 20) * getResources().getDisplayMetrics().density);
			dots[i].getLayoutParams().height = (int) (3 * getResources().getDisplayMetrics().density);
			dots[i].requestLayout();
		}
		
		img.setImageResource(images[0]);
		Text_title.setText(titles[0]);
		Text_info.setText(infos[0]);
		back_button.setVisibility(View.GONE);
		
		// Setup the Runnable
		runnable = new Runnable() {
			@Override
			public void run() {
				if (currentIndex[0] < dots.length - 1) {
					next_button.callOnClick();
					handler.postDelayed(this, 4000);
				}
			}
		};
		
		handler.postDelayed(runnable, 4000);
		
		// OnTouchListener to stop/start the Runnable when screen is touched
		mainLayout.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					handler.removeCallbacks(runnable);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					if (currentIndex[0] < dots.length - 1) {
						handler.postDelayed(runnable, 4000);
					}
				}
				return true;
			}
		});
		
		// Back button click (modified)
		back_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Prevent rapid clicking and double button press
				long clickTime = System.currentTimeMillis();
				if (clickTime - lastGeneralClickTime < 500) return;
				lastGeneralClickTime = clickTime;
				
				handler.removeCallbacks(runnable);
				handler.postDelayed(runnable, 4000);
				
				int nextIndex = currentIndex[0] - 1;
				if (nextIndex < 0) return;
				
				// Call dot update function
				_animateDotChange(dots[currentIndex[0]], dots[nextIndex]);
				
				final int finalIndex = nextIndex;
				final int imageDuration = 300;
				final int textDuration = 200;
				
				img.animate().translationX(img.getWidth()).alpha(0f).setDuration(imageDuration).start();
				Text_title.animate().translationX(Text_title.getWidth()).alpha(0f).setDuration(textDuration).start();
				Text_info.animate().translationX(Text_info.getWidth()).alpha(0f).setDuration(textDuration).withEndAction(new Runnable() {
					@Override
					public void run() {
						img.setImageResource(images[finalIndex]);
						Text_title.setText(titles[finalIndex]);
						Text_info.setText(infos[finalIndex]);
						
						img.setTranslationX(-img.getWidth());
						Text_title.setTranslationX(-Text_title.getWidth());
						Text_info.setTranslationX(-Text_info.getWidth());
						
						img.animate().translationX(0).alpha(1f).setDuration(imageDuration).start();
						Text_title.animate().translationX(0).alpha(1f).setDuration(textDuration).start();
						Text_info.animate().translationX(0).alpha(1f).setDuration(textDuration).start();
						
						_updateButtonState(finalIndex);
					}
				}).start();
				
				currentIndex[0] = nextIndex;
			}
		});
		
		// Next button click (modified)
		next_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Prevent rapid clicking and double button press
				long clickTime = System.currentTimeMillis();
				if (clickTime - lastGeneralClickTime < 500) return;
				lastGeneralClickTime = clickTime;
				
				if (currentIndex[0] == dots.length - 1) {
					handler.removeCallbacks(runnable);
					
					i.setClass(getApplicationContext(), AuthActivity.class);
					startActivity(i);
					overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					return;
				}
				
				handler.removeCallbacks(runnable);
				handler.postDelayed(runnable, 4000);
				
				int prevIndex = currentIndex[0] + 1;
				_animateDotChange(dots[currentIndex[0]], dots[prevIndex]);
				
				final int finalIndex = prevIndex;
				final int imageDuration = 300;
				final int textDuration = 200;
				
				img.animate().translationX(-img.getWidth()).alpha(0f).setDuration(imageDuration).start();
				Text_title.animate().translationX(-Text_title.getWidth()).alpha(0f).setDuration(textDuration).start();
				Text_info.animate().translationX(-Text_info.getWidth()).alpha(0f).setDuration(textDuration).withEndAction(new Runnable() {
					@Override
					public void run() {
						img.setImageResource(images[finalIndex]);
						Text_title.setText(titles[finalIndex]);
						Text_info.setText(infos[finalIndex]);
						
						_updateButtonState(finalIndex);
						
						img.setTranslationX(img.getWidth());
						Text_title.setTranslationX(Text_title.getWidth());
						Text_info.setTranslationX(Text_info.getWidth());
						
						img.animate().translationX(0).alpha(1f).setDuration(imageDuration).start();
						Text_title.animate().translationX(0).alpha(1f).setDuration(textDuration).start();
						Text_info.animate().translationX(0).alpha(1f).setDuration(textDuration).start();
					}
				}).start();
				
				currentIndex[0] = prevIndex;
				
				// Show skip button when moving from first page if not already visible
				if (currentIndex[0] == 1) { 
					_showButtons();
				}
			}
		});
		
		// =======================================================
		// ** Final centralized modification for controlling "jump" timing **
		// =======================================================
		
		final LinearLayout parentLayout = findViewById(R.id.Background_intro_page);
		android.animation.LayoutTransition layoutTransition = new android.animation.LayoutTransition();
		parentLayout.setLayoutTransition(layoutTransition);
		
		// Set movement duration in milliseconds (e.g., 300ms)
		long movementDurationMs = 300L; 
		
		// Keep "text jitter" fix active (disable general CHANGING animation)
		// This prevents conflict and solves the Jitter issue
		layoutTransition.setAnimator(android.animation.LayoutTransition.CHANGING, null); 
		layoutTransition.disableTransitionType(android.animation.LayoutTransition.CHANGING); 
		
		// Disable automatic animation for element appearance/disappearance (to avoid fading, etc.)
		// Using "null" ensures no animation, meeting "no fade, nothing" requirement
		layoutTransition.setAnimator(android.animation.LayoutTransition.APPEARING, null);
		layoutTransition.setAnimator(android.animation.LayoutTransition.DISAPPEARING, null);
		
		// Control only "smooth jump" timing (movement of surrounding elements)
		// This reactivates default smooth animation and controls its timing
		
		// Animate surrounding elements when new element appears
		layoutTransition.setDuration(android.animation.LayoutTransition.CHANGE_APPEARING, movementDurationMs);
		
		// Animate surrounding elements when element disappears
		layoutTransition.setDuration(android.animation.LayoutTransition.CHANGE_DISAPPEARING, movementDurationMs);
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		// 10. منع التسرب الذاكري (Prevent Memory Leak)
		// إزالة جميع المهام المؤجلة وغير المنفذة من الـ Handlers
		handler.removeCallbacksAndMessages(null); 
		skipButtonHandler.removeCallbacksAndMessages(null);
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
	
	
	public void _UI_Ripple(final View _view, final String _bg, final double _lt, final double _rt, final double _lb, final double _rb, final double _str, final String _str_color, final double _ele, final String _ripple) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_bg));
		gd.setStroke((int)_str, Color.parseColor(_str_color));
		gd.setCornerRadii(new float[] {(float)_lt, (float)_lt, (float)_rt, (float)_rt, (float)_rb, (float)_rb, (float)_lb, (float)_lb});
		_view.setElevation((int)_ele);
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_ripple)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
		_view.setBackground(ripdrb);
	}
	
	
	public void _Animator(final View _view, final String _propertyName, final double _value, final double _duration) {
		ObjectAnimator anim = new ObjectAnimator();
		anim.setTarget(_view);
		anim.setPropertyName(_propertyName);
		anim.setFloatValues((float)_value);
		anim.setDuration((long)_duration);
		anim.start();
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
	
	
	public void _animateDotChange(final View _currentDot, final View _nextDot) {
		// مدخلين: _currentDot و _nextDot
		
		GradientDrawable currentBg = (GradientDrawable) _currentDot.getBackground();
		GradientDrawable nextBg = (GradientDrawable) _nextDot.getBackground();
		
		// تغيير لون الخط الحالي إلى غير نشط
		currentBg.setColor(Color.parseColor("#F5F5F5"));
		
		// تغيير لون الخط التالي إلى نشط
		nextBg.setColor(Color.parseColor("#B71C1C"));
		
		// قيم البيكسل
		final int dpWidthNormal = (int) (20 * getResources().getDisplayMetrics().density);
		final int dpWidthActive = (int) (35 * getResources().getDisplayMetrics().density); // وسط بين 30 و40
		
		// انميشن تكبير العرض للخط النشط
		ValueAnimator animator = ValueAnimator.ofInt(dpWidthNormal, dpWidthActive);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				_nextDot.getLayoutParams().width = (int) valueAnimator.getAnimatedValue();
				_nextDot.requestLayout();
			}
		});
		animator.setDuration(250);
		animator.setInterpolator(new android.view.animation.OvershootInterpolator());
		animator.start();
		
		// ارجاع الخط السابق لحجمه الطبيعي
		_currentDot.getLayoutParams().width = dpWidthNormal;
		_currentDot.requestLayout();
	}
	
	
	public void _showButtons() {
		// ---------------------------------------------
		// إظهار الأزرار بالأنيميشن
		// --------------------------------------------
		// إظهار back_button بصعود سلس
		back_button.setVisibility(View.VISIBLE);
		back_button.setTranslationY(200); // يبدأ أسفل مكانه
		back_button.animate()
		.translationY(0)           // يصل لمكانه الطبيعي
		.setDuration(300)          // أبطأ من next_button
		.start();
		
		// إظهار next_button بصعود أسرع
		next_button.setVisibility(View.VISIBLE);
		next_button.setTranslationY(200); // يبدأ أسفل مكانه
		next_button.animate()
		.translationY(0)           // يصل لمكانه الطبيعي
		.setDuration(200)          // أسرع من back_button
		.start();
		
		
		// ---------------------------------------------
		// إخفاء الأزرار بالأنيميشن
		//
	}
	
	
	public void _hideButtons() {
		
		// إخفاء back_button بالهبوط أسرع
		back_button.animate()
		.translationY(200)         // ينزل أسفل الشاشة
		.setDuration(200)          // أسرع من next_button
		.withEndAction(new Runnable() {
			@Override
			public void run() {
				back_button.setVisibility(View.GONE);
			}
		}).start();
		
		// إخفاء next_button بالهبوط أبطأ
		next_button.animate()
		.translationY(200)         // ينزل أسفل الشاشة
		.setDuration(300)          // أبطأ من back_button
		.withEndAction(new Runnable() {
			@Override
			public void run() {
				next_button.setVisibility(View.GONE);
			}
		}).start();
	}
	
	
	public void _blockLongPress(final View _view) {
		// 9. منع الضغط المطول (Block Long Press)
		_view.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				isLongClick = true; // ضبط حالة الضغط المطول
				return true; // إرجاع true يستهلك الحدث ويمنع الاستجابة الافتراضية
			}
		});
	}
	
	
	public void _setButtonState() {
		// هذا هو الكود الصحيح لدالة _setButtonState() التي لا تستقبل مداخل
		
		// نستخدم المتغير العام currentIndex[0] للوصول إلى الفهرس
		if (currentIndex[0] == dots.length - 1) { 
			// الشريحة الأخيرة: إظهار "أبدأ الآن"
			Text_next.setText("Get Started!");
			handler.removeCallbacks(runnable);
			back_button.setVisibility(View.VISIBLE);
			
		} else if (currentIndex[0] == 0) { 
			// الشريحة الأولى: إظهار "التالي" وإخفاء زر الرجوع
			Text_next.setText("Next");
			img_next.setVisibility(View.VISIBLE);
			back_button.setVisibility(View.GONE);
		} else {
			// الشرائح الوسطى: إظهار "التالي" وكلا السهمين
			Text_next.setText("Next");
			back_button.setVisibility(View.VISIBLE);
		}
	}
	
	
	public void _updateButtonState(final double _index) {
		// هذا هو الكود النهائي للدالة _updateButtonState() (بدون مدخلات)
		
		// 1. تحديد الحالة الحالية والسابقة للنص (لتقرير ما إذا كان يجب تشغيل الأنيميشن)
		final boolean isFinalState = (currentIndex[0] == dots.length - 1);
		final boolean wasFinalState = (Text_next.getText().toString().equals("Get Started!"));
		
		// 2. تحديد ما إذا كنا نعود من الشريحة الأخيرة (لتطبيق الحركة المعكوسة)
		final boolean isReturning = (wasFinalState && !isFinalState);
		
		// 3. الشرط: الأنيميشن يحدث فقط إذا كانت حالة "أبدأ الآن!" تتغير (من/إلى).
		if (isFinalState != wasFinalState) { 
			
			final long ANIM_DURATION = 100; 
			
			// 👈 تحديد اتجاه الانزلاق (Slide)
			// إذا كنا عائدين: الإخفاء للأسفل (Hide Down) والظهور من الأعلى (Show from Top)
			// إذا كنا نتقدم: الإخفاء للأعلى (Hide Up) والظهور من الأسفل (Show from Bottom)
			final float hideTranslation = isReturning ? Text_next.getHeight() : -Text_next.getHeight();
			final float showTranslation = isReturning ? -Text_next.getHeight() : Text_next.getHeight();
			
			// أ. إخفاء النص والصورة بالانزلاق والتلاشي (Slide & Fade Out)
			Text_next.animate()
			.translationY(hideTranslation)
			.alpha(0f)
			.setDuration(ANIM_DURATION)
			.start(); 
			
			img_next.animate()
			.translationY(hideTranslation)
			.alpha(0f)
			.setDuration(ANIM_DURATION)
			.withEndAction(new Runnable() {
				@Override
				public void run() {
					// ب. تحديث المحتوى (النص والصورة)
					_setButtonState(); 
					
					// 🚨 تغيير الأيقونة
					if (isFinalState) {
						img_next.setImageResource(R.drawable.img); 
					} else {
						img_next.setImageResource(R.drawable.next); 
					}
					
					// ج. تهيئة النص والصورة الجديدين للموقع المعاكس
					Text_next.setTranslationY(showTranslation);
					img_next.setTranslationY(showTranslation); 
					
					// د. إظهار النص الجديد والصورة بالانزلاق والعودة للموقع الأصلي (Slide & Fade In)
					Text_next.animate()
					.translationY(0)
					.alpha(1f)
					.setDuration(ANIM_DURATION)
					.start();
					
					img_next.animate() 
					.translationY(0)
					.alpha(1f)
					.setDuration(ANIM_DURATION)
					.start();
				}
			}).start();
			
		} else {
			// 👈 إذا كنا نتحرك بين الشرائح الوسطى، نكتفي بالتحديث المباشر
			_setButtonState(); 
			
			// إعادة ضبط الأنماط لضمان الظهور الصحيح
			Text_next.setAlpha(1.0f); 
			Text_next.setTranslationY(0); 
			img_next.setAlpha(1.0f); 
			img_next.setTranslationY(0); 
		}
	}
	
}