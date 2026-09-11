package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;

/* JADX INFO: loaded from: classes.dex */
final class c implements Runnable {
    private final Bitmap a;
    private final String b;
    private final com.nostra13.universalimageloader.core.c.a c;
    private final String d;
    private final com.nostra13.universalimageloader.core.b.a e;
    private final com.nostra13.universalimageloader.core.d.a f;
    private final ImageLoaderEngine g;
    private final LoadedFrom h;

    public c(Bitmap bitmap, p pVar, ImageLoaderEngine imageLoaderEngine, LoadedFrom loadedFrom) {
        this.a = bitmap;
        this.b = pVar.a;
        this.c = pVar.c;
        this.d = pVar.b;
        this.e = pVar.e.q();
        this.f = pVar.f;
        this.g = imageLoaderEngine;
        this.h = loadedFrom;
    }

    private boolean a() {
        return !this.d.equals(this.g.a(this.c));
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.c.e()) {
            com.nostra13.universalimageloader.b.e.a("ImageAware was collected by GC. Task is cancelled. [%s]", this.d);
            this.f.a(this.b, this.c.d());
        } else if (a()) {
            com.nostra13.universalimageloader.b.e.a("ImageAware is reused for another image. Task is cancelled. [%s]", this.d);
            this.f.a(this.b, this.c.d());
        } else {
            com.nostra13.universalimageloader.b.e.a("Display image in ImageAware (loaded from %1$s) [%2$s]", this.h, this.d);
            this.e.a(this.a, this.c, this.h);
            this.g.b(this.c);
            this.f.a(this.b, this.c.d(), this.a);
        }
    }
}
