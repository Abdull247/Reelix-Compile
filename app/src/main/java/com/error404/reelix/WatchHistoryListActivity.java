package com.error404.reelix;

import android.animation.*;
import android.app.*;
import android.content.*;
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

public class WatchHistoryListActivity extends AppCompatActivity {
	
	private String fontName = "";
	private String typeace = "";
	
	private ArrayList<HashMap<String, Object>> watch_list_map = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private RecyclerView recyclerview1;
	private ImageView imageview1;
	private TextView textview1;
	private ImageView imageview2;
	
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.watch_history_list);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		recyclerview1 = findViewById(R.id.recyclerview1);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		imageview2 = findViewById(R.id.imageview2);
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			final Window window = WatchHistoryListActivity.this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(0xFF0B0D0F);
		}
		_changeActivityFont("appfont");
		// --- INITIALIZE WATCH HISTORY DATA PIPELINE ---
		try {
			android.content.SharedPreferences history_pref = getSharedPreferences("history_pref", android.content.Context.MODE_PRIVATE);
			String historyJson = history_pref.getString("watch_history", "");
			
			// Clear your Sketchware Map/List variable before reloading to avoid layout accumulation
			watch_list_map.clear();
			
			if (!historyJson.isEmpty()) {
				java.util.ArrayList<java.util.HashMap<String, Object>> temporaryList = 
				new com.google.gson.Gson().fromJson(historyJson, new com.google.gson.reflect.TypeToken<java.util.ArrayList<java.util.HashMap<String, Object>>>(){}.getType());
				
				if (temporaryList != null) {
					watch_list_map.addAll(temporaryList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Attach configuration elements onto RecyclerView1
		recyclerview1.setAdapter(new Recyclerview1Adapter(watch_list_map));
		recyclerview1.setLayoutManager(new LinearLayoutManager(this));
		
	}
	
	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		// --- INITIALIZE WATCH HISTORY DATA PIPELINE ---
		try {
			android.content.SharedPreferences history_pref = getSharedPreferences("history_pref", android.content.Context.MODE_PRIVATE);
			String historyJson = history_pref.getString("watch_history", "");
			
			// Clear your Sketchware Map/List variable before reloading to avoid layout accumulation
			watch_list_map.clear();
			
			if (!historyJson.isEmpty()) {
				java.util.ArrayList<java.util.HashMap<String, Object>> temporaryList = 
				new com.google.gson.Gson().fromJson(historyJson, new com.google.gson.reflect.TypeToken<java.util.ArrayList<java.util.HashMap<String, Object>>>(){}.getType());
				
				if (temporaryList != null) {
					watch_list_map.addAll(temporaryList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Attach configuration elements onto RecyclerView1
		recyclerview1.setAdapter(new Recyclerview1Adapter(watch_list_map));
		recyclerview1.setLayoutManager(new LinearLayoutManager(this));
		
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
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.watch_history_items, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout header = _view.findViewById(R.id.header);
			final LinearLayout main_list = _view.findViewById(R.id.main_list);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout relative_overlay = _view.findViewById(R.id.relative_overlay);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final ImageView type_img = _view.findViewById(R.id.type_img);
			final ProgressBar progressbar1 = _view.findViewById(R.id.progressbar1);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final TextView title = _view.findViewById(R.id.title);
			final TextView percentage_watched_txt = _view.findViewById(R.id.percentage_watched_txt);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			
			// =========================================================
			// STEP 1: PARSE AND FORMAT THE CHRONOLOGICAL DATE HEADER
			// =========================================================
			java.util.HashMap<String, Object> currentItem = _data.get(_position);
			long currentTimestamp = 0;
			
			if (currentItem.containsKey("timestamp")) {
				try {
					currentTimestamp = Long.parseLong(String.valueOf(currentItem.get("timestamp")));
				} catch (Exception e) {
					currentTimestamp = 0;
				}
			}
			
			// Convert absolute timestamps into standardized Calendar representations
			java.util.Calendar currentCal = java.util.Calendar.getInstance();
			currentCal.setTimeInMillis(currentTimestamp);
			
			java.util.Calendar todayCal = java.util.Calendar.getInstance();
			java.util.Calendar yesterdayCal = java.util.Calendar.getInstance();
			yesterdayCal.add(java.util.Calendar.DATE, -1);
			
			String calculatedHeaderLabel = "";
			
			// Determine structural date signatures (Today, Yesterday, or formatted Day layout)
			if (currentTimestamp == 0) {
				calculatedHeaderLabel = "Unknown Date";
			} else if (currentCal.get(java.util.Calendar.YEAR) == todayCal.get(java.util.Calendar.YEAR) &&
			currentCal.get(java.util.Calendar.DAY_OF_YEAR) == todayCal.get(java.util.Calendar.DAY_OF_YEAR)) {
				calculatedHeaderLabel = "Today";
			} else if (currentCal.get(java.util.Calendar.YEAR) == yesterdayCal.get(java.util.Calendar.YEAR) &&
			currentCal.get(java.util.Calendar.DAY_OF_YEAR) == yesterdayCal.get(java.util.Calendar.DAY_OF_YEAR)) {
				calculatedHeaderLabel = "Yesterday";
			} else {
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM dd, yyyy", java.util.Locale.getDefault());
				calculatedHeaderLabel = sdf.format(currentCal.getTime());
			}
			
			boolean shouldShowHeader = false;
			
			if (_position == 0) {
				shouldShowHeader = true;
			} else {
				java.util.HashMap<String, Object> prevItem = _data.get(_position - 1);
				long prevTimestamp = 0;
				if (prevItem.containsKey("timestamp")) {
					try {
						prevTimestamp = Long.parseLong(String.valueOf(prevItem.get("timestamp")));
					} catch (Exception e) {
						prevTimestamp = 0;
					}
				}
				
				java.util.Calendar prevCal = java.util.Calendar.getInstance();
				prevCal.setTimeInMillis(prevTimestamp);
				
				boolean sameDay = (currentCal.get(java.util.Calendar.YEAR) == prevCal.get(java.util.Calendar.YEAR)) &&
				(currentCal.get(java.util.Calendar.DAY_OF_YEAR) == prevCal.get(java.util.Calendar.DAY_OF_YEAR));
				
				if (!sameDay) {
					shouldShowHeader = true;
				}
			}
			
			LinearLayout headerLayout = (LinearLayout) _view.findViewById(R.id.header);
			TextView headerTextView = (TextView) _view.findViewById(R.id.textview1);
			
			if (shouldShowHeader) {
				headerLayout.setVisibility(View.VISIBLE);
				headerTextView.setText(calculatedHeaderLabel);
			} else {
				headerLayout.setVisibility(View.GONE);
			}
			
			
			// =========================================================
			// STEP 2: BIND METADATA TO CHILD VIEW ID ELEMENT STREAMS
			// =========================================================
			TextView itemTitle = (TextView) _view.findViewById(R.id.title);
			TextView percentageText = (TextView) _view.findViewById(R.id.percentage_watched_txt);
			ImageView itemCover = (ImageView) _view.findViewById(R.id.imageview1);
			ImageView typeIcon = (ImageView) _view.findViewById(R.id.type_img);
			ProgressBar watchProgress = (ProgressBar) _view.findViewById(R.id.progressbar1);
			ImageView contextMenuBtn = (ImageView) _view.findViewById(R.id.imageview2);
			
			// 1. Title Processing
			final String titleStr = currentItem.containsKey("title") ? String.valueOf(currentItem.get("title")) : "Unknown Video";
			itemTitle.setText(titleStr);
			
			// 2. Format Context Badges (TV Series vs. Movies)
			String mediaType = currentItem.containsKey("type") ? String.valueOf(currentItem.get("type")) : "movie";
			if (mediaType.equalsIgnoreCase("tv")) {
				typeIcon.setImageResource(R.drawable.icon_movie_round); 
			} else {
				typeIcon.setImageResource(R.drawable.icon_movie_round);
			}
			
			// 3. Image Layout Poster Binding
			String coverUrlStr = currentItem.containsKey("cover_url") ? String.valueOf(currentItem.get("cover_url")) : "";
			if (coverUrlStr != null && !coverUrlStr.trim().isEmpty()) {
				if (coverUrlStr.startsWith("http")) {
					com.bumptech.glide.Glide.with(getApplicationContext())
					.load(coverUrlStr)
					.placeholder(R.drawable.background_banner)
					.into(itemCover);
				} else {
					java.io.File imgFile = new java.io.File(coverUrlStr);
					if (imgFile.exists()) {
						com.bumptech.glide.Glide.with(getApplicationContext())
						.load(imgFile)
						.placeholder(R.drawable.background_banner)
						.into(itemCover);
					} else {
						itemCover.setImageResource(R.drawable.background_banner);
					}
				}
			} else {
				itemCover.setImageResource(R.drawable.background_banner);
			}
			
			// 4. FIX: CALCULATE PROGRESS AND UPDATE TEXT LAYOUT (e.g. "(50% watched)")
			long progressMs = 0;
			long durationMs = 0;
			
			try {
				if (currentItem.containsKey("progress")) {
					progressMs = Long.parseLong(String.valueOf(currentItem.get("progress")));
				}
				if (currentItem.containsKey("duration")) {
					durationMs = Long.parseLong(String.valueOf(currentItem.get("duration")));
				}
			} catch (Exception parseEx) {
				progressMs = 0;
				durationMs = 0;
			}
			
			if (durationMs > 0 && progressMs > 0) {
				int progressPercentage = (int) ((progressMs * 100) / durationMs);
				
				if (progressPercentage > 100) progressPercentage = 100;
				if (progressPercentage < 0) progressPercentage = 0;
				
				watchProgress.setMax(100);
				watchProgress.setProgress(progressPercentage);
				percentageText.setText("(" + progressPercentage + "% watched)");
			} else {
				watchProgress.setProgress(0);
				percentageText.setText("(0% watched)");
			}
			
			// 5. OnClick Navigation Action Handling Blueprint
			LinearLayout mainListItemLayout = (LinearLayout) _view.findViewById(R.id.main_list);
			mainListItemLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String path = currentItem.containsKey("video_path") ? String.valueOf(currentItem.get("video_path")) : "";
					if (new java.io.File(path).exists()) {
						android.content.Intent playIntent = new android.content.Intent();
						playIntent.setClass(getApplicationContext(), PlayerActivity.class);
						playIntent.putExtra("video_path", path);
						playIntent.putExtra("video_title", titleStr);
						startActivity(playIntent);
					} else {
						Toast.makeText(getApplicationContext(), "File is no longer available locally.", Toast.LENGTH_SHORT).show();
					}
				}
			});
			
			// 6. FIX: MATERIAL DIALOG CONTEXT MENU REMOVAL PIPELINE
			contextMenuBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// Build a beautiful styled Google Material System Dialog Window Frame
					com.google.android.material.dialog.MaterialAlertDialogBuilder builder = 
					new com.google.android.material.dialog.MaterialAlertDialogBuilder(v.getContext());
					
					builder.setTitle("Remove from History");
					builder.setMessage("Are you sure you want to clear \"" + titleStr + "\" out of your local watch history log entries?");
					
					// Destructive / Action Confirmation Button Setup 
					builder.setPositiveButton("Delete", new android.content.DialogInterface.OnClickListener() {
						@Override
						public void onClick(android.content.DialogInterface dialog, int which) {
							// Remove the node out of the active underlying array list
							_data.remove(_position);
							
							// Save updated array configuration schema cleanly into Shared Preference fields
							android.content.SharedPreferences history_pref = getSharedPreferences("history_pref", android.content.Context.MODE_PRIVATE);
							history_pref.edit().putString("watch_history", new com.google.gson.Gson().toJson(_data)).apply();
							
							// Notify adapter components to safely animate and recalculate time grouped structures
							recyclerview1.getAdapter().notifyDataSetChanged();
							
							Toast.makeText(getApplicationContext(), "Entry removed completely.", Toast.LENGTH_SHORT).show();
						}
					});
					
					// Safe Cancellation Fallback Execution
					builder.setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
						@Override
						public void onClick(android.content.DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
					
					// Display configuration context layout to interface layer
					builder.create().show();
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