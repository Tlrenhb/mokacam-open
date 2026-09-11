package com.aee.mokacam.activity;

/* JADX INFO: loaded from: classes.dex */
class cg extends Thread {
    final /* synthetic */ SelectLibraryActivity a;

    cg(SelectLibraryActivity selectLibraryActivity) {
        this.a = selectLibraryActivity;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        this.a.a(this.a.g, 0);
    }
}
