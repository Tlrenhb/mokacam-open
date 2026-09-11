package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;
import com.aee.mokacam.bean.SendMsg;
import com.aee.mokacam.utils.ResolveJson;

/* JADX INFO: loaded from: classes.dex */
class j implements Runnable {
    final /* synthetic */ a a;
    private final /* synthetic */ int b;
    private final /* synthetic */ n c;

    j(a aVar, int i, n nVar) {
        this.a = aVar;
        this.b = i;
        this.c = nVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        ReceiveMsg receiveMsg;
        if (AeeApplication.a().e == -1000 ? this.a.g() : true) {
            try {
                receiveMsg = (ReceiveMsg) ResolveJson.resolveNormalInfo(a.a().c(new SendMsg(this.b, AeeApplication.a().e, null, null).toJson()), ReceiveMsg.class);
            } catch (Exception e) {
                e.printStackTrace();
                receiveMsg = null;
            }
        } else {
            receiveMsg = null;
        }
        if (this.c != null) {
            this.c.a(receiveMsg);
        }
    }
}
