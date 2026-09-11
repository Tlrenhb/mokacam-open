package com.aee.mokacam.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast helper. Replacement of the original com.aee.zone.utils.w.
 */
public final class ToastUtils {

    private ToastUtils() {
    }

    public static void show(Context context, String msg) {
        if (context != null && msg != null) {
            Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void show(Context context, int resId) {
        if (context != null) {
            Toast.makeText(context.getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
        }
    }
}
