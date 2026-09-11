package com.nostra13.universalimageloader.core;

import android.content.Context;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class l {
    public static final QueueProcessingType a = QueueProcessingType.FIFO;
    private Context b;
    private com.nostra13.universalimageloader.core.a.d w;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private com.nostra13.universalimageloader.core.e.a g = null;
    private Executor h = null;
    private Executor i = null;
    private boolean j = false;
    private boolean k = false;
    private int l = 3;
    private int m = 3;
    private boolean n = false;
    private QueueProcessingType o = a;
    private int p = 0;
    private long q = 0;
    private int r = 0;
    private com.nostra13.universalimageloader.a.b.a s = null;
    private com.nostra13.universalimageloader.a.a.a t = null;
    private com.nostra13.universalimageloader.a.a.b.a u = null;
    private ImageDownloader v = null;
    private d x = null;
    private boolean y = false;

    public l(Context context) {
        this.b = context.getApplicationContext();
    }

    private void b() {
        if (this.h == null) {
            this.h = a.a(this.l, this.m, this.o);
        } else {
            this.j = true;
        }
        if (this.i == null) {
            this.i = a.a(this.l, this.m, this.o);
        } else {
            this.k = true;
        }
        if (this.t == null) {
            if (this.u == null) {
                this.u = a.b();
            }
            this.t = a.a(this.b, this.u, this.q, this.r);
        }
        if (this.s == null) {
            this.s = a.a(this.b, this.p);
        }
        if (this.n) {
            this.s = new com.nostra13.universalimageloader.a.b.a.a(this.s, com.nostra13.universalimageloader.b.f.a());
        }
        if (this.v == null) {
            this.v = a.a(this.b);
        }
        if (this.w == null) {
            this.w = a.a(this.y);
        }
        if (this.x == null) {
            this.x = d.t();
        }
    }

    public j a() {
        b();
        return new j(this);
    }
}
