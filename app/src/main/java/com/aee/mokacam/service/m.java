package com.aee.mokacam.service;

import android.os.Handler;
import android.os.Message;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;

/* JADX INFO: loaded from: classes.dex */
class m implements n {
    final /* synthetic */ a a;
    private final /* synthetic */ Handler b;
    private final /* synthetic */ int c;

    m(a aVar, Handler handler, int i) {
        this.a = aVar;
        this.b = handler;
        this.c = i;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        ReceiveMsg receiveMsg = (ReceiveMsg) obj;
        Message messageObtainMessage = this.b.obtainMessage();
        messageObtainMessage.what = 32773;
        if (receiveMsg == null || receiveMsg.getRval() != 0) {
            if (receiveMsg != null) {
                messageObtainMessage.arg1 = receiveMsg.getRval();
                this.b.sendMessageDelayed(messageObtainMessage, 1000L);
                return;
            } else {
                messageObtainMessage.arg1 = -1;
                this.b.sendMessageDelayed(messageObtainMessage, 1000L);
                return;
            }
        }
        messageObtainMessage.arg1 = receiveMsg.getRval();
        if (this.c == 0) {
            this.b.sendMessageDelayed(messageObtainMessage, 2800L);
        } else {
            if (this.c == 1) {
                this.b.sendMessageDelayed(messageObtainMessage, 7000L);
                return;
            }
            this.b.sendMessageDelayed(messageObtainMessage, Integer.parseInt(AeeApplication.aV.substring(0, AeeApplication.aV.indexOf("s"))) * 1000);
        }
    }
}
