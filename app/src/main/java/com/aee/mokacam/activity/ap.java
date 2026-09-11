package com.aee.mokacam.activity;

import com.aee.mokacam.R;
import com.aee.mokacam.bean.ReceiveMsg;

/* JADX INFO: loaded from: classes.dex */
class ap implements com.aee.mokacam.service.n {
    final /* synthetic */ AeeCameraWifiConfigActivity a;

    ap(AeeCameraWifiConfigActivity aeeCameraWifiConfigActivity) {
        this.a = aeeCameraWifiConfigActivity;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        if (((ReceiveMsg) obj) != null) {
            com.aee.mokacam.utils.w.a(R.string.wifi_restart, true);
        } else {
            com.aee.mokacam.utils.w.a(R.string.failed, true);
        }
    }
}
