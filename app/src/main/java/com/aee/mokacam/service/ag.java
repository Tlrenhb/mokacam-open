package com.aee.mokacam.service;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
class ag extends Handler {
    final /* synthetic */ UpdateManager a;

    ag(UpdateManager updateManager) {
        this.a = updateManager;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.a.f.setProgress(this.a.c);
                this.a.h.setText(String.valueOf(this.a.c) + "%");
                break;
            case 2:
                this.a.e();
                break;
        }
    }
}
