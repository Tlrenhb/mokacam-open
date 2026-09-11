package com.aee.mokacam.activity;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class ax implements View.OnClickListener {
    final /* synthetic */ LegalActivity a;

    ax(LegalActivity legalActivity) {
        this.a = legalActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.finish();
    }
}
