package com.error404.reelix;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.google.android.material.divider.MaterialDivider;
import com.google.android.material.textfield.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.*;
import com.google.zxing.client.android.*;
import de.hdodenhof.circleimageview.*;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class UpdateProfileActivity extends AppCompatActivity {
	
	public final int REQ_CD_FP = 101;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String fontName = "";
	private String typeace = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String EncodedFileString = "";
	private HashMap<String, Object> upload_map = new HashMap<>();
	private String imageUrl = "";
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private MaterialDivider materialDivider3;
	private LinearLayout linear4;
	private ImageView imageview1;
	private TextView textview1;
	private TextView textview2;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear9;
	private TextInputLayout textinputlayout1;
	private RelativeLayout relativelayout1;
	private CircleImageView circleimageview1;
	private LinearLayout linear8;
	private ImageView imageview2;
	private EditText edittext1;
	
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
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent intent = new Intent();
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.update_profile);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		materialDivider3 = findViewById(R.id.materialDivider3);
		linear4 = findViewById(R.id.linear4);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear9 = findViewById(R.id.linear9);
		textinputlayout1 = findViewById(R.id.textinputlayout1);
		relativelayout1 = findViewById(R.id.relativelayout1);
		circleimageview1 = findViewById(R.id.circleimageview1);
		linear8 = findViewById(R.id.linear8);
		imageview2 = findViewById(R.id.imageview2);
		edittext1 = findViewById(R.id.edittext1);
		auth = FirebaseAuth.getInstance();
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		net = new RequestNetwork(this);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				onBackPressed();
			}
		});
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!edittext1.getText().toString().equals("")) {
					map = new HashMap<>();
					map.put("username", edittext1.getText().toString());
					if (!imageUrl.equals("")) {
						map.put("profile_pic", imageUrl);
					}
					users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
					com.google.android.material.snackbar.Snackbar.make(linear1, "Profile info updated successfully", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener(){
						@Override
						public void onClick(View _view) {
							intent.setClass(getApplicationContext(), ProfileActivity.class);
							startActivity(intent);
							overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
							finish();
						}
					}).show();
				}
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
			}
		});
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		users.addChildEventListener(_users_child_listener);
		
		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				imageUrl = "https://drive.google.com/uc?id=".concat(_response);
				_customLoading(false);
				com.google.android.material.snackbar.Snackbar.make(linear1, "Image uploaded successfully", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
					@Override
					public void onClick(View _view) {
						
					}
				}).show();
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				_customLoading(false);
				com.google.android.material.snackbar.Snackbar.make(linear1, "Upload failed : ".concat(_message), com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener(){
					@Override
					public void onClick(View _view) {
						
					}
				}).show();
			}
		};
		
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
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			final Window window = UpdateProfileActivity.this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(0xFF0B0D0F);
		}
		_changeActivityFont("ints");
		// 1. Check if the user is authenticated
		if (FirebaseAuth.getInstance().getCurrentUser() != null) {
			// Get the current user's unique ID
			String currentUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
			
			// 2. Query the "users" node for username + profile_pic
			FirebaseDatabase.getInstance().getReference("users")
			.child(currentUid)
			.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(@NonNull DataSnapshot snapshot) {
					// Username
					if (snapshot.exists() && snapshot.hasChild("username")) {
						String username = snapshot.child("username").getValue(String.class);
						edittext1.setText(username);
					} else {
						edittext1.setText("");
					}
					
					// Profile picture
					if (snapshot.exists() && snapshot.hasChild("profile_pic")) {
						String profilePic = snapshot.child("profile_pic").getValue(String.class);
						if (profilePic != null && !profilePic.isEmpty()) {
							Glide.with(getApplicationContext()).load(Uri.parse(profilePic)).into(circleimageview1);
						} else {
							circleimageview1.setImageResource(R.drawable.user);
						}
					} else {
						circleimageview1.setImageResource(R.drawable.user);
					}
				}
				
				@Override
				public void onCancelled(@NonNull DatabaseError error) {
					edittext1.setText("");
					circleimageview1.setImageResource(R.drawable.user);
					SketchwareUtil.showMessage(getApplicationContext(), "Database Error: " + error.getMessage());
				}
			});
		} else {
			/*
    intent.setClass(getApplicationContext(), LoginActivity.class);
    startActivity(intent);
    finish();
    */
			edittext1.setText("");
			circleimageview1.setImageResource(R.drawable.user);
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FP:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				circleimageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
				EncodedFileString = readFileBase64(_filePath.get((int)(0)));
				upload_map = new HashMap<>();
				upload_map.put("filename", Uri.parse(_filePath.get((int)(0))).getLastPathSegment());
				upload_map.put("data", EncodedFileString);
				net.setParams(upload_map, RequestNetworkController.REQUEST_PARAM);
				net.startRequestNetwork(RequestNetworkController.POST, "https://script.google.com/macros/s/AKfycbzo23vJszMHABbx8LlILWdjNpBg76aDy0cUegIusvBoDY3wMl59T3FLDA7Y07zOJlxO/exec", "upload", _net_request_listener);
				_customLoading(true);
				//https://script.google.com/macros/s/AKfycbwdIpvQj7w2ctaENTGOy84vZW4gnDW6Lh0eIxDeukSboidhV33dmOECW1ObKA7lYCvo/exec
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
	
	
	public void _extra() {
	}
	public String readFileBase64(String path){
		String filePath = path;
		String outData = "";
		
		java.io.File file = new java.io.File(filePath);
		try (java.io.FileInputStream InFile = new java.io.FileInputStream(file)) {
			// Reading a file from file system
			byte fileData[] = new byte[(int) file.length()];
			InFile.read(fileData);
			byte[] encodedBytesFile = org.apache.commons.codec.binary.Base64.encodeBase64(fileData);
			
			outData = new String(encodedBytesFile);
		} catch (java.io.FileNotFoundException e) {
			SketchwareUtil.showMessage(getApplicationContext(), "File not found" + e);
		} catch (java.io.IOException ioe) {
			SketchwareUtil.showMessage(getApplicationContext(),"Exception while reading the File " + ioe);
		}
		return outData;
	}
	public void haha_lol () {
	}
	
}