package com.aee.mokacam.service;

import android.os.Handler;
import android.os.Message;
import com.aee.mokacam.bean.ReceiveMsg;

/* JADX INFO: loaded from: classes.dex */
class l implements n {
    final /* synthetic */ a a;
    final /* synthetic */ Handler b;

    l(a aVar, Handler handler) {
        this.a = aVar;
        this.b = handler;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        ReceiveMsg receiveMsg = (ReceiveMsg) obj;
        Message messageObtainMessage = this.b.obtainMessage();
        messageObtainMessage.what = 101;
        if (receiveMsg != null) {
            messageObtainMessage.arg1 = receiveMsg.getRval();
            this.b.sendMessage(messageObtainMessage);
        }
    }
}
