package com.zcw.togglebutton;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class b implements View.OnClickListener {
    final /* synthetic */ ToggleButton a;

    b(ToggleButton toggleButton) {
        this.a = toggleButton;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.a(this.a.v);
    }
}
