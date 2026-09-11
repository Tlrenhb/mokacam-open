package com.aee.mokacam.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.aee.mokacam.R;
import com.aee.mokacam.widget.NoScrollViewPager;

/* JADX INFO: loaded from: classes.dex */
public class RegisterActivity extends BaseActivity {
    private NoScrollViewPager a;
    private View b;
    private View c;
    private View d;
    private TextView e;
    private TextView f;
    private TextView g;
    private ImageView h;
    private int k = 0;

    private void g() {
        this.a = (NoScrollViewPager) findViewById(R.id.vp_register);
        this.h = (ImageView) findViewById(R.id.iv_register_back);
    }

    private void h() {
        this.a.setAdapter(new by(this, null));
    }

    private void i() {
        this.h.setOnClickListener(this);
    }

    public void a() {
        this.e = (TextView) this.b.findViewById(R.id.tv_next);
    }

    public void b() {
        this.f = (TextView) this.c.findViewById(R.id.tv_submit);
    }

    public void c() {
        this.g = (TextView) this.d.findViewById(R.id.tv_go);
    }

    public void d() {
        if (this.f == null) {
            return;
        }
        this.f.setOnClickListener(new bv(this));
    }

    public void e() {
        if (this.g == null) {
            return;
        }
        this.g.setOnClickListener(new bw(this));
    }

    public void f() {
        this.e.setOnClickListener(new bx(this));
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_register_back /* 2131427618 */:
                if (this.k == 0) {
                    finish();
                } else if (this.k == 1) {
                    this.a.setCurrentItem(0);
                }
                break;
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_register);
        g();
        h();
        i();
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityHoneycomb, android.app.Activity, android.view.LayoutInflater.Factory2
    public /* bridge */ /* synthetic */ View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(view, str, context, attributeSet);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity, android.view.LayoutInflater.Factory
    public /* bridge */ /* synthetic */ View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || this.k != 1) {
            return super.onKeyDown(i, keyEvent);
        }
        this.a.setCurrentItem(0);
        return false;
    }
}
