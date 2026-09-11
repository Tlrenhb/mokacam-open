package com.aee.mokacam.activity;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class bv implements View.OnClickListener {
    final /* synthetic */ RegisterActivity a;

    bv(RegisterActivity registerActivity) {
        this.a = registerActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.a.a(2, false);
    }
}
