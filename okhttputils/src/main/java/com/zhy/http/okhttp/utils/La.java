package com.zhy.http.okhttp.utils;

import android.util.Log;

/**
 * Created by zhy on 15/11/6.
 */
public class La {
    private static boolean debug = true;

    public static void e(String msg) {
        if (debug) {
            Log.e("OkHttp", msg);
        }
    }

}

