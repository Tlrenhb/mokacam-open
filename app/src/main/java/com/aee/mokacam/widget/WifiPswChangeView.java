package com.aee.mokacam.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
public class WifiPswChangeView extends LinearLayout {
    Context a;
    View b;
    TextView c;
    TextView d;
    EditText e;
    EditText f;

    public WifiPswChangeView(Context context) {
        super(context);
    }

    public WifiPswChangeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        a();
    }

    public WifiPswChangeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    void a() {
        this.b = View.inflate(this.a, R.layout.wifi_pswchange_page, this);
        this.c = (TextView) this.b.findViewById(R.id.tv_products_title);
        this.d = (TextView) this.b.findViewById(R.id.tv_product_model);
        this.e = (EditText) this.b.findViewById(R.id.et_newpsw);
        this.f = (EditText) this.b.findViewById(R.id.et_confirmpsw);
    }

    public String getConfirmPassword() {
        return this.f.getText().toString();
    }

    public String getNewPassword() {
        return this.e.getText().toString();
    }

    public void setProductModel(String str) {
        this.d.setText(str);
    }

    public void setProductTitle(int i) {
        this.c.setText(i);
    }
}
