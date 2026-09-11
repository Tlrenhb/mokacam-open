package com.aee.mokacam.photoview;

import android.content.Context;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public final class ac {
    public static g a(Context context, q qVar) {
        int i = Build.VERSION.SDK_INT;
        g bVar = i < 5 ? new b(context) : i < 8 ? new d(context) : new e(context);
        bVar.a(qVar);
        return bVar;
    }
}
