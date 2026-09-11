package com.aee.mokacam.activity;

import android.view.View;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
class dh implements View.OnClickListener {
    final /* synthetic */ SupportActivity a;
    final /* synthetic */ TextView b;

    dh(SupportActivity supportActivity, TextView textView) {
        this.a = supportActivity;
        this.b = textView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.a(this.b);
    }
}
