package com.aee.mokacam.activity;

import android.os.Looper;
import com.aee.mokacam.service.UpdateManager;

/* JADX INFO: loaded from: classes.dex */
class bs implements Runnable {
    final /* synthetic */ ProductActivity a;

    bs(ProductActivity productActivity) {
        this.a = productActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.a.b = new UpdateManager(this.a);
        this.a.b.a(false);
        Looper.loop();
    }
}
