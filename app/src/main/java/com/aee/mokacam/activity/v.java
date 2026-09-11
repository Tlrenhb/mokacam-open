package com.aee.mokacam.activity;

/* JADX INFO: loaded from: classes.dex */
class v implements Runnable {
    final /* synthetic */ AeeCameraSettingActivity a;

    v(AeeCameraSettingActivity aeeCameraSettingActivity) {
        this.a = aeeCameraSettingActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (com.aee.mokacam.service.a.a().b()) {
            this.a.a();
        }
        this.a.i.post(new w(this));
    }
}
