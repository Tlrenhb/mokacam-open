package com.aee.mokacam.activity;

import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.constants.AeeConstants;

/* JADX INFO: loaded from: classes.dex */
class c implements com.aee.mokacam.widget.f {
    final /* synthetic */ AeeAppSettingActivity a;
    final /* synthetic */ com.aee.mokacam.widget.e b;

    c(AeeAppSettingActivity aeeAppSettingActivity, com.aee.mokacam.widget.e eVar) {
        this.a = aeeAppSettingActivity;
        this.b = eVar;
    }

    @Override // com.aee.mokacam.widget.f
    public void a() {
        this.b.dismiss();
        AeeApplication.a().UpdateInit(AeeConstants.f);
        this.a.c();
    }

    @Override // com.aee.mokacam.widget.f
    public void b() {
        this.b.dismiss();
    }
}
