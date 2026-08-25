package com.error404.reelix;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import eightbitlab.com.blurview.BlurView;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import androidx.core.view.WindowCompat;

public class ViewMovieDetailsActivity extends AppCompatActivity {
	
	private String fontName = "";
	private String typeace = "";
	private String media_type = "";
	private String request_download_search_url = "";
	private String vttSubtitleContent = "";
	
	private ArrayList<HashMap<String, Object>> stars_list_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> cast_list_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> download_search_result_listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> genres_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> recommendations_map = new ArrayList<>();
	
	private LinearLayout main;
	private NestedScrollView nestedScrollView1;
	private LinearLayout inner_holder;
	private LinearLayout top_header_banner_holder;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private LinearLayout linear15;
	private LinearLayout linear18;
	private LinearLayout linear19;
	private LinearLayout linear20;
	private LinearLayout castandcrewHolder;
	private LinearLayout linear21;
	private RecyclerView toppicks_rec_view;
	private RelativeLayout relativelayout1;
	private ImageView poster_imageview;
	private LinearLayout top_header_overlay_holder;
	private LinearLayout top_overlay_content_holder;
	private LinearLayout bottom_overlay_content_holder;
	private LinearLayout linear13;
	private LinearLayout linear14;
	private BlurView back;
	private LinearLayout linear12;
	private ImageView imageview2;
	private TextView title_textview;
	private TextView main_lang_txt;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear11;
	private LinearLayout play_trailer_btn;
	private LinearLayout date_chip;
	private LinearLayout time_chip;
	private LinearLayout movie_or_tvshows_ship;
	private ImageView imageview3;
	private TextView textview3;
	private TextView year_textview;
	private TextView runtime_textview;
	private TextView type_txt;
	private LinearLayout watch_btn;
	private BlurView play_glass_back;
	private LinearLayout like_btn_holder;
	private TextView view_txt;
	private ImageView imageview4;
	private LinearLayout linear17;
	private ImageView imageview5;
	private ImageView heart_img;
	private TextView textview8;
	private TextView plot_textview;
	private TextView textview10;
	private RecyclerView recyclerview_cast;
	private TextView textview11;
	
	private Intent intent = new Intent();
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	private RequestNetwork get_cast;
	private RequestNetwork.RequestListener _get_cast_request_listener;
	private RequestNetwork get_download_search_list;
	private RequestNetwork.RequestListener _get_download_search_list_request_listener;
	private RequestNetwork check_loacl_sream;
	private RequestNetwork.RequestListener _check_loacl_sream_request_listener;
	private RequestNetwork get_related;
	private RequestNetwork.RequestListener _get_related_request_listener;
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
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view_movie_details);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main = findViewById(R.id.main);
		nestedScrollView1 = findViewById(R.id.nestedScrollView1);
		inner_holder = findViewById(R.id.inner_holder);
		top_header_banner_holder = findViewById(R.id.top_header_banner_holder);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		linear15 = findViewById(R.id.linear15);
		linear18 = findViewById(R.id.linear18);
		linear19 = findViewById(R.id.linear19);
		linear20 = findViewById(R.id.linear20);
		castandcrewHolder = findViewById(R.id.castandcrewHolder);
		linear21 = findViewById(R.id.linear21);
		toppicks_rec_view = findViewById(R.id.toppicks_rec_view);
		relativelayout1 = findViewById(R.id.relativelayout1);
		poster_imageview = findViewById(R.id.poster_imageview);
		top_header_overlay_holder = findViewById(R.id.top_header_overlay_holder);
		top_overlay_content_holder = findViewById(R.id.top_overlay_content_holder);
		bottom_overlay_content_holder = findViewById(R.id.bottom_overlay_content_holder);
		linear13 = findViewById(R.id.linear13);
		linear14 = findViewById(R.id.linear14);
		back = findViewById(R.id.back);
		linear12 = findViewById(R.id.linear12);
		imageview2 = findViewById(R.id.imageview2);
		title_textview = findViewById(R.id.title_textview);
		main_lang_txt = findViewById(R.id.main_lang_txt);
		hscroll1 = findViewById(R.id.hscroll1);
		linear11 = findViewById(R.id.linear11);
		play_trailer_btn = findViewById(R.id.play_trailer_btn);
		date_chip = findViewById(R.id.date_chip);
		time_chip = findViewById(R.id.time_chip);
		movie_or_tvshows_ship = findViewById(R.id.movie_or_tvshows_ship);
		imageview3 = findViewById(R.id.imageview3);
		textview3 = findViewById(R.id.textview3);
		year_textview = findViewById(R.id.year_textview);
		runtime_textview = findViewById(R.id.runtime_textview);
		type_txt = findViewById(R.id.type_txt);
		watch_btn = findViewById(R.id.watch_btn);
		play_glass_back = findViewById(R.id.play_glass_back);
		like_btn_holder = findViewById(R.id.like_btn_holder);
		view_txt = findViewById(R.id.view_txt);
		imageview4 = findViewById(R.id.imageview4);
		linear17 = findViewById(R.id.linear17);
		imageview5 = findViewById(R.id.imageview5);
		heart_img = findViewById(R.id.heart_img);
		textview8 = findViewById(R.id.textview8);
		plot_textview = findViewById(R.id.plot_textview);
		textview10 = findViewById(R.id.textview10);
		recyclerview_cast = findViewById(R.id.recyclerview_cast);
		textview11 = findViewById(R.id.textview11);
		net = new RequestNetwork(this);
		get_cast = new RequestNetwork(this);
		get_download_search_list = new RequestNetwork(this);
		check_loacl_sream = new RequestNetwork(this);
		get_related = new RequestNetwork(this);
		auth = FirebaseAuth.getInstance();
		
		castandcrewHolder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		linear12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				onBackPressed();
			}
		});
		
		play_trailer_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		watch_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (view_txt.getText().toString().equals("Watch")) {
					if (getIntent().hasExtra("id")) {
						String targetId = getIntent().getStringExtra("id");
						String idTypeStr = getIntent().hasExtra("id_type") ? getIntent().getStringExtra("id_type") : "tmdb";
						String mediaType = getIntent().hasExtra("type") ? getIntent().getStringExtra("type") : "movie";
						
						String numericIdType = "3";
						if ("paxsenix".equalsIgnoreCase(idTypeStr)) {
							numericIdType = "2";
						} else if ("default".equalsIgnoreCase(idTypeStr)) {
							numericIdType = "3";
						}
						
						String route = "movie";
						if ("series".equalsIgnoreCase(mediaType) || "tv".equalsIgnoreCase(mediaType)) {
							route = "tv";
						}
						
						String streamUrl = "https://error404-api.vercel.app/api/" + route + "/" + targetId + "?id_type=" + numericIdType;
						
						// Create headers map and put the API key
						HashMap<String, Object> reqHeaders = new HashMap<>();
						reqHeaders.put("x-api-key", "516577400478683");
						check_loacl_sream.setHeaders(reqHeaders);
						
						check_loacl_sream.startRequestNetwork(RequestNetworkController.GET, streamUrl, "", _check_loacl_sream_request_listener);
						_customLoading(true);
					} else {
						com.google.android.material.snackbar.Snackbar.make(main, "Video id not found", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).show();
					}
					
				} else {
					if (view_txt.getText().toString().equals("View seasons") && getIntent().hasExtra("id")) {
						String seriesId = getIntent().getStringExtra("id");
						
						// Safely get id_type or default to tmdb
						String idType = getIntent().hasExtra("id_type") ? getIntent().getStringExtra("id_type") : "tmdb";
						
						TvSeriesListDetailsBottomdialogFragmentActivity bottomSheet = new TvSeriesListDetailsBottomdialogFragmentActivity();
						Bundle args = new Bundle();
						args.putString("id", seriesId);
						args.putString("id_type", idType); // Passing id_type
						
						bottomSheet.setArguments(args);
						bottomSheet.setStyle(androidx.fragment.app.DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme);
						bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
					} else {
						com.google.android.material.snackbar.Snackbar.make(main, "Invalid series id", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
							@Override
							public void onClick(View _view) {
							}
						}).show();
					}
					
				}
			}
		});
		
		play_glass_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!title_textview.getText().toString().equals("Loading....")) {
					try {
						String mediaId = getIntent().getStringExtra("id");
						String mediaType = getIntent().getStringExtra("type");
						String rawIdType = getIntent().hasExtra("id_type") ? getIntent().getStringExtra("id_type") : "tmdb";
						
						if (mediaId == null || mediaId.isEmpty()) {
							SketchwareUtil.showMessage(getApplicationContext(), "Invalid media ID");
							return;
						}
						
						// Map text id_type to expected numerical value
						String apiIdTypeNum = "3"; // Default TMDB
						if ("paxsenix".equalsIgnoreCase(rawIdType) || "2".equals(rawIdType)) {
							apiIdTypeNum = "2";
						} else if ("composite".equalsIgnoreCase(rawIdType) || "default".equalsIgnoreCase(rawIdType) || "1".equals(rawIdType)) {
							apiIdTypeNum = "1";
						}
						
						String apiUrl;
						if (mediaType != null && (mediaType.equalsIgnoreCase("tv") || mediaType.equalsIgnoreCase("series"))) {
							String season = getIntent().getStringExtra("season");
							String episode = getIntent().getStringExtra("episode");
							String s = (season != null && !season.isEmpty()) ? season : "1";
							String e = (episode != null && !episode.isEmpty()) ? episode : "1";
							apiUrl = "https://error404-api.vercel.app/api/tv/" + mediaId + "/" + s + "/" + e + "?id_type=" + apiIdTypeNum;
						} else {
							apiUrl = "https://error404-api.vercel.app/api/movie/" + mediaId + "?id_type=" + apiIdTypeNum;
						}
						
						HashMap<String, Object> reqHeaders = new HashMap<>();
						reqHeaders.put("x-api-key", "516577400478683");
						check_loacl_sream.setHeaders(reqHeaders);
						
						get_download_search_list.startRequestNetwork(RequestNetworkController.GET, apiUrl, "", _get_download_search_list_request_listener);
						_customLoading(true);
						
					} catch (Exception e) {
						e.printStackTrace();
						_customLoading(false);
						SketchwareUtil.showMessage(getApplicationContext(), "Error: " + e.getMessage());
					}
				} else {
					SketchwareUtil.showMessage(getApplicationContext(), "Please wait for content to load");
				}
				
			}
		});
		
		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", _response));
				try {
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					if (responseObj.optBoolean("success", false) && responseObj.has("data")) {
						org.json.JSONObject dataObj = responseObj.getJSONObject("data");
						
						// 1. Extract raw primitive layout detail variables
						String title = dataObj.optString("title", "");
						String overview = dataObj.optString("overview", "No plot overview available.");
						String releaseDate = dataObj.optString("release_date", "");
						String originalLanguage = dataObj.optString("original_language", "en");
						
						// Dynamic string fallback matching for duration states between layouts
						String runtimeStr = "N/A";
						if (!dataObj.isNull("runtime_minutes")) {
							runtimeStr = String.valueOf(dataObj.optInt("runtime_minutes")) + " mins";
						}
						
						// Extract layout context release year from absolute time format
						String releaseYear = "N/A";
						if (!releaseDate.isEmpty() && releaseDate.contains("-")) {
							releaseYear = releaseDate.split("-")[0];
						} else if (!releaseDate.isEmpty()) {
							releaseYear = releaseDate;
						}
						
						// 2. Populate Text Views based on UI references
						title_textview.setText(title);
						plot_textview.setText(overview);
						year_textview.setText(releaseYear);
						runtime_textview.setText(runtimeStr);
						main_lang_txt.setText(originalLanguage.toUpperCase());
						
						// 3. Update the execution intent play mode text label dynamically
						String mediaType = getIntent().getStringExtra("type");
						if ("series".equalsIgnoreCase(mediaType) || "tv".equalsIgnoreCase(mediaType)) {
							view_txt.setText("View seasons");
						} else {
							view_txt.setText("Watch");
						}
						
						// 4. Parse the genres array ([{"id": ..., "name": ...}] objects) into genres_list.
						if (genres_list == null) {
							genres_list = new java.util.ArrayList<java.util.HashMap<String, Object>>();
						} else {
							genres_list.clear();
						}
						
						org.json.JSONArray genresArray = dataObj.optJSONArray("genres");
						
						if (genresArray != null) {
							for (int i = 0; i < genresArray.length(); i++) {
								org.json.JSONObject genreItem = genresArray.optJSONObject(i);
								if (genreItem == null) continue;
								
								java.util.HashMap<String, Object> genreMap = new java.util.HashMap<>();
								// id may be null (e.g. Nollywood, Yoruba - no real TMDB equivalent)
								genreMap.put("id", genreItem.isNull("id") ? "" : String.valueOf(genreItem.optInt("id", 0)));
								genreMap.put("name", genreItem.optString("name", ""));
								genres_list.add(genreMap);
							}
						}
						
						// 5. Load image asset safely via dynamic structural paths
						String posterUrl = dataObj.optString("poster_url", "");
						if (posterUrl.isEmpty()) {
							posterUrl = dataObj.optString("cover_url", "");
						}
						
						if (!posterUrl.isEmpty()) {
							Glide.with(getApplicationContext())
							.load(android.net.Uri.parse(posterUrl))
							.centerCrop()
							.into(poster_imageview);
						}
					} else {
						SketchwareUtil.showMessage(getApplicationContext(), "Failed to read data block details.");
					}
				} catch (Exception e) {
					e.printStackTrace();
					SketchwareUtil.showMessage(getApplicationContext(), "Parsing Error: Failed to unpack source stream data.");
				}
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_get_cast_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				try {
					if (cast_list_map == null) {
						cast_list_map = new java.util.ArrayList<java.util.HashMap<String, Object>>();
					} else {
						cast_list_map.clear();
					}
					
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					
					// Unpack inner dynamic payload content map branch check matching new format configurations
					if (responseObj.optBoolean("success", false) && responseObj.has("data")) {
						org.json.JSONObject dataObj = responseObj.getJSONObject("data");
						
						if (dataObj.has("cast")) {
							org.json.JSONArray castArray = dataObj.getJSONArray("cast");
							
							for (int i = 0; i < castArray.length(); i++) {
								org.json.JSONObject castObj = castArray.getJSONObject(i);
								java.util.HashMap<String, Object> map = new java.util.HashMap<>();
								
								map.put("name", castObj.optString("name", "Unknown Actor"));
								map.put("character", castObj.optString("character", "Unknown Character"));
								map.put("id", castObj.optString("id", "0"));
								
								// Directly pass down structural configuration URL elements natively found inside stream
								String profileUrl = castObj.optString("profile_url", "");
								if (profileUrl.isEmpty() || castObj.isNull("profile_url")) {
									// Fall back to old key or placeholder image context tracking state parameters if empty
									profileUrl = castObj.optString("avatar_url", "");
								}
								
								map.put("profile_url", profileUrl);
								cast_list_map.add(map);
							}
							
							// Bind complete layout payload arrays onto target Recyclerview items
							recyclerview_cast.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getApplicationContext(), androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false));
							recyclerview_cast.setAdapter(new Recyclerview_castAdapter(cast_list_map));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_get_download_search_list_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", _response));
				_customLoading(false);
				
				try {
					String mediaType = getIntent().getStringExtra("type");
					String rawIdType = getIntent().hasExtra("id_type") ? getIntent().getStringExtra("id_type") : "tmdb";
					
					JSONObject jsonResponse = new JSONObject(_response);
					
					boolean success = jsonResponse.optBoolean("success", false);
					if (!success) {
						SketchwareUtil.showMessage(getApplicationContext(), "API returned failure");
						return;
					}
					
					JSONObject data = jsonResponse.getJSONObject("data");
					
					if (mediaType != null && (mediaType.equalsIgnoreCase("tv") || mediaType.equalsIgnoreCase("series"))) {
						Bundle bundle = new Bundle();
						bundle.putString("action", "download");
						bundle.putString("type", "tv");
						
						// Grab the ID natively from data, fallback to intent
						String passedId = data.optString("tmdb_id", "");
						if (passedId.isEmpty() || passedId.equals("null")) passedId = data.optString("paxsenix_id", "");
						
						bundle.putString("id", passedId);
						bundle.putString("id_type", rawIdType);
						bundle.putString("title", data.optString("title", ""));
						
						TvSeriesListDetailsBottomdialogFragmentActivity bottomSheet = new TvSeriesListDetailsBottomdialogFragmentActivity();
						bottomSheet.setArguments(bundle);
						bottomSheet.setStyle(androidx.fragment.app.DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme);
						bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
						
					} else {
						// For Movies: Extract the full "streams" object to pass to the bottom sheet
						JSONObject streamsObj = data.optJSONObject("streams");
						
						if (streamsObj == null || streamsObj.length() == 0) {
							SketchwareUtil.showMessage(getApplicationContext(), "No streams available");
							return;
						}
						
						JSONArray subtitlesArray = data.optJSONArray("subtitles");
						String subtitlesJson = subtitlesArray != null ? subtitlesArray.toString() : "[]";
						
						Bundle bundle = new Bundle();
						bundle.putString("type", "movie");
						bundle.putString("title", data.optString("title", ""));
						bundle.putString("poster", data.optString("poster", ""));
						bundle.putString("streams", streamsObj.toString()); // Passes {server1: [...], server2: [...]}
						bundle.putString("subtitles", subtitlesJson);
						
						DownloadListBottomdialogFragmentActivity bottomSheet = new DownloadListBottomdialogFragmentActivity();
						bottomSheet.setArguments(bundle);
						bottomSheet.setStyle(androidx.fragment.app.DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme);
						bottomSheet.show(getSupportFragmentManager(), "download_bottom_sheet");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					_customLoading(false);
					SketchwareUtil.showMessage(getApplicationContext(), "Failed to parse stream response: " + e.getMessage());
				}
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				_telegramLoaderDialog(false);
				com.google.android.material.snackbar.Snackbar.make(main, _message, com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("Report", new View.OnClickListener(){
					@Override
					public void onClick(View _view) {
						
					}
				}).show();
			}
		};
		
		_check_loacl_sream_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", _response));
				_customLoading(false);
				try {
					// Hand over response execution mapping and Dialog generation to our Java Helper
					com.error404.reelix.StreamDialogManager manager = new com.error404.reelix.StreamDialogManager(ViewMovieDetailsActivity.this, getIntent());
					manager.setGenreAndYearContext(genres_list, year_textview.getText().toString());
					manager.showDialog(_response);
				} catch (Exception e) {
					e.printStackTrace();
					SketchwareUtil.showMessage(getApplicationContext(), "Error initiating stream manager.");
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				_telegramLoaderDialog(false);
				intent.setClass(getApplicationContext(), MoviePlayerPageActivity.class);
				intent.putExtra("id", getIntent().getStringExtra("id"));
				intent.putExtra("type", "movie");
				intent.putExtra("name", title_textview.getText().toString());
				startActivity(intent);
				overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
			}
		};
		
		_get_related_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", _response));
				try {
					if (recommendations_map == null) {
						recommendations_map = new java.util.ArrayList<java.util.HashMap<String, Object>>();
					} else {
						recommendations_map.clear();
					}
					
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					
					if (responseObj.optBoolean("success", false) && responseObj.has("data")) {
						org.json.JSONObject dataObj = responseObj.getJSONObject("data");
						
						if (dataObj.has("results")) {
							org.json.JSONArray resultsArray = dataObj.getJSONArray("results");
							
							for (int i = 0; i < resultsArray.length(); i++) {
								org.json.JSONObject entry = resultsArray.getJSONObject(i);
								java.util.HashMap<String, Object> itemMap = new java.util.HashMap<>();
								
								// Parse basic content detail string keys
								itemMap.put("title", entry.optString("title", "Untitled Content"));
								itemMap.put("media_type", entry.optString("media_type", "movie"));
								
								// Dynamic date string fallback matching detail components look
								String rawDate = entry.optString("release_date", "N/A");
								String structuralYear = "N/A";
								if (!rawDate.isEmpty() && rawDate.contains("-")) {
									structuralYear = rawDate.split("-")[0];
								} else if (!rawDate.isEmpty()) {
									structuralYear = rawDate;
								}
								itemMap.put("release_date", structuralYear);
								
								// Image asset validation checking: Fallback to cover_url if poster_url is missing
								String poster = entry.optString("poster_url", "");
								if (poster.isEmpty() || entry.isNull("poster_url")) {
									poster = entry.optString("cover_url", "");
								}
								itemMap.put("poster_url", poster);
								
								// Smart Identifier routing mapping matrix
								String passedId = "";
								String determinedIdType = "tmdb";
								
								if (entry.has("tmdb_id") && !entry.isNull("tmdb_id") && !entry.optString("tmdb_id", "").isEmpty()) {
									passedId = entry.optString("tmdb_id", "");
									determinedIdType = "tmdb";
								} else if (entry.has("paxsenix_id") && !entry.isNull("paxsenix_id") && !entry.optString("paxsenix_id", "").isEmpty()) {
									passedId = entry.optString("paxsenix_id", "");
									determinedIdType = "paxsenix";
								} else {
									passedId = entry.optString("id", "");
									determinedIdType = "default";
								}
								
								itemMap.put("id", passedId);
								itemMap.put("id_type", determinedIdType);
								
								recommendations_map.add(itemMap);
							}
							
							// Bind content layout arrays safely onto target Recyclerview items
							toppicks_rec_view.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(ViewMovieDetailsActivity.this, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false));
							toppicks_rec_view.setAdapter(new Toppicks_rec_viewAdapter(recommendations_map));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					SketchwareUtil.showMessage(getApplicationContext(), "Recommendations Parse Error: " + e.getMessage());
				}
				
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
		((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", ""));
		final String receivedId = getIntent().getStringExtra("id");
		final String transitionName = "poster_expand_" + (receivedId != null ? receivedId : "");
		
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
			main.setTransitionName(transitionName);
		}
		_changeActivityFont("ooo");
		WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		getWindow().setStatusBarColor(Color.TRANSPARENT);
		play_trailer_btn.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)15, (int)2, 0xFF212121, Color.TRANSPARENT));
		date_chip.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)15, (int)2, 0xFF212121, Color.TRANSPARENT));
		time_chip.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)15, (int)2, 0xFF212121, Color.TRANSPARENT));
		movie_or_tvshows_ship.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)15, (int)2, 0xFF212121, Color.TRANSPARENT));
		// 1. Define shared setup configurations (Declared ONCE)
		android.view.ViewGroup activityContainer = (android.view.ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
		android.graphics.drawable.Drawable windowBackground = getWindow().getDecorView().getBackground();
		float blurRadius = 25f;
		
		// Fetch two distinct ripple instances so both views get their own animation layer
		int[] attrs = new int[]{android.R.attr.selectableItemBackgroundBorderless};
		android.content.res.TypedArray typedArray = obtainStyledAttributes(attrs);
		android.graphics.drawable.Drawable ripple1 = typedArray.getDrawable(0);
		android.graphics.drawable.Drawable ripple2 = typedArray.getDrawable(0);
		typedArray.recycle();
		
		
		// ==========================================
		// 2. Setup blur and ripple on 'back' view
		// ==========================================
		back.setupWith(activityContainer)
		.setFrameClearDrawable(windowBackground)
		.setBlurRadius(blurRadius)
		.setBlurAutoUpdate(true)
		.setOverlayColor(Color.argb(60, 255, 255, 255));
		
		back.setOutlineProvider(new android.view.ViewOutlineProvider() {
			@Override
			public void getOutline(android.view.View view, android.graphics.Outline outline) {
				int radius = Math.min(view.getWidth(), view.getHeight()) / 2;
				outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
			}
		});
		back.setClipToOutline(true);
		back.setForeground(ripple1);
		back.setClickable(true);
		
		
		// ==========================================
		// 3. Setup blur and ripple on 'play_glass_back' view
		// ==========================================
		play_glass_back.setupWith(activityContainer)
		.setFrameClearDrawable(windowBackground)
		.setBlurRadius(blurRadius)
		.setBlurAutoUpdate(true)
		.setOverlayColor(Color.argb(60, 255, 255, 255));
		
		play_glass_back.setOutlineProvider(new android.view.ViewOutlineProvider() {
			@Override
			public void getOutline(android.view.View view, android.graphics.Outline outline) {
				int radius = Math.min(view.getWidth(), view.getHeight()) / 2;
				outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
			}
		});
		play_glass_back.setClipToOutline(true);
		play_glass_back.setForeground(ripple2);
		play_glass_back.setClickable(true);
		
		if (getIntent().hasExtra("type")) {
			media_type = getIntent().getStringExtra("type");
		}
		if (media_type.equals("movie")) {
			type_txt.setText("Movie");
		} else {
			type_txt.setText("TV series");
		}
		_rippleRoundStroke(watch_btn, "#B71C1C", "#EEEEEE", 60, 0, "#000000");
		hscroll1.setHorizontalScrollBarEnabled(false);
		hscroll1.setVerticalScrollBarEnabled(false);
		hscroll1.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
		// 1. Retrieve the parameters passed from the previous intent components
		final String titleId = getIntent().getStringExtra("id");
		final String idTypeStr = getIntent().hasExtra("id_type") ? getIntent().getStringExtra("id_type") : "tmdb";
		final String mediaType = getIntent().getStringExtra("type"); // Expecting "movie" or "series"
		
		// 2. Validate incoming parameters and map payload structures
		if (titleId != null && !titleId.trim().isEmpty() && mediaType != null) {
			
			// Map human-readable id_type tags to endpoint numeric configurations
			String numericIdType = "3"; // Default fallback to TMDB matrix
			if ("paxsenix".equalsIgnoreCase(idTypeStr)) {
				numericIdType = "2";
			} else if ("default".equalsIgnoreCase(idTypeStr)) {
				numericIdType = "3";
			}
			
			// Translate structural layout references to API route segments
			String routeType = "movie";
			if ("series".equalsIgnoreCase(mediaType) || "tv".equalsIgnoreCase(mediaType)) {
				routeType = "tv";
			}
			
			// Formulate final complete microservice query URL segments
			String endpointUrl = "https://error404-api.vercel.app/api/details/" + routeType + "/" + titleId + "?id_type=" + numericIdType;
			String castEndpointUrl = "https://error404-api.vercel.app/api/credits/" + routeType + "/" + titleId + "?id_type=" + numericIdType;
			String recommendationsUrl = "https://error404-api.vercel.app/api/recommendations/" + routeType + "/" + titleId + "?id_type=" + numericIdType;
			
			// Inject application custom auth headers required by the endpoints
			java.util.HashMap<String, Object> connectionHeaders = new java.util.HashMap<>();
			connectionHeaders.put("accept", "application/json");
			connectionHeaders.put("x-api-key", "516577400478683");
			
			// Fire detail collection network configurations
			net.setHeaders(connectionHeaders);
			net.startRequestNetwork(RequestNetworkController.GET, endpointUrl, "", _net_request_listener);
			
			// Fire runtime cast collection network configurations
			get_cast.setHeaders(connectionHeaders);
			get_cast.startRequestNetwork(RequestNetworkController.GET, castEndpointUrl, "", _get_cast_request_listener);
			
			// Fire dynamic recommendations dataset network configurations
			get_related.setHeaders(connectionHeaders);
			get_related.startRequestNetwork(RequestNetworkController.GET, recommendationsUrl, "", _get_related_request_listener);
			
		} else {
			SketchwareUtil.showMessage(getApplicationContext(), "Error: Invalid Details Intent Payload.");
			finish(); 
		}
		
	}
	
	@Override
	public void onBackPressed() {
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
			finishAfterTransition();
		} else {
			finish();
			overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
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
	
	
	public void _telegramLoaderDialog(final boolean _visibility) {
		if (_visibility) {
			if (coreprog == null){
				coreprog = new ProgressDialog(this);
				coreprog.setCancelable(false);
				coreprog.setCanceledOnTouchOutside(false);
				
				coreprog.requestWindowFeature(Window.FEATURE_NO_TITLE);  coreprog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
				
			}
			coreprog.show();
			coreprog.setContentView(R.layout.loading);
			
			
			LinearLayout linear2 = (LinearLayout)coreprog.findViewById(R.id.linear2);
			
			LinearLayout back = (LinearLayout)coreprog.findViewById(R.id.background);
			
			LinearLayout layout_progress = (LinearLayout)coreprog.findViewById(R.id.layout_progress);
			
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(); 
			gd.setColor(Color.parseColor("#BDBDBD")); /* color */
			gd.setCornerRadius(40); /* radius */
			gd.setStroke(0, Color.WHITE); /* stroke heigth and color */
			linear2.setBackground(gd);
			
			RadialProgressView progress = new RadialProgressView(this);
			layout_progress.addView(progress);
		} else {
			if (coreprog != null){
				coreprog.dismiss();
			}
		}
	}
	private ProgressDialog coreprog;
	{
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
	
	
	public void _fetchRawSubtitleVttContent(final String _videoUrl, final String _subtitleUrl) {
		try {
			_customLoading(true);
			RequestNetwork subFetcher = new RequestNetwork(this);
			subFetcher.startRequestNetwork(RequestNetworkController.GET, _subtitleUrl, "", new RequestNetwork.RequestListener() {
				@Override
				public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
					_customLoading(false);
					
					vttSubtitleContent = response; 
					
					intent.setClass(getApplicationContext(), PlayerActivity.class);
					intent.putExtra("link", _videoUrl);
					intent.putExtra("title", title_textview.getText().toString());
					intent.putExtra("type", "movie");
					intent.putExtra("subtitle_data", vttSubtitleContent); 
					startActivity(intent);
					overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
				}
				
				@Override
				public void onErrorResponse(String tag, String message) {
					_customLoading(false);
					intent.setClass(getApplicationContext(), PlayerActivity.class);
					intent.putExtra("link", _videoUrl);
					intent.putExtra("title", title_textview.getText().toString());
					intent.putExtra("type", "movie");
					intent.putExtra("subtitle_data", "");
					startActivity(intent);
					overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			_customLoading(false);
		}
		
	}
	
	public class Toppicks_rec_viewAdapter extends RecyclerView.Adapter<Toppicks_rec_viewAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Toppicks_rec_viewAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
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
			
			if (_data.get((int)_position) != null) {
				final java.util.HashMap<String, Object> currentItem = _data.get((int)_position);
				
				// 1. Setup Typefaces and Layout Parameters
				title_txt.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/appfont.ttf"), 1);
				//date.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ints.ttf"), 0);
				
				RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				_view.setLayoutParams(_lp);
				
				// 3. Render Images with Glide using Activity Context
				String posterUrl = currentItem.containsKey("poster_url") && currentItem.get("poster_url") != null
				? currentItem.get("poster_url").toString()
				: "";
				
				if (!posterUrl.isEmpty()) {
					com.bumptech.glide.Glide.with(ViewMovieDetailsActivity.this)
					.load(android.net.Uri.parse(posterUrl))
					.placeholder(R.drawable.background_banner)
					.error(R.drawable.background_banner)
					.centerCrop()
					.into(banner);
				} else {
					banner.setImageResource(R.drawable.background_banner);
				}
				
				// 4. Bind Text Content Elements
				title_txt.setText(currentItem.containsKey("title") && currentItem.get("title") != null
				? currentItem.get("title").toString()
				: "Unknown");
				
				/* date.setText(currentItem.containsKey("release_date") && currentItem.get("release_date") != null
    ? currentItem.get("release_date").toString()
    : "0000");*/
				
				// Ensure text fits elegantly onto the horizontal list shelf items
				title_txt.setSingleLine(true);
				title_txt.setMaxLines(1);
				title_txt.setEllipsize(android.text.TextUtils.TruncateAt.END);
				title_txt.setHorizontallyScrolling(false);
				
				// 5. Click Action Framework Routing back into Details Engine natively
				item_cd.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						try {
							String targetId = currentItem.containsKey("id") && currentItem.get("id") != null ? currentItem.get("id").toString().trim() : "";
							String idType = currentItem.containsKey("id_type") && currentItem.get("id_type") != null ? currentItem.get("id_type").toString().trim() : "tmdb";
							
							if (!targetId.isEmpty()) {
								String mediaType = currentItem.containsKey("media_type") && currentItem.get("media_type") != null ? currentItem.get("media_type").toString().trim() : "movie";
								
								// Maintain system consistency checking types
								String intentType = "movie";
								if ("series".equalsIgnoreCase(mediaType) || "tv".equalsIgnoreCase(mediaType)) {
									intentType = "series";
								}
								
								Intent nextIntent = new Intent();
								nextIntent.setClass(ViewMovieDetailsActivity.this, ViewMovieDetailsActivity.class);
								nextIntent.putExtra("id", targetId);
								nextIntent.putExtra("id_type", idType);
								nextIntent.putExtra("type", intentType);
								
								if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
									String transitionName = "poster_expand_" + targetId;
									_view.setTransitionName(transitionName);
									android.app.ActivityOptions options = android.app.ActivityOptions.makeSceneTransitionAnimation(
									ViewMovieDetailsActivity.this, _view, transitionName);
									startActivity(nextIntent, options.toBundle());
								} else {
									startActivity(nextIntent);
									overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
								}
							} else {
								SketchwareUtil.showMessage(ViewMovieDetailsActivity.this, "Invalid details");
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
	
	public class Recyclerview_castAdapter extends RecyclerView.Adapter<Recyclerview_castAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview_castAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.circular_cast_cree, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final ImageView actor_image = _view.findViewById(R.id.actor_image);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView character_name = _view.findViewById(R.id.character_name);
			final TextView actor_name = _view.findViewById(R.id.actor_name);
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			if (cast_list_map.get((int)_position) != null) {
				java.util.HashMap<String, Object> actorData = cast_list_map.get((int)_position);
				
				// 1. Bind Real Actor Name
				if (actor_name != null && actorData.containsKey("name")) {
					actor_name.setText(String.valueOf(actorData.get("name")));
				}
				
				// 2. Bind Character Role Name
				if (character_name != null && actorData.containsKey("character")) {
					character_name.setText(String.valueOf(actorData.get("character")));
				}
				
				// 3. Bind Actor Profile Image via Glide 
				if (actor_image != null && actorData.containsKey("profile_url")) {
					String profileUrl = String.valueOf(actorData.get("profile_url"));
					
					if (!profileUrl.trim().isEmpty()) {
						com.bumptech.glide.Glide.with(getApplicationContext())
						.load(android.net.Uri.parse(profileUrl))
						.centerCrop()
						.placeholder(R.drawable.background_banner)
						.into(actor_image);
					} else {
						actor_image.setImageResource(R.drawable.background_banner);
					}
				}
			}
			
			cardview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					intent.setClass(getApplicationContext(), CastDetailsActivity.class);
					intent.putExtra("id", _data.get((int)_position).get("id").toString());
					startActivity(intent);
					overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
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