package com.aee.mokacam.activity;

/* JADX INFO: loaded from: classes.dex */
class q implements Runnable {
    final /* synthetic */ p a;

    q(p pVar) {
        this.a = pVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a.a.y % 2 == 0) {
            this.a.a.l.setVisibility(4);
        } else {
            this.a.a.l.setVisibility(0);
        }
        this.a.a.o.setText(com.a.a.a.a.a(this.a.a.y));
    }
}
