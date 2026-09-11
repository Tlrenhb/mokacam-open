package com.aee.mokacam.activity;

import com.aee.mokacam.R;
import com.aee.mokacam.bean.ReceiveMsg;

/* JADX INFO: loaded from: classes.dex */
class am implements com.aee.mokacam.service.n {
    final /* synthetic */ AeeCameraSettingActivity a;

    am(AeeCameraSettingActivity aeeCameraSettingActivity) {
        this.a = aeeCameraSettingActivity;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        ReceiveMsg receiveMsg = (ReceiveMsg) obj;
        if (receiveMsg == null || receiveMsg.getRval() != 0) {
            this.a.g = false;
        } else {
            com.aee.mokacam.utils.w.a(R.string.wifi_restart, true);
            this.a.g = true;
        }
        this.a.i.sendEmptyMessage(112);
    }
}
