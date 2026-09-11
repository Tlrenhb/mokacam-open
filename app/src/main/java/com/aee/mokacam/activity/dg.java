package com.aee.mokacam.activity;

import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: loaded from: classes.dex */
class dg implements AdapterView.OnItemClickListener {
    final /* synthetic */ SupportActivity a;

    dg(SupportActivity supportActivity) {
        this.a = supportActivity;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        switch (i) {
            case 0:
                this.a.d();
                break;
        }
    }
}
