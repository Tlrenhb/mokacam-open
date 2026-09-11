package com.aee.mokacam.activity;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
class dc extends Thread {
    final /* synthetic */ SplashActivity a;

    dc(SplashActivity splashActivity) {
        this.a = splashActivity;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        SystemClock.sleep(3200L);
        this.a.runOnUiThread(new dd(this));
    }
}
