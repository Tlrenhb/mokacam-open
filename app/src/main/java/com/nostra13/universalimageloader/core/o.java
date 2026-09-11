package com.nostra13.universalimageloader.core;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
class o implements Runnable {
    final /* synthetic */ q a;
    final /* synthetic */ ImageLoaderEngine b;

    o(ImageLoaderEngine imageLoaderEngine, q qVar) {
        this.b = imageLoaderEngine;
        this.a = qVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        File fileA = this.b.a.o.a(this.a.a());
        boolean z = fileA != null && fileA.exists();
        this.b.g();
        if (z) {
            this.b.c.execute(this.a);
        } else {
            this.b.b.execute(this.a);
        }
    }
}
