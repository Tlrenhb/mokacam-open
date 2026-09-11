package com.nostra13.universalimageloader.core.c;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;

/* JADX INFO: loaded from: classes.dex */
public class c implements a {
    protected final String a;
    protected final com.nostra13.universalimageloader.core.assist.c b;
    protected final ViewScaleType c;

    public c(String str, com.nostra13.universalimageloader.core.assist.c cVar, ViewScaleType viewScaleType) {
        if (cVar == null) {
            throw new IllegalArgumentException("imageSize must not be null");
        }
        if (viewScaleType == null) {
            throw new IllegalArgumentException("scaleType must not be null");
        }
        this.a = str;
        this.b = cVar;
        this.c = viewScaleType;
    }

    @Override // com.nostra13.universalimageloader.core.c.a
    public int a() {
        return this.b.a();
    }

    @Override // com.nostra13.universalimageloader.core.c.a
    public boolean a(Bitmap bitmap) {
        return true;
    }

    @Override // com.nostra13.universalimageloader.core.c.a
    public boolean a(Drawable drawable) {
        return true;
    }

    @Override // com.nostra13.universalimageloader.core.c.a
    public int b() {
        return this.b.b();
    }

    @Override // com.nostra13.universalimageloader.core.c.a
    public ViewScaleType c() {
        return this.c;
    }

    @Override // com.nostra13.universalimageloader.core.c.a
    public View d() {
        return null;
    }

    @Override // com.nostra13.universalimageloader.core.c.a
    public boolean e() {
        return false;
    }

    @Override // com.nostra13.universalimageloader.core.c.a
    public int f() {
        return TextUtils.isEmpty(this.a) ? super.hashCode() : this.a.hashCode();
    }
}
