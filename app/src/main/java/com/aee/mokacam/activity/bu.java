package com.aee.mokacam.activity;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class bu implements View.OnClickListener {
    final /* synthetic */ ProductParamsActivity a;

    bu(ProductParamsActivity productParamsActivity) {
        this.a = productParamsActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.finish();
    }
}
