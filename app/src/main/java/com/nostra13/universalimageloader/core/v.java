package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;

/* JADX INFO: loaded from: classes.dex */
final class v implements Runnable {
    private final ImageLoaderEngine a;
    private final Bitmap b;
    private final p c;
    private final Handler d;

    public v(ImageLoaderEngine imageLoaderEngine, Bitmap bitmap, p pVar, Handler handler) {
        this.a = imageLoaderEngine;
        this.b = bitmap;
        this.c = pVar;
        this.d = handler;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.nostra13.universalimageloader.b.e.a("PostProcess image before displaying [%s]", this.c.b);
        q.a(new c(this.c.e.p().a(this.b), this.c, this.a, LoadedFrom.MEMORY_CACHE), this.c.e.s(), this.d, this.a);
    }
}
