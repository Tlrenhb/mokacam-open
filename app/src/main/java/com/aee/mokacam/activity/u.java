package com.aee.mokacam.activity;

import com.aee.mokacam.bean.ReceiveMsg;

/* JADX INFO: loaded from: classes.dex */
class u implements com.aee.mokacam.service.n {
    final /* synthetic */ AeeCameraPlaybackActivity a;

    u(AeeCameraPlaybackActivity aeeCameraPlaybackActivity) {
        this.a = aeeCameraPlaybackActivity;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        Object listing;
        ReceiveMsg receiveMsg = (ReceiveMsg) obj;
        if (receiveMsg == null || (listing = receiveMsg.getListing()) == null) {
            return;
        }
        com.aee.mokacam.bean.g.a(listing);
    }
}
