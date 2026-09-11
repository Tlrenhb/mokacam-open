package com.aee.mokacam.activity;

import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;

/* JADX INFO: loaded from: classes.dex */
class cd implements com.aee.mokacam.service.n {
    final /* synthetic */ SelectLibraryActivity a;
    final /* synthetic */ String b;

    cd(SelectLibraryActivity selectLibraryActivity, String str) {
        this.a = selectLibraryActivity;
        this.b = str;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        Object listing;
        ReceiveMsg receiveMsg = (ReceiveMsg) obj;
        if (receiveMsg == null || (listing = receiveMsg.getListing()) == null) {
            return;
        }
        this.a.q.addAll(com.aee.mokacam.bean.g.a(listing, this.b));
        AeeApplication.a().r = this.a.q;
    }
}
