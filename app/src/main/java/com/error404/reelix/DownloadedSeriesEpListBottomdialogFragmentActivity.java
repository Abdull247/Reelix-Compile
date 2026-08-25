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
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.json.JSONObject;
import com.google.gson.reflect.TypeToken;

public class DownloadedSeriesEpListBottomdialogFragmentActivity extends BottomSheetDialogFragment {
	
	private String folderPath = "";
	
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
	private TextView title_txt;
	private BlurView close;
	private LinearLayout linear12;
	private ImageView imageview2;
	private TextView overview_header;
	private TextView overview_txt;
	private TextView textview2;
	private TextView season_count;
	private RecyclerView recyclerview1;
	private ShimmerFrameLayout loading_shim;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.downloaded_series_ep_list_bottomdialog_fragment, _container, false);
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
		title_txt = _view.findViewById(R.id.title_txt);
		close = _view.findViewById(R.id.close);
		linear12 = _view.findViewById(R.id.linear12);
		imageview2 = _view.findViewById(R.id.imageview2);
		overview_header = _view.findViewById(R.id.overview_header);
		overview_txt = _view.findViewById(R.id.overview_txt);
		textview2 = _view.findViewById(R.id.textview2);
		season_count = _view.findViewById(R.id.season_count);
		recyclerview1 = _view.findViewById(R.id.recyclerview1);
		loading_shim = _view.findViewById(R.id.loading_shim);
		
		viewpager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageSelected(int _position) {
				if (season_list_map != null && season_list_map.size() > _position) {
					String seasonNum = season_list_map.get(_position).get("season_number").toString();
					
					// Show shimmer, hide list immediately on swipe
					loading_shim.setVisibility(View.VISIBLE);
					recyclerview1.setVisibility(View.GONE);
					episodes_list_map.clear();
					if (recyclerview1.getAdapter() != null) {
						recyclerview1.getAdapter().notifyDataSetChanged();
					}
					
					_loadSeasonEpisodes(seasonNum);
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
	}
	
	private void initializeLogic() {
		main.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)40, 0xFF141414));
		title_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 1);
		overview_header.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 1);
		overview_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
		episodes_rec_list.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF000000));
		loading_shim.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF212121));
		recyclerview1.setAdapter(new Recyclerview1Adapter(season_list_map));
		recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
		Bundle args = getArguments();
		if (args == null) return;
		
		folderPath = args.getString("folder_path", "");
		String coverUrl = args.getString("cover_url", "");
		
		if (folderPath.isEmpty()) {
			Toast.makeText(getContext(), "Invalid folder path", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// Read master metadata.json from show root folder
		File showFolder = new File(folderPath);
		File masterMeta = new File(showFolder, "metadata.json");
		
		if (!masterMeta.exists()) {
			Toast.makeText(getContext(), "Metadata not found", Toast.LENGTH_SHORT).show();
			return;
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(masterMeta));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) sb.append(line);
			br.close();
			
			JSONObject masterJson = new JSONObject(sb.toString());
			
			// Set title
			title_txt.setText(masterJson.optString("title", "Unknown"));
			
			// Get seasons object and build season_list_map
			season_list_map.clear();
			JSONObject seasonsObj = masterJson.optJSONObject("seasons");
			
			if (seasonsObj != null) {
				// Get season keys and sort numerically
				java.util.List<String> seasonKeys = new java.util.ArrayList<>();
				java.util.Iterator<String> keys = seasonsObj.keys();
				while (keys.hasNext()) {
					seasonKeys.add(keys.next());
				}
				java.util.Collections.sort(seasonKeys, new java.util.Comparator<String>() {
					@Override
					public int compare(String a, String b) {
						try {
							return Integer.parseInt(a) - Integer.parseInt(b);
						} catch (Exception e) {
							return a.compareTo(b);
						}
					}
				});
				
				for (String seasonNum : seasonKeys) {
					HashMap<String, Object> map = new HashMap<>();
					map.put("season_number", seasonNum);
					map.put("title", "Season " + seasonNum);
					map.put("cover_url", coverUrl);
					season_list_map.add(map);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getContext(), "Failed to read metadata: " + e.getMessage(), Toast.LENGTH_SHORT).show();
			return;
		}
		
		// ViewPager carousel setup
		viewpager1.setClipToPadding(false);
		viewpager1.setClipChildren(false);
		viewpager1.setOffscreenPageLimit(3);
		
		((ViewGroup)viewpager1.getParent()).setClipToPadding(false);
		((ViewGroup)viewpager1.getParent()).setClipChildren(false);
		
		float density = getResources().getDisplayMetrics().density;
		int horizontalPadding = (int)(55 * density);
		viewpager1.setPadding(horizontalPadding, 0, horizontalPadding, 0);
		viewpager1.setPageMargin((int)(-25 * density));
		viewpager1.setOverScrollMode(View.OVER_SCROLL_NEVER);
		
		viewpager1.setPageTransformer(false, new androidx.viewpager.widget.ViewPager.PageTransformer() {
			@Override
			public void transformPage(@NonNull View page, float position) {
				float scale = 0.88f + (1 - Math.abs(position)) * 0.12f;
				page.setScaleX(scale);
				page.setScaleY(scale);
				
				float translationY = Math.abs(position) * 35;
				page.setTranslationY(translationY);
				
				float alpha = 0.7f + (1 - Math.abs(position)) * 0.3f;
				page.setAlpha(alpha);
				
				if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
					page.setElevation((1 - Math.abs(position)) * 20);
				}
			}
		});
		
		viewpager1.setAdapter(new Viewpager1Adapter(season_list_map));
		
		// Load episodes for the first season by default
		if (!season_list_map.isEmpty()) {
			String firstSeasonNum = season_list_map.get(0).get("season_number").toString();
			_loadSeasonEpisodes(firstSeasonNum);
		}
		
		// On page swipe load that season's episodes
		viewpager1.addOnPageChangeListener(new androidx.viewpager.widget.ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
			
			@Override
			public void onPageSelected(int position) {
				if (season_list_map != null && season_list_map.size() > position) {
					String seasonNum = season_list_map.get(position).get("season_number").toString();
					_loadSeasonEpisodes(seasonNum);
				}
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {}
		});
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
	
	
	public void _loadSeasonEpisodes(final String _seasonNum) {
		loading_shim.setVisibility(View.VISIBLE);
		recyclerview1.setVisibility(View.GONE);
		
		episodes_list_map.clear();
		
		try {
			File seasonFolder = new File(folderPath + "/Season " + _seasonNum);
			
			if (!seasonFolder.exists() || !seasonFolder.isDirectory()) {
				loading_shim.setVisibility(View.GONE);
				Toast.makeText(getContext(), "Season folder not found", Toast.LENGTH_SHORT).show();
				return;
			}
			
			File[] epFolders = seasonFolder.listFiles();
			if (epFolders != null) {
				// Sort ep folders numerically
				java.util.Arrays.sort(epFolders, new java.util.Comparator<File>() {
					@Override
					public int compare(File a, File b) {
						try {
							int numA = Integer.parseInt(a.getName().replace("ep", ""));
							int numB = Integer.parseInt(b.getName().replace("ep", ""));
							return numA - numB;
						} catch (Exception e) {
							return a.getName().compareTo(b.getName());
						}
					}
				});
				
				for (File epFolder : epFolders) {
					if (!epFolder.isDirectory()) continue;
					
					File epMeta = new File(epFolder, "metadata.json");
					if (!epMeta.exists()) continue;
					
					BufferedReader br = new BufferedReader(new FileReader(epMeta));
					StringBuilder sb = new StringBuilder();
					String line;
					while ((line = br.readLine()) != null) sb.append(line);
					br.close();
					
					JSONObject epObj = new JSONObject(sb.toString());
					
					HashMap<String, Object> map = new HashMap<>();
					map.put("episode_number", epObj.optString("episode_number", "0"));
					map.put("season_number", epObj.optString("season_number", _seasonNum));
					map.put("episode_title", epObj.optString("episode_title", ""));
					map.put("cover_url", epObj.optString("cover_url", ""));
					map.put("resolution", epObj.optString("resolution", ""));
					map.put("download_date", String.valueOf(epObj.optLong("download_date", 0)));
					map.put("video_file_name", epObj.optString("video_file_name", ""));
					map.put("video_path", epFolder.getAbsolutePath() + "/" + epObj.optString("video_file_name", ""));
					
					episodes_list_map.add(map);
				}
			}
			
			loading_shim.setVisibility(View.GONE);
			recyclerview1.setVisibility(View.VISIBLE);
			recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
			recyclerview1.setAdapter(new Recyclerview1Adapter(episodes_list_map));
			
		} catch (Exception e) {
			e.printStackTrace();
			loading_shim.setVisibility(View.GONE);
			Toast.makeText(getContext(), "Failed to load episodes: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
			HashMap<String, Object> currentItem = _data.get(_position);
			
			String seasonTitle = currentItem.containsKey("title") ? currentItem.get("title").toString() : "Season";
			String posterUrl = currentItem.containsKey("cover_url") ? currentItem.get("cover_url").toString() : "";
			
			title.setText(seasonTitle);
			
			if (!posterUrl.isEmpty()) {
				com.bumptech.glide.Glide.with(getContext())
				.load(posterUrl)
				.centerCrop()
				.into(imageview1);
			} else {
				imageview1.setImageResource(R.drawable.shadow);
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
			HashMap<String, Object> currentItem = episodes_list_map.get(_position);
			
			String seasonNum = currentItem.containsKey("season_number") ? currentItem.get("season_number").toString() : "1";
			String episodeNum = currentItem.containsKey("episode_number") ? currentItem.get("episode_number").toString() : "1";
			String episodeTitle = currentItem.containsKey("episode_title") ? currentItem.get("episode_title").toString() : "";
			String resolution = currentItem.containsKey("resolution") ? currentItem.get("resolution").toString() : "";
			String coverUrl = currentItem.containsKey("cover_url") ? currentItem.get("cover_url").toString() : "";
			String downloadDate = currentItem.containsKey("download_date") ? currentItem.get("download_date").toString() : "0";
			String videoPath = currentItem.containsKey("video_path") ? currentItem.get("video_path").toString() : "";
			
			// Format title as S5 E2: Blood and Bone
			String displayTitle = "S" + seasonNum + " E" + episodeNum + ": " + episodeTitle;
			title_txt.setText(displayTitle);
			
			// Resolution
			duration_txt.setText(resolution);
			
			// Format download date
			try {
				long dateMs = Long.parseLong(downloadDate);
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMM dd, yyyy", java.util.Locale.getDefault());
				date.setText(sdf.format(new java.util.Date(dateMs)));
			} catch (Exception e) {
				date.setText("");
			}
			
			// Poster
			if (!coverUrl.isEmpty()) {
				imageview1.setVisibility(View.VISIBLE);
				com.bumptech.glide.Glide.with(getContext())
				.load(coverUrl)
				.centerCrop()
				.into(imageview1);
			} else {
				imageview1.setVisibility(View.GONE);
			}
			play.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					String videoPath = currentItem.containsKey("video_path") ? currentItem.get("video_path").toString() : "";
					String seasonNum = currentItem.containsKey("season_number") ? currentItem.get("season_number").toString() : "1";
					String episodeNum = currentItem.containsKey("episode_number") ? currentItem.get("episode_number").toString() : "1";
					String episodeTitle = currentItem.containsKey("episode_title") ? currentItem.get("episode_title").toString() : "";
					String showTitle = getArguments() != null ? getArguments().getString("title", "Unknown") : "Unknown";
					String coverUrl = getArguments() != null ? getArguments().getString("cover_url", "") : "";
					
					if (videoPath.isEmpty()) {
						Toast.makeText(getContext(), "Video file not found", Toast.LENGTH_SHORT).show();
						return;
					}
					
					if (!new java.io.File(videoPath).exists()) {
						Toast.makeText(getContext(), "Video file missing from storage", Toast.LENGTH_SHORT).show();
						return;
					}
					
					String displayTitle = showTitle + " S" + seasonNum + " E" + episodeNum;
					if (!episodeTitle.trim().isEmpty()) {
						displayTitle += ": " + episodeTitle;
					}
					
					try {
						android.content.SharedPreferences history_pref = getContext().getSharedPreferences("history_pref", android.content.Context.MODE_PRIVATE);
						java.util.ArrayList<java.util.HashMap<String, Object>> historyList = new java.util.ArrayList<>();
						String historyJson = history_pref.getString("watch_history", "");
						
						if (!historyJson.isEmpty()) {
							historyList = new com.google.gson.Gson().fromJson(historyJson, new com.google.gson.reflect.TypeToken<java.util.ArrayList<java.util.HashMap<String, Object>>>(){}.getType());
						}
						
						java.util.HashMap<String, Object> historyItem = new java.util.HashMap<>();
						historyItem.put("title", displayTitle);
						historyItem.put("cover_url", coverUrl);
						historyItem.put("type", "tv");
						historyItem.put("video_path", videoPath);
						historyItem.put("timestamp", String.valueOf(System.currentTimeMillis()));
						
						for (int i = 0; i < historyList.size(); i++) {
							if (String.valueOf(historyList.get(i).get("title")).equals(displayTitle)) {
								historyList.remove(i);
								break;
							}
						}
						
						historyList.add(0, historyItem);
						history_pref.edit().putString("watch_history", new com.google.gson.Gson().toJson(historyList)).apply();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					android.content.Intent playerIntent = new android.content.Intent();
					playerIntent.setClass(getContext(), PlayerActivity.class);
					playerIntent.putExtra("video_path", videoPath);
					playerIntent.putExtra("video_title", displayTitle);
					startActivity(playerIntent);
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