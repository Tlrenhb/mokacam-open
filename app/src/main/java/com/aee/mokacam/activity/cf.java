package com.aee.mokacam.activity;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes.dex */
class cf implements Runnable {
    final /* synthetic */ ce a;
    final /* synthetic */ Bitmap b;

    cf(ce ceVar, Bitmap bitmap) {
        this.a = ceVar;
        this.b = bitmap;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.a.h.setImageBitmap(this.b);
        this.a.a.n.setVisibility(0);
    }
}
