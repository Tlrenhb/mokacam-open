package com.aee.mokacam.activity;

import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;

/* JADX INFO: loaded from: classes.dex */
class ad implements com.aee.mokacam.service.n {
    final /* synthetic */ AeeCameraSettingActivity a;

    ad(AeeCameraSettingActivity aeeCameraSettingActivity) {
        this.a = aeeCameraSettingActivity;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        ReceiveMsg receiveMsg = (ReceiveMsg) obj;
        if (receiveMsg == null || receiveMsg.getRval() < 0) {
            return;
        }
        AeeApplication.a().e();
    }
}
