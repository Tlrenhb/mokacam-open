package com.nostra13.universalimageloader.core.assist;

import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class d {
    static final /* synthetic */ int[] a = new int[ImageView.ScaleType.values().length];

    static {
        try {
            a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[ImageView.ScaleType.FIT_XY.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[ImageView.ScaleType.FIT_START.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[ImageView.ScaleType.FIT_END.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[ImageView.ScaleType.MATRIX.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[ImageView.ScaleType.CENTER.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
        try {
            a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 8;
        } catch (NoSuchFieldError e8) {
        }
    }
}
