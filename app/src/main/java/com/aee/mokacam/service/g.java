package com.aee.mokacam.service;

import android.os.Handler;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
class g implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ Handler c;

    g(a aVar, String str, Handler handler) {
        this.a = aVar;
        this.b = str;
        this.c = handler;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            ReceiveMsg receiveMsgA = a.a().a(new com.aee.mokacam.bean.h(1286, "firmware.bin", null, 0, this.b));
            TimeUnit.MILLISECONDS.sleep(880L);
            System.out.println("receiveMsg-------------->" + receiveMsgA.toString());
            if (receiveMsgA == null || receiveMsgA.getRval() < 0) {
                return;
            }
            AeeApplication.a().bl = this.a.b(this.b);
            this.c.sendEmptyMessageDelayed(32800, 5800L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
