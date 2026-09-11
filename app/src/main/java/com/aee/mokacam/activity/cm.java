package com.aee.mokacam.activity;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class cm implements View.OnClickListener {
    final /* synthetic */ ShowPicOrVideoActivity a;

    cm(ShowPicOrVideoActivity showPicOrVideoActivity) {
        this.a = showPicOrVideoActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.a.X.isShown()) {
            this.a.X.startAnimation(this.a.y);
            this.a.Y.startAnimation(this.a.A);
            this.a.X.setVisibility(8);
            this.a.Y.setVisibility(8);
            return;
        }
        this.a.X.startAnimation(this.a.x);
        this.a.Y.startAnimation(this.a.z);
        this.a.X.setVisibility(0);
        this.a.Y.setVisibility(0);
    }
}
