package com.error404.reelix;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import com.google.android.material.*;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
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
import android.content.ClipboardManager;
import android.content.ClipData;

public class TvSeriesListDetailsBottomdialogFragmentActivity extends BottomSheetDialogFragment {
	
	private double ep_position = 0;
	private String action_st = "";
	private String type_st = "";
	private String seriesIdTarget = "";
	
	private ArrayList<HashMap<String, Object>> season_list_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> episodes_list_map = new ArrayList<>();
	
	private LinearLayout main;
	private NestedScrollView nestedScrollView1;
	private LinearLayout topper_back;
	private LinearLayout linear3;
	private LinearLayout linear13;
	private ViewPager viewpager1;
	private LinearLayout divider;
	private LinearLayout season_overview_holder;
	private LinearLayout linear14;
	private LinearLayout linear15;
	private LinearLayout episodes_rec_list;
	private TextView textview1;
	private BlurView close;
	private LinearLayout linear12;
	private ImageView imageview2;
	private TextView overview_header;
	private TextView overview_txt;
	private TextView textview2;
	private TextView season_count;
	private RecyclerView recyclerview1;
	private ShimmerFrameLayout loading_shim;
	
	private RequestNetwork get_seasons;
	private RequestNetwork.RequestListener _get_seasons_request_listener;
	private RequestNetwork get_episodes;
	private RequestNetwork.RequestListener _get_episodes_request_listener;
	private Intent intent = new Intent();
	private RequestNetwork get_episode_stream;
	private RequestNetwork.RequestListener _get_episode_stream_request_listener;
	private RequestNetwork get_tv_epi_stream;
	private RequestNetwork.RequestListener _get_tv_epi_stream_request_listener;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.tv_series_list_details_bottomdialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		main = _view.findViewById(R.id.main);
		nestedScrollView1 = _view.findViewById(R.id.nestedScrollView1);
		topper_back = _view.findViewById(R.id.topper_back);
		linear3 = _view.findViewById(R.id.linear3);
		linear13 = _view.findViewById(R.id.linear13);
		viewpager1 = _view.findViewById(R.id.viewpager1);
		divider = _view.findViewById(R.id.divider);
		season_overview_holder = _view.findViewById(R.id.season_overview_holder);
		linear14 = _view.findViewById(R.id.linear14);
		linear15 = _view.findViewById(R.id.linear15);
		episodes_rec_list = _view.findViewById(R.id.episodes_rec_list);
		textview1 = _view.findViewById(R.id.textview1);
		close = _view.findViewById(R.id.close);
		linear12 = _view.findViewById(R.id.linear12);
		imageview2 = _view.findViewById(R.id.imageview2);
		overview_header = _view.findViewById(R.id.overview_header);
		overview_txt = _view.findViewById(R.id.overview_txt);
		textview2 = _view.findViewById(R.id.textview2);
		season_count = _view.findViewById(R.id.season_count);
		recyclerview1 = _view.findViewById(R.id.recyclerview1);
		loading_shim = _view.findViewById(R.id.loading_shim);
		get_seasons = new RequestNetwork((Activity) getContext());
		get_episodes = new RequestNetwork((Activity) getContext());
		get_episode_stream = new RequestNetwork((Activity) getContext());
		get_tv_epi_stream = new RequestNetwork((Activity) getContext());
		
		viewpager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageSelected(int _position) {
				try {
					if (season_list_map != null && season_list_map.size() > _position) {
						
						String targetSeason = String.valueOf(season_list_map.get((int)_position).get("season_number"));
						ep_position = Double.parseDouble(targetSeason);
						
						if (getActivity() != null && getActivity().getIntent() != null) {
							String seriesId = getActivity().getIntent().getStringExtra("id");
							
							// 1. Show loading shimmer and clear previous items on swipe
							if (loading_shim != null) {
								loading_shim.setVisibility(android.view.View.VISIBLE);
							}
							if (episodes_list_map != null) {
								episodes_list_map.clear();
								if (recyclerview1.getAdapter() != null) {
									recyclerview1.getAdapter().notifyDataSetChanged();
								}
							}
							
							// 2. Trigger network call using the Helper
							String episodesUrl = com.error404.reelix.EpisodesHelper.getEndpointUrl(seriesId, targetSeason);
							get_episodes.setHeaders(com.error404.reelix.EpisodesHelper.getTmdbHeaders());
							get_episodes.startRequestNetwork(RequestNetworkController.GET, episodesUrl, "", _get_episodes_request_listener);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void onPageScrollStateChanged(int _scrollState) {
				
			}
		});
		
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dismiss();
			}
		});
		
		_get_seasons_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				try {
					// 1. Initialize or wipe the data container clear
					if (season_list_map == null) {
						season_list_map = new java.util.ArrayList<java.util.HashMap<String, Object>>();
					} else {
						season_list_map.clear();
					}
					
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					
					// EXTRACT MAIN OVERVIEW: Set the TV series description to overview_txt
					if (overview_txt != null) {
						overview_txt.setText(responseObj.optString("overview", "No overview available."));
					}
					
					if (responseObj.has("seasons")) {
						org.json.JSONArray seasonsArray = responseObj.getJSONArray("seasons");
						String imageBaseUrl = "https://image.tmdb.org/t/p/w500";
						
						for (int i = 0; i < seasonsArray.length(); i++) {
							org.json.JSONObject seasonObj = seasonsArray.getJSONObject(i);
							
							// Optional filter: Skip "Season 0" Specials if you only want official main seasons
							int seasonNumber = seasonObj.optInt("season_number", -1);
							if (seasonNumber == 0) {
								continue; 
							} else {
								season_count.setText(String.valueOf((long)(seasonNumber)));
							}
							
							java.util.HashMap<String, Object> map = new java.util.HashMap<>();
							
							// Extract the core strings requested
							map.put("title", seasonObj.optString("name", "Unknown Season"));
							map.put("date", seasonObj.optString("air_date", "Release Date TBD"));
							map.put("id", String.valueOf(seasonObj.optLong("id", 0)));
							map.put("season_number", String.valueOf(seasonNumber));
							
							// Construct the complete image path resolution
							String posterPath = seasonObj.optString("poster_path", "");
							if (!posterPath.isEmpty() && !seasonObj.isNull("poster_path")) {
								map.put("image_url", imageBaseUrl + posterPath);
							} else {
								map.put("image_url", "");
							}
							
							season_list_map.add(map);
						}
						
						// 2. Bind the updated list data straight to your ViewPager
						viewpager1.setAdapter(new Viewpager1Adapter(season_list_map));
						
					} else {
						android.widget.Toast.makeText(getContext().getApplicationContext(), "No seasons data returned from server.", android.widget.Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					if (getContext() != null) {
						e.printStackTrace();
						android.widget.Toast.makeText(getContext(), "Parsing Error: " + e.getMessage(), android.widget.Toast.LENGTH_SHORT).show();
					}
				}
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_get_episodes_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				try {
					if (loading_shim != null) {
						loading_shim.setVisibility(android.view.View.GONE);
						recyclerview1.setVisibility(android.view.View.VISIBLE);
					}
					
					if (episodes_list_map == null) {
						episodes_list_map = new java.util.ArrayList<java.util.HashMap<String, Object>>();
					} else {
						episodes_list_map.clear();
					}
					
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					
					if (responseObj.has("episodes")) {
						org.json.JSONArray episodesArray = responseObj.getJSONArray("episodes");
						String imageBaseUrl = "https://image.tmdb.org/t/p/w300"; 
						
						for (int i = 0; i < episodesArray.length(); i++) {
							org.json.JSONObject epObj = episodesArray.getJSONObject(i);
							java.util.HashMap<String, Object> map = new java.util.HashMap<>();
							
							// Extracting everything needed (keeping episode_number for no use right now)
							map.put("id", String.valueOf(epObj.optLong("id", 0)));
							map.put("name", epObj.optString("name", "No Title"));
							map.put("episode_number", String.valueOf(epObj.optInt("episode_number", 0)));
							map.put("runtime", epObj.optInt("runtime", 0) + "m");
							map.put("air_date", epObj.optString("air_date", "N/A")); // Extracted air_date
							
							String stillPath = epObj.optString("still_path", "");
							if (!stillPath.isEmpty() && !epObj.isNull("still_path")) {
								map.put("thumbnail_url", imageBaseUrl + stillPath);
							} else {
								map.put("thumbnail_url", "");
							}
							
							episodes_list_map.add(map);
						}
						
						// Bind to RecyclerView
						recyclerview1.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getContext()));
						recyclerview1.setAdapter(new Recyclerview1Adapter(episodes_list_map));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				// Hide loading shimmer even if the request fails
				if (loading_shim != null) {
					loading_shim.setVisibility(android.view.View.GONE);
				}
				
				if (getContext() != null && isAdded()) {
					// Fixed typo: changed _mesage to _message
					android.widget.Toast.makeText(getContext(), "Error: " + _message, android.widget.Toast.LENGTH_SHORT).show();
				}
				
			}
		};
		
		_get_episode_stream_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				ReelixToast.cancel();
				try {
					JSONObject jsonResponse = new JSONObject(_response);
					
					boolean success = jsonResponse.optBoolean("success", false);
					if (!success) {
						Toast.makeText(getContext(), "API returned failure", Toast.LENGTH_SHORT).show();
						return;
					}
					
					JSONObject data = jsonResponse.getJSONObject("data");
					JSONObject streamsObj = data.optJSONObject("streams");
					
					if (streamsObj == null || streamsObj.length() == 0) {
						Toast.makeText(getContext(), "No streams available", Toast.LENGTH_SHORT).show();
						return;
					}
					
					JSONArray subtitlesArray = data.optJSONArray("subtitles");
					String subtitlesJson = subtitlesArray != null ? subtitlesArray.toString() : "[]";
					
					// Format Title safely
					String baseTitle = data.optString("title", "");
					int sNum = data.optInt("season", 1);
					int eNum = data.optInt("episode", 1);
					String epTitle = data.optString("episode_title", "");
					
					String formattedTitle = baseTitle + "-S" + sNum + "-E" + eNum;
					if (!epTitle.isEmpty() && !"null".equalsIgnoreCase(epTitle)) {
						formattedTitle += " (" + epTitle + ")";
					}
					
					// Resolve accurate ID
					String passedId = data.optString("tmdb_id", "");
					if (passedId.isEmpty() || passedId.equals("null")) passedId = data.optString("paxsenix_id", "");
					
					android.os.Bundle bundle = new android.os.Bundle();
					bundle.putString("type", "tv");
					bundle.putString("title", formattedTitle);
					bundle.putString("poster", data.optString("poster", ""));
					bundle.putString("tmdb_id", passedId);
					bundle.putString("season", String.valueOf(sNum));
					bundle.putString("episode", String.valueOf(eNum));
					bundle.putString("episode_title", epTitle);
					bundle.putString("streams", streamsObj.toString()); // Pass the whole object!
					bundle.putString("subtitles", subtitlesJson);
					
					DownloadListBottomdialogFragmentActivity qualityBottomSheet = new DownloadListBottomdialogFragmentActivity();
					qualityBottomSheet.setStyle(androidx.fragment.app.DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme);
					qualityBottomSheet.setArguments(bundle);
					qualityBottomSheet.show(getChildFragmentManager(), "download_quality_bottom_sheet");
					
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(getContext(), "Failed to parse response: " + e.getMessage(), Toast.LENGTH_SHORT).show();
				}
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				ReelixToast.cancel();
				Toast.makeText(getContext(), "Network error: " + _message, Toast.LENGTH_SHORT).show();
			}
		};
		
		_get_tv_epi_stream_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				try {
					// Initialize the updated StreamDialogManager
					StreamDialogManager streamDialog = new StreamDialogManager(getActivity(), getActivity().getIntent());
					// Pass the raw JSON response. Parsing and title construction happens inside the manager.
					streamDialog.showDialog(_response);
					
					((ClipboardManager) getContext().getSystemService(getContext().getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", _response));
				} catch (Exception e) {
					e.printStackTrace();
					SketchwareUtil.showMessage(getContext().getApplicationContext(), "Error handling stream response");
				}
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		main.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)40, 0xFF141414));
		textview1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 1);
		overview_header.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 1);
		overview_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
		// ======================================
		// NETFLIX / PRIME VIDEO STYLE CAROUSEL
		// ======================================
		
		// Disable clipping
		viewpager1.setClipToPadding(false);
		viewpager1.setClipChildren(false);
		viewpager1.setOffscreenPageLimit(3);
		
		// Disable parent clipping too
		((ViewGroup)viewpager1.getParent()).setClipToPadding(false);
		((ViewGroup)viewpager1.getParent()).setClipChildren(false);
		
		// Convert DP manually
		float density = getResources().getDisplayMetrics().density;
		
		// Side padding
		int horizontalPadding = (int)(55 * density);
		
		// Apply padding so side cards show
		viewpager1.setPadding(horizontalPadding, 0, horizontalPadding, 0);
		
		// Negative margin brings cards closer together
		viewpager1.setPageMargin((int)(-25 * density));
		
		// Optional smoother feel
		viewpager1.setOverScrollMode(View.OVER_SCROLL_NEVER);
		
		// Page animation transformer
		viewpager1.setPageTransformer(false, new ViewPager.PageTransformer() {
			
			@Override
			public void transformPage(@NonNull View page, float position) {
				
				// CENTER CARD SCALE
				float scale = 0.88f + (1 - Math.abs(position)) * 0.12f;
				
				page.setScaleX(scale);
				page.setScaleY(scale);
				
				// SLIGHT DOWN EFFECT FOR SIDE CARDS
				float translationY = Math.abs(position) * 35;
				
				page.setTranslationY(translationY);
				
				// FADE SIDE CARDS A LITTLE
				float alpha = 0.7f + (1 - Math.abs(position)) * 0.3f;
				
				page.setAlpha(alpha);
				
				// ELEVATION FOR DEPTH
				if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
					page.setElevation((1 - Math.abs(position)) * 20);
				}
			}
		});
		episodes_rec_list.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF000000));
		loading_shim.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF212121));
		recyclerview1.setAdapter(new Recyclerview1Adapter(season_list_map));
		recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
		// --- Safe Bundle Argument Extraction Engine ---
		android.os.Bundle fragmentArgs = getArguments();
		
		if (fragmentArgs != null) {
			action_st = "";
			type_st = "";
			
			if (fragmentArgs.containsKey("action")) {
				action_st = fragmentArgs.getString("action");
			}
			
			if (fragmentArgs.containsKey("type")) {
				type_st = fragmentArgs.getString("type");
			}
			
			if ("local_category".equals(type_st)) {
				// --- LOCAL SERIES PATH: no network calls, build everything from passed items ---
				try {
					String categoryTitle = fragmentArgs.getString("title", "Unknown");
					ArrayList<HashMap<String, Object>> localItems =
					(ArrayList<HashMap<String, Object>>) fragmentArgs.getSerializable("items");
					
					if (loading_shim != null) {
						loading_shim.setVisibility(android.view.View.GONE);
						recyclerview1.setVisibility(android.view.View.VISIBLE);
					}
					
					if (overview_txt != null) {
						overview_txt.setText(""); // no remote overview for local content
					}
					
					// Build a single "season" entry representing this local category,
					// so the existing ViewPager UI still has something to show.
					if (season_list_map == null) {
						season_list_map = new ArrayList<>();
					} else {
						season_list_map.clear();
					}
					
					HashMap<String, Object> localSeason = new HashMap<>();
					localSeason.put("title", categoryTitle);
					localSeason.put("date", "");
					localSeason.put("season_number", "1");
					
					// Derive the cover thumbnail from the first video's path
					String firstVideoPath = "";
					if (localItems != null && !localItems.isEmpty()) {
						firstVideoPath = String.valueOf(localItems.get(0).get("path"));
					}
					localSeason.put("image_url", ""); // no remote URL — flagged for local thumbnail handling below
					localSeason.put("local_thumb_path", firstVideoPath);
					localSeason.put("is_local", true);
					
					season_list_map.add(localSeason);
					if (season_count != null) {
						season_count.setText("1");
					}
					
					viewpager1.setAdapter(new Viewpager1Adapter(season_list_map));
					
					// Populate episodes_list_map directly from the passed local items
					if (episodes_list_map == null) {
						episodes_list_map = new ArrayList<>();
					} else {
						episodes_list_map.clear();
					}
					
					if (localItems != null) {
						for (HashMap<String, Object> video : localItems) {
							HashMap<String, Object> epMap = new HashMap<>();
							epMap.put("name", String.valueOf(video.get("name")));
							epMap.put("runtime", String.valueOf(video.get("durationFormatted")));
							epMap.put("air_date", ""); // no air date for local files
							epMap.put("video_path", String.valueOf(video.get("path")));
							epMap.put("is_local", true);
							episodes_list_map.add(epMap);
						}
					}
					
					recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
					recyclerview1.setAdapter(new Recyclerview1Adapter(episodes_list_map));
					
				} catch (Exception e) {
					e.printStackTrace();
					android.widget.Toast.makeText(getContext(), "Error loading local series: " + e.getMessage(), android.widget.Toast.LENGTH_SHORT).show();
				}
				
			} else if (fragmentArgs.containsKey("id")) {
				// --- REMOTE TMDB PATH: existing network-driven flow ---
				String seriesId = fragmentArgs.getString("id");
				
				if (seriesId != null && !seriesId.trim().isEmpty()) {
					
					String seasonsUrl = "https://api.themoviedb.org/3/tv/" + seriesId + "?language=en-US";
					get_seasons.setHeaders(com.error404.reelix.EpisodesHelper.getTmdbHeaders());
					get_seasons.startRequestNetwork(RequestNetworkController.GET, seasonsUrl, "", _get_seasons_request_listener);
					
					if (loading_shim != null) {
						loading_shim.setVisibility(android.view.View.VISIBLE);
						recyclerview1.setVisibility(android.view.View.GONE);
					}
					
					String episodesUrl = com.error404.reelix.EpisodesHelper.getEndpointUrl(seriesId, "1");
					get_episodes.setHeaders(com.error404.reelix.EpisodesHelper.getTmdbHeaders());
					get_episodes.startRequestNetwork(RequestNetworkController.GET, episodesUrl, "", _get_episodes_request_listener);
					
				} else {
					android.widget.Toast.makeText(getContext(), "Error: Missing Series ID inside payload structure.", android.widget.Toast.LENGTH_SHORT).show();
				}
			}
		} else {
			// Optional Fallback: Check Activity intent if the Fragment was opened without direct bundle args
			if (getActivity() != null && getActivity().getIntent() != null) {
				String seriesId = getActivity().getIntent().getStringExtra("id");
				if (seriesId != null && !seriesId.trim().isEmpty()) {
					
					String seasonsUrl = "https://api.themoviedb.org/3/tv/" + seriesId + "?language=en-US";
					get_seasons.setHeaders(com.error404.reelix.EpisodesHelper.getTmdbHeaders());
					get_seasons.startRequestNetwork(RequestNetworkController.GET, seasonsUrl, "", _get_seasons_request_listener);
					
					if (loading_shim != null) {
						loading_shim.setVisibility(android.view.View.VISIBLE);
						recyclerview1.setVisibility(android.view.View.GONE);
					}
					
					String episodesUrl = com.error404.reelix.EpisodesHelper.getEndpointUrl(seriesId, "1");
					get_episodes.setHeaders(com.error404.reelix.EpisodesHelper.getTmdbHeaders());
					get_episodes.startRequestNetwork(RequestNetworkController.GET, episodesUrl, "", _get_episodes_request_listener);
				}
			}
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		// Sketchware handles onStart(); we just run the post-layout logic directly
		if (getView() != null) {
			getView().post(new Runnable() {
				@Override
				public void run() {
					// Safety check in case fragment detaches while waiting for layout pass
					if (getActivity() == null || getContext() == null) return;
					
					android.view.ViewGroup activityContainer = (android.view.ViewGroup) getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
					android.graphics.drawable.Drawable windowBackground = getActivity().getWindow().getDecorView().getBackground();
					float blurRadius = 25f;
					
					int[] attrs = new int[]{android.R.attr.selectableItemBackgroundBorderless};
					android.content.res.TypedArray typedArray = getContext().obtainStyledAttributes(attrs);
					android.graphics.drawable.Drawable ripple1 = typedArray.getDrawable(0);
					typedArray.recycle();
					
					close.setupWith(activityContainer)
					.setFrameClearDrawable(windowBackground)
					.setBlurRadius(blurRadius)
					.setBlurAutoUpdate(true)
					.setOverlayColor(Color.argb(60, 255, 255, 255));
					
					close.setOutlineProvider(new android.view.ViewOutlineProvider() {
						@Override
						public void getOutline(android.view.View view, android.graphics.Outline outline) {
							int radius = Math.min(view.getWidth(), view.getHeight()) / 2;
							outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
						}
					});
					close.setClipToOutline(true);
					close.setForeground(ripple1);
					close.setClickable(true);
				}
			});
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
			View _view = LayoutInflater.from(_context).inflate(R.layout.vertical_slim_series_list, _container, false);
			
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout overlay = _view.findViewById(R.id.overlay);
			final LinearLayout overlay_details_holder = _view.findViewById(R.id.overlay_details_holder);
			final LinearLayout title_holder = _view.findViewById(R.id.title_holder);
			final LinearLayout sub_holder = _view.findViewById(R.id.sub_holder);
			final TextView title = _view.findViewById(R.id.title);
			final TextView date = _view.findViewById(R.id.date);
			
			title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 1);
			date.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			if (season_list_map.get((int)_position) != null) {
				
				java.util.HashMap<String, Object> itemData = season_list_map.get((int)_position);
				
				if (title != null && itemData.containsKey("title")) {
					title.setText(String.valueOf(itemData.get("title")));
				}
				
				if (date != null && itemData.containsKey("date")) {
					date.setText(String.valueOf(itemData.get("date")));
				}
				
				Boolean isLocal = (Boolean) itemData.get("is_local");
				
				if (isLocal != null && isLocal) {
					// Derive thumbnail from the local video file, off the main thread
					final String localVideoPath = String.valueOf(itemData.get("local_thumb_path"));
					final ImageView targetView = imageview1;
					targetView.setImageResource(R.drawable.background_banner); // placeholder
					targetView.setTag(localVideoPath);
					
					if (localVideoPath != null && !localVideoPath.equals("null") && !localVideoPath.isEmpty()) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								final Bitmap thumb = VideoCacheHelper.getLocalThumbnail(localVideoPath, 500, 500);
								if (thumb == null) return;
								
								targetView.post(new Runnable() {
									@Override
									public void run() {
										if (localVideoPath.equals(targetView.getTag())) {
											targetView.setImageBitmap(thumb);
										}
									}
								});
							}
						}).start();
					}
				} else if (imageview1 != null && itemData.containsKey("image_url")) {
					String imgUrl = String.valueOf(itemData.get("image_url"));
					
					if (!imgUrl.trim().isEmpty()) {
						com.bumptech.glide.Glide.with(getContext().getApplicationContext())
						.load(android.net.Uri.parse(imgUrl))
						.centerCrop()
						.into(imageview1);
					} else {
						imageview1.setImageResource(R.drawable.background_banner);
					}
				}
			}
			
			_container.addView(_view);
			return _view;
		}
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.vertical_episods_list, null);
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
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView title_txt = _view.findViewById(R.id.title_txt);
			final TextView duration_txt = _view.findViewById(R.id.duration_txt);
			final TextView date = _view.findViewById(R.id.date);
			final eightbitlab.com.blurview.BlurView play = _view.findViewById(R.id.play);
			final LinearLayout linear12 = _view.findViewById(R.id.linear12);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			
			// 1. Apply your custom styling and background structures
			_rippleRoundStroke(main_back, "#141414", "#E0E0E0", 30, 2, "#212121");
			
			// 2. Set custom typography and typefaces
			title_txt.setTypeface(android.graphics.Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 1);
			date.setTypeface(android.graphics.Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			duration_txt.setTypeface(android.graphics.Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			
			android.view.ViewGroup activityContainer = (android.view.ViewGroup) getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
			android.graphics.drawable.Drawable windowBackground = getActivity().getWindow().getDecorView().getBackground();
			float blurRadius = 25f;
			
			int[] attrs = new int[]{android.R.attr.selectableItemBackgroundBorderless};
			android.content.res.TypedArray typedArray = getContext().obtainStyledAttributes(attrs);
			android.graphics.drawable.Drawable ripple1 = typedArray.getDrawable(0);
			typedArray.recycle();
			
			play.setupWith(activityContainer)
			.setFrameClearDrawable(windowBackground)
			.setBlurRadius(blurRadius)
			.setBlurAutoUpdate(true)
			.setOverlayColor(Color.argb(60, 255, 255, 255));
			
			play.setOutlineProvider(new android.view.ViewOutlineProvider() {
				@Override
				public void getOutline(android.view.View view, android.graphics.Outline outline) {
					int radius = Math.min(view.getWidth(), view.getHeight()) / 2;
					outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
				}
			});
			play.setClipToOutline(true);
			play.setForeground(ripple1);
			play.setClickable(true);
			
			// Force single line so it doesn't wrap to the bottom
			title_txt.setSingleLine(true);
			title_txt.setMaxLines(1);
			
			// Put ellipsis at the start
			title_txt.setEllipsize(android.text.TextUtils.TruncateAt.END);
			
			// Make sure it actually truncates instead of scrolling
			title_txt.setHorizontallyScrolling(false);
			if (episodes_list_map.get((int)_position) != null) {
				java.util.HashMap<String, Object> epData = episodes_list_map.get((int)_position);
				
				if (title_txt != null && epData.containsKey("name")) {
					title_txt.setText(String.valueOf(epData.get("name")));
				}
				
				if (date != null && epData.containsKey("air_date")) {
					date.setText(String.valueOf(epData.get("air_date")));
				}
				
				if (duration_txt != null && epData.containsKey("runtime")) {
					duration_txt.setText(String.valueOf(epData.get("runtime")));
				}
				
				Boolean isLocalEp = (Boolean) epData.get("is_local");
				
				if (isLocalEp != null && isLocalEp) {
					final String epVideoPath = String.valueOf(epData.get("video_path"));
					final ImageView targetView = imageview1;
					targetView.setImageResource(R.drawable.background_banner);
					targetView.setTag(epVideoPath);
					
					if (epVideoPath != null && !epVideoPath.equals("null") && !epVideoPath.isEmpty()) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								final Bitmap thumb = VideoCacheHelper.getLocalThumbnail(epVideoPath, 300, 300);
								if (thumb == null) return;
								
								targetView.post(new Runnable() {
									@Override
									public void run() {
										if (epVideoPath.equals(targetView.getTag())) {
											targetView.setImageBitmap(thumb);
										}
									}
								});
							}
						}).start();
					}
				} else if (imageview1 != null && epData.containsKey("thumbnail_url")) {
					String thumbUrl = String.valueOf(epData.get("thumbnail_url"));
					
					if (!thumbUrl.trim().isEmpty()) {
						com.bumptech.glide.Glide.with(getContext().getApplicationContext())
						.load(android.net.Uri.parse(thumbUrl))
						.centerCrop()
						.into(imageview1);
					} else {
						imageview1.setImageResource(R.drawable.background_banner);
					}
				}
			}
			
			// Force single line so it doesn't wrap to the bottom
			title_txt.setSingleLine(true);
			title_txt.setMaxLines(1);
			title_txt.setEllipsize(android.text.TextUtils.TruncateAt.END);
			title_txt.setHorizontallyScrolling(false);
			
			// Branch play button behavior: local playback vs remote stream fetching
			final HashMap<String, Object> currentEpForClick = episodes_list_map.get((int)_position);
			Boolean epIsLocal = currentEpForClick != null ? (Boolean) currentEpForClick.get("is_local") : null;
			
			if (epIsLocal != null && epIsLocal) {
				imageview2.setImageResource(R.drawable.icon_play_arrow_round);
				if (play != null) {
					play.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							String videoPath = String.valueOf(currentEpForClick.get("video_path"));
							String videoTitle = String.valueOf(currentEpForClick.get("name"));
							
							Intent customPlayerIntent = new Intent();
							customPlayerIntent.setClass(getContext().getApplicationContext(), PlayerActivity.class);
							customPlayerIntent.putExtra("video_path", videoPath);
							customPlayerIntent.putExtra("video_title", videoTitle);
							getContext().startActivity(customPlayerIntent);
							if (getActivity() != null) {
								getActivity().overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
							}
						}
					});
				}
			} else if (!action_st.equals("") && action_st.equals("download")) {
				imageview2.setImageResource(R.drawable.icon_download_round);
				if (play != null) {
					play.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							String episodeStrSelected = "1";
							if (episodes_list_map != null && episodes_list_map.size() > (int)_position) {
								HashMap<String, Object> currentEpDataTarget = episodes_list_map.get((int)_position);
								if (currentEpDataTarget != null && currentEpDataTarget.containsKey("episode_number")) {
									episodeStrSelected = String.valueOf(currentEpDataTarget.get("episode_number"));
								}
							}
							
							String seasonStrSelected = "1";
							if (season_list_map != null && !season_list_map.isEmpty()) {
								int activeSeasonIndex = viewpager1 != null ? viewpager1.getCurrentItem() : 0;
								if (activeSeasonIndex >= 0 && season_list_map.size() > activeSeasonIndex) {
									HashMap<String, Object> currentSeasonDataTarget = season_list_map.get(activeSeasonIndex);
									if (currentSeasonDataTarget != null && currentSeasonDataTarget.containsKey("season_number")) {
										seasonStrSelected = String.valueOf(currentSeasonDataTarget.get("season_number"));
									}
								}
							}
							
							String seriesIdTarget = "";
							String rawIdType = "tmdb";
							
							android.os.Bundle existingArgs = getArguments();
							if (existingArgs != null) {
								if (existingArgs.containsKey("id")) seriesIdTarget = existingArgs.getString("id");
								if (existingArgs.containsKey("id_type")) rawIdType = existingArgs.getString("id_type");
							}
							
							String apiIdTypeNum = "3";
							if ("paxsenix".equalsIgnoreCase(rawIdType) || "2".equals(rawIdType)) {
								apiIdTypeNum = "2";
							} else if ("composite".equalsIgnoreCase(rawIdType) || "default".equalsIgnoreCase(rawIdType) || "1".equals(rawIdType)) {
								apiIdTypeNum = "1";
							}
							
							String apiUrl = "https://error404-main-api.vercel.app/api/tv/" + seriesIdTarget + "/" + seasonStrSelected + "/" + episodeStrSelected + "?id_type=" + apiIdTypeNum;
							
							get_episode_stream.startRequestNetwork(RequestNetworkController.GET, apiUrl, "", _get_episode_stream_request_listener);
							ReelixToast.show(getContext(), "Fetching stream...", 0, false);
						}
					});
				}
				
			} else {
				imageview2.setImageResource(R.drawable.icon_play_arrow_round);
				if (play != null) {
					play.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							String seriesId = "";
							String rawIdType = "tmdb";
							
							if (getActivity() != null && getActivity().getIntent() != null) {
								seriesId = getActivity().getIntent().getStringExtra("id");
								if (getActivity().getIntent().hasExtra("id_type")) {
									rawIdType = getActivity().getIntent().getStringExtra("id_type");
								}
							} else if (getArguments() != null) {
								seriesId = getArguments().getString("id", "");
								rawIdType = getArguments().getString("id_type", "tmdb");
							}
							
							String apiIdTypeNum = "3";
							if (rawIdType != null) {
								if ("tmdb".equalsIgnoreCase(rawIdType) || "3".equals(rawIdType)) {
									apiIdTypeNum = "3";
								} else if ("paxsenix".equalsIgnoreCase(rawIdType) || "2".equals(rawIdType)) {
									apiIdTypeNum = "2";
								} else if ("composite".equalsIgnoreCase(rawIdType) || "default".equalsIgnoreCase(rawIdType) || "1".equals(rawIdType)) {
									apiIdTypeNum = "1";
								}
							}
							
							String seasonStr = "1";
							int currentPage = viewpager1.getCurrentItem();
							if (season_list_map != null && season_list_map.size() > currentPage) {
								Object seasonNum = season_list_map.get(currentPage).get("season_number");
								if (seasonNum != null) {
									seasonStr = String.valueOf(seasonNum);
								}
							}
							
							String episodeStr = "1";
							if (_data != null && _data.get((int)_position) != null) {
								java.util.HashMap<String, Object> currentEpData = _data.get((int)_position);
								if (currentEpData.containsKey("episode_number")) {
									episodeStr = String.valueOf(currentEpData.get("episode_number"));
								}
							}
							
							String reqUrl = "https://error404-main-api.vercel.app/api/tv/" + seriesId + "/" + seasonStr + "/" + episodeStr + "?id_type=" + apiIdTypeNum;
							
							get_tv_epi_stream.startRequestNetwork(RequestNetworkController.GET, reqUrl, "", _get_tv_epi_stream_request_listener);
							
							SketchwareUtil.showMessage(getContext().getApplicationContext(), "Fetching episode streams...");
						}
					});
				}
				
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