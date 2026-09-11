package com.aee.mokacam.activity;

import com.aee.mokacam.R;
import com.aee.mokacam.bean.ReceiveMsg;

/* JADX INFO: loaded from: classes.dex */
class i implements com.aee.mokacam.service.n {
    final /* synthetic */ AeeCameraActivty a;

    i(AeeCameraActivty aeeCameraActivty) {
        this.a = aeeCameraActivty;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        if (((ReceiveMsg) obj) == null) {
            com.aee.mokacam.utils.w.a(R.string.set_failed, true);
        }
    }
}
