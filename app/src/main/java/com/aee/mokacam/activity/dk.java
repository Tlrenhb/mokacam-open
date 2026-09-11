package com.aee.mokacam.activity;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class dk implements View.OnClickListener {
    final /* synthetic */ SupportActivity a;

    dk(SupportActivity supportActivity) {
        this.a = supportActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.d.dismiss();
    }
}
