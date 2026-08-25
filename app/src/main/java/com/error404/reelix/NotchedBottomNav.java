package com.error404.reelix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Custom bottom navigation container that draws a rounded background
 * with an inward-then-outward notch cutout in the middle (MovieBox style),
 * meant to frame a circular badge/button placed on top of the notch.
 *
 * Usage in Sketchware Pro:
 * 1. Add this as a Java Class (not an XML component) via "Add Java Class".
 * 2. In the Designer, add a "Custom View" component and set its class name to:
 *    com.my.newproject23.NotchedBottomNav
 * 3. Place your existing icon views as children inside it, same as you did with linbottom.
 * 4. Place your circular "Fight Zone"-style badge view as a sibling ABOVE this in the
 *    layout hierarchy (or in a parent FrameLayout) with a negative top margin so it
 *    overlaps the notch.
 */
public class NotchedBottomNav extends FrameLayout {

    private Paint backgroundPaint;
    private Path backgroundPath;

    // Colors - tweak these to taste
    private int colorTop = 0xFF2A1618;
    private int colorBottom = 0xFF1F2024;

    // Notch geometry - tweak these to taste
    private float notchRadius;      // radius of the circular cutout
    private float notchDepth;       // how far down the notch dips
    private float cornerRadius;     // bottom corner radius of the bar
    private float topCornerRadius;  // top-left / top-right corner radius of the bar
    private float curveSpanMultiplier; // how wide the approach curve is, relative to notchRadius

    public NotchedBottomNav(Context context) {
        super(context);
        init();
    }

    public NotchedBottomNav(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NotchedBottomNav(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Needed so onDraw actually gets called on a ViewGroup
        setWillNotDraw(false);

        float density = getResources().getDisplayMetrics().density;
        notchRadius = 38 * density;
        notchDepth = 22 * density;
        cornerRadius = 20 * density;
        topCornerRadius = 0; // default: square top corners, matching reference image
        curveSpanMultiplier = 1.9f;

        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setStyle(Paint.Style.FILL);

        backgroundPath = new Path();

        // Elevation so the whole bar still lifts off content behind it
        setElevation(12 * density);
        setTranslationZ(4 * density);
    }

    /** Optional setters so you can tune colors/geometry from MainActivity if needed */
    public void setBarColors(int top, int bottom) {
        colorTop = top;
        colorBottom = bottom;
        if (getWidth() > 0 && getHeight() > 0) {
            LinearGradient gradient = new LinearGradient(
                    0, 0, 0, getHeight(),
                    colorTop, colorBottom,
                    Shader.TileMode.CLAMP
            );
            backgroundPaint.setShader(gradient);
        }
        invalidate();
    }

    /** Radius of the circular notch cutout, in pixels */
    public void setNotchRadius(float radiusPx) {
        notchRadius = radiusPx;
        rebuild();
    }

    /** How far down the notch dips, in pixels */
    public void setNotchDepth(float depthPx) {
        notchDepth = depthPx;
        rebuild();
    }

    /** How wide the approach curve spans on either side of the notch, relative to notchRadius (e.g. 1.9f) */
    public void setCurveSpanMultiplier(float multiplier) {
        curveSpanMultiplier = multiplier;
        rebuild();
    }

    /** Convenience: set radius + depth together */
    public void setNotchGeometry(float radiusPx, float depthPx) {
        notchRadius = radiusPx;
        notchDepth = depthPx;
        rebuild();
    }

    /** Bottom-left / bottom-right corner radius, in pixels */
    public void setBottomCornerRadius(float radiusPx) {
        cornerRadius = radiusPx;
        rebuild();
    }

    /** Top-left / top-right corner radius, in pixels */
    public void setTopCornerRadius(float radiusPx) {
        topCornerRadius = radiusPx;
        rebuild();
    }

    private void rebuild() {
        if (getWidth() > 0 && getHeight() > 0) {
            buildPath(getWidth(), getHeight());
            invalidateOutline();
        }
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        buildPath(w, h);

        // Gradient needs actual view bounds, build it here
        LinearGradient gradient = new LinearGradient(
                0, 0, 0, h,
                colorTop, colorBottom,
                Shader.TileMode.CLAMP
        );
        backgroundPaint.setShader(gradient);

        // Make the elevation shadow follow the notched path instead of the
        // default rectangular bounds. Note: the notch makes this path concave,
        // and setConvexPath silently produces no shadow for concave paths on
        // some API levels — this is a best-effort visual improvement, not
        // guaranteed on every device/OS version.
        setOutlineProvider(new android.view.ViewOutlineProvider() {
            @Override
            public void getOutline(android.view.View view, android.graphics.Outline outline) {
                try {
                    outline.setConvexPath(backgroundPath);
                } catch (IllegalArgumentException e) {
                    // Path not convex on this API level - fall back to rectangular shadow
                    outline.setRect(0, 0, view.getWidth(), view.getHeight());
                }
            }
        });
    }

    private void buildPath(int w, int h) {
        backgroundPath.reset();

        float centerX = w / 2f;
        // How wide the "approach" curve is on either side of the notch center
        float curveSpan = notchRadius * curveSpanMultiplier;

        float left = 0;
        float right = w;
        float top = 0;
        float bottom = h;

        // start partway down the left edge, below the top-left corner curve
        backgroundPath.moveTo(left, top + topCornerRadius);

        // top-left corner (radius 0 = sharp/square, matches reference image by default)
        if (topCornerRadius > 0) {
            backgroundPath.quadTo(left, top, left + topCornerRadius, top);
        } else {
            backgroundPath.lineTo(left, top);
        }

        // straight line to where the inward curve starts
        backgroundPath.lineTo(centerX - curveSpan, top);

        // inward curve down into the notch (left side)
        backgroundPath.cubicTo(
                centerX - curveSpan * 0.45f, top,
                centerX - notchRadius, top + notchDepth,
                centerX, top + notchDepth
        );

        // outward curve back up out of the notch (right side)
        backgroundPath.cubicTo(
                centerX + notchRadius, top + notchDepth,
                centerX + curveSpan * 0.45f, top,
                centerX + curveSpan, top
        );

        // continue straight to top-right corner
        if (topCornerRadius > 0) {
            backgroundPath.lineTo(right - topCornerRadius, top);
            backgroundPath.quadTo(right, top, right, top + topCornerRadius);
        } else {
            backgroundPath.lineTo(right, top);
        }

        // right edge down
        backgroundPath.lineTo(right, bottom - cornerRadius);
        backgroundPath.quadTo(right, bottom, right - cornerRadius, bottom);

        // bottom edge
        backgroundPath.lineTo(left + cornerRadius, bottom);
        backgroundPath.quadTo(left, bottom, left, bottom - cornerRadius);

        // left edge back up to the starting point (just below the top-left corner curve)
        backgroundPath.lineTo(left, top + topCornerRadius);

        backgroundPath.close();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        // Draw our custom background first, then let children (icons) draw on top
        canvas.drawPath(backgroundPath, backgroundPaint);
        super.dispatchDraw(canvas);
    }
}
