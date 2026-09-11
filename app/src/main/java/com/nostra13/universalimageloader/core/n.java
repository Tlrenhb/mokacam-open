package com.nostra13.universalimageloader.core;

import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
class n implements ImageDownloader {
    private final ImageDownloader a;

    public n(ImageDownloader imageDownloader) {
        this.a = imageDownloader;
    }

    @Override // com.nostra13.universalimageloader.core.download.ImageDownloader
    public InputStream a(String str, Object obj) {
        InputStream inputStreamA = this.a.a(str, obj);
        switch (ImageDownloader.Scheme.ofUri(str)) {
            case HTTP:
            case HTTPS:
                return new com.nostra13.universalimageloader.core.assist.b(inputStreamA);
            default:
                return inputStreamA;
        }
    }
}
