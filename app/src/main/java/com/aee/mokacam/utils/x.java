package com.aee.mokacam.utils;

import android.os.Looper;
import android.widget.Toast;
import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
class x implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ String b;

    x(boolean z, String str) {
        this.a = z;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.a) {
                Looper.prepare();
                w.a = Toast.makeText(AeeApplication.a().getApplicationContext(), this.b, 0);
                w.a.setGravity(17, 0, 0);
                w.a.show();
                Looper.loop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
