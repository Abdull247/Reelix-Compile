package com.error404.reelix;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;

public class ReelixPublicDownloadHelper {

    private static final String TAG = "ReelixPublicDownload";

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
    private static final HashMap<String, String>  folderPathMap      = new HashMap<>();

    private static final String PREFS_NAME     = "reelix_public_downloads";
    private static final String KEY_PROGRESS   = "progress_";
    private static final String KEY_DOWNLOADED = "downloaded_";
    private static final String KEY_TOTAL      = "total_";
    private static final String KEY_STATUS     = "status_";
    private static final String KEY_URL        = "url_";
    private static final String KEY_DEST_PATH  = "dest_path_";
    private static final String KEY_TITLE      = "title_";
    private static final String KEY_FOLDER     = "folder_";

    // ── Public root / folder helpers ─────────────────────────────────────────

    public static File getPublicRoot() {
        File root = new File(Environment.getExternalStorageDirectory(), "Reelix Movies/Downloads");
        if (!root.exists()) root.mkdirs();
        return root;
    }

    public static String sanitiseName(String raw) {
        String safe = raw.replaceAll("[\\\\/:*?\"<>|]", "").trim();
        return safe.isEmpty() ? "Download_" + System.currentTimeMillis() : safe;
    }

    public static File getDownloadFolder(String dId) {
        File folder = new File(getPublicRoot(), dId);
        if (!folder.exists()) folder.mkdirs();
        return folder;
    }

    public static String buildFileName(String title, String quality, String extension) {
        return sanitiseName(title) + "_" + quality + "." + extension;
    }

    public static void writeMetadata(File folder, String dId, String title, String thumbnailUrl,
                                     String quality, String type, String fileName, String sourceUrl) {
        try {
            JSONObject json = new JSONObject();
            json.put("download_id",    dId);
            json.put("title",          title);
            json.put("thumbnail_url",  thumbnailUrl);
            json.put("quality",        quality);
            json.put("type",           type);
            json.put("video_file_name", fileName);
            json.put("source_url",     sourceUrl);
            json.put("download_date",  System.currentTimeMillis());
            json.put("is_downloading", true);
            java.io.FileWriter writer = new java.io.FileWriter(new File(folder, "metadata.json"));
            writer.write(json.toString(4));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void markMetadataFinished(String metaFilePath) {
        try {
            File metaFile = new File(metaFilePath);
            if (!metaFile.exists()) return;
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(metaFile));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            br.close();
            JSONObject obj = new JSONObject(sb.toString());
            obj.put("is_downloading", false);
            java.io.FileWriter writer = new java.io.FileWriter(metaFile);
            writer.write(obj.toString(4));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public static synchronized void putFolderPath(String dId, String folderPath) {
        folderPathMap.put(dId, folderPath);
    }
    public static synchronized String getFolderPath(String dId) {
        return folderPathMap.getOrDefault(dId, "");
    }

    // ── Persistence ──────────────────────────────────────────────────────────

    public static synchronized void saveDownloadState(Context context, String dId) {
        SharedPreferences.Editor ed = prefs(context).edit();
        ed.putInt   (KEY_PROGRESS   + dId, progressMap       .getOrDefault(dId, 0));
        ed.putLong  (KEY_DOWNLOADED + dId, downloadedBytesMap.getOrDefault(dId, 0L));
        ed.putLong  (KEY_TOTAL      + dId, totalBytesMap     .getOrDefault(dId, 0L));
        ed.putInt   (KEY_STATUS     + dId, statusMap         .getOrDefault(dId, 2));
        ed.putString(KEY_URL        + dId, urlMap       .getOrDefault(dId, ""));
        ed.putString(KEY_DEST_PATH  + dId, destPathMap  .getOrDefault(dId, ""));
        ed.putString(KEY_TITLE      + dId, titleMap     .getOrDefault(dId, ""));
        ed.putString(KEY_FOLDER     + dId, folderPathMap.getOrDefault(dId, ""));
        ed.apply();
    }

    public static synchronized void loadDownloadState(Context context, String dId) {
        SharedPreferences p = prefs(context);
        progressMap       .put(dId, p.getInt   (KEY_PROGRESS   + dId, 0));
        downloadedBytesMap.put(dId, p.getLong  (KEY_DOWNLOADED + dId, 0L));
        totalBytesMap     .put(dId, p.getLong  (KEY_TOTAL      + dId, 0L));
        statusMap         .put(dId, p.getInt   (KEY_STATUS     + dId, 2));
        urlMap            .put(dId, p.getString(KEY_URL        + dId, ""));
        destPathMap       .put(dId, p.getString(KEY_DEST_PATH  + dId, ""));
        titleMap          .put(dId, p.getString(KEY_TITLE      + dId, ""));
        folderPathMap     .put(dId, p.getString(KEY_FOLDER     + dId, ""));
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
        ed.remove(KEY_FOLDER     + dId);
        ed.apply();

        progressMap       .remove(dId);
        downloadedBytesMap.remove(dId);
        totalBytesMap     .remove(dId);
        statusMap         .remove(dId);
        urlMap            .remove(dId);
        destPathMap       .remove(dId);
        titleMap          .remove(dId);
        folderPathMap     .remove(dId);
        pauseMap          .remove(dId);
    }

    // ── Start / Pause / Resume ───────────────────────────────────────────────

    // dId should be a fresh unique id (e.g. job_id from the API, or System.currentTimeMillis() based)
    public static void startPublicDownload(Context context, String url, String title,
                                           String quality, String type, String extension,
                                           String thumbnailUrl, String dId) {
        Log.d(TAG, "startPublicDownload dId=" + dId + " url=" + url);

        File   folder   = getDownloadFolder(dId);
        String fileName = buildFileName(title, quality, extension);
        File   destFile = new File(folder, fileName);

        writeMetadata(folder, dId, title, thumbnailUrl, quality, type, fileName, url);

        putUrl(dId, url);
        putDestPath(dId, destFile.getAbsolutePath());
        putTitle(dId, title);
        putFolderPath(dId, folder.getAbsolutePath());

        File partial   = new File(destFile.getAbsolutePath() + ".partial");
        long diskBytes = partial.exists() ? partial.length() : 0L;

        setDownloadedBytes(dId, diskBytes);
        setTotalBytes(dId, 0L);
        setProgress(dId, 0);
        setStatus(dId, 2);
        pauseMap.put(dId, false);

        saveDownloadState(context, dId);

        Intent intent = new Intent(context, ReelixPublicDownloadService.class);
        intent.setAction("START_DOWNLOAD");
        intent.putExtra("url",            url);
        intent.putExtra("dest_path",      destFile.getAbsolutePath());
        intent.putExtra("title",          title);
        intent.putExtra("download_id",    dId);
        intent.putExtra("existing_bytes", diskBytes);
        intent.putExtra("meta_folder",    folder.getAbsolutePath());
        startService(context, intent);
    }

    public static void pauseDownload(Context context, String dId) {
        Log.d(TAG, "pauseDownload dId=" + dId);
        pauseMap.put(dId, true);
        setStatus(dId, 4);
        saveDownloadState(context, dId);

        Intent intent = new Intent(context, ReelixPublicDownloadService.class);
        intent.setAction("PAUSE_DOWNLOAD");
        intent.putExtra("download_id", dId);
        startService(context, intent);
    }

    public static void resumeDownload(Context context, String dId) {
        Log.d(TAG, "resumeDownload dId=" + dId);
        String url    = getUrl(dId);
        String path   = getDestPath(dId);
        String title  = getTitle(dId);
        String folder = getFolderPath(dId);

        if (url.isEmpty() || path.isEmpty()) {
            SharedPreferences p = prefs(context);
            url    = p.getString(KEY_URL       + dId, "");
            path   = p.getString(KEY_DEST_PATH + dId, "");
            title  = p.getString(KEY_TITLE     + dId, "");
            folder = p.getString(KEY_FOLDER    + dId, "");
        }

        if (url.isEmpty() || path.isEmpty()) {
            Log.e(TAG, "resumeDownload: missing url or path for dId=" + dId);
            return;
        }

        pauseMap.put(dId, false);
        setStatus(dId, 2);
        saveDownloadState(context, dId);

        Intent intent = new Intent(context, ReelixPublicDownloadService.class);
        intent.setAction("RESUME_DOWNLOAD");
        intent.putExtra("download_id", dId);
        intent.putExtra("url",         url);
        intent.putExtra("dest_path",   path);
        intent.putExtra("title",       title);
        intent.putExtra("meta_folder", folder);
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

            String url    = p.getString(KEY_URL       + dId, "");
            String path   = p.getString(KEY_DEST_PATH + dId, "");
            String title  = p.getString(KEY_TITLE     + dId, "");
            String folder = p.getString(KEY_FOLDER    + dId, "");

            if (url.isEmpty() || path.isEmpty()) continue;

            Log.d(TAG, "resumeAllDownloads: resuming dId=" + dId);

            progressMap       .put(dId, p.getInt (KEY_PROGRESS   + dId, 0));
            downloadedBytesMap.put(dId, p.getLong(KEY_DOWNLOADED + dId, 0L));
            totalBytesMap     .put(dId, p.getLong(KEY_TOTAL      + dId, 0L));
            statusMap         .put(dId, 2);
            urlMap            .put(dId, url);
            destPathMap       .put(dId, path);
            titleMap          .put(dId, title);
            folderPathMap     .put(dId, folder);
            pauseMap          .put(dId, false);

            File partial   = new File(path + ".partial");
            long diskBytes = partial.exists() ? partial.length() : 0L;

            Intent intent = new Intent(context, ReelixPublicDownloadService.class);
            intent.setAction("START_DOWNLOAD");
            intent.putExtra("url",            url);
            intent.putExtra("dest_path",      path);
            intent.putExtra("title",          title);
            intent.putExtra("download_id",    dId);
            intent.putExtra("existing_bytes", diskBytes);
            intent.putExtra("meta_folder",    folder);
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
                    deleteRecursive(new File(folderPath));
                }
            }, 600);
        } else {
            deleteRecursive(new File(folderPath));
        }
    }

    private static void deleteRecursive(File file) {
        if (!file.exists()) return;
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) deleteRecursive(child);
            }
        }
        file.delete();
    }

    // ── UI Helpers ───────────────────────────────────────────────────────────

    public static void loadActiveMetadata(Context context,
                                          ArrayList<HashMap<String, Object>> targetList) {
        ArrayList<HashMap<String, Object>> tempList = new ArrayList<>();
        try {
            File root = getPublicRoot();
            File[] folders = root.listFiles();
            if (folders != null) {
                for (File folder : folders) {
                    if (!folder.isDirectory()) continue;
                    File metaFile = new File(folder, "metadata.json");
                    if (!metaFile.exists()) continue;

                    java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(metaFile));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) sb.append(line);
                    br.close();
                    JSONObject obj = new JSONObject(sb.toString());

                    if (!obj.optBoolean("is_downloading", false)) continue;

                    String dId = obj.optString("download_id", folder.getName());
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("download_id",    dId);
                    map.put("title",          obj.optString("title", "Unknown"));
                    map.put("cover_url",      obj.optString("thumbnail_url", ""));
                    map.put("quality",        obj.optString("quality", ""));
                    map.put("type",           obj.optString("type", "video"));
                    map.put("meta_file_path", metaFile.getAbsolutePath());
                    map.put("folder_path",    folder.getAbsolutePath());
                    map.put("progress",       0);
                    map.put("bytes_so_far",   0L);
                    map.put("bytes_total",    0L);
                    map.put("status",         2);
                    tempList.add(map);
                }
            }
            targetList.clear();
            targetList.addAll(tempList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (HashMap<String, Object> item : targetList) {
            String dId = String.valueOf(item.get("download_id"));
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
                markMetadataFinished(String.valueOf(task.get("meta_file_path")));
                clearDownloadState(context, dId);
                toRemove.add(task);
            }
        }

        targetList.removeAll(toRemove);
    }

    public static void loadCompletedDownloads(Context context,
                                              ArrayList<HashMap<String, Object>> targetList) {
        targetList.clear();
        try {
            File root = getPublicRoot();
            File[] folders = root.listFiles();
            if (folders == null) return;

            for (File folder : folders) {
                if (!folder.isDirectory()) continue;
                File metaFile = new File(folder, "metadata.json");
                if (!metaFile.exists()) continue;

                java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(metaFile));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sb.append(line);
                br.close();
                JSONObject obj = new JSONObject(sb.toString());

                if (obj.optBoolean("is_downloading", true)) continue;

                String videoFile = obj.optString("video_file_name", "");
                if (videoFile.isEmpty()) continue;
                File vf = new File(folder, videoFile);
                if (!vf.exists()) continue;

                HashMap<String, Object> map = new HashMap<>();
                map.put("title",       obj.optString("title", "Unknown"));
                map.put("cover_url",   obj.optString("thumbnail_url", ""));
                map.put("quality",     obj.optString("quality", ""));
                map.put("type",        obj.optString("type", "video"));
                map.put("size",        formatSize(vf.length()));
                map.put("folder_path", folder.getAbsolutePath());
                map.put("video_path",  vf.getAbsolutePath());
                targetList.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String formatSize(long bytes) {
        return String.format(java.util.Locale.US, "%.1f MB", bytes / (1024.0 * 1024.0));
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
