package com.facebook.rebound;

import android.os.SystemClock;
import android.view.Choreographer;

/* JADX INFO: loaded from: classes.dex */
class c implements Choreographer.FrameCallback {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j) {
        if (!this.a.d || this.a.a == null) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        this.a.a.b(jUptimeMillis - this.a.e);
        this.a.e = jUptimeMillis;
        this.a.b.postFrameCallback(this.a.c);
    }
}
