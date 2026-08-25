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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
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
import com.bumptech.glide.Glide;
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
import com.google.android.material.*;
import com.google.firebase.FirebaseApp;
import com.google.zxing.*;
import com.google.zxing.client.android.*;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class FeedTvshowsFragmentActivity extends Fragment {
	
	private ArrayList<HashMap<String, Object>> tv_shows_trend_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> tv_shows_top_rated_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> tv_shows_live_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> tv_shows_airing_td_map = new ArrayList<>();
	
	private LinearLayout linear1;
	private SwipeRefreshLayout swiperefreshlayout1;
	private NestedScrollView nestedScrollView1;
	private LinearLayout innermain;
	private LinearLayout linear4;
	private LinearLayout rec1_holder;
	private LinearLayout trending_now_loading_holder;
	private LinearLayout linear14;
	private LinearLayout rec2_holder;
	private LinearLayout top_rated_shim_holder;
	private LinearLayout linear19;
	private LinearLayout rec3_holder;
	private LinearLayout live_tv_shows_load_holder;
	private LinearLayout linear24;
	private LinearLayout rec4holder;
	private LinearLayout airing_td_load_holder;
	private TextView textview6;
	private RecyclerView recyclerview1;
	private ShimmerFrameLayout trend_load_shim;
	private LinearLayout linear13;
	private TextView trend_load_txt;
	private TextView textview7;
	private RecyclerView recyclerview2;
	private ShimmerFrameLayout top_rated_shim;
	private LinearLayout linear18;
	private TextView top_rated_load_txt;
	private TextView textview8;
	private RecyclerView recyclerview3;
	private ShimmerFrameLayout live_tv_load_shim;
	private LinearLayout linear23;
	private TextView live_tv_show_load_txt;
	private TextView textview9;
	private RecyclerView recyclerview4;
	private ShimmerFrameLayout airing_today_shim;
	private LinearLayout linear29;
	private TextView airing_td_txt;
	
	private RequestNetwork get_trend_tvshows;
	private RequestNetwork.RequestListener _get_trend_tvshows_request_listener;
	private Intent intent = new Intent();
	private RequestNetwork top_rated_list;
	private RequestNetwork.RequestListener _top_rated_list_request_listener;
	private RequestNetwork get_live_tvshows;
	private RequestNetwork.RequestListener _get_live_tvshows_request_listener;
	private RequestNetwork get_airing_td;
	private RequestNetwork.RequestListener _get_airing_td_request_listener;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.feed_tvshows_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		swiperefreshlayout1 = _view.findViewById(R.id.swiperefreshlayout1);
		nestedScrollView1 = _view.findViewById(R.id.nestedScrollView1);
		innermain = _view.findViewById(R.id.innermain);
		linear4 = _view.findViewById(R.id.linear4);
		rec1_holder = _view.findViewById(R.id.rec1_holder);
		trending_now_loading_holder = _view.findViewById(R.id.trending_now_loading_holder);
		linear14 = _view.findViewById(R.id.linear14);
		rec2_holder = _view.findViewById(R.id.rec2_holder);
		top_rated_shim_holder = _view.findViewById(R.id.top_rated_shim_holder);
		linear19 = _view.findViewById(R.id.linear19);
		rec3_holder = _view.findViewById(R.id.rec3_holder);
		live_tv_shows_load_holder = _view.findViewById(R.id.live_tv_shows_load_holder);
		linear24 = _view.findViewById(R.id.linear24);
		rec4holder = _view.findViewById(R.id.rec4holder);
		airing_td_load_holder = _view.findViewById(R.id.airing_td_load_holder);
		textview6 = _view.findViewById(R.id.textview6);
		recyclerview1 = _view.findViewById(R.id.recyclerview1);
		trend_load_shim = _view.findViewById(R.id.trend_load_shim);
		linear13 = _view.findViewById(R.id.linear13);
		trend_load_txt = _view.findViewById(R.id.trend_load_txt);
		textview7 = _view.findViewById(R.id.textview7);
		recyclerview2 = _view.findViewById(R.id.recyclerview2);
		top_rated_shim = _view.findViewById(R.id.top_rated_shim);
		linear18 = _view.findViewById(R.id.linear18);
		top_rated_load_txt = _view.findViewById(R.id.top_rated_load_txt);
		textview8 = _view.findViewById(R.id.textview8);
		recyclerview3 = _view.findViewById(R.id.recyclerview3);
		live_tv_load_shim = _view.findViewById(R.id.live_tv_load_shim);
		linear23 = _view.findViewById(R.id.linear23);
		live_tv_show_load_txt = _view.findViewById(R.id.live_tv_show_load_txt);
		textview9 = _view.findViewById(R.id.textview9);
		recyclerview4 = _view.findViewById(R.id.recyclerview4);
		airing_today_shim = _view.findViewById(R.id.airing_today_shim);
		linear29 = _view.findViewById(R.id.linear29);
		airing_td_txt = _view.findViewById(R.id.airing_td_txt);
		get_trend_tvshows = new RequestNetwork((Activity) getContext());
		top_rated_list = new RequestNetwork((Activity) getContext());
		get_live_tvshows = new RequestNetwork((Activity) getContext());
		get_airing_td = new RequestNetwork((Activity) getContext());
		
		swiperefreshlayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				if (isAdded()) {
					// 1. Set up TMDB API headers
					java.util.HashMap<String, Object> tmdbHeaders = new java.util.HashMap<>();
					tmdbHeaders.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMDEwNDFlYzgxODlhMDYwODgyM2RlNTg0YjMwNTU2NiIsIm5iZiI6MTc3ODk0MTUxNi42NzIsInN1YiI6IjZhMDg3ZTRjYjExMGNhZWNhMjk1ZGU4ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.UUCDFvZWR-8Mg347tcy4DzI4yF2PHPGJ2E6OtQnw4bw");
					tmdbHeaders.put("accept", "application/json");
					
					get_trend_tvshows.setHeaders(tmdbHeaders);
					top_rated_list.setHeaders(tmdbHeaders);
					get_live_tvshows.setHeaders(tmdbHeaders);
					get_airing_td.setHeaders(tmdbHeaders);
					
					// 2. Dispatch parallel networking requests
					get_trend_tvshows.startRequestNetwork(RequestNetworkController.GET, "https://api.themoviedb.org/3/trending/tv/day?language=en-US", "", _get_trend_tvshows_request_listener);
					top_rated_list.startRequestNetwork(RequestNetworkController.GET, "https://api.themoviedb.org/3/tv/top_rated?language=en-US&page=1", "", _top_rated_list_request_listener);
					get_live_tvshows.startRequestNetwork(RequestNetworkController.GET, "https://api.themoviedb.org/3/tv/on_the_air?language=en-US&page=1", "", _get_live_tvshows_request_listener);
					get_airing_td.startRequestNetwork(RequestNetworkController.GET, "https://api.themoviedb.org/3/tv/airing_today?language=en-US&page=1", "", _get_airing_td_request_listener);
					
					// 3. OFFLINE RECOVERY LAYER: Read data directly from cache right away
					com.error404.reelix.ReelixCacheManager cacheInstance = com.error404.reelix.ReelixCacheManager.getInstance();
					try {
						String cachedTrend = cacheInstance.getResponse("tv_trending");
						String cachedTopRated = cacheInstance.getResponse("tv_top_rated");
						String cachedLive = cacheInstance.getResponse("tv_live");
						String cachedAiring = cacheInstance.getResponse("tv_airing_today");
						
						Context validContext = getContext() != null ? getContext() : getActivity();
						if (validContext != null) {
							
							// Parse Trending Cache
							if (!cachedTrend.isEmpty() && tv_shows_trend_map.isEmpty()) {
								org.json.JSONObject jsonObject = new org.json.JSONObject(cachedTrend);
								org.json.JSONArray results = jsonObject.getJSONArray("results");
								for (int i = 0; i < results.length(); i++) {
									org.json.JSONObject item = results.getJSONObject(i);
									java.util.HashMap<String, Object> map = new java.util.HashMap<>();
									map.put("name", item.optString("name", ""));
									map.put("poster_path", "https://image.tmdb.org/t/p/w500" + item.optString("poster_path", ""));
									map.put("id", String.valueOf(item.optInt("id", 0)));
									map.put("media_type", item.optString("media_type", "tv"));
									map.put("first_air_date", item.optString("first_air_date", ""));
									tv_shows_trend_map.add(map);
								}
								recyclerview1.setLayoutManager(new LinearLayoutManager(validContext, LinearLayoutManager.HORIZONTAL, false));
								recyclerview1.setAdapter(new Recyclerview1Adapter(tv_shows_trend_map));
							}
							
							// Parse Top Rated Cache
							if (!cachedTopRated.isEmpty() && tv_shows_top_rated_map.isEmpty()) {
								org.json.JSONObject jsonObject = new org.json.JSONObject(cachedTopRated);
								org.json.JSONArray results = jsonObject.getJSONArray("results");
								for (int i = 0; i < results.length(); i++) {
									org.json.JSONObject item = results.getJSONObject(i);
									java.util.HashMap<String, Object> map = new java.util.HashMap<>();
									map.put("id", String.valueOf(item.optInt("id", 0)));
									map.put("original_name", item.optString("original_name", ""));
									map.put("first_air_date", item.optString("first_air_date", ""));
									map.put("poster_path", "https://image.tmdb.org/t/p/w500" + item.optString("poster_path", ""));
									tv_shows_top_rated_map.add(map);
								}
								recyclerview2.setLayoutManager(new LinearLayoutManager(validContext, LinearLayoutManager.HORIZONTAL, false));
								recyclerview2.setAdapter(new Recyclerview2Adapter(tv_shows_top_rated_map));
							}
							
							// Parse On The Air / Live Cache
							if (!cachedLive.isEmpty() && tv_shows_live_map.isEmpty()) {
								org.json.JSONObject jsonObject = new org.json.JSONObject(cachedLive);
								org.json.JSONArray FluxResults = jsonObject.getJSONArray("results");
								for (int i = 0; i < FluxResults.length(); i++) {
									org.json.JSONObject item = FluxResults.getJSONObject(i);
									java.util.HashMap<String, Object> map = new java.util.HashMap<>();
									map.put("id", String.valueOf(item.optInt("id", 0)));
									map.put("name", item.optString("name", ""));
									map.put("original_name", item.optString("original_name", ""));
									map.put("first_air_date", item.optString("first_air_date", ""));
									map.put("poster_path", "https://image.tmdb.org/t/p/w500" + item.optString("poster_path", ""));
									tv_shows_live_map.add(map);
								}
								recyclerview3.setLayoutManager(new LinearLayoutManager(validContext, LinearLayoutManager.HORIZONTAL, false));
								recyclerview3.setAdapter(new Recyclerview3Adapter(tv_shows_live_map));
							}
							
							// Parse Airing Today Cache
							if (!cachedAiring.isEmpty() && tv_shows_airing_td_map.isEmpty()) {
								org.json.JSONObject jsonObject = new org.json.JSONObject(cachedAiring);
								org.json.JSONArray results = jsonObject.getJSONArray("results");
								for (int i = 0; i < results.length(); i++) {
									org.json.JSONObject item = results.getJSONObject(i);
									java.util.HashMap<String, Object> map = new java.util.HashMap<>();
									map.put("id", String.valueOf(item.optInt("id", 0)));
									map.put("name", item.optString("name", ""));
									map.put("original_name", item.optString("original_name", ""));
									map.put("first_air_date", item.optString("first_air_date", ""));
									map.put("poster_path", "https://image.tmdb.org/t/p/w500" + item.optString("poster_path", ""));
									tv_shows_airing_td_map.add(map);
								}
								recyclerview4.setLayoutManager(new LinearLayoutManager(validContext, LinearLayoutManager.HORIZONTAL, false));
								recyclerview4.setAdapter(new Recyclerview4Adapter(tv_shows_airing_td_map));
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					// 4. VISIBILITY CONFIGURATION RESOLUTION
					if (!tv_shows_trend_map.isEmpty() || !tv_shows_top_rated_map.isEmpty() || !tv_shows_live_map.isEmpty() || !tv_shows_airing_td_map.isEmpty()) {
						if (!tv_shows_trend_map.isEmpty()) {
							rec1_holder.setVisibility(View.VISIBLE);
							trending_now_loading_holder.setVisibility(View.GONE);
						}
						if (!tv_shows_top_rated_map.isEmpty()) {
							rec2_holder.setVisibility(View.VISIBLE);
							top_rated_shim_holder.setVisibility(View.GONE);
						}
						if (!tv_shows_live_map.isEmpty()) {
							rec3_holder.setVisibility(View.VISIBLE);
							live_tv_shows_load_holder.setVisibility(View.GONE);
						}
						if (!tv_shows_airing_td_map.isEmpty()) {
							rec4holder.setVisibility(View.VISIBLE);
							airing_td_load_holder.setVisibility(View.GONE);
						}
					} else {
						// Fallback: If absolutely nothing is cached, turn on shimmers globally
						rec1_holder.setVisibility(View.GONE);
						trending_now_loading_holder.setVisibility(View.VISIBLE);
						trend_load_shim.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF212121));
						trend_load_txt.setText("Loading content....");
						
						rec2_holder.setVisibility(View.GONE);
						top_rated_shim_holder.setVisibility(View.VISIBLE);
						top_rated_shim.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF212121));
						top_rated_load_txt.setText("Loading content....");
						
						rec3_holder.setVisibility(View.GONE);
						live_tv_shows_load_holder.setVisibility(View.VISIBLE);
						live_tv_load_shim.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF212121));
						live_tv_show_load_txt.setText("Loading content....");
						
						rec4holder.setVisibility(View.GONE);
						airing_td_load_holder.setVisibility(View.VISIBLE);
						airing_today_shim.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF212121));
						airing_td_txt.setText("Loading content....");
					}
				}
				
			}
		});
		
		_get_trend_tvshows_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (!isAdded() || getContext() == null) return;
				try {
					// Cache the fresh network string payload immediately
					com.error404.reelix.ReelixCacheManager.getInstance().saveResponse("tv_trending", _response);
					
					tv_shows_trend_map.clear();
					org.json.JSONObject jsonObject = new org.json.JSONObject(_response);
					org.json.JSONArray results = jsonObject.getJSONArray("results");
					
					for (int i = 0; i < results.length(); i++) {
						org.json.JSONObject item = results.getJSONObject(i);
						java.util.HashMap<String, Object> map = new java.util.HashMap<>();
						map.put("name", item.optString("name", ""));
						map.put("poster_path", "https://image.tmdb.org/t/p/w500" + item.optString("poster_path", ""));
						map.put("id", String.valueOf(item.optInt("id", 0)));
						map.put("media_type", item.optString("media_type", "tv"));
						map.put("first_air_date", item.optString("first_air_date", ""));
						tv_shows_trend_map.add(map);
					}
					
					Context validContext = getContext() != null ? getContext() : getActivity();
					if (validContext != null) {
						recyclerview1.setLayoutManager(new LinearLayoutManager(validContext, LinearLayoutManager.HORIZONTAL, false));
						recyclerview1.setAdapter(new Recyclerview1Adapter(tv_shows_trend_map));
					}
					
					trending_now_loading_holder.setVisibility(View.GONE);
					rec1_holder.setVisibility(View.VISIBLE);
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					swiperefreshlayout1.setRefreshing(false);
				}
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				if (!isAdded() || getContext() == null) return;
				swiperefreshlayout1.setRefreshing(false);
				
				// Fix: Only show error layout if we don't have any cached data visible
				if (tv_shows_trend_map.isEmpty()) {
					trending_now_loading_holder.setVisibility(View.VISIBLE);
					rec1_holder.setVisibility(View.GONE);
					trend_load_txt.setText(_message);
				} else {
					// Keep data visible, just optionally alert or log the error quietly
					trending_now_loading_holder.setVisibility(View.GONE);
					rec1_holder.setVisibility(View.VISIBLE);
				}
				
			}
		};
		
		_top_rated_list_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (!isAdded() || getContext() == null) return;
				try {
					// Cache the fresh network string payload immediately
					com.error404.reelix.ReelixCacheManager.getInstance().saveResponse("tv_top_rated", _response);
					
					tv_shows_top_rated_map.clear();
					org.json.JSONObject jsonObject = new org.json.JSONObject(_response);
					org.json.JSONArray results = jsonObject.getJSONArray("results");
					
					for (int i = 0; i < results.length(); i++) {
						org.json.JSONObject item = results.getJSONObject(i);
						java.util.HashMap<String, Object> map = new java.util.HashMap<>();
						map.put("id", String.valueOf(item.optInt("id", 0)));
						map.put("original_name", item.optString("original_name", ""));
						map.put("first_air_date", item.optString("first_air_date", ""));
						map.put("poster_path", "https://image.tmdb.org/t/p/w500" + item.optString("poster_path", ""));
						tv_shows_top_rated_map.add(map);
					}
					
					Context validContext = getContext() != null ? getContext() : getActivity();
					if (validContext != null) {
						recyclerview2.setLayoutManager(new LinearLayoutManager(validContext, LinearLayoutManager.HORIZONTAL, false));
						recyclerview2.setAdapter(new Recyclerview2Adapter(tv_shows_top_rated_map));
					}
					
					top_rated_shim_holder.setVisibility(View.GONE);
					rec2_holder.setVisibility(View.VISIBLE);
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					swiperefreshlayout1.setRefreshing(false);
				}
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				if (!isAdded() || getContext() == null) return;
				swiperefreshlayout1.setRefreshing(false);
				
				// Fix: Only show error layout if we don't have any cached data visible
				if (tv_shows_top_rated_map.isEmpty()) {
					top_rated_shim_holder.setVisibility(View.VISIBLE);
					rec2_holder.setVisibility(View.GONE);
					top_rated_load_txt.setText(_message);
				} else {
					top_rated_shim_holder.setVisibility(View.GONE);
					rec2_holder.setVisibility(View.VISIBLE);
				}
				
			}
		};
		
		_get_live_tvshows_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (!isAdded() || getContext() == null) return;
				try {
					// Cache the fresh network string payload immediately
					com.error404.reelix.ReelixCacheManager.getInstance().saveResponse("tv_live", _response);
					
					tv_shows_live_map.clear();
					org.json.JSONObject jsonObject = new org.json.JSONObject(_response);
					org.json.JSONArray results = jsonObject.getJSONArray("results");
					
					for (int i = 0; i < results.length(); i++) {
						org.json.JSONObject item = results.getJSONObject(i);
						java.util.HashMap<String, Object> map = new java.util.HashMap<>();
						map.put("id", String.valueOf(item.optInt("id", 0)));
						map.put("name", item.optString("name", ""));
						map.put("original_name", item.optString("original_name", ""));
						map.put("first_air_date", item.optString("first_air_date", ""));
						map.put("poster_path", "https://image.tmdb.org/t/p/w500" + item.optString("poster_path", ""));
						tv_shows_live_map.add(map);
					}
					
					Context validContext = getContext() != null ? getContext() : getActivity();
					if (validContext != null) {
						recyclerview3.setLayoutManager(new LinearLayoutManager(validContext, LinearLayoutManager.HORIZONTAL, false));
						recyclerview3.setAdapter(new Recyclerview3Adapter(tv_shows_live_map));
					}
					
					live_tv_shows_load_holder.setVisibility(View.GONE);
					rec3_holder.setVisibility(View.VISIBLE);
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					swiperefreshlayout1.setRefreshing(false);
				}
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				if (!isAdded() || getContext() == null) return;
				swiperefreshlayout1.setRefreshing(false);
				
				// Fix: Only show error layout if we don't have any cached data visible
				if (tv_shows_live_map.isEmpty()) {
					live_tv_shows_load_holder.setVisibility(View.VISIBLE);
					rec3_holder.setVisibility(View.GONE);
					live_tv_show_load_txt.setText(_message);
				} else {
					live_tv_shows_load_holder.setVisibility(View.GONE);
					rec3_holder.setVisibility(View.VISIBLE);
				}
				
			}
		};
		
		_get_airing_td_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (!isAdded() || getContext() == null) return;
				try {
					// Cache the fresh network string payload immediately
					com.error404.reelix.ReelixCacheManager.getInstance().saveResponse("tv_airing_today", _response);
					
					tv_shows_airing_td_map.clear();
					org.json.JSONObject jsonObject = new org.json.JSONObject(_response);
					org.json.JSONArray results = jsonObject.getJSONArray("results");
					
					for (int i = 0; i < results.length(); i++) {
						org.json.JSONObject item = results.getJSONObject(i);
						java.util.HashMap<String, Object> map = new java.util.HashMap<>();
						map.put("id", String.valueOf(item.optInt("id", 0)));
						map.put("name", item.optString("name", ""));
						map.put("original_name", item.optString("original_name", ""));
						map.put("first_air_date", item.optString("first_air_date", ""));
						map.put("poster_path", "https://image.tmdb.org/t/p/w500" + item.optString("poster_path", ""));
						tv_shows_airing_td_map.add(map);
					}
					
					Context validContext = getContext() != null ? getContext() : getActivity();
					if (validContext != null) {
						recyclerview4.setLayoutManager(new LinearLayoutManager(validContext, LinearLayoutManager.HORIZONTAL, false));
						recyclerview4.setAdapter(new Recyclerview4Adapter(tv_shows_airing_td_map));
					}
					
					airing_td_load_holder.setVisibility(View.GONE);
					rec4holder.setVisibility(View.VISIBLE);
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					swiperefreshlayout1.setRefreshing(false);
				}
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				if (!isAdded() || getContext() == null) return;
				swiperefreshlayout1.setRefreshing(false);
				
				// Fix: Only show error layout if we don't have any cached data visible
				if (tv_shows_airing_td_map.isEmpty()) {
					airing_td_load_holder.setVisibility(View.VISIBLE);
					rec4holder.setVisibility(View.GONE);
					airing_td_txt.setText(_message);
				} else {
					airing_td_load_holder.setVisibility(View.GONE);
					rec4holder.setVisibility(View.VISIBLE);
				}
				
			}
		};
	}
	
	private void initializeLogic() {
		textview6.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/visions.ttf"), 1);
		trend_load_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		textview7.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/visions.ttf"), 1);
		top_rated_load_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		textview8.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/visions.ttf"), 1);
		live_tv_show_load_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		// 1. Initialize custom styling & typography
		/*textview6.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 1);
trend_load_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
textview7.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 1);
top_rated_load_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
textview8.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 1);
live_tv_show_load_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);

// Set up TMDB network configuration
java.util.HashMap<String, Object> tmdbHeaders = new java.util.HashMap<>();
tmdbHeaders.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMDEwNDFlYzgxODlhMDYwODgyM2RlNTg0YjMwNTU2NiIsIm5iZiI6MTc3ODk0MTUxNi42NzIsInN1YiI6IjZhMDg3ZTRjYjExMGNhZWNhMjk1ZGU4ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.UUCDFvZWR-8Mg347tcy4DzI4yF2PHPGJ2E6OtQnw4bw");
tmdbHeaders.put("accept", "application/json");

get_trend_tvshows.setHeaders(tmdbHeaders);
top_rated_list.setHeaders(tmdbHeaders);
get_live_tvshows.setHeaders(tmdbHeaders);
get_airing_td.setHeaders(tmdbHeaders);

// 2. IMMEDIATE OFFLINE RECOVERY LAYER (Runs before layouts alter visibility)
com.error404.reelix.ReelixCacheManager cacheInstance = com.error404.reelix.ReelixCacheManager.getInstance();
try {
    String cachedTrend = cacheInstance.getResponse("tv_trending");
    String cachedTopRated = cacheInstance.getResponse("tv_top_rated");
    String cachedLive = cacheInstance.getResponse("tv_live");
    String cachedAiring = cacheInstance.getResponse("tv_airing_today");
    
    Context validContext = getContext() != null ? getContext() : getActivity();
    if (validContext != null) {
        
        // Parse Trending Cache
        if (!cachedTrend.isEmpty() && tv_shows_trend_map.isEmpty()) {
            org.json.JSONObject jsonObject = new org.json.JSONObject(cachedTrend);
            org.json.JSONArray results = jsonObject.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                org.json.JSONObject item = results.getJSONObject(i);
                java.util.HashMap<String, Object> map = new java.util.HashMap<>();
                map.put("name", item.optString("name", ""));
                map.put("poster_path", "https://image.tmdb.org/t/p/w500" + item.optString("poster_path", ""));
                map.put("id", String.valueOf(item.optInt("id", 0)));
                map.put("media_type", item.optString("media_type", "tv"));
                map.put("first_air_date", item.optString("first_air_date", ""));
                tv_shows_trend_map.add(map);
            }
            recyclerview1.setLayoutManager(new LinearLayoutManager(validContext, LinearLayoutManager.HORIZONTAL, false));
            recyclerview1.setAdapter(new Recyclerview1Adapter(tv_shows_trend_map));
        }
        
        // Parse Top Rated Cache
        if (!cachedTopRated.isEmpty() && tv_shows_top_rated_map.isEmpty()) {
            org.json.JSONObject jsonObject = new org.json.JSONObject(cachedTopRated);
            org.json.JSONArray results = jsonObject.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                org.json.JSONObject item = results.getJSONObject(i);
                java.util.HashMap<String, Object> map = new java.util.HashMap<>();
                map.put("id", String.valueOf(item.optInt("id", 0)));
                map.put("original_name", item.optString("original_name", ""));
                map.put("first_air_date", item.optString("first_air_date", ""));
                map.put("poster_path", "https://image.tmdb.org/t/p/w500" + item.optString("poster_path", ""));
                tv_shows_top_rated_map.add(map);
            }
            recyclerview2.setLayoutManager(new LinearLayoutManager(validContext, LinearLayoutManager.HORIZONTAL, false));
            recyclerview2.setAdapter(new Recyclerview2Adapter(tv_shows_top_rated_map));
        }
        
        // Parse On The Air / Live Cache
        if (!cachedLive.isEmpty() && tv_shows_live_map.isEmpty()) {
            org.json.JSONObject jsonObject = new org.json.JSONObject(cachedLive);
            org.json.JSONArray FluxResults = jsonObject.getJSONArray("results");
            for (int i = 0; i < FluxResults.length(); i++) {
                org.json.JSONObject item = FluxResults.getJSONObject(i);
                java.util.HashMap<String, Object> map = new java.util.HashMap<>();
                map.put("id", String.valueOf(item.optInt("id", 0)));
                map.put("name", item.optString("name", ""));
                map.put("original_name", item.optString("original_name", ""));
                map.put("first_air_date", item.optString("first_air_date", ""));
                map.put("poster_path", "https://image.tmdb.org/t/p/w500" + item.optString("poster_path", ""));
                tv_shows_live_map.add(map);
            }
            recyclerview3.setLayoutManager(new LinearLayoutManager(validContext, LinearLayoutManager.HORIZONTAL, false));
            recyclerview3.setAdapter(new Recyclerview3Adapter(tv_shows_live_map));
        }
        
        // Parse Airing Today Cache
        if (!cachedAiring.isEmpty() && tv_shows_airing_td_map.isEmpty()) {
            org.json.JSONObject jsonObject = new org.json.JSONObject(cachedAiring);
            org.json.JSONArray results = jsonObject.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                org.json.JSONObject item = results.getJSONObject(i);
                java.util.HashMap<String, Object> map = new java.util.HashMap<>();
                map.put("id", String.valueOf(item.optInt("id", 0)));
                map.put("name", item.optString("name", ""));
                map.put("original_name", item.optString("original_name", ""));
                map.put("first_air_date", item.optString("first_air_date", ""));
                map.put("poster_path", "https://image.tmdb.org/t/p/w500" + item.optString("poster_path", ""));
                tv_shows_airing_td_map.add(map);
            }
            recyclerview4.setLayoutManager(new LinearLayoutManager(validContext, LinearLayoutManager.HORIZONTAL, false));
            recyclerview4.setAdapter(new Recyclerview4Adapter(tv_shows_airing_td_map));
        }
    }
} catch (Exception e) {
    e.printStackTrace();
}

// 3. INTELLIGENT VISIBILITY CONFIGURATION 
if (!tv_shows_trend_map.isEmpty() || !tv_shows_top_rated_map.isEmpty() || !tv_shows_live_map.isEmpty() || !tv_shows_airing_td_map.isEmpty()) {
    // Cache available: immediately build layout structure to hide shimmers completely
    if (!tv_shows_trend_map.isEmpty()) {
        rec1_holder.setVisibility(View.VISIBLE);
        trending_now_loading_holder.setVisibility(View.GONE);
    } else {
        rec1_holder.setVisibility(View.GONE);
        trending_now_loading_holder.setVisibility(View.VISIBLE);
        trend_load_txt.setText("Loading content....");
    }
    if (!tv_shows_top_rated_map.isEmpty()) {
        rec2_holder.setVisibility(View.VISIBLE);
        top_rated_shim_holder.setVisibility(View.GONE);
    } else {
        rec2_holder.setVisibility(View.GONE);
        top_rated_shim_holder.setVisibility(View.VISIBLE);
        top_rated_load_txt.setText("Loading content....");
    }
    if (!tv_shows_live_map.isEmpty()) {
        rec3_holder.setVisibility(View.VISIBLE);
        live_tv_shows_load_holder.setVisibility(View.GONE);
    } else {
        rec3_holder.setVisibility(View.GONE);
        live_tv_shows_load_holder.setVisibility(View.VISIBLE);
        live_tv_show_load_txt.setText("Loading content....");
    }
    if (!tv_shows_airing_td_map.isEmpty()) {
        rec4holder.setVisibility(View.VISIBLE);
        airing_td_load_holder.setVisibility(View.GONE);
    } else {
        rec4holder.setVisibility(View.GONE);
        airing_td_load_holder.setVisibility(View.VISIBLE);
        airing_td_txt.setText("Loading content....");
    }
} else {
    // Empty Fallback state: set shimmers up safely
    rec1_holder.setVisibility(View.GONE);
    trending_now_loading_holder.setVisibility(View.VISIBLE);
    trend_load_shim.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF212121));
    trend_load_txt.setText("Loading content....");
    
    rec2_holder.setVisibility(View.GONE);
    top_rated_shim_holder.setVisibility(View.VISIBLE);
    top_rated_shim.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF212121));
    top_rated_load_txt.setText("Loading content....");
    
    rec3_holder.setVisibility(View.GONE);
    live_tv_shows_load_holder.setVisibility(View.VISIBLE);
    live_tv_load_shim.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF212121));
    live_tv_show_load_txt.setText("Loading content....");
    
    rec4holder.setVisibility(View.GONE);
    airing_td_load_holder.setVisibility(View.VISIBLE);
    airing_today_shim.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF212121));
    airing_td_txt.setText("Loading content....");
}

// 4. FIRE COMPONENT WORKERS
swiperefreshlayout1.setRefreshing(true);
get_trend_tvshows.startRequestNetwork(RequestNetworkController.GET, "https://api.themoviedb.org/3/trending/tv/day?language=en-US", "", _get_trend_tvshows_request_listener);
top_rated_list.startRequestNetwork(RequestNetworkController.GET, "https://api.themoviedb.org/3/tv/top_rated?language=en-US&page=1", "", _top_rated_list_request_listener);
get_live_tvshows.startRequestNetwork(RequestNetworkController.GET, "https://api.themoviedb.org/3/tv/on_the_air?language=en-US&page=1", "", _get_live_tvshows_request_listener);
get_airing_td.startRequestNetwork(RequestNetworkController.GET, "https://api.themoviedb.org/3/tv/airing_today?language=en-US&page=1", "", _get_airing_td_request_listener);
*/
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			// 3. Setup Truncation layout parameters
			title_txt.setSingleLine(true);
			title_txt.setMaxLines(1);
			title_txt.setEllipsize(android.text.TextUtils.TruncateAt.END);
			title_txt.setHorizontallyScrolling(false);
			
			title_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ints.ttf"), 0);
			if (_data.get((int)_position).containsKey("name")) {
				title_txt.setText(_data.get((int)_position).get("name").toString());
			} else {
				title_txt.setText("Unknown");
			}
			if (_data.get((int)_position).containsKey("poster_path")) {
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("poster_path").toString())).into(banner);
			} else {
				banner.setImageResource(R.drawable.background_banner);
			}
			item_cd.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (_data.get((int)_position).containsKey("id") && _data.get((int)_position).get("id") != null) {
						intent.setClass(requireContext(), ViewMovieDetailsActivity.class);
						intent.putExtra("id", _data.get((int)_position).get("id").toString());
						intent.putExtra("type", "series");
						startActivity(intent);
						requireActivity().overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
					} else {
						SketchwareUtil.showMessage(getContext().getApplicationContext(), "Invalid show details");
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
	
	public class Recyclerview2Adapter extends RecyclerView.Adapter<Recyclerview2Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			// 3. Setup Truncation layout parameters
			title_txt.setSingleLine(true);
			title_txt.setMaxLines(1);
			title_txt.setEllipsize(android.text.TextUtils.TruncateAt.END);
			title_txt.setHorizontallyScrolling(false);
			title_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ints.ttf"), 0);
			if (_data.get((int)_position).containsKey("original_name")) {
				title_txt.setText(_data.get((int)_position).get("original_name").toString());
			} else {
				title_txt.setText("Unknown");
			}
			if (_data.get((int)_position).containsKey("poster_path")) {
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("poster_path").toString())).into(banner);
			} else {
				banner.setImageResource(R.drawable.background_banner);
			}
			item_cd.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (_data.get((int)_position).containsKey("id") && _data.get((int)_position).get("id") != null) {
						intent.setClass(requireContext(), ViewMovieDetailsActivity.class);
						intent.putExtra("id", _data.get((int)_position).get("id").toString());
						intent.putExtra("type", "series");
						startActivity(intent);
						requireActivity().overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
					} else {
						SketchwareUtil.showMessage(getContext().getApplicationContext(), "Invalid show details");
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
	
	public class Recyclerview3Adapter extends RecyclerView.Adapter<Recyclerview3Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview3Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			// 3. Setup Truncation layout parameters
			title_txt.setSingleLine(true);
			title_txt.setMaxLines(1);
			title_txt.setEllipsize(android.text.TextUtils.TruncateAt.END);
			title_txt.setHorizontallyScrolling(false);
			title_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ints.ttf"), 0);
			if (_data.get((int)_position).containsKey("name")) {
				title_txt.setText(_data.get((int)_position).get("name").toString());
			} else {
				title_txt.setText("Unknown");
			}
			if (_data.get((int)_position).containsKey("poster_path")) {
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("poster_path").toString())).into(banner);
			} else {
				banner.setImageResource(R.drawable.background_banner);
			}
			item_cd.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (_data.get((int)_position).containsKey("id") && _data.get((int)_position).get("id") != null) {
						intent.setClass(requireContext(), ViewMovieDetailsActivity.class);
						intent.putExtra("id", _data.get((int)_position).get("id").toString());
						intent.putExtra("type", "series");
						startActivity(intent);
						requireActivity().overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
					} else {
						SketchwareUtil.showMessage(getContext().getApplicationContext(), "Invalid show details");
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
	
	public class Recyclerview4Adapter extends RecyclerView.Adapter<Recyclerview4Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview4Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			// 3. Setup Truncation layout parameters
			title_txt.setSingleLine(true);
			title_txt.setMaxLines(1);
			title_txt.setEllipsize(android.text.TextUtils.TruncateAt.END);
			title_txt.setHorizontallyScrolling(false);
			title_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ints.ttf"), 0);
			if (_data.get((int)_position).containsKey("name")) {
				title_txt.setText(_data.get((int)_position).get("name").toString());
			} else {
				title_txt.setText("Unknown");
			}
			if (_data.get((int)_position).containsKey("poster_path")) {
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("poster_path").toString())).into(banner);
			} else {
				banner.setImageResource(R.drawable.background_banner);
			}
			item_cd.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (_data.get((int)_position).containsKey("id") && _data.get((int)_position).get("id") != null) {
						intent.setClass(requireContext(), ViewMovieDetailsActivity.class);
						intent.putExtra("id", _data.get((int)_position).get("id").toString());
						intent.putExtra("type", "series");
						startActivity(intent);
						requireActivity().overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
					} else {
						SketchwareUtil.showMessage(getContext().getApplicationContext(), "Invalid show details");
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