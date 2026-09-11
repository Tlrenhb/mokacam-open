package com.aee.mokacam.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.aee.mokacam.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class dl extends BaseAdapter {
    final /* synthetic */ SupportActivity a;
    private List<String> b;

    public dl(SupportActivity supportActivity, List<String> list) {
        this.a = supportActivity;
        this.b = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.b.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewInflate = View.inflate(this.a, R.layout.item_supportactivity, null);
        ((TextView) viewInflate.findViewById(R.id.tv_item_support)).setText(this.b.get(i));
        return viewInflate;
    }
}
