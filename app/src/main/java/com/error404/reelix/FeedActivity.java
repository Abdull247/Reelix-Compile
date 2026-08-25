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
import androidx.cursoradapter.*;
import androidx.customview.*;
import androidx.documentfile.*;
import androidx.drawerlayout.*;
import androidx.exifinterface.*;
import androidx.fragment.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
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
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;


public class FeedActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String fontName = "";
	private String typeace = "";
	private ScrollBlurHelper scrollBlurHelper;
	
	private ArrayList<HashMap<String, Object>> slide_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> popular_shows_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> trending_now = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> upcoming_map = new ArrayList<>();
	
	private LinearLayout main;
	private RelativeLayout parent_main_back_relative;
	private RelativeLayout main_relative;
	private BlurView top_bar;
	private LinearLayout inner_scroll_container;
	private NotchedBottomNav notchedBottomNav;
	private LinearLayout viewPagerHolder;
	private ViewPager viewpager1;
	private LinearLayout main_back;
	private LinearLayout other_back;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private ImageView imageview1;
	private ImageView imageview2;
	private ImageView imageview3;
	private ImageView imageview4;
	private LinearLayout inner_top_linear;
	private LinearLayout top_inner_text;
	private LinearLayout scroller_back;
	private ImageView app_top_img;
	private TextView walcome_user_text;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear3;
	private TextView item_tab_txt1;
	private TextView item_tab_txt2;
	private TextView item_tab_txt3;
	private TextView item_tab_txt4;
	
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
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private Intent intent = new Intent();
	private Frag_adapterFragmentAdapter frag_adapter;
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.feed);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main = findViewById(R.id.main);
		parent_main_back_relative = findViewById(R.id.parent_main_back_relative);
		main_relative = findViewById(R.id.main_relative);
		top_bar = findViewById(R.id.top_bar);
		inner_scroll_container = findViewById(R.id.inner_scroll_container);
		notchedBottomNav = findViewById(R.id.notchedBottomNav);
		viewPagerHolder = findViewById(R.id.viewPagerHolder);
		viewpager1 = findViewById(R.id.viewpager1);
		main_back = findViewById(R.id.main_back);
		other_back = findViewById(R.id.other_back);
		linear2 = findViewById(R.id.linear2);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		imageview1 = findViewById(R.id.imageview1);
		imageview2 = findViewById(R.id.imageview2);
		imageview3 = findViewById(R.id.imageview3);
		imageview4 = findViewById(R.id.imageview4);
		inner_top_linear = findViewById(R.id.inner_top_linear);
		top_inner_text = findViewById(R.id.top_inner_text);
		scroller_back = findViewById(R.id.scroller_back);
		app_top_img = findViewById(R.id.app_top_img);
		walcome_user_text = findViewById(R.id.walcome_user_text);
		hscroll1 = findViewById(R.id.hscroll1);
		linear3 = findViewById(R.id.linear3);
		item_tab_txt1 = findViewById(R.id.item_tab_txt1);
		item_tab_txt2 = findViewById(R.id.item_tab_txt2);
		item_tab_txt3 = findViewById(R.id.item_tab_txt3);
		item_tab_txt4 = findViewById(R.id.item_tab_txt4);
		auth = FirebaseAuth.getInstance();
		frag_adapter = new Frag_adapterFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
		net = new RequestNetwork(this);
		
		viewpager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageSelected(int _position) {
				_selectTab(_position);
			}
			
			@Override
			public void onPageScrollStateChanged(int _scrollState) {
				
			}
		});
		
		linear4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(imageview2);
				intent.setClass(getApplicationContext(), SearchActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				
				// Apply smooth custom transition
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(imageview3);
				intent.setClass(getApplicationContext(), DownloadsActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				
				// Apply smooth custom transition
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		
		linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(imageview4);
				
				intent.setClass(getApplicationContext(), ProfileActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				
				// Apply smooth custom transition
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		users.addChildEventListener(_users_child_listener);
		
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
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			final Window window = FeedActivity.this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.getDecorView().setSystemUiVisibility(
			android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
			| android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
			);
			window.setStatusBarColor(Color.TRANSPARENT);
		}
		
		_changeActivityFont("ooo");
		
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
		scrollBlurHelper = new ScrollBlurHelper();
		scrollBlurHelper.attachBlurView(this, top_bar);
		top_bar.setOverlayColor(0x000B0D0F);
		_selectTab(0);
		
		item_tab_txt1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)0);
				_selectTab(0);
			}
		});
		
		item_tab_txt2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)1);
				_selectTab(1);
			}
		});
		
		item_tab_txt3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)2);
				_selectTab(2);
			}
		});
		
		item_tab_txt4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)3);
				_selectTab(3);
			}
		});
		hscroll1.setHorizontalScrollBarEnabled(false);
		hscroll1.setVerticalScrollBarEnabled(false);
		hscroll1.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
		try{
			viewpager1.setOffscreenPageLimit((int)4);
			frag_adapter.setTabCount(4);
			viewpager1.setAdapter(frag_adapter);
		}catch(Exception e){
			
		}
		if (!SketchwareUtil.isConnected(getApplicationContext())) {
			com.google.android.material.snackbar.Snackbar.make(main, "Connect to the Internet to load movie data.", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener(){
				@Override
				public void onClick(View _view) {
					
				}
			}).show();
		}
		_getFirebaseDetails();
	} // Closes initializeLogic early
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		overridePendingTransition(com.error404.reelix.R.anim.fade_in, com.error404.reelix.R.anim.fade_out);
		
	}
	
	public class Frag_adapterFragmentAdapter extends FragmentStatePagerAdapter {
		// This class is deprecated, you should migrate to ViewPager2:
		// https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2
		Context context;
		int tabCount;
		
		public Frag_adapterFragmentAdapter(Context context, FragmentManager manager) {
			super(manager);
			this.context = context;
		}
		
		public void setTabCount(int tabCount) {
			this.tabCount = tabCount;
		}
		
		@Override
		public int getCount() {
			return tabCount;
		}
		
		@Override
		public CharSequence getPageTitle(int _position) {
			return "";
		}
		
		
		@Override
		public Fragment getItem(int _position) {
			if (_position == 0) {
				return new FeedHomeFragmentActivity();
			}
			if (_position == 1) {
				return new FeedTvshowsFragmentActivity();
			}
			if (_position == 2) {
				return new FeedCartoonsFragmentActivity();
			}
			if (_position == 3) {
				return new FeedActionFragmentActivity();
			}
			return new Fragment();
		}
		
	}
	
	
	@Override
	public void onStart() {
		super.onStart();
		if (!SketchwareUtil.isConnected(getApplicationContext())) {
			com.google.android.material.snackbar.Snackbar.make(main, "Connect to the Internet to load movie data.", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener(){
				@Override
				public void onClick(View _view) {
					
				}
			}).show();
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		_getFirebaseDetails();
		if (!SketchwareUtil.isConnected(getApplicationContext())) {
			com.google.android.material.snackbar.Snackbar.make(main, "Connect to the Internet to load movie data.", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener(){
				@Override
				public void onClick(View _view) {
					
				}
			}).show();
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
	
	
	public void _ImgRound(final ImageView _imageview, final double _value) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable ();
		gd.setColor(android.R.color.transparent);
		gd.setCornerRadius((int)_value);
		_imageview.setClipToOutline(true);
		_imageview.setBackground(gd);
	}
	
	
	public void _getFirebaseDetails() {
		
	}
	
	
	public void _clickAnimation(final View _view) {
		ScaleAnimation fade_in = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
		fade_in.setDuration(300);
		fade_in.setFillAfter(true);
		_view.startAnimation(fade_in);
	}
	
	
	public void _anchore() {
		
	}
    
    public ScrollBlurHelper getScrollBlurHelper() {
    return scrollBlurHelper;
}

public void _selectTab(int _selectedIndex) {
    TextView[] tabs = new TextView[]{item_tab_txt1, item_tab_txt2, item_tab_txt3, item_tab_txt4};

    for (int i = 0; i < tabs.length; i++) {
        if (i == _selectedIndex) {
            tabs[i].setTypeface(Typeface.createFromAsset(getAssets(), "fonts/sans.ttf"), 1);
            tabs[i].setTextColor(0xFFFFFFFF);
        } else {
            tabs[i].setTypeface(Typeface.createFromAsset(getAssets(), "fonts/sans.ttf"), 0);
            tabs[i].setTextColor(0xFFA4A4A5);
        }
    }
}
	
}