package com.error404.reelix;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.HashMap;

public class MovieDetailsHelper {

    public static HashMap<String, Object> parseDetails(String jsonString, String type) {
        HashMap<String, Object> details = new HashMap<>();
        String imageBaseUrl = "https://image.tmdb.org/t/p/w780"; 

        try {
            JSONObject root = new JSONObject(jsonString);

            // 1. Handle Titles dynamically based on media type
            if (type.equalsIgnoreCase("movies") || type.equalsIgnoreCase("movie")) {
                details.put("primaryTitle", root.optString("title", "Unknown Title"));
            } else {
                details.put("primaryTitle", root.optString("name", "Unknown Title"));
            }
            
            // Plot is standard across both endpoints
            details.put("plot", root.optString("overview", "No summary available."));

            // 2. Image Handling
            if (!root.isNull("poster_path") && !root.optString("poster_path").isEmpty()) {
                details.put("imageUrl", imageBaseUrl + root.optString("poster_path"));
            } else if (!root.isNull("backdrop_path") && !root.optString("backdrop_path").isEmpty()) {
                details.put("imageUrl", imageBaseUrl + root.optString("backdrop_path"));
            } else {
                details.put("imageUrl", "");
            }

            // 3. Extract Release/Air Year
            String dateKey = (type.equalsIgnoreCase("movies") || type.equalsIgnoreCase("movie")) ? "release_date" : "first_air_date";
            String dateValue = root.optString(dateKey, "");
            if (dateValue.length() >= 4) {
                details.put("year", dateValue.substring(0, 4));
            } else {
                details.put("year", "N/A");
            }

            // 4. Runtime Extraction (Movies use int field, TV Series use an int array)
            int runtime = 0;
            if (type.equalsIgnoreCase("movies") || type.equalsIgnoreCase("movie")) {
                runtime = root.optInt("runtime", 0);
            } else {
                JSONArray runtimeArray = root.optJSONArray("episode_run_time");
                if (runtimeArray != null && runtimeArray.length() > 0) {
                    runtime = runtimeArray.optInt(0, 0);
                }
            }
            details.put("runtime", runtime + " minutes");

            // 5. Country handling
            JSONArray countries = root.optJSONArray("origin_country");
            if (countries != null && countries.length() > 0) {
                details.put("country", countries.optString(0, "Unknown"));
            } else {
                details.put("country", "Unknown");
            }

        } catch (JSONException e) {
            android.util.Log.e("DETAILS_PARSE_ERROR", "Error parsing details: " + e.getMessage());
        }

        return details;
    }
}
