package com.aee.mokacam.activity;

import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;

/* JADX INFO: loaded from: classes.dex */
class ab implements com.aee.mokacam.service.n {
    final /* synthetic */ y a;

    ab(y yVar) {
        this.a = yVar;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        ReceiveMsg receiveMsg = (ReceiveMsg) obj;
        if (receiveMsg == null || receiveMsg.getRval() < 0) {
            com.aee.mokacam.utils.w.a(R.string.set_failed, true);
            return;
        }
        com.aee.mokacam.service.a.a().f();
        AeeApplication.a().f = false;
        AeeApplication.a().d();
    }
}
