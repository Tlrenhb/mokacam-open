package com.nostra13.universalimageloader.core.assist;

import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public enum ViewScaleType {
    FIT_INSIDE,
    CROP;

    public static ViewScaleType fromImageView(ImageView imageView) {
        switch (d.a[imageView.getScaleType().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return FIT_INSIDE;
            default:
                return CROP;
        }
    }
}
