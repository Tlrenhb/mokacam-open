package com.aee.mokacam.activity;

import com.aee.mokacam.bean.ReceiveMsg;
import com.aee.mokacam.bean.SendMsg;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
class f extends Thread {
    final /* synthetic */ AeeCameraActivty a;

    f(AeeCameraActivty aeeCameraActivty) {
        this.a = aeeCameraActivty;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Object param;
        ReceiveMsg receiveMsgA = com.aee.mokacam.service.a.a().a(new SendMsg("录像时间 ", 515, (String) null, "video_time", 21));
        if (receiveMsgA == null || receiveMsgA.getRval() < 0 || (param = receiveMsgA.getParam()) == null) {
            return;
        }
        String string = param.toString();
        if (Pattern.matches("\\d+", string)) {
            this.a.y = Integer.parseInt(string);
            this.a.k();
        }
    }
}
