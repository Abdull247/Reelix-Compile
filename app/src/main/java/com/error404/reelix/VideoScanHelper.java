package com.error404.reelix;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;

import java.util.HashMap;

public class VideoScanHelper {

    public interface OnVideoFoundListener {
        void onVideoFound(HashMap<String, Object> videoItem, int currentIndex, int totalCount);
        void onScanComplete(int totalFound);
        void onScanError(String message);
    }

    private Context context;
    private volatile boolean isCancelled = false;
    private Handler mainHandler = new Handler(Looper.getMainLooper());

    public VideoScanHelper(Context context) {
        this.context = context;
    }

    public void cancelScan() {
        isCancelled = true;
    }

    public void scanVideos(final OnVideoFoundListener listener) {
        isCancelled = false;

        Uri collection = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = new String[]{
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DATE_ADDED
        };

        String sortOrder = MediaStore.Video.Media.DATE_ADDED + " DESC";

        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(
                    collection, projection, null, null, sortOrder);

            if (cursor == null) {
                postError(listener, "Could not access media store");
                return;
            }

            int totalCount = cursor.getCount();
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            int nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
            int sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);
            int durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION);
            int dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);

            int currentIndex = 0;

            while (cursor.moveToNext()) {
                if (isCancelled) break;

                currentIndex++;

                long id = cursor.getLong(idColumn);
                String name = cursor.getString(nameColumn);
                long size = cursor.getLong(sizeColumn);
                long duration = cursor.getLong(durationColumn);
                String path = cursor.getString(dataColumn);

                Uri contentUri = ContentUris.withAppendedId(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id);

                HashMap<String, Object> item = new HashMap<>();
                item.put("id", id);
                item.put("name", name != null ? name : "Unknown");
                item.put("path", path);
                item.put("uri", contentUri.toString());
                item.put("size", size);
                item.put("sizeFormatted", formatSize(size));
                item.put("duration", duration);
                item.put("durationFormatted", formatDuration(duration));
                item.put("is_local", true);
                item.put("type", "movie");

                final int fCurrent = currentIndex;
                final int fTotal = totalCount;

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null) {
                            listener.onVideoFound(item, fCurrent, fTotal);
                        }
                    }
                });

                try {
                    Thread.sleep(15);
                } catch (InterruptedException ignored) {
                }
            }

            final int finalCount = currentIndex;
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (listener != null) listener.onScanComplete(finalCount);
                }
            });

        } catch (Exception e) {
            Log.e("VideoScanHelper", "Scan error", e);
            postError(listener, e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    private void postError(final OnVideoFoundListener listener, final String message) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (listener != null) listener.onScanError(message);
            }
        });
    }

    public static String formatDuration(long millis) {
        long totalSeconds = millis / 1000;
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        if (hours > 0) {
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%d:%02d", minutes, seconds);
        }
    }

    public static String formatSize(long bytes) {
        if (bytes <= 0) return "0 B";
        String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(bytes) / Math.log10(1024));
        if (digitGroups >= units.length) digitGroups = units.length - 1;
        return String.format("%.1f %s", bytes / Math.pow(1024, digitGroups), units[digitGroups]);
    }
}