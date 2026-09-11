package com.aee.mokacam.service;

import android.text.TextUtils;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;
import com.aee.mokacam.bean.SendMsg;
import com.aee.mokacam.utils.ResolveJson;

/* JADX INFO: loaded from: classes.dex */
class k implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ SendMsg b;
    final /* synthetic */ n c;

    k(a aVar, SendMsg sendMsg, n nVar) {
        this.a = aVar;
        this.b = sendMsg;
        this.c = nVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0069  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void run() {
        ReceiveMsg receiveMsg = null;
        if (AeeApplication.a().e == -1000 ? this.a.g() : true) {
            try {
                String strC = a.a().c(this.b.toJson());
                if (TextUtils.isEmpty(strC)) {
                    receiveMsg = null;
                } else {
                    receiveMsg = (ReceiveMsg) ResolveJson.resolveNormalInfo(strC, ReceiveMsg.class);
                    try {
                        if (!ResolveJson.checkRval(receiveMsg, this.b.getMsg_id())) {
                            receiveMsg = (ReceiveMsg) ResolveJson.resolveNormalInfo(a.a().c(this.b.toJson()), ReceiveMsg.class);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                receiveMsg = null;
                e3.printStackTrace();
            }
        }
        if (this.c != null) {
            this.c.a(receiveMsg);
        }
    }
}