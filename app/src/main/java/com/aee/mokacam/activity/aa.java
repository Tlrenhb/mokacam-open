package com.aee.mokacam.activity;

/* JADX INFO: loaded from: classes.dex */
class aa implements Runnable {
    final /* synthetic */ z a;

    aa(z zVar) {
        this.a = zVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.a.a.O.cancel();
    }
}
