package com.aee.mokacam.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
class df implements AdapterView.OnItemClickListener {
    final /* synthetic */ SupportActivity a;

    df(SupportActivity supportActivity) {
        this.a = supportActivity;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i != 0) {
            com.aee.mokacam.utils.w.a(R.string.coming_soon, true);
        } else {
            this.a.startActivity(new Intent(this.a, (Class<?>) ProductInSupportActivity.class));
        }
    }
}
