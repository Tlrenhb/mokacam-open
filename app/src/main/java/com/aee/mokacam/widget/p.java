package com.aee.mokacam.widget;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
public class p extends Dialog {
    int a;
    int b;
    TextView c;
    TextView d;
    TextView e;
    TextView f;

    public p(Context context, View.OnClickListener onClickListener, int i, int i2, int i3) {
        super(context, R.style.TransparentDialog);
        setCanceledOnTouchOutside(false);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.dialog_updateapp, (ViewGroup) null);
        ((LinearLayout) viewInflate.findViewById(R.id.dialog_view)).setVisibility(8);
        this.c = (TextView) viewInflate.findViewById(R.id.title);
        this.c.setText(i2);
        this.d = (TextView) viewInflate.findViewById(R.id.info);
        this.d.setText(i3);
        this.e = (TextView) viewInflate.findViewById(R.id.dialog_button_cancel);
        this.f = (TextView) viewInflate.findViewById(R.id.dialog_button_ok);
        switch (i) {
            case 0:
                this.e.setVisibility(8);
                this.f.setVisibility(8);
                break;
            case 1:
                this.e.setVisibility(0);
                this.f.setVisibility(0);
                break;
            case 2:
                this.e.setVisibility(8);
                this.f.setVisibility(0);
                break;
            case 3:
                this.e.setVisibility(0);
                this.f.setVisibility(8);
                break;
            default:
                this.e.setVisibility(8);
                this.f.setVisibility(8);
                break;
        }
        this.e.setOnClickListener(onClickListener);
        this.f.setOnClickListener(onClickListener);
        setContentView(viewInflate);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        a(context);
        if (this.a < this.b) {
            attributes.width = (this.a * 8) / 10;
        } else {
            attributes.width = (this.a * 5) / 10;
        }
        attributes.height = -2;
        attributes.gravity = 17;
        window.setAttributes(attributes);
        show();
    }

    public p(Context context, View.OnClickListener onClickListener, View view, int i, int i2) {
        super(context, R.style.TransparentDialog);
        setCanceledOnTouchOutside(false);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.dialog_updateapp, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.dialog_view);
        viewInflate.findViewById(R.id.view_divider).setVisibility(8);
        linearLayout.addView(view);
        this.c = (TextView) viewInflate.findViewById(R.id.title);
        this.c.setText(i2);
        this.d = (TextView) viewInflate.findViewById(R.id.info);
        this.d.setVisibility(8);
        this.e = (TextView) viewInflate.findViewById(R.id.dialog_button_cancel);
        this.f = (TextView) viewInflate.findViewById(R.id.dialog_button_ok);
        switch (i) {
            case 0:
                this.e.setVisibility(8);
                this.f.setVisibility(8);
                break;
            case 1:
                this.e.setVisibility(0);
                this.f.setVisibility(0);
                break;
            case 2:
                this.e.setVisibility(8);
                this.f.setVisibility(0);
                break;
            case 3:
                this.e.setVisibility(0);
                this.f.setVisibility(8);
                break;
            default:
                this.e.setVisibility(8);
                this.f.setVisibility(8);
                break;
        }
        this.e.setOnClickListener(onClickListener);
        this.f.setOnClickListener(onClickListener);
        setContentView(viewInflate);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        a(context);
        if (this.a < this.b) {
            attributes.width = (this.a * 8) / 10;
        } else {
            attributes.width = (this.a * 5) / 10;
        }
        attributes.height = -2;
        attributes.gravity = 17;
        window.setAttributes(attributes);
        show();
    }

    void a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.a = displayMetrics.widthPixels;
        this.b = displayMetrics.heightPixels;
    }

    public void a(int i) {
        if (this.e != null) {
            this.e.setText(i);
        }
    }

    public void a(String str) {
        if (this.d != null) {
            this.d.setTextColor(-1);
        }
        this.d.setText(str);
    }

    public void b(int i) {
        if (this.f != null) {
            this.f.setText(i);
        }
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        if (this.c != null) {
            this.c.setText(i);
        }
    }
}
