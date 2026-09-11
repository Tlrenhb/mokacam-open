package com.aee.mokacam.activity;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class cr implements View.OnClickListener {
    final /* synthetic */ ShowPicOrVideoActivity a;

    cr(ShowPicOrVideoActivity showPicOrVideoActivity) {
        this.a = showPicOrVideoActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.finish();
    }
}
