package com.facebook.rebound.ui;

import android.view.View;
import android.widget.AdapterView;
import com.facebook.rebound.k;

/* JADX INFO: loaded from: classes.dex */
class e implements AdapterView.OnItemSelectedListener {
    final /* synthetic */ SpringConfiguratorView a;

    private e(SpringConfiguratorView springConfiguratorView) {
        this.a = springConfiguratorView;
    }

    /* synthetic */ e(SpringConfiguratorView springConfiguratorView, e eVar) {
        this(springConfiguratorView);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.n = (k) this.a.c.get(i);
        this.a.a(this.a.n);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
