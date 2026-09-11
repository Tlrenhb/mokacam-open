package com.facebook.rebound;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
class e implements Runnable {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!this.a.d || this.a.a == null) {
            return;
        }
        this.a.a.b(SystemClock.uptimeMillis() - this.a.e);
        this.a.b.post(this.a.c);
    }
}
