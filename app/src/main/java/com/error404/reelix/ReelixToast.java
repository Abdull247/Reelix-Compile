package com.error404.reelix;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ReelixToast {

    private static Toast currentToast;

    public static void show(Context context, String text, int position, boolean cancelable) {
        if (currentToast != null) {
            currentToast.cancel();
        }

        Toast toast = new Toast(context);

        // Build the view programmatically
        TextView tv = new TextView(context);
        tv.setText(text);
        tv.setTextColor(Color.parseColor("#E0E0E0"));
        tv.setTextSize(14f);
        tv.setPadding(48, 28, 48, 28);

        // Glass dark background
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.parseColor("#CC1A1A2E")); // dark with alpha
        bg.setCornerRadius(60f);
        bg.setStroke(2, Color.parseColor("#44FFFFFF")); // subtle white border

        tv.setBackground(bg);

        toast.setView(tv);
        toast.setDuration(cancelable ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);

        // Convert 64dp to px for margin
        float density = context.getResources().getDisplayMetrics().density;
        int marginPx = (int)(64 * density);

        if (position == 0) {
            // Bottom
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, marginPx);
        } else {
            // Top
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, marginPx);
        }

        toast.show();
        currentToast = toast;

        // If not cancelable, keep re-showing until cancel() is called
        if (!cancelable) {
            final Handler handler = new Handler(Looper.getMainLooper());
            final Runnable[] runnable = new Runnable[1];
            runnable[0] = new Runnable() {
                @Override
                public void run() {
                    if (currentToast != null) {
                        currentToast.show();
                        handler.postDelayed(runnable[0], 2000);
                    }
                }
            };
            handler.postDelayed(runnable[0], 2000);
        }
    }

    public static void cancel() {
        if (currentToast != null) {
            currentToast.cancel();
            currentToast = null;
        }
    }
}