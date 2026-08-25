package com.error404.reelix;

import android.app.Activity;

import java.util.HashMap;

public class RequestNetworkV2 {
	private HashMap<String, Object> params = new HashMap<>();
	private String rawJsonBody = null;
	private HashMap<String, Object> headers = new HashMap<>();
	
	private Activity activity;
	
	private int requestType = 0;
	
	public RequestNetworkV2(Activity activity) {
		this.activity = activity;
	}
	
	public void setHeaders(HashMap<String, Object> headers) {
		this.headers = headers;
	}
	
	public void setParams(HashMap<String, Object> params, int requestType) {
		this.params = params;
		this.rawJsonBody = null;
		this.requestType = requestType;
	}
	
	// New: accept a pre-built raw JSON string as the body (bypasses Gson serialization of the HashMap)
	public void setJsonBody(String jsonBody, int requestType) {
		this.rawJsonBody = jsonBody;
		this.params = new HashMap<>();
		this.requestType = requestType;
	}
	
	public HashMap<String, Object> getParams() {
		return params;
	}
	
	public String getRawJsonBody() {
		return rawJsonBody;
	}
	
	public boolean hasRawJsonBody() {
		return rawJsonBody != null;
	}
	
	public HashMap<String, Object> getHeaders() {
		return headers;
	}
	
	public Activity getActivity() {
		return activity;
	}
	
	public int getRequestType() {
		return requestType;
	}
	
	public void startRequestNetwork(String method, String url, String tag, RequestListener requestListener) {
		RequestNetworkControllerV2.getInstance().execute(this, method, url, tag, requestListener);
	}
	
	public interface RequestListener {
		public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders);
		public void onErrorResponse(String tag, String message);
	}
}
