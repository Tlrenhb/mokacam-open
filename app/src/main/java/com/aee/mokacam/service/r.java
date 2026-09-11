package com.aee.mokacam.service;

import android.os.Handler;
import android.os.Looper;
import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
class r implements Runnable {
    final /* synthetic */ o a;

    r(o oVar) {
        this.a = oVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (AeeApplication.a().b && AeeApplication.a().p == null) {
                a.a().a(new s(this));
            }
            Thread.sleep(10000L);
            if (this.a.d == null || !this.a.d.isClosed()) {
                AeeApplication.a().b = true;
            } else {
                AeeApplication.a().b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            AeeApplication.a().b = false;
        }
        this.a.g();
        if (!this.a.b || AeeApplication.a().b) {
            return;
        }
        this.a.b();
        Looper.prepare();
        new Handler().postDelayed(new t(this), 10000L);
        Looper.loop();
    }
}
