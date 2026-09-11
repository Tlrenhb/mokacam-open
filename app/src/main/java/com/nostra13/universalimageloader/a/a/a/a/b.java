package com.nostra13.universalimageloader.a.a.a.a;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
class b implements Callable<Void> {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void call() {
        synchronized (this.a) {
            if (this.a.m != null) {
                this.a.k();
                this.a.l();
                if (this.a.i()) {
                    this.a.h();
                    this.a.o = 0;
                }
            }
        }
        return null;
    }
}
