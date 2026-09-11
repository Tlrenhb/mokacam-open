package com.facebook.rebound.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class d extends BaseAdapter {
    final /* synthetic */ SpringConfiguratorView a;
    private final Context b;
    private final List<String> c = new ArrayList();

    public d(SpringConfiguratorView springConfiguratorView, Context context) {
        this.a = springConfiguratorView;
        this.b = context;
    }

    public void a() {
        this.c.clear();
        notifyDataSetChanged();
    }

    public void a(String str) {
        this.c.add(str);
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.c.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.c.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if (view == null) {
            textView = new TextView(this.b);
            textView.setLayoutParams(new AbsListView.LayoutParams(-1, -1));
            int iA = f.a(12.0f, this.a.getResources());
            textView.setPadding(iA, iA, iA, iA);
            textView.setTextColor(this.a.h);
        } else {
            textView = (TextView) view;
        }
        textView.setText(this.c.get(i));
        return textView;
    }
}
