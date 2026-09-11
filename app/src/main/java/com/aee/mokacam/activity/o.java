package com.aee.mokacam.activity;

import android.text.TextUtils;
import com.aee.mokacam.bean.ReceiveMsg;
import com.aee.mokacam.bean.SendMsg;

/* JADX INFO: loaded from: classes.dex */
class o extends Thread {
    final /* synthetic */ AeeCameraActivty a;

    o(AeeCameraActivty aeeCameraActivty) {
        this.a = aeeCameraActivty;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        ReceiveMsg receiveMsgA = com.aee.mokacam.service.a.a().a(new SendMsg("相机电量 ", 13, (String) null, "video_bat", 21));
        if (receiveMsgA == null || receiveMsgA.getRval() < 0) {
            return;
        }
        Object param = receiveMsgA.getParam();
        if (param != null) {
            this.a.J = param.toString();
        }
        String type = receiveMsgA.getType();
        if (!TextUtils.isEmpty(type)) {
            this.a.K = type.toString();
        }
        this.a.i.sendEmptyMessage(100);
    }
}
