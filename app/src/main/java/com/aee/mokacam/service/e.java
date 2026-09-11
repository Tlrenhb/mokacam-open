package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;

/* JADX INFO: loaded from: classes.dex */
class e implements n {
    final /* synthetic */ a a;

    e(a aVar) {
        this.a = aVar;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        ReceiveMsg receiveMsg = (ReceiveMsg) obj;
        if (receiveMsg == null || receiveMsg.getRval() < 0) {
            AeeApplication.a().bf = false;
        } else {
            AeeApplication.a().bf = true;
        }
    }
}
