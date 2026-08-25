package com.error404.reelix;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class SubtitleSelectionDialog {

    public interface SubtitleDialogListener {
        void onSubtitleUrlSelected(String subtitleUrl);
        void onSkipSelected();
    }

    public static void showDialog(Context context, String jsonStreamsResponse, final SubtitleDialogListener listener) {
        try {
            final Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            
            Window window = dialog.getWindow();
            if (window != null) {
                window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#121212")));
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            }

            LinearLayout root = new LinearLayout(context);
            root.setOrientation(LinearLayout.VERTICAL);
            root.setBackgroundColor(Color.parseColor("#121212"));
            root.setPadding(48, 64, 48, 48);
            root.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            TextView headerTitle = new TextView(context);
            headerTitle.setText("Select Subtitle Language");
            headerTitle.setTextColor(Color.WHITE);
            headerTitle.setTextSize(22);
            headerTitle.setTypeface(Typeface.DEFAULT_BOLD);
            headerTitle.setPadding(0, 0, 0, 40);
            root.addView(headerTitle);

            ScrollView scrollView = new ScrollView(context);
            LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
            scrollView.setLayoutParams(scrollParams);
            scrollView.setVerticalScrollBarEnabled(false);

            LinearLayout listContainer = new LinearLayout(context);
            listContainer.setOrientation(LinearLayout.VERTICAL);
            listContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            JSONObject jsonObject = new JSONObject(jsonStreamsResponse);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray subtitlesArray = data.getJSONArray("subtitles");

            for (int i = 0; i < subtitlesArray.length(); i++) {
                JSONObject subObj = subtitlesArray.getJSONObject(i);
                final String label = subObj.optString("label", "Unknown Language");
                final String url = subObj.optString("url", "");

                TextView rowItem = new TextView(context);
                rowItem.setText(label);
                rowItem.setTextColor(Color.parseColor("#E0E0E0"));
                rowItem.setTextSize(16);
                rowItem.setPadding(32, 40, 32, 40);
                rowItem.setGravity(Gravity.CENTER_VERTICAL);
                
                LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                rowParams.setMargins(0, 0, 0, 16);
                rowItem.setLayoutParams(rowParams);

                android.graphics.drawable.GradientDrawable itemBg = new android.graphics.drawable.GradientDrawable();
                itemBg.setColor(Color.parseColor("#1A1A1A"));
                itemBg.setCornerRadius(20);
                rowItem.setBackground(itemBg);

                rowItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        if (listener != null) {
                            listener.onSubtitleUrlSelected(url);
                        }
                    }
                });

                listContainer.addView(rowItem);
            }

            scrollView.addView(listContainer);
            root.addView(scrollView);

            Button skipBtn = new Button(context);
            LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            btnParams.setMargins(0, 32, 0, 0);
            skipBtn.setLayoutParams(btnParams);
            skipBtn.setText("Continue Without Subtitles");
            skipBtn.setTextColor(Color.WHITE);
            skipBtn.setTextSize(14);
            skipBtn.setAllCaps(false);

            android.graphics.drawable.GradientDrawable btnBg = new android.graphics.drawable.GradientDrawable();
            btnBg.setColor(Color.parseColor("#B71C1C"));
            btnBg.setCornerRadius(30);
            skipBtn.setBackground(btnBg);

            skipBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (listener != null) {
                        listener.onSkipSelected();
                    }
                }
            });

            root.addView(skipBtn);
            dialog.setContentView(root);
            dialog.show();

        } catch (Exception e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onSkipSelected();
            }
        }
    }
}
