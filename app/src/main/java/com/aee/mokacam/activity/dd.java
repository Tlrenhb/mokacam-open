package com.aee.mokacam.activity;

import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
class dd implements Runnable {
    final /* synthetic */ dc a;

    dd(dc dcVar) {
        this.a = dcVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.a.startActivity(new Intent(this.a.a, (Class<?>) MainActivity.class));
        this.a.a.finish();
    }
}
