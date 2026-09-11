package com.facebook.rebound;

import android.os.Handler;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
class d extends n {
    final Handler b;
    final Runnable c = new e(this);
    boolean d;
    long e;

    public d(Handler handler) {
        this.b = handler;
    }

    public static n a() {
        return new d(new Handler());
    }

    @Override // com.facebook.rebound.n
    public void b() {
        if (this.d) {
            return;
        }
        this.d = true;
        this.e = SystemClock.uptimeMillis();
        this.b.removeCallbacks(this.c);
        this.b.post(this.c);
    }

    @Override // com.facebook.rebound.n
    public void c() {
        this.d = false;
        this.b.removeCallbacks(this.c);
    }
}
