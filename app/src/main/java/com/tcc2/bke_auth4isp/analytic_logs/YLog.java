package com.tcc2.bke_auth4isp.analytic_logs;

import android.util.Log;

public class YLog {

    private static final String TAG = "Y_LOG_MANAGER";

    // Debug
    public static void d(String codeClass, String method, String message) {
        Log.d(TAG, codeClass + "." + method + "(): " + message);
    }

    public static void d(String codeClass, String method, String message, String exception) {
        Log.d(TAG, codeClass + "." + method + "(): " + message);
    }

    public static void e(String message) {
        Log.d(TAG, message);
    }
}
