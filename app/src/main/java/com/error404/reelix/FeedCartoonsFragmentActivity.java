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

public class FeedCartoonsFragmentActivity extends Fragment {
	
	private ArrayList<HashMap<String, Object>> anim_movies_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> anim_tvshows_map = new ArrayList<>();
	
	private LinearLayout linear45;
	private SwipeRefreshLayout swiperefreshlayout1;
	private NestedScrollView nestedScrollView1;
	private LinearLayout main;
	private LinearLayout linear39;
	private LinearLayout rec1_holder;
	private LinearLayout rec1_loading_holder;
	private LinearLayout linear40;
	private LinearLayout rec2_holder;
	private LinearLayout rec2_loading_holder;
	private TextView hd1;
	private RecyclerView recyclerview1;
	private ShimmerFrameLayout load_shim1;
	private LinearLayout linear13;
	private TextView load_txt1;
	private TextView hd2;
	private RecyclerView recyclerview2;
	private ShimmerFrameLayout load_shim2;
	private LinearLayout linear44;
	private TextView load_txt2;
	
	private RequestNetwork get_anim_movies;
	private RequestNetwork.RequestListener _get_anim_movies_request_listener;
	private RequestNetwork get_anim_tvshows;
	private RequestNetwork.RequestListener _get_anim_tvshows_request_listener;
	private Intent intent = new Intent();
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.feed_cartoons_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear45 = _view.findViewById(R.id.linear45);
		swiperefreshlayout1 = _view.findViewById(R.id.swiperefreshlayout1);
		nestedScrollView1 = _view.findViewById(R.id.nestedScrollView1);
		main = _view.findViewById(R.id.main);
		linear39 = _view.findViewById(R.id.linear39);
		rec1_holder = _view.findViewById(R.id.rec1_holder);
		rec1_loading_holder = _view.findViewById(R.id.rec1_loading_holder);
		linear40 = _view.findViewById(R.id.linear40);
		rec2_holder = _view.findViewById(R.id.rec2_holder);
		rec2_loading_holder = _view.findViewById(R.id.rec2_loading_holder);
		hd1 = _view.findViewById(R.id.hd1);
		recyclerview1 = _view.findViewById(R.id.recyclerview1);
		load_shim1 = _view.findViewById(R.id.load_shim1);
		linear13 = _view.findViewById(R.id.linear13);
		load_txt1 = _view.findViewById(R.id.load_txt1);
		hd2 = _view.findViewById(R.id.hd2);
		recyclerview2 = _view.findViewById(R.id.recyclerview2);
		load_shim2 = _view.findViewById(R.id.load_shim2);
		linear44 = _view.findViewById(R.id.linear44);
		load_txt2 = _view.findViewById(R.id.load_txt2);
		get_anim_movies = new RequestNetwork((Activity) getContext());
		get_anim_tvshows = new RequestNetwork((Activity) getContext());
		
		swiperefreshlayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				rec1_holder.setVisibility(View.GONE);
				rec1_loading_holder.setVisibility(View.VISIBLE);
				load_txt1.setText("Loading content...");
				
				anim_movies_map.clear();
				recyclerview1.setAdapter(null);
				
				get_anim_movies.setHeaders(com.error404.reelix.EpisodesHelper.getTmdbHeaders());
				get_anim_movies.startRequestNetwork(RequestNetworkController.GET,
				"https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&with_genres=16",
				"",
				_get_anim_movies_request_listener);
				
				rec2_holder.setVisibility(View.GONE);
				rec2_loading_holder.setVisibility(View.VISIBLE);
				load_txt2.setText("Loading content...");
				
				anim_tvshows_map.clear();
				recyclerview2.setAdapter(null);
				
				get_anim_tvshows.setHeaders(com.error404.reelix.EpisodesHelper.getTmdbHeaders());
				get_anim_tvshows.startRequestNetwork(RequestNetworkController.GET,
				"https://api.themoviedb.org/3/discover/tv?include_adult=false&include_null_first_air_dates=false&language=en-US&page=1&sort_by=popularity.desc&with_genres=16",
				"",
				_get_anim_tvshows_request_listener);
				
				swiperefreshlayout1.setRefreshing(false);
			}
		});
		
		_get_anim_movies_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				swiperefreshlayout1.setRefreshing(false);
				try {
					if (anim_movies_map == null) {
						anim_movies_map = new java.util.ArrayList<java.util.HashMap<String, Object>>();
					} else {
						anim_movies_map.clear();
					}
					
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					
					if (responseObj.has("results")) {
						org.json.JSONArray resultsArray = responseObj.getJSONArray("results");
						String imageBaseUrl = "https://image.tmdb.org/t/p/w500";
						
						for (int i = 0; i < resultsArray.length(); i++) {
							org.json.JSONObject movieObj = resultsArray.getJSONObject(i);
							java.util.HashMap<String, Object> map = new java.util.HashMap<>();
							
							map.put("id", String.valueOf(movieObj.optLong("id", 0)));
							map.put("title", movieObj.optString("title", "Unknown Title"));
							map.put("release_date", movieObj.optString("release_date", "N/A"));
							
							String posterPath = movieObj.optString("poster_path", "");
							if (!posterPath.isEmpty() && !movieObj.isNull("poster_path")) {
								map.put("poster_url", imageBaseUrl + posterPath);
							} else {
								map.put("poster_url", "");
							}
							
							anim_movies_map.add(map);
						}
						
						recyclerview1.setAdapter(new Recyclerview1Adapter(anim_movies_map));
						
						rec1_loading_holder.setVisibility(View.GONE);
						rec1_holder.setVisibility(View.VISIBLE);
					}
				} catch (Exception e) {
					e.printStackTrace();
					load_txt1.setText("Failed to parse content.");
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				swiperefreshlayout1.setRefreshing(false);
				rec1_loading_holder.setVisibility(View.GONE);
				rec1_holder.setVisibility(View.GONE);
				load_txt1.setText("Failed to load content.");
				
				if (getContext() != null) {
					android.widget.Toast.makeText(getContext(), "Error: " + _message, android.widget.Toast.LENGTH_SHORT).show();
				}
			}
		};
		
		_get_anim_tvshows_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				swiperefreshlayout1.setRefreshing(false);
				try {
					if (anim_tvshows_map == null) {
						anim_tvshows_map = new java.util.ArrayList<java.util.HashMap<String, Object>>();
					} else {
						anim_tvshows_map.clear();
					}
					
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					
					if (responseObj.has("results")) {
						org.json.JSONArray resultsArray = responseObj.getJSONArray("results");
						String imageBaseUrl = "https://image.tmdb.org/t/p/w500";
						
						for (int i = 0; i < resultsArray.length(); i++) {
							org.json.JSONObject showObj = resultsArray.getJSONObject(i);
							java.util.HashMap<String, Object> map = new java.util.HashMap<>();
							
							map.put("id", String.valueOf(showObj.optLong("id", 0)));
							map.put("title", showObj.optString("name", "Unknown Title"));
							map.put("release_date", showObj.optString("first_air_date", "N/A"));
							
							String posterPath = showObj.optString("poster_path", "");
							if (!posterPath.isEmpty() && !showObj.isNull("poster_path")) {
								map.put("poster_url", imageBaseUrl + posterPath);
							} else {
								map.put("poster_url", "");
							}
							
							anim_tvshows_map.add(map);
						}
						
						recyclerview2.setAdapter(new Recyclerview2Adapter(anim_tvshows_map));
						
						rec2_loading_holder.setVisibility(View.GONE);
						rec2_holder.setVisibility(View.VISIBLE);
					}
				} catch (Exception e) {
					e.printStackTrace();
					load_txt2.setText("Failed to parse content.");
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				swiperefreshlayout1.setRefreshing(false);
				rec2_loading_holder.setVisibility(View.GONE);
				rec2_holder.setVisibility(View.GONE);
				load_txt2.setText("Failed to load content.");
				
				if (getContext() != null) {
					android.widget.Toast.makeText(getContext(), "Error: " + _message, android.widget.Toast.LENGTH_SHORT).show();
				}
			}
		};
	}
	
	private void initializeLogic() {
		hd1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/visions.ttf"), 1);
		load_txt1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		hd2.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/visions.ttf"), 1);
		load_txt2.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/appfont.ttf"), 0);
		swiperefreshlayout1.setRefreshing(true);
		load_shim1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)30, (int)0, 0xFF212121, 0xFF212121));
		load_shim2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)30, (int)0, 0xFF212121, 0xFF212121));
		rec1_holder.setVisibility(View.GONE);
		rec1_loading_holder.setVisibility(View.VISIBLE);
		load_txt1.setText("Loading content...");
		
		recyclerview1.setLayoutManager(new androidx.recyclerview.widget.GridLayoutManager(getContext(), 2));
		
		get_anim_movies.setHeaders(com.error404.reelix.EpisodesHelper.getTmdbHeaders());
		get_anim_movies.startRequestNetwork(RequestNetworkController.GET,
		"https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc&with_genres=16",
		"",
		_get_anim_movies_request_listener);
		
		rec2_holder.setVisibility(View.GONE);
		rec2_loading_holder.setVisibility(View.VISIBLE);
		load_txt2.setText("Loading content...");
		
		recyclerview2.setLayoutManager(new androidx.recyclerview.widget.GridLayoutManager(getContext(), 2));
		
		get_anim_tvshows.setHeaders(com.error404.reelix.EpisodesHelper.getTmdbHeaders());
		get_anim_tvshows.startRequestNetwork(RequestNetworkController.GET,
		"https://api.themoviedb.org/3/discover/tv?include_adult=false&include_null_first_air_dates=false&language=en-US&page=1&sort_by=popularity.desc&with_genres=16",
		"",
		_get_anim_tvshows_request_listener);    
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.larger_grid_items, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final TextView title = _view.findViewById(R.id.title);
			final TextView date = _view.findViewById(R.id.date);
			final ImageView banner = _view.findViewById(R.id.banner);
			
			title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			date.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			title.setSingleLine(true);
			title.setMaxLines(1);
			title.setEllipsize(android.text.TextUtils.TruncateAt.END);
			title.setHorizontallyScrolling(false);
			if (_data.get((int)_position).containsKey("title")) {
				title.setText(_data.get((int)_position).get("title").toString());
			}
			if (_data.get((int)_position).containsKey("release_date")) {
				date.setText(_data.get((int)_position).get("release_date").toString());
			}
			if (_data.get((int)_position).containsKey("poster_url")) {
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("poster_url").toString())).into(banner);
			}
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (_data.get((int)_position).containsKey("id") && _data.get((int)_position).get("id") != null) {
						intent.setClass(requireContext(), ViewMovieDetailsActivity.class);
						intent.putExtra("id", _data.get((int)_position).get("id").toString());
						intent.putExtra("type", "movie");
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
			View _v = _inflater.inflate(R.layout.larger_grid_items, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final TextView title = _view.findViewById(R.id.title);
			final TextView date = _view.findViewById(R.id.date);
			final ImageView banner = _view.findViewById(R.id.banner);
			
			title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			date.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			title.setSingleLine(true);
			title.setMaxLines(1);
			title.setEllipsize(android.text.TextUtils.TruncateAt.END);
			title.setHorizontallyScrolling(false);
			if (_data.get((int)_position).containsKey("title")) {
				title.setText(_data.get((int)_position).get("title").toString());
			}
			if (_data.get((int)_position).containsKey("release_date")) {
				date.setText(_data.get((int)_position).get("release_date").toString());
			}
			if (_data.get((int)_position).containsKey("poster_url")) {
				Glide.with(getContext().getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("poster_url").toString())).into(banner);
			}
			linear1.setOnClickListener(new View.OnClickListener() {
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