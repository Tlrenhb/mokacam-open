package com.aee.mokacam.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class ZoomImageView extends ImageView implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {
    boolean a;
    float b;
    float c;
    float d;
    Matrix e;
    ScaleGestureDetector f;
    int g;
    float h;
    float i;
    int j;
    boolean k;
    GestureDetector l;
    boolean m;

    public ZoomImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ZoomImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = false;
        this.k = false;
        this.e = new Matrix();
        setScaleType(ImageView.ScaleType.MATRIX);
        this.f = new ScaleGestureDetector(context, this);
        setOnTouchListener(this);
        this.j = ViewConfiguration.get(context).getScaledTouchSlop();
        this.l = new GestureDetector(context, new q(this));
    }

    /* JADX INFO: Access modifiers changed from: */
    public void a() {
        float fWidth;
        float fHeight;
        RectF drawableRectF = getDrawableRectF();
        int width = getWidth();
        int height = getHeight();
        if (drawableRectF.width() >= width) {
            fWidth = drawableRectF.left > 0.0f ? -drawableRectF.left : 0.0f;
            if (drawableRectF.right < width) {
                fWidth = width - drawableRectF.right;
            }
        } else {
            fWidth = 0.0f;
        }
        if (drawableRectF.height() >= height) {
            fHeight = drawableRectF.top > 0.0f ? -drawableRectF.top : 0.0f;
            if (drawableRectF.bottom < height) {
                fHeight = height - drawableRectF.bottom;
            }
        }
        if (drawableRectF.width() < width) {
            fWidth = ((width / 2) - drawableRectF.right) + (drawableRectF.width() / 2.0f);
        }
        if (drawableRectF.height() < height) {
            fHeight = ((height / 2) - drawableRectF.bottom) + (drawableRectF.height() / 2.0f);
        }
        this.e.postTranslate(fWidth, fHeight);
    }

    boolean a(float f, float f2) {
        return Math.sqrt((double) ((f * f) + (f2 * f2))) > ((double) this.j);
    }

    void b() {
        float f = 0.0f;
        RectF drawableRectF = getDrawableRectF();
        int width = getWidth();
        int height = getHeight();
        float f2 = (drawableRectF.width() <= ((float) width) || drawableRectF.left <= 0.0f) ? 0.0f : -drawableRectF.left;
        if (drawableRectF.width() > width && drawableRectF.right < width) {
            f2 = width - drawableRectF.right;
        }
        if (drawableRectF.height() > height && drawableRectF.top > 0.0f) {
            f = -drawableRectF.top;
        }
        if (drawableRectF.height() > height && drawableRectF.bottom < height) {
            f = height - drawableRectF.bottom;
        }
        this.e.postTranslate(f2, f);
    }

    RectF getDrawableRectF() {
        Matrix matrix = this.e;
        RectF rectF = new RectF();
        if (getDrawable() != null) {
            rectF.set(0.0f, 0.0f, getDrawable().getIntrinsicWidth(), getDrawable().getIntrinsicHeight());
        }
        matrix.mapRect(rectF);
        return rectF;
    }

    /* JADX INFO: Access modifiers changed from: */
    public float getDrawableScale() {
        float[] fArr = new float[9];
        this.e.getValues(fArr);
        return fArr[0];
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override // android.widget.ImageView, android.view.View
    @SuppressLint({"NewApi"})
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        Drawable drawable;
        if (this.a || (drawable = getDrawable()) == null) {
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int width = getWidth();
        int height = getHeight();
        float fMin = (intrinsicWidth <= width || intrinsicHeight >= height) ? 1.0f : (width * 1.0f) / intrinsicWidth;
        if (intrinsicWidth > width && intrinsicHeight > height) {
            fMin = Math.min((width * 1.0f) / intrinsicWidth, (height * 1.0f) / intrinsicHeight);
        }
        if (intrinsicWidth < width && intrinsicHeight < height) {
            fMin = Math.min((width * 1.0f) / intrinsicWidth, (height * 1.0f) / intrinsicHeight);
        }
        if (intrinsicWidth < width && intrinsicHeight > height) {
            fMin = (height * 1.0f) / intrinsicHeight;
        }
        this.b = fMin;
        this.c = this.b * 2.0f;
        this.d = this.b * 4.0f;
        this.e.postTranslate((width / 2) - (intrinsicWidth / 2), (height / 2) - (intrinsicHeight / 2));
        this.e.postScale(this.b, this.b, width / 2, height / 2);
        setImageMatrix(this.e);
        this.a = true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        if (getDrawable() != null) {
            float drawableScale = getDrawableScale();
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            if ((drawableScale < this.d && scaleFactor > 1.0f) || (drawableScale > this.b && scaleFactor < 1.0f)) {
                if (drawableScale * scaleFactor < this.b) {
                    scaleFactor = this.b / drawableScale;
                }
                if (drawableScale * scaleFactor > this.d) {
                    scaleFactor = this.d / drawableScale;
                }
                this.e.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getCurrentSpan());
                a();
                setImageMatrix(this.e);
            }
        }
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.l.onTouchEvent(motionEvent)) {
            if (this.f != null) {
                this.f.onTouchEvent(motionEvent);
            }
            int pointerCount = motionEvent.getPointerCount();
            float y = 0.0f;
            float x = 0.0f;
            for (int i = 0; i < pointerCount; i++) {
                x += motionEvent.getX(i);
                y += motionEvent.getY(i);
            }
            float f = x / pointerCount;
            float f2 = y / pointerCount;
            if (this.g != pointerCount) {
                this.k = false;
                this.h = f;
                this.i = f2;
            }
            this.g = pointerCount;
            RectF drawableRectF = getDrawableRectF();
            switch (motionEvent.getAction()) {
                case 0:
                    if ((drawableRectF.width() > ((double) getWidth()) + 0.01d || drawableRectF.height() > ((double) getHeight()) + 0.01d) && (getParent() instanceof ViewPager)) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    break;
                case 1:
                case 3:
                    this.g = 0;
                    break;
                case 2:
                    if ((drawableRectF.width() > ((double) getWidth()) + 0.01d || drawableRectF.height() > ((double) getHeight()) + 0.01d) && (getParent() instanceof ViewPager)) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    float f3 = f - this.h;
                    float f4 = f2 - this.i;
                    if (!this.k) {
                        this.k = a(f3, f4);
                    }
                    if (this.k) {
                        if (drawableRectF.width() <= getWidth()) {
                            f3 = 0.0f;
                        }
                        this.e.postTranslate(f3, drawableRectF.height() > ((float) getHeight()) ? f4 : 0.0f);
                        b();
                        setImageMatrix(this.e);
                    }
                    this.h = f;
                    this.i = f2;
                    break;
            }
        }
        return true;
    }
}
