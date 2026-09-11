package com.aee.mokacam.activity;

import com.aee.mokacam.bean.SendMsg;
import com.aee.mokacam.constants.AeeConstants;

/* JADX INFO: loaded from: classes.dex */
class x implements Runnable {
    final /* synthetic */ AeeCameraSettingActivity a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;

    x(AeeCameraSettingActivity aeeCameraSettingActivity, String str, String str2, String str3) {
        this.a = aeeCameraSettingActivity;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.b = new SendMsg(this.b, AeeConstants.i, this.c, this.d, 18);
        this.a.c = com.aee.mokacam.service.a.a().a(this.a.b);
        if (this.a.c == null || this.a.c.getRval() < 0 || !this.a.c.getParam().equals(this.c)) {
            return;
        }
        this.a.e = com.aee.mokacam.utils.a.c(this.a.c.getOptions());
        if (this.b.equals("快拍设置配置") || this.b.equals("延迟拍照配置")) {
            String[] strArr = new String[this.a.e.length - 1];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = this.a.e[i + 1];
            }
            this.a.a = com.aee.mokacam.utils.q.a(this.c, strArr);
        } else {
            this.a.a = com.aee.mokacam.utils.q.a(this.c, this.a.e);
        }
        if (!this.a.c.getPermission().equals("settable")) {
            this.a.d = false;
        } else {
            this.a.d = true;
            this.a.i.sendEmptyMessage(110);
        }
    }
}
