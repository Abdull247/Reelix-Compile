package com.error404.reelix;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class VideoScanNotificationHelper {

    private static final String CHANNEL_ID = "reelix_scan_channel";
    private static final int NOTIFICATION_ID = 5001;
    private static final long MIN_UPDATE_INTERVAL_MS = 300; // throttle to avoid system rate-limiting

    private Context context;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;
    private long lastUpdateTime = 0;

    public VideoScanNotificationHelper(Context context) {
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        createChannel();
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Video Scanning",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel.setDescription("Shows progress while scanning for video files");
            notificationManager.createNotificationChannel(channel);
        }
    }

    public int getNotificationId() {
        return NOTIFICATION_ID;
    }

    public void showStart() {
        builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_menu_gallery)
                .setContentTitle("Scanning for media files")
                .setContentText("Starting scan...")
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setProgress(0, 0, true);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
        lastUpdateTime = System.currentTimeMillis();
    }

    /**
     * Call on every found item. Internally throttled so we don't spam the
     * system (which can cause updates to silently drop or appear frozen).
     * Pass force=true to bypass throttling (e.g. for the very first/last item).
     */
    public void updateProgress(String currentPath, int current, int total) {
        updateProgress(currentPath, current, total, false);
    }

    public void updateProgress(String currentPath, int current, int total, boolean force) {
        long now = System.currentTimeMillis();
        if (!force && (now - lastUpdateTime) < MIN_UPDATE_INTERVAL_MS) {
            return; // skip this update, too soon since the last one
        }
        lastUpdateTime = now;

        if (builder == null) showStart();

        String fileName = currentPath;
        int lastSlash = currentPath != null ? currentPath.lastIndexOf('/') : -1;
        if (lastSlash != -1 && lastSlash < currentPath.length() - 1) {
            fileName = currentPath.substring(lastSlash + 1);
        }

        builder.setContentTitle("Scanning for media files (" + current + "/" + total + ")")
                .setContentText("Discovering " + fileName)
                .setProgress(total > 0 ? total : 100, current, total <= 0);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void showComplete(int totalFound) {
        if (builder == null) showStart();

        builder.setContentTitle("Scan complete")
                .setContentText(totalFound + " videos found")
                .setOngoing(false)
                .setAutoCancel(true)
                .setProgress(0, 0, false);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void showError(String message) {
        if (builder == null) showStart();

        builder.setContentTitle("Scan failed")
                .setContentText(message != null ? message : "Unknown error")
                .setOngoing(false)
                .setAutoCancel(true)
                .setProgress(0, 0, false);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void cancel() {
        notificationManager.cancel(NOTIFICATION_ID);
    }
}