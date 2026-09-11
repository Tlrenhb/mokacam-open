package com.aee.mokacam.activity;

import com.aee.mokacam.bean.ReceiveMsg;

/* JADX INFO: loaded from: classes.dex */
class t implements com.aee.mokacam.service.n {
    final /* synthetic */ AeeCameraPlaybackActivity a;

    t(AeeCameraPlaybackActivity aeeCameraPlaybackActivity) {
        this.a = aeeCameraPlaybackActivity;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        if (((ReceiveMsg) obj) != null) {
            this.a.a = true;
        } else {
            this.a.a = false;
        }
    }
}
