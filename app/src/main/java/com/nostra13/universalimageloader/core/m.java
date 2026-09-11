package com.nostra13.universalimageloader.core;

import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
class m implements ImageDownloader {
    private final ImageDownloader a;

    public m(ImageDownloader imageDownloader) {
        this.a = imageDownloader;
    }

    @Override // com.nostra13.universalimageloader.core.download.ImageDownloader
    public InputStream a(String str, Object obj) {
        switch (ImageDownloader.Scheme.ofUri(str)) {
            case HTTP:
            case HTTPS:
                throw new IllegalStateException();
            default:
                return this.a.a(str, obj);
        }
    }
}
