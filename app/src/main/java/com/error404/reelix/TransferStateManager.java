package com.error404.reelix;

import java.util.ArrayList;
import java.util.HashMap;

public class TransferStateManager {
    
    // 1. Centralized Data Lists (Accessible from anywhere)
    public static ArrayList<HashMap<String, Object>> send_trans_list = new ArrayList<>();
    public static ArrayList<HashMap<String, Object>> receive_trans_map = new ArrayList<>();

    // 2. Interfaces to notify Fragments when data changes
    public interface UIUpdateListener {
        void onDataUpdated();
    }

    private static UIUpdateListener sendListener;
    private static UIUpdateListener receiveListener;

    public static void setSendListener(UIUpdateListener listener) { 
        sendListener = listener; 
    }
    
    public static void setReceiveListener(UIUpdateListener listener) { 
        receiveListener = listener; 
    }

    // 3. Trigger Methods (Called by TransferProgressActivity when transferring bytes)
    public static void notifySendUpdated() {
        if (sendListener != null) {
            sendListener.onDataUpdated();
        }
    }

    public static void notifyReceiveUpdated() {
        if (receiveListener != null) {
            receiveListener.onDataUpdated();
        }
    }

    // 4. Cleanup method to prevent memory leaks when leaving the transfer session
    public static void clearAll() {
        send_trans_list.clear();
        receive_trans_map.clear();
        sendListener = null;
        receiveListener = null;
    }
}
