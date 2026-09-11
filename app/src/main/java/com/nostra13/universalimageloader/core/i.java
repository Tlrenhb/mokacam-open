package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class i extends com.nostra13.universalimageloader.core.d.d {
    private Bitmap a;

    private i() {
    }

    public Bitmap a() {
        return this.a;
    }

    @Override // com.nostra13.universalimageloader.core.d.d, com.nostra13.universalimageloader.core.d.a
    public void a(String str, View view, Bitmap bitmap) {
        this.a = bitmap;
    }
}
