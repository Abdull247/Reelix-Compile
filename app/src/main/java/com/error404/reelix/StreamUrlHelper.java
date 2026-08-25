package com.error404.reelix;

public class StreamUrlHelper {

    private static final String BASE_EMBED_URL = "https://vidlink.pro/movie/";

    /**
     * Constructs the final movie streaming URL using the provided TMDB content ID.
     * @param contentId The raw ID passed from the previous activity intent.
     * @return The complete sanitized streaming URL string.
     */
    public static String generateMovieStreamUrl(String contentId) {
        if (contentId == null || contentId.trim().isEmpty()) {
            return "";
        }

        // Clean up any potential whitespace or trailing characters
        String cleanId = contentId.trim();

        return BASE_EMBED_URL + cleanId;
    }
}