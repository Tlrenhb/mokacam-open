package com.aee.mokacam.activity;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
class e extends Handler {
    e() {
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        String str = (String) message.obj;
        if (AeeCameraActivty.t == 0) {
            if (str.contains("M")) {
                AeeCameraActivty.v = str.substring(0, str.length() - 16);
                return;
            } else {
                AeeCameraActivty.u = com.aee.mokacam.utils.a.b(str);
                AeeCameraActivty.Y.setText(AeeCameraActivty.u);
                return;
            }
        }
        if (!str.contains("M")) {
            AeeCameraActivty.u = com.aee.mokacam.utils.a.b(str);
        } else {
            AeeCameraActivty.v = str.substring(0, str.length() - 16);
            AeeCameraActivty.Y.setText(AeeCameraActivty.v);
        }
    }
}
