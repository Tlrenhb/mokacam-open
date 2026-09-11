package com.aee.mokacam.activity;

import android.os.Message;
import com.aee.mokacam.bean.ReceiveMsg;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
class cc implements com.aee.mokacam.service.n {
    final /* synthetic */ SelectLibraryActivity a;

    cc(SelectLibraryActivity selectLibraryActivity) {
        this.a = selectLibraryActivity;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        ReceiveMsg receiveMsg = (ReceiveMsg) obj;
        if (receiveMsg == null || receiveMsg.getRval() != 0) {
            this.a.a = false;
            this.a.i.sendEmptyMessage(3);
            return;
        }
        Object listing = receiveMsg.getListing();
        this.a.a = true;
        Message messageObtainMessage = this.a.i.obtainMessage();
        messageObtainMessage.what = 2;
        messageObtainMessage.obj = listing;
        try {
            TimeUnit.MILLISECONDS.sleep(300L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.a.i.sendMessage(messageObtainMessage);
    }
}
