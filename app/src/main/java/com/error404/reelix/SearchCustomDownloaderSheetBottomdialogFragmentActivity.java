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
import androidx.cardview.widget.CardView;
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
import com.google.android.material.*;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.*;
import com.google.android.material.card.*;
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

public class SearchCustomDownloaderSheetBottomdialogFragmentActivity extends BottomSheetDialogFragment {
	
	private String quality_value = "";
	private String download_url = "";
	private String inputed_url = "";
	private String thumbnail_url_field = "";
	
	private ArrayList<HashMap<String, Object>> quality_listmap = new ArrayList<>();
	
	private LinearLayout main_back;
	private NestedScrollView nestedScrollView2;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private MaterialCardView cardview1;
	private LinearLayout linear17;
	private LinearLayout linear7;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private MaterialButton download_btn;
	private LinearLayout linear15;
	private MaterialCardView materialCardView1;
	private TextView textview1;
	private BlurView blurView2;
	private LinearLayout linear5;
	private LinearLayout linear3;
	private CardView cardview3;
	private ImageView banner;
	private TextView File_name_txt;
	private TextView textview2;
	private MaterialCardView materialCardView4;
	private LinearLayout linear8;
	private MaterialCardView selection_inner_item1;
	private LinearLayout middle_sep;
	private MaterialCardView selection_inner_item2;
	private LinearLayout linear9;
	private ImageView item_1_selected_ic;
	private TextView textview3;
	private LinearLayout linear11;
	private ImageView item_2_selected_ic;
	private TextView textview4;
	private TextView textview5;
	private RecyclerView quality_rec;
	
	private RequestNetwork get_qualities;
	private RequestNetwork.RequestListener _get_qualities_request_listener;
	private RequestNetwork get_dowload_details;
	private RequestNetwork.RequestListener _get_dowload_details_request_listener;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.search_custom_downloader_sheet_bottomdialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		main_back = _view.findViewById(R.id.main_back);
		nestedScrollView2 = _view.findViewById(R.id.nestedScrollView2);
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
		linear4 = _view.findViewById(R.id.linear4);
		cardview1 = _view.findViewById(R.id.cardview1);
		linear17 = _view.findViewById(R.id.linear17);
		linear7 = _view.findViewById(R.id.linear7);
		linear12 = _view.findViewById(R.id.linear12);
		linear13 = _view.findViewById(R.id.linear13);
		download_btn = _view.findViewById(R.id.download_btn);
		linear15 = _view.findViewById(R.id.linear15);
		materialCardView1 = _view.findViewById(R.id.materialCardView1);
		textview1 = _view.findViewById(R.id.textview1);
		blurView2 = _view.findViewById(R.id.blurView2);
		linear5 = _view.findViewById(R.id.linear5);
		linear3 = _view.findViewById(R.id.linear3);
		cardview3 = _view.findViewById(R.id.cardview3);
		banner = _view.findViewById(R.id.banner);
		File_name_txt = _view.findViewById(R.id.File_name_txt);
		textview2 = _view.findViewById(R.id.textview2);
		materialCardView4 = _view.findViewById(R.id.materialCardView4);
		linear8 = _view.findViewById(R.id.linear8);
		selection_inner_item1 = _view.findViewById(R.id.selection_inner_item1);
		middle_sep = _view.findViewById(R.id.middle_sep);
		selection_inner_item2 = _view.findViewById(R.id.selection_inner_item2);
		linear9 = _view.findViewById(R.id.linear9);
		item_1_selected_ic = _view.findViewById(R.id.item_1_selected_ic);
		textview3 = _view.findViewById(R.id.textview3);
		linear11 = _view.findViewById(R.id.linear11);
		item_2_selected_ic = _view.findViewById(R.id.item_2_selected_ic);
		textview4 = _view.findViewById(R.id.textview4);
		textview5 = _view.findViewById(R.id.textview5);
		quality_rec = _view.findViewById(R.id.quality_rec);
		get_qualities = new RequestNetwork((Activity) getContext());
		get_dowload_details = new RequestNetwork((Activity) getContext());
		
		download_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		selection_inner_item1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		selection_inner_item2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		_get_qualities_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				try {
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					
					if (responseObj.optBoolean("success", false) && responseObj.has("data")) {
						org.json.JSONObject dataObj = responseObj.getJSONObject("data");
						org.json.JSONArray qualitiesArray = dataObj.getJSONArray("qualities");
						
						quality_listmap.clear();
						
						for (int i = 0; i < qualitiesArray.length(); i++) {
							java.util.HashMap<String, Object> map = new java.util.HashMap<>();
							map.put("quality_name", qualitiesArray.getString(i));
							quality_listmap.add(map);
						}
						
						quality_rec.setAdapter(new Quality_recAdapter(quality_listmap));
						quality_rec.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
					} else {
						String serverError = responseObj.optString("message", "Unknown API error");
						android.widget.Toast.makeText(getContext().getApplicationContext(), "Quality Error: " + serverError, android.widget.Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
					android.widget.Toast.makeText(getContext().getApplicationContext(), "Error parsing qualities: " + e.getMessage(), android.widget.Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_get_dowload_details_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				try {
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					
					if (responseObj.optBoolean("success", false) && responseObj.has("data")) {
						org.json.JSONObject dataObj = responseObj.getJSONObject("data");
						
						download_url = dataObj.optString("download_url", "");
						File_name_txt.setText(dataObj.optString("title", ""));
						
						Glide.with(banner)
						.load(dataObj.optString("thumbnail"))
						.placeholder(R.drawable.background_banner)
						.error(R.drawable.background_banner)
						.fallback(R.drawable.background_banner)
						.into(banner);
						
						download_btn.setText("Download");
					} else {
						String serverError = responseObj.optString("message", "Unknown API error");
						android.widget.Toast.makeText(getContext().getApplicationContext(), "Fetch Error: " + serverError, android.widget.Toast.LENGTH_SHORT).show();
						download_btn.setText("Retry");
					}
				} catch (Exception e) {
					e.printStackTrace();
					android.widget.Toast.makeText(getContext().getApplicationContext(), "Error parsing download details: " + e.getMessage(), android.widget.Toast.LENGTH_SHORT).show();
					download_btn.setText("Retry");
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
		main_back.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFF151C22));
		textview1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/sans.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/sans.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
		textview5.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/sans.ttf"), 0);
		download_btn.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/sans.ttf"), 0);
		final float density = getResources().getDisplayMetrics().density;
		final float defaultRadius = 28f * density;  // Circular shape
		final float pressedRadius = 8f * density;   // Squircle/square shape
		
		if (download_btn != null) {
			// Set initial circular shape
			download_btn.setShapeAppearanceModel(download_btn.getShapeAppearanceModel().toBuilder()
			.setAllCornerSizes(defaultRadius)
			.build());
			
			// Add touch listener for morphing animation
			download_btn.setOnTouchListener(new android.view.View.OnTouchListener() {
				private android.animation.ValueAnimator animator;
				
				@Override
				public boolean onTouch(android.view.View v, android.view.MotionEvent event) {
					
					if (v.getParent() != null) {
						v.getParent().requestDisallowInterceptTouchEvent(true);
					}
					
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
						startAnim(pressedRadius, 100, new android.view.animation.DecelerateInterpolator());
					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP ||
					event.getAction() == android.view.MotionEvent.ACTION_CANCEL) {
						startAnim(defaultRadius, 300, new android.view.animation.AccelerateDecelerateInterpolator());
					}
					
					return false;
				}
				
				private void startAnim(float target, int duration, android.view.animation.Interpolator interpolator) {
					if (animator != null && animator.isRunning()) {
						animator.cancel();
					}
					
					android.graphics.RectF rect = new android.graphics.RectF(0, 0, download_btn.getWidth(), download_btn.getHeight());
					float startVal = download_btn.getShapeAppearanceModel().getBottomLeftCornerSize().getCornerSize(rect);
					
					animator = android.animation.ValueAnimator.ofFloat(startVal, target);
					animator.setDuration(duration);
					animator.setInterpolator(interpolator);
					
					animator.addUpdateListener(animation -> {
						float value = (float) animation.getAnimatedValue();
						download_btn.setShapeAppearanceModel(download_btn.getShapeAppearanceModel().toBuilder()
						.setAllCornerSizes(value)
						.build());
					});
					animator.start();
				}
			});
		}
		android.os.Bundle args = getArguments();
		
		String thumbnailUrl = "";
		String titleText = "";
		
		if (args != null) {
			thumbnailUrl = args.getString("thumbnail", "");
			titleText = args.getString("title", "");
			inputed_url = args.getString("url", "");
			quality_value = args.getString("quality", "360");
		}
		
		Glide.with(banner)
		.load(thumbnailUrl)
		.placeholder(R.drawable.background_banner)
		.error(R.drawable.background_banner)
		.fallback(R.drawable.background_banner)
		.into(banner);
		
		File_name_txt.setText(titleText);
		
		selection_inner_item1.setCardBackgroundColor(Color.parseColor("#364954"));
		selection_inner_item2.setCardBackgroundColor(Color.parseColor("#00000000"));
		item_2_selected_ic.setVisibility(View.GONE);
		item_1_selected_ic.setVisibility(View.VISIBLE);
		
		selection_inner_item1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ObjectAnimator fadeOut1 = ObjectAnimator.ofFloat(selection_inner_item2, "alpha", 1f, 0.3f, 1f);
				fadeOut1.setDuration(250);
				fadeOut1.start();
				selection_inner_item1.setCardBackgroundColor(Color.parseColor("#364954"));
				selection_inner_item2.setCardBackgroundColor(Color.parseColor("#00000000"));
				
				_swapIcon(item_1_selected_ic, item_2_selected_ic);
			}
		});
		
		selection_inner_item2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ObjectAnimator fadeOut2 = ObjectAnimator.ofFloat(selection_inner_item1, "alpha", 1f, 0.3f, 1f);
				fadeOut2.setDuration(250);
				fadeOut2.start();
				selection_inner_item2.setCardBackgroundColor(Color.parseColor("#364954"));
				selection_inner_item1.setCardBackgroundColor(Color.parseColor("#00000000"));
				
				_swapIcon(item_2_selected_ic, item_1_selected_ic);
			}
		});
		
		java.util.HashMap<String, Object> qualityParams = new java.util.HashMap<>();
		qualityParams.put("url", inputed_url);
		
		java.util.HashMap<String, Object> qualityHeaders = new java.util.HashMap<>();
		qualityHeaders.put("x-api-key", "516577400478683");
		qualityHeaders.put("accept", "application/json");
		
		get_qualities.setHeaders(qualityHeaders);
		get_qualities.setParams(qualityParams, RequestNetworkController.REQUEST_BODY);
		get_qualities.startRequestNetwork(RequestNetworkController.POST, "https://error404-api.vercel.app/api/dl/yt/qualities", "GetQualities", _get_qualities_request_listener);
		
		download_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (download_url == null || download_url.isEmpty()) {
					return;
				}
				
				String selectedType = item_1_selected_ic.getVisibility() == View.VISIBLE ? "video" : "audio";
				String extension = quality_value.equalsIgnoreCase("mp3") ? "mp3" : "mp4";
				
				ReelixPublicDownloadHelper.startPublicDownload(
				getContext().getApplicationContext(),
				download_url,
				File_name_txt.getText().toString(),
				quality_value,
				selectedType,
				extension,
				thumbnail_url_field,
				String.valueOf(System.currentTimeMillis())
				);
				
				android.widget.Toast.makeText(getContext().getApplicationContext(), "Download started", android.widget.Toast.LENGTH_SHORT).show();
				dismiss();
			}
		});
	}
	
	public void _swapIcon(final View _showIcon, final View _hideIcon) {
		
		_showIcon.setVisibility(View.VISIBLE);
		_showIcon.setAlpha(0f);
		_showIcon.setTranslationX(30f);
		_showIcon.setTranslationY(20f);
		_showIcon.animate()
		.alpha(1f)
		.translationX(0f)
		.translationY(0f)
		.setDuration(280)
		.setInterpolator(new android.view.animation.DecelerateInterpolator())
		.start();
		
		_hideIcon.animate()
		.alpha(0f)
		.translationX(30f)
		.translationY(-20f)
		.setDuration(200)
		.setInterpolator(new android.view.animation.AccelerateInterpolator())
		.withEndAction(new Runnable() {
			@Override
			public void run() {
				_hideIcon.setVisibility(View.GONE);
				_hideIcon.setTranslationX(0f);
				_hideIcon.setTranslationY(0f);
				_hideIcon.setAlpha(1f);
			}
		})
		.start();
	}
	
	
	public void _fetchDownloadDetails(final String _selectedQuality) {
		
		download_btn.setText("Fetch");
		
		java.util.HashMap<String, Object> downloadDetailParams = new java.util.HashMap<>();
		downloadDetailParams.put("url", inputed_url);
		downloadDetailParams.put("quality", _selectedQuality);
		
		java.util.HashMap<String, Object> downloadDetailHeaders = new java.util.HashMap<>();
		downloadDetailHeaders.put("x-api-key", "516577400478683");
		downloadDetailHeaders.put("accept", "application/json");
		
		get_dowload_details.setHeaders(downloadDetailHeaders);
		get_dowload_details.setParams(downloadDetailParams, RequestNetworkController.REQUEST_BODY);
		get_dowload_details.startRequestNetwork(RequestNetworkController.POST, "https://error404-api.vercel.app/api/dl/yt/download", "DownloadDetails", _get_dowload_details_request_listener);
	}
	
	public class Quality_recAdapter extends RecyclerView.Adapter<Quality_recAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Quality_recAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.small_quality_pills, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView pillHolder = _view.findViewById(R.id.pillHolder);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final TextView quality_txt = _view.findViewById(R.id.quality_txt);
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			quality_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/fontawesome.ttf"), 1);
			java.util.HashMap<String, Object> currentItem = _data.get((int)_position);
			
			String qualityName = String.valueOf(currentItem.get("quality_name"));
			quality_txt.setText(qualityName);
			
			if (qualityName.equals(quality_value)) {
				pillHolder.setCardBackgroundColor(0xFF4E90C6);
			} else {
				pillHolder.setCardBackgroundColor(0xFF003548);
			}
			
			pillHolder.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					quality_value = qualityName;
					notifyDataSetChanged();
					_fetchDownloadDetails(qualityName);
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