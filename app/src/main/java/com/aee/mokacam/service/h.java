package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;
import com.aee.mokacam.bean.SendMsg;
import com.aee.mokacam.utils.ResolveJson;

/* JADX INFO: loaded from: classes.dex */
class h implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ int b;
    final /* synthetic */ n c;

    h(a aVar, int i, n nVar) {
        this.a = aVar;
        this.b = i;
        this.c = nVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0052  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void run() {
        boolean z = false;
        if (AeeApplication.a().e == -1000 ? this.a.g() : true) {
            try {
                z = ResolveJson.checkRval((ReceiveMsg) ResolveJson.resolveNormalInfo(a.a().c(new SendMsg(this.b, AeeApplication.a().e, null, null).toJson()), ReceiveMsg.class), this.b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.c != null) {
            this.c.a(Boolean.valueOf(z));
        }
    }
}
