package com.aee.mokacam.photoview;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(5)
public class d extends b {
    int f;
    int g;

    public d(Context context) {
        super(context);
        this.f = -1;
        this.g = 0;
    }

    @Override // com.aee.mokacam.photoview.b
    float a(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.g);
        } catch (Exception e) {
            return motionEvent.getX();
        }
    }

    @Override // com.aee.mokacam.photoview.b
    float b(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.g);
        } catch (Exception e) {
            return motionEvent.getY();
        }
    }

    @Override // com.aee.mokacam.photoview.b, com.aee.mokacam.photoview.g
    public boolean c(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.f = motionEvent.getPointerId(0);
                break;
            case 1:
            case 3:
                this.f = -1;
                break;
            case 6:
                int iA = com.aee.mokacam.photoview.a.a(motionEvent.getAction());
                if (motionEvent.getPointerId(iA) == this.f) {
                    int i = iA == 0 ? 1 : 0;
                    this.f = motionEvent.getPointerId(i);
                    this.b = motionEvent.getX(i);
                    this.c = motionEvent.getY(i);
                }
                break;
        }
        this.g = motionEvent.findPointerIndex(this.f != -1 ? this.f : 0);
        try {
            return super.c(motionEvent);
        } catch (IllegalArgumentException e) {
            return true;
        }
    }
}
