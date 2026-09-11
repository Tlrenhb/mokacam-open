package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;
import com.aee.mokacam.utils.ResolveJson;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
class u implements Runnable {
    final /* synthetic */ o a;

    u(o oVar) {
        this.a = oVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.a.b) {
            try {
                if (o.e == null) {
                    o.e = this.a.d.getInputStream();
                }
                while (true) {
                    if (!this.a.b) {
                        break;
                    }
                    if (this.a.i) {
                        TimeUnit.SECONDS.sleep(1L);
                        break;
                    }
                    int iAvailable = o.e.available();
                    byte[] bArr = new byte[iAvailable];
                    if (o.e.read(bArr, 0, iAvailable - 0) + 0 > 0) {
                        ReceiveMsg receiveMsg = (ReceiveMsg) ResolveJson.resolveNormalInfo(new String(bArr), ReceiveMsg.class);
                        receiveMsg.getMsg_id();
                        receiveMsg.getType();
                        if (receiveMsg != null) {
                            if (this.a.c(receiveMsg)) {
                                AeeApplication.a().m = true;
                            } else if (this.a.b(receiveMsg)) {
                                AeeApplication.a().i = true;
                            } else if (this.a.a(receiveMsg)) {
                                AeeApplication.a().j = true;
                            } else if (this.a.d(receiveMsg) != 0) {
                                AeeApplication.a().k = this.a.d(receiveMsg);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
