package com.aee.mokacam.activity;

import com.aee.mokacam.bean.ReceiveMsg;
import com.aee.mokacam.bean.SendMsg;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
class s extends Thread {
    final /* synthetic */ AeeCameraActivty a;

    s(AeeCameraActivty aeeCameraActivty) {
        this.a = aeeCameraActivty;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Object param;
        ReceiveMsg receiveMsgA = com.aee.mokacam.service.a.a().a(new SendMsg("相机SD卡剩余内存", 5, (String) null, "free", 21));
        if (receiveMsgA == null || receiveMsgA.getRval() < 0 || (param = receiveMsgA.getParam()) == null) {
            return;
        }
        if (Integer.parseInt(param.toString()) <= 0) {
            this.a.i.sendEmptyMessage(102);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.a.n();
    }
}
