package com.error404.reelix;

import java.util.HashMap;

public class EpisodesHelper {

    // Dynamically builds the episode endpoint URL
    public static String getEndpointUrl(String seriesId, String seasonNumber) {
        return "https://api.themoviedb.org/3/tv/" + seriesId + "/season/" + seasonNumber + "?language=en-US";
    }

    // Returns your TMDB Authorization headers
    public static HashMap<String, Object> getTmdbHeaders() {
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMDEwNDFlYzgxODlhMDYwODgyM2RlNTg0YjMwNTU2NiIsIm5iZiI6MTc3ODk0MTUxNi42NzIsInN1YiI6IjZhMDg3ZTRjYjExMGNhZWNhMjk1ZGU4ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.UUCDFvZWR-8Mg347tcy4DzI4yF2PHPGJ2E6OtQnw4bw");
        headers.put("accept", "application/json");
        return headers;
    }
}
