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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.google.android.material.card.*;
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

public class DownloadListBottomdialogFragmentActivity extends BottomSheetDialogFragment {
	
	private boolean setEnabled = false;
	private String downloadUrl = "";
	private String resLabel = "";
	private String currentItem = "";
	
	private ArrayList<HashMap<String, Object>> download_list = new ArrayList<>();
	private ArrayList<String> server_spinner_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> s1List = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> s2List = new ArrayList<>();
	
	private LinearLayout main;
	private NestedScrollView nestedScrollView1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear5;
	private RecyclerView recyclerview1;
	private MaterialCardView view_downloads_page_btn;
	private ShimmerFrameLayout loading_hold;
	private LinearLayout close;
	private ImageView imageview1;
	private TextView textview1;
	private Spinner server_spinner;
	private LinearLayout linear14;
	private TextView textview2;
	private ImageView imageview2;
	private LinearLayout linear13;
	private TextView loading_txt;
	
	private Intent intent = new Intent();
	private RequestNetwork get_download;
	private RequestNetwork.RequestListener _get_download_request_listener;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.download_list_bottomdialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		main = _view.findViewById(R.id.main);
		nestedScrollView1 = _view.findViewById(R.id.nestedScrollView1);
		linear2 = _view.findViewById(R.id.linear2);
		linear3 = _view.findViewById(R.id.linear3);
		linear5 = _view.findViewById(R.id.linear5);
		recyclerview1 = _view.findViewById(R.id.recyclerview1);
		view_downloads_page_btn = _view.findViewById(R.id.view_downloads_page_btn);
		loading_hold = _view.findViewById(R.id.loading_hold);
		close = _view.findViewById(R.id.close);
		imageview1 = _view.findViewById(R.id.imageview1);
		textview1 = _view.findViewById(R.id.textview1);
		server_spinner = _view.findViewById(R.id.server_spinner);
		linear14 = _view.findViewById(R.id.linear14);
		textview2 = _view.findViewById(R.id.textview2);
		imageview2 = _view.findViewById(R.id.imageview2);
		linear13 = _view.findViewById(R.id.linear13);
		loading_txt = _view.findViewById(R.id.loading_txt);
		get_download = new RequestNetwork((Activity) getContext());
		
		view_downloads_page_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dismiss();
			}
		});
		
		server_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				try {
					String selectedServer = server_spinner_list.get((int)_position);
					download_list.clear();
					
					if ("Server 1".equals(selectedServer)) {
						download_list.addAll(s1List);
					} else if ("Server 2".equals(selectedServer)) {
						download_list.addAll(s2List);
					}
					
					if (download_list.size() > 0) {
						recyclerview1.setVisibility(View.VISIBLE);
						// Re-bind adapter to refresh the UI cleanly
						recyclerview1.setAdapter(new Recyclerview1Adapter(download_list));
					} else {
						recyclerview1.setVisibility(View.GONE);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		_get_download_request_listener = new RequestNetwork.RequestListener() {
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
	}
	
	private void initializeLogic() {
		main.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)30, (int)2, 0xFF141414, 0xFF000000));
		textview1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
		loading_hold.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF212121));
		if (loading_hold.getVisibility() == View.VISIBLE) {
			setEnabled = false;
		} else {
			setEnabled = true;
		}
		// 1. Create the background shape with a transparent center, 360dp corners, and a 2dp border
		android.graphics.drawable.GradientDrawable shape = new android.graphics.drawable.GradientDrawable();
		shape.setColor(android.graphics.Color.TRANSPARENT);
		
		// Convert 360dp to pixels dynamically so it scales properly on all screen densities
		float radiusPx = android.util.TypedValue.applyDimension(
		android.util.TypedValue.COMPLEX_UNIT_DIP, 360, getContext().getResources().getDisplayMetrics());
		shape.setCornerRadius(radiusPx);
		
		// Convert 2dp border width to pixels dynamically
		int strokePx = (int) android.util.TypedValue.applyDimension(
		android.util.TypedValue.COMPLEX_UNIT_DIP, 2, getContext().getResources().getDisplayMetrics());
		shape.setStroke(strokePx, android.graphics.Color.parseColor("#141414"));
		
		// 2. Define the color states for the Ripple effect (#E0E0E0 when pressed)
		int[][] states = new int[][] {
			new int[] { android.R.attr.state_pressed }, // Pressed state
			new int[] {}                                // Normal state
		};
		
		int[] colors = new int[] {
			android.graphics.Color.parseColor("#E0E0E0"), // Ripple color
			android.graphics.Color.TRANSPARENT
		};
		
		android.content.res.ColorStateList rippleColors = new android.content.res.ColorStateList(states, colors);
		
		// 3. Combine the ripple effect and the custom shape background
		android.graphics.drawable.RippleDrawable rippleDrawable = new android.graphics.drawable.RippleDrawable(rippleColors, shape, null);
		
		// 4. Apply directly to your 'close' layout using Sketchware's global reference
		close.setBackground(rippleDrawable);
		close.setClickable(true);
		close.setFocusable(true);
		Bundle args = getArguments();
		if (args == null) return;
		
		final String streamsJson = args.getString("streams", "{}");
		final String title = args.getString("title", "Unknown");
		final String type = args.getString("type", "movie");
		final String posterUrl = args.getString("poster", "");
		
		download_list = new ArrayList<>();
		server_spinner_list.clear(); // Ensure spinner list starts clean
		
		// Temporary lists to hold data to avoid reparsing the JSON every time the spinner changes
		final ArrayList<HashMap<String, Object>> s1List = new ArrayList<>();
		final ArrayList<HashMap<String, Object>> s2List = new ArrayList<>();
		
		try {
			JSONObject streamsObj = new JSONObject(streamsJson);
			JSONArray s1Array = streamsObj.optJSONArray("server1");
			JSONArray s2Array = streamsObj.optJSONArray("server2");
			
			// Process Server 1
			if (s1Array != null && s1Array.length() > 0) {
				server_spinner_list.add("Server 1");
				for (int i = 0; i < s1Array.length(); i++) {
					JSONObject stream = s1Array.getJSONObject(i);
					HashMap<String, Object> item = new HashMap<>();
					item.put("quality", stream.optString("quality", "Auto"));
					// Prioritize direct_url for downloads/exoplayer
					item.put("url", stream.optString("direct_url", stream.optString("url", "")));
					item.put("title", title);
					item.put("type", type);
					item.put("poster", posterUrl);
					s1List.add(item);
				}
			}
			
			// Process Server 2
			if (s2Array != null && s2Array.length() > 0) {
				server_spinner_list.add("Server 2");
				for (int i = 0; i < s2Array.length(); i++) {
					JSONObject stream = s2Array.getJSONObject(i);
					HashMap<String, Object> item = new HashMap<>();
					item.put("quality", stream.optString("quality", "Auto"));
					item.put("url", stream.optString("direct_url", stream.optString("url", "")));
					item.put("title", title);
					item.put("type", type);
					item.put("poster", posterUrl);
					s2List.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (loading_hold != null) loading_hold.setVisibility(View.GONE);
		
		if (server_spinner_list.size() > 0) {
			try {
				ArrayAdapter<String> customSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, server_spinner_list) {
					@Override
					public View getView(int position, View convertView, ViewGroup parent) {
						View view = super.getView(position, convertView, parent);
						TextView text = (TextView) view.findViewById(android.R.id.text1);
						text.setTextColor(Color.WHITE); 
						return view;
					}
					
					@Override
					public View getDropDownView(int position, View convertView, ViewGroup parent) {
						View view = super.getDropDownView(position, convertView, parent);
						TextView text = (TextView) view.findViewById(android.R.id.text1);
						text.setTextColor(Color.BLACK); 
						return view;
					}
				};
				
				customSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				server_spinner.setAdapter(customSpinnerAdapter);
				
				// Spinner Selection logic
				server_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						String selectedServer = server_spinner_list.get(position);
						download_list.clear();
						
						if ("Server 1".equals(selectedServer)) {
							download_list.addAll(s1List);
						} else if ("Server 2".equals(selectedServer)) {
							download_list.addAll(s2List);
						}
						
						if (download_list.size() > 0) {
							recyclerview1.setVisibility(View.VISIBLE);
							if (recyclerview1.getAdapter() == null) {
								recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
								recyclerview1.setAdapter(new Recyclerview1Adapter(download_list));
							} else {
								recyclerview1.getAdapter().notifyDataSetChanged();
							}
						} else {
							recyclerview1.setVisibility(View.GONE);
						}
					}
					
					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
				
				// Initialize default list rendering immediately (Defaults to first available)
				if (server_spinner_list.contains("Server 1")) {
					download_list.addAll(s1List);
				} else {
					download_list.addAll(s2List);
				}
				
				recyclerview1.setVisibility(View.VISIBLE);
				recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
				recyclerview1.setAdapter(new Recyclerview1Adapter(download_list));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			recyclerview1.setVisibility(View.GONE);
			server_spinner.setVisibility(View.GONE);
			Toast.makeText(getContext(), "No streams available", Toast.LENGTH_SHORT).show();
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
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.search_download_list, null);
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
			final TextView type_txt = _view.findViewById(R.id.type_txt);
			final TextView qualitytxt = _view.findViewById(R.id.qualitytxt);
			final LinearLayout download_btn = _view.findViewById(R.id.download_btn);
			final LinearLayout linear12 = _view.findViewById(R.id.linear12);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			
			_rippleRoundStroke(main_back, "#141414", "#E0E0E0", 30, 2, "#212121");
			
			title_txt.setTypeface(android.graphics.Typeface.createFromAsset(getContext().getAssets(), "fonts/ooo.ttf"), 1);
			qualitytxt.setTypeface(android.graphics.Typeface.createFromAsset(getContext().getAssets(), "fonts/ooo.ttf"), 0);
			type_txt.setTypeface(android.graphics.Typeface.createFromAsset(getContext().getAssets(), "fonts/ooo.ttf"), 0);
			
			title_txt.setSingleLine(true);
			title_txt.setMaxLines(1);
			title_txt.setEllipsize(android.text.TextUtils.TruncateAt.END);
			title_txt.setHorizontallyScrolling(false);
			
			_rippleRoundStroke(download_btn, "#616161", "#E0E0E0", 360, 0, "#000000");
			
			HashMap<String, Object> currentItem = _data.get(_position);
			
			String itemTitle = currentItem.containsKey("title") ? currentItem.get("title").toString() : "Unknown";
			String itemType = currentItem.containsKey("type") ? currentItem.get("type").toString() : "movie";
			String itemQuality = currentItem.containsKey("quality") ? currentItem.get("quality").toString() : "Auto";
			String itemPoster = currentItem.containsKey("poster") ? currentItem.get("poster").toString() : "";
			
			// Get episode title from bundle if TV
			Bundle fragmentArgs = getArguments();
			String episodeTitle = fragmentArgs != null ? fragmentArgs.getString("episode_title", "") : "";
			String itemTypeArg = fragmentArgs != null ? fragmentArgs.getString("type", "movie") : "movie";
			
			String displayTitle;
			if (itemTypeArg.equalsIgnoreCase("tv") && !episodeTitle.isEmpty()) {
				displayTitle = itemTitle + " : " + episodeTitle;
			} else {
				displayTitle = itemTitle;
			}
			
			title_txt.setText(displayTitle);
			qualitytxt.setText(itemQuality);
			type_txt.setText(itemTypeArg.equalsIgnoreCase("tv") ? "TV Show" : "Movie");
			
			
			if (!itemPoster.isEmpty()) {
				imageview1.setVisibility(View.VISIBLE);
				com.bumptech.glide.Glide.with(getContext())
				.load(itemPoster)
				.centerCrop()
				.into(imageview1);
			} else {
				imageview1.setVisibility(View.GONE);
			}
			download_btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					String downloadUrl = currentItem.containsKey("url") ? currentItem.get("url").toString() : "";
					
					if (downloadUrl.isEmpty()) {
						Toast.makeText(getContext(), "Stream URL not available", Toast.LENGTH_SHORT).show();
						return;
					}
					
					Bundle fragmentArgs = getArguments();
					String videoName = fragmentArgs != null ? fragmentArgs.getString("title", "Unknown") : "Unknown";
					String itemTypeArg = fragmentArgs != null ? fragmentArgs.getString("type", "movie") : "movie";
					String tmdbId = fragmentArgs != null ? fragmentArgs.getString("tmdb_id", "") : "";
					String posterUrl = fragmentArgs != null ? fragmentArgs.getString("poster", "") : "";
					String seasonNum = fragmentArgs != null ? fragmentArgs.getString("season", "1") : "1";
					String episodeNum = fragmentArgs != null ? fragmentArgs.getString("episode", "1") : "1";
					String episodeTitle = fragmentArgs != null ? fragmentArgs.getString("episode_title", "") : "";
					
					String safeVideoName = videoName.replaceAll("[\\\\/:*?\"<>|]", "").trim();
					if (safeVideoName.isEmpty()) safeVideoName = "Video_" + System.currentTimeMillis();
					
					File baseDir = getContext().getExternalFilesDir(null);
					if (baseDir == null) {
						Toast.makeText(getContext(), "Storage access error", Toast.LENGTH_SHORT).show();
						return;
					}
					
					if (itemTypeArg.equalsIgnoreCase("tv") || itemTypeArg.equalsIgnoreCase("series")) {
						// TV structure: Downloads/Videos/{ShowName}/Season {n}/ep{n}/
						String fileName = "S" + seasonNum + "E" + episodeNum + "_" + itemQuality + ".mp4";
						String epFolderPath = "Downloads/Videos/" + safeVideoName + "/Season " + seasonNum + "/ep" + episodeNum;
						
						File epFolder = new File(baseDir, epFolderPath);
						if (!epFolder.exists()) epFolder.mkdirs();
						
						File videoFile = new File(epFolder, fileName);
						String uniqueId = String.valueOf(System.currentTimeMillis());
						
						// Episode-level metadata.json
						try {
							File epMetaFile = new File(epFolder, "metadata.json");
							org.json.JSONObject epMeta = new org.json.JSONObject();
							epMeta.put("download_id", uniqueId);
							epMeta.put("title", videoName);
							epMeta.put("episode_title", episodeTitle);
							epMeta.put("cover_url", posterUrl);
							epMeta.put("tmdb_id", tmdbId);
							epMeta.put("season_number", seasonNum);
							epMeta.put("episode_number", episodeNum);
							epMeta.put("resolution", itemQuality);
							epMeta.put("video_file_name", fileName);
							epMeta.put("download_date", System.currentTimeMillis());
							epMeta.put("is_downloading", true);
							epMeta.put("type", "tv");
							
							java.io.FileWriter epWriter = new java.io.FileWriter(epMetaFile);
							epWriter.write(epMeta.toString(4));
							epWriter.flush();
							epWriter.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						// Update master metadata.json at show root
						ReelixStorageHelper.updateTvMasterMetadata(
						baseDir,
						safeVideoName,
						tmdbId,
						posterUrl,
						seasonNum,
						episodeNum,
						episodeTitle,
						itemQuality,
						fileName,
						uniqueId
						);
						
						ReelixDownloadHelper.startCustomDownload(
						getContext(),
						downloadUrl,
						videoFile.getAbsolutePath(),
						videoName,
						uniqueId
						);
						
					} else {
						// Movie structure: Downloads/Videos/{MovieName}/
						String fileName = "Reelix_" + itemQuality + "_" + System.currentTimeMillis() + ".mp4";
						String folderPath = "Downloads/Videos/" + safeVideoName;
						
						File videoFolder = new File(baseDir, folderPath);
						if (!videoFolder.exists()) videoFolder.mkdirs();
						
						File videoFile = new File(videoFolder, fileName);
						String uniqueId = String.valueOf(System.currentTimeMillis());
						
						try {
							File metadataFile = new File(videoFolder, "metadata.json");
							org.json.JSONObject metadataJson = new org.json.JSONObject();
							metadataJson.put("download_id", uniqueId);
							metadataJson.put("title", videoName);
							metadataJson.put("cover_url", posterUrl);
							metadataJson.put("tmdb_id", tmdbId);
							metadataJson.put("resolution", itemQuality);
							metadataJson.put("video_file_name", fileName);
							metadataJson.put("download_date", System.currentTimeMillis());
							metadataJson.put("is_downloading", true);
							metadataJson.put("type", "movie");
							
							java.io.FileWriter writer = new java.io.FileWriter(metadataFile);
							writer.write(metadataJson.toString(4));
							writer.flush();
							writer.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						ReelixDownloadHelper.startCustomDownload(
						getContext(),
						downloadUrl,
						videoFile.getAbsolutePath(),
						videoName,
						uniqueId
						);
					}
					
					Toast.makeText(getContext(), "Download started: " + itemQuality, Toast.LENGTH_SHORT).show();
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