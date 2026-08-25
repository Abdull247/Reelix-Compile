package com.error404.reelix;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class VideoCacheHelper {

    private static final String PREF_NAME = "video_cache_pref";
    private static final String KEY_CACHE = "cached_videos";

    private Context context;
    private SharedPreferences prefs;
    private Gson gson;

    public VideoCacheHelper(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    public ArrayList<HashMap<String, Object>> loadCache() {
        String json = prefs.getString(KEY_CACHE, "");
        if (json.isEmpty()) return new ArrayList<>();

        try {
            ArrayList<HashMap<String, Object>> list = gson.fromJson(
                    json, new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
            return list != null ? list : new ArrayList<>();
        } catch (Exception e) {
            Log.e("VideoCacheHelper", "Cache load error", e);
            return new ArrayList<>();
        }
    }

    /** Save the full current video list to cache. No bitmaps are ever stored — just metadata. */
    public void saveCache(ArrayList<HashMap<String, Object>> videoList) {
        try {
            ArrayList<HashMap<String, Object>> toSave = new ArrayList<>();
            for (HashMap<String, Object> item : videoList) {
                HashMap<String, Object> copy = new HashMap<>(item);
                copy.remove("thumbnail"); // never persist live Bitmap objects
                toSave.add(copy);
            }
            String json = gson.toJson(toSave);
            prefs.edit().putString(KEY_CACHE, json).apply();
        } catch (Exception e) {
            Log.e("VideoCacheHelper", "Cache save error", e);
        }
    }

    /**
     * Derives a thumbnail directly from the video file — no separate thumbnail file needed.
     * Call this off the main thread (e.g. in onBindViewHolder via a background decode, or
     * pre-warm in the paginator page load).
     */
    public static Bitmap getLocalThumbnail(String videoPath, int reqWidth, int reqHeight) {
        if (videoPath == null || videoPath.isEmpty()) return null;
        try {
            return ThumbnailUtils.createVideoThumbnail(videoPath, MediaStore.Video.Thumbnails.MINI_KIND);
        } catch (Exception e) {
            Log.e("VideoCacheHelper", "Local thumb error for " + videoPath, e);
            return null;
        }
    }

    public void clearCache() {
        prefs.edit().remove(KEY_CACHE).apply();
    }
}