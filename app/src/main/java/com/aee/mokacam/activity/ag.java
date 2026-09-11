package com.aee.mokacam.activity;

import android.os.Message;
import com.aee.mokacam.R;
import com.aee.mokacam.bean.ReceiveMsg;

/* JADX INFO: loaded from: classes.dex */
class ag implements com.aee.mokacam.service.n {
    final /* synthetic */ AeeCameraSettingActivity a;
    private final /* synthetic */ int b;
    private final /* synthetic */ String c;

    ag(AeeCameraSettingActivity aeeCameraSettingActivity, int i, String str) {
        this.a = aeeCameraSettingActivity;
        this.b = i;
        this.c = str;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        if (((ReceiveMsg) obj) != null) {
            com.aee.mokacam.utils.w.a(R.string.set_success, true);
            this.a.f = true;
        } else {
            com.aee.mokacam.utils.w.a(R.string.set_failed, true);
            this.a.f = false;
        }
        Message messageObtainMessage = this.a.i.obtainMessage();
        messageObtainMessage.what = 111;
        messageObtainMessage.arg1 = this.b;
        messageObtainMessage.obj = this.c;
        this.a.i.sendMessage(messageObtainMessage);
    }
}
