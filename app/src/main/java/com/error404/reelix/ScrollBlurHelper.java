package com.error404.reelix;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

/**
 * Drives a top_bar BlurView's blur radius and a fade-edge overlay's tint/opacity
 * based on scroll progress from a fragment's NestedScrollView.
 *
 * Usage:
 * 1. In your Activity's onCreate, after setContentView, create one instance and call
 *    attachBlurView(activity, top_bar) once.
 * 2. In your Fragment, after the NestedScrollView is inflated, call
 *    attachScroll(nestedScrollView1) on the SAME instance (pass it in, e.g. via
 *    activity reference, a shared ViewModel, or a constructor/setter on the fragment).
 * 3. As the user scrolls, blur radius and fade-edge opacity update smoothly together.
 *
 * Behavior:
 * - At scroll position 0 (top): blur radius = MAX_BLUR_RADIUS (25), fade overlay near-transparent.
 * - At scroll position >= HALF_SCROLL_DISTANCE_PX: blur radius = 0 (fully opaque solid look),
 *   fade overlay fully opaque at SOLID_COLOR (#101114).
 * - Values in between are linearly interpolated for a smooth transition.
 */
public class ScrollBlurHelper {

    // Tweak these to taste
    private static final float MAX_BLUR_RADIUS = 25f;
    private static final int SOLID_COLOR = 0xFF101114; // fully opaque target color
    private static final int TRANSPARENT_COLOR = 0x00101114; // same hue, alpha 0

    // Distance (in px) of scroll needed to go from full blur to full solid.
    // Set via attachScroll() based on the scroll content, or overridden manually.
    private int halfScrollDistancePx = 400;

    private BlurView blurView;
    private View fadeEdge;
    private Activity activity;

    /**
     * Sets up the BlurView once. Call from Activity onCreate after setContentView.
     */
    public void attachBlurView(final Activity activity, final BlurView topBar) {
        this.activity = activity;
        this.blurView = topBar;

        View decorView = activity.getWindow().getDecorView();
        final ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        final Drawable windowBackground = decorView.getBackground();

        topBar.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(activity))
                .setBlurRadius(MAX_BLUR_RADIUS)
                .setBlurAutoUpdate(true);

        // Build the fade-edge overlay view and attach it below top_bar
        fadeEdge = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (int) (16 * activity.getResources().getDisplayMetrics().density)
        );
        fadeEdge.setLayoutParams(params);

        GradientDrawable fade = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{ TRANSPARENT_COLOR, TRANSPARENT_COLOR }
        );
        fadeEdge.setBackground(fade);

        ViewGroup parent = (ViewGroup) topBar.getParent();
        parent.addView(fadeEdge);
    }

    /**
     * Optionally override the scroll distance (in px) needed to reach full opacity.
     * Call this after attachBlurView if the default 400px doesn't match your content.
     */
    public void setHalfScrollDistancePx(int px) {
        this.halfScrollDistancePx = px;
    }

    /**
     * Call this from the fragment's NestedScrollView.OnScrollChangeListener,
     * passing the current vertical scroll position (scrollY).
     */
    public void onScrollChanged(int scrollY) {
        if (blurView == null || activity == null) return;

        // Clamp progress between 0 (top) and 1 (halfScrollDistancePx or beyond)
        float progress = scrollY / (float) halfScrollDistancePx;
        if (progress < 0f) progress = 0f;
        if (progress > 1f) progress = 1f;

        updateBlur(progress);
    }

    /**
     * progress: 0f = top of scroll (full blur, transparent fade),
     *           1f = halfway point reached (no blur, fully opaque solid color)
     */
    private void updateBlur(float progress) {
        // Blur radius: MAX at top, 0 at full progress
        float radius = MAX_BLUR_RADIUS * (1f - progress);
        if (radius < 0.1f) {
            // RenderScript blur algorithm can behave oddly at radius 0 on some devices;
            // treat near-zero as a tiny minimum instead of exactly 0.
            radius = 0.1f;
        }
        blurView.setBlurRadius(radius);

        // Fade overlay: interpolate alpha from 0 to fully opaque SOLID_COLOR
        int interpolatedColor = interpolateColor(TRANSPARENT_COLOR, SOLID_COLOR, progress);
        GradientDrawable fade = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{ interpolatedColor, interpolatedColor }
        );
        fadeEdge.setBackground(fade);

        // Also tint the blur view's own overlay so the bar body (not just the fade strip)
        // smoothly darkens toward SOLID_COLOR as the user scrolls.
        int barOverlay = interpolateColor(0x000B0D0F, SOLID_COLOR, progress);
        blurView.setOverlayColor(barOverlay);
    }

    private int interpolateColor(int colorStart, int colorEnd, float fraction) {
        int startA = Color.alpha(colorStart);
        int startR = Color.red(colorStart);
        int startG = Color.green(colorStart);
        int startB = Color.blue(colorStart);

        int endA = Color.alpha(colorEnd);
        int endR = Color.red(colorEnd);
        int endG = Color.green(colorEnd);
        int endB = Color.blue(colorEnd);

        int outA = (int) (startA + fraction * (endA - startA));
        int outR = (int) (startR + fraction * (endR - startR));
        int outG = (int) (startG + fraction * (endG - startG));
        int outB = (int) (startB + fraction * (endB - startB));

        return Color.argb(outA, outR, outG, outB);
    }
}
