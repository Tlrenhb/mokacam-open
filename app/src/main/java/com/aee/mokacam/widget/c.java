package com.aee.mokacam.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.aee.mokacam.R;
import java.text.NumberFormat;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class c extends AlertDialog {
    ProgressBar a;
    TextView b;
    TextView c;
    Handler d;
    String e;
    NumberFormat f;
    int g;
    int h;
    boolean i;
    ImageView j;
    Animation k;

    public c(Context context) {
        super(context);
        a(context);
    }

    void a() {
        this.j = (ImageView) findViewById(R.id.deleting_img);
        this.a = (ProgressBar) findViewById(R.id.pb_delete);
        this.b = (TextView) findViewById(R.id.tv_progress_precent);
        this.c = (TextView) findViewById(R.id.tv_delete_number);
        this.j.startAnimation(this.k);
        this.d = new d(this);
        if (this.g > 0) {
            a(this.g);
        }
        if (this.h > 0) {
            b(this.h);
        }
        b();
    }

    void a(Context context) {
        this.e = "%1d/%2d";
        this.f = NumberFormat.getPercentInstance();
        this.f.setMaximumFractionDigits(0);
        this.k = AnimationUtils.loadAnimation(context, R.anim.load_animation);
    }

    void b() {
        if (this.d == null || this.d.hasMessages(0)) {
            return;
        }
        this.d.sendEmptyMessage(0);
    }

    public void a(int i) {
        this.g = i;
        if (this.a == null) {
            this.g = i;
        } else {
            this.a.setMax(i);
            b();
        }
    }

    public void b(int i) {
        if (i == 0 && this.a != null) {
            this.b.setText("0%");
        }
        if (!this.i) {
            this.h = i;
            return;
        }
        this.a.setProgress(this.g - i);
        if (this.e != null) {
            this.c.setText(String.format(this.e, Integer.valueOf(this.g - i), Integer.valueOf(this.g)));
        } else {
            this.c.setText(BuildConfig.FLAVOR);
        }
        if (this.f == null) {
            this.b.setText(BuildConfig.FLAVOR);
            return;
        }
        SpannableString spannableString = new SpannableString(this.f.format(1.0d - (((double) (this.g - i)) / ((double) this.g))));
        spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 33);
        this.b.setText(spannableString);
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_myprogress_dialog);
        a();
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        this.i = true;
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        this.i = false;
    }
}
