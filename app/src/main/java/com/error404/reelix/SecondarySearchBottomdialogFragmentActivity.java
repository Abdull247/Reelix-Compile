package com.error404.reelix;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
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
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class SecondarySearchBottomdialogFragmentActivity extends BottomSheetDialogFragment {
	
	private ArrayList<HashMap<String, Object>> movie_search_list = new ArrayList<>();
	
	private LinearLayout main;
	private NestedScrollView nestedScrollView1;
	private LinearLayout topper_back;
	private LinearLayout linear3;
	private LinearLayout linear18;
	private RecyclerView recyclerview1;
	private LinearLayout empty_info_holder;
	private ShimmerFrameLayout loading_shim;
	private LinearLayout topper;
	private LinearLayout linear17;
	private TextView textview1;
	private TextView textview3;
	private LinearLayout search_body;
	private BlurView search_icon_body;
	private EditText edittext1;
	private LinearLayout linear4;
	private ImageView imageview2;
	private TextView inner_info;
	
	private RequestNetwork get_search_details;
	private RequestNetwork.RequestListener _get_search_details_request_listener;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.secondary_search_bottomdialog_fragment, _container, false);
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
		linear18 = _view.findViewById(R.id.linear18);
		recyclerview1 = _view.findViewById(R.id.recyclerview1);
		empty_info_holder = _view.findViewById(R.id.empty_info_holder);
		loading_shim = _view.findViewById(R.id.loading_shim);
		topper = _view.findViewById(R.id.topper);
		linear17 = _view.findViewById(R.id.linear17);
		textview1 = _view.findViewById(R.id.textview1);
		textview3 = _view.findViewById(R.id.textview3);
		search_body = _view.findViewById(R.id.search_body);
		search_icon_body = _view.findViewById(R.id.search_icon_body);
		edittext1 = _view.findViewById(R.id.edittext1);
		linear4 = _view.findViewById(R.id.linear4);
		imageview2 = _view.findViewById(R.id.imageview2);
		inner_info = _view.findViewById(R.id.inner_info);
		get_search_details = new RequestNetwork((Activity) getContext());
		
		search_icon_body.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try {
					// Get search query from EditText
					String searchQuery = edittext1.getText().toString().trim();
					
					if (!searchQuery.isEmpty()) {
						// Update UI: hide empty info, show loading
						empty_info_holder.setVisibility(android.view.View.GONE);
						loading_shim.setVisibility(android.view.View.VISIBLE);
						recyclerview1.setVisibility(android.view.View.GONE);
						
						// Construct URL with proper encoding
						String encodedQuery = java.net.URLEncoder.encode(searchQuery, "UTF-8");
						String url = "https://movieapi.xcasper.space/api/search?keyword=" + encodedQuery + "&page=1&perPage=10&subjectType=1";
						
						// Make network request using Sketchware's standard RequestNetwork controller
						get_search_details.startRequestNetwork(RequestNetworkController.GET, url, "", _get_search_details_request_listener);
					} else {
						// Show error if search is empty
						empty_info_holder.setVisibility(android.view.View.VISIBLE);
						loading_shim.setVisibility(android.view.View.GONE);
						inner_info.setText("Please enter a movie title to search");
					}
				} catch (java.io.UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		_get_search_details_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				// Hide loading, show recyclerview
				loading_shim.setVisibility(android.view.View.GONE);
				recyclerview1.setVisibility(android.view.View.VISIBLE);
				empty_info_holder.setVisibility(android.view.View.GONE);
				
				try {
					// Parse JSON response
					org.json.JSONObject jsonResponse = new org.json.JSONObject(_response);
					
					if (jsonResponse.getInt("code") == 200) {
						org.json.JSONObject data = jsonResponse.getJSONObject("data");
						org.json.JSONArray items = data.getJSONArray("items");
						
						// Clear existing list
						movie_search_list.clear();
						
						// Loop through items and add to listmap
						for (int i = 0; i < items.length(); i++) {
							org.json.JSONObject movie = items.getJSONObject(i);
							
							java.util.HashMap<String, Object> movieMap = new java.util.HashMap<>();
							movieMap.put("subjectId", movie.getString("subjectId"));
							movieMap.put("title", movie.getString("title"));
							movieMap.put("releaseDate", movie.getString("releaseDate"));
							
							// Get cover URL (handle if cover is null)
							if (!movie.isNull("cover")) {
								org.json.JSONObject cover = movie.getJSONObject("cover");
								movieMap.put("coverUrl", cover.getString("url"));
							} else {
								movieMap.put("coverUrl", "");
							}
							
							movie_search_list.add(movieMap);
						}
						
						// Update adapter
						recyclerview1.setAdapter(new Recyclerview1Adapter(movie_search_list));
						recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
						
						// Show empty message if no results
						if (movie_search_list.size() == 0) {
							String currentQuery = edittext1.getText().toString().trim();
							empty_info_holder.setVisibility(android.view.View.VISIBLE);
							recyclerview1.setVisibility(android.view.View.GONE);
							inner_info.setText("No movies found for \"" + currentQuery + "\"");
						}
					} else {
						// API returned error code
						empty_info_holder.setVisibility(android.view.View.VISIBLE);
						recyclerview1.setVisibility(android.view.View.GONE);
						inner_info.setText("API Error: " + jsonResponse.getString("code"));
					}
					
				} catch (org.json.JSONException e) {
					// JSON parsing error
					empty_info_holder.setVisibility(android.view.View.VISIBLE);
					recyclerview1.setVisibility(android.view.View.GONE);
					inner_info.setText("Error parsing movie data");
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				// Hide all UI elements
				recyclerview1.setVisibility(android.view.View.GONE);
				loading_shim.setVisibility(android.view.View.GONE);
				empty_info_holder.setVisibility(android.view.View.VISIBLE);
				
				// Show error message
				inner_info.setText("Network Error: " + _message);
			}
		};
	}
	
	private void initializeLogic() {
		main.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF141414));
		textview1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 1);
		textview3.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
		edittext1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
		inner_info.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
		float blurRadius = 25f;
		
		// --- RIPPLE DRAWABLES ---
		int[] attrs = new int[]{android.R.attr.selectableItemBackgroundBorderless};
		android.content.res.TypedArray typedArray = requireContext().obtainStyledAttributes(attrs);
		android.graphics.drawable.Drawable ripple1 = typedArray.getDrawable(0);
		android.graphics.drawable.Drawable ripple2 = typedArray.getDrawable(0);
		typedArray.recycle();
		
		// Delay to ensure dialog is ready
		new android.os.Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (getDialog() != null && getDialog().getWindow() != null) {
					ViewGroup activityContainer = getDialog().getWindow().getDecorView().findViewById(android.R.id.content);
					Drawable windowBackground = getDialog().getWindow().getDecorView().getBackground();
					
					if (search_icon_body != null) {
						search_icon_body.setupWith(activityContainer)
						.setFrameClearDrawable(windowBackground)
						.setBlurRadius(blurRadius)
						.setBlurAutoUpdate(true)
						.setOverlayColor(android.graphics.Color.argb(60, 255, 255, 255));
						
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
					}
					
					if (search_body != null) {
						search_body.setBackground(new android.graphics.drawable.GradientDrawable() { 
							public android.graphics.drawable.GradientDrawable getIns(int a, int b) { 
								this.setCornerRadius(a); 
								this.setColor(b); 
								return this; 
							} 
						}.getIns((int)60, 0xFF212121));
					}
				}
			}
		}, 200);
		loading_shim.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0B0D0F));
		// Initialize UI state
		recyclerview1.setVisibility(android.view.View.GONE);
		loading_shim.setVisibility(android.view.View.GONE);
		empty_info_holder.setVisibility(android.view.View.VISIBLE);
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
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.vertical_list, null);
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
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final de.hdodenhof.circleimageview.CircleImageView circleimageview1 = _view.findViewById(R.id.circleimageview1);
			final TextView title_txt = _view.findViewById(R.id.title_txt);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final TextView date = _view.findViewById(R.id.date);
			final TextView media_type = _view.findViewById(R.id.media_type);
			final ImageView download_img = _view.findViewById(R.id.download_img);
			
			title_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 1);
			date.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			media_type.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			// 1. Apply your custom styling and background structures
			_rippleRoundStroke(main_back, "#0B0D0F", "#E0E0E0", 30, 2, "#212121");
			
			// Get data for current position
			java.util.HashMap<String, Object> currentMovie = (java.util.HashMap<String, Object>) _data.get((int)_position);
			
			// Set title
			String title = currentMovie.get("title") != null ? currentMovie.get("title").toString() : "Unknown Title";
			title_txt.setText(title);
			
			// Set release date
			Object releaseDateObj = currentMovie.get("releaseDate");
			String releaseDate = releaseDateObj != null ? releaseDateObj.toString() : "";
			if (!releaseDate.isEmpty() && !releaseDate.equals("null")) {
				String[] parts = releaseDate.split("-");
				String year = parts.length > 0 ? parts[0] : "N/A";
				date.setText(year);
			} else {
				date.setText("N/A");
			}
			
			// Set cover image
			Object coverUrlObj = currentMovie.get("coverUrl");
			String coverUrl = coverUrlObj != null ? coverUrlObj.toString() : "";
			if (!coverUrl.isEmpty() && !coverUrl.equals("null")) {
				com.bumptech.glide.Glide.with(getContext())
				.load(coverUrl)
				.placeholder(R.drawable.background_banner)
				.error(R.drawable.background_banner)
				.into(circleimageview1);
			} else {
				circleimageview1.setImageResource(android.R.drawable.ic_menu_gallery);
			}
			
			main_back.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					
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