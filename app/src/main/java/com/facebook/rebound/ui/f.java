package com.facebook.rebound.ui;

import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {
    public static final int a(float f, Resources resources) {
        return (int) TypedValue.applyDimension(1, f, resources.getDisplayMetrics());
    }

    public static final FrameLayout.LayoutParams a() {
        return a(-1, -1);
    }

    public static final FrameLayout.LayoutParams a(int i, int i2) {
        return new FrameLayout.LayoutParams(i, i2);
    }

    public static final FrameLayout.LayoutParams b() {
        return a(-1, -2);
    }
}
