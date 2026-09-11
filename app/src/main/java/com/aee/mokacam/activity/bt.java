package com.aee.mokacam.activity;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
class bt extends BaseExpandableListAdapter {
    final /* synthetic */ ProductInSupportActivity a;

    private bt(ProductInSupportActivity productInSupportActivity) {
        this.a = productInSupportActivity;
    }

    /* synthetic */ bt(ProductInSupportActivity productInSupportActivity, bt btVar) {
        this(productInSupportActivity);
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i, int i2) {
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return 0L;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(this.a);
        textView.setText((CharSequence) this.a.d.get(Integer.valueOf(i)));
        textView.setTextSize(14.0f);
        textView.setBackgroundColor(-1);
        textView.setPadding(com.aee.mokacam.utils.d.a(this.a, 15.0f), com.aee.mokacam.utils.d.a(this.a, 10.0f), com.aee.mokacam.utils.d.a(this.a, 15.0f), com.aee.mokacam.utils.d.a(this.a, 10.0f));
        return textView;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i) {
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.a.b.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return 0L;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(this.a, R.layout.item_expandlistview_parent, null);
        }
        TextView textView = (TextView) view.findViewById(R.id.tv_parent);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_parent);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_itemgroup);
        textView.setText((CharSequence) this.a.b.get(i));
        if (z) {
            imageView.setImageResource(R.drawable.pack_up);
            relativeLayout.setBackgroundColor(Color.parseColor("#cccccc"));
        } else {
            imageView.setImageResource(R.drawable.unfold);
            relativeLayout.setBackgroundColor(-1);
        }
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }
}
