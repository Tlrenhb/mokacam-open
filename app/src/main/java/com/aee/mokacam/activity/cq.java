package com.aee.mokacam.activity;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class cq implements View.OnClickListener {
    final /* synthetic */ ShowPicOrVideoActivity a;

    cq(ShowPicOrVideoActivity showPicOrVideoActivity) {
        this.a = showPicOrVideoActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if ("camera_lib".equals(this.a.G)) {
            this.a.n();
        } else {
            this.a.a();
        }
    }
}
