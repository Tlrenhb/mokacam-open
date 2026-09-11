package com.facebook.rebound;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
abstract class a {
    public static n a() {
        return Build.VERSION.SDK_INT >= 16 ? b.a() : d.a();
    }
}
