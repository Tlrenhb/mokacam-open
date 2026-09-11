package com.aee.mokacam.activity;

/* JADX INFO: loaded from: classes.dex */
class z implements com.aee.mokacam.service.n {
    final /* synthetic */ y a;

    z(y yVar) {
        this.a = yVar;
    }

    @Override // com.aee.mokacam.service.n
    public void a(Object obj) {
        this.a.a.i.post(new aa(this));
        this.a.a.i.sendEmptyMessageDelayed(32799, 1000L);
    }
}
