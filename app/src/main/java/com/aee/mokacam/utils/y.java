package com.aee.mokacam.utils;

import android.os.Looper;
import android.widget.Toast;
import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
class y implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ int b;

    y(boolean z, int i) {
        this.a = z;
        this.b = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.a) {
                Looper.prepare();
                Toast.makeText(AeeApplication.a().getApplicationContext(), this.b, 0).show();
                Looper.loop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
