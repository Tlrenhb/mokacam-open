package com.aee.mokacam.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
public class e extends Dialog implements View.OnClickListener {
    private boolean a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private f f;

    public e(Context context) {
        this(context, R.style.TransparentDialog);
    }

    public e(Context context, int i) {
        super(context, i);
    }

    public e(Context context, boolean z) {
        this(context);
        this.a = z;
    }

    private void a() {
        this.b = (ImageView) findViewById(R.id.icon_iv);
        this.c = (TextView) findViewById(R.id.warn_tv);
        this.d = (TextView) findViewById(R.id.sure_tv);
        this.e = (TextView) findViewById(R.id.cancel_tv);
        a(R.drawable.icon_circlewarning);
    }

    private void b() {
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    public void a(int i) {
        if (this.a) {
            this.b.setVisibility(0);
            this.b.setImageResource(i);
        }
    }

    public void a(f fVar) {
        this.f = fVar;
    }

    public void a(String str) {
        this.c.setText(str);
    }

    public void b(int i) {
        this.c.setText(i);
    }

    public void c(int i) {
        this.d.setText(i);
    }

    public void d(int i) {
        this.e.setText(i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sure_tv /* 2131427704 */:
                if (this.f != null) {
                    this.f.a();
                }
                break;
            case R.id.cancel_tv /* 2131427705 */:
                if (this.f != null) {
                    this.f.b();
                }
                break;
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_warning);
        a();
        b();
    }
}
