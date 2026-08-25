package com.error404.reelix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class VideoListDiffHelper {

    /** Returns the ids present in the current live list, for quick lookup. */
    public static Set<String> getExistingPaths(ArrayList<HashMap<String, Object>> list) {
        Set<String> paths = new HashSet<>();
        for (HashMap<String, Object> item : list) {
            paths.add(String.valueOf(item.get("path")));
        }
        return paths;
    }

    /** Checks whether a video (by path) still exists in the freshly scanned set. */
    public static boolean existsInFreshSet(String path, Set<String> freshPaths) {
        return freshPaths.contains(path);
    }
}