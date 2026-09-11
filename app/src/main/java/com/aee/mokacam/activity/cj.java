package com.aee.mokacam.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
class cj extends ArrayAdapter<String> {
    final /* synthetic */ SelectSimpleActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    cj(SelectSimpleActivity selectSimpleActivity, Context context, int i, String[] strArr) {
        super(context, i, strArr);
        this.a = selectSimpleActivity;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(this.a);
        textView.setTextColor(-1);
        textView.setTextSize(18.0f);
        textView.setPadding(15, 15, 0, 15);
        textView.setText(this.a.a[i]);
        return textView;
    }
}
