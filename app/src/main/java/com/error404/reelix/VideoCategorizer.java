package com.error404.reelix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoCategorizer {

    // Matches common episode markers: S1E1, S01E01, Season 1 Episode 2, Ep10, E10, etc.
    private static final Pattern EPISODE_PATTERN = Pattern.compile(
            "(?i)[\\s._-]*(s(eason)?\\s*\\d{1,2}[\\s._-]*e(p(isode)?)?\\s*\\d{1,3}" +
            "|e(p(isode)?)?[\\s._-]*\\d{1,3}" +
            "|season\\s*\\d{1,2})" +
            ".*$"
    );

    // Cleans separators/extension noise for the extracted base title
    private static final Pattern CLEANUP_PATTERN = Pattern.compile("[\\s._-]+$");

    /**
     * Groups a flat list of scanned video items into categories.
     * Each returned item is either:
     *  - a "series" category: { title, type=local_category, count, items: [...], thumbPath (first ep) }
     *  - a standalone "movie" item: passed through unchanged with type=movie (already set by scanner)
     */
    public static ArrayList<HashMap<String, Object>> categorize(ArrayList<HashMap<String, Object>> flatList) {
        // Map of base title -> list of matching video items
        LinkedHashMap<String, ArrayList<HashMap<String, Object>>> groups = new LinkedHashMap<>();
        ArrayList<HashMap<String, Object>> standalone = new ArrayList<>();

        for (HashMap<String, Object> video : flatList) {
            String rawName = String.valueOf(video.get("name"));
            String nameNoExt = stripExtension(rawName);
            String baseTitle = extractBaseTitle(nameNoExt);

            if (baseTitle == null || baseTitle.length() < 2) {
                // Couldn't confidently extract a series-style base title — treat as standalone
                standalone.add(video);
                continue;
            }

            String key = baseTitle.toLowerCase();
            if (!groups.containsKey(key)) {
                groups.put(key, new ArrayList<HashMap<String, Object>>());
            }
            groups.get(key).add(video);
        }

        ArrayList<HashMap<String, Object>> result = new ArrayList<>();

        for (String key : groups.keySet()) {
            ArrayList<HashMap<String, Object>> episodes = groups.get(key);

            if (episodes.size() >= 2) {
                // Confirmed series — build a category item
                HashMap<String, Object> categoryItem = new HashMap<>();
                String displayTitle = toTitleCase(key);

                categoryItem.put("title", displayTitle);
                categoryItem.put("name", displayTitle); // so vid_title binds the same way
                categoryItem.put("type", "local_category");
                categoryItem.put("count", episodes.size());
                categoryItem.put("sizeFormatted", episodes.size() + (episodes.size() == 1 ? " video" : " videos"));

                // Use the first episode's path/thumbnail as the category cover
                HashMap<String, Object> firstEp = episodes.get(0);
                categoryItem.put("path", firstEp.get("path")); // used for thumbnail derivation
                categoryItem.put("is_local", true);
                categoryItem.put("folder_path", getParentFolder(String.valueOf(firstEp.get("path"))));

                categoryItem.put("items", episodes); // full episode list for the bottom sheet

                result.add(categoryItem);
            } else {
                // Only one video matched this "base title" — not really a series, treat as standalone
                standalone.addAll(episodes);
            }
        }

        result.addAll(standalone);
        return result;
    }

    private static String extractBaseTitle(String nameNoExt) {
        Matcher matcher = EPISODE_PATTERN.matcher(nameNoExt);
        if (!matcher.find()) return null;

        String base = nameNoExt.substring(0, matcher.start());
        base = CLEANUP_PATTERN.matcher(base).replaceAll("");
        base = base.replace('_', ' ').replace('.', ' ').replace('-', ' ').trim();
        base = base.replaceAll("\\s+", " ");

        return base.isEmpty() ? null : base;
    }

    private static String stripExtension(String name) {
        int dot = name.lastIndexOf('.');
        return dot > 0 ? name.substring(0, dot) : name;
    }

    private static String getParentFolder(String path) {
        if (path == null) return "";
        int lastSlash = path.lastIndexOf('/');
        return lastSlash > 0 ? path.substring(0, lastSlash) : path;
    }

    private static String toTitleCase(String input) {
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (w.isEmpty()) continue;
            sb.append(Character.toUpperCase(w.charAt(0)));
            if (w.length() > 1) sb.append(w.substring(1));
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}