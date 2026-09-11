package com.facebook.rebound;

import android.annotation.TargetApi;
import android.os.SystemClock;
import android.view.Choreographer;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
class b extends n {
    private final Choreographer b;
    private final Choreographer.FrameCallback c = new c(this);
    private boolean d;
    private long e;

    public b(Choreographer choreographer) {
        this.b = choreographer;
    }

    public static b a() {
        return new b(Choreographer.getInstance());
    }

    @Override // com.facebook.rebound.n
    public void b() {
        if (this.d) {
            return;
        }
        this.d = true;
        this.e = SystemClock.uptimeMillis();
        this.b.removeFrameCallback(this.c);
        this.b.postFrameCallback(this.c);
    }

    @Override // com.facebook.rebound.n
    public void c() {
        this.d = false;
        this.b.removeFrameCallback(this.c);
    }
}
