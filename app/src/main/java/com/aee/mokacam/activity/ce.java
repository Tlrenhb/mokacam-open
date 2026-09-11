package com.aee.mokacam.activity;

/* JADX INFO: loaded from: classes.dex */
class ce extends Thread {
    final /* synthetic */ SelectLibraryActivity a;

    ce(SelectLibraryActivity selectLibraryActivity) {
        this.a = selectLibraryActivity;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        this.a.runOnUiThread(new cf(this, this.a.a(this.a.b(this.a.f, 0))));
    }
}
