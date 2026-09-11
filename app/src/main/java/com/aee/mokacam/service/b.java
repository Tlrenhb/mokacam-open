package com.aee.mokacam.service;

/* JADX INFO: loaded from: classes.dex */
class b implements Runnable {
    final /* synthetic */ a a;
    private final /* synthetic */ n b;

    b(a aVar, n nVar) {
        this.a = aVar;
        this.b = nVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean zG = this.a.g();
        if (this.b != null) {
            this.b.a(Boolean.valueOf(zG));
        }
    }
}
