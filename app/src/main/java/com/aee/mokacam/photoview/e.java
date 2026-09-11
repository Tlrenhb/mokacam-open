package com.aee.mokacam.photoview;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(8)
public class e extends d {
    protected final ScaleGestureDetector f;

    public e(Context context) {
        super(context);
        this.f = new ScaleGestureDetector(context, new f(this));
    }

    @Override // com.aee.mokacam.photoview.b, com.aee.mokacam.photoview.g
    public boolean a() {
        return this.f.isInProgress();
    }

    @Override // com.aee.mokacam.photoview.d, com.aee.mokacam.photoview.b, com.aee.mokacam.photoview.g
    public boolean c(MotionEvent motionEvent) {
        try {
            this.f.onTouchEvent(motionEvent);
            return super.c(motionEvent);
        } catch (IllegalArgumentException e) {
            return true;
        }
    }
}
