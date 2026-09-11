package com.aee.mokacam.activity;

/* JADX INFO: loaded from: classes.dex */
class bj implements Runnable {
    final /* synthetic */ bh a;

    bj(bh bhVar) {
        this.a = bhVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.a.e.dismiss();
        this.a.a.i.sendEmptyMessage(32793);
    }
}
