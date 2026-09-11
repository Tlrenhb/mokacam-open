package com.nostra13.universalimageloader.b;

import com.nostra13.universalimageloader.core.assist.ViewScaleType;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class b {
    static final /* synthetic */ int[] a = new int[ViewScaleType.values().length];

    static {
        try {
            a[ViewScaleType.FIT_INSIDE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[ViewScaleType.CROP.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
