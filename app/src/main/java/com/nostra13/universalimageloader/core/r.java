package com.nostra13.universalimageloader.core;

/* JADX INFO: loaded from: classes.dex */
class r implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ q c;

    r(q qVar, int i, int i2) {
        this.c = qVar;
        this.a = i;
        this.b = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.e.a(this.c.a, this.c.b.d(), this.a, this.b);
    }
}
