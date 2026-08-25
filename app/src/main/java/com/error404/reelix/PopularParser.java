package com.error404.reelix;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;

public class PopularParser {

    public static ArrayList<HashMap<String, Object>> parseMovies(String jsonString) {
        ArrayList<HashMap<String, Object>> movieList = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(jsonString);
            
            // Check if the response has a "data" object with "results"
            JSONArray resultsArray;
            if (root.has("data") && root.getJSONObject("data").has("results")) {
                // New API format: { "data": { "results": [...] } }
                resultsArray = root.getJSONObject("data").getJSONArray("results");
            } else if (root.has("results")) {
                // Old TMDB format: { "results": [...] }
                resultsArray = root.getJSONArray("results");
            } else {
                // Fallback: try to get results directly
                resultsArray = root.getJSONArray("results");
            }

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject item = resultsArray.getJSONObject(i);
                HashMap<String, Object> movieMap = new HashMap<>();

                // Get IDs - include id, tmdb_id, and paxsenix_id
                String id = item.optString("id", "");
                movieMap.put("id", id);
                
                // Get tmdb_id
                String tmdbId = "";
                if (!item.isNull("tmdb_id")) {
                    Object tmdbObj = item.get("tmdb_id");
                    if (tmdbObj instanceof Integer) {
                        tmdbId = String.valueOf(item.optInt("tmdb_id", 0));
                    } else if (tmdbObj instanceof String) {
                        tmdbId = item.optString("tmdb_id", "");
                    }
                }
                movieMap.put("tmdb_id", tmdbId);
                
                // Get paxsenix_id
                String paxsenixId = "";
                if (!item.isNull("paxsenix_id")) {
                    Object paxsenixObj = item.get("paxsenix_id");
                    if (paxsenixObj instanceof Integer) {
                        paxsenixId = String.valueOf(item.optInt("paxsenix_id", 0));
                    } else if (paxsenixObj instanceof String) {
                        paxsenixId = item.optString("paxsenix_id", "");
                    }
                }
                movieMap.put("paxsenix_id", paxsenixId);

                // Detect media type
                String mediaType = item.optString("media_type", "movie");
                movieMap.put("media_type", mediaType);

                // Title: tv shows use "name", movies use "title"
                String title = "";
                if (mediaType.equals("tv")) {
                    title = item.optString("name", item.optString("title", ""));
                } else {
                    title = item.optString("title", item.optString("name", ""));
                }
                movieMap.put("primaryTitle", title);

                // Plot/Overview
                movieMap.put("plot", item.optString("overview", ""));

                // Image Resolution - check for both poster_url (new API) and poster_path (TMDB)
                String imageUrl = "";
                if (!item.isNull("poster_url") && !item.optString("poster_url").isEmpty()) {
                    // New API uses full URL in poster_url
                    imageUrl = item.optString("poster_url");
                } else if (!item.isNull("poster_path") && !item.optString("poster_path").isEmpty()) {
                    // Old TMDB format uses poster_path
                    imageUrl = "https://image.tmdb.org/t/p/w500" + item.optString("poster_path");
                } else if (!item.isNull("cover_url") && !item.optString("cover_url").isEmpty()) {
                    // Fallback to cover_url
                    imageUrl = item.optString("cover_url");
                } else if (!item.isNull("backdrop_url") && !item.optString("backdrop_url").isEmpty()) {
                    // Fallback to backdrop_url
                    imageUrl = item.optString("backdrop_url");
                } else if (!item.isNull("backdrop_path") && !item.optString("backdrop_path").isEmpty()) {
                    // Fallback to backdrop_path
                    imageUrl = "https://image.tmdb.org/t/p/w500" + item.optString("backdrop_path");
                }
                movieMap.put("imageUrl", imageUrl);

                // Release Date/Year: tv uses "first_air_date", movies use "release_date"
                String dateKey = mediaType.equals("tv") ? "first_air_date" : "release_date";
                String releaseDate = item.optString(dateKey, "");
                
                // Also check for release_date in the new API format (it might be directly available)
                if (releaseDate.isEmpty()) {
                    releaseDate = item.optString("release_date", "");
                }
                
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

                // Rating - check for vote_average or imdb_rating
                double rating = 0.0;
                if (!item.isNull("vote_average")) {
                    rating = item.optDouble("vote_average", 0.0);
                } else if (!item.isNull("imdb_rating")) {
                    try {
                        String imdbRating = item.optString("imdb_rating", "0");
                        if (!imdbRating.isEmpty()) {
                            rating = Double.parseDouble(imdbRating);
                        }
                    } catch (Exception e) {
                        rating = 0.0;
                    }
                }
                movieMap.put("rating", rating);

                // Additional fields that might be useful
                if (!item.isNull("duration")) {
                    movieMap.put("duration", item.optString("duration", ""));
                }
                
                if (!item.isNull("viewers")) {
                    movieMap.put("viewers", item.optInt("viewers", 0));
                }
                
                // Country and language
                if (!item.isNull("country")) {
                    movieMap.put("country", item.optString("country", ""));
                }
                
                if (!item.isNull("language")) {
                    movieMap.put("language", item.optString("language", ""));
                }
                
                // Content rating
                if (!item.isNull("content_rating")) {
                    movieMap.put("content_rating", item.optString("content_rating", ""));
                }

                movieList.add(movieMap);
            }
        } catch (JSONException e) {
            android.util.Log.e("POPULAR_PARSE_ERROR", "Error: " + e.getMessage());
        }

        return movieList;
    }
}