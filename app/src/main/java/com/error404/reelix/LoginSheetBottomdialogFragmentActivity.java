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
import android.widget.ProgressBar;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.*;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.*;
import com.google.zxing.client.android.*;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class LoginSheetBottomdialogFragmentActivity extends BottomSheetDialogFragment {
	
	private LinearLayout main;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ImageView imageview1;
	private LinearLayout linear3;
	private TextInputLayout textinputlayout2;
	private TextInputLayout textinputlayout3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout login_btn;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout sep;
	private EditText edittext1;
	private EditText edittext2;
	private TextView textview1;
	private ProgressBar progressbar1;
	private TextView result_txt;
	
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.login_sheet_bottomdialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		main = _view.findViewById(R.id.main);
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
		imageview1 = _view.findViewById(R.id.imageview1);
		linear3 = _view.findViewById(R.id.linear3);
		textinputlayout2 = _view.findViewById(R.id.textinputlayout2);
		textinputlayout3 = _view.findViewById(R.id.textinputlayout3);
		linear4 = _view.findViewById(R.id.linear4);
		linear5 = _view.findViewById(R.id.linear5);
		login_btn = _view.findViewById(R.id.login_btn);
		linear6 = _view.findViewById(R.id.linear6);
		linear7 = _view.findViewById(R.id.linear7);
		linear8 = _view.findViewById(R.id.linear8);
		linear9 = _view.findViewById(R.id.linear9);
		sep = _view.findViewById(R.id.sep);
		edittext1 = _view.findViewById(R.id.edittext1);
		edittext2 = _view.findViewById(R.id.edittext2);
		textview1 = _view.findViewById(R.id.textview1);
		progressbar1 = _view.findViewById(R.id.progressbar1);
		result_txt = _view.findViewById(R.id.result_txt);
		auth = FirebaseAuth.getInstance();
		
		login_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				result_txt.setVisibility(View.GONE);
				// 1. Clear previous errors first
				textinputlayout2.setError(null);
				textinputlayout3.setError(null);
				
				// 2. Get input values
				String email = edittext1.getText().toString().trim();
				String password = edittext2.getText().toString().trim();
				
				// 3. Validation Logic using setError
				if (email.isEmpty()) {
					textinputlayout2.setError("Enter email");
					edittext1.requestFocus();
					return;
				}
				
				if (password.isEmpty()) {
					textinputlayout3.setError("Enter password");
					edittext2.requestFocus();
					return;
				}
				
				if (password.length() < 6) {
					textinputlayout3.setError("Password must be at least 6 characters");
					edittext2.requestFocus();
					return;
				}
				
				// 4. UI Feedback
				textview1.setVisibility(View.GONE);
				progressbar1.setVisibility(View.VISIBLE);
				
				// 5. Firebase Login Logic
				// Changed 'createUser...' to 'signIn...'
				auth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener(getActivity(), _auth_sign_in_listener);
			}
		});
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					textview1.setVisibility(View.VISIBLE);
					progressbar1.setVisibility(View.GONE);
					result_txt.setVisibility(View.VISIBLE);
					result_txt.setTextColor(0xFF4CAF50);
					result_txt.setText("Login successful, welcome back ".concat(edittext1.getText().toString()));
					Intent intent = new Intent();
					intent.setClass(getActivity(), FeedActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // Optional: prevents multiple instances
					startActivity(intent);
					
					// If you want to close the BottomSheet after navigating
					dismiss(); 
					
					// If you want to close the previous activity (like Login) so they can't go back
					getActivity().finish(); 
				} else {
					textview1.setVisibility(View.VISIBLE);
					progressbar1.setVisibility(View.GONE);
					result_txt.setVisibility(View.VISIBLE);
					result_txt.setTextColor(0xFFF44336);
					result_txt.setText(_errorMessage);
				}
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		main.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)40, (int)2, 0xFF424242, 0xFF0B0D0F));
		sep.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)60, 0xFF424242));
		textinputlayout2.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED);
		textinputlayout2.setBoxBackgroundColor(Color.TRANSPARENT);
		textinputlayout2.setBoxStrokeColor(0xFFB71C1C);
		
		textinputlayout3.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED);
		textinputlayout3.setBoxBackgroundColor(Color.TRANSPARENT);
		textinputlayout3.setBoxStrokeColor(0xFFB71C1C);
		edittext1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
		edittext2.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
		textview1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 1);
		edittext1.setTextColor(0xFFF4F5FC);
		edittext2.setTextColor(0xFFF4F5FC);
		// 1. Create the background (Transparent with a slim #212121 border)
		android.graphics.drawable.GradientDrawable background = new android.graphics.drawable.GradientDrawable();
		background.setColor(Color.TRANSPARENT);
		background.setStroke(3, 0xFF212121); // '3' is the thickness in pixels; #212121 is the color
		background.setCornerRadius(20); // Optional: Adjust this to match your UI's roundness
		
		// 2. Create the Ripple Effect (#E0E0E0)
		// This creates a ColorStateList for the ripple color
		int[][] states = new int[][] { new int[] { android.R.attr.state_enabled } };
		int[] colors = new int[] { 0xFFE0E0E0 };
		android.content.res.ColorStateList rippleColor = new android.content.res.ColorStateList(states, colors);
		
		// 3. Apply the RippleDrawable to the button
		// The third parameter (background) acts as the mask so the ripple stays inside the button
		android.graphics.drawable.RippleDrawable rippleDrawable = new android.graphics.drawable.RippleDrawable(rippleColor, background, background);
		
		login_btn.setBackground(rippleDrawable);
		login_btn.setElevation(0); // Optional: Removes shadow to keep the "slim/flat" look
		progressbar1.setVisibility(View.GONE);
		result_txt.setVisibility(View.GONE);
	}
	
}