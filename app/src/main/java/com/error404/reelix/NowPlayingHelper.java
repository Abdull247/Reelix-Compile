package com.error404.reelix;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;

public class NowPlayingHelper {

    public static ArrayList<HashMap<String, Object>> parseMovies(String jsonString) {
        ArrayList<HashMap<String, Object>> movieList = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(jsonString);
            
            if (root.has("data") && !root.isNull("data")) {
                JSONObject dataObject = root.getJSONObject("data");
                JSONArray resultsArray = dataObject.getJSONArray("results");

                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject item = resultsArray.getJSONObject(i);
                    HashMap<String, Object> movieMap = new HashMap<>();

                    movieMap.put("id", item.optString("id", ""));
                    movieMap.put("paxsenix_id", item.isNull("paxsenix_id") ? "" : item.optString("paxsenix_id", ""));
                    movieMap.put("tmdb_id", String.valueOf(item.optInt("tmdb_id", 0)));

                    movieMap.put("primaryTitle", item.optString("title", "")); 
                    movieMap.put("plot", item.optString("overview", ""));      

                    if (!item.isNull("poster_url") && !item.optString("poster_url").isEmpty()) {
                        movieMap.put("imageUrl", item.optString("poster_url"));
                    } else if (!item.isNull("backdrop_url") && !item.optString("backdrop_url").isEmpty()) {
                        movieMap.put("imageUrl", item.optString("backdrop_url")); 
                    } else {
                        movieMap.put("imageUrl", ""); 
                    }

                    String releaseDate = item.optString("release_date", "");
                    if (releaseDate.length() >= 4) {
                        try {
                            movieMap.put("startYear", Integer.parseInt(releaseDate.substring(0, 4)));
                        } catch (Exception e) {
                            movieMap.put("startYear", 0);
                        }
                    } else {
                        movieMap.put("startYear", 0);
                    }
                    
                    movieMap.put("endYear", ""); 
                    movieMap.put("rating", item.optDouble("vote_average", 0.0));

                    movieList.add(movieMap);
                }
            }
        } catch (JSONException e) {
            android.util.Log.e("API_PARSE_ERROR", "Error parsing custom payload: " + e.getMessage());
        }

        return movieList;
    }
}
