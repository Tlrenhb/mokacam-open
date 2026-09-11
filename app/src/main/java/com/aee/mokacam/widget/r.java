package com.aee.mokacam.widget;

/* JADX INFO: loaded from: classes.dex */
class r implements Runnable {
    final /* synthetic */ ZoomImageView a;
    float b;
    float c;
    float d;
    float e;
    float f = 1.07f;
    float g = 0.93f;

    public r(ZoomImageView zoomImageView, float f, float f2, float f3) {
        this.a = zoomImageView;
        this.b = f;
        this.c = f2;
        this.d = f3;
        if (zoomImageView.getDrawableScale() < f) {
            this.e = this.f;
        }
        if (zoomImageView.getDrawableScale() > f) {
            this.e = this.g;
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.e.postScale(this.e, this.e, this.c, this.d);
        this.a.a();
        this.a.setImageMatrix(this.a.e);
        float drawableScale = this.a.getDrawableScale();
        if ((drawableScale < this.b && this.e > 1.0f) || (drawableScale > this.b && this.e < 1.0f)) {
            this.a.postDelayed(this, 16L);
            return;
        }
        this.a.e.postScale(this.b / drawableScale, this.b / drawableScale, this.c, this.d);
        this.a.a();
        this.a.setImageMatrix(this.a.e);
        this.a.m = false;
    }
}
