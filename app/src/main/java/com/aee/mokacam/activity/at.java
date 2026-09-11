package com.aee.mokacam.activity;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class at implements View.OnClickListener {
    final /* synthetic */ as a;
    private final /* synthetic */ int b;

    at(as asVar, int i) {
        this.a = asVar;
        this.b = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (((com.aee.mokacam.bean.g) this.a.a.c.get(this.b)).g() == 2) {
            ((com.aee.mokacam.bean.g) this.a.a.c.get(this.b)).e.cancel();
            ((com.aee.mokacam.bean.g) this.a.a.c.get(this.b)).e = null;
            this.a.a.a(this.b);
        } else if (((com.aee.mokacam.bean.g) this.a.a.c.get(this.b)).g() == 1) {
            ((com.aee.mokacam.bean.g) this.a.a.c.get(this.b)).a(6);
            ((com.aee.mokacam.bean.g) this.a.a.c.get(this.b)).e.cancel();
            this.a.a.d.notifyDataSetChanged();
        }
    }
}
