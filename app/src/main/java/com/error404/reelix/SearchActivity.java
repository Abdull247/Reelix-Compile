package com.error404.reelix;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
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
import android.widget.EditText;
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
import androidx.vectordrawable.*;
import androidx.versionedparcelable.*;
import androidx.viewpager.*;
import com.budiyev.android.codescanner.*;
import com.bumptech.glide.*;
import com.bumptech.glide.gifdecoder.*;
import com.error404.reelix.NotchedBottomNav;
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
import com.google.android.material.card.*;
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
import android.animation.ValueAnimator;
import android.graphics.LinearGradient;
import android.graphics.Color;
import android.graphics.Shader;
import androidx.appcompat.widget.SwitchCompat; // Corrected import
import android.content.res.ColorStateList;
import android.graphics.Color;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class SearchActivity extends AppCompatActivity {
	
	private String fontName = "";
	private String typeace = "";
	private String type = "";
	private double total_pages = 0;
	private double current_page = 0;
	private String search_query = "";
	private HashMap<String, Object> map = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> movie_search_result = new ArrayList<>();
	
	private LinearLayout main;
	private RelativeLayout back_relative;
	private NestedScrollView nestedScrollView1;
	private NotchedBottomNav notchedBottomNav;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private HorizontalScrollView hscroll1;
	private LinearLayout search_body_holder;
	private LinearLayout downloader_layout;
	private ImageView imageview1;
	private TextView app_name;
	private LinearLayout search_body;
	private BlurView search_icon_body;
	private EditText edittext1;
	private LinearLayout linear4;
	private ImageView imageview2;
	private LinearLayout linear5;
	private LinearLayout cat_chip1;
	private LinearLayout cat_chip_2;
	private LinearLayout cat_chip_3;
	private LinearLayout cat_chip_4;
	private LinearLayout cat_chip_5;
	private LinearLayout cat_chip_6;
	private TextView cat_txt1;
	private TextView textview1;
	private TextView textview4;
	private TextView textview5;
	private TextView textview6;
	private TextView textview7;
	private ShimmerFrameLayout loading_hold;
	private RecyclerView search_results_recycular_view;
	private ShimmerFrameLayout load_more_pages_hold;
	private BlurView load_more_btn;
	private LinearLayout linear13;
	private TextView loading_txt;
	private LinearLayout linear17;
	private TextView textview3;
	private LinearLayout linear15;
	private TextView textview2;
	private TextView textview8;
	private TextView textview9;
	private LinearLayout linear18;
	private LinearLayout linear19;
	private TextView textview10;
	private MaterialCardView materialCardView1;
	private MaterialCardView cardview1;
	private TextView textview15;
	private LinearLayout linear20;
	private RelativeLayout relativelayout1;
	private LinearLayout linear21;
	private LinearLayout linear23;
	private ImageView imageview3;
	private TextView textview11;
	private TextView textview12;
	private LinearLayout linear24;
	private RelativeLayout relativelayout2;
	private LinearLayout linear25;
	private LinearLayout linear26;
	private ImageView imageview4;
	private TextView textview13;
	private TextView textview14;
	private LinearLayout main_back;
	private LinearLayout other_back;
	private LinearLayout linear27;
	private LinearLayout linear28;
	private LinearLayout linear29;
	private LinearLayout linear6;
	private ImageView imageview5;
	private ImageView imageview6;
	private ImageView imageview7;
	private ImageView imageview8;
	
	private Intent intent = new Intent();
	private RequestNetwork get_movies;
	private RequestNetwork.RequestListener _get_movies_request_listener;
	private RequestNetwork get_series;
	private RequestNetwork.RequestListener _get_series_request_listener;
	private AlertDialog.Builder dialog;
	private RequestNetwork get_all;
	private RequestNetwork.RequestListener _get_all_request_listener;
	private RequestNetwork custom_download;
	private RequestNetwork.RequestListener _custom_download_request_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.search);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main = findViewById(R.id.main);
		back_relative = findViewById(R.id.back_relative);
		nestedScrollView1 = findViewById(R.id.nestedScrollView1);
		notchedBottomNav = findViewById(R.id.notchedBottomNav);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		hscroll1 = findViewById(R.id.hscroll1);
		search_body_holder = findViewById(R.id.search_body_holder);
		downloader_layout = findViewById(R.id.downloader_layout);
		imageview1 = findViewById(R.id.imageview1);
		app_name = findViewById(R.id.app_name);
		search_body = findViewById(R.id.search_body);
		search_icon_body = findViewById(R.id.search_icon_body);
		edittext1 = findViewById(R.id.edittext1);
		linear4 = findViewById(R.id.linear4);
		imageview2 = findViewById(R.id.imageview2);
		linear5 = findViewById(R.id.linear5);
		cat_chip1 = findViewById(R.id.cat_chip1);
		cat_chip_2 = findViewById(R.id.cat_chip_2);
		cat_chip_3 = findViewById(R.id.cat_chip_3);
		cat_chip_4 = findViewById(R.id.cat_chip_4);
		cat_chip_5 = findViewById(R.id.cat_chip_5);
		cat_chip_6 = findViewById(R.id.cat_chip_6);
		cat_txt1 = findViewById(R.id.cat_txt1);
		textview1 = findViewById(R.id.textview1);
		textview4 = findViewById(R.id.textview4);
		textview5 = findViewById(R.id.textview5);
		textview6 = findViewById(R.id.textview6);
		textview7 = findViewById(R.id.textview7);
		loading_hold = findViewById(R.id.loading_hold);
		search_results_recycular_view = findViewById(R.id.search_results_recycular_view);
		load_more_pages_hold = findViewById(R.id.load_more_pages_hold);
		load_more_btn = findViewById(R.id.load_more_btn);
		linear13 = findViewById(R.id.linear13);
		loading_txt = findViewById(R.id.loading_txt);
		linear17 = findViewById(R.id.linear17);
		textview3 = findViewById(R.id.textview3);
		linear15 = findViewById(R.id.linear15);
		textview2 = findViewById(R.id.textview2);
		textview8 = findViewById(R.id.textview8);
		textview9 = findViewById(R.id.textview9);
		linear18 = findViewById(R.id.linear18);
		linear19 = findViewById(R.id.linear19);
		textview10 = findViewById(R.id.textview10);
		materialCardView1 = findViewById(R.id.materialCardView1);
		cardview1 = findViewById(R.id.cardview1);
		textview15 = findViewById(R.id.textview15);
		linear20 = findViewById(R.id.linear20);
		relativelayout1 = findViewById(R.id.relativelayout1);
		linear21 = findViewById(R.id.linear21);
		linear23 = findViewById(R.id.linear23);
		imageview3 = findViewById(R.id.imageview3);
		textview11 = findViewById(R.id.textview11);
		textview12 = findViewById(R.id.textview12);
		linear24 = findViewById(R.id.linear24);
		relativelayout2 = findViewById(R.id.relativelayout2);
		linear25 = findViewById(R.id.linear25);
		linear26 = findViewById(R.id.linear26);
		imageview4 = findViewById(R.id.imageview4);
		textview13 = findViewById(R.id.textview13);
		textview14 = findViewById(R.id.textview14);
		main_back = findViewById(R.id.main_back);
		other_back = findViewById(R.id.other_back);
		linear27 = findViewById(R.id.linear27);
		linear28 = findViewById(R.id.linear28);
		linear29 = findViewById(R.id.linear29);
		linear6 = findViewById(R.id.linear6);
		imageview5 = findViewById(R.id.imageview5);
		imageview6 = findViewById(R.id.imageview6);
		imageview7 = findViewById(R.id.imageview7);
		imageview8 = findViewById(R.id.imageview8);
		get_movies = new RequestNetwork(this);
		get_series = new RequestNetwork(this);
		dialog = new AlertDialog.Builder(this);
		get_all = new RequestNetwork(this);
		custom_download = new RequestNetwork(this);
		
		search_icon_body.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				search_query = edittext1.getText().toString().trim();
				
				if (search_query.isEmpty()) {
					android.widget.Toast.makeText(getApplicationContext(), "Please enter a search term", android.widget.Toast.LENGTH_SHORT).show();
					return;
				}
				
				if ("Downloader".equalsIgnoreCase(type)) {
					String downloadUrl = search_query;
					boolean isYoutube = downloadUrl.contains("youtube.com") || downloadUrl.contains("youtu.be");
					boolean isTiktok = downloadUrl.contains("tiktok.com");
					
					if (!isYoutube && !isTiktok) {
						android.widget.Toast.makeText(getApplicationContext(), "Please enter a valid YouTube or TikTok URL", android.widget.Toast.LENGTH_SHORT).show();
						return;
					}
					
					_customLoading(true);
					
					java.util.HashMap<String, Object> downloadParams = new java.util.HashMap<>();
					downloadParams.put("url", downloadUrl);
					downloadParams.put("quality", "360");
					
					java.util.HashMap<String, Object> downloadHeaders = new java.util.HashMap<>();
					downloadHeaders.put("x-api-key", "516577400478683");
					downloadHeaders.put("accept", "application/json");
					
					custom_download.setHeaders(downloadHeaders);
					custom_download.setParams(downloadParams, RequestNetworkController.REQUEST_BODY);
					
					if (isYoutube) {
						custom_download.startRequestNetwork(RequestNetworkController.POST, "https://error404-api.vercel.app/api/dl/yt/download", "CustomDownload", _custom_download_request_listener);
					} else {
						custom_download.startRequestNetwork(RequestNetworkController.POST, "https://error404-api.vercel.app/api/dl/tiktok/download", "CustomDownload", _custom_download_request_listener);
					}
					return;
				}
				
				current_page = 1;
				total_pages = 1;
				
				loading_hold.setVisibility(android.view.View.VISIBLE);
				search_results_recycular_view.setVisibility(android.view.View.GONE);
				load_more_btn.setVisibility(android.view.View.GONE);
				load_more_pages_hold.setVisibility(android.view.View.GONE);
				
				java.util.HashMap<String, Object> searchParams = new java.util.HashMap<>();
				searchParams.put("q", search_query);
				searchParams.put("page", String.valueOf((int)current_page));
				
				java.util.HashMap<String, Object> searchHeaders = new java.util.HashMap<>();
				searchHeaders.put("x-api-key", "516577400478683");
				searchHeaders.put("accept", "application/json");
				
				if ("Movies".equalsIgnoreCase(type)) {
					get_movies.setHeaders(searchHeaders);
					get_movies.setParams(searchParams, RequestNetworkController.REQUEST_PARAM);
					get_movies.startRequestNetwork(RequestNetworkController.GET, "https://error404-api.vercel.app/api/search/movie", "MovieSearch", _get_movies_request_listener);
				} else if ("TV".equalsIgnoreCase(type)) {
					get_series.setHeaders(searchHeaders);
					get_series.setParams(searchParams, RequestNetworkController.REQUEST_PARAM);
					get_series.startRequestNetwork(RequestNetworkController.GET, "https://error404-api.vercel.app/api/search/tv", "SeriesSearch", _get_series_request_listener);
				} else {
					get_all.setHeaders(searchHeaders);
					get_all.setParams(searchParams, RequestNetworkController.REQUEST_PARAM);
					get_all.startRequestNetwork(RequestNetworkController.GET, "https://error404-api.vercel.app/api/search", "AllSearch", _get_all_request_listener);
				}
			}
		});
		
		cat_chip1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				android.transition.TransitionManager.beginDelayedTransition((android.view.ViewGroup)cat_chip1.getParent(), 
				new android.transition.AutoTransition().setDuration(250));
				
				_rippleRoundStroke(cat_chip1, "#172944", "#E0E0E0", 60, 0, "#000000");
				_rippleRoundStroke(cat_chip_2, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_3, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_4, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_5, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_6, "#141414", "#E0E0E0", 30, 0, "#000000");
				
				type = "All";
				_TransitionManager(main, 150);
				edittext1.setHint("Search for Movies and TV shows");
				search_body_holder.setVisibility(View.VISIBLE);
				downloader_layout.setVisibility(View.GONE);
			}
		});
		
		cat_chip_2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				android.transition.TransitionManager.beginDelayedTransition((android.view.ViewGroup)cat_chip_2.getParent(), 
				new android.transition.AutoTransition().setDuration(250));
				
				_rippleRoundStroke(cat_chip1, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_2, "#172944", "#E0E0E0", 60, 0, "#000000");
				_rippleRoundStroke(cat_chip_3, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_4, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_5, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_6, "#141414", "#E0E0E0", 30, 0, "#000000");
				
				type = "Movies";
				_TransitionManager(main, 150);
				edittext1.setHint("Search for Movies");
				search_body_holder.setVisibility(View.VISIBLE);
				downloader_layout.setVisibility(View.GONE);
			}
		});
		
		cat_chip_3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				android.transition.TransitionManager.beginDelayedTransition((android.view.ViewGroup)cat_chip_3.getParent(), 
				new android.transition.AutoTransition().setDuration(250));
				
				_rippleRoundStroke(cat_chip1, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_2, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_3, "#172944", "#E0E0E0", 60, 0, "#000000");
				_rippleRoundStroke(cat_chip_4, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_5, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_6, "#141414", "#E0E0E0", 30, 0, "#000000");
				
				type = "TV";
				_TransitionManager(main, 150);
				edittext1.setHint("Search for TV shows");
				search_body_holder.setVisibility(View.VISIBLE);
				downloader_layout.setVisibility(View.GONE);
			}
		});
		
		cat_chip_4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				android.transition.TransitionManager.beginDelayedTransition((android.view.ViewGroup)cat_chip_4.getParent(), 
				new android.transition.AutoTransition().setDuration(250));
				
				_rippleRoundStroke(cat_chip1, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_2, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_3, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_4, "#172944", "#E0E0E0", 60, 0, "#000000");
				_rippleRoundStroke(cat_chip_5, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_6, "#141414", "#E0E0E0", 30, 0, "#000000");
				
				type = "People";
				_TransitionManager(main, 150);
				edittext1.setHint("Search for a Person");
				search_body_holder.setVisibility(View.VISIBLE);
				downloader_layout.setVisibility(View.GONE);
			}
		});
		
		cat_chip_5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				android.transition.TransitionManager.beginDelayedTransition((android.view.ViewGroup)cat_chip_5.getParent(), 
				new android.transition.AutoTransition().setDuration(250));
				
				_rippleRoundStroke(cat_chip1, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_2, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_3, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_4, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_5, "#172944", "#E0E0E0", 60, 0, "#000000");
				_rippleRoundStroke(cat_chip_6, "#141414", "#E0E0E0", 30, 0, "#000000");
				
				type = "Subtitle";
				_TransitionManager(main, 150);
				edittext1.setHint("Search for Subtitles");
				search_body_holder.setVisibility(View.VISIBLE);
				downloader_layout.setVisibility(View.GONE);
			}
		});
		
		cat_chip_6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				android.transition.TransitionManager.beginDelayedTransition((android.view.ViewGroup)cat_chip_6.getParent(), 
				new android.transition.AutoTransition().setDuration(250));
				
				_rippleRoundStroke(cat_chip1, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_2, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_3, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_4, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_5, "#141414", "#E0E0E0", 30, 0, "#000000");
				_rippleRoundStroke(cat_chip_6, "#172944", "#E0E0E0", 60, 0, "#000000");
				
				type = "Downloader";
				_TransitionManager(main, 150);
				edittext1.setHint("Enter Download url");
				search_body_holder.setVisibility(View.GONE);
				downloader_layout.setVisibility(View.VISIBLE);
			}
		});
		
		load_more_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		materialCardView1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		cardview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		linear27.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(imageview5);
				intent.setClass(getApplicationContext(), FeedActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		
		linear29.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(imageview7);
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
				_clickAnimation(imageview8);
				
				intent.setClass(getApplicationContext(), ProfileActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				
				// Apply smooth custom transition
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		
		_get_movies_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				loading_hold.setVisibility(android.view.View.GONE);
				load_more_pages_hold.setVisibility(android.view.View.GONE);
				
				try {
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					
					if (responseObj.optBoolean("success", false) && responseObj.has("data")) {
						org.json.JSONObject dataObj = responseObj.getJSONObject("data");
						org.json.JSONArray resultsArray = dataObj.getJSONArray("results");
						
						total_pages = dataObj.optDouble("total_pages", 1);
						
						if ((int)current_page == 1) {
							movie_search_result.clear();
						}
						
						boolean gotResults = resultsArray.length() > 0;
						
						for (int i = 0; i < resultsArray.length(); i++) {
							org.json.JSONObject item = resultsArray.getJSONObject(i);
							java.util.HashMap<String, Object> map = new java.util.HashMap<>();
							
							map.put("primaryTitle", item.optString("title", "Unknown Movie"));
							map.put("mediaType", "Movie");
							
							String releaseDate = item.optString("release_date", "");
							String year = (releaseDate != null && releaseDate.length() >= 4) ? releaseDate.substring(0, 4) : "N/A";
							map.put("displayYear", year);
							
							map.put("id", item.optString("id", ""));
							map.put("overview", item.optString("overview", ""));
							map.put("poster_url", item.optString("poster_url", ""));
							map.put("backdrop_url", item.optString("backdrop_url", ""));
							map.put("vote_average", item.optDouble("vote_average", 0.0));
							map.put("type", "movie");
							
							boolean hasTmdbId = item.has("tmdb_id") && !item.isNull("tmdb_id");
							boolean hasPaxsenixId = item.has("paxsenix_id") && !item.isNull("paxsenix_id");
							map.put("is_tmdb_id_available", hasTmdbId);
							map.put("is_paxsenix_id_available", hasPaxsenixId);
							map.put("tmdb_id", hasTmdbId ? String.valueOf(item.optLong("tmdb_id")) : "");
							map.put("paxsenix_id", hasPaxsenixId ? item.optString("paxsenix_id", "") : "");
							
							movie_search_result.add(map);
						}
						
						search_results_recycular_view.setVisibility(android.view.View.VISIBLE);
						
						if (search_results_recycular_view.getAdapter() == null || (int)current_page == 1) {
							search_results_recycular_view.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(SearchActivity.this));
							search_results_recycular_view.setAdapter(new Search_results_recycular_viewAdapter(movie_search_result));
						} else {
							search_results_recycular_view.getAdapter().notifyDataSetChanged();
						}
						
						if (gotResults && !movie_search_result.isEmpty()) {
							load_more_btn.setVisibility(android.view.View.VISIBLE);
						} else {
							load_more_btn.setVisibility(android.view.View.GONE);
						}
					} else {
						String serverError = responseObj.optString("message", "Unknown API error");
						com.google.android.material.snackbar.Snackbar.make(main, "API Error: " + serverError, com.google.android.material.snackbar.Snackbar.LENGTH_LONG).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
					android.widget.Toast.makeText(getApplicationContext(), "Error parsing movies: " + e.getMessage(), android.widget.Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				loading_hold.setVisibility(android.view.View.GONE);
				load_more_pages_hold.setVisibility(android.view.View.GONE);
				
				// Safe recovery recovery loop check
				if ((int)current_page < (int)total_pages) {
					load_more_btn.setVisibility(android.view.View.VISIBLE);
				}
				
				final String errorMsg = _message != null ? _message : "Unknown connection error";
				
				com.google.android.material.snackbar.Snackbar.make(main, "Search failed: " + errorMsg, com.google.android.material.snackbar.Snackbar.LENGTH_LONG)
				.setAction("COPY", new android.view.View.OnClickListener() {
					@Override
					public void onClick(android.view.View _view) {
						android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(android.content.Context.CLIPBOARD_SERVICE);
						android.content.ClipData clip = android.content.ClipData.newPlainText("Reelix Error Log", errorMsg);
						if (clipboard != null) {
							clipboard.setPrimaryClip(clip);
							android.widget.Toast.makeText(getApplicationContext(), "Error copied to clipboard", android.widget.Toast.LENGTH_SHORT).show();
						}
					}
				}).show();
				
			}
		};
		
		_get_series_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				loading_hold.setVisibility(android.view.View.GONE);
				load_more_pages_hold.setVisibility(android.view.View.GONE);
				
				try {
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					
					if (responseObj.optBoolean("success", false) && responseObj.has("data")) {
						org.json.JSONObject dataObj = responseObj.getJSONObject("data");
						org.json.JSONArray resultsArray = dataObj.getJSONArray("results");
						
						total_pages = dataObj.optDouble("total_pages", 1);
						
						if ((int)current_page == 1) {
							movie_search_result.clear();
						}
						
						boolean gotResults = resultsArray.length() > 0;
						
						for (int i = 0; i < resultsArray.length(); i++) {
							org.json.JSONObject item = resultsArray.getJSONObject(i);
							java.util.HashMap<String, Object> map = new java.util.HashMap<>();
							
							map.put("primaryTitle", item.optString("title", "Unknown TV Show"));
							map.put("mediaType", "TV Show");
							
							String airDate = item.optString("release_date", "");
							String year = (airDate != null && airDate.length() >= 4) ? airDate.substring(0, 4) : "N/A";
							map.put("displayYear", year);
							
							map.put("id", item.optString("id", ""));
							map.put("overview", item.optString("overview", ""));
							map.put("poster_url", item.optString("poster_url", ""));
							map.put("backdrop_url", item.optString("backdrop_url", ""));
							map.put("vote_average", item.optDouble("vote_average", 0.0));
							map.put("type", "series");
							
							boolean hasTmdbId = item.has("tmdb_id") && !item.isNull("tmdb_id");
							boolean hasPaxsenixId = item.has("paxsenix_id") && !item.isNull("paxsenix_id");
							map.put("is_tmdb_id_available", hasTmdbId);
							map.put("is_paxsenix_id_available", hasPaxsenixId);
							map.put("tmdb_id", hasTmdbId ? String.valueOf(item.optLong("tmdb_id")) : "");
							map.put("paxsenix_id", hasPaxsenixId ? item.optString("paxsenix_id", "") : "");
							
							movie_search_result.add(map);
						}
						
						search_results_recycular_view.setVisibility(android.view.View.VISIBLE);
						
						if (search_results_recycular_view.getAdapter() == null || (int)current_page == 1) {
							search_results_recycular_view.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(SearchActivity.this));
							search_results_recycular_view.setAdapter(new Search_results_recycular_viewAdapter(movie_search_result));
						} else {
							search_results_recycular_view.getAdapter().notifyDataSetChanged();
						}
						
						if (gotResults && !movie_search_result.isEmpty()) {
							load_more_btn.setVisibility(android.view.View.VISIBLE);
						} else {
							load_more_btn.setVisibility(android.view.View.GONE);
						}
						
					} else {
						String serverError = responseObj.optString("message", "Unknown API error");
						com.google.android.material.snackbar.Snackbar.make(main, "API Error: " + serverError, com.google.android.material.snackbar.Snackbar.LENGTH_LONG).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
					android.widget.Toast.makeText(getApplicationContext(), "Error parsing TV shows: " + e.getMessage(), android.widget.Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				loading_hold.setVisibility(android.view.View.GONE);
				load_more_pages_hold.setVisibility(android.view.View.GONE);
				
				// Safe recovery recovery loop check
				if ((int)current_page < (int)total_pages) {
					load_more_btn.setVisibility(android.view.View.VISIBLE);
				}
				
				final String errorMsg = _message != null ? _message : "Unknown connection error";
				
				com.google.android.material.snackbar.Snackbar.make(main, "Search failed: " + errorMsg, com.google.android.material.snackbar.Snackbar.LENGTH_LONG)
				.setAction("COPY", new android.view.View.OnClickListener() {
					@Override
					public void onClick(android.view.View _view) {
						android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(android.content.Context.CLIPBOARD_SERVICE);
						android.content.ClipData clip = android.content.ClipData.newPlainText("Reelix Error Log", errorMsg);
						if (clipboard != null) {
							clipboard.setPrimaryClip(clip);
							android.widget.Toast.makeText(getApplicationContext(), "Error copied to clipboard", android.widget.Toast.LENGTH_SHORT).show();
						}
					}
				}).show();
				
			}
		};
		
		_get_all_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				loading_hold.setVisibility(android.view.View.GONE);
				load_more_pages_hold.setVisibility(android.view.View.GONE);
				
				try {
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					
					if (responseObj.optBoolean("success", false) && responseObj.has("data")) {
						org.json.JSONObject dataObj = responseObj.getJSONObject("data");
						org.json.JSONArray resultsArray = dataObj.getJSONArray("results");
						
						total_pages = dataObj.optDouble("total_pages", 1);
						
						if ((int)current_page == 1) {
							movie_search_result.clear();
						}
						
						boolean gotResults = resultsArray.length() > 0;
						
						for (int i = 0; i < resultsArray.length(); i++) {
							org.json.JSONObject item = resultsArray.getJSONObject(i);
							java.util.HashMap<String, Object> map = new java.util.HashMap<>();
							
							String mediaType = item.optString("media_type", "movie");
							boolean isTv = "tv".equalsIgnoreCase(mediaType);
							
							map.put("primaryTitle", item.optString("title", isTv ? "Unknown TV Show" : "Unknown Movie"));
							map.put("mediaType", isTv ? "TV Show" : "Movie");
							
							String releaseDate = item.optString("release_date", "");
							String year = (releaseDate != null && releaseDate.length() >= 4) ? releaseDate.substring(0, 4) : "N/A";
							map.put("displayYear", year);
							
							map.put("id", item.optString("id", ""));
							map.put("overview", item.optString("overview", ""));
							map.put("poster_url", item.optString("poster_url", ""));
							map.put("backdrop_url", item.optString("backdrop_url", ""));
							map.put("vote_average", item.optDouble("vote_average", 0.0));
							map.put("type", isTv ? "series" : "movie");
							
							boolean hasTmdbId = item.has("tmdb_id") && !item.isNull("tmdb_id");
							boolean hasPaxsenixId = item.has("paxsenix_id") && !item.isNull("paxsenix_id");
							map.put("is_tmdb_id_available", hasTmdbId);
							map.put("is_paxsenix_id_available", hasPaxsenixId);
							map.put("tmdb_id", hasTmdbId ? String.valueOf(item.optLong("tmdb_id")) : "");
							map.put("paxsenix_id", hasPaxsenixId ? item.optString("paxsenix_id", "") : "");
							
							movie_search_result.add(map);
						}
						
						search_results_recycular_view.setVisibility(android.view.View.VISIBLE);
						
						if (search_results_recycular_view.getAdapter() == null || (int)current_page == 1) {
							search_results_recycular_view.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(SearchActivity.this));
							search_results_recycular_view.setAdapter(new Search_results_recycular_viewAdapter(movie_search_result));
						} else {
							search_results_recycular_view.getAdapter().notifyDataSetChanged();
						}
						
						if (gotResults && !movie_search_result.isEmpty()) {
							load_more_btn.setVisibility(android.view.View.VISIBLE);
						} else {
							load_more_btn.setVisibility(android.view.View.GONE);
						}
						
					} else {
						String serverError = responseObj.optString("message", "Unknown API error");
						com.google.android.material.snackbar.Snackbar.make(main, "API Error: " + serverError, com.google.android.material.snackbar.Snackbar.LENGTH_LONG).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
					android.widget.Toast.makeText(getApplicationContext(), "Error parsing search results: " + e.getMessage(), android.widget.Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				loading_hold.setVisibility(android.view.View.GONE);
				load_more_pages_hold.setVisibility(android.view.View.GONE);
				
				// Safe recovery recovery loop check
				if ((int)current_page < (int)total_pages) {
					load_more_btn.setVisibility(android.view.View.VISIBLE);
				}
				
				final String errorMsg = _message != null ? _message : "Unknown connection error";
				
				com.google.android.material.snackbar.Snackbar.make(main, "Search failed: " + errorMsg, com.google.android.material.snackbar.Snackbar.LENGTH_LONG)
				.setAction("COPY", new android.view.View.OnClickListener() {
					@Override
					public void onClick(android.view.View _view) {
						android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(android.content.Context.CLIPBOARD_SERVICE);
						android.content.ClipData clip = android.content.ClipData.newPlainText("Reelix Error Log", errorMsg);
						if (clipboard != null) {
							clipboard.setPrimaryClip(clip);
							android.widget.Toast.makeText(getApplicationContext(), "Error copied to clipboard", android.widget.Toast.LENGTH_SHORT).show();
						}
					}
				}).show();
				
			}
		};
		
		_custom_download_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", _response));
				_customLoading(false);
				
				try {
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					
					if (responseObj.optBoolean("success", false) && responseObj.has("data")) {
						org.json.JSONObject dataObj = responseObj.getJSONObject("data");
						
						SearchCustomDownloaderSheetBottomdialogFragmentActivity bottomSheet = new SearchCustomDownloaderSheetBottomdialogFragmentActivity();
						bottomSheet.setStyle(androidx.fragment.app.DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme);
						
						android.os.Bundle bundle = new android.os.Bundle();
						bundle.putString("title", dataObj.optString("title", ""));
						bundle.putString("type", dataObj.optString("type", ""));
						bundle.putString("format", dataObj.optString("format", ""));
						bundle.putString("quality", dataObj.optString("quality", ""));
						bundle.putString("thumbnail", dataObj.optString("thumbnail", ""));
						bundle.putString("download_url", dataObj.optString("download_url", ""));
						bundle.putString("video_id", dataObj.optString("video_id", ""));
						bundle.putString("duration_seconds", String.valueOf(dataObj.optLong("duration_seconds", 0)));
						bundle.putString("job_id", dataObj.optString("job_id", ""));
						bundle.putString("url", edittext1.getText().toString());
						bottomSheet.setArguments(bundle);
						
						bottomSheet.show(getSupportFragmentManager(), "CustomDownloaderSheet");
					} else {
						String serverError = responseObj.optString("message", "Unknown API error");
						com.google.android.material.snackbar.Snackbar.make(main, "Download Error: " + serverError, com.google.android.material.snackbar.Snackbar.LENGTH_LONG).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
					android.widget.Toast.makeText(getApplicationContext(), "Error parsing download: " + e.getMessage(), android.widget.Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				_customLoading(false);
				
				final String errorMsg = _message != null ? _message : "Unknown connection error";
				
				com.google.android.material.snackbar.Snackbar.make(main, "Download failed: " + errorMsg, com.google.android.material.snackbar.Snackbar.LENGTH_LONG)
				.setAction("COPY", new android.view.View.OnClickListener() {
					@Override
					public void onClick(android.view.View _view) {
						android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(android.content.Context.CLIPBOARD_SERVICE);
						android.content.ClipData clip = android.content.ClipData.newPlainText("Reelix Error Log", errorMsg);
						if (clipboard != null) {
							clipboard.setPrimaryClip(clip);
							android.widget.Toast.makeText(getApplicationContext(), "Error copied to clipboard", android.widget.Toast.LENGTH_SHORT).show();
						}
					}
				}).show();
			}
		};
	}
	
	private void initializeLogic() {
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
		
		_changeActivityFont("ooo");
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			final Window window = SearchActivity.this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(0xFF0B0D0F);
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
		
		
		ViewGroup activityContainer = findViewById(android.R.id.content);
		float blurRadius = 25f;
		Drawable windowBackground = getWindow().getDecorView().getBackground();
		
		// --- BACKGROUNDS & STYLING ---
		search_body.setBackground(new GradientDrawable() { 
			public GradientDrawable getIns(int a, int b) { 
				this.setCornerRadius(a); 
				this.setColor(b); 
				return this; 
			} 
		}.getIns((int)60, 0xFF212121));
		
		// Applied the same GradientDrawable logic to load_more_btn
		load_more_btn.setBackground(new GradientDrawable() { 
			public GradientDrawable getIns(int a, int b) { 
				this.setCornerRadius(a); 
				this.setColor(b); 
				return this; 
			} 
		}.getIns((int)60, 0xFF212121));
		
		
		// --- BLUR CONFIGURATION ---
		// FIX: Removed data types here to prevent duplicate local variable errors
		activityContainer = (android.view.ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
		windowBackground = getWindow().getDecorView().getBackground();
		blurRadius = 25f;
		
		
		// --- RIPPLE DRAWABLES ---
		int[] attrs = new int[]{android.R.attr.selectableItemBackgroundBorderless};
		android.content.res.TypedArray typedArray = obtainStyledAttributes(attrs);
		android.graphics.drawable.Drawable ripple1 = typedArray.getDrawable(0);
		android.graphics.drawable.Drawable ripple2 = typedArray.getDrawable(0);
		typedArray.recycle();
		
		
		// --- SEARCH ICON BODY BLUR & EFFECTS ---
		search_icon_body.setupWith(activityContainer)
		.setFrameClearDrawable(windowBackground)
		.setBlurRadius(blurRadius)
		.setBlurAutoUpdate(true)
		.setOverlayColor(Color.argb(60, 255, 255, 255));
		
		search_icon_body.setOutlineProvider(new android.view.ViewOutlineProvider() {
			@Override
			public void getOutline(android.view.View view, android.graphics.Outline outline) {
				int radius = Math.min(view.getWidth(), view.getHeight()) / 2;
				outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
			}
		});
		search_icon_body.setClipToOutline(true);
		search_icon_body.setForeground(ripple2);
		search_icon_body.setClickable(true);
		
		
		// --- LOAD MORE BTN BLUR & EFFECTS ---
		load_more_btn.setupWith(activityContainer)
		.setFrameClearDrawable(windowBackground)
		.setBlurRadius(blurRadius)
		.setBlurAutoUpdate(true)
		.setOverlayColor(Color.argb(60, 255, 255, 255));
		
		load_more_btn.setOutlineProvider(new android.view.ViewOutlineProvider() {
			@Override
			public void getOutline(android.view.View view, android.graphics.Outline outline) {
				// Explicitly set the corner radius to 60 for the outline provider
				outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 60);
			}
		});
		load_more_btn.setClipToOutline(true);
		load_more_btn.setForeground(ripple1); // Using ripple1 to keep it independent
		load_more_btn.setClickable(true);
		
		edittext1.setSingleLine(true);
		_rippleRoundStroke(cat_chip1, "#172944", "#E0E0E0", 60, 0, "#000000");
		_rippleRoundStroke(cat_chip_2, "#141414", "#E0E0E0", 30, 0, "#000000");
		_rippleRoundStroke(cat_chip_3, "#141414", "#E0E0E0", 30, 0, "#000000");
		_rippleRoundStroke(cat_chip_4, "#141414", "#E0E0E0", 30, 0, "#000000");
		_rippleRoundStroke(cat_chip_5, "#141414", "#E0E0E0", 30, 0, "#000000");
		_rippleRoundStroke(cat_chip_6, "#141414", "#E0E0E0", 30, 0, "#000000");
		type = "All";
		_TransitionManager(main, 150);
		edittext1.setHint("Search for Movies and TV shows");
		search_body_holder.setVisibility(View.VISIBLE);
		downloader_layout.setVisibility(View.GONE);
		loading_hold.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF212121));
		loading_hold.setVisibility(View.GONE);
		load_more_pages_hold.setVisibility(View.GONE);
		load_more_btn.setVisibility(View.GONE);
		hscroll1.setHorizontalScrollBarEnabled(false);
		hscroll1.setVerticalScrollBarEnabled(false);
		hscroll1.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
		search_results_recycular_view.setAdapter(new Search_results_recycular_viewAdapter(movie_search_result));
		search_results_recycular_view.setLayoutManager(new LinearLayoutManager(this));
		app_name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sans.ttf"), 1);
		imageview6.setColorFilter(0xFFE50914, PorterDuff.Mode.MULTIPLY);
		if ((int)current_page < (int)total_pages) {
			current_page++;
			
			load_more_pages_hold.setVisibility(android.view.View.VISIBLE);
			load_more_btn.setVisibility(android.view.View.GONE);
			
			java.util.HashMap<String, Object> loadMoreParams = new java.util.HashMap<>();
			loadMoreParams.put("q", search_query);
			loadMoreParams.put("page", String.valueOf((int)current_page));
			
			java.util.HashMap<String, Object> loadMoreHeaders = new java.util.HashMap<>();
			loadMoreHeaders.put("x-api-key", "516577400478683");
			loadMoreHeaders.put("accept", "application/json");
			
			if ("Movies".equalsIgnoreCase(type)) {
				get_movies.setHeaders(loadMoreHeaders);
				get_movies.setParams(loadMoreParams, RequestNetworkController.REQUEST_PARAM);
				get_movies.startRequestNetwork(RequestNetworkController.GET, "https://error404-api.vercel.app/api/search/movie", "MovieSearch", _get_movies_request_listener);
			} else if ("TV".equalsIgnoreCase(type)) {
				get_series.setHeaders(loadMoreHeaders);
				get_series.setParams(loadMoreParams, RequestNetworkController.REQUEST_PARAM);
				get_series.startRequestNetwork(RequestNetworkController.GET, "https://error404-api.vercel.app/api/search/tv", "SeriesSearch", _get_series_request_listener);
			} else {
				get_all.setHeaders(loadMoreHeaders);
				get_all.setParams(loadMoreParams, RequestNetworkController.REQUEST_PARAM);
				get_all.startRequestNetwork(RequestNetworkController.GET, "https://error404-api.vercel.app/api/search", "AllSearch", _get_all_request_listener);
			}
		}
		
		load_more_btn.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View _view) {
				current_page++;
				
				load_more_pages_hold.setVisibility(android.view.View.VISIBLE);
				load_more_btn.setVisibility(android.view.View.GONE);
				
				java.util.HashMap<String, Object> loadMoreParams = new java.util.HashMap<>();
				loadMoreParams.put("q", search_query);
				loadMoreParams.put("page", String.valueOf((int)current_page));
				
				java.util.HashMap<String, Object> loadMoreHeaders = new java.util.HashMap<>();
				loadMoreHeaders.put("x-api-key", "516577400478683");
				loadMoreHeaders.put("accept", "application/json");
				
				if ("Movies".equalsIgnoreCase(type)) {
					get_movies.setHeaders(loadMoreHeaders);
					get_movies.setParams(loadMoreParams, RequestNetworkController.REQUEST_PARAM);
					get_movies.startRequestNetwork(RequestNetworkController.GET, "https://error404-api.vercel.app/api/search/movie", "MovieSearch", _get_movies_request_listener);
				} else if ("TV".equalsIgnoreCase(type)) {
					get_series.setHeaders(loadMoreHeaders);
					get_series.setParams(loadMoreParams, RequestNetworkController.REQUEST_PARAM);
					get_series.startRequestNetwork(RequestNetworkController.GET, "https://error404-api.vercel.app/api/search/tv", "SeriesSearch", _get_series_request_listener);
				} else {
					get_all.setHeaders(loadMoreHeaders);
					get_all.setParams(loadMoreParams, RequestNetworkController.REQUEST_PARAM);
					get_all.startRequestNetwork(RequestNetworkController.GET, "https://error404-api.vercel.app/api/search", "AllSearch", _get_all_request_listener);
				}
			}
		});
	} // Closes initializeLogic early
	
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
	
	
	public void _TransitionManager(final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration);
		autoTransition.setInterpolator(new android.view.animation.DecelerateInterpolator()); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
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
	
	public class Search_results_recycular_viewAdapter extends RecyclerView.Adapter<Search_results_recycular_viewAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Search_results_recycular_viewAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.vertical_movies_list, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout main_back = _view.findViewById(R.id.main_back);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final de.hdodenhof.circleimageview.CircleImageView circleimageview1 = _view.findViewById(R.id.circleimageview1);
			final TextView title_txt = _view.findViewById(R.id.title_txt);
			final TextView date = _view.findViewById(R.id.date);
			
			// Safe reference to the current item's data map
			java.util.HashMap<String, Object> currentItem = movie_search_result.get((int)_position);
			
			// 1. Apply your custom styling and background structures
			_rippleRoundStroke(main_back, "#141414", "#E0E0E0", 30, 2, "#212121");
			
			// 2. Set custom typography and typefaces
			title_txt.setTypeface(android.graphics.Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 1);
			date.setTypeface(android.graphics.Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 0);
			
			// 3. Bind text properties safely from your mapped parameters
			if (currentItem.containsKey("primaryTitle")) {
				title_txt.setText(String.valueOf(currentItem.get("primaryTitle")));
			} else {
				title_txt.setText("Unknown Title");
			}
			
			if (currentItem.containsKey("displayYear")) {
				date.setText(String.valueOf(currentItem.get("displayYear")));
			} else {
				date.setText("N/A");
			}
			
			// 4. Extract poster URL (already full URL from Error404 API) and load via Glide
			String posterUrl = "";
			if (currentItem.containsKey("poster_url") && currentItem.get("poster_url") != null) {
				posterUrl = String.valueOf(currentItem.get("poster_url"));
			}
			
			if (!posterUrl.isEmpty() && !posterUrl.equals("null")) {
				Glide.with(getApplicationContext()).load(android.net.Uri.parse(posterUrl)).into(circleimageview1);
			} else {
				// Optional fallback image or placeholder if the item doesn't have a poster
				circleimageview1.setImageResource(R.drawable.background_banner);
			}
			main_back.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					java.util.HashMap<String, Object> clickedItem = _data.get((int)_position);
					
					if (clickedItem.containsKey("type")) {
						boolean tmdbAvailable = clickedItem.containsKey("is_tmdb_id_available") && (boolean) clickedItem.get("is_tmdb_id_available");
						boolean paxsenixAvailable = clickedItem.containsKey("is_paxsenix_id_available") && (boolean) clickedItem.get("is_paxsenix_id_available");
						
						String idType;
						String chosenId;
						
						if (tmdbAvailable) {
							idType = "tmdb";
							chosenId = String.valueOf(clickedItem.get("tmdb_id"));
						} else if (paxsenixAvailable) {
							idType = "paxsenix";
							chosenId = String.valueOf(clickedItem.get("paxsenix_id"));
						} else {
							idType = "tmdb";
							chosenId = String.valueOf(clickedItem.get("id"));
						}
						
						intent.setClass(getApplicationContext(), ViewMovieDetailsActivity.class);
						intent.putExtra("type", clickedItem.get("type").toString());
						intent.putExtra("id", chosenId);
						intent.putExtra("id_type", idType);
						startActivity(intent);
						overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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