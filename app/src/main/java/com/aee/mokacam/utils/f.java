package com.aee.mokacam.utils;

import java.io.IOException;
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
                try {
                    this.a.h();
                    if (this.a.f()) {
                        this.a.e();
                    }
                    this.a.k = 0;
                } catch (IOException e) {
                }
            }
        }
        return null;
    }
}
