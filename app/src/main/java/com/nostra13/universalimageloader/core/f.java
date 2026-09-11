package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/* JADX INFO: loaded from: classes.dex */
public class f {
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private Drawable d = null;
    private Drawable e = null;
    private Drawable f = null;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private ImageScaleType j = ImageScaleType.IN_SAMPLE_POWER_OF_2;
    private BitmapFactory.Options k = new BitmapFactory.Options();
    private int l = 0;
    private boolean m = false;
    private Object n = null;
    private com.nostra13.universalimageloader.core.e.a o = null;
    private com.nostra13.universalimageloader.core.e.a p = null;
    private com.nostra13.universalimageloader.core.b.a q = a.c();
    private Handler r = null;
    private boolean s = false;

    public d a() {
        return new d(this);
    }

    public f a(int i) {
        this.a = i;
        return this;
    }

    public f a(Bitmap.Config config) {
        if (config == null) {
            throw new IllegalArgumentException("bitmapConfig can't be null");
        }
        this.k.inPreferredConfig = config;
        return this;
    }

    public f a(ImageScaleType imageScaleType) {
        this.j = imageScaleType;
        return this;
    }

    public f a(d dVar) {
        this.a = dVar.a;
        this.b = dVar.b;
        this.c = dVar.c;
        this.d = dVar.d;
        this.e = dVar.e;
        this.f = dVar.f;
        this.g = dVar.g;
        this.h = dVar.h;
        this.i = dVar.i;
        this.j = dVar.j;
        this.k = dVar.k;
        this.l = dVar.l;
        this.m = dVar.m;
        this.n = dVar.n;
        this.o = dVar.o;
        this.p = dVar.p;
        this.q = dVar.q;
        this.r = dVar.r;
        this.s = dVar.s;
        return this;
    }

    public f a(boolean z) {
        this.h = z;
        return this;
    }

    public f b(boolean z) {
        this.i = z;
        return this;
    }

    f c(boolean z) {
        this.s = z;
        return this;
    }
}
