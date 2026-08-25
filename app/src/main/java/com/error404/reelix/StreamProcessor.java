/*package com.error404.reelix;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class StreamProcessor {
    
    private Context context;
    private String streamUrl;
    private OnStreamsReadyListener listener;
    
    public interface OnStreamsReadyListener {
        void onSuccess(ArrayList<HashMap<String, Object>> streamsList);
        void onError(String error);
    }
    
    public StreamProcessor(Context context, String streamUrl, OnStreamsReadyListener listener) {
        this.context = context;
        this.streamUrl = streamUrl;
        this.listener = listener;
    }
    
    public void process() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String proxiedUrl = "https://movie-scraper-pied.vercel.app/api?url=" + 
                        URLEncoder.encode(streamUrl, "UTF-8");
                    
                    ArrayList<HashMap<String, Object>> qualities = parseQualities(proxiedUrl);
                    
                    if (qualities.isEmpty()) {
                        HashMap<String, Object> fallback = new HashMap<>();
                        fallback.put("resolution", "Auto");
                        fallback.put("url", proxiedUrl);
                        fallback.put("size", "Unknown");
                        qualities.add(fallback);
                    }
                    
                    final ArrayList<HashMap<String, Object>> finalQualities = qualities;
                    
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if (listener != null) {
                                listener.onSuccess(finalQualities);
                            }
                        }
                    });
                    
                } catch (final Exception e) {
                    e.printStackTrace();
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if (listener != null) {
                                listener.onError(e.getMessage());
                            }
                        }
                    });
                }
            }
        }).start();
    }
    
    private ArrayList<HashMap<String, Object>> parseQualities(String playlistUrl) {
        ArrayList<HashMap<String, Object>> qualities = new ArrayList<>();
        
        try {
            URL url = new URL(playlistUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            connection.connect();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            String currentQualityUrl = null;
            int currentHeight = 0;
            int currentBandwidth = 0;
            
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#EXT-X-STREAM-INF")) {
                    currentHeight = 0;
                    currentBandwidth = 0;
                    currentQualityUrl = null;
                    
                    if (line.contains("RESOLUTION=")) {
                        String resolutionPart = line.substring(line.indexOf("RESOLUTION=") + 11);
                        if (resolutionPart.contains(",")) {
                            resolutionPart = resolutionPart.split(",")[0];
                        }
                        if (resolutionPart.contains("x")) {
                            String[] dimensions = resolutionPart.split("x");
                            try {
                                currentHeight = Integer.parseInt(dimensions[1]);
                            } catch (NumberFormatException e) {
                                currentHeight = 0;
                            }
                        }
                    }
                    
                    if (line.contains("BANDWIDTH=")) {
                        String bandwidthPart = line.substring(line.indexOf("BANDWIDTH=") + 10);
                        if (bandwidthPart.contains(",")) {
                            bandwidthPart = bandwidthPart.split(",")[0];
                        }
                        try {
                            currentBandwidth = Integer.parseInt(bandwidthPart);
                        } catch (NumberFormatException e) {
                            currentBandwidth = 0;
                        }
                    }
                    
                    // Read next line for URL
                    currentQualityUrl = reader.readLine();
                    
                    if (currentQualityUrl != null && !currentQualityUrl.startsWith("#")) {
                        if (!currentQualityUrl.startsWith("http")) {
                            int lastSlash = playlistUrl.lastIndexOf('/');
                            String baseUrl = playlistUrl.substring(0, lastSlash + 1);
                            currentQualityUrl = baseUrl + currentQualityUrl;
                        }
                        
                        HashMap<String, Object> quality = new HashMap<>();
                        String qualityName = getQualityLabel(currentHeight, currentBandwidth);
                        quality.put("resolution", qualityName);
                        quality.put("url", currentQualityUrl);
                        quality.put("height", currentHeight);
                        quality.put("bandwidth", currentBandwidth);
                        
                        if (currentBandwidth > 0) {
                            long estimatedMB = (currentBandwidth / 8) * 300 / 1024 / 1024;
                            quality.put("size", estimatedMB + " MB (est)");
                        } else {
                            quality.put("size", "Unknown");
                        }
                        
                        qualities.add(quality);
                    }
                }
            }
            
            reader.close();
            connection.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Sort by height (highest first)
        for (int i = 0; i < qualities.size() - 1; i++) {
            for (int j = i + 1; j < qualities.size(); j++) {
                int height1 = (int) qualities.get(i).get("height");
                int height2 = (int) qualities.get(j).get("height");
                if (height1 < height2) {
                    HashMap<String, Object> temp = qualities.get(i);
                    qualities.set(i, qualities.get(j));
                    qualities.set(j, temp);
                }
            }
        }
        
        return qualities;
    }
    
    private String getQualityLabel(int height, int bandwidth) {
        if (height >= 2160) return "4K";
        if (height >= 1080) return "1080p";
        if (height >= 720) return "720p";
        if (height >= 480) return "480p";
        if (height >= 360) return "360p";
        if (height >= 240) return "240p";
        if (bandwidth > 5000000) return "HD";
        if (bandwidth > 2000000) return "SD";
        return "Auto";
    }
}*/