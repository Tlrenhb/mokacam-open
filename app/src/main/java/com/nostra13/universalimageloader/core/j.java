package com.nostra13.universalimageloader.core;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class j {
    final Resources a;
    final int b;
    final int c;
    final int d;
    final int e;
    final com.nostra13.universalimageloader.core.e.a f;
    final Executor g;
    final Executor h;
    final boolean i;
    final boolean j;
    final int k;
    final int l;
    final QueueProcessingType m;
    final com.nostra13.universalimageloader.a.b.a n;
    final com.nostra13.universalimageloader.a.a.a o;
    final ImageDownloader p;
    final com.nostra13.universalimageloader.core.a.d q;
    final d r;
    final ImageDownloader s;
    final ImageDownloader t;

    private j(l lVar) {
        this.a = lVar.b.getResources();
        this.b = lVar.c;
        this.c = lVar.d;
        this.d = lVar.e;
        this.e = lVar.f;
        this.f = lVar.g;
        this.g = lVar.h;
        this.h = lVar.i;
        this.k = lVar.l;
        this.l = lVar.m;
        this.m = lVar.o;
        this.o = lVar.t;
        this.n = lVar.s;
        this.r = lVar.x;
        this.p = lVar.v;
        this.q = lVar.w;
        this.i = lVar.j;
        this.j = lVar.k;
        this.s = new m(this.p);
        this.t = new n(this.p);
        com.nostra13.universalimageloader.b.e.a(lVar.y);
    }

    public static j a(Context context) {
        return new l(context).a();
    }

    com.nostra13.universalimageloader.core.assist.c a() {
        DisplayMetrics displayMetrics = this.a.getDisplayMetrics();
        int i = this.b;
        if (i <= 0) {
            i = displayMetrics.widthPixels;
        }
        int i2 = this.c;
        if (i2 <= 0) {
            i2 = displayMetrics.heightPixels;
        }
        return new com.nostra13.universalimageloader.core.assist.c(i, i2);
    }
}
