package com.aee.mokacam.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.aee.mokacam.R;
import com.aee.mokacam.photoview.PhotoView;

/* JADX INFO: loaded from: classes.dex */
public class ProductParamsActivity extends BaseActivity {
    private ImageView a;
    private PhotoView b;
    private String c;

    private void a() {
        this.c = getResources().getConfiguration().locale.getLanguage();
    }

    private void b() {
        this.a = (ImageView) findViewById(R.id.iv_params_back);
        this.b = (PhotoView) findViewById(R.id.pv_params);
    }

    private void c() {
        if (this.c.equals("zh")) {
            this.b.setImageResource(R.drawable.lyfe_titan_zh);
        } else if (this.c.equals("en")) {
            this.b.setImageResource(R.drawable.lyfe_titan);
        } else {
            this.b.setImageResource(R.drawable.lyfe_titan);
        }
    }

    private void d() {
        this.a.setOnClickListener(new bu(this));
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_product_params);
        a();
        b();
        c();
        d();
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityHoneycomb, android.app.Activity, android.view.LayoutInflater.Factory2
    public /* bridge */ /* synthetic */ View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(view, str, context, attributeSet);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity, android.view.LayoutInflater.Factory
    public /* bridge */ /* synthetic */ View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }
}
