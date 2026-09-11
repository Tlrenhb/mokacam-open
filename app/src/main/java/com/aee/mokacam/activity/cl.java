package com.aee.mokacam.activity;

import com.aee.mokacam.R;
import com.aee.mokacam.bean.ReceiveMsg;

/* JADX INFO: loaded from: classes.dex */
class cl implements com.aee.mokacam.service.n {
    final /* synthetic */ ck a;

    cl(ck ckVar) {
        this.a = ckVar;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        if (((ReceiveMsg) obj) != null) {
            com.aee.mokacam.utils.w.a(R.string.set_success, true);
        } else {
            com.aee.mokacam.utils.w.a(R.string.set_failed, true);
        }
    }
}
