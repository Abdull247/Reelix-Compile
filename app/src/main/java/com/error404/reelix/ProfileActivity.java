package com.error404.reelix;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.*;
import com.google.android.material.button.*;
import com.google.android.material.card.*;
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
import de.hdodenhof.circleimageview.*;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import com.google.gson.reflect.TypeToken;


public class ProfileActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> watch_history_map = new ArrayList<>();
	
	private LinearLayout linear1;
	private RelativeLayout back_relative;
	private NestedScrollView nestedScrollView1;
	private NotchedBottomNav notchedBottomNav;
	private LinearLayout mainback;
	private LinearLayout linear3;
	private LinearLayout linear11;
	private LinearLayout pro_info_back;
	private LinearLayout linear12;
	private LinearLayout first_content_holder;
	private LinearLayout linear15;
	private LinearLayout second_content_holder;
	private LinearLayout linear20;
	private LinearLayout third_content_holder;
	private LinearLayout linear27;
	private TextView textview9;
	private LinearLayout contact_me_section_holder;
	private LinearLayout linear31;
	private LinearLayout linear34;
	private LinearLayout linear35;
	private LinearLayout linear36;
	private LinearLayout linear37;
	private ImageView imageview2;
	private TextView app_name;
	private ImageView imageview1;
	private LinearLayout profile_pic_holder;
	private LinearLayout linear8;
	private CircleImageView circleimageview1;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private TextView name;
	private ImageView imageview3;
	private TextView sus_type_main;
	private TextView sus_type_content;
	private LinearLayout inner_first_content;
	private LinearLayout linear16;
	private LinearLayout linear17;
	private LinearLayout linear18;
	private ImageView imageview10;
	private TextView free_premium_title;
	private TextView free_premium_desc;
	private ImageView imageview12;
	private LinearLayout inner_second_content;
	private LinearLayout wachlist_linear;
	private LinearLayout notifications_linear;
	private LinearLayout watch_history_mainholder_linear;
	private LinearLayout linear19;
	private ImageView imageview4;
	private TextView textview1;
	private ImageView imageview5;
	private ImageView imageview6;
	private TextView textview2;
	private ImageView imageview7;
	private LinearLayout inner_watch_history_linear;
	private LinearLayout inner_watch_history_linear2;
	private ImageView imageview8;
	private TextView textview3;
	private ImageView imageview9;
	private RecyclerView watch_history_rec;
	private ImageView imageview13;
	private TextView textview4;
	private ImageView imageview14;
	private LinearLayout inner_third_content;
	private LinearLayout linear24;
	private LinearLayout linear25;
	private LinearLayout linear26;
	private TextView textview6;
	private ImageView imageview18;
	private TextView textview7;
	private ImageView imageview19;
	private TextView textview8;
	private ImageView imageview20;
	private MaterialCardView materialCardView1;
	private MaterialCardView cardview1;
	private MaterialCardView cardview2;
	private LinearLayout linear28;
	private ImageView imageview21;
	private LinearLayout linear29;
	private ImageView imageview22;
	private LinearLayout linear30;
	private ImageView imageview23;
	private TextView textview10;
	private TextView version_txt;
	private TextView disclaimer_txt;
	private MaterialButton materialbutton1;
	private LinearLayout main_back;
	private LinearLayout other_back;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private ImageView imageview24;
	private ImageView imageview25;
	private ImageView imageview26;
	private ImageView imageview27;
	
	private Intent intent = new Intent();
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
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private SharedPreferences history_pref;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.profile);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		back_relative = findViewById(R.id.back_relative);
		nestedScrollView1 = findViewById(R.id.nestedScrollView1);
		notchedBottomNav = findViewById(R.id.notchedBottomNav);
		mainback = findViewById(R.id.mainback);
		linear3 = findViewById(R.id.linear3);
		linear11 = findViewById(R.id.linear11);
		pro_info_back = findViewById(R.id.pro_info_back);
		linear12 = findViewById(R.id.linear12);
		first_content_holder = findViewById(R.id.first_content_holder);
		linear15 = findViewById(R.id.linear15);
		second_content_holder = findViewById(R.id.second_content_holder);
		linear20 = findViewById(R.id.linear20);
		third_content_holder = findViewById(R.id.third_content_holder);
		linear27 = findViewById(R.id.linear27);
		textview9 = findViewById(R.id.textview9);
		contact_me_section_holder = findViewById(R.id.contact_me_section_holder);
		linear31 = findViewById(R.id.linear31);
		linear34 = findViewById(R.id.linear34);
		linear35 = findViewById(R.id.linear35);
		linear36 = findViewById(R.id.linear36);
		linear37 = findViewById(R.id.linear37);
		imageview2 = findViewById(R.id.imageview2);
		app_name = findViewById(R.id.app_name);
		imageview1 = findViewById(R.id.imageview1);
		profile_pic_holder = findViewById(R.id.profile_pic_holder);
		linear8 = findViewById(R.id.linear8);
		circleimageview1 = findViewById(R.id.circleimageview1);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		name = findViewById(R.id.name);
		imageview3 = findViewById(R.id.imageview3);
		sus_type_main = findViewById(R.id.sus_type_main);
		sus_type_content = findViewById(R.id.sus_type_content);
		inner_first_content = findViewById(R.id.inner_first_content);
		linear16 = findViewById(R.id.linear16);
		linear17 = findViewById(R.id.linear17);
		linear18 = findViewById(R.id.linear18);
		imageview10 = findViewById(R.id.imageview10);
		free_premium_title = findViewById(R.id.free_premium_title);
		free_premium_desc = findViewById(R.id.free_premium_desc);
		imageview12 = findViewById(R.id.imageview12);
		inner_second_content = findViewById(R.id.inner_second_content);
		wachlist_linear = findViewById(R.id.wachlist_linear);
		notifications_linear = findViewById(R.id.notifications_linear);
		watch_history_mainholder_linear = findViewById(R.id.watch_history_mainholder_linear);
		linear19 = findViewById(R.id.linear19);
		imageview4 = findViewById(R.id.imageview4);
		textview1 = findViewById(R.id.textview1);
		imageview5 = findViewById(R.id.imageview5);
		imageview6 = findViewById(R.id.imageview6);
		textview2 = findViewById(R.id.textview2);
		imageview7 = findViewById(R.id.imageview7);
		inner_watch_history_linear = findViewById(R.id.inner_watch_history_linear);
		inner_watch_history_linear2 = findViewById(R.id.inner_watch_history_linear2);
		imageview8 = findViewById(R.id.imageview8);
		textview3 = findViewById(R.id.textview3);
		imageview9 = findViewById(R.id.imageview9);
		watch_history_rec = findViewById(R.id.watch_history_rec);
		imageview13 = findViewById(R.id.imageview13);
		textview4 = findViewById(R.id.textview4);
		imageview14 = findViewById(R.id.imageview14);
		inner_third_content = findViewById(R.id.inner_third_content);
		linear24 = findViewById(R.id.linear24);
		linear25 = findViewById(R.id.linear25);
		linear26 = findViewById(R.id.linear26);
		textview6 = findViewById(R.id.textview6);
		imageview18 = findViewById(R.id.imageview18);
		textview7 = findViewById(R.id.textview7);
		imageview19 = findViewById(R.id.imageview19);
		textview8 = findViewById(R.id.textview8);
		imageview20 = findViewById(R.id.imageview20);
		materialCardView1 = findViewById(R.id.materialCardView1);
		cardview1 = findViewById(R.id.cardview1);
		cardview2 = findViewById(R.id.cardview2);
		linear28 = findViewById(R.id.linear28);
		imageview21 = findViewById(R.id.imageview21);
		linear29 = findViewById(R.id.linear29);
		imageview22 = findViewById(R.id.imageview22);
		linear30 = findViewById(R.id.linear30);
		imageview23 = findViewById(R.id.imageview23);
		textview10 = findViewById(R.id.textview10);
		version_txt = findViewById(R.id.version_txt);
		disclaimer_txt = findViewById(R.id.disclaimer_txt);
		materialbutton1 = findViewById(R.id.materialbutton1);
		main_back = findViewById(R.id.main_back);
		other_back = findViewById(R.id.other_back);
		linear2 = findViewById(R.id.linear2);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		imageview24 = findViewById(R.id.imageview24);
		imageview25 = findViewById(R.id.imageview25);
		imageview26 = findViewById(R.id.imageview26);
		imageview27 = findViewById(R.id.imageview27);
		auth = FirebaseAuth.getInstance();
		net = new RequestNetwork(this);
		history_pref = getSharedPreferences("history_pref", Activity.MODE_PRIVATE);
		
		pro_info_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), UpdateProfileActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		
		inner_watch_history_linear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), WatchHistoryListActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
			}
		});
		
		linear24.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String url = "https://error404portfolio.vercel.app/";
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(intent);
			}
		});
		
		materialCardView1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String url = "https://reelix-movies-app.vercel.app//";
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(intent);
			}
		});
		
		cardview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String email = "momohabdullahi14@gmail.com";
				Intent intent = new Intent(Intent.ACTION_SENDTO);
				intent.setData(Uri.parse("mailto:" + email));
				startActivity(intent);
			}
		});
		
		cardview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String url = "https://t.me/Reelix_Streaming";
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(intent);
			}
		});
		
		materialbutton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (SketchwareUtil.isConnected(getApplicationContext())) {
					intent.setClass(getApplicationContext(), WelcomeActivity.class);
					startActivity(intent);
					overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
					FirebaseAuth.getInstance().signOut();
				}
			}
		});
		
		linear2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(imageview24);
				intent.setClass(getApplicationContext(), FeedActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		
		linear4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(imageview25);
				intent.setClass(getApplicationContext(), SearchActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		
		linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(imageview26);
				
				intent.setClass(getApplicationContext(), DownloadsActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				
				// Apply smooth custom transition
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			final Window window = ProfileActivity.this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(0xFF0B0D0F);
		}
		notchedBottomNav.setTopCornerRadius(13 * getResources().getDisplayMetrics().density);
		notchedBottomNav.setBottomCornerRadius(20 * getResources().getDisplayMetrics().density);
		
		float nav_density = getApplicationContext().getResources().getDisplayMetrics().density;
		
		notchedBottomNav.setNotchRadius(36 * nav_density);
		notchedBottomNav.setNotchDepth(28 * nav_density);
		notchedBottomNav.setCurveSpanMultiplier(2.0f);
		notchedBottomNav.setNotchGeometry(40 * nav_density, 24 * nav_density);
		notchedBottomNav.setTopCornerRadius(16 * nav_density);
		notchedBottomNav.setBottomCornerRadius(20 * nav_density);
		notchedBottomNav.setBarColors(0xFF2A1618, 0xFF1F2024);
		app_name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sans.ttf"), 1);
		name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/visions.ttf"), 1);
		sus_type_main.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/visions.ttf"), 0);
		sus_type_content.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ints.ttf"), 0);
		free_premium_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sans.ttf"), 0);
		free_premium_desc.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ints.ttf"), 0);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ints.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ints.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ints.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ints.ttf"), 0);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ints.ttf"), 0);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ints.ttf"), 0);
		textview8.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ints.ttf"), 0);
		textview9.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google.ttf"), 0);
		textview10.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sans.ttf"), 1);
		version_txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/appfont.ttf"), 0);
		disclaimer_txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google.ttf"), 0);
		materialbutton1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 0);
		inner_first_content.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)12, 0xFF0E1419));
		inner_second_content.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)12, 0xFF0E1419));
		inner_third_content.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)12, 0xFF0E1419));
		imageview27.setColorFilter(0xFFCE0002, PorterDuff.Mode.MULTIPLY);
		_firebaseUpdate();
		try {
			watch_history_map.clear();
			String historyJson = history_pref.getString("watch_history", "");
			
			if (!historyJson.isEmpty()) {
				watch_history_map = new com.google.gson.Gson().fromJson(
				historyJson, 
				new com.google.gson.reflect.TypeToken<java.util.ArrayList<java.util.HashMap<String, Object>>>(){}.getType()
				);
				
				// Bind the adapter and layout manager so the list items display
				watch_history_rec.setAdapter(new Watch_history_recAdapter(watch_history_map));
				watch_history_rec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
				watch_history_rec.getAdapter().notifyDataSetChanged();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// --- BUTTON 5 LOGIC ---
		final float density = getResources().getDisplayMetrics().density;
		final float defaultRadius = 28f * density;  // Circular shape
		final float pressedRadius = 8f * density;   // Squircle/square shape
		
		com.google.android.material.button.MaterialButton button5 = findViewById(R.id.materialbutton1);
		
		if (button5 != null) {
			// Set initial circular shape
			button5.setShapeAppearanceModel(button5.getShapeAppearanceModel().toBuilder()
			.setAllCornerSizes(defaultRadius)
			.build());
			
			// Add touch listener for morphing animation
			button5.setOnTouchListener(new android.view.View.OnTouchListener() {
				private android.animation.ValueAnimator animator;
				
				@Override
				public boolean onTouch(android.view.View v, android.view.MotionEvent event) {
					
					// Prevent parent from intercepting touch
					if (v.getParent() != null) {
						v.getParent().requestDisallowInterceptTouchEvent(true);
					}
					
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
						// Press: quick morph to squircle (100ms)
						startAnim(pressedRadius, 100, new android.view.animation.DecelerateInterpolator());
					} 
					else if (event.getAction() == android.view.MotionEvent.ACTION_UP || 
					event.getAction() == android.view.MotionEvent.ACTION_CANCEL) {
						// Release: smooth morph back to circle (300ms)
						startAnim(defaultRadius, 300, new android.view.animation.AccelerateDecelerateInterpolator());
					}
					
					return false; // Allow OnClickListener to work
				}
				
				private void startAnim(float target, int duration, android.view.animation.Interpolator interpolator) {
					if (animator != null && animator.isRunning()) {
						animator.cancel();
					}
					
					android.graphics.RectF rect = new android.graphics.RectF(0, 0, button5.getWidth(), button5.getHeight());
					float startVal = button5.getShapeAppearanceModel().getBottomLeftCornerSize().getCornerSize(rect);
					
					animator = android.animation.ValueAnimator.ofFloat(startVal, target);
					animator.setDuration(duration);
					animator.setInterpolator(interpolator);
					
					animator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {
						@Override
						public void onAnimationUpdate(android.animation.ValueAnimator animation) {
							float value = (float) animation.getAnimatedValue();
							button5.setShapeAppearanceModel(button5.getShapeAppearanceModel().toBuilder()
							.setAllCornerSizes(value)
							.build());
						}
					});
					animator.start();
				}
			});
		}
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
		
	} // Closes initializeLogic early
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		overridePendingTransition(com.error404.reelix.R.anim.fade_in, com.error404.reelix.R.anim.fade_out);
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		_firebaseUpdate();
		try {
			watch_history_map.clear();
			String historyJson = history_pref.getString("watch_history", "");
			
			if (!historyJson.isEmpty()) {
				watch_history_map = new com.google.gson.Gson().fromJson(
				historyJson, 
				new com.google.gson.reflect.TypeToken<java.util.ArrayList<java.util.HashMap<String, Object>>>(){}.getType()
				);
				
				// Bind the adapter and layout manager so the list items display
				watch_history_rec.setAdapter(new Watch_history_recAdapter(watch_history_map));
				watch_history_rec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
				watch_history_rec.getAdapter().notifyDataSetChanged();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void _clickAnimation(final View _view) {
		ScaleAnimation fade_in = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
		fade_in.setDuration(300);
		fade_in.setFillAfter(true);
		_view.startAnimation(fade_in);
	}
	
	
	public void _firebaseUpdate() {
		// 1. Check if the user is authenticated
		if (FirebaseAuth.getInstance().getCurrentUser() != null) {
			// Get the current user's unique ID
			String currentUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
			
			// 2. Query the "users" node for username + profile_pic
			FirebaseDatabase.getInstance().getReference("users")
			.child(currentUid)
			.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(@NonNull DataSnapshot snapshot) {
					// Username
					if (snapshot.exists() && snapshot.hasChild("username")) {
						String username = snapshot.child("username").getValue(String.class);
						name.setText(username);
					} else {
						name.setText("User");
					}
					
					// Profile picture
					if (snapshot.exists() && snapshot.hasChild("profile_pic")) {
						String profilePic = snapshot.child("profile_pic").getValue(String.class);
						if (profilePic != null && !profilePic.isEmpty()) {
							Glide.with(getApplicationContext()).load(Uri.parse(profilePic)).into(circleimageview1);
						} else {
							circleimageview1.setImageResource(R.drawable.user);
						}
					} else {
						circleimageview1.setImageResource(R.drawable.user);
					}
				}
				
				@Override
				public void onCancelled(@NonNull DatabaseError error) {
					name.setText("User");
					circleimageview1.setImageResource(R.drawable.user);
					SketchwareUtil.showMessage(getApplicationContext(), "Database Error: " + error.getMessage());
				}
			});
			
			// 3. Query the "plan" node for subscription type
			FirebaseDatabase.getInstance().getReference("plan")
			.child(currentUid)
			.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(@NonNull DataSnapshot snapshot) {
					if (snapshot.exists() && snapshot.hasChild("plan")) {
						String planType = snapshot.child("plan").getValue(String.class);
						sus_type_content.setText(planType);
					} else {
						sus_type_content.setText("Free");
					}
				}
				
				@Override
				public void onCancelled(@NonNull DatabaseError error) {
					sus_type_content.setText("Free");
					SketchwareUtil.showMessage(getApplicationContext(), "Database Error: " + error.getMessage());
				}
			});
		} else {
			/*
    intent.setClass(getApplicationContext(), LoginActivity.class);
    startActivity(intent);
    finish();
    */
			name.setText("Guest");
			circleimageview1.setImageResource(R.drawable.user);
			sus_type_content.setText("Free");
		}
	}
	
	public class Watch_history_recAdapter extends RecyclerView.Adapter<Watch_history_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Watch_history_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.vid_watch_history, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout mainback = _view.findViewById(R.id.mainback);
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final TextView title = _view.findViewById(R.id.title);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout relative_overlay = _view.findViewById(R.id.relative_overlay);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final ImageView type_img = _view.findViewById(R.id.type_img);
			final ProgressBar progressbar1 = _view.findViewById(R.id.progressbar1);
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/visions.ttf"), 0);
			if (watch_history_map == null || _position >= watch_history_map.size()) return;
			
			try {
				java.util.HashMap<String, Object> map = watch_history_map.get((int)_position);
				
				// 1. Text Truncation Properties
				title.setSingleLine(true);
				title.setMaxLines(1);
				title.setEllipsize(android.text.TextUtils.TruncateAt.END);
				title.setHorizontallyScrolling(false);
				
				// 2. Bind Text and Images
				title.setText(String.valueOf(map.get("title")));
				
				String coverUrl = map.containsKey("cover_url") ? String.valueOf(map.get("cover_url")) : "";
				if (coverUrl != null && !coverUrl.trim().isEmpty()) {
					com.bumptech.glide.Glide.with(_view.getContext())
					.load(coverUrl)
					.placeholder(R.drawable.shadow)
					.error(R.drawable.shadow)
					.into(imageview1);
				} else {
					imageview1.setImageResource(R.drawable.shadow);
				}
				
				// 3. Bind Media Type Indicators
				String itemType = map.containsKey("type") ? String.valueOf(map.get("type")) : "movie";
				if (itemType.equals("tv")) {
					type_img.setImageResource(R.drawable.icon_live_tv_round);
				} else {
					type_img.setImageResource(R.drawable.icon_movie_round);
				}
				
				// 4. Bind Progress to ProgressBar1
				if (map.containsKey("progress")) {
					int progressValue = ((Double) Double.parseDouble(String.valueOf(map.get("progress")))).intValue();
					
					progressbar1.setMax(100);
					progressbar1.setProgress(progressValue);
					progressbar1.setVisibility(android.view.View.VISIBLE);
				} else {
					progressbar1.setProgress(0);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			mainback.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					try {
						java.util.HashMap<String, Object> nativeMap = watch_history_map.get((int)_position);
						String videoTitle = String.valueOf(nativeMap.get("title"));
						String videoPath = nativeMap.containsKey("video_path") ? String.valueOf(nativeMap.get("video_path")) : "";
						
						if (!videoPath.isEmpty()) {
							android.content.Intent customPlayerIntent = new android.content.Intent();
							customPlayerIntent.setClass(_view.getContext().getApplicationContext(), PlayerActivity.class);
							customPlayerIntent.putExtra("video_path", videoPath);
							customPlayerIntent.putExtra("video_title", videoTitle);
							
							_view.getContext().startActivity(customPlayerIntent);
							((android.app.Activity)_view.getContext()).overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
						} else {
							android.widget.Toast.makeText(_view.getContext(), "Video path missing from history database", android.widget.Toast.LENGTH_SHORT).show();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
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