package com.aee.mokacam.utils;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
class f implements Callable<Void> {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void call() {
        synchronized (this.a) {
            if (this.a.i != null) {
                this.a.h();
                if (this.a.f()) {
                    try {
                        this.a.e();
                    } catch (IOException e) {
                    }
                    this.a.k = 0;
                }
            }
        }
        return null;
    }
}
