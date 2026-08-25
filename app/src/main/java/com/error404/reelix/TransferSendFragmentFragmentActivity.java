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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
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
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class TransferSendFragmentFragmentActivity extends Fragment {
	
	private ArrayList<HashMap<String, Object>> send_trans_list = new ArrayList<>();
	
	private LinearLayout linear1;
	private RecyclerView send_rec_details_view;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.transfer_send_fragment_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		send_rec_details_view = _view.findViewById(R.id.send_rec_details_view);
	}
	
	private void initializeLogic() {
		// 1. Set up the RecyclerView directly pointing to the centralized helper list
		send_rec_details_view.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getContext()));
		send_rec_details_view.setAdapter(new Send_rec_details_viewAdapter(com.error404.reelix.TransferStateManager.send_trans_list));
		
		// 2. Register the UI Update Listener to refresh the items as bytes stream over sockets
		com.error404.reelix.TransferStateManager.setSendListener(new com.error404.reelix.TransferStateManager.UIUpdateListener() {
			@Override
			public void onDataUpdated() {
				// Ensure UI updates run safely on the main UI thread context
				if (getActivity() != null) {
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if (send_rec_details_view.getAdapter() != null) {
								send_rec_details_view.getAdapter().notifyDataSetChanged();
							}
						}
					});
				}
			}
		});
		
	}
	
	public class Send_rec_details_viewAdapter extends RecyclerView.Adapter<Send_rec_details_viewAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Send_rec_details_viewAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.transfer_item_rec_list, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout main = _view.findViewById(R.id.main);
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView title = _view.findViewById(R.id.title);
			final TextView size_progress = _view.findViewById(R.id.size_progress);
			final ProgressBar progressbar = _view.findViewById(R.id.progressbar);
			final TextView receiving_txt = _view.findViewById(R.id.receiving_txt);
			
			title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 1);
			size_progress.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			receiving_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			// Extract the current item from the shared State Manager
			java.util.HashMap<String, Object> currentItem = com.error404.reelix.TransferStateManager.send_trans_list.get(_position);
			
			// Set Data
			title.setText(currentItem.containsKey("title") ? currentItem.get("title").toString() : "Unknown Movie");
			
			// Handle Progress & Size Text formatting (e.g. "45% / 300.5MB")
			String progressVal = currentItem.containsKey("progress") ? currentItem.get("progress").toString() : "0";
			String sizeVal = currentItem.containsKey("size") ? currentItem.get("size").toString() : "0MB";
			size_progress.setText(progressVal + "% / " + sizeVal);
			
			// Handle ProgressBar width
			try {
				progressbar.setProgress(Integer.parseInt(progressVal));
			} catch (Exception e) {
				progressbar.setProgress(0);
			}
			
			// Handle Status Text
			String statusText = currentItem.containsKey("status") ? currentItem.get("status").toString() : "Sending";
			receiving_txt.setText(statusText);
			// Optional: Make Sender text blue instead of red to differentiate
			receiving_txt.setTextColor(android.graphics.Color.parseColor("#1976D2"));
			
			// Handle Glide Image Loading
			String imageUrl = currentItem.containsKey("cover_url") ? currentItem.get("cover_url").toString() : "";
			if (!imageUrl.isEmpty()) {
				com.bumptech.glide.Glide.with(getContext())
				.load(imageUrl)
				.placeholder(R.drawable.background_banner) // Your default XML image
				.error(R.drawable.background_banner)
				.into(imageview1);
			} else {
				imageview1.setImageResource(R.drawable.background_banner);
			}
			
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