package com.aee.mokacam.photoview;

import android.content.Context;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public abstract class ab {
    public static ab a(Context context) {
        return Build.VERSION.SDK_INT < 9 ? new aa(context) : Build.VERSION.SDK_INT < 14 ? new h(context) : new j(context);
    }

    public abstract void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

    public abstract void a(boolean z);

    public abstract boolean a();

    public abstract boolean b();

    public abstract int c();

    public abstract int d();
}
