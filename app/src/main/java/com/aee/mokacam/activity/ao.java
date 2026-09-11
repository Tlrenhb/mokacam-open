package com.aee.mokacam.activity;

import com.aee.mokacam.bean.SendMsg;
import com.aee.mokacam.constants.AeeConstants;

/* JADX INFO: loaded from: classes.dex */
class ao implements Runnable {
    final /* synthetic */ AeeCameraSettingActivity a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;

    ao(AeeCameraSettingActivity aeeCameraSettingActivity, String str, String str2) {
        this.a = aeeCameraSettingActivity;
        this.b = str;
        this.c = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.b = new SendMsg(this.b, AeeConstants.i, this.c, (String) null, 18);
        this.a.c = com.aee.mokacam.service.a.a().a(this.a.b);
        if (this.a.c == null || this.a.c.getRval() < 0 || !this.a.c.getParam().equals(this.c)) {
            return;
        }
        this.a.a = com.aee.mokacam.utils.a.c(this.a.c.getOptions());
        if (!this.a.c.getPermission().equals("settable")) {
            this.a.d = false;
        } else {
            this.a.d = true;
            this.a.i.sendEmptyMessage(110);
        }
    }
}
