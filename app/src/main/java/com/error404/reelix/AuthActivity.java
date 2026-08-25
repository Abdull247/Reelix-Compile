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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.*;
import com.google.android.material.button.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.zxing.*;
import com.google.zxing.client.android.*;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import androidx.core.view.WindowCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;
import android.content.res.Configuration;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthActivity extends AppCompatActivity {
	
	public final int REQ_CD_GOOGLE_LOGIN = 101;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String fontName = "";
	private String typeace = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String token = "";
	
	private LinearLayout main;
	private RelativeLayout relativelayout1;
	private ImageView background_banner;
	private LinearLayout top_layer;
	private LinearLayout linear3;
	private TextView Text_title;
	private LinearLayout linear6;
	private MaterialButton button1;
	
	private Intent intent = new Intent();
	private GoogleSignInClient google_login;
	private DatabaseReference user = _firebase.getReference("user");
	private ChildEventListener _user_child_listener;
	private DatabaseReference plan = _firebase.getReference("plan");
	private ChildEventListener _plan_child_listener;
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
	private OnCompleteListener cloud_onCompleteListener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.auth);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main = findViewById(R.id.main);
		relativelayout1 = findViewById(R.id.relativelayout1);
		background_banner = findViewById(R.id.background_banner);
		top_layer = findViewById(R.id.top_layer);
		linear3 = findViewById(R.id.linear3);
		Text_title = findViewById(R.id.Text_title);
		linear6 = findViewById(R.id.linear6);
		button1 = findViewById(R.id.button1);
		auth = FirebaseAuth.getInstance();
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_customLoading(true);
				intent = google_login.getSignInIntent();
				startActivityForResult(intent, REQ_CD_GOOGLE_LOGIN);
			}
		});
		
		_user_child_listener = new ChildEventListener() {
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
		user.addChildEventListener(_user_child_listener);
		
		_plan_child_listener = new ChildEventListener() {
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
		plan.addChildEventListener(_plan_child_listener);
		
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
		WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		getWindow().setStatusBarColor(Color.TRANSPARENT);
		_changeActivityFont("ooo");
		com.google.android.gms.auth.api.signin.GoogleSignInOptions gso =
		new com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder(
		com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN)
		.requestIdToken("552225783548-33pq3s3v0uttg15prk48dcq7a8ttq8qp.apps.googleusercontent.com")
		.requestEmail()
		.build();
		
		google_login = com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(this, gso);
		_subscribeFCMTopic("all");
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_GOOGLE_LOGIN:
			if (_resultCode == Activity.RESULT_OK) {
				Task<GoogleSignInAccount> _task = GoogleSignIn.getSignedInAccountFromIntent(_data);
				
				_customLoading(false);
				try {
					final com.google.android.gms.auth.api.signin.GoogleSignInAccount account =
					_task.getResult(com.google.android.gms.common.api.ApiException.class);
					
					if (account != null) {
						com.google.firebase.auth.AuthCredential credential =
						com.google.firebase.auth.GoogleAuthProvider.getCredential(account.getIdToken(), null);
						
						auth.signInWithCredential(credential).addOnCompleteListener(this,
						new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
							@Override
							public void onComplete(Task<com.google.firebase.auth.AuthResult> _task) {
								try {
									if (_task.isSuccessful()) {
										final FirebaseUser user = auth.getCurrentUser();
										if (user != null) {
											final String uid = user.getUid();
											
											users.child(uid).addListenerForSingleValueEvent(
											new com.google.firebase.database.ValueEventListener() {
												@Override
												public void onDataChange(com.google.firebase.database.DataSnapshot _snapshot) {
													try {
														if (_snapshot.exists()) {
															// EXISTING USER: fetch token first, then update DB
															FirebaseInstanceId.getInstance().getInstanceId()
															.addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
																@Override
																public void onComplete(@NonNull Task<InstanceIdResult> tokenTask) {
																	if (tokenTask.isSuccessful()) {
																		token = tokenTask.getResult().getToken();
																		SketchwareUtil.showMessage(getApplicationContext(), "Fcm Token generated !");
																	} else {
																		SketchwareUtil.showMessage(getApplicationContext(), "Unknown Error Occurred");
																	}
																	
																	map = new HashMap<>();
																	map.put("fcm_token", token);
																	
																	users.child(uid).updateChildren(map)
																	.addOnCompleteListener(new OnCompleteListener<Void>() {
																		@Override
																		public void onComplete(Task<Void> dbTask) {
																			intent.setClass(getApplicationContext(), FeedActivity.class);
																			startActivity(intent);
																			overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
																			finish();
																		}
																	});
																}
															});
														} else {
															// NEW USER: fetch token first, then create profile
															FirebaseInstanceId.getInstance().getInstanceId()
															.addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
																@Override
																public void onComplete(@NonNull Task<InstanceIdResult> tokenTask) {
																	if (tokenTask.isSuccessful()) {
																		token = tokenTask.getResult().getToken();
																		SketchwareUtil.showMessage(getApplicationContext(), "Fcm Token generated !");
																	} else {
																		SketchwareUtil.showMessage(getApplicationContext(), "Unknown Error Occurred");
																	}
																	
																	String googleName = account.getDisplayName();
																	String googlePhotoUrl = account.getPhotoUrl() != null
																	? account.getPhotoUrl().toString()
																	: "https://drive.google.com/uc?export=download&id=1ft5ojAO0zA3aaCGFPQ7K4i3M_GI_i5bm";
																	
																	map = new HashMap<>();
																	map.put("username", googleName);
																	map.put("uid", uid);
																	map.put("email", user.getEmail());
																	map.put("profile_pic", googlePhotoUrl);
																	map.put("fcm_token", token);
																	
																	users.child(uid).updateChildren(map);
																	
																	map.clear();
																	map = new HashMap<>();
																	map.put("plan", "Free");
																	map.put("points", 100);
																	
																	plan.child(uid).updateChildren(map)
																	.addOnCompleteListener(new OnCompleteListener<Void>() {
																		@Override
																		public void onComplete(Task<Void> dbTask) {
																			intent.setClass(getApplicationContext(), FeedActivity.class);
																			startActivity(intent);
																			overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
																			finish();
																		}
																	});
																}
															});
														}
													} catch (Exception e) {
														SketchwareUtil.showMessage(getApplicationContext(), "DB Error: " + e.getMessage());
													}
												}
												
												@Override
												public void onCancelled(com.google.firebase.database.DatabaseError _error) {
													SketchwareUtil.showMessage(getApplicationContext(), "Cancelled: " + _error.getMessage());
												}
											});
										}
									} else {
										Toast.makeText(getApplicationContext(),
										"Authentication Failed: " + _task.getException().getMessage(),
										Toast.LENGTH_LONG).show();
										SketchwareUtil.showMessage(getApplicationContext(), "Failed");
									}
								} catch (Exception e) {
									Toast.makeText(getApplicationContext(),
									"Error handling login result: " + e.getMessage(),
									Toast.LENGTH_LONG).show();
									SketchwareUtil.showMessage(getApplicationContext(), "Failed");
								}
							}
						});
					} else {
						Toast.makeText(getApplicationContext(), "Google account is null", Toast.LENGTH_LONG).show();
						SketchwareUtil.showMessage(getApplicationContext(), "Failed");
					}
				} catch (com.google.android.gms.common.api.ApiException e) {
					Toast.makeText(getApplicationContext(), "Google Sign-In failed: " + e.getStatusCode(), Toast.LENGTH_LONG).show();
					SketchwareUtil.showMessage(getApplicationContext(), "Failed");
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Unexpected error: " + e.getMessage(), Toast.LENGTH_LONG).show();
					SketchwareUtil.showMessage(getApplicationContext(), "Failed");
				}
			}
			else {
				SketchwareUtil.showMessage(getApplicationContext(), "Cancelled");
				_customLoading(false);
			}
			break;
			default:
			break;
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
	
	
	public void _subscribeFCMTopic(final String _name) {
		if (_name.matches("[a-zA-Z0-9-_.~%]{1,900}")) {
			String topicName = java.text.Normalizer.normalize(_name, java.text.Normalizer.Form.NFD);
			topicName = topicName.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
			FirebaseMessaging.getInstance().subscribeToTopic(topicName).addOnCompleteListener(new OnCompleteListener<Void>() {
				@Override
				public void onComplete(@NonNull Task<Void> task) {
					if (task.isSuccessful()) {
						SketchwareUtil.showMessage(getApplicationContext(), "Subscribed Successfully");
					} else {
						SketchwareUtil.showMessage(getApplicationContext(), "Couldn't Subscribe");
					}}});
		} else {
			SketchwareUtil.showMessage(getApplicationContext(), "Badly Formated Topic");
		}
	}
	
}