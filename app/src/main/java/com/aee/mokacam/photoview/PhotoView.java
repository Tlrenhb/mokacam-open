package com.aee.mokacam.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class PhotoView extends ImageView implements i {
    private r a;
    private ImageView.ScaleType b;

    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setScaleType(ImageView.ScaleType.MATRIX);
        a();
    }

    protected void a() {
        if (this.a == null || this.a.c() == null) {
            this.a = new r(this);
        }
        if (this.b != null) {
            setScaleType(this.b);
            this.b = null;
        }
    }

    public RectF getDisplayRect() {
        return this.a.b();
    }

    public i getIPhotoViewImplementation() {
        return this.a;
    }

    @Override // android.widget.ImageView
    public Matrix getImageMatrix() {
        return this.a.l();
    }

    public float getMaximumScale() {
        return this.a.f();
    }

    public float getMediumScale() {
        return this.a.e();
    }

    public float getMinimumScale() {
        return this.a.d();
    }

    public float getScale() {
        return this.a.g();
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.a.h();
    }

    public Bitmap getVisibleRectangleBitmap() {
        return this.a.m();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        a();
        super.onAttachedToWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        this.a.a();
        this.a = null;
        super.onDetachedFromWindow();
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.a.a(z);
    }

    @Override // android.widget.ImageView
    protected boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        if (this.a != null) {
            this.a.k();
        }
        return frame;
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (this.a != null) {
            this.a.k();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        if (this.a != null) {
            this.a.k();
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (this.a != null) {
            this.a.k();
        }
    }

    public void setMaximumScale(float f) {
        this.a.e(f);
    }

    public void setMediumScale(float f) {
        this.a.d(f);
    }

    public void setMinimumScale(float f) {
        this.a.c(f);
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.a.a(onDoubleTapListener);
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.a.a(onLongClickListener);
    }

    public void setOnMatrixChangeListener(v vVar) {
        this.a.a(vVar);
    }

    public void setOnPhotoTapListener(w wVar) {
        this.a.a(wVar);
    }

    public void setOnScaleChangeListener(x xVar) {
        this.a.a(xVar);
    }

    public void setOnSingleFlingListener(y yVar) {
        this.a.a(yVar);
    }

    public void setOnViewTapListener(z zVar) {
        this.a.a(zVar);
    }

    public void setRotationBy(float f) {
        this.a.b(f);
    }

    public void setRotationTo(float f) {
        this.a.a(f);
    }

    public void setScale(float f) {
        this.a.f(f);
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (this.a != null) {
            this.a.a(scaleType);
        } else {
            this.b = scaleType;
        }
    }

    public void setZoomTransitionDuration(int i) {
        this.a.a(i);
    }

    public void setZoomable(boolean z) {
        this.a.b(z);
    }
}
