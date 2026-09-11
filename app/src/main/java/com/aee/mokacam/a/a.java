package com.aee.mokacam.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.aee.mokacam.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a extends ArrayAdapter<com.aee.mokacam.bean.b> {
    private int a;

    public a(Context context, int i, List<com.aee.mokacam.bean.b> list) {
        super(context, i, list);
        this.a = i;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        com.aee.mokacam.bean.b item = getItem(i);
        if (view == null) {
            view = View.inflate(getContext(), this.a, null);
            b bVar2 = new b(this);
            bVar2.a = (ImageView) view.findViewById(R.id.btn_product_image);
            view.setTag(bVar2);
            bVar = bVar2;
        } else {
            bVar = (b) view.getTag();
        }
        bVar.a.setImageResource(item.a());
        return view;
    }
}
