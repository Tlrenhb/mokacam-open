package com.aee.mokacam.activity;

import android.os.Looper;
import com.aee.mokacam.service.UpdateManager;

/* JADX INFO: loaded from: classes.dex */
class d implements Runnable {
    final /* synthetic */ AeeAppSettingActivity a;

    d(AeeAppSettingActivity aeeAppSettingActivity) {
        this.a = aeeAppSettingActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        Looper.prepare();
        new UpdateManager(this.a).a(false);
        Looper.loop();
    }
}
