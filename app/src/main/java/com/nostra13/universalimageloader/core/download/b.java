package com.nostra13.universalimageloader.core.download;

import com.nostra13.universalimageloader.core.download.ImageDownloader;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class b {
    static final /* synthetic */ int[] a = new int[ImageDownloader.Scheme.values().length];

    static {
        try {
            a[ImageDownloader.Scheme.HTTP.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[ImageDownloader.Scheme.HTTPS.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[ImageDownloader.Scheme.FILE.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[ImageDownloader.Scheme.CONTENT.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[ImageDownloader.Scheme.ASSETS.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[ImageDownloader.Scheme.DRAWABLE.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[ImageDownloader.Scheme.UNKNOWN.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
    }
}
