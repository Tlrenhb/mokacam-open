package com.nostra13.universalimageloader.core;

import com.nostra13.universalimageloader.core.download.ImageDownloader;

/* JADX INFO: loaded from: classes.dex */
/* synthetic */ class k {
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
    }
}
