package com.aee.mokacam.photoview;

import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
class t implements Runnable {
    final /* synthetic */ r a;
    private final float b;
    private final float c;
    private final long d = System.currentTimeMillis();
    private final float e;
    private final float f;

    public t(r rVar, float f, float f2, float f3, float f4) {
        this.a = rVar;
        this.b = f3;
        this.c = f4;
        this.e = f;
        this.f = f2;
    }

    private float a() {
        return this.a.d.getInterpolation(Math.min(1.0f, ((System.currentTimeMillis() - this.d) * 1.0f) / this.a.a));
    }

    @Override // java.lang.Runnable
    public void run() {
        ImageView imageViewC = this.a.c();
        if (imageViewC == null) {
            return;
        }
        float fA = a();
        this.a.a((this.e + ((this.f - this.e) * fA)) / this.a.g(), this.b, this.c);
        if (fA < 1.0f) {
            a.a(imageViewC, this);
        }
    }
}
