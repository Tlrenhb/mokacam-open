package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
class d implements n {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        AeeApplication.a().ba = ((Boolean) obj).booleanValue();
    }
}
