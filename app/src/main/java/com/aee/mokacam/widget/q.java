package com.aee.mokacam.widget;

import android.view.GestureDetector;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes.dex */
class q extends GestureDetector.SimpleOnGestureListener {
    final /* synthetic */ ZoomImageView a;

    q(ZoomImageView zoomImageView) {
        this.a = zoomImageView;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (!this.a.m) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (this.a.getDrawableScale() < this.a.c) {
                this.a.postDelayed(new r(this.a, this.a.c, x, y), 16L);
                this.a.m = true;
            } else {
                this.a.postDelayed(new r(this.a, this.a.b, x, y), 16L);
                this.a.m = true;
            }
        }
        return true;
    }
}
