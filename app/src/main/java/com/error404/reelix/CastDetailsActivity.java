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
import com.bumptech.glide.Glide;
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

public class CastDetailsActivity extends AppCompatActivity {
	
	private String fontName = "";
	private String typeace = "";
	private String cast_id = "";
	
	private ArrayList<HashMap<String, Object>> get_movie_cast_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> get_tv_cast_map = new ArrayList<>();
	
	private LinearLayout main;
	private NestedScrollView nestedScrollView1;
	private LinearLayout inner_holder;
	private LinearLayout top_header_banner_holder;
	private LinearLayout linear18;
	private LinearLayout linear19;
	private LinearLayout linear20;
	private LinearLayout linear39;
	private LinearLayout castandcrewHolder;
	private LinearLayout linear21;
	private RecyclerView cast_tv_rec;
	private RelativeLayout relativelayout1;
	private ImageView cast_image;
	private LinearLayout top_header_overlay_holder;
	private LinearLayout top_overlay_content_holder;
	private LinearLayout bottom_overlay_content_holder;
	private LinearLayout linear13;
	private LinearLayout linear14;
	private BlurView back;
	private LinearLayout linear12;
	private ImageView imageview2;
	private LinearLayout linear22;
	private ImageView imageview6;
	private TextView name_txt;
	private TextView gender_txt;
	private TextView birthday_txt;
	private TextView textview8;
	private TextView biography_txt;
	private TextView textview10;
	private TextView textview17;
	private RecyclerView cast_movie_rec;
	private TextView textview11;
	
	private RequestNetwork get_cast_details;
	private RequestNetwork.RequestListener _get_cast_details_request_listener;
	private RequestNetwork get_cast_movies;
	private RequestNetwork.RequestListener _get_cast_movies_request_listener;
	private RequestNetwork get_cast_tv;
	private RequestNetwork.RequestListener _get_cast_tv_request_listener;
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.cast_details);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main = findViewById(R.id.main);
		nestedScrollView1 = findViewById(R.id.nestedScrollView1);
		inner_holder = findViewById(R.id.inner_holder);
		top_header_banner_holder = findViewById(R.id.top_header_banner_holder);
		linear18 = findViewById(R.id.linear18);
		linear19 = findViewById(R.id.linear19);
		linear20 = findViewById(R.id.linear20);
		linear39 = findViewById(R.id.linear39);
		castandcrewHolder = findViewById(R.id.castandcrewHolder);
		linear21 = findViewById(R.id.linear21);
		cast_tv_rec = findViewById(R.id.cast_tv_rec);
		relativelayout1 = findViewById(R.id.relativelayout1);
		cast_image = findViewById(R.id.cast_image);
		top_header_overlay_holder = findViewById(R.id.top_header_overlay_holder);
		top_overlay_content_holder = findViewById(R.id.top_overlay_content_holder);
		bottom_overlay_content_holder = findViewById(R.id.bottom_overlay_content_holder);
		linear13 = findViewById(R.id.linear13);
		linear14 = findViewById(R.id.linear14);
		back = findViewById(R.id.back);
		linear12 = findViewById(R.id.linear12);
		imageview2 = findViewById(R.id.imageview2);
		linear22 = findViewById(R.id.linear22);
		imageview6 = findViewById(R.id.imageview6);
		name_txt = findViewById(R.id.name_txt);
		gender_txt = findViewById(R.id.gender_txt);
		birthday_txt = findViewById(R.id.birthday_txt);
		textview8 = findViewById(R.id.textview8);
		biography_txt = findViewById(R.id.biography_txt);
		textview10 = findViewById(R.id.textview10);
		textview17 = findViewById(R.id.textview17);
		cast_movie_rec = findViewById(R.id.cast_movie_rec);
		textview11 = findViewById(R.id.textview11);
		get_cast_details = new RequestNetwork(this);
		get_cast_movies = new RequestNetwork(this);
		get_cast_tv = new RequestNetwork(this);
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				onBackPressed();
			}
		});
		
		_get_cast_details_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				try {
					org.json.JSONObject obj = new org.json.JSONObject(_response);
					
					String _name = obj.getString("name");
					String _biography = obj.getString("biography");
					String _birthday = obj.getString("birthday");
					int _gender = obj.getInt("gender");
					String _profile_path = obj.getString("profile_path");
					
					String _gender_str = "";
					if (_gender == 1) {
						_gender_str = "Female";
					} else if (_gender == 2) {
						_gender_str = "Male";
					} else {
						_gender_str = "Unknown";
					}
					
					String _image_url = "https://image.tmdb.org/t/p/w500" + _profile_path;
					
					name_txt.setText(_name);
					biography_txt.setText(_biography);
					birthday_txt.setText(_birthday);
					gender_txt.setText(_gender_str);
					
					com.bumptech.glide.Glide.with(getApplicationContext())
					.load(_image_url)
					.into(cast_image);
					
				} catch (org.json.JSONException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_get_cast_movies_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				try {
					if (get_movie_cast_map == null) {
						get_movie_cast_map = new java.util.ArrayList<java.util.HashMap<String, Object>>();
					} else {
						get_movie_cast_map.clear();
					}
					
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					String imageBaseUrl = "https://image.tmdb.org/t/p/w342";
					
					if (responseObj.has("cast")) {
						org.json.JSONArray castArray = responseObj.getJSONArray("cast");
						
						for (int i = 0; i < castArray.length(); i++) {
							org.json.JSONObject movieObj = castArray.getJSONObject(i);
							java.util.HashMap<String, Object> map = new java.util.HashMap<>();
							
							map.put("id", movieObj.optString("id", ""));
							map.put("title", movieObj.optString("title", "Unknown Title"));
							map.put("release_date", movieObj.optString("release_date", "N/A"));
							
							String posterPath = movieObj.optString("poster_path", "");
							if (!posterPath.isEmpty() && !movieObj.isNull("poster_path")) {
								map.put("poster_url", imageBaseUrl + posterPath);
							} else {
								map.put("poster_url", "");
							}
							
							get_movie_cast_map.add(map);
						}
					}
					
					// 1. Create a 3-column Grid Layout Manager
					androidx.recyclerview.widget.GridLayoutManager gridLayoutManager =
					new androidx.recyclerview.widget.GridLayoutManager(CastDetailsActivity.this, 3);
					cast_movie_rec.setLayoutManager(gridLayoutManager);
					
					// 2. Add minimal, clean spacing (8dp)
					int spacingInPixels = (int) android.util.TypedValue.applyDimension(
					android.util.TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
					
					cast_movie_rec.addItemDecoration(new androidx.recyclerview.widget.RecyclerView.ItemDecoration() {
						@Override
						public void getItemOffsets(android.graphics.Rect outRect, android.view.View view,
						androidx.recyclerview.widget.RecyclerView parent,
						androidx.recyclerview.widget.RecyclerView.State state) {
							int position = parent.getChildAdapterPosition(view);
							int column = position % 3;
							
							outRect.left = spacingInPixels - column * spacingInPixels / 3;
							outRect.right = (column + 1) * spacingInPixels / 3;
							
							if (position >= 3) {
								outRect.top = spacingInPixels;
							}
						}
					});
					
					// 3. Set adapter and config
					cast_movie_rec.setAdapter(new Cast_movie_recAdapter(get_movie_cast_map));
					cast_movie_rec.setNestedScrollingEnabled(false);
					cast_movie_rec.setHasFixedSize(true);
					
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
		
		_get_cast_tv_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				try {
					if (get_tv_cast_map == null) {
						get_tv_cast_map = new java.util.ArrayList<java.util.HashMap<String, Object>>();
					} else {
						get_tv_cast_map.clear();
					}
					
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					String imageBaseUrl = "https://image.tmdb.org/t/p/w342";
					
					if (responseObj.has("cast")) {
						org.json.JSONArray castArray = responseObj.getJSONArray("cast");
						
						for (int i = 0; i < castArray.length(); i++) {
							org.json.JSONObject tvObj = castArray.getJSONObject(i);
							java.util.HashMap<String, Object> map = new java.util.HashMap<>();
							
							map.put("id", tvObj.optString("id", ""));
							map.put("name", tvObj.optString("name", "Unknown Title"));
							map.put("first_air_date", tvObj.optString("first_air_date", "N/A"));
							
							String posterPath = tvObj.optString("poster_path", "");
							if (!posterPath.isEmpty() && !tvObj.isNull("poster_path")) {
								map.put("poster_url", imageBaseUrl + posterPath);
							} else {
								map.put("poster_url", "");
							}
							
							get_tv_cast_map.add(map);
						}
					}
					
					// 1. Create a 3-column Grid Layout Manager
					androidx.recyclerview.widget.GridLayoutManager gridLayoutManager =
					new androidx.recyclerview.widget.GridLayoutManager(CastDetailsActivity.this, 3);
					cast_tv_rec.setLayoutManager(gridLayoutManager);
					
					// 2. Add spacing (8dp)
					int spacingInPixels = (int) android.util.TypedValue.applyDimension(
					android.util.TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
					
					cast_tv_rec.addItemDecoration(new androidx.recyclerview.widget.RecyclerView.ItemDecoration() {
						@Override
						public void getItemOffsets(android.graphics.Rect outRect, android.view.View view,
						androidx.recyclerview.widget.RecyclerView parent,
						androidx.recyclerview.widget.RecyclerView.State state) {
							int position = parent.getChildAdapterPosition(view);
							int column = position % 3;
							
							outRect.left = spacingInPixels - column * spacingInPixels / 3;
							outRect.right = (column + 1) * spacingInPixels / 3;
							
							if (position >= 3) {
								outRect.top = spacingInPixels;
							}
						}
					});
					
					// 3. Set adapter and config
					cast_tv_rec.setAdapter(new Cast_tv_recAdapter(get_tv_cast_map));
					cast_tv_rec.setNestedScrollingEnabled(false);
					cast_tv_rec.setHasFixedSize(true);
					
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
	}
	
	private void initializeLogic() {
		_changeActivityFont("ooo");
		WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		getWindow().setStatusBarColor(Color.TRANSPARENT);
		
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
		
		if (getIntent().hasExtra("id")) {
			cast_id = getIntent().getStringExtra("id");
		}
		// 1. Retrieve the cast ID passed from the previous intent
		final String cast_id = getIntent().getStringExtra("id");
		
		// 2. Validate and fire the request
		if (cast_id != null && !cast_id.trim().isEmpty()) {
			
			// Set up TMDB authorization headers
			java.util.HashMap<String, Object> tmdbHeaders = new java.util.HashMap<>();
			tmdbHeaders.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMDEwNDFlYzgxODlhMDYwODgyM2RlNTg0YjMwNTU2NiIsIm5iZiI6MTc3ODk0MTUxNi42NzIsInN1YiI6IjZhMDg3ZTRjYjExMGNhZWNhMjk1ZGU4ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.UUCDFvZWR-8Mg347tcy4DzI4yF2PHPGJ2E6OtQnw4bw");
			tmdbHeaders.put("accept", "application/json");
			
			// Fire cast details network request
			get_cast_details.setHeaders(tmdbHeaders);
			get_cast_details.startRequestNetwork(RequestNetworkController.GET, "https://api.themoviedb.org/3/person/" + cast_id + "?language=en-US", "", _get_cast_details_request_listener);
			
			// Fire cast movies network request
			get_cast_movies.setHeaders(tmdbHeaders);
			get_cast_movies.startRequestNetwork(RequestNetworkController.GET, "https://api.themoviedb.org/3/person/" + cast_id + "/movie_credits?language=en-US", "", _get_cast_movies_request_listener);
			
			// Fire cast TV series network request
			get_cast_tv.setHeaders(tmdbHeaders);
			get_cast_tv.startRequestNetwork(RequestNetworkController.GET, "https://api.themoviedb.org/3/person/" + cast_id + "/tv_credits?language=en-US", "", _get_cast_tv_request_listener);
			
		} else {
			SketchwareUtil.showMessage(getApplicationContext(), "Error: Invalid Cast ID.");
			finish();
		}
		
	}
	
	
	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
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
	
	public class Cast_tv_recAdapter extends RecyclerView.Adapter<Cast_tv_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Cast_tv_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.grid_items, null);
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
			
			title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 0);
			date.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 0);
			title.setSingleLine(true);
			title.setMaxLines(1);
			title.setEllipsize(android.text.TextUtils.TruncateAt.END);
			title.setHorizontallyScrolling(false);
			if (_data.get((int)_position).containsKey("name")) {
				title.setText(_data.get((int)_position).get("name").toString());
			}
			if (_data.get((int)_position).containsKey("first_air_date")) {
				date.setText(_data.get((int)_position).get("first_air_date").toString());
			}
			if (_data.get((int)_position).containsKey("poster_url")) {
				Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("poster_url").toString())).into(banner);
			}
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					intent.setClass(getApplicationContext(), ViewMovieDetailsActivity.class);
					intent.putExtra("id", _data.get((int)_position).get("id").toString());
					intent.putExtra("type", "series");
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
	
	public class Cast_movie_recAdapter extends RecyclerView.Adapter<Cast_movie_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Cast_movie_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.grid_items, null);
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
			
			title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 0);
			date.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 0);
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
				Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("poster_url").toString())).into(banner);
			}
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					intent.setClass(getApplicationContext(), ViewMovieDetailsActivity.class);
					intent.putExtra("id", _data.get((int)_position).get("id").toString());
					intent.putExtra("type", "movie");
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