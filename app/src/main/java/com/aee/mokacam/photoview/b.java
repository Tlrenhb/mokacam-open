package com.aee.mokacam.photoview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class b implements g {
    protected q a;
    float b;
    float c;
    final float d;
    final float e;
    private VelocityTracker f;
    private boolean g;

    public b(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.e = viewConfiguration.getScaledMinimumFlingVelocity();
        this.d = viewConfiguration.getScaledTouchSlop();
    }

    float a(MotionEvent motionEvent) {
        return motionEvent.getX();
    }

    @Override // com.aee.mokacam.photoview.g
    public void a(q qVar) {
        this.a = qVar;
    }

    @Override // com.aee.mokacam.photoview.g
    public boolean a() {
        return false;
    }

    float b(MotionEvent motionEvent) {
        return motionEvent.getY();
    }

    @Override // com.aee.mokacam.photoview.g
    public boolean b() {
        return this.g;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.aee.mokacam.photoview.g
    public boolean c(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.f = VelocityTracker.obtain();
                if (this.f != null) {
                    this.f.addMovement(motionEvent);
                }
                this.b = a(motionEvent);
                this.c = b(motionEvent);
                this.g = false;
                return true;
            case 1:
                if (this.g && this.f != null) {
                    this.b = a(motionEvent);
                    this.c = b(motionEvent);
                    this.f.addMovement(motionEvent);
                    this.f.computeCurrentVelocity(1000);
                    float xVelocity = this.f.getXVelocity();
                    float yVelocity = this.f.getYVelocity();
                    if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.e) {
                        this.a.a(this.b, this.c, -xVelocity, -yVelocity);
                    }
                }
                if (this.f != null) {
                    this.f.recycle();
                    this.f = null;
                }
                return true;
            case 2:
                float fA = a(motionEvent);
                float fB = b(motionEvent);
                float f = fA - this.b;
                float f2 = fB - this.c;
                if (!this.g) {
                    this.g = Math.sqrt((double) ((f * f) + (f2 * f2))) >= ((double) this.d);
                }
                if (this.g) {
                    this.a.a(f, f2);
                    this.b = fA;
                    this.c = fB;
                    if (this.f != null) {
                        this.f.addMovement(motionEvent);
                    }
                }
                return true;
            case 3:
                if (this.f != null) {
                    this.f.recycle();
                    this.f = null;
                }
                return true;
            default:
                return true;
        }
    }
}
