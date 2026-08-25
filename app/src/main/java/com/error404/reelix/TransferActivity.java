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

public class TransferActivity extends AppCompatActivity {
	
	private String fontName = "";
	private String typeace = "";
	private String vid_path = "";
	private double selected_count = 0;
	private String file_name = "";
	
	private ArrayList<HashMap<String, Object>> files_list = new ArrayList<>();
	
	private LinearLayout main;
	private LinearLayout top_bar;
	private RecyclerView recyclerview1;
	private LinearLayout bottom_holder;
	private ImageView imageview1;
	private TextView textview1;
	private ImageView imageview2;
	private LinearLayout send_btn;
	private LinearLayout receive_btn;
	private LinearLayout total_selected;
	private ImageView imageview3;
	private TextView textview2;
	private ImageView imageview4;
	private TextView textview3;
	private TextView total_selected_txt;
	
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.transfer);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main = findViewById(R.id.main);
		top_bar = findViewById(R.id.top_bar);
		recyclerview1 = findViewById(R.id.recyclerview1);
		bottom_holder = findViewById(R.id.bottom_holder);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		imageview2 = findViewById(R.id.imageview2);
		send_btn = findViewById(R.id.send_btn);
		receive_btn = findViewById(R.id.receive_btn);
		total_selected = findViewById(R.id.total_selected);
		imageview3 = findViewById(R.id.imageview3);
		textview2 = findViewById(R.id.textview2);
		imageview4 = findViewById(R.id.imageview4);
		textview3 = findViewById(R.id.textview3);
		total_selected_txt = findViewById(R.id.total_selected_txt);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				onBackPressed();
			}
		});
		
		send_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				// 1. Filter out all selected items and convert them into a structured JSON payload
				org.json.JSONArray selectedFilesArray = new org.json.JSONArray();
				
				for (java.util.HashMap<String, Object> item : files_list) {
					if ("true".equals(item.get("is_selected"))) {
						try {
							org.json.JSONObject fileObj = new org.json.JSONObject();
							fileObj.put("filePath", String.valueOf(item.get("video_path")));
							fileObj.put("movieTitle", String.valueOf(item.get("title")));
							fileObj.put("size", String.valueOf(item.get("size")));
							
							// Added online image cover URL fallback routing
							if (item.containsKey("cover_url")) {
								fileObj.put("imageUrl", String.valueOf(item.get("cover_url")));
							} else {
								fileObj.put("imageUrl", "");
							}
							
							selectedFilesArray.put(fileObj);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
				// 2. Process routing only if items are truly staged
				if (selectedFilesArray.length() > 0) {
					
					// A. Check Wi-Fi Status
					boolean isWifiEnabled = false;
					android.net.wifi.WifiManager wifiManager = (android.net.wifi.WifiManager) getApplicationContext().getSystemService(android.content.Context.WIFI_SERVICE);
					if (wifiManager != null && wifiManager.isWifiEnabled()) {
						isWifiEnabled = true;
					}
					
					// B. Check Location Services Status
					boolean isLocationEnabled = false;
					android.location.LocationManager locationManager = (android.location.LocationManager) getApplicationContext().getSystemService(android.content.Context.LOCATION_SERVICE);
					if (locationManager != null) {
						boolean isGpsEnabled = locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
						boolean isNetworkLocationEnabled = locationManager.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER);
						if (isGpsEnabled || isNetworkLocationEnabled) {
							isLocationEnabled = true;
						}
					}
					
					// C. Pipeline Navigation Router
					android.content.Intent targetIntent = new android.content.Intent();
					targetIntent.putExtra("selected_files_json", selectedFilesArray.toString());
					targetIntent.putExtra("transferType", "send");
					
					if (isWifiEnabled && isLocationEnabled) {
						targetIntent.setClass(getApplicationContext(), TransferSendActivity.class);
					} else {
						targetIntent.setClass(getApplicationContext(), TransferProcessPreparetionsActivity.class);
					}
					startActivity(targetIntent);
					overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
					
				} else {
					// Safety fallback message block
					android.widget.Toast.makeText(getApplicationContext(), "Please select files to send first", android.widget.Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		receive_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				// 1. Check Wi-Fi Status
				boolean isWifiEnabled = false;
				android.net.wifi.WifiManager wifiManager = (android.net.wifi.WifiManager) getApplicationContext().getSystemService(android.content.Context.WIFI_SERVICE);
				if (wifiManager != null && wifiManager.isWifiEnabled()) {
					isWifiEnabled = true;
				}
				
				// 2. Check Location Services (GPS) Status
				boolean isLocationEnabled = false;
				android.location.LocationManager locationManager = (android.location.LocationManager) getApplicationContext().getSystemService(android.content.Context.LOCATION_SERVICE);
				if (locationManager != null) {
					boolean isGpsEnabled = locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
					boolean isNetworkLocationEnabled = locationManager.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER);
					// Either GPS high accuracy or network location works for hardware discovery scanning
					if (isGpsEnabled || isNetworkLocationEnabled) {
						isLocationEnabled = true;
					}
				}
				
				// 3. Conditional Pipeline Navigation Router for Receiver
				android.content.Intent targetIntent = new android.content.Intent();
				
				// Pass the key identifying this device explicitly as a receiver
				targetIntent.putExtra("transferType", "receive");
				
				if (isWifiEnabled && isLocationEnabled) {
					// Direct flight: Hardware radios are turned on, go straight to scanner
					targetIntent.setClass(getApplicationContext(), TransferRecieverActivity.class);
				} else {
					// Layover: Guide the receiver to fix permission locks and toggles first
					targetIntent.setClass(getApplicationContext(), TransferProcessPreparetionsActivity.class);
				}
				
				startActivity(targetIntent);
				overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
			}
		});
		
		total_selected.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			final Window window = TransferActivity.this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(0xFF0B0D0F);
		}
		_changeActivityFont("ooo");
		_rippleRoundStroke(send_btn, "#141414", "#E0E0E0", 30, 0, "#000000");
		_rippleRoundStroke(receive_btn, "#141414", "#E0E0E0", 30, 0, "#000000");
		_rippleRoundStroke(total_selected, "#141414", "#E0E0E0", 30, 0, "#000000");
		total_selected.setVisibility(View.GONE);
		// Initialize selection count
		selected_count = 0;
		
		// 1. Initialize the dataset array safely
		if (files_list == null) {
			files_list = new java.util.ArrayList<>();
		}
		files_list.clear();
		
		// 2. Crawl local storage directories to parse metadata and file sizes
		try {
			java.io.File baseDir = getExternalFilesDir(null);
			if (baseDir != null) {
				java.io.File videoRootFolder = new java.io.File(baseDir, "Downloads/Videos");
				
				if (videoRootFolder.exists() && videoRootFolder.isDirectory()) {
					java.io.File[] folders = videoRootFolder.listFiles();
					
					if (folders != null) {
						for (java.io.File singleMovieFolder : folders) {
							if (singleMovieFolder.isDirectory()) {
								java.io.File metaFile = new java.io.File(singleMovieFolder, "metadata.json");
								
								if (metaFile.exists()) {
									java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(metaFile));
									java.lang.StringBuilder sb = new java.lang.StringBuilder();
									String line;
									while ((line = br.readLine()) != null) {
										sb.append(line);
									}
									br.close();
									
									org.json.JSONObject metaObj = new org.json.JSONObject(sb.toString());
									
									// Only get completed downloads
									if (!metaObj.optBoolean("is_downloading", false)) {
										String videoFileName = metaObj.optString("video_file_name", "");
										
										if (!videoFileName.isEmpty()) {
											java.io.File videoFile = new java.io.File(singleMovieFolder, videoFileName);
											
											// Verify the media container file exists
											if (videoFile.exists()) {
												long fileSizeBytes = videoFile.length();
												double fileSizeMB = fileSizeBytes / (1024.0 * 1024.0);
												String readableSize = String.format(java.util.Locale.US, "%.1f MB", fileSizeMB);
												
												java.util.HashMap<String, Object> completeMap = new java.util.HashMap<>();
												completeMap.put("title", metaObj.optString("title", "Unknown Movie"));
												completeMap.put("cover_url", metaObj.optString("cover_url", ""));
												completeMap.put("size", readableSize);
												completeMap.put("video_path", videoFile.getAbsolutePath());
												
												// Track selection state (default is false)
												completeMap.put("is_selected", "false");
												
												files_list.add(completeMap);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 3. Link Adapter and LayoutManager
		recyclerview1.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));
		recyclerview1.setAdapter(new Recyclerview1Adapter(files_list));
		
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
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.transfer_file_list, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final ImageView image = _view.findViewById(R.id.image);
			final TextView name = _view.findViewById(R.id.name);
			final TextView files_count_or_size_count = _view.findViewById(R.id.files_count_or_size_count);
			final RadioButton selection_radio_btn = _view.findViewById(R.id.selection_radio_btn);
			
			name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 0);
			files_count_or_size_count.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 0);
			selection_radio_btn.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 0);
			name.setSingleLine(true);
			name.setMaxLines(1);
			
			// Put ellipsis at the start
			name.setEllipsize(android.text.TextUtils.TruncateAt.END);
			
			// Make sure it actually truncates instead of scrolling
			name.setHorizontallyScrolling(false);
			// 1. Get the current map safely
			final java.util.HashMap<String, Object> map = files_list.get(_position);
			
			// 2. Map basic text data
			name.setText(map.get("title").toString());
			files_count_or_size_count.setText(map.get("size").toString());
			
			// 3. Load the image safely using Glide
			com.bumptech.glide.Glide.with(getApplicationContext())
			.load(map.get("cover_url").toString())
			.placeholder(R.drawable.background_banner)
			.into(image);
			
			// 4. Handle RadioButton state based on our HashMap
			final boolean isSelected = "true".equals(map.get("is_selected"));
			selection_radio_btn.setChecked(isSelected);
			
			// 5. Handle Clicks with smooth UI transitions and dynamic text counter
			android.view.View.OnClickListener toggleSelection = new android.view.View.OnClickListener() {
				@Override
				public void onClick(android.view.View _view) {
					// Toggle state in data source
					if (isSelected) {
						map.put("is_selected", "false");
					} else {
						map.put("is_selected", "true");
					}
					
					// Notify row changed immediately
					recyclerview1.getAdapter().notifyItemChanged(_position);
					
					// Recalculate total selection from data source safely
					int tempCount = 0;
					for (java.util.HashMap<String, Object> item : files_list) {
						if ("true".equals(item.get("is_selected"))) {
							tempCount++;
						}
					}
					selected_count = tempCount;
					
					// Apply slick layout transition changes inside bottom_holder wrapper
					androidx.transition.TransitionManager.beginDelayedTransition(bottom_holder, new androidx.transition.AutoTransition());
					
					if (selected_count > 0) {
						// Update the text view string layout to show the absolute count
						total_selected_txt.setText("Selected: " + (int)selected_count);
						
						// Hide normal bottom button, show selection metrics layout
						receive_btn.setVisibility(android.view.View.GONE);
						total_selected.setVisibility(android.view.View.VISIBLE);
					} else {
						// Revert back to original layout configuration if nothing is chosen
						total_selected.setVisibility(android.view.View.GONE);
						receive_btn.setVisibility(android.view.View.VISIBLE);
					}
				}
			};
			
			linear1.setOnClickListener(toggleSelection);
			selection_radio_btn.setOnClickListener(toggleSelection);
			
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