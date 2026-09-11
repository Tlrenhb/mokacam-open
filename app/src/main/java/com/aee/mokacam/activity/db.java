package com.aee.mokacam.activity;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class db implements View.OnClickListener {
    final /* synthetic */ da a;

    db(da daVar) {
        this.a = daVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.a.i();
        if (this.a.a.t != null) {
            this.a.a.t.releaseDisplay();
        }
        this.a.a.h();
        if (this.a.a.M != null) {
            this.a.a.M.cancel();
            this.a.a.M = null;
        }
        if (this.a.a.H != null) {
            this.a.a.H.cancel();
            this.a.a.H = null;
        }
        this.a.a.finish();
    }
}
