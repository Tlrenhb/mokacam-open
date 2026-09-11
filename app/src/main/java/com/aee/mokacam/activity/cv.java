package com.aee.mokacam.activity;

/* JADX INFO: loaded from: classes.dex */
class cv implements Runnable {
    final /* synthetic */ cu a;
    final /* synthetic */ String b;

    cv(cu cuVar, String str) {
        this.a = cuVar;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.a.l.setText(this.b);
    }
}
