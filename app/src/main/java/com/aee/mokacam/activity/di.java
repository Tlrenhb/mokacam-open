package com.aee.mokacam.activity;

import android.view.View;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
class di implements View.OnClickListener {
    final /* synthetic */ SupportActivity a;
    private final /* synthetic */ TextView b;

    di(SupportActivity supportActivity, TextView textView) {
        this.a = supportActivity;
        this.b = textView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.a(this.b);
    }
}
