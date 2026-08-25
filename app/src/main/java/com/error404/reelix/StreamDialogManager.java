package com.error404.reelix;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class StreamDialogManager {

    private Activity activity;
    private Dialog dialog;
    private Intent sourceIntent;

    private int selectedServer1Pos = -1;
    private int selectedServer2Pos = -1;
    private boolean isServer3Selected = false;

    private ArrayList<HashMap<String, String>> server1List = new ArrayList<>();
    private ArrayList<HashMap<String, String>> server2List = new ArrayList<>();

    private QualityAdapter adapter1;
    private QualityAdapter adapter2;

    private String tmdbId = "";
    private String paxsenixId = "";
    private String idType = "";
    private String mediaTitle = "";
    private String mediaType = "";
    private String subtitleData = "";
    private Typeface customFont;

    // Genre / release-year context passed in from the details activity, used
    // to build the watch-history log payload once the user picks a stream.
    private ArrayList<HashMap<String, Object>> genresContext = new ArrayList<>();
    private String releaseYearContext = "";

    private RequestNetwork logWatchHistory;
    private RequestNetwork.RequestListener _logWatchHistoryListener;

    public StreamDialogManager(Activity activity, Intent sourceIntent) {
        this.activity = activity;
        this.sourceIntent = sourceIntent;
        this.idType = sourceIntent.hasExtra("id_type") ? sourceIntent.getStringExtra("id_type") : "tmdb";
        this.mediaType = sourceIntent.hasExtra("type") ? sourceIntent.getStringExtra("type") : "tv";
        
        try {
            this.customFont = Typeface.createFromAsset(activity.getAssets(), "fonts/ooo.ttf");
        } catch (Exception e) {
            e.printStackTrace();
            this.customFont = Typeface.DEFAULT;
        }

        logWatchHistory = new RequestNetwork(activity);
        _logWatchHistoryListener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
                // Fire-and-forget: no UI action needed on success.
                ((ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE))
    .setPrimaryClip(ClipData.newPlainText("clipboard", response));
            }

            @Override
            public void onErrorResponse(String tag, String message) {
                // Fire-and-forget: don't interrupt playback if logging fails.
            }
        };
    }

    /**
     * Pass in the genres list (as built in ViewMovieDetailsActivity's genres_list,
     * containing {"id": "<tmdb genre id>", "name": "<genre name>"} maps) and the
     * release year text, so we can log accurate watch-history data once the
     * user picks a stream and taps Watch.
     */
    public void setGenreAndYearContext(ArrayList<HashMap<String, Object>> genres, String releaseYear) {
        this.genresContext = genres != null ? genres : new ArrayList<HashMap<String, Object>>();
        this.releaseYearContext = releaseYear != null ? releaseYear : "";
    }

    public void showDialog(String jsonResponse) {
        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONObject data = root.optJSONObject("data");
            if (data == null) {
                Toast("Invalid data received.");
                return;
            }

            tmdbId = data.optString("tmdb_id", "");
            paxsenixId = data.optString("paxsenix_id", "");
            String baseTitle = data.optString("title", "Unknown Title");
            String posterUrl = data.optString("poster", "");
            
            // Check the incoming data type or rely on our source metadata intent structure
            String extractedType = data.optString("type", mediaType);
            
            // Case-insensitive check to confirm TV architecture routing rules
            if ("tv".equalsIgnoreCase(extractedType) || "tv".equalsIgnoreCase(mediaType)) {
                int sNum = data.optInt("season", 1);
                int eNum = data.optInt("episode", 1);
                String epTitle = data.optString("episode_title", "");
                
                mediaTitle = baseTitle + "-S" + sNum + "-E" + eNum;
                if (!epTitle.isEmpty() && !"null".equalsIgnoreCase(epTitle)) {
                    mediaTitle += " (" + epTitle + ")";
                }
            } else {
                mediaTitle = baseTitle;
            }

            // Extract Subtitles cleanly as string array structure
            JSONArray subsArray = data.optJSONArray("subtitles");
            if (subsArray != null) {
                subtitleData = subsArray.toString();
            } else {
                subtitleData = "";
            }

            // Clear data structures completely before building to avoid duplications
            server1List.clear();
            server2List.clear();

            JSONObject streams = data.optJSONObject("streams");
            if (streams != null) {
                parseStreamArray(streams.optJSONArray("server1"), server1List, "server1");
                parseStreamArray(streams.optJSONArray("server2"), server2List, "server2");
            }

            setupAndShowDialog(posterUrl);

        } catch (Exception e) {
            e.printStackTrace();
            Toast("Error parsing stream data: " + e.getMessage());
        }
    }

    private void parseStreamArray(JSONArray array, ArrayList<HashMap<String, String>> list, String serverName) {
        if (array == null) return;
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.optJSONObject(i);
            if (obj != null) {
                HashMap<String, String> map = new HashMap<>();
                map.put("quality", obj.optString("quality", "Unknown"));
                map.put("url", obj.optString("url", ""));
                map.put("direct_url", obj.optString("direct_url", obj.optString("url", "")));
                map.put("server", serverName);
                list.add(map);
            }
        }
    }

    private void setupAndShowDialog(String posterUrl) {
        dialog = new Dialog(activity, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_stream_selection, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setWindowAnimations(android.R.style.Animation_InputMethod);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        ImageView posterImg = view.findViewById(R.id.poster_imageview);
        TextView titleTxt = view.findViewById(R.id.title_textview);
        TextView idTxt = view.findViewById(R.id.id_txt);
        
        LinearLayout s1Header = view.findViewById(R.id.server1_header);
        RecyclerView s1Recycler = view.findViewById(R.id.server1_streams_rec);
        
        LinearLayout s2Header = view.findViewById(R.id.server2_header);
        RecyclerView s2Recycler = view.findViewById(R.id.server2_qualities_rec);
        
        LinearLayout s3Holder = view.findViewById(R.id.server3_holder);
        RadioButton s3Radio = view.findViewById(R.id.radio_server3);
        
        LinearLayout watchBtn = view.findViewById(R.id.watch_btn);

        // Apply custom asset fonts directly to known structural text layers
        titleTxt.setTypeface(customFont, Typeface.BOLD);
        idTxt.setTypeface(customFont, Typeface.NORMAL);
        s3Radio.setTypeface(customFont, Typeface.NORMAL);
        
        // Apply systemic bold fonts iteratively across container headers
        applyFontToViewGroup(s1Header, customFont, true);
        applyFontToViewGroup(s2Header, customFont, true);
        applyFontToViewGroup(watchBtn, customFont, true);

        titleTxt.setText(mediaTitle);
        String displayId = idType.equals("tmdb") || idType.equals("default") ? "TMDB: " + tmdbId : "Paxsenix: " + paxsenixId;
        idTxt.setText(displayId);

        if (!posterUrl.isEmpty()) {
            Glide.with(activity).load(posterUrl).into(posterImg);
        }

        applyRipple(watchBtn, "#B71C1C", "#EEEEEE", 60, 0, "#000000");
        updateWatchButtonState(watchBtn);

        if (server1List.isEmpty()) {
            s1Header.setVisibility(View.GONE);
            s1Recycler.setVisibility(View.GONE);
        } else {
            s1Recycler.setLayoutManager(new LinearLayoutManager(activity));
            adapter1 = new QualityAdapter(server1List, 1, s3Radio, watchBtn);
            s1Recycler.setAdapter(adapter1);
        }

        if (server2List.isEmpty()) {
            s2Header.setVisibility(View.GONE);
            s2Recycler.setVisibility(View.GONE);
        } else {
            s2Recycler.setLayoutManager(new LinearLayoutManager(activity));
            adapter2 = new QualityAdapter(server2List, 2, s3Radio, watchBtn);
            s2Recycler.setAdapter(adapter2);
        }

        s3Holder.setOnClickListener(v -> {
            isServer3Selected = true;
            s3Radio.setChecked(true);
            selectedServer1Pos = -1;
            selectedServer2Pos = -1;
            if (adapter1 != null) adapter1.notifyDataSetChanged();
            if (adapter2 != null) adapter2.notifyDataSetChanged();
            updateWatchButtonState(watchBtn);
        });
        
        s3Radio.setOnClickListener(v -> s3Holder.performClick());

        watchBtn.setOnClickListener(v -> {
            if (!isServer3Selected && selectedServer1Pos == -1 && selectedServer2Pos == -1) return;

            // Log this watch to the user's history before launching playback.
            logWatchHistoryEntry();

            if (isServer3Selected) {
                if ("paxsenix".equalsIgnoreCase(idType)) {
                    Toast("Server 3 is only available for TMDB titles.");
                    return;
                }
                Intent intent = new Intent(activity, MoviePlayerPageActivity.class);
                intent.putExtra("id", tmdbId);
                intent.putExtra("type", mediaType);
                intent.putExtra("name", mediaTitle);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
                dialog.dismiss();

            } else {
                HashMap<String, String> selectedData = selectedServer1Pos != -1 ? server1List.get(selectedServer1Pos) : server2List.get(selectedServer2Pos);
                
                Intent intent = new Intent(activity, PlayerActivity.class);
                // Extract and provide direct_url to the player context
                intent.putExtra("link", selectedData.get("direct_url"));
                intent.putExtra("title", mediaTitle);
                intent.putExtra("type", mediaType);
                intent.putExtra("quality", selectedData.get("quality"));
                intent.putExtra("server", selectedData.get("server"));
                intent.putExtra("subtitle_data", subtitleData);
                
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    /**
     * Builds and fires a POST to /api/watch-history/log using the currently
     * selected media's id/type/title/genres/release year, tagged to the
     * signed-in Firebase user's uid. Fire-and-forget — failures don't block
     * playback.
     */
    private void logWatchHistoryEntry() {
    try {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            return; // Not signed in — nothing to log against.
        }
        final String uid = currentUser.getUid();

        final String logId = idType.equals("paxsenix") ? paxsenixId : tmdbId;
        final String logIdType = idType.equals("paxsenix") ? "paxsenix" : "tmdb";
        final String category = ("tv".equalsIgnoreCase(mediaType) || "series".equalsIgnoreCase(mediaType)) ? "tv" : "movie";

        // Build genre_ids array from the genres context passed in from the details activity
        final JSONArray genreIdsArray = new JSONArray();
        if (genresContext != null) {
            for (HashMap<String, Object> genre : genresContext) {
                Object rawId = genre.get("id");
                if (rawId != null) {
                    try {
                        genreIdsArray.put(Integer.parseInt(rawId.toString().trim()));
                    } catch (NumberFormatException nfe) {
                        // Skip non-numeric genre ids rather than failing the whole log
                    }
                }
            }
        }

        int releaseYearInt = 0;
        if (releaseYearContext != null && !releaseYearContext.trim().isEmpty() && !releaseYearContext.equalsIgnoreCase("N/A")) {
            try {
                releaseYearInt = Integer.parseInt(releaseYearContext.trim());
            } catch (NumberFormatException nfe) {
                releaseYearInt = 0;
            }
        }

        final JSONObject body = new JSONObject();
        body.put("uid", uid);
        body.put("movie_name", mediaTitle);
        body.put("id", logId);
        body.put("id_type", logIdType);
        body.put("id_number", logIdType.equals("tmdb") ? 2 : 1);
        body.put("category", category);
        body.put("genre_ids", genreIdsArray);
        if (releaseYearInt > 0) {
            body.put("release_year", releaseYearInt);
        }

        android.util.Log.d("WatchLog", "Body: " + body.toString());

        // RequestNetwork's setParams(HashMap, REQUEST_BODY) re-serializes nested
        // JSONArray values incorrectly (wraps them as {"values":[...]}), so we
        // bypass it here and send the already-correct JSON string manually via
        // HttpURLConnection on a background thread.
        new Thread(new Runnable() {
            @Override
            public void run() {
                java.net.HttpURLConnection conn = null;
                try {
                    java.net.URL url = new java.net.URL("https://error404-api.vercel.app/api/watch-history/log");
                    conn = (java.net.HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("accept", "application/json");
                    conn.setRequestProperty("x-api-key", "516577400478683");
                    conn.setDoOutput(true);
                    conn.setConnectTimeout(15000);
                    conn.setReadTimeout(15000);

                    byte[] payload = body.toString().getBytes("UTF-8");
                    conn.setFixedLengthStreamingMode(payload.length);

                    java.io.OutputStream os = conn.getOutputStream();
                    os.write(payload);
                    os.flush();
                    os.close();

                    int responseCode = conn.getResponseCode();
                    android.util.Log.d("WatchLog", "Response code: " + responseCode);

                } catch (Exception e) {
                    android.util.Log.e("WatchLog", "Failed to log watch history: " + e.getMessage());
                } finally {
                    if (conn != null) conn.disconnect();
                }
            }
        }).start();

    } catch (Exception e) {
        e.printStackTrace();
        // Swallow — logging failure should never block the user from watching.
    }
}
    
    // Helper method to loop systematically down child view tree layers
    private void applyFontToViewGroup(ViewGroup root, Typeface tf, boolean isBold) {
        if (root == null || tf == null) return;
        for (int i = 0; i < root.getChildCount(); i++) {
            View child = root.getChildAt(i);
            if (child instanceof TextView) {
                ((TextView) child).setTypeface(tf, isBold ? Typeface.BOLD : Typeface.NORMAL);
            } else if (child instanceof ViewGroup) {
                applyFontToViewGroup((ViewGroup) child, tf, isBold);
            }
        }
    }

    private void updateWatchButtonState(View watchBtn) {
        if (isServer3Selected || selectedServer1Pos != -1 || selectedServer2Pos != -1) {
            watchBtn.setAlpha(1.0f);
            watchBtn.setEnabled(true);
        } else {
            watchBtn.setAlpha(0.5f);
            watchBtn.setEnabled(false);
        }
    }

    private void applyRipple(View view, String focus, String pressed, double round, double stroke, String strokeclr) {
        android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
        GG.setColor(Color.parseColor(focus));
        GG.setCornerRadius((float) round);
        GG.setStroke((int) stroke, Color.parseColor("#" + strokeclr.replace("#", "")));
        view.setElevation(0f);
        android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(
                new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#22000000")}),
                GG, null);
        view.setBackground(RE);
    }

    private void Toast(String msg) {
        android.widget.Toast.makeText(activity, msg, android.widget.Toast.LENGTH_SHORT).show();
    }

    private class QualityAdapter extends RecyclerView.Adapter<QualityAdapter.ViewHolder> {
        private ArrayList<HashMap<String, String>> data;
        private int serverId;
        private RadioButton s3Radio;
        private View watchBtn;

        public QualityAdapter(ArrayList<HashMap<String, String>> data, int serverId, RadioButton s3Radio, View watchBtn) {
            this.data = data;
            this.serverId = serverId;
            this.s3Radio = s3Radio;
            this.watchBtn = watchBtn;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stream_quality, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.radio.setText(data.get(position).get("quality"));
            holder.radio.setTypeface(customFont, Typeface.NORMAL);
            
            boolean isSelected = (serverId == 1 && selectedServer1Pos == position) || 
                                 (serverId == 2 && selectedServer2Pos == position);
            holder.radio.setChecked(isSelected);

            holder.root.setOnClickListener(v -> {
                isServer3Selected = false;
                s3Radio.setChecked(false);

                if (serverId == 1) {
                    selectedServer1Pos = position;
                    selectedServer2Pos = -1;
                } else {
                    selectedServer2Pos = position;
                    selectedServer1Pos = -1;
                }

                if (adapter1 != null) adapter1.notifyDataSetChanged();
                if (adapter2 != null) adapter2.notifyDataSetChanged();
                
                updateWatchButtonState(watchBtn);
            });

            holder.radio.setOnClickListener(v -> holder.root.performClick());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            LinearLayout root;
            RadioButton radio;
            public ViewHolder(View itemView) {
                super(itemView);
                root = itemView.findViewById(R.id.root_item);
                radio = itemView.findViewById(R.id.quality_radio);
            }
        }
    }
}