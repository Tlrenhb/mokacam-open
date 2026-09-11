package com.nostra13.universalimageloader.core;

import com.nostra13.universalimageloader.core.assist.FailReason;

/* JADX INFO: loaded from: classes.dex */
class s implements Runnable {
    final /* synthetic */ FailReason.FailType a;
    final /* synthetic */ Throwable b;
    final /* synthetic */ q c;

    s(q qVar, FailReason.FailType failType, Throwable th) {
        this.c = qVar;
        this.a = failType;
        this.b = th;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.c.c.c()) {
            this.c.b.a(this.c.c.c(this.c.i.a));
        }
        this.c.d.a(this.c.a, this.c.b.d(), new FailReason(this.a, this.b));
    }
}
