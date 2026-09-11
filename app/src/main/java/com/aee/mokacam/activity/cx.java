package com.aee.mokacam.activity;

import com.aee.mokacam.bean.ReceiveMsg;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
class cx implements com.aee.mokacam.service.n {
    final /* synthetic */ ShowPicOrVideoActivity a;
    final /* synthetic */ String b;

    cx(ShowPicOrVideoActivity showPicOrVideoActivity, String str) {
        this.a = showPicOrVideoActivity;
        this.b = str;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        ReceiveMsg receiveMsg = (ReceiveMsg) obj;
        if (receiveMsg != null) {
            if (receiveMsg.getRval() != 0) {
                this.a.a(false, this.a.f);
                return;
            }
            if (this.b.endsWith(".JPG")) {
                File fileA = com.aee.mokacam.utils.t.a(this.a, new com.aee.mokacam.utils.z().generate(this.b));
                if (fileA.exists()) {
                    fileA.delete();
                }
            } else {
                try {
                    this.a.s.c(com.aee.mokacam.utils.o.a(this.b));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.a.a(true, this.a.f);
        }
    }
}
