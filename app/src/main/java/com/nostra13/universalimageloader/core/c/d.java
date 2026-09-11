package com.nostra13.universalimageloader.core.c;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import com.nostra13.universalimageloader.b.e;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class d implements a {
    protected Reference<View> a;
    protected boolean b;

    public d(View view) {
        this(view, true);
    }

    public d(View view, boolean z) {
        if (view == null) {
            throw new IllegalArgumentException("view must not be null");
        }
        this.a = new WeakReference(view);
        this.b = z;
    }

    @Override // com.nostra13.universalimageloader.core.c.a
    public int a() {
        View view = this.a.get();
        if (view == null) {
            return 0;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int width = (!this.b || layoutParams == null || layoutParams.width == -2) ? 0 : view.getWidth();
        return (width > 0 || layoutParams == null) ? width : layoutParams.width;
    }

    protected abstract void a(Bitmap bitmap, View view);

    protected abstract void a(Drawable drawable, View view);

    @Override // com.nostra13.universalimageloader.core.c.a
    public boolean a(Bitmap bitmap) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = this.a.get();
            if (view != null) {
                a(bitmap, view);
                return true;
            }
        } else {
            e.c("Can't set a bitmap into view. You should call ImageLoader on UI thread for it.", new Object[0]);
        }
        return false;
    }

    @Override // com.nostra13.universalimageloader.core.c.a
    public boolean a(Drawable drawable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = this.a.get();
            if (view != null) {
                a(drawable, view);
                return true;
            }
        } else {
            e.c("Can't set a drawable into view. You should call ImageLoader on UI thread for it.", new Object[0]);
        }
        return false;
    }

    @Override // com.nostra13.universalimageloader.core.c.a
    public int b() {
        View view = this.a.get();
        if (view == null) {
            return 0;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int height = (!this.b || layoutParams == null || layoutParams.height == -2) ? 0 : view.getHeight();
        return (height > 0 || layoutParams == null) ? height : layoutParams.height;
    }

    @Override // com.nostra13.universalimageloader.core.c.a
    public ViewScaleType c() {
        return ViewScaleType.CROP;
    }

    @Override // com.nostra13.universalimageloader.core.c.a
    public View d() {
        return this.a.get();
    }

    @Override // com.nostra13.universalimageloader.core.c.a
    public boolean e() {
        return this.a.get() == null;
    }

    @Override // com.nostra13.universalimageloader.core.c.a
    public int f() {
        View view = this.a.get();
        return view == null ? super.hashCode() : view.hashCode();
    }
}
