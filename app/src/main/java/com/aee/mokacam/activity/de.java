package com.aee.mokacam.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class de extends ArrayAdapter<String> {
    final /* synthetic */ SupportActivity a;
    private final /* synthetic */ List b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    de(SupportActivity supportActivity, Context context, int i, List list, List list2) {
        super(context, i, list);
        this.a = supportActivity;
        this.b = list2;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(this.a);
        textView.setTextSize(16.0f);
        textView.setPadding(com.aee.mokacam.utils.d.a(this.a, 15.0f), com.aee.mokacam.utils.d.a(this.a, 10.0f), 0, com.aee.mokacam.utils.d.a(this.a, 10.0f));
        textView.setText((CharSequence) this.b.get(i));
        return textView;
    }
}
