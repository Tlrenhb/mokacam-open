package com.aee.mokacam.photoview;

import androidx.core.view.MotionEventCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes.dex */
class s extends GestureDetector.SimpleOnGestureListener {
    final /* synthetic */ r a;

    s(r rVar) {
        this.a = rVar;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (this.a.w == null || this.a.g() > 1.0f || MotionEventCompat.getPointerCount(motionEvent) > r.b || MotionEventCompat.getPointerCount(motionEvent2) > r.b) {
            return false;
        }
        return this.a.w.a(motionEvent, motionEvent2, f, f2);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        if (this.a.u != null) {
            this.a.u.onLongClick(this.a.c());
        }
    }
}
