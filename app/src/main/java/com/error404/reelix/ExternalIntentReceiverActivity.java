package com.error404.reelix;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
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
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class ExternalIntentReceiverActivity extends AppCompatActivity {
	
	private String downloadUrl = "";
	
	private RequestNetwork custom_download;
	private RequestNetwork.RequestListener _custom_download_request_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.external_intent_receiver);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		custom_download = new RequestNetwork(this);
		
		_custom_download_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				_customLoading(false);
				
				try {
					org.json.JSONObject responseObj = new org.json.JSONObject(_response);
					
					if (responseObj.optBoolean("success", false) && responseObj.has("data")) {
						org.json.JSONObject dataObj = responseObj.getJSONObject("data");
						
						SearchCustomDownloaderSheetBottomdialogFragmentActivity bottomSheet = new SearchCustomDownloaderSheetBottomdialogFragmentActivity();
						bottomSheet.setStyle(androidx.fragment.app.DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme);
						
						android.os.Bundle bundle = new android.os.Bundle();
						bundle.putString("title", dataObj.optString("title", ""));
						bundle.putString("type", dataObj.optString("type", ""));
						bundle.putString("format", dataObj.optString("format", ""));
						bundle.putString("quality", dataObj.optString("quality", ""));
						bundle.putString("thumbnail", dataObj.optString("thumbnail", ""));
						bundle.putString("download_url", dataObj.optString("download_url", ""));
						bundle.putString("video_id", dataObj.optString("video_id", ""));
						bundle.putString("duration_seconds", String.valueOf(dataObj.optLong("duration_seconds", 0)));
						bundle.putString("job_id", dataObj.optString("job_id", ""));
						bundle.putString("url", downloadUrl);
						bottomSheet.setArguments(bundle);
						
						bottomSheet.show(getSupportFragmentManager(), "CustomDownloaderSheet");
					} else {
						String serverError = responseObj.optString("message", "Unknown API error");
						android.widget.Toast.makeText(getApplicationContext(), "Download Error: " + serverError, android.widget.Toast.LENGTH_SHORT).show();
						finish();
					}
				} catch (Exception e) {
					e.printStackTrace();
					android.widget.Toast.makeText(getApplicationContext(), "Error parsing download: " + e.getMessage(), android.widget.Toast.LENGTH_SHORT).show();
					finish();
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				_customLoading(false);
			}
		};
	}
	
	private void initializeLogic() {
		String sharedText = "";
		if (getIntent() != null && "android.intent.action.SEND".equals(getIntent().getAction())) {
			sharedText = getIntent().getStringExtra(android.content.Intent.EXTRA_TEXT);
		}
		
		if (sharedText == null || sharedText.trim().isEmpty()) {
			android.widget.Toast.makeText(getApplicationContext(), "No link found to download", android.widget.Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		
		String extractedUrl = "";
		java.util.regex.Pattern urlPattern = java.util.regex.Pattern.compile("(https?://\\S+)");
		java.util.regex.Matcher urlMatcher = urlPattern.matcher(sharedText);
		if (urlMatcher.find()) {
			extractedUrl = urlMatcher.group(1);
		}
		
		if (extractedUrl.isEmpty()) {
			android.widget.Toast.makeText(getApplicationContext(), "No valid link found", android.widget.Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		
		final String downloadUrl = extractedUrl;
		
		boolean isYoutube = downloadUrl.contains("youtube.com") || downloadUrl.contains("youtu.be");
		boolean isTiktok = downloadUrl.contains("tiktok.com");
		
		if (!isYoutube && !isTiktok) {
			android.widget.Toast.makeText(getApplicationContext(), "Please share a valid YouTube or TikTok link", android.widget.Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		
		_customLoading(true);
		
		java.util.HashMap<String, Object> downloadParams = new java.util.HashMap<>();
		downloadParams.put("url", downloadUrl);
		downloadParams.put("quality", "360");
		
		java.util.HashMap<String, Object> downloadHeaders = new java.util.HashMap<>();
		downloadHeaders.put("x-api-key", "516577400478683");
		downloadHeaders.put("accept", "application/json");
		
		custom_download.setHeaders(downloadHeaders);
		custom_download.setParams(downloadParams, RequestNetworkController.REQUEST_BODY);
		
		if (isYoutube) {
			custom_download.startRequestNetwork(RequestNetworkController.POST, "https://error404-api.vercel.app/api/dl/yt/download", "CustomDownload", _custom_download_request_listener);
		} else {
			custom_download.startRequestNetwork(RequestNetworkController.POST, "https://error404-api.vercel.app/api/dl/tiktok/download", "CustomDownload", _custom_download_request_listener);
		}
	}
	
	public void _customLoading(final boolean _visibility) {
		try {
			// Get the global root container of the current activity
			android.view.ViewGroup rootContainer = (android.view.ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
			
			// Attempt to find an already inflated instance using a unique tag string
			View inflatedLoadingView = rootContainer.findViewWithTag("built_in_loading_overlay");
			
			if (_visibility) {
				if (inflatedLoadingView == null) {
					// Inflate directly since it doesn't exist yet
					inflatedLoadingView = getLayoutInflater().inflate(R.layout.custom_loading, null);
					inflatedLoadingView.setTag("built_in_loading_overlay");
					
					// Block touches behind the overlay layer
					inflatedLoadingView.setClickable(true);
					inflatedLoadingView.setFocusable(true);
					
					// Inject it globally over the activity layout
					rootContainer.addView(inflatedLoadingView);
				}
				
				// Target layouts inside custom_loading.xml
				LinearLayout mainLayout = inflatedLoadingView.findViewById(R.id.main);
				com.airbnb.lottie.LottieAnimationView lottieView = inflatedLoadingView.findViewById(R.id.lottie1);
				
				mainLayout.setBackgroundColor(Color.parseColor("#80000000")); 
				inflatedLoadingView.setVisibility(View.VISIBLE);
				lottieView.playAnimation();
			} else {
				// If false is called and the view exists, clean it up
				if (inflatedLoadingView != null) {
					com.airbnb.lottie.LottieAnimationView lottieView = inflatedLoadingView.findViewById(R.id.lottie1);
					if (lottieView != null) {
						lottieView.cancelAnimation();
					}
					inflatedLoadingView.setVisibility(View.GONE);
					
					// Completely detach it from the view hierarchy to free up RAM
					rootContainer.removeView(inflatedLoadingView);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}