package com.aee.mokacam.utils;

import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class w {
    public static Toast a;

    public static void a(int i, boolean z) {
        new Thread(new y(z, i)).start();
    }

    public static void a(String str, boolean z) {
        new Thread(new x(z, str)).start();
    }
}
