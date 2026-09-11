package com.aee.mokacam.service;

/* JADX INFO: loaded from: classes.dex */
class p implements Runnable {
    final /* synthetic */ o a;

    p(o oVar) {
        this.a = oVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.k = true;
    }
}
