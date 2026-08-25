package com.error404.reelix;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReelixPublicDownloadService extends Service {

    public static final String CHANNEL_ID = "ReelixPublicDownloads";
    private static final String TAG = "ReelixPublicDownload";

    private static final Set<String> activeDownloads = new HashSet<>();

    private ExecutorService     executorService;
    private NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        executorService     = Executors.newFixedThreadPool(3);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            checkServiceLifecycle();
            return START_STICKY;
        }

        String action = intent.getAction();
        if (action == null) {
            checkServiceLifecycle();
            return START_STICKY;
        }

        Log.d(TAG, "onStartCommand action=" + action);

        switch (action) {

            case "START_DOWNLOAD": {
                String url           = intent.getStringExtra("url");
                String path          = intent.getStringExtra("dest_path");
                String title         = intent.getStringExtra("title");
                String dId           = intent.getStringExtra("download_id");
                String metaFolder    = intent.getStringExtra("meta_folder");
                long   existingBytes = intent.getLongExtra("existing_bytes", 0);

                Log.d(TAG, "START_DOWNLOAD dId=" + dId + " url=" + url);

                if (url == null || path == null || dId == null) {
                    Log.e(TAG, "START_DOWNLOAD missing extras, aborting");
                    break;
                }

                if (!ReelixPublicDownloadHelper.pauseMap.containsKey(dId)) {
                    ReelixPublicDownloadHelper.pauseMap.put(dId, false);
                }

                int nId = notifId(dId);
                startForeground(nId, buildProgressNotification(title, 0));

                synchronized (activeDownloads) {
                    if (activeDownloads.contains(dId)) {
                        Log.d(TAG, "dId already active, skip: " + dId);
                        break;
                    }
                    activeDownloads.add(dId);
                }

                executorService.execute(
                        new DownloadTask(url, path, title, dId, existingBytes, metaFolder, this));
                break;
            }

            case "PAUSE_DOWNLOAD": {
                String dId = intent.getStringExtra("download_id");
                Log.d(TAG, "PAUSE_DOWNLOAD dId=" + dId);
                if (dId != null) {
                    ReelixPublicDownloadHelper.pauseMap.put(dId, true);
                    ReelixPublicDownloadHelper.setStatus(dId, 4);
                    ReelixPublicDownloadHelper.saveDownloadState(this, dId);
                }
                break;
            }

            case "RESUME_DOWNLOAD": {
                String dId        = intent.getStringExtra("download_id");
                String url        = intent.getStringExtra("url");
                String path       = intent.getStringExtra("dest_path");
                String title      = intent.getStringExtra("title");
                String metaFolder = intent.getStringExtra("meta_folder");
                Log.d(TAG, "RESUME_DOWNLOAD dId=" + dId);
                if (dId == null || url == null || path == null) break;

                ReelixPublicDownloadHelper.pauseMap.put(dId, false);
                ReelixPublicDownloadHelper.setStatus(dId, 2);
                ReelixPublicDownloadHelper.saveDownloadState(this, dId);

                int nId = notifId(dId);
                int currentProgress = ReelixPublicDownloadHelper.getProgress(dId);
                startForeground(nId, buildProgressNotification(title, currentProgress));

                synchronized (activeDownloads) {
                    if (activeDownloads.contains(dId)) break;
                    activeDownloads.add(dId);
                }

                long resumeFrom = ReelixPublicDownloadHelper.getDownloadedBytes(dId);
                executorService.execute(
                        new DownloadTask(url, path, title, dId, resumeFrom, metaFolder, this));
                break;
            }
        }

        return START_STICKY;
    }

    private void checkServiceLifecycle() {
        synchronized (activeDownloads) {
            if (activeDownloads.isEmpty()) {
                Log.d(TAG, "No active tasks remaining. Shutting down service safely.");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    stopForeground(STOP_FOREGROUND_REMOVE);
                } else {
                    stopForeground(true);
                }
                stopSelf();
            }
        }
    }

    // ── Notifications ────────────────────────────────────────────────────────

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel ch = new NotificationChannel(
                    CHANNEL_ID, "Downloads", NotificationManager.IMPORTANCE_LOW);
            ch.setSound(null, null);
            if (notificationManager != null)
                notificationManager.createNotificationChannel(ch);
        }
    }

    Notification buildProgressNotification(String title, int progress) {
        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Downloading: " + title)
                .setContentText(progress + "%")
                .setProgress(100, progress, false)
                .setSmallIcon(android.R.drawable.stat_sys_download)
                .setOngoing(true)
                .setSilent(true)
                .build();
    }

    static int notifId(String dId) {
        return (int) (Math.abs(dId.hashCode()) % 100000);
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }

    // ── Download Task ────────────────────────────────────────────────────────

    private class DownloadTask implements Runnable {

        private final String  urlStr, path, title, dId, metaFolder;
        private final long    startFromBytes;
        private final Context ctx;

        DownloadTask(String u, String p, String t, String id,
                     long startFrom, String metaFolder, Context c) {
            urlStr         = u;
            path           = p;
            title          = t;
            dId            = id;
            startFromBytes = startFrom;
            this.metaFolder = metaFolder;
            ctx            = c;
        }

        @Override
        public void run() {
            Log.d(TAG, "DownloadTask run() dId=" + dId);

            File              partial = new File(path + ".partial");
            RandomAccessFile  raf     = null;
            HttpURLConnection conn    = null;

            try {
                long diskBytes  = partial.exists() ? partial.length() : 0L;
                long resumeFrom = Math.min(Math.max(diskBytes, startFromBytes), diskBytes);

                Log.d(TAG, "diskBytes=" + diskBytes + " resumeFrom=" + resumeFrom);

                conn = openConnection(urlStr, resumeFrom);
                conn.connect();

                int responseCode = conn.getResponseCode();
                Log.d(TAG, "responseCode=" + responseCode);

                long totalSize;
                long writeFrom;

                if (responseCode == HttpURLConnection.HTTP_PARTIAL) {
                    String contentRange = conn.getHeaderField("Content-Range");
                    Log.d(TAG, "Content-Range=" + contentRange);
                    if (contentRange != null && contentRange.contains("/")) {
                        totalSize = Long.parseLong(
                                contentRange.substring(contentRange.indexOf('/') + 1).trim());
                    } else {
                        totalSize = resumeFrom + conn.getContentLengthLong();
                    }
                    writeFrom = resumeFrom;
                } else if (responseCode == HttpURLConnection.HTTP_OK) {
                    totalSize = conn.getContentLengthLong();
                    writeFrom = 0;
                    if (partial.exists()) partial.delete();
                } else {
                    Log.e(TAG, "Unexpected response code: " + responseCode);
                    ReelixPublicDownloadHelper.setStatus(dId, 16);
                    ReelixPublicDownloadHelper.saveDownloadState(ctx, dId);
                    return;
                }

                Log.d(TAG, "totalSize=" + totalSize + " writeFrom=" + writeFrom);

                if (totalSize <= 0) {
                    long cachedTotal = ReelixPublicDownloadHelper.getTotalBytes(dId);
                    totalSize = cachedTotal > 0 ? cachedTotal : -1;
                }

                ReelixPublicDownloadHelper.setTotalBytes(dId, totalSize);
                ReelixPublicDownloadHelper.setDownloadedBytes(dId, writeFrom);
                ReelixPublicDownloadHelper.setProgress(dId,
                        totalSize > 0 ? (int) (writeFrom * 100L / totalSize) : 0);
                ReelixPublicDownloadHelper.setStatus(dId, 2);
                ReelixPublicDownloadHelper.saveDownloadState(ctx, dId);

                raf = new RandomAccessFile(partial, "rw");
                raf.seek(writeFrom);

                InputStream in        = conn.getInputStream();
                byte[]      buffer    = new byte[8192];
                long        written   = writeFrom;
                long        lastSave  = System.currentTimeMillis();
                long        lastNotif = System.currentTimeMillis();
                int         len;

                while ((len = in.read(buffer)) != -1) {

                    // ── Clean Pause Handling ───────────────────────────────────
                    if (Boolean.TRUE.equals(ReelixPublicDownloadHelper.pauseMap.getOrDefault(dId, false))) {
                        raf.close();
                        raf = null;

                        ReelixPublicDownloadHelper.setStatus(dId, 4);
                        ReelixPublicDownloadHelper.setDownloadedBytes(dId, written);
                        ReelixPublicDownloadHelper.setProgress(dId,
                                totalSize > 0 ? (int) (written * 100L / totalSize) : 0);
                        ReelixPublicDownloadHelper.saveDownloadState(ctx, dId);

                        notificationManager.cancel(notifId(dId));
                        Log.d(TAG, "Paused safely from loop. written=" + written);
                        return;
                    }

                    // ── Write ────────────────────────────────────────────
                    raf.write(buffer, 0, len);
                    written += len;

                    int  pct = totalSize > 0 ? (int) (written * 100L / totalSize) : 0;
                    long now = System.currentTimeMillis();

                    ReelixPublicDownloadHelper.setDownloadedBytes(dId, written);
                    ReelixPublicDownloadHelper.setProgress(dId, pct);
                    ReelixPublicDownloadHelper.setStatus(dId, 2);

                    if (now - lastSave > 3000) {
                        ReelixPublicDownloadHelper.saveDownloadState(ctx, dId);
                        lastSave = now;
                    }

                    if (now - lastNotif > 1500) {
                        if (!Boolean.TRUE.equals(ReelixPublicDownloadHelper.pauseMap.getOrDefault(dId, false))) {
                            notificationManager.notify(notifId(dId), buildProgressNotification(title, pct));
                        }
                        lastNotif = now;
                    }
                }

                // ── Finished ─────────────────────────────────────────────
                if (raf != null) { raf.close(); raf = null; }
                in.close();
                conn.disconnect();
                conn = null;

                File finalFile = new File(path);
                if (finalFile.exists()) finalFile.delete();
                boolean renamed = partial.renameTo(finalFile);

                ReelixPublicDownloadHelper.setStatus(dId, renamed ? 8 : 16);
                ReelixPublicDownloadHelper.setProgress(dId, renamed ? 100 : ReelixPublicDownloadHelper.getProgress(dId));
                ReelixPublicDownloadHelper.saveDownloadState(ctx, dId);
                notificationManager.cancel(notifId(dId));

                if (renamed && metaFolder != null && !metaFolder.isEmpty()) {
                    ReelixPublicDownloadHelper.markMetadataFinished(
                            new File(metaFolder, "metadata.json").getAbsolutePath());
                }

            } catch (Exception e) {
                Log.e(TAG, "DownloadTask exception for dId=" + dId, e);
                ReelixPublicDownloadHelper.setStatus(dId, 16);
                ReelixPublicDownloadHelper.saveDownloadState(ctx, dId);
                notificationManager.cancel(notifId(dId));

            } finally {
                try { if (raf  != null) raf.close();       } catch (Exception ignored) {}
                try { if (conn != null) conn.disconnect(); } catch (Exception ignored) {}
                synchronized (activeDownloads) {
                    activeDownloads.remove(dId);
                }
                progressHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        checkServiceLifecycle();
                    }
                });
            }
        }

        private HttpURLConnection openConnection(String url, long fromByte) throws Exception {
            HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
            c.setConnectTimeout(15_000);
            c.setReadTimeout(30_000);
            c.setRequestProperty("User-Agent",      "Mozilla/5.0");
            c.setRequestProperty("Accept-Encoding", "identity");
            if (fromByte > 0) {
                c.setRequestProperty("Range", "bytes=" + fromByte + "-");
            }
            return c;
        }
    }

    private final android.os.Handler progressHandler = new android.os.Handler(android.os.Looper.getMainLooper());
}
