package com.aee.mokacam.activity;

import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
class cu extends TimerTask {
    final /* synthetic */ ShowPicOrVideoActivity a;

    cu(ShowPicOrVideoActivity showPicOrVideoActivity) {
        this.a = showPicOrVideoActivity;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        if (this.a.c) {
            long currentPosition = this.a.t.getCurrentPosition();
            if (this.a.t.isPlaying()) {
                this.a.k.setProgress((int) currentPosition);
            }
            this.a.runOnUiThread(new cv(this, ShowPicOrVideoActivity.a(this.a.u - currentPosition)));
        }
    }
}
