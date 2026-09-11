package com.aee.mokacam.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PinchImageView extends ImageView {
    View.OnClickListener a;
    View.OnLongClickListener b;
    Matrix c;
    RectF d;
    int e;
    List<m> f;
    List<m> g;
    int h;
    PointF i;
    PointF j;
    float k;
    o l;
    i m;
    GestureDetector n;

    public PinchImageView(Context context) {
        super(context);
        this.c = new Matrix();
        this.e = 0;
        this.i = new PointF();
        this.j = new PointF();
        this.k = 0.0f;
        this.n = new GestureDetector(getContext(), new h(this));
        b();
    }

    public PinchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new Matrix();
        this.e = 0;
        this.i = new PointF();
        this.j = new PointF();
        this.k = 0.0f;
        this.n = new GestureDetector(getContext(), new h(this));
        b();
    }

    public PinchImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new Matrix();
        this.e = 0;
        this.i = new PointF();
        this.j = new PointF();
        this.k = 0.0f;
        this.n = new GestureDetector(getContext(), new h(this));
        b();
    }

    /* JADX INFO: Access modifiers changed from: */
    public void a() {
        if (this.f == null) {
            return;
        }
        this.h++;
        Iterator<m> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
        this.h--;
        if (this.h != 0 || this.g == null) {
            return;
        }
        this.f = this.g;
        this.g = null;
    }

    void a(float f, float f2, float f3, float f4) {
        this.k = com.aee.mokacam.widget.j.c(this.c)[0] / com.aee.mokacam.widget.j.b(f, f2, f3, f4);
        float[] fArrA = com.aee.mokacam.widget.j.a(com.aee.mokacam.widget.j.c(f, f2, f3, f4), this.c);
        this.j.set(fArrA[0], fArrA[1]);
    }

    void a(PointF pointF, float f, float f2, PointF pointF2) {
        if (c()) {
            float f3 = f * f2;
            Matrix matrixA = com.aee.mokacam.widget.j.a();
            matrixA.postScale(f3, f3, pointF.x, pointF.y);
            matrixA.postTranslate(pointF2.x - pointF.x, pointF2.y - pointF.y);
            this.c.set(matrixA);
            com.aee.mokacam.widget.j.b(matrixA);
            a();
            invalidate();
        }
    }

    void b() {
        super.setScaleType(ImageView.ScaleType.MATRIX);
    }

    /* JADX INFO: Access modifiers changed from: */
    public boolean b(float f, float f2) {
        if (!c()) {
            return false;
        }
        RectF rectFB = com.aee.mokacam.widget.j.b();
        a(rectFB);
        float width = getWidth();
        float height = getHeight();
        if (rectFB.right - rectFB.left < width) {
            f = 0.0f;
        } else if (rectFB.left + f > 0.0f) {
            f = rectFB.left < 0.0f ? -rectFB.left : 0.0f;
        } else if (rectFB.right + f < width) {
            f = rectFB.right > width ? width - rectFB.right : 0.0f;
        }
        if (rectFB.bottom - rectFB.top < height) {
            f2 = 0.0f;
        } else if (rectFB.top + f2 > 0.0f) {
            f2 = rectFB.top < 0.0f ? -rectFB.top : 0.0f;
        } else if (rectFB.bottom + f2 < height) {
            f2 = rectFB.bottom > height ? height - rectFB.bottom : 0.0f;
        }
        com.aee.mokacam.widget.j.a(rectFB);
        this.c.postTranslate(f, f2);
        a();
        invalidate();
        return (f == 0.0f && f2 == 0.0f) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: */
    public void c(float f, float f2) {
        float f3 = 0.0f;
        if (c()) {
            Matrix matrixA = com.aee.mokacam.widget.j.a();
            a(matrixA);
            float f4 = com.aee.mokacam.widget.j.c(matrixA)[0];
            float f5 = com.aee.mokacam.widget.j.c(this.c)[0];
            float f6 = f4 * f5;
            float width = getWidth();
            float height = getHeight();
            float maxScale = getMaxScale();
            float fA = a(f4, f5);
            if (fA <= maxScale) {
                maxScale = fA;
            }
            if (maxScale >= f4) {
                f4 = maxScale;
            }
            Matrix matrixA2 = com.aee.mokacam.widget.j.a(this.c);
            matrixA2.postScale(f4 / f6, f4 / f6, f, f2);
            matrixA2.postTranslate((width / 2.0f) - f, (height / 2.0f) - f2);
            Matrix matrixA3 = com.aee.mokacam.widget.j.a(matrixA);
            matrixA3.postConcat(matrixA2);
            RectF rectFA = com.aee.mokacam.widget.j.a(0.0f, 0.0f, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
            matrixA3.mapRect(rectFA);
            float f7 = rectFA.right - rectFA.left < width ? (width / 2.0f) - ((rectFA.right + rectFA.left) / 2.0f) : rectFA.left > 0.0f ? -rectFA.left : rectFA.right < width ? width - rectFA.right : 0.0f;
            if (rectFA.bottom - rectFA.top < height) {
                f3 = (height / 2.0f) - ((rectFA.bottom + rectFA.top) / 2.0f);
            } else if (rectFA.top > 0.0f) {
                f3 = -rectFA.top;
            } else if (rectFA.bottom < height) {
                f3 = height - rectFA.bottom;
            }
            matrixA2.postTranslate(f7, f3);
            e();
            this.l = new o(this, this.c, matrixA2);
            this.l.start();
            com.aee.mokacam.widget.j.a(rectFA);
            com.aee.mokacam.widget.j.b(matrixA3);
            com.aee.mokacam.widget.j.b(matrixA2);
            com.aee.mokacam.widget.j.b(matrixA);
        }
    }

    boolean c() {
        return getDrawable() != null && getDrawable().getIntrinsicWidth() > 0 && getDrawable().getIntrinsicHeight() > 0 && getWidth() > 0 && getHeight() > 0;
    }

    void d() {
        if (c()) {
            Matrix matrixA = com.aee.mokacam.widget.j.a();
            b(matrixA);
            float f = com.aee.mokacam.widget.j.c(matrixA)[0];
            float f2 = com.aee.mokacam.widget.j.c(this.c)[0];
            float width = getWidth();
            float height = getHeight();
            float maxScale = getMaxScale();
            float f3 = f > maxScale ? maxScale / f : 1.0f;
            float f4 = f2 * f3 < 1.0f ? 1.0f / f2 : f3;
            boolean z = f4 != 1.0f;
            Matrix matrixA2 = com.aee.mokacam.widget.j.a(matrixA);
            matrixA2.postScale(f4, f4, this.i.x, this.i.y);
            RectF rectFA = com.aee.mokacam.widget.j.a(0.0f, 0.0f, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
            matrixA2.mapRect(rectFA);
            float f5 = rectFA.right - rectFA.left < width ? (width / 2.0f) - ((rectFA.right + rectFA.left) / 2.0f) : rectFA.left > 0.0f ? -rectFA.left : rectFA.right < width ? width - rectFA.right : 0.0f;
            float f6 = rectFA.bottom - rectFA.top < height ? (height / 2.0f) - ((rectFA.bottom + rectFA.top) / 2.0f) : rectFA.top > 0.0f ? -rectFA.top : rectFA.bottom < height ? height - rectFA.bottom : 0.0f;
            if (f5 != 0.0f || f6 != 0.0f) {
                z = true;
            }
            if (z) {
                Matrix matrixA3 = com.aee.mokacam.widget.j.a(this.c);
                matrixA3.postScale(f4, f4, this.i.x, this.i.y);
                matrixA3.postTranslate(f5, f6);
                e();
                this.l = new o(this, this.c, matrixA3);
                this.l.start();
                com.aee.mokacam.widget.j.b(matrixA3);
            }
            com.aee.mokacam.widget.j.a(rectFA);
            com.aee.mokacam.widget.j.b(matrixA2);
            com.aee.mokacam.widget.j.b(matrixA);
        }
    }

    /* JADX INFO: Access modifiers changed from: */
    public void d(float f, float f2) {
        if (c()) {
            e();
            this.m = new i(this, f / 60.0f, f2 / 60.0f);
            this.m.start();
        }
    }

    void e() {
        if (this.l != null) {
            this.l.cancel();
            this.l = null;
        }
        if (this.m != null) {
            this.m.cancel();
            this.m = null;
        }
    }

    protected float a(float f, float f2) {
        if (f * f2 < 4.0f) {
            return 4.0f;
        }
        return f;
    }

    public Matrix a(Matrix matrix) {
        if (matrix == null) {
            matrix = new Matrix();
        } else {
            matrix.reset();
        }
        if (c()) {
            RectF rectFA = com.aee.mokacam.widget.j.a(0.0f, 0.0f, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
            RectF rectFA2 = com.aee.mokacam.widget.j.a(0.0f, 0.0f, getWidth(), getHeight());
            matrix.setRectToRect(rectFA, rectFA2, Matrix.ScaleToFit.CENTER);
            com.aee.mokacam.widget.j.a(rectFA2);
            com.aee.mokacam.widget.j.a(rectFA);
        }
        return matrix;
    }

    public RectF a(RectF rectF) {
        if (rectF == null) {
            rectF = new RectF();
        } else {
            rectF.setEmpty();
        }
        if (c()) {
            Matrix matrixA = com.aee.mokacam.widget.j.a();
            b(matrixA);
            rectF.set(0.0f, 0.0f, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
            matrixA.mapRect(rectF);
            com.aee.mokacam.widget.j.b(matrixA);
        }
        return rectF;
    }

    public Matrix b(Matrix matrix) {
        Matrix matrixA = a(matrix);
        matrixA.postConcat(this.c);
        return matrixA;
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i) {
        if (this.e == 2) {
            return true;
        }
        RectF rectFA = a((RectF) null);
        if (rectFA != null && !rectFA.isEmpty()) {
            return i > 0 ? rectFA.right > ((float) getWidth()) : rectFA.left < 0.0f;
        }
        return false;
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i) {
        if (this.e == 2) {
            return true;
        }
        RectF rectFA = a((RectF) null);
        if (rectFA != null && !rectFA.isEmpty()) {
            return i > 0 ? rectFA.bottom > ((float) getHeight()) : rectFA.top < 0.0f;
        }
        return false;
    }

    public RectF getMask() {
        if (this.d != null) {
            return new RectF(this.d);
        }
        return null;
    }

    protected float getMaxScale() {
        return 4.0f;
    }

    public int getPinchMode() {
        return this.e;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (c()) {
            Matrix matrixA = com.aee.mokacam.widget.j.a();
            setImageMatrix(b(matrixA));
            com.aee.mokacam.widget.j.b(matrixA);
        }
        if (this.d == null) {
            super.onDraw(canvas);
            return;
        }
        canvas.save();
        canvas.clipRect(this.d);
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action == 1 || action == 3) {
            if (this.e == 2) {
                d();
            }
            this.e = 0;
        } else if (action == 6) {
            if (this.e == 2 && motionEvent.getPointerCount() > 2) {
                if ((motionEvent.getAction() >> 8) == 0) {
                    a(motionEvent.getX(1), motionEvent.getY(1), motionEvent.getX(2), motionEvent.getY(2));
                } else if ((motionEvent.getAction() >> 8) == 1) {
                    a(motionEvent.getX(0), motionEvent.getY(0), motionEvent.getX(2), motionEvent.getY(2));
                }
            }
        } else if (action == 0) {
            if (this.l == null || !this.l.isRunning()) {
                e();
                this.e = 1;
                this.i.set(motionEvent.getX(), motionEvent.getY());
            }
        } else if (action == 5) {
            e();
            this.e = 2;
            a(motionEvent.getX(0), motionEvent.getY(0), motionEvent.getX(1), motionEvent.getY(1));
        } else if (action == 2 && (this.l == null || !this.l.isRunning())) {
            if (this.e == 1) {
                b(motionEvent.getX() - this.i.x, motionEvent.getY() - this.i.y);
                this.i.set(motionEvent.getX(), motionEvent.getY());
            } else if (this.e == 2 && motionEvent.getPointerCount() > 1) {
                float fB = com.aee.mokacam.widget.j.b(motionEvent.getX(0), motionEvent.getY(0), motionEvent.getX(1), motionEvent.getY(1));
                float[] fArrC = com.aee.mokacam.widget.j.c(motionEvent.getX(0), motionEvent.getY(0), motionEvent.getX(1), motionEvent.getY(1));
                this.i.set(fArrC[0], fArrC[1]);
                a(this.j, this.k, fB, this.i);
            }
        }
        this.n.onTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.a = onClickListener;
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.b = onLongClickListener;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
    }
}
