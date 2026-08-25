package com.error404.reelix;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;

public class FilterTopRatedResponse {

    public static ArrayList<HashMap<String, Object>> parseMovies(String jsonString) {
        ArrayList<HashMap<String, Object>> movieList = new ArrayList<>();
        String imageBaseUrl = "https://image.tmdb.org/t/p/w500";

        try {
            JSONObject root = new JSONObject(jsonString);
            JSONArray resultsArray = root.getJSONArray("results");

           for (int i = 0; i < resultsArray.length(); i++) {                JSONObject item = resultsArray.getJSONObject(i);
                HashMap<String, Object> movieMap = new HashMap<>();

                movieMap.put("id", String.valueOf(item.optInt("id", 0)));
                movieMap.put("primaryTitle", item.optString("title", ""));
                movieMap.put("plot", item.optString("overview", ""));

                // Image processing
                if (!item.isNull("poster_path") && !item.optString("poster_path").isEmpty()) {
                    movieMap.put("imageUrl", imageBaseUrl + item.optString("poster_path"));
                } else if (!item.isNull("backdrop_path") && !item.optString("backdrop_path").isEmpty()) {
                    movieMap.put("imageUrl", imageBaseUrl + item.optString("backdrop_path"));
                } else {
                    movieMap.put("imageUrl", ""); 
                }

                // Extract Year substring
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
        } catch (JSONException e) {
            android.util.Log.e("TOP_RATED_PARSE_ERROR", "Error: " + e.getMessage());
        }

        return movieList;
    }
}