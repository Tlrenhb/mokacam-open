package com.aee.mokacam.photoview;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static int a(int i) {
        return Build.VERSION.SDK_INT >= 11 ? c(i) : b(i);
    }

    public static void a(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            b(view, runnable);
        } else {
            view.postDelayed(runnable, 16L);
        }
    }

    @TargetApi(5)
    static int b(int i) {
        return (65280 & i) >> 8;
    }

    @TargetApi(16)
    static void b(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    @TargetApi(11)
    static int c(int i) {
        return (65280 & i) >> 8;
    }
}
