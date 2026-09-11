package com.aee.mokacam.activity;

import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
class p extends TimerTask {
    final /* synthetic */ AeeCameraActivty a;

    p(AeeCameraActivty aeeCameraActivty) {
        this.a = aeeCameraActivty;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.a.y++;
        this.a.runOnUiThread(new q(this));
    }
}
