package com.aee.mokacam.photoview;

import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class c implements GestureDetector.OnDoubleTapListener {
    r a;

    public c(r rVar) {
        a(rVar);
    }

    public void a(r rVar) {
        this.a = rVar;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (this.a == null) {
            return false;
        }
        try {
            float fG = this.a.g();
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (fG < this.a.e()) {
                this.a.a(this.a.e(), x, y, true);
            } else if (fG < this.a.e() || fG >= this.a.f()) {
                this.a.a(this.a.d(), x, y, true);
            } else {
                this.a.a(this.a.f(), x, y, true);
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        RectF rectFB;
        if (this.a == null) {
            return false;
        }
        ImageView imageViewC = this.a.c();
        if (this.a.i() != null && (rectFB = this.a.b()) != null) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (rectFB.contains(x, y)) {
                this.a.i().a(imageViewC, (x - rectFB.left) / rectFB.width(), (y - rectFB.top) / rectFB.height());
                return true;
            }
            this.a.i().a();
        }
        if (this.a.j() == null) {
            return false;
        }
        this.a.j().a(imageViewC, motionEvent.getX(), motionEvent.getY());
        return false;
    }
}
