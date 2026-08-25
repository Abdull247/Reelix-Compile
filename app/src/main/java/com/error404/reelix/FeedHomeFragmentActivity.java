package com.error404.reelix;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
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
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.budiyev.android.codescanner.*;
import com.bumptech.glide.*;
import com.bumptech.glide.gifdecoder.*;
import com.facebook.shimmer.*;
import com.facebook.shimmer.ShimmerFrameLayout;
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
import eightbitlab.com.blurview.*;
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
import com.bumptech.glide.Glide;
import androidx.recyclerview.widget.GridLayoutManager;
import com.google.firebase.auth.GetTokenResult;
import android.content.ClipboardManager;
import android.content.ClipData;

public class FeedHomeFragmentActivity extends Fragment {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String currentUserId = "";
	private ConnectivityManager.NetworkCallback networkCallback;
	private HashMap<String, ArrayList<HashMap<String, Object>>> hm_sec3_genre_items_map = new HashMap<>();
	private String selectedGenreKey = "";
	private HashMap<String, ArrayList<HashMap<String, Object>>> hm_sec4_genre_items_map = new HashMap<>();
	private String selectedGenreKeySec4 = "";
	
	private ArrayList<HashMap<String, Object>> slide_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> hm_sec1_data_lsmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> hm_sec2_data_lsmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> hm_sec5_data_lsmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> hm_sec3_genre_lsmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> hm_sec3_data_lsmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> hm_sec4_genre_lsmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> hm_sec4_data_lsmap = new ArrayList<>();
	
	private LinearLayout frag_main;
	private SwipeRefreshLayout swiperefreshlayout1;
	private NestedScrollView nestedScrollView1;
	private LinearLayout inner_scroll_container;
	private ViewPager viewpager1;
	private ShimmerFrameLayout banner_shimmer;
	private MaterialCardView no_internet_holder;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear7;
	private RelativeLayout hm_sec1_relative;
	private LinearLayout linear9;
	private RelativeLayout hm_sec2_relative;
	private LinearLayout linear11;
	private RecyclerView hm_sec3_data_choices_rec;
	private RelativeLayout hm_sec3_relative;
	private LinearLayout linear15;
	private RecyclerView hm_sec4_data_choices_rec;
	private RelativeLayout hm_sec4_relative;
	private LinearLayout linear16;
	private RelativeLayout hm_sec5_relative;
	private LinearLayout linear17;
	private LinearLayout linear18;
	private LinearLayout linear19;
	private ImageView imageview1;
	private TextView textview18;
	private MaterialButton materialbutton1;
	private TextView textview6;
	private TextView textview7;
	private HorizontalScrollView hscroll2;
	private LinearLayout linear6;
	private LinearLayout cat_chip1;
	private LinearLayout cat_chip2;
	private LinearLayout cat_chip3;
	private LinearLayout cat_chip4;
	private LinearLayout cat_chip5;
	private LinearLayout cat_chip6;
	private LinearLayout cat_chip7;
	private TextView cat_txt1;
	private TextView cat_txt2;
	private TextView cat_txt3;
	private TextView cat_txt4;
	private TextView cat_txt5;
	private TextView cat_txt6;
	private TextView cat_txt7;
	private TextView textview8;
	private TextView textview9;
	private RecyclerView hm_sec1_loading_rec;
	private RecyclerView hm_sec1_data_rec;
	private TextView textview10;
	private RecyclerView hm_sec2_loading_rec;
	private RecyclerView hm_sec2_data_rec;
	private TextView textview12;
	private TextView textview13;
	private RecyclerView hm_sec3_loading_rec;
	private RecyclerView hm_sec3_data_rec;
	private TextView textview14;
	private TextView textview15;
	private RecyclerView hm_sec4_loading_rec;
	private RecyclerView hm_sec4_data_rec;
	private TextView textview16;
	private TextView textview17;
	private RecyclerView hm_sec5_loading_rec;
	private RecyclerView hm_sec5_data_rec;
	
	private TimerTask timer;
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
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private RequestNetworkV2 request_home;
private RequestNetworkV2.RequestListener _request_home_request_listener;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.feed_home_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		frag_main = _view.findViewById(R.id.frag_main);
		swiperefreshlayout1 = _view.findViewById(R.id.swiperefreshlayout1);
		nestedScrollView1 = _view.findViewById(R.id.nestedScrollView1);
		inner_scroll_container = _view.findViewById(R.id.inner_scroll_container);
		viewpager1 = _view.findViewById(R.id.viewpager1);
		banner_shimmer = _view.findViewById(R.id.banner_shimmer);
		no_internet_holder = _view.findViewById(R.id.no_internet_holder);
		linear4 = _view.findViewById(R.id.linear4);
		linear5 = _view.findViewById(R.id.linear5);
		linear7 = _view.findViewById(R.id.linear7);
		hm_sec1_relative = _view.findViewById(R.id.hm_sec1_relative);
		linear9 = _view.findViewById(R.id.linear9);
		hm_sec2_relative = _view.findViewById(R.id.hm_sec2_relative);
		linear11 = _view.findViewById(R.id.linear11);
		hm_sec3_data_choices_rec = _view.findViewById(R.id.hm_sec3_data_choices_rec);
		hm_sec3_relative = _view.findViewById(R.id.hm_sec3_relative);
		linear15 = _view.findViewById(R.id.linear15);
		hm_sec4_data_choices_rec = _view.findViewById(R.id.hm_sec4_data_choices_rec);
		hm_sec4_relative = _view.findViewById(R.id.hm_sec4_relative);
		linear16 = _view.findViewById(R.id.linear16);
		hm_sec5_relative = _view.findViewById(R.id.hm_sec5_relative);
		linear17 = _view.findViewById(R.id.linear17);
		linear18 = _view.findViewById(R.id.linear18);
		linear19 = _view.findViewById(R.id.linear19);
		imageview1 = _view.findViewById(R.id.imageview1);
		textview18 = _view.findViewById(R.id.textview18);
		materialbutton1 = _view.findViewById(R.id.materialbutton1);
		textview6 = _view.findViewById(R.id.textview6);
		textview7 = _view.findViewById(R.id.textview7);
		hscroll2 = _view.findViewById(R.id.hscroll2);
		linear6 = _view.findViewById(R.id.linear6);
		cat_chip1 = _view.findViewById(R.id.cat_chip1);
		cat_chip2 = _view.findViewById(R.id.cat_chip2);
		cat_chip3 = _view.findViewById(R.id.cat_chip3);
		cat_chip4 = _view.findViewById(R.id.cat_chip4);
		cat_chip5 = _view.findViewById(R.id.cat_chip5);
		cat_chip6 = _view.findViewById(R.id.cat_chip6);
		cat_chip7 = _view.findViewById(R.id.cat_chip7);
		cat_txt1 = _view.findViewById(R.id.cat_txt1);
		cat_txt2 = _view.findViewById(R.id.cat_txt2);
		cat_txt3 = _view.findViewById(R.id.cat_txt3);
		cat_txt4 = _view.findViewById(R.id.cat_txt4);
		cat_txt5 = _view.findViewById(R.id.cat_txt5);
		cat_txt6 = _view.findViewById(R.id.cat_txt6);
		cat_txt7 = _view.findViewById(R.id.cat_txt7);
		textview8 = _view.findViewById(R.id.textview8);
		textview9 = _view.findViewById(R.id.textview9);
		hm_sec1_loading_rec = _view.findViewById(R.id.hm_sec1_loading_rec);
		hm_sec1_data_rec = _view.findViewById(R.id.hm_sec1_data_rec);
		textview10 = _view.findViewById(R.id.textview10);
		hm_sec2_loading_rec = _view.findViewById(R.id.hm_sec2_loading_rec);
		hm_sec2_data_rec = _view.findViewById(R.id.hm_sec2_data_rec);
		textview12 = _view.findViewById(R.id.textview12);
		textview13 = _view.findViewById(R.id.textview13);
		hm_sec3_loading_rec = _view.findViewById(R.id.hm_sec3_loading_rec);
		hm_sec3_data_rec = _view.findViewById(R.id.hm_sec3_data_rec);
		textview14 = _view.findViewById(R.id.textview14);
		textview15 = _view.findViewById(R.id.textview15);
		hm_sec4_loading_rec = _view.findViewById(R.id.hm_sec4_loading_rec);
		hm_sec4_data_rec = _view.findViewById(R.id.hm_sec4_data_rec);
		textview16 = _view.findViewById(R.id.textview16);
		textview17 = _view.findViewById(R.id.textview17);
		hm_sec5_loading_rec = _view.findViewById(R.id.hm_sec5_loading_rec);
		hm_sec5_data_rec = _view.findViewById(R.id.hm_sec5_data_rec);
		auth = FirebaseAuth.getInstance();
		request_home = new RequestNetworkV2((Activity) getContext());
		
		swiperefreshlayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				_fetchHomeData();
			}
		});
		
		viewpager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageSelected(int _position) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int _scrollState) {
				
			}
		});
		
		materialbutton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_fetchHomeData();
				com.google.android.material.snackbar.Snackbar.make(inner_scroll_container, "Fetching data....", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction(".....", new View.OnClickListener(){
					@Override
					public void onClick(View _view) {
						
					}
				}).show();
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
		
		_request_home_request_listener = new RequestNetworkV2.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
                
                //start of on response content
				try {
    JSONObject root = new JSONObject(_response);
    boolean success = root.optBoolean("success", false);

    if (success) {
        com.error404.reelix.ReelixCacheManager.getInstance().saveResponse("home_data", _response);

        JSONObject data = root.getJSONObject("data");

        // Banners
        JSONArray banners = data.getJSONArray("banners");
        slide_list.clear();
        for (int i = 0; i < banners.length(); i++) {
            JSONObject item = banners.getJSONObject(i);
            HashMap<String, Object> map = new HashMap<>();
            map.put("banner_url", item.optString("image_url", ""));
            map.put("title", item.optString("title", ""));
            map.put("paxsenix_id", item.optString("paxsenix_id", ""));
            map.put("moviebox_id", item.optString("moviebox_id", ""));
            map.put("media_type", item.optString("media_type", "movie"));
            map.put("source", item.optString("source", ""));
            slide_list.add(map);
        }
        viewpager1.setAdapter(new Viewpager1Adapter(slide_list));
        ((PagerAdapter) viewpager1.getAdapter()).notifyDataSetChanged();
        viewpager1.setVisibility(View.VISIBLE);
        banner_shimmer.setVisibility(View.GONE);

        JSONArray sections = data.getJSONArray("sections");
        hm_sec1_data_lsmap.clear();
        hm_sec2_data_lsmap.clear();
        hm_sec5_data_lsmap.clear();
        hm_sec3_genre_lsmap.clear();
        hm_sec3_genre_items_map.clear();
        hm_sec4_genre_lsmap.clear();
        hm_sec4_genre_items_map.clear();

        // DEBUG: collect raw genre sections as we encounter them
        JSONArray debugGenreSections = new JSONArray();

        for (int s = 0; s < sections.length(); s++) {
            JSONObject section = sections.getJSONObject(s);
            String sectionType = section.optString("type", "");
            JSONArray items = section.optJSONArray("items");

            if (sectionType.equals("trending") && items != null) {
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("id", item.optString("id", ""));
                    map.put("paxsenix_id", item.isNull("paxsenix_id") ? "" : item.optString("paxsenix_id", ""));
                    map.put("tmdb_id", item.isNull("tmdb_id") ? "" : item.optString("tmdb_id", ""));
                    map.put("moviebox_id", item.isNull("moviebox_id") ? "" : item.optString("moviebox_id", ""));
                    map.put("media_type", item.optString("media_type", "movie"));
                    map.put("title", item.optString("title", "Unknown Title"));
                    map.put("poster_url", item.isNull("poster_url") ? "" : item.optString("poster_url", ""));
                    map.put("cover_url", item.isNull("cover_url") ? "" : item.optString("cover_url", ""));
                    map.put("vote_average", item.optDouble("vote_average", 0));
                    map.put("source", item.optString("source", ""));
                    hm_sec1_data_lsmap.add(map);
                }
            } else if (sectionType.equals("latest") && items != null) {
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("id", item.optString("id", ""));
                    map.put("paxsenix_id", item.isNull("paxsenix_id") ? "" : item.optString("paxsenix_id", ""));
                    map.put("tmdb_id", item.isNull("tmdb_id") ? "" : item.optString("tmdb_id", ""));
                    map.put("moviebox_id", item.isNull("moviebox_id") ? "" : item.optString("moviebox_id", ""));
                    map.put("media_type", item.optString("media_type", "movie"));
                    map.put("title", item.optString("title", "Unknown Title"));
                    map.put("poster_url", item.isNull("poster_url") ? "" : item.optString("poster_url", ""));
                    map.put("cover_url", item.isNull("cover_url") ? "" : item.optString("cover_url", ""));
                    map.put("vote_average", item.optDouble("vote_average", 0));
                    map.put("source", item.optString("source", ""));
                    hm_sec2_data_lsmap.add(map);
                }
            } else if (sectionType.equals("random") && items != null) {
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("id", item.optString("id", ""));
                    map.put("paxsenix_id", item.isNull("paxsenix_id") ? "" : item.optString("paxsenix_id", ""));
                    map.put("tmdb_id", item.isNull("tmdb_id") ? "" : item.optString("tmdb_id", ""));
                    map.put("moviebox_id", item.isNull("moviebox_id") ? "" : item.optString("moviebox_id", ""));
                    map.put("media_type", item.optString("media_type", "movie"));
                    map.put("title", item.optString("title", "Unknown Title"));
                    map.put("poster_url", item.isNull("poster_url") ? "" : item.optString("poster_url", ""));
                    map.put("cover_url", item.isNull("cover_url") ? "" : item.optString("cover_url", ""));
                    map.put("vote_average", item.optDouble("vote_average", 0));
                    map.put("source", item.optString("source", ""));
                    hm_sec5_data_lsmap.add(map);
                }
            } else if (sectionType.equals("genre") && items != null) {
                String genreKey = section.optString("key", "");
                String genreTitle = section.optString("title", "");

                // DEBUG: stash this raw genre section object (minus items, to keep it small) for clipboard
                try {
                    JSONObject debugSection = new JSONObject();
                    debugSection.put("key", genreKey);
                    debugSection.put("title", genreTitle);
                    debugSection.put("item_count", items.length());
                    debugGenreSections.put(debugSection);
                } catch (Exception ignored) {}

                ArrayList<HashMap<String, Object>> genreItems = new ArrayList<>();
                ArrayList<HashMap<String, Object>> genreItemsTv = new ArrayList<>();
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    String itemMediaType = item.optString("media_type", "");

                    HashMap<String, Object> map = new HashMap<>();
                    map.put("id", item.optString("id", ""));
                    map.put("paxsenix_id", item.isNull("paxsenix_id") ? "" : item.optString("paxsenix_id", ""));
                    map.put("tmdb_id", item.isNull("tmdb_id") ? "" : item.optString("tmdb_id", ""));
                    map.put("moviebox_id", item.isNull("moviebox_id") ? "" : item.optString("moviebox_id", ""));
                    map.put("media_type", item.optString("media_type", "movie"));
                    map.put("title", item.optString("title", "Unknown Title"));
                    map.put("poster_url", item.isNull("poster_url") ? "" : item.optString("poster_url", ""));
                    map.put("cover_url", item.isNull("cover_url") ? "" : item.optString("cover_url", ""));
                    map.put("vote_average", item.optDouble("vote_average", 0));
                    map.put("source", item.optString("source", ""));

                    // Movies only
                    if ("movie".equals(itemMediaType)) {
                        genreItems.add(map);
                    }
                    // TV/series only
                    if ("tv".equals(itemMediaType) || "series".equals(itemMediaType)) {
                        genreItemsTv.add(map);
                    }
                }

                if (!genreItems.isEmpty()) {
                    hm_sec3_genre_items_map.put(genreKey, genreItems);

                    HashMap<String, Object> chipMap = new HashMap<>();
                    chipMap.put("key", genreKey);
                    chipMap.put("title", genreTitle);
                    hm_sec3_genre_lsmap.add(chipMap);
                }

                if (!genreItemsTv.isEmpty()) {
                    hm_sec4_genre_items_map.put(genreKey, genreItemsTv);

                    HashMap<String, Object> chipMapTv = new HashMap<>();
                    chipMapTv.put("key", genreKey);
                    chipMapTv.put("title", genreTitle);
                    hm_sec4_genre_lsmap.add(chipMapTv);
                }
            }
        }

        hm_sec1_data_rec.setAdapter(new Hm_sec1_data_recAdapter(hm_sec1_data_lsmap));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        hm_sec1_data_rec.setLayoutManager(gridLayoutManager);
        hm_sec1_data_rec.setVisibility(View.VISIBLE);
        hm_sec1_loading_rec.setVisibility(View.GONE);

        hm_sec2_data_rec.setAdapter(new Hm_sec2_data_recAdapter(hm_sec2_data_lsmap));
        hm_sec2_data_rec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        hm_sec2_data_rec.setVisibility(View.VISIBLE);
        hm_sec2_loading_rec.setVisibility(View.GONE);

        hm_sec5_data_rec.setAdapter(new Hm_sec5_data_recAdapter(hm_sec5_data_lsmap));
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        hm_sec5_data_rec.setLayoutManager(gridLayoutManager2);
        hm_sec5_data_rec.setNestedScrollingEnabled(false);
        hm_sec5_data_rec.setVisibility(View.VISIBLE);
        hm_sec5_loading_rec.setVisibility(View.GONE);

        // Genre chips + default selection (first genre) — Movies Rankings
        hm_sec3_data_choices_rec.setAdapter(new Hm_sec3_data_choices_recAdapter(hm_sec3_genre_lsmap));
        hm_sec3_data_choices_rec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Genre chips + default selection (first genre) — Series Rankings
        hm_sec4_data_choices_rec.setAdapter(new Hm_sec4_data_choices_recAdapter(hm_sec4_genre_lsmap));
        hm_sec4_data_choices_rec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // DEBUG: copy summary of genre sections + final chip list size to clipboard
        try {
            JSONObject debugPayload = new JSONObject();
            debugPayload.put("raw_genre_sections_found", debugGenreSections.length());
            debugPayload.put("raw_genre_sections", debugGenreSections);
            debugPayload.put("final_chip_count_movies", hm_sec3_genre_lsmap.size());
            debugPayload.put("final_chip_count_tv", hm_sec4_genre_lsmap.size());

            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("genre_debug", debugPayload.toString(2));
            clipboard.setPrimaryClip(clip);
        } catch (Exception ignored) {}

        if (!hm_sec3_genre_lsmap.isEmpty()) {
            String firstKey = hm_sec3_genre_lsmap.get(0).get("key").toString();
            _selectGenre(firstKey);
        }

        if (!hm_sec4_genre_lsmap.isEmpty()) {
            String firstKeyTv = hm_sec4_genre_lsmap.get(0).get("key").toString();
            _selectGenreSec4(firstKeyTv);
        }
    } else {
        SketchwareUtil.showMessage(getContext(), "Failed to load home data");
    }
} catch (Exception e) {
    e.printStackTrace();
    SketchwareUtil.showMessage(getContext(), "Parse Error: " + e.getMessage());
}

swiperefreshlayout1.setRefreshing(false);

//end of onresponse content
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
                
                //start of on error content
                
				if (!isAdded() || getContext() == null) return;
    swiperefreshlayout1.setRefreshing(false);
    
    // Banners: only show error/loading state if we have no cached data visible
    if (slide_list.isEmpty()) {
        viewpager1.setVisibility(View.GONE);
        banner_shimmer.setVisibility(View.VISIBLE);
    } else {
        viewpager1.setVisibility(View.VISIBLE);
        banner_shimmer.setVisibility(View.GONE);
    }
    
    // Trending: only show error/loading state if we have no cached data visible
    if (hm_sec1_data_lsmap.isEmpty()) {
        hm_sec1_loading_rec.setVisibility(View.VISIBLE);
        hm_sec1_data_rec.setVisibility(View.GONE);
    } else {
        hm_sec1_loading_rec.setVisibility(View.GONE);
        hm_sec1_data_rec.setVisibility(View.VISIBLE);
    }
    
    // Latest: only show error/loading state if we have no cached data visible
    if (hm_sec2_data_lsmap.isEmpty()) {
        hm_sec2_loading_rec.setVisibility(View.VISIBLE);
        hm_sec2_data_rec.setVisibility(View.GONE);
    } else {
        hm_sec2_loading_rec.setVisibility(View.GONE);
        hm_sec2_data_rec.setVisibility(View.VISIBLE);
    }
    
    if (hm_sec3_data_lsmap.isEmpty()) {
        hm_sec3_loading_rec.setVisibility(View.VISIBLE);
        hm_sec3_data_rec.setVisibility(View.GONE);
    } else {
        hm_sec3_loading_rec.setVisibility(View.GONE);
        hm_sec3_data_rec.setVisibility(View.VISIBLE);
    }
    
    // Series Rankings: only show error/loading state if we have no cached data visible
    if (hm_sec4_data_lsmap.isEmpty()) {
        hm_sec4_loading_rec.setVisibility(View.VISIBLE);
        hm_sec4_data_rec.setVisibility(View.GONE);
    } else {
        hm_sec4_loading_rec.setVisibility(View.GONE);
        hm_sec4_data_rec.setVisibility(View.VISIBLE);
    }
    
    // Random Picks: only show error/loading state if we have no cached data visible
    if (hm_sec5_data_lsmap.isEmpty()) {
        hm_sec5_loading_rec.setVisibility(View.VISIBLE);
        hm_sec5_data_rec.setVisibility(View.GONE);
    } else {
        hm_sec5_loading_rec.setVisibility(View.GONE);
        hm_sec5_data_rec.setVisibility(View.VISIBLE);
    }
    
    // Only surface the error message if there's genuinely nothing to show the user
    if (slide_list.isEmpty() && hm_sec1_data_lsmap.isEmpty() && hm_sec2_data_lsmap.isEmpty() && hm_sec5_data_lsmap.isEmpty()) {
        SketchwareUtil.showMessage(getContext(), "Network Error: " + _message);
    }
			}
            
            //end of on error content
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
		hscroll2.setHorizontalScrollBarEnabled(false);
		hscroll2.setVerticalScrollBarEnabled(false);
		hscroll2.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
		
		// Category chips
		cat_chip1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)2, 0xFF212121, Color.TRANSPARENT));
		cat_chip2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)2, 0xFF212121, Color.TRANSPARENT));
		cat_chip3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)2, 0xFF212121, Color.TRANSPARENT));
		cat_chip4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)2, 0xFF212121, Color.TRANSPARENT));
		cat_chip5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)2, 0xFF212121, Color.TRANSPARENT));
		cat_chip6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)2, 0xFF212121, Color.TRANSPARENT));
		cat_chip7.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)2, 0xFF212121, Color.TRANSPARENT));
		// Initialize cache manager instance securely
		com.error404.reelix.ReelixCacheManager.initialize(getContext());
		com.error404.reelix.ReelixCacheManager cacheInstance = com.error404.reelix.ReelixCacheManager.getInstance();
		
		// Load cached home data to avoid layout stutter if offline or slow connection
		try {
			String cachedHomeData = cacheInstance.getResponse("home_data");
			
			if (!cachedHomeData.isEmpty()) {
				JSONObject cachedRoot = new JSONObject(cachedHomeData);
				JSONObject cachedData = cachedRoot.getJSONObject("data");
				
				// Banners
				JSONArray cachedBanners = cachedData.getJSONArray("banners");
				if (slide_list.isEmpty()) {
					for (int i = 0; i < cachedBanners.length(); i++) {
						JSONObject item = cachedBanners.getJSONObject(i);
						HashMap<String, Object> map = new HashMap<>();
						map.put("banner_url", item.optString("image_url", ""));
						map.put("title", item.optString("title", ""));
						map.put("paxsenix_id", item.optString("paxsenix_id", ""));
						map.put("moviebox_id", item.optString("moviebox_id", ""));
						map.put("media_type", item.optString("media_type", "movie"));
						map.put("source", item.optString("source", ""));
						slide_list.add(map);
					}
					viewpager1.setAdapter(new Viewpager1Adapter(slide_list));
					viewpager1.setVisibility(View.VISIBLE);
					banner_shimmer.setVisibility(View.GONE);
				}
				
				// Trending + Latest + Random + Genre (Movies/Series Rankings) sections
				Context warmContext = getContext() != null ? getContext() : getActivity();
				
				boolean needsSectionRestore = hm_sec1_data_lsmap.isEmpty() || hm_sec2_data_lsmap.isEmpty()
				|| hm_sec5_data_lsmap.isEmpty() || hm_sec3_genre_lsmap.isEmpty() || hm_sec4_genre_lsmap.isEmpty();
				
				if (needsSectionRestore) {
					JSONArray cachedSections = cachedData.getJSONArray("sections");
					
					for (int s = 0; s < cachedSections.length(); s++) {
						JSONObject section = cachedSections.getJSONObject(s);
						String sectionType = section.optString("type", "");
						JSONArray items = section.optJSONArray("items");
						
						if (sectionType.equals("trending") && hm_sec1_data_lsmap.isEmpty() && items != null) {
							for (int i = 0; i < items.length(); i++) {
								JSONObject item = items.getJSONObject(i);
								HashMap<String, Object> map = new HashMap<>();
								map.put("id", item.optString("id", ""));
								map.put("paxsenix_id", item.isNull("paxsenix_id") ? "" : item.optString("paxsenix_id", ""));
								map.put("tmdb_id", item.isNull("tmdb_id") ? "" : item.optString("tmdb_id", ""));
								map.put("moviebox_id", item.isNull("moviebox_id") ? "" : item.optString("moviebox_id", ""));
								map.put("media_type", item.optString("media_type", "movie"));
								map.put("title", item.optString("title", "Unknown Title"));
								map.put("poster_url", item.isNull("poster_url") ? "" : item.optString("poster_url", ""));
								map.put("cover_url", item.isNull("cover_url") ? "" : item.optString("cover_url", ""));
								map.put("vote_average", item.optDouble("vote_average", 0));
								map.put("source", item.optString("source", ""));
								hm_sec1_data_lsmap.add(map);
							}
						} else if (sectionType.equals("latest") && hm_sec2_data_lsmap.isEmpty() && items != null) {
							for (int i = 0; i < items.length(); i++) {
								JSONObject item = items.getJSONObject(i);
								HashMap<String, Object> map = new HashMap<>();
								map.put("id", item.optString("id", ""));
								map.put("paxsenix_id", item.isNull("paxsenix_id") ? "" : item.optString("paxsenix_id", ""));
								map.put("tmdb_id", item.isNull("tmdb_id") ? "" : item.optString("tmdb_id", ""));
								map.put("moviebox_id", item.isNull("moviebox_id") ? "" : item.optString("moviebox_id", ""));
								map.put("media_type", item.optString("media_type", "movie"));
								map.put("title", item.optString("title", "Unknown Title"));
								map.put("poster_url", item.isNull("poster_url") ? "" : item.optString("poster_url", ""));
								map.put("cover_url", item.isNull("cover_url") ? "" : item.optString("cover_url", ""));
								map.put("vote_average", item.optDouble("vote_average", 0));
								map.put("source", item.optString("source", ""));
								hm_sec2_data_lsmap.add(map);
							}
						} else if (sectionType.equals("random") && hm_sec5_data_lsmap.isEmpty() && items != null) {
							for (int i = 0; i < items.length(); i++) {
								JSONObject item = items.getJSONObject(i);
								HashMap<String, Object> map = new HashMap<>();
								map.put("id", item.optString("id", ""));
								map.put("paxsenix_id", item.isNull("paxsenix_id") ? "" : item.optString("paxsenix_id", ""));
								map.put("tmdb_id", item.isNull("tmdb_id") ? "" : item.optString("tmdb_id", ""));
								map.put("moviebox_id", item.isNull("moviebox_id") ? "" : item.optString("moviebox_id", ""));
								map.put("media_type", item.optString("media_type", "movie"));
								map.put("title", item.optString("title", "Unknown Title"));
								map.put("poster_url", item.isNull("poster_url") ? "" : item.optString("poster_url", ""));
								map.put("cover_url", item.isNull("cover_url") ? "" : item.optString("cover_url", ""));
								map.put("vote_average", item.optDouble("vote_average", 0));
								map.put("source", item.optString("source", ""));
								hm_sec5_data_lsmap.add(map);
							}
						} else if (sectionType.equals("genre") && items != null) {
							String genreKey = section.optString("key", "");
							String genreTitle = section.optString("title", "");
							
							ArrayList<HashMap<String, Object>> genreItems = new ArrayList<>();
							ArrayList<HashMap<String, Object>> genreItemsTv = new ArrayList<>();
							
							for (int i = 0; i < items.length(); i++) {
								JSONObject item = items.getJSONObject(i);
								String itemMediaType = item.optString("media_type", "");
								
								HashMap<String, Object> map = new HashMap<>();
								map.put("id", item.optString("id", ""));
								map.put("paxsenix_id", item.isNull("paxsenix_id") ? "" : item.optString("paxsenix_id", ""));
								map.put("tmdb_id", item.isNull("tmdb_id") ? "" : item.optString("tmdb_id", ""));
								map.put("moviebox_id", item.isNull("moviebox_id") ? "" : item.optString("moviebox_id", ""));
								map.put("media_type", item.optString("media_type", "movie"));
								map.put("title", item.optString("title", "Unknown Title"));
								map.put("poster_url", item.isNull("poster_url") ? "" : item.optString("poster_url", ""));
								map.put("cover_url", item.isNull("cover_url") ? "" : item.optString("cover_url", ""));
								map.put("vote_average", item.optDouble("vote_average", 0));
								map.put("source", item.optString("source", ""));
								
								if ("movie".equals(itemMediaType)) {
									genreItems.add(map);
								}
								if ("tv".equals(itemMediaType) || "series".equals(itemMediaType)) {
									genreItemsTv.add(map);
								}
							}
							
							if (!hm_sec3_genre_items_map.containsKey(genreKey) && !genreItems.isEmpty()) {
								hm_sec3_genre_items_map.put(genreKey, genreItems);
								
								HashMap<String, Object> chipMap = new HashMap<>();
								chipMap.put("key", genreKey);
								chipMap.put("title", genreTitle);
								hm_sec3_genre_lsmap.add(chipMap);
							}
							
							if (!hm_sec4_genre_items_map.containsKey(genreKey) && !genreItemsTv.isEmpty()) {
								hm_sec4_genre_items_map.put(genreKey, genreItemsTv);
								
								HashMap<String, Object> chipMapTv = new HashMap<>();
								chipMapTv.put("key", genreKey);
								chipMapTv.put("title", genreTitle);
								hm_sec4_genre_lsmap.add(chipMapTv);
							}
						}
					}
					
					if (warmContext != null) {
						if (!hm_sec1_data_lsmap.isEmpty()) {
							hm_sec1_data_rec.setAdapter(new Hm_sec1_data_recAdapter(hm_sec1_data_lsmap));
							GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
							hm_sec1_data_rec.setLayoutManager(gridLayoutManager);
							hm_sec1_data_rec.setVisibility(View.VISIBLE);
							hm_sec1_loading_rec.setVisibility(View.GONE);
						}
						
						if (!hm_sec2_data_lsmap.isEmpty()) {
							hm_sec2_data_rec.setAdapter(new Hm_sec2_data_recAdapter(hm_sec2_data_lsmap));
							hm_sec2_data_rec.setLayoutManager(new LinearLayoutManager(warmContext, LinearLayoutManager.HORIZONTAL, false));
							hm_sec2_data_rec.setVisibility(View.VISIBLE);
							hm_sec2_loading_rec.setVisibility(View.GONE);
						}
						
						if (!hm_sec5_data_lsmap.isEmpty()) {
							hm_sec5_data_rec.setAdapter(new Hm_sec5_data_recAdapter(hm_sec5_data_lsmap));
							GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
							hm_sec5_data_rec.setLayoutManager(gridLayoutManager2);
							hm_sec5_data_rec.setNestedScrollingEnabled(false);
							hm_sec5_data_rec.setVisibility(View.VISIBLE);
							hm_sec5_loading_rec.setVisibility(View.GONE);
						}
						
						if (!hm_sec3_genre_lsmap.isEmpty()) {
							hm_sec3_data_choices_rec.setAdapter(new Hm_sec3_data_choices_recAdapter(hm_sec3_genre_lsmap));
							hm_sec3_data_choices_rec.setLayoutManager(new LinearLayoutManager(warmContext, LinearLayoutManager.HORIZONTAL, false));
							
							String firstKey = hm_sec3_genre_lsmap.get(0).get("key").toString();
							_selectGenre(firstKey);
						}
						
						if (!hm_sec4_genre_lsmap.isEmpty()) {
							hm_sec4_data_choices_rec.setAdapter(new Hm_sec4_data_choices_recAdapter(hm_sec4_genre_lsmap));
							hm_sec4_data_choices_rec.setLayoutManager(new LinearLayoutManager(warmContext, LinearLayoutManager.HORIZONTAL, false));
							
							String firstKeyTv = hm_sec4_genre_lsmap.get(0).get("key").toString();
							_selectGenreSec4(firstKeyTv);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		no_internet_holder.setVisibility(View.GONE);
		_fetchHomeData();
		_setupNetworkMonitor();
		nestedScrollView1.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
			@Override
			public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
				ScrollBlurHelper helper = ((FeedActivity) getActivity()).getScrollBlurHelper();
				if (helper != null) {
					helper.onScrollChanged(scrollY);
				}
			}
		});
		textview6.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/visions.ttf"), 1);
		textview7.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		textview8.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/visions.ttf"), 1);
		textview9.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		textview10.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/visions.ttf"), 1);
		materialbutton1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		textview12.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/visions.ttf"), 1);
		textview13.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		textview14.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/visions.ttf"), 1);
		textview15.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		textview16.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/visions.ttf"), 1);
		textview17.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		cat_txt1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		cat_txt2.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		cat_txt3.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		cat_txt4.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		cat_txt5.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		cat_txt6.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		cat_txt7.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		hm_sec2_data_rec.setAdapter(new Hm_sec2_data_recAdapter(hm_sec2_data_lsmap));
		hm_sec2_data_rec.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
		hm_sec5_data_rec.setAdapter(new Hm_sec5_data_recAdapter(hm_sec5_data_lsmap));
		GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
		hm_sec5_data_rec.setLayoutManager(gridLayoutManager2);
		hm_sec5_data_rec.setNestedScrollingEnabled(false);
		hm_sec1_loading_rec.setVisibility(View.GONE);
		hm_sec2_loading_rec.setVisibility(View.GONE);
		hm_sec3_loading_rec.setVisibility(View.GONE);
		hm_sec4_loading_rec.setVisibility(View.GONE);
		hm_sec5_loading_rec.setVisibility(View.GONE);
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (networkCallback != null) {
			ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
			if (cm != null) {
				cm.unregisterNetworkCallback(networkCallback);
			}
		}
	}
	public void _ImgRound(final ImageView _imageview, final double _value) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable ();
		gd.setColor(android.R.color.transparent);
		gd.setCornerRadius((int)_value);
		_imageview.setClipToOutline(true);
		_imageview.setBackground(gd);
	}
	
	
	public double _convertToDp(final double _pixels) {
		return TypedValue.applyDimension(
		TypedValue.COMPLEX_UNIT_DIP,
		(float)_pixels,
		getResources().getDisplayMetrics());
	}
	
	
	public void _TransitionManager(final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _anchore() {
		
	}
    
    public void _fetchHomeData() {
	swiperefreshlayout1.setRefreshing(true);

	if (slide_list.isEmpty()) {
		viewpager1.setVisibility(View.GONE);
		banner_shimmer.setVisibility(View.VISIBLE);
	}

	if (hm_sec1_data_lsmap.isEmpty()) {
		hm_sec1_data_rec.setVisibility(View.GONE);
		hm_sec1_loading_rec.setVisibility(View.VISIBLE);
	}

	if (hm_sec2_data_lsmap.isEmpty()) {
		hm_sec2_data_rec.setVisibility(View.GONE);
		hm_sec2_loading_rec.setVisibility(View.VISIBLE);
	}

	if (hm_sec3_data_lsmap.isEmpty()) {
		hm_sec3_data_rec.setVisibility(View.GONE);
		hm_sec3_loading_rec.setVisibility(View.VISIBLE);
	}

	if (hm_sec4_data_lsmap.isEmpty()) {
		hm_sec4_data_rec.setVisibility(View.GONE);
		hm_sec4_loading_rec.setVisibility(View.VISIBLE);
	}

	if (hm_sec5_data_lsmap.isEmpty()) {
		hm_sec5_data_rec.setVisibility(View.GONE);
		hm_sec5_loading_rec.setVisibility(View.VISIBLE);
	}

	_sendHomeRequest();
}

public void _sendHomeRequest() {
	HashMap<String, Object> headers = new HashMap<>();
	headers.put("x-api-key", "516577400478683");
	headers.put("Content-Type", "application/json");
	request_home.setHeaders(headers);

	try {
		JSONObject bodyJson = new JSONObject();
		bodyJson.put("trending_limit", 10);
		bodyJson.put("genre_batches", "all");
		bodyJson.put("random_limit", 60);
		bodyJson.put("random_genre_count", 3);
		bodyJson.put("random_pages", 2);

		request_home.setJsonBody(bodyJson.toString(), RequestNetworkControllerV2.REQUEST_BODY);
	} catch (Exception e) {
		e.printStackTrace();
		// fallback: still attempt request with the old HashMap params
		HashMap<String, Object> body = new HashMap<>();
		body.put("trending_limit", (int)(10));
		body.put("genre_batches", "all");
		body.put("random_limit", (int)(60));
		body.put("random_genre_count", (int)(3));
		body.put("random_pages", (int)(2));
		request_home.setParams(body, RequestNetworkControllerV2.REQUEST_BODY);
	}

	request_home.startRequestNetwork(RequestNetworkControllerV2.POST, "https://error404-api.vercel.app/api/home", "home_req", _request_home_request_listener);
}

public void _setupNetworkMonitor() {
    ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

    // Check initial state immediately on load
    boolean isConnectedNow = false;
    if (cm != null) {
        android.net.NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnectedNow = activeNetwork != null && activeNetwork.isConnected();
    }
    _updateNoInternetUI(isConnectedNow, false);

    if (cm != null) {
        networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(android.net.Network network) {
                if (getActivity() == null) return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _updateNoInternetUI(true, true);
                    }
                });
            }

            @Override
            public void onLost(android.net.Network network) {
                if (getActivity() == null) return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _updateNoInternetUI(false, true);
                    }
                });
            }
        };

        android.net.NetworkRequest request = new android.net.NetworkRequest.Builder()
                .addCapability(android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build();

        cm.registerNetworkCallback(request, networkCallback);
    }
}

public void _updateNoInternetUI(final boolean isConnected, final boolean animate) {
    if (!isAdded() || getContext() == null) return;

    if (isConnected) {
        if (no_internet_holder.getVisibility() == View.VISIBLE) {
            if (animate) {
                no_internet_holder.animate()
                        .alpha(0f)
                        .setDuration(280)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                no_internet_holder.setVisibility(View.GONE);
                            }
                        })
                        .start();
            } else {
                no_internet_holder.setVisibility(View.GONE);
            }

            // Auto-retry only if we still have nothing to show the user
            if (slide_list.isEmpty() && hm_sec1_data_lsmap.isEmpty() && hm_sec2_data_lsmap.isEmpty() && hm_sec5_data_lsmap.isEmpty()) {
                _fetchHomeData();
            }
        }
    } else {
        if (no_internet_holder.getVisibility() != View.VISIBLE) {
            no_internet_holder.setAlpha(0f);
            no_internet_holder.setVisibility(View.VISIBLE);
            if (animate) {
                no_internet_holder.animate()
                        .alpha(1f)
                        .setDuration(280)
                        .start();
            } else {
                no_internet_holder.setAlpha(1f);
            }
        }
    }
}


public void _selectGenre(String genreKey) {
    if (!hm_sec3_genre_items_map.containsKey(genreKey)) return;

    selectedGenreKey = genreKey;

    hm_sec3_data_lsmap.clear();
    hm_sec3_data_lsmap.addAll(hm_sec3_genre_items_map.get(genreKey));

    hm_sec3_data_rec.setAdapter(new Hm_sec3_data_recAdapter(hm_sec3_data_lsmap));
    hm_sec3_data_rec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

    hm_sec3_data_rec.setVisibility(View.VISIBLE);
    hm_sec3_loading_rec.setVisibility(View.GONE);

    // Refresh chip styling to reflect new selection
    if (hm_sec3_data_choices_rec.getAdapter() != null) {
        hm_sec3_data_choices_rec.getAdapter().notifyDataSetChanged();
    }
}

public void _selectGenreSec4(String genreKey) {
    if (!hm_sec4_genre_items_map.containsKey(genreKey)) return;

    selectedGenreKeySec4 = genreKey;

    hm_sec4_data_lsmap.clear();
    hm_sec4_data_lsmap.addAll(hm_sec4_genre_items_map.get(genreKey));

    hm_sec4_data_rec.setAdapter(new Hm_sec4_data_recAdapter(hm_sec4_data_lsmap));
    hm_sec4_data_rec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

    hm_sec4_data_rec.setVisibility(View.VISIBLE);
    hm_sec4_loading_rec.setVisibility(View.GONE);

    if (hm_sec4_data_choices_rec.getAdapter() != null) {
        hm_sec4_data_choices_rec.getAdapter().notifyDataSetChanged();
    }
}
	
	public class Viewpager1Adapter extends PagerAdapter {
		
		Context _context;
		ArrayList<HashMap<String, Object>> _data;
		
		public Viewpager1Adapter(Context _ctx, ArrayList<HashMap<String, Object>> _arr) {
			_context = _ctx;
			_data = _arr;
		}
		
		public Viewpager1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_context = getContext().getApplicationContext();
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public boolean isViewFromObject(View _view, Object _object) {
			return _view == _object;
		}
		
		@Override
		public void destroyItem(ViewGroup _container, int _position, Object _object) {
			_container.removeView((View) _object);
		}
		
		@Override
		public int getItemPosition(Object _object) {
			return super.getItemPosition(_object);
		}
		
		@Override
		public CharSequence getPageTitle(int pos) {
			// Use the Activity Event (onTabLayoutNewTabAdded) in order to use this method
			return "page " + String.valueOf(pos);
		}
		
		@Override
		public Object instantiateItem(ViewGroup _container,  final int _position) {
			View _view = LayoutInflater.from(_context).inflate(R.layout.img, _container, false);
			
			final androidx.cardview.widget.CardView materialCardView1 = _view.findViewById(R.id.materialCardView1);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout overlay = _view.findViewById(R.id.overlay);
			final LinearLayout overlay_details_holder = _view.findViewById(R.id.overlay_details_holder);
			final LinearLayout title_holder = _view.findViewById(R.id.title_holder);
			final LinearLayout sub_holder = _view.findViewById(R.id.sub_holder);
			final TextView title = _view.findViewById(R.id.title);
			final TextView date = _view.findViewById(R.id.date);
			final TextView sub_text = _view.findViewById(R.id.sub_text);
			
			title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 1);
			date.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			sub_text.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			final HashMap<String, Object> currentMovie = _data.get((int)_position);
			
			if (getContext() != null && getActivity() != null) {
				final Context fragmentContext = getContext();
				final Activity hostActivity = getActivity();
				
				// Load the banner image using Glide
				String bannerUrl = currentMovie.containsKey("banner_url") && currentMovie.get("banner_url") != null ? currentMovie.get("banner_url").toString() : "";
				if (!bannerUrl.isEmpty()) {
					Glide.with(fragmentContext).load(Uri.parse(bannerUrl)).into(imageview1);
				} else {
					imageview1.setImageResource(R.drawable.background_banner);
				}
				
				// Set Title
				String movieTitle = currentMovie.containsKey("title") && currentMovie.get("title") != null ? currentMovie.get("title").toString() : "Unknown Title";
				title.setText(movieTitle);
				
				// No year/plot fields in banner data, so hide those views entirely
				date.setVisibility(View.GONE);
				sub_text.setVisibility(View.GONE);
				
				// Apply Custom Font to title only
				Typeface boldFont = Typeface.createFromAsset(fragmentContext.getAssets(), "fonts/ooo.ttf");
				title.setTypeface(boldFont, Typeface.BOLD);
				
				materialCardView1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						try {
							String targetId = "";
							String idType = "";
							
							String paxsenixId = currentMovie.containsKey("paxsenix_id") && currentMovie.get("paxsenix_id") != null ? currentMovie.get("paxsenix_id").toString().trim() : "";
							String moviebexId = currentMovie.containsKey("moviebox_id") && currentMovie.get("moviebox_id") != null ? currentMovie.get("moviebox_id").toString().trim() : "";
							
							String mediaType = currentMovie.containsKey("media_type") && currentMovie.get("media_type") != null ? currentMovie.get("media_type").toString().trim() : "movie";
							String intentType = mediaType.equals("tv") ? "series" : "movie";
							
							if (!paxsenixId.isEmpty()) {
								targetId = paxsenixId;
								idType = "paxsenix";
							} else if (!moviebexId.isEmpty()) {
								targetId = moviebexId;
								idType = "moviebox";
							}
							
							if (!targetId.isEmpty()) {
								intent.setClass(fragmentContext, ViewMovieDetailsActivity.class);
								intent.putExtra("id", targetId);
								intent.putExtra("id_type", idType);
								intent.putExtra("type", intentType);
								
								if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
									String transitionName = "poster_expand_" + targetId;
									_view.setTransitionName(transitionName);
									android.app.ActivityOptions options = android.app.ActivityOptions.makeSceneTransitionAnimation(
									hostActivity, _view, transitionName);
									startActivity(intent, options.toBundle());
								} else {
									startActivity(intent);
									hostActivity.overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
								}
							} else {
								SketchwareUtil.showMessage(fragmentContext, "Invalid movie details");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			
			_container.addView(_view);
			return _view;
		}
	}
	
	public class Hm_sec3_data_choices_recAdapter extends RecyclerView.Adapter<Hm_sec3_data_choices_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Hm_sec3_data_choices_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.choices_small_item, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final TextView choice_txt = _view.findViewById(R.id.choice_txt);
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			final HashMap<String, Object> currentGenre = _data.get((int)_position);
			
			if (currentGenre != null && getContext() != null) {
				String genreKey = currentGenre.containsKey("key") && currentGenre.get("key") != null ? currentGenre.get("key").toString() : "";
				String genreTitle = currentGenre.containsKey("title") && currentGenre.get("title") != null ? currentGenre.get("title").toString() : "";
				
				choice_txt.setText(genreTitle);
				
				if (genreKey.equals(selectedGenreKey)) {
					// selected
					choice_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/google.ttf"), 1);
					choice_txt.setTextColor(0xFFEEEEEE);
					choice_txt.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)60, 0xFF28292C));
				} else {
					// unselected
					choice_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/google.ttf"), 0);
					choice_txt.setTextColor(0xFFD2D2D3);
					choice_txt.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)0, Color.TRANSPARENT));
				}
				
				_view.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						_selectGenre(genreKey);
					}
				});
			}
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
	
	public class Hm_sec4_data_choices_recAdapter extends RecyclerView.Adapter<Hm_sec4_data_choices_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Hm_sec4_data_choices_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.choices_small_item, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final TextView choice_txt = _view.findViewById(R.id.choice_txt);
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			final HashMap<String, Object> currentGenre = _data.get((int)_position);
			
			if (currentGenre != null && getContext() != null) {
				String genreKey = currentGenre.containsKey("key") && currentGenre.get("key") != null ? currentGenre.get("key").toString() : "";
				String genreTitle = currentGenre.containsKey("title") && currentGenre.get("title") != null ? currentGenre.get("title").toString() : "";
				
				choice_txt.setText(genreTitle);
				
				if (genreKey.equals(selectedGenreKeySec4)) {
					// selected
					choice_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/google.ttf"), 1);
					choice_txt.setTextColor(0xFFEEEEEE);
					choice_txt.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)60, 0xFF28292C));
				} else {
					// unselected
					choice_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/google.ttf"), 0);
					choice_txt.setTextColor(0xFFD2D2D3);
					choice_txt.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)0, Color.TRANSPARENT));
				}
				
				_view.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						_selectGenreSec4(genreKey);
					}
				});
			}
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
	
	public class Hm_sec1_loading_recAdapter extends RecyclerView.Adapter<Hm_sec1_loading_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Hm_sec1_loading_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.top_picks, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView item_cd = _view.findViewById(R.id.item_cd);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final ImageView banner = _view.findViewById(R.id.banner);
			final TextView title_txt = _view.findViewById(R.id.title_txt);
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
	
	public class Hm_sec1_data_recAdapter extends RecyclerView.Adapter<Hm_sec1_data_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Hm_sec1_data_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.top_picks, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView item_cd = _view.findViewById(R.id.item_cd);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final ImageView banner = _view.findViewById(R.id.banner);
			final TextView title_txt = _view.findViewById(R.id.title_txt);
			
			final java.util.HashMap<String, Object> currentItem = _data.get((int)_position);
			
			if (currentItem != null && getContext() != null && getActivity() != null) {
				final Context fragmentContext = getContext();
				final Activity hostActivity = getActivity();
				
				// 1. Setup Typeface and Layout Parameters
				title_txt.setTypeface(Typeface.createFromAsset(fragmentContext.getAssets(), "fonts/appfont.ttf"), 1);
				
				RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				_view.setLayoutParams(_lp);
				
				// 2. Render Poster Image with Glide (Fragment-safe context)
				String posterUrl = currentItem.containsKey("poster_url") && currentItem.get("poster_url") != null
				? currentItem.get("poster_url").toString()
				: "";
				
				if (!posterUrl.isEmpty()) {
					com.bumptech.glide.Glide.with(fragmentContext)
					.load(android.net.Uri.parse(posterUrl))
					.placeholder(R.drawable.background_banner)
					.error(R.drawable.background_banner)
					.centerCrop()
					.into(banner);
				} else {
					banner.setImageResource(R.drawable.background_banner);
				}
				
				// 3. Bind Title
				title_txt.setText(currentItem.containsKey("title") && currentItem.get("title") != null
				? currentItem.get("title").toString()
				: "Unknown");
				
				title_txt.setSingleLine(true);
				title_txt.setMaxLines(1);
				title_txt.setEllipsize(android.text.TextUtils.TruncateAt.END);
				title_txt.setHorizontallyScrolling(false);
				
				// 4. Click Action Routing into Details fragment/activity
				item_cd.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						try {
							String tmdbId = currentItem.containsKey("tmdb_id") && currentItem.get("tmdb_id") != null ? currentItem.get("tmdb_id").toString().trim() : "";
							String paxsenixId = currentItem.containsKey("paxsenix_id") && currentItem.get("paxsenix_id") != null ? currentItem.get("paxsenix_id").toString().trim() : "";
							String moviebexId = currentItem.containsKey("moviebox_id") && currentItem.get("moviebox_id") != null ? currentItem.get("moviebox_id").toString().trim() : "";
							
							String targetId = "";
							String idType = "";
							
							if (!tmdbId.isEmpty()) {
								targetId = tmdbId;
								idType = "tmdb";
							} else if (!paxsenixId.isEmpty()) {
								targetId = paxsenixId;
								idType = "paxsenix";
							} else if (!moviebexId.isEmpty()) {
								targetId = moviebexId;
								idType = "moviebox";
							}
							
							if (!targetId.isEmpty()) {
								String mediaType = currentItem.containsKey("media_type") && currentItem.get("media_type") != null ? currentItem.get("media_type").toString().trim() : "movie";
								String intentType = "movie";
								if ("series".equalsIgnoreCase(mediaType) || "tv".equalsIgnoreCase(mediaType)) {
									intentType = "series";
								}
								
								intent.setClass(fragmentContext, ViewMovieDetailsActivity.class);
								intent.putExtra("id", targetId);
								intent.putExtra("id_type", idType);
								intent.putExtra("type", intentType);
								
								if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
									String transitionName = "poster_expand_" + targetId;
									_view.setTransitionName(transitionName);
									android.app.ActivityOptions options = android.app.ActivityOptions.makeSceneTransitionAnimation(
									hostActivity, _view, transitionName);
									startActivity(intent, options.toBundle());
								} else {
									startActivity(intent);
									hostActivity.overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
								}
							} else {
								SketchwareUtil.showMessage(fragmentContext, "Invalid movie details");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
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
	
	public class Hm_sec2_loading_recAdapter extends RecyclerView.Adapter<Hm_sec2_loading_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Hm_sec2_loading_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.top_picks, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView item_cd = _view.findViewById(R.id.item_cd);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final ImageView banner = _view.findViewById(R.id.banner);
			final TextView title_txt = _view.findViewById(R.id.title_txt);
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
	
	public class Hm_sec2_data_recAdapter extends RecyclerView.Adapter<Hm_sec2_data_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Hm_sec2_data_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.top_picks, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView item_cd = _view.findViewById(R.id.item_cd);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final ImageView banner = _view.findViewById(R.id.banner);
			final TextView title_txt = _view.findViewById(R.id.title_txt);
			
			final java.util.HashMap<String, Object> currentItem = _data.get((int)_position);
			
			if (currentItem != null && getContext() != null && getActivity() != null) {
				final Context fragmentContext = getContext();
				final Activity hostActivity = getActivity();
				
				title_txt.setTypeface(Typeface.createFromAsset(fragmentContext.getAssets(), "fonts/appfont.ttf"), 1);
				
				RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				_view.setLayoutParams(_lp);
				
				String posterUrl = currentItem.containsKey("poster_url") && currentItem.get("poster_url") != null
				? currentItem.get("poster_url").toString()
				: "";
				
				if (!posterUrl.isEmpty()) {
					com.bumptech.glide.Glide.with(fragmentContext)
					.load(android.net.Uri.parse(posterUrl))
					.placeholder(R.drawable.background_banner)
					.error(R.drawable.background_banner)
					.centerCrop()
					.into(banner);
				} else {
					banner.setImageResource(R.drawable.background_banner);
				}
				
				title_txt.setText(currentItem.containsKey("title") && currentItem.get("title") != null
				? currentItem.get("title").toString()
				: "Unknown");
				
				title_txt.setSingleLine(true);
				title_txt.setMaxLines(1);
				title_txt.setEllipsize(android.text.TextUtils.TruncateAt.END);
				title_txt.setHorizontallyScrolling(false);
				
				item_cd.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						try {
							String tmdbId = currentItem.containsKey("tmdb_id") && currentItem.get("tmdb_id") != null ? currentItem.get("tmdb_id").toString().trim() : "";
							String paxsenixId = currentItem.containsKey("paxsenix_id") && currentItem.get("paxsenix_id") != null ? currentItem.get("paxsenix_id").toString().trim() : "";
							String moviebexId = currentItem.containsKey("moviebox_id") && currentItem.get("moviebox_id") != null ? currentItem.get("moviebox_id").toString().trim() : "";
							
							String targetId = "";
							String idType = "";
							
							if (!tmdbId.isEmpty()) {
								targetId = tmdbId;
								idType = "tmdb";
							} else if (!paxsenixId.isEmpty()) {
								targetId = paxsenixId;
								idType = "paxsenix";
							} else if (!moviebexId.isEmpty()) {
								targetId = moviebexId;
								idType = "moviebox";
							}
							
							if (!targetId.isEmpty()) {
								String mediaType = currentItem.containsKey("media_type") && currentItem.get("media_type") != null ? currentItem.get("media_type").toString().trim() : "movie";
								String intentType = "movie";
								if ("series".equalsIgnoreCase(mediaType) || "tv".equalsIgnoreCase(mediaType)) {
									intentType = "series";
								}
								
								intent.setClass(fragmentContext, ViewMovieDetailsActivity.class);
								intent.putExtra("id", targetId);
								intent.putExtra("id_type", idType);
								intent.putExtra("type", intentType);
								
								if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
									String transitionName = "poster_expand_" + targetId;
									_view.setTransitionName(transitionName);
									android.app.ActivityOptions options = android.app.ActivityOptions.makeSceneTransitionAnimation(
									hostActivity, _view, transitionName);
									startActivity(intent, options.toBundle());
								} else {
									startActivity(intent);
									hostActivity.overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
								}
							} else {
								SketchwareUtil.showMessage(fragmentContext, "Invalid movie details");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
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
	
	public class Hm_sec3_loading_recAdapter extends RecyclerView.Adapter<Hm_sec3_loading_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Hm_sec3_loading_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.top_picks, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView item_cd = _view.findViewById(R.id.item_cd);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final ImageView banner = _view.findViewById(R.id.banner);
			final TextView title_txt = _view.findViewById(R.id.title_txt);
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
	
	public class Hm_sec3_data_recAdapter extends RecyclerView.Adapter<Hm_sec3_data_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Hm_sec3_data_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.top_picks, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView item_cd = _view.findViewById(R.id.item_cd);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final ImageView banner = _view.findViewById(R.id.banner);
			final TextView title_txt = _view.findViewById(R.id.title_txt);
			
			final java.util.HashMap<String, Object> currentItem = _data.get((int)_position);
			
			if (currentItem != null && getContext() != null && getActivity() != null) {
				final Context fragmentContext = getContext();
				final Activity hostActivity = getActivity();
				
				title_txt.setTypeface(Typeface.createFromAsset(fragmentContext.getAssets(), "fonts/appfont.ttf"), 1);
				
				RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				_view.setLayoutParams(_lp);
				
				String posterUrl = currentItem.containsKey("poster_url") && currentItem.get("poster_url") != null
				? currentItem.get("poster_url").toString()
				: "";
				
				if (!posterUrl.isEmpty()) {
					com.bumptech.glide.Glide.with(fragmentContext)
					.load(android.net.Uri.parse(posterUrl))
					.placeholder(R.drawable.background_banner)
					.error(R.drawable.background_banner)
					.centerCrop()
					.into(banner);
				} else {
					banner.setImageResource(R.drawable.background_banner);
				}
				
				title_txt.setText(currentItem.containsKey("title") && currentItem.get("title") != null
				? currentItem.get("title").toString()
				: "Unknown");
				
				title_txt.setSingleLine(true);
				title_txt.setMaxLines(1);
				title_txt.setEllipsize(android.text.TextUtils.TruncateAt.END);
				title_txt.setHorizontallyScrolling(false);
				
				item_cd.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						try {
							String tmdbId = currentItem.containsKey("tmdb_id") && currentItem.get("tmdb_id") != null ? currentItem.get("tmdb_id").toString().trim() : "";
							String paxsenixId = currentItem.containsKey("paxsenix_id") && currentItem.get("paxsenix_id") != null ? currentItem.get("paxsenix_id").toString().trim() : "";
							String moviebexId = currentItem.containsKey("moviebox_id") && currentItem.get("moviebox_id") != null ? currentItem.get("moviebox_id").toString().trim() : "";
							
							String targetId = "";
							String idType = "";
							
							if (!tmdbId.isEmpty()) {
								targetId = tmdbId;
								idType = "tmdb";
							} else if (!paxsenixId.isEmpty()) {
								targetId = paxsenixId;
								idType = "paxsenix";
							} else if (!moviebexId.isEmpty()) {
								targetId = moviebexId;
								idType = "moviebox";
							}
							
							if (!targetId.isEmpty()) {
								String mediaType = currentItem.containsKey("media_type") && currentItem.get("media_type") != null ? currentItem.get("media_type").toString().trim() : "movie";
								String intentType = "movie";
								if ("series".equalsIgnoreCase(mediaType) || "tv".equalsIgnoreCase(mediaType)) {
									intentType = "series";
								}
								
								intent.setClass(fragmentContext, ViewMovieDetailsActivity.class);
								intent.putExtra("id", targetId);
								intent.putExtra("id_type", idType);
								intent.putExtra("type", intentType);
								
								if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
									String transitionName = "poster_expand_" + targetId;
									_view.setTransitionName(transitionName);
									android.app.ActivityOptions options = android.app.ActivityOptions.makeSceneTransitionAnimation(
									hostActivity, _view, transitionName);
									startActivity(intent, options.toBundle());
								} else {
									startActivity(intent);
									hostActivity.overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
								}
							} else {
								SketchwareUtil.showMessage(fragmentContext, "Invalid movie details");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
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
	
	public class Hm_sec4_loading_recAdapter extends RecyclerView.Adapter<Hm_sec4_loading_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Hm_sec4_loading_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.top_picks, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView item_cd = _view.findViewById(R.id.item_cd);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final ImageView banner = _view.findViewById(R.id.banner);
			final TextView title_txt = _view.findViewById(R.id.title_txt);
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
	
	public class Hm_sec4_data_recAdapter extends RecyclerView.Adapter<Hm_sec4_data_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Hm_sec4_data_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.top_picks, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView item_cd = _view.findViewById(R.id.item_cd);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final ImageView banner = _view.findViewById(R.id.banner);
			final TextView title_txt = _view.findViewById(R.id.title_txt);
			
			final java.util.HashMap<String, Object> currentItem = _data.get((int)_position);
			
			if (currentItem != null && getContext() != null && getActivity() != null) {
				final Context fragmentContext = getContext();
				final Activity hostActivity = getActivity();
				
				title_txt.setTypeface(Typeface.createFromAsset(fragmentContext.getAssets(), "fonts/appfont.ttf"), 1);
				
				RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				_view.setLayoutParams(_lp);
				
				String posterUrl = currentItem.containsKey("poster_url") && currentItem.get("poster_url") != null
				? currentItem.get("poster_url").toString()
				: "";
				
				if (!posterUrl.isEmpty()) {
					com.bumptech.glide.Glide.with(fragmentContext)
					.load(android.net.Uri.parse(posterUrl))
					.placeholder(R.drawable.background_banner)
					.error(R.drawable.background_banner)
					.centerCrop()
					.into(banner);
				} else {
					banner.setImageResource(R.drawable.background_banner);
				}
				
				title_txt.setText(currentItem.containsKey("title") && currentItem.get("title") != null
				? currentItem.get("title").toString()
				: "Unknown");
				
				title_txt.setSingleLine(true);
				title_txt.setMaxLines(1);
				title_txt.setEllipsize(android.text.TextUtils.TruncateAt.END);
				title_txt.setHorizontallyScrolling(false);
				
				item_cd.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						try {
							String tmdbId = currentItem.containsKey("tmdb_id") && currentItem.get("tmdb_id") != null ? currentItem.get("tmdb_id").toString().trim() : "";
							String paxsenixId = currentItem.containsKey("paxsenix_id") && currentItem.get("paxsenix_id") != null ? currentItem.get("paxsenix_id").toString().trim() : "";
							String moviebexId = currentItem.containsKey("moviebox_id") && currentItem.get("moviebox_id") != null ? currentItem.get("moviebox_id").toString().trim() : "";
							
							String targetId = "";
							String idType = "";
							
							if (!tmdbId.isEmpty()) {
								targetId = tmdbId;
								idType = "tmdb";
							} else if (!paxsenixId.isEmpty()) {
								targetId = paxsenixId;
								idType = "paxsenix";
							} else if (!moviebexId.isEmpty()) {
								targetId = moviebexId;
								idType = "moviebox";
							}
							
							if (!targetId.isEmpty()) {
								String mediaType = currentItem.containsKey("media_type") && currentItem.get("media_type") != null ? currentItem.get("media_type").toString().trim() : "movie";
								String intentType = "movie";
								if ("series".equalsIgnoreCase(mediaType) || "tv".equalsIgnoreCase(mediaType)) {
									intentType = "series";
								}
								
								intent.setClass(fragmentContext, ViewMovieDetailsActivity.class);
								intent.putExtra("id", targetId);
								intent.putExtra("id_type", idType);
								intent.putExtra("type", intentType);
								
								if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
									String transitionName = "poster_expand_" + targetId;
									_view.setTransitionName(transitionName);
									android.app.ActivityOptions options = android.app.ActivityOptions.makeSceneTransitionAnimation(
									hostActivity, _view, transitionName);
									startActivity(intent, options.toBundle());
								} else {
									startActivity(intent);
									hostActivity.overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
								}
							} else {
								SketchwareUtil.showMessage(fragmentContext, "Invalid movie details");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
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
	
	public class Hm_sec5_loading_recAdapter extends RecyclerView.Adapter<Hm_sec5_loading_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Hm_sec5_loading_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.top_picks, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView item_cd = _view.findViewById(R.id.item_cd);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final ImageView banner = _view.findViewById(R.id.banner);
			final TextView title_txt = _view.findViewById(R.id.title_txt);
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
	
	public class Hm_sec5_data_recAdapter extends RecyclerView.Adapter<Hm_sec5_data_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Hm_sec5_data_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.cus_similar_grid_item, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView item_cd = _view.findViewById(R.id.item_cd);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final ImageView banner = _view.findViewById(R.id.banner);
			final TextView title_txt = _view.findViewById(R.id.title_txt);
			
			final java.util.HashMap<String, Object> currentItem = _data.get((int)_position);
			
			if (currentItem != null && getContext() != null && getActivity() != null) {
				final Context fragmentContext = getContext();
				final Activity hostActivity = getActivity();
				
				title_txt.setTypeface(Typeface.createFromAsset(fragmentContext.getAssets(), "fonts/appfont.ttf"), 1);
				
				RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				_view.setLayoutParams(_lp);
				
				String posterUrl = currentItem.containsKey("poster_url") && currentItem.get("poster_url") != null
				? currentItem.get("poster_url").toString()
				: "";
				
				if (!posterUrl.isEmpty()) {
					com.bumptech.glide.Glide.with(fragmentContext)
					.load(android.net.Uri.parse(posterUrl))
					.placeholder(R.drawable.background_banner)
					.error(R.drawable.background_banner)
					.centerCrop()
					.into(banner);
				} else {
					banner.setImageResource(R.drawable.background_banner);
				}
				
				title_txt.setText(currentItem.containsKey("title") && currentItem.get("title") != null
				? currentItem.get("title").toString()
				: "Unknown");
				
				title_txt.setSingleLine(true);
				title_txt.setMaxLines(1);
				title_txt.setEllipsize(android.text.TextUtils.TruncateAt.END);
				title_txt.setHorizontallyScrolling(false);
				
				item_cd.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						try {
							String tmdbId = currentItem.containsKey("tmdb_id") && currentItem.get("tmdb_id") != null ? currentItem.get("tmdb_id").toString().trim() : "";
							String paxsenixId = currentItem.containsKey("paxsenix_id") && currentItem.get("paxsenix_id") != null ? currentItem.get("paxsenix_id").toString().trim() : "";
							String moviebexId = currentItem.containsKey("moviebox_id") && currentItem.get("moviebox_id") != null ? currentItem.get("moviebox_id").toString().trim() : "";
							
							String targetId = "";
							String idType = "";
							
							if (!tmdbId.isEmpty()) {
								targetId = tmdbId;
								idType = "tmdb";
							} else if (!paxsenixId.isEmpty()) {
								targetId = paxsenixId;
								idType = "paxsenix";
							} else if (!moviebexId.isEmpty()) {
								targetId = moviebexId;
								idType = "moviebox";
							}
							
							if (!targetId.isEmpty()) {
								String mediaType = currentItem.containsKey("media_type") && currentItem.get("media_type") != null ? currentItem.get("media_type").toString().trim() : "movie";
								String intentType = "movie";
								if ("series".equalsIgnoreCase(mediaType) || "tv".equalsIgnoreCase(mediaType)) {
									intentType = "series";
								}
								
								intent.setClass(fragmentContext, ViewMovieDetailsActivity.class);
								intent.putExtra("id", targetId);
								intent.putExtra("id_type", idType);
								intent.putExtra("type", intentType);
								
								if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
									String transitionName = "poster_expand_" + targetId;
									_view.setTransitionName(transitionName);
									android.app.ActivityOptions options = android.app.ActivityOptions.makeSceneTransitionAnimation(
									hostActivity, _view, transitionName);
									startActivity(intent, options.toBundle());
								} else {
									startActivity(intent);
									hostActivity.overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
								}
							} else {
								SketchwareUtil.showMessage(fragmentContext, "Invalid movie details");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
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