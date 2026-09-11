package com.aee.mokacam.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
public class SettingsItemViewWithArrow extends RelativeLayout {
    private Context a;
    private String b;
    private String c;
    private TextView d;
    private TextView e;
    private ImageView f;

    public SettingsItemViewWithArrow(Context context) {
        this(context, null);
    }

    public SettingsItemViewWithArrow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SettingsItemViewWithArrow);
        this.b = typedArrayObtainStyledAttributes.getString(0);
        this.c = typedArrayObtainStyledAttributes.getString(1);
        typedArrayObtainStyledAttributes.recycle();
        a();
    }

    public SettingsItemViewWithArrow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void a() {
        View viewInflate = View.inflate(this.a, R.layout.item_settings_arrow, this);
        this.d = (TextView) viewInflate.findViewById(R.id.item_left);
        this.e = (TextView) viewInflate.findViewById(R.id.item_right);
        this.f = (ImageView) viewInflate.findViewById(R.id.item_arrow);
        this.d.setText(this.b);
        this.e.setText(this.c);
    }

    public String getRightText() {
        return this.e.getText().toString().trim();
    }

    public void setLeftText(int i) {
        this.d.setText(i);
    }

    public void setRightText(int i) {
        this.e.setText(i);
    }

    public void setRightText(String str) {
        this.e.setText(str);
    }

    public void setTextColor(int i) {
        this.d.setTextColor(i);
        this.e.setTextColor(i);
        this.f.setImageResource(R.drawable.expand_gray);
    }
}
