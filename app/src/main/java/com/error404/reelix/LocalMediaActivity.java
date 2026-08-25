package com.error404.reelix;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
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
import com.airbnb.lottie.*;
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
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LocalMediaActivity extends AppCompatActivity {
	
	private String fontName = "";
	private String typeace = "";
	
	private ArrayList<HashMap<String, Object>> history_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> vid_list_lamap = new ArrayList<>();
	
	private LinearLayout linear1;
	private NestedScrollView nestedScrollView1;
	private LinearLayout linear4;
	private LinearLayout linear3;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private RecyclerView watch_history_rec;
	private LinearLayout linear7;
	private RecyclerView vid_list_rec;
	private LinearLayout loading_holder;
	private ImageView imageview1;
	private TextView app_name;
	private TextView textview1;
	private ImageView imageview2;
	private TextView textview2;
	private ImageView imageview3;
	private LottieAnimationView lottie1;
	
	private SharedPreferences history_pref;
private static final int BATCH_FLUSH_INTERVAL_MS = 200;

private ArrayList<HashMap<String, Object>> pendingBatch = new ArrayList<>();
private Handler batchHandler = new Handler(Looper.getMainLooper());
private Runnable batchFlushRunnable;

	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.local_media);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		nestedScrollView1 = findViewById(R.id.nestedScrollView1);
		linear4 = findViewById(R.id.linear4);
		linear3 = findViewById(R.id.linear3);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		watch_history_rec = findViewById(R.id.watch_history_rec);
		linear7 = findViewById(R.id.linear7);
		vid_list_rec = findViewById(R.id.vid_list_rec);
		loading_holder = findViewById(R.id.loading_holder);
		imageview1 = findViewById(R.id.imageview1);
		app_name = findViewById(R.id.app_name);
		textview1 = findViewById(R.id.textview1);
		imageview2 = findViewById(R.id.imageview2);
		textview2 = findViewById(R.id.textview2);
		imageview3 = findViewById(R.id.imageview3);
		lottie1 = findViewById(R.id.lottie1);
		history_pref = getSharedPreferences("history_pref", Activity.MODE_PRIVATE);
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			final Window window = LocalMediaActivity.this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(0xFF0B0D0F);
		}
		_changeActivityFont("ooo");
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
		// --- INITIALIZE WATCH HISTORY DATA PIPELINE ---
		try {
			SharedPreferences historyPrefs = getSharedPreferences("history_pref", Context.MODE_PRIVATE);
			String historyJson = historyPrefs.getString("watch_history", "");
			
			// Clear before reloading to avoid duplicate accumulation on repeated calls
			history_list.clear();
			
			if (!historyJson.isEmpty()) {
				ArrayList<HashMap<String, Object>> temporaryList =
				new Gson().fromJson(historyJson, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				
				if (temporaryList != null) {
					history_list.addAll(temporaryList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Attach adapter + layout manager to the horizontal watch history RecyclerView
		watch_history_rec.setAdapter(new Watch_history_recAdapter(history_list));
		watch_history_rec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
		loading_holder.setVisibility(View.VISIBLE);
		vid_list_rec.setVisibility(View.GONE);
		
		vid_list_rec.setAdapter(new Vid_list_recAdapter(vid_list_lamap));
		vid_list_rec.setLayoutManager(new LinearLayoutManager(this));
		
		if (PermissionHelper.hasRequiredPermissions(this)) {
			startVideoScan();
		} else {
			PermissionHelper.requestRequiredPermissions(this);
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
	
	
	public void _anchore() {
		
	}
    
     @Override
public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if (requestCode == PermissionHelper.REQUEST_CODE_PERMISSIONS) {
        if (PermissionHelper.hasRequiredPermissions(this)) {
            startVideoScan();
        } else {
            loading_holder.setVisibility(View.GONE);
            vid_list_rec.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Permission denied — cannot scan videos", Toast.LENGTH_LONG).show();
        }
    }
}


private void startVideoScan() {
    loading_holder.setVisibility(View.VISIBLE);
    vid_list_rec.setVisibility(View.GONE);

    vid_list_lamap.clear();
    if (vid_list_rec.getAdapter() != null) {
        vid_list_rec.getAdapter().notifyDataSetChanged();
    }

    final VideoScanNotificationHelper notifHelper = new VideoScanNotificationHelper(this);
    notifHelper.showStart();

    final VideoScanHelper scanHelper = new VideoScanHelper(this);
    final boolean[] firstItemReceived = {false};

    // Periodically flush whatever's accumulated in pendingBatch into the visible list.
    // This turns "insert one item every 15ms" into "insert a chunk every 200ms",
    // which is dramatically smoother for RecyclerView and avoids layout thrash.
    batchFlushRunnable = new Runnable() {
        @Override
        public void run() {
            flushPendingBatch();
            batchHandler.postDelayed(this, BATCH_FLUSH_INTERVAL_MS);
        }
    };
    batchHandler.postDelayed(batchFlushRunnable, BATCH_FLUSH_INTERVAL_MS);

    new Thread(new Runnable() {
        @Override
        public void run() {
            scanHelper.scanVideos(new VideoScanHelper.OnVideoFoundListener() {
                @Override
                public void onVideoFound(final HashMap<String, Object> videoItem, final int currentIndex, final int totalCount) {
                    notifHelper.updateProgress((String) videoItem.get("path"), currentIndex, totalCount);

                    pendingBatch.add(videoItem);

                    if (!firstItemReceived[0]) {
                        firstItemReceived[0] = true;
                        loading_holder.setVisibility(View.GONE);
                        vid_list_rec.setVisibility(View.VISIBLE);
                        flushPendingBatch(); // show the very first item immediately, don't wait for the interval
                    }
                }

                @Override
public void onScanComplete(int totalFound) {
    flushPendingBatch(); // flush any remaining raw items first
    batchHandler.removeCallbacks(batchFlushRunnable);

    notifHelper.updateProgress("", totalFound, totalFound, true);
    notifHelper.showComplete(totalFound);

    // Categorize the full flat list we've accumulated, then swap the visible list
    // over to the categorized version in one clean pass.
    ArrayList<HashMap<String, Object>> categorized = VideoCategorizer.categorize(vid_list_lamap);

    vid_list_lamap.clear();
    vid_list_lamap.addAll(categorized);
    vid_list_rec.getAdapter().notifyDataSetChanged();

    if (!firstItemReceived[0]) {
        loading_holder.setVisibility(View.GONE);
        vid_list_rec.setVisibility(View.VISIBLE);
    }
}

                @Override
                public void onScanError(String message) {
                    batchHandler.removeCallbacks(batchFlushRunnable);
                    notifHelper.showError(message);

                    if (vid_list_lamap.isEmpty()) {
                        loading_holder.setVisibility(View.GONE);
                        vid_list_rec.setVisibility(View.VISIBLE);
                    }
                    Toast.makeText(LocalMediaActivity.this, "Scan error: " + message, Toast.LENGTH_LONG).show();
                }
            });
        }
    }).start();
}

private void flushPendingBatch() {
    if (pendingBatch.isEmpty()) return;

    int startPos = vid_list_lamap.size();
    int count = pendingBatch.size();

    vid_list_lamap.addAll(pendingBatch);
    vid_list_rec.getAdapter().notifyItemRangeInserted(startPos, count);

    pendingBatch.clear();
}
	
	public class Watch_history_recAdapter extends RecyclerView.Adapter<Watch_history_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Watch_history_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.horizontal_vid_item, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final com.google.android.material.card.MaterialCardView materialCardView1 = _view.findViewById(R.id.materialCardView1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView time_bar_txt = _view.findViewById(R.id.time_bar_txt);
			final ProgressBar progressbar1 = _view.findViewById(R.id.progressbar1);
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			time_bar_txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/appfont.ttf"), 0);
			final HashMap<String, Object> item = (HashMap<String, Object>) _data.get(_position);
			
			// Duration text
			String durationRaw = String.valueOf(item.get("duration"));
			long durationMillis = 0;
			try {
				durationMillis = Long.parseLong(durationRaw);
			} catch (Exception e) {
				durationMillis = 0;
			}
			time_bar_txt.setText(VideoScanHelper.formatDuration(durationMillis));
			time_bar_txt.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/appfont.ttf"), 0);
			
			// Progress bar — value + red theme color
			int progress = 0;
			try {
				progress = Integer.parseInt(String.valueOf(item.get("progress")));
			} catch (Exception e) {
				progress = 0;
			}
			progressbar1.setProgress(progress);
			progressbar1.getProgressDrawable().setColorFilter(
			Color.parseColor("#E50914"), PorterDuff.Mode.SRC_IN);
			
			// Thumbnail — derive locally since these are local video files
			final String videoPath = String.valueOf(item.get("video_path"));
			imageview1.setImageResource(R.drawable.default_video_thumb); // placeholder
			imageview1.setTag(videoPath);
			
			if (videoPath != null && !videoPath.equals("null")) {
				final ImageView targetView = imageview1;
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						final Bitmap thumb = VideoCacheHelper.getLocalThumbnail(videoPath, 200, 200);
						if (thumb == null) return;
						
						targetView.post(new Runnable() {
							@Override
							public void run() {
								if (videoPath.equals(targetView.getTag())) {
									targetView.setImageBitmap(thumb);
								}
							}
						});
					}
				}).start();
			}
			
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					Intent customPlayerIntent = new Intent();
					customPlayerIntent.setClass(_view.getContext().getApplicationContext(), PlayerActivity.class);
					customPlayerIntent.putExtra("video_path", String.valueOf(item.get("video_path")));
					customPlayerIntent.putExtra("video_title", String.valueOf(item.get("title")));
					_view.getContext().startActivity(customPlayerIntent);
					((Activity) _view.getContext()).overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
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
	
	public class Vid_list_recAdapter extends RecyclerView.Adapter<Vid_list_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Vid_list_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.vertical_local_vid_item, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView bg_cd = _view.findViewById(R.id.bg_cd);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final com.google.android.material.card.MaterialCardView materialCardView1 = _view.findViewById(R.id.materialCardView1);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final RelativeLayout relativelayout1 = _view.findViewById(R.id.relativelayout1);
			final ImageView vid_ic = _view.findViewById(R.id.vid_ic);
			final LinearLayout vid_overlay = _view.findViewById(R.id.vid_overlay);
			final TextView vid_duration = _view.findViewById(R.id.vid_duration);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			final TextView vid_title = _view.findViewById(R.id.vid_title);
			final TextView vid_size = _view.findViewById(R.id.vid_size);
			final ImageView imageview3 = _view.findViewById(R.id.imageview3);
			
			vid_duration.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google.ttf"), 0);
			vid_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sans.ttf"), 1);
			vid_size.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google.ttf"), 0);
			HashMap<String, Object> item = (HashMap<String, Object>) _data.get(_position);
			
			// Title / name
			String name = (String) item.get("name");
			vid_title.setText(name != null ? name : "Unknown");
			
			// Size — for categories, show episode count instead of file size
			String itemType = String.valueOf(item.get("type"));
			if ("local_category".equals(itemType)) {
				Object countObj = item.get("count");
				int count = countObj != null ? (int) countObj : 0;
				vid_size.setText(count + (count == 1 ? " video" : " videos"));
			} else {
				String sizeFormatted = (String) item.get("sizeFormatted");
				vid_size.setText(sizeFormatted != null ? sizeFormatted : "0 B");
			}
			
			// Duration — categories don't have a single duration, hide or blank it
			if ("local_category".equals(itemType)) {
				vid_duration.setText("");
			} else {
				String durationFormatted = (String) item.get("durationFormatted");
				vid_duration.setText(durationFormatted != null ? durationFormatted : "0:00");
			}
			
			// Thumbnail — decode lazily off the main thread since is_local means we derive from the video file
			vid_ic.setImageResource(R.drawable.default_video_thumb); // placeholder while loading
			
			Boolean isLocal = (Boolean) item.get("is_local");
			final String videoPath = (String) item.get("path");
			
			if (isLocal != null && isLocal && videoPath != null) {
				final ImageView targetView = vid_ic;
				targetView.setTag(videoPath); // guards against recycled-view mismatch
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						final Bitmap thumb = VideoCacheHelper.getLocalThumbnail(videoPath, 200, 200);
						if (thumb == null) return;
						
						targetView.post(new Runnable() {
							@Override
							public void run() {
								// Only apply if this view hasn't been recycled for a different item
								if (videoPath.equals(targetView.getTag())) {
									targetView.setImageBitmap(thumb);
								}
							}
						});
					}
				}).start();
			}
			bg_cd.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					HashMap<String, Object> map = (HashMap<String, Object>) _data.get(_position);
					String itemType = String.valueOf(map.get("type"));
					
					if ("local_category".equals(itemType)) {
						// Open the series/category bottom sheet with the full episode list
						android.os.Bundle bundle = new android.os.Bundle();
						bundle.putString("title", String.valueOf(map.get("title")));
						bundle.putString("folder_path", String.valueOf(map.get("folder_path")));
						bundle.putString("type", "local_category");
						
						ArrayList<HashMap<String, Object>> episodeItems =
						(ArrayList<HashMap<String, Object>>) map.get("items");
						bundle.putSerializable("items", episodeItems);
						
						TvSeriesListDetailsBottomdialogFragmentActivity bottomSheet =
						new TvSeriesListDetailsBottomdialogFragmentActivity();
						bottomSheet.setArguments(bundle);
						bottomSheet.setStyle(androidx.fragment.app.DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme);
						bottomSheet.show(((androidx.fragment.app.FragmentActivity) _view.getContext()).getSupportFragmentManager(), "series_ep_list");
						return;
					}
					
					// Standalone movie — existing play + history logic
					try {
						ArrayList<HashMap<String, Object>> historyList = new ArrayList<>();
						SharedPreferences history_pref = _view.getContext().getSharedPreferences("history_pref", Context.MODE_PRIVATE);
						String historyJson = history_pref.getString("watch_history", "");
						
						if (!historyJson.isEmpty()) {
							historyList = new Gson().fromJson(historyJson, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
						}
						
						HashMap<String, Object> historyItem = new HashMap<>();
						String currentTitle = String.valueOf(map.get("name"));
						String thumbPath = String.valueOf(map.get("thumbPath"));
						String videoPath = String.valueOf(map.get("path"));
						
						historyItem.put("title", currentTitle);
						historyItem.put("thumb_path", thumbPath);
						historyItem.put("type", "movie");
						historyItem.put("video_path", videoPath);
						historyItem.put("duration", String.valueOf(map.get("duration")));
						historyItem.put("progress", "0");
						historyItem.put("timestamp", String.valueOf(System.currentTimeMillis()));
						
						for (int i = 0; i < historyList.size(); i++) {
							if (String.valueOf(historyList.get(i).get("title")).equals(currentTitle)) {
								historyList.remove(i);
								break;
							}
						}
						
						historyList.add(0, historyItem);
						history_pref.edit().putString("watch_history", new Gson().toJson(historyList)).apply();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					Intent customPlayerIntent = new Intent();
					customPlayerIntent.setClass(_view.getContext().getApplicationContext(), PlayerActivity.class);
					customPlayerIntent.putExtra("video_path", String.valueOf(map.get("path")));
					customPlayerIntent.putExtra("video_title", String.valueOf(map.get("name")));
					_view.getContext().startActivity(customPlayerIntent);
					((Activity) _view.getContext()).overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
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