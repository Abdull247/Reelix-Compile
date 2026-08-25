package com.error404.reelix;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class ReelixDownloadHelper {

    private static final String TAG = "ReelixDownload";

    public static final Handler progressHandler = new Handler(Looper.getMainLooper());
    public static Runnable progressRunnable;
    public static RecyclerView.Adapter downloading_adapter;

    // Shared pause map - used by both helper and service
    public static final HashMap<String, Boolean> pauseMap = new HashMap<>();

    private static final HashMap<String, Integer> progressMap        = new HashMap<>();
    private static final HashMap<String, Long>    downloadedBytesMap = new HashMap<>();
    private static final HashMap<String, Long>    totalBytesMap      = new HashMap<>();
    private static final HashMap<String, Integer> statusMap          = new HashMap<>();
    private static final HashMap<String, String>  urlMap             = new HashMap<>();
    private static final HashMap<String, String>  destPathMap        = new HashMap<>();
    private static final HashMap<String, String>  titleMap           = new HashMap<>();

    private static final String PREFS_NAME     = "reelix_downloads";
    private static final String KEY_PROGRESS   = "progress_";
    private static final String KEY_DOWNLOADED = "downloaded_";
    private static final String KEY_TOTAL      = "total_";
    private static final String KEY_STATUS     = "status_";
    private static final String KEY_URL        = "url_";
    private static final String KEY_DEST_PATH  = "dest_path_";
    private static final String KEY_TITLE      = "title_";

    // ── Getters / Setters ────────────────────────────────────────────────────

    public static synchronized void setProgress(String dId, int progress) {
        progressMap.put(dId, progress);
    }
    public static synchronized int getProgress(String dId) {
        return progressMap.getOrDefault(dId, 0);
    }

    public static synchronized void setDownloadedBytes(String dId, long bytes) {
        downloadedBytesMap.put(dId, bytes);
    }
    public static synchronized long getDownloadedBytes(String dId) {
        return downloadedBytesMap.getOrDefault(dId, 0L);
    }

    public static synchronized void setTotalBytes(String dId, long bytes) {
        totalBytesMap.put(dId, bytes);
    }
    public static synchronized long getTotalBytes(String dId) {
        return totalBytesMap.getOrDefault(dId, 0L);
    }

    public static synchronized void setStatus(String dId, int status) {
        statusMap.put(dId, status);
    }
    public static synchronized int getStatus(String dId) {
        return statusMap.getOrDefault(dId, 2);
    }

    public static synchronized void putUrl(String dId, String url) {
        urlMap.put(dId, url);
    }
    public static synchronized String getUrl(String dId) {
        return urlMap.getOrDefault(dId, "");
    }

    public static synchronized void putDestPath(String dId, String path) {
        destPathMap.put(dId, path);
    }
    public static synchronized String getDestPath(String dId) {
        return destPathMap.getOrDefault(dId, "");
    }

    public static synchronized void putTitle(String dId, String title) {
        titleMap.put(dId, title);
    }
    public static synchronized String getTitle(String dId) {
        return titleMap.getOrDefault(dId, "");
    }

    // ── Persistence ──────────────────────────────────────────────────────────

    public static synchronized void saveDownloadState(Context context, String dId) {
        SharedPreferences.Editor ed = prefs(context).edit();
        ed.putInt   (KEY_PROGRESS   + dId, progressMap       .getOrDefault(dId, 0));
        ed.putLong  (KEY_DOWNLOADED + dId, downloadedBytesMap.getOrDefault(dId, 0L));
        ed.putLong  (KEY_TOTAL      + dId, totalBytesMap     .getOrDefault(dId, 0L));
        ed.putInt   (KEY_STATUS     + dId, statusMap         .getOrDefault(dId, 2));
        ed.putString(KEY_URL        + dId, urlMap      .getOrDefault(dId, ""));
        ed.putString(KEY_DEST_PATH  + dId, destPathMap .getOrDefault(dId, ""));
        ed.putString(KEY_TITLE      + dId, titleMap    .getOrDefault(dId, ""));
        ed.apply();
    }

    public static synchronized void loadDownloadState(Context context, String dId) {
        SharedPreferences p = prefs(context);
        // Always load from prefs - the in-memory values will be overwritten
        // by the live service as soon as it starts writing progress
        progressMap       .put(dId, p.getInt   (KEY_PROGRESS   + dId, 0));
        downloadedBytesMap.put(dId, p.getLong  (KEY_DOWNLOADED + dId, 0L));
        totalBytesMap     .put(dId, p.getLong  (KEY_TOTAL      + dId, 0L));
        statusMap         .put(dId, p.getInt   (KEY_STATUS     + dId, 2));
        urlMap            .put(dId, p.getString(KEY_URL        + dId, ""));
        destPathMap       .put(dId, p.getString(KEY_DEST_PATH  + dId, ""));
        titleMap          .put(dId, p.getString(KEY_TITLE      + dId, ""));
        Log.d(TAG, "loadDownloadState dId=" + dId
                + " status=" + statusMap.get(dId)
                + " total=" + totalBytesMap.get(dId)
                + " downloaded=" + downloadedBytesMap.get(dId));
    }

    public static synchronized void clearDownloadState(Context context, String dId) {
        SharedPreferences.Editor ed = prefs(context).edit();
        ed.remove(KEY_PROGRESS   + dId);
        ed.remove(KEY_DOWNLOADED + dId);
        ed.remove(KEY_TOTAL      + dId);
        ed.remove(KEY_STATUS     + dId);
        ed.remove(KEY_URL        + dId);
        ed.remove(KEY_DEST_PATH  + dId);
        ed.remove(KEY_TITLE      + dId);
        ed.apply();

        progressMap       .remove(dId);
        downloadedBytesMap.remove(dId);
        totalBytesMap     .remove(dId);
        statusMap         .remove(dId);
        urlMap            .remove(dId);
        destPathMap       .remove(dId);
        titleMap          .remove(dId);
        pauseMap          .remove(dId);
    }

    // ── Start / Pause / Resume ───────────────────────────────────────────────

    public static void startCustomDownload(Context context, String url,
                                           String destPath, String title, String dId) {
        Log.d(TAG, "startCustomDownload dId=" + dId + " url=" + url);

        putUrl(dId, url);
        putDestPath(dId, destPath);
        putTitle(dId, title);

        File partial   = new File(destPath + ".partial");
        long diskBytes = partial.exists() ? partial.length() : 0L;

        // Always initialise fresh — service will overwrite with real values
        setDownloadedBytes(dId, diskBytes);
        setTotalBytes(dId, 0L);
        setProgress(dId, 0);
        setStatus(dId, 2);
        pauseMap.put(dId, false);

        saveDownloadState(context, dId);

        Intent intent = new Intent(context, ReelixDownloadService.class);
        intent.setAction("START_DOWNLOAD");
        intent.putExtra("url",            url);
        intent.putExtra("dest_path",      destPath);
        intent.putExtra("title",          title);
        intent.putExtra("download_id",    dId);
        intent.putExtra("existing_bytes", diskBytes);
        startService(context, intent);
    }

    public static void pauseDownload(Context context, String dId) {
        Log.d(TAG, "pauseDownload dId=" + dId);
        pauseMap.put(dId, true);
        setStatus(dId, 4);
        saveDownloadState(context, dId);

        Intent intent = new Intent(context, ReelixDownloadService.class);
        intent.setAction("PAUSE_DOWNLOAD");
        intent.putExtra("download_id", dId);
        startService(context, intent);
    }

    public static void resumeDownload(Context context, String dId) {
        Log.d(TAG, "resumeDownload dId=" + dId);
        String url   = getUrl(dId);
        String path  = getDestPath(dId);
        String title = getTitle(dId);

        if (url.isEmpty() || path.isEmpty()) {
            SharedPreferences p = prefs(context);
            url   = p.getString(KEY_URL       + dId, "");
            path  = p.getString(KEY_DEST_PATH + dId, "");
            title = p.getString(KEY_TITLE     + dId, "");
        }

        if (url.isEmpty() || path.isEmpty()) {
            Log.e(TAG, "resumeDownload: missing url or path for dId=" + dId);
            return;
        }

        pauseMap.put(dId, false);
        setStatus(dId, 2);
        saveDownloadState(context, dId);

        Intent intent = new Intent(context, ReelixDownloadService.class);
        intent.setAction("RESUME_DOWNLOAD");
        intent.putExtra("download_id", dId);
        intent.putExtra("url",         url);
        intent.putExtra("dest_path",   path);
        intent.putExtra("title",       title);
        startService(context, intent);
    }

    public static void togglePauseDownload(Context context, String dId) {
        boolean isPaused = Boolean.TRUE.equals(pauseMap.getOrDefault(dId, false));
        Log.d(TAG, "togglePauseDownload dId=" + dId + " currentlyPaused=" + isPaused);
        if (isPaused) {
            resumeDownload(context, dId);
        } else {
            pauseDownload(context, dId);
        }
    }

    public static void resumeAllDownloads(Context context) {
        SharedPreferences p = prefs(context);
        for (String key : p.getAll().keySet()) {
            if (!key.startsWith(KEY_STATUS)) continue;

            String dId    = key.substring(KEY_STATUS.length());
            int    status = p.getInt(key, -1);

            if (status != 2 && status != 4) continue;

            String url   = p.getString(KEY_URL       + dId, "");
            String path  = p.getString(KEY_DEST_PATH + dId, "");
            String title = p.getString(KEY_TITLE     + dId, "");

            if (url.isEmpty() || path.isEmpty()) continue;

            Log.d(TAG, "resumeAllDownloads: resuming dId=" + dId);

            progressMap       .put(dId, p.getInt (KEY_PROGRESS   + dId, 0));
            downloadedBytesMap.put(dId, p.getLong(KEY_DOWNLOADED + dId, 0L));
            totalBytesMap     .put(dId, p.getLong(KEY_TOTAL      + dId, 0L));
            statusMap         .put(dId, 2);
            urlMap            .put(dId, url);
            destPathMap       .put(dId, path);
            titleMap          .put(dId, title);
            pauseMap          .put(dId, false);

            File partial   = new File(path + ".partial");
            long diskBytes = partial.exists() ? partial.length() : 0L;

            Intent intent = new Intent(context, ReelixDownloadService.class);
            intent.setAction("START_DOWNLOAD");
            intent.putExtra("url",            url);
            intent.putExtra("dest_path",      path);
            intent.putExtra("title",          title);
            intent.putExtra("download_id",    dId);
            intent.putExtra("existing_bytes", diskBytes);
            startService(context, intent);
        }
    }

    public static void deleteDownload(Context context, String dId, String folderPath) {
        if (dId != null && !dId.isEmpty()) {
            pauseMap.put(dId, true);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    clearDownloadState(context, dId);
                    ReelixStorageHelper.deleteDownload(folderPath);
                }
            }, 600);
        } else {
            ReelixStorageHelper.deleteDownload(folderPath);
        }
    }

    // ── UI Helpers ───────────────────────────────────────────────────────────

    public static void loadActiveMetadata(Context context,
                                          ArrayList<HashMap<String, Object>> targetList) {
        // Always reload from disk
        ReelixStorageHelper.loadActiveMetadata(context, targetList);

        for (HashMap<String, Object> item : targetList) {
            String dId = String.valueOf(item.get("download_id"));
            // Always load from prefs so we get the last saved state
            loadDownloadState(context, dId);
            item.put("progress",     getProgress(dId));
            item.put("bytes_so_far", getDownloadedBytes(dId));
            item.put("bytes_total",  getTotalBytes(dId));
            item.put("status",       getStatus(dId));
            Log.d(TAG, "loadActiveMetadata item dId=" + dId
                    + " progress=" + getProgress(dId)
                    + " status=" + getStatus(dId));
        }
    }

    public static void updateLiveProgress(Context context,
                                          ArrayList<HashMap<String, Object>> targetList) {
        ArrayList<HashMap<String, Object>> toRemove = new ArrayList<>();

        for (HashMap<String, Object> task : targetList) {
            String dId    = String.valueOf(task.get("download_id"));
            int    status = getStatus(dId);

            task.put("progress",     getProgress(dId));
            task.put("bytes_so_far", getDownloadedBytes(dId));
            task.put("bytes_total",  getTotalBytes(dId));
            task.put("status",       status);

            if (status == 8) {
                Log.d(TAG, "Download finished dId=" + dId);
                ReelixStorageHelper.markDownloadFinished(
                        String.valueOf(task.get("meta_file_path")));
                clearDownloadState(context, dId);
                toRemove.add(task);
            }
        }

        targetList.removeAll(toRemove);
    }

    public static void loadCompletedDownloads(Context context,
                                              ArrayList<HashMap<String, Object>> targetList) {
        ReelixStorageHelper.loadCompletedDownloads(context, targetList);
    }

    // ── Internal ─────────────────────────────────────────────────────────────

    private static void startService(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    private static SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}