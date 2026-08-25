package com.error404.reelix;

import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class ReelixStorageHelper {

    public static File getVideoRoot(Context context) {
        File baseDir = context.getExternalFilesDir(null);
        if (baseDir == null) return null;
        File root = new File(baseDir, "Downloads/Videos");
        if (!root.exists()) root.mkdirs();
        return root;
    }

    public static String sanitiseName(String raw) {
        String safe = raw.replaceAll("[\\\\/:*?\"<>|]", "").trim();
        return safe.isEmpty() ? "Video_" + System.currentTimeMillis() : safe;
    }

    public static File getMovieFolder(Context context, String safeMovieName) {
        File baseDir = context.getExternalFilesDir(null);
        if (baseDir == null) return null;
        File folder = new File(baseDir, "Downloads/Videos/" + safeMovieName);
        if (!folder.exists()) folder.mkdirs();
        return folder;
    }

    public static String buildMovieFileName(String quality) {
        return "Reelix_" + quality + "_" + System.currentTimeMillis() + ".mp4";
    }

    public static File getEpisodeFolder(Context context, String safeShowName,
                                        String seasonNum, String episodeNum) {
        File baseDir = context.getExternalFilesDir(null);
        if (baseDir == null) return null;
        File folder = new File(baseDir, "Downloads/Videos/" + safeShowName
                + "/Season " + seasonNum + "/ep" + episodeNum);
        if (!folder.exists()) folder.mkdirs();
        return folder;
    }

    public static String buildEpisodeFileName(String seasonNum, String episodeNum, String quality) {
        return "S" + seasonNum + "E" + episodeNum + "_" + quality + ".mp4";
    }

    public static void writeMovieMetadata(File movieFolder, String downloadId, String title,
                                          String posterUrl, String tmdbId, String quality,
                                          String fileName) {
        try {
            JSONObject json = new JSONObject();
            json.put("download_id",    downloadId);
            json.put("title",          title);
            json.put("cover_url",      posterUrl);
            json.put("tmdb_id",        tmdbId);
            json.put("resolution",     quality);
            json.put("video_file_name", fileName);
            json.put("download_date",  System.currentTimeMillis());
            json.put("is_downloading", true);
            json.put("type",           "movie");
            writeJson(new File(movieFolder, "metadata.json"), json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeEpisodeMetadata(File episodeFolder, String downloadId, String title,
                                            String episodeTitle, String posterUrl, String tmdbId,
                                            String seasonNum, String episodeNum, String quality,
                                            String fileName) {
        try {
            JSONObject json = new JSONObject();
            json.put("download_id",    downloadId);
            json.put("title",          title);
            json.put("episode_title",  episodeTitle);
            json.put("cover_url",      posterUrl);
            json.put("tmdb_id",        tmdbId);
            json.put("season_number",  seasonNum);
            json.put("episode_number", episodeNum);
            json.put("resolution",     quality);
            json.put("video_file_name", fileName);
            json.put("download_date",  System.currentTimeMillis());
            json.put("is_downloading", true);
            json.put("type",           "tv");
            writeJson(new File(episodeFolder, "metadata.json"), json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateTvMasterMetadata(File baseDir, String safeShowName, String tmdbId,
                                              String posterUrl, String seasonNum, String episodeNum,
                                              String episodeTitle, String quality, String fileName,
                                              String downloadId) {
        try {
            File showFolder = new File(baseDir, "Downloads/Videos/" + safeShowName);
            if (!showFolder.exists()) showFolder.mkdirs();

            File masterMeta = new File(showFolder, "metadata.json");
            JSONObject masterJson = masterMeta.exists() ? readJson(masterMeta) : new JSONObject();

            masterJson.put("title",   safeShowName);
            masterJson.put("tmdb_id", tmdbId);
            masterJson.put("poster",  posterUrl);
            masterJson.put("type",    "tv");

            JSONObject seasons = masterJson.optJSONObject("seasons");
            if (seasons == null) seasons = new JSONObject();

            JSONArray seasonEpisodes = seasons.optJSONArray(seasonNum);
            if (seasonEpisodes == null) seasonEpisodes = new JSONArray();

            JSONObject epEntry = new JSONObject();
            epEntry.put("episode",       Integer.parseInt(episodeNum));
            epEntry.put("episode_title", episodeTitle);
            epEntry.put("quality",       quality);
            epEntry.put("file_name",     fileName);
            epEntry.put("download_id",   downloadId);
            epEntry.put("downloaded_at", System.currentTimeMillis());

            seasonEpisodes.put(epEntry);
            seasons.put(seasonNum, seasonEpisodes);
            masterJson.put("seasons", seasons);
            writeJson(masterMeta, masterJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void markDownloadFinished(String metaFilePath) {
        try {
            File metaFile = new File(metaFilePath);
            if (!metaFile.exists()) return;
            JSONObject obj = readJson(metaFile);
            obj.put("is_downloading", false);
            writeJson(metaFile, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteDownload(String folderPath) {
        try {
            File folder = new File(folderPath);
            if (folder.exists()) {
                deleteRecursive(folder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteRecursive(File file) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    deleteRecursive(child);
                }
            }
        }
        file.delete();
    }

    public static void loadActiveMetadata(Context context,
                                          ArrayList<HashMap<String, Object>> targetList) {
        ArrayList<HashMap<String, Object>> tempList = new ArrayList<>();
        try {
            File videoRoot = getVideoRoot(context);
            if (videoRoot == null) return;
            File[] showFolders = videoRoot.listFiles();
            if (showFolders == null) return;

            for (File show : showFolders) {
                if (!show.isDirectory()) continue;
                File masterMeta = new File(show, "metadata.json");
                if (!masterMeta.exists()) continue;

                JSONObject master = readJson(masterMeta);
                String type = master.optString("type", "movie");

                if (type.equals("tv")) {
                    File[] seasons = show.listFiles();
                    if (seasons == null) continue;
                    for (File season : seasons) {
                        if (!season.isDirectory()) continue;
                        File[] episodes = season.listFiles();
                        if (episodes == null) continue;
                        for (File ep : episodes) {
                            if (!ep.isDirectory()) continue;
                            File epMeta = new File(ep, "metadata.json");
                            if (!epMeta.exists()) continue;
                            JSONObject epObj = readJson(epMeta);
                            if (!epObj.optBoolean("is_downloading", false)) continue;
                            String dId = epObj.optString("download_id", "");
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("download_id",    dId);
                            map.put("title",          epObj.optString("title", "Unknown"));
                            map.put("episode_title",  epObj.optString("episode_title", ""));
                            map.put("cover_url",      epObj.optString("cover_url", ""));
                            map.put("meta_file_path", epMeta.getAbsolutePath());
                            map.put("type",           "tv");
                            map.put("progress",       0);
                            map.put("bytes_so_far",   0L);
                            map.put("bytes_total",    0L);
                            map.put("status",         2);
                            tempList.add(map);
                        }
                    }
                } else {
                    if (!master.optBoolean("is_downloading", false)) continue;
                    String dId = master.optString("download_id", "");
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("download_id",    dId);
                    map.put("title",          master.optString("title", "Unknown"));
                    map.put("cover_url",      master.optString("cover_url", ""));
                    map.put("meta_file_path", masterMeta.getAbsolutePath());
                    map.put("type",           "movie");
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
    }

    public static void loadCompletedDownloads(Context context,
                                              ArrayList<HashMap<String, Object>> targetList) {
        targetList.clear();
        try {
            File videoRoot = getVideoRoot(context);
            if (videoRoot == null) return;
            File[] shows = videoRoot.listFiles();
            if (shows == null) return;

            for (File show : shows) {
                if (!show.isDirectory()) continue;
                File masterMeta = new File(show, "metadata.json");
                if (!masterMeta.exists()) continue;

                JSONObject master = readJson(masterMeta);
                String type = master.optString("type", "movie");

                if (type.equals("tv")) {
                    int  completedEpisodes = 0;
                    long totalSize         = 0;
                    File[] seasons = show.listFiles();
                    if (seasons != null) {
                        for (File season : seasons) {
                            if (!season.isDirectory()) continue;
                            File[] episodes = season.listFiles();
                            if (episodes == null) continue;
                            for (File ep : episodes) {
                                if (!ep.isDirectory()) continue;
                                File epMeta = new File(ep, "metadata.json");
                                if (!epMeta.exists()) continue;
                                JSONObject epObj = readJson(epMeta);
                                if (epObj.optBoolean("is_downloading", true)) continue;
                                String videoFile = epObj.optString("video_file_name", "");
                                if (videoFile.isEmpty()) continue;
                                File vf = new File(ep, videoFile);
                                if (vf.exists()) {
                                    totalSize += vf.length();
                                    completedEpisodes++;
                                }
                            }
                        }
                    }
                    if (completedEpisodes == 0) continue;
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("title",       master.optString("title", "Unknown"));
                    map.put("cover_url",   master.optString("poster", ""));
                    map.put("type",        "tv");
                    map.put("size",        formatSize(totalSize));
                    map.put("episodes",    completedEpisodes + " episode" + (completedEpisodes > 1 ? "s" : ""));
                    map.put("folder_path", show.getAbsolutePath());
                    map.put("video_path",  "");
                    targetList.add(map);
                } else {
                    if (master.optBoolean("is_downloading", true)) continue;
                    String videoFile = master.optString("video_file_name", "");
                    if (videoFile.isEmpty()) continue;
                    File vf = new File(show, videoFile);
                    if (!vf.exists()) continue;
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("title",       master.optString("title", "Unknown"));
                    map.put("cover_url",   master.optString("cover_url", ""));
                    map.put("type",        "movie");
                    map.put("size",        formatSize(vf.length()));
                    map.put("episodes",    "");
                    map.put("folder_path", show.getAbsolutePath());
                    map.put("video_path",  vf.getAbsolutePath());
                    targetList.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONObject readJson(File file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder  sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);
        br.close();
        return new JSONObject(sb.toString());
    }

    private static void writeJson(File file, JSONObject json) throws Exception {
        FileWriter writer = new FileWriter(file);
        writer.write(json.toString(4));
        writer.flush();
        writer.close();
    }

    private static String formatSize(long bytes) {
        return String.format(java.util.Locale.US, "%.1f MB", bytes / (1024.0 * 1024.0));
    }
}