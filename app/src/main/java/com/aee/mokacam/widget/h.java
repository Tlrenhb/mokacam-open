package com.aee.mokacam.widget;

import android.view.GestureDetector;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes.dex */
class h extends GestureDetector.SimpleOnGestureListener {
    final /* synthetic */ PinchImageView a;

    h(PinchImageView pinchImageView) {
        this.a = pinchImageView;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (this.a.e == 1 && (this.a.l == null || !this.a.l.isRunning())) {
            this.a.c(motionEvent.getX(), motionEvent.getY());
        }
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (this.a.e != 0) {
            return true;
        }
        if (this.a.l != null && this.a.l.isRunning()) {
            return true;
        }
        this.a.d(f, f2);
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        if (this.a.b != null) {
            this.a.b.onLongClick(this.a);
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        if (this.a.a == null) {
            return true;
        }
        this.a.a.onClick(this.a);
        return true;
    }
}
