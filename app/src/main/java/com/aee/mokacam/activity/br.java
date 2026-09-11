package com.aee.mokacam.activity;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class br implements View.OnClickListener {
    final /* synthetic */ MainActivity a;

    br(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.a.q) {
            this.a.r.b();
            this.a.q = !this.a.q;
        }
    }
}
