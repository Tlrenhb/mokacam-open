package com.aee.mokacam.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.service.UpdateManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ProductActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    ListView a;
    UpdateManager b;
    List<com.aee.mokacam.bean.b> c = new ArrayList();
    com.aee.mokacam.bean.b d;
    com.aee.mokacam.bean.b e;
    com.aee.mokacam.bean.b f;
    com.aee.mokacam.bean.b g;

    void a() {
        new Thread(new bs(this)).start();
    }

    void b() {
        this.a = (ListView) findViewById(R.id.list_product);
        this.a.setDivider(new ColorDrawable(0));
        this.a.setDividerHeight(0);
        c();
    }

    void c() {
        this.c.clear();
        this.d = new com.aee.mokacam.bean.b(R.drawable.btn_drones_selector);
        this.c.add(this.d);
        this.e = new com.aee.mokacam.bean.b(R.drawable.btn_a10_selector);
        this.c.add(this.e);
        this.g = new com.aee.mokacam.bean.b(R.drawable.btn_hand_gimbals_selector);
        this.c.add(this.g);
        this.f = new com.aee.mokacam.bean.b(R.drawable.btn_cams_selector);
        this.c.add(this.f);
        this.a.setAdapter((ListAdapter) new com.aee.mokacam.a.a(getBaseContext(), R.layout.product_item, this.c));
        this.a.setOnItemClickListener(this);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_products);
        b();
        a();
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityHoneycomb, android.app.Activity, android.view.LayoutInflater.Factory2
    public /* bridge */ /* synthetic */ View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(view, str, context, attributeSet);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity, android.view.LayoutInflater.Factory
    public /* bridge */ /* synthetic */ View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        if (AeeApplication.a().a == 1) {
            com.aee.mokacam.service.v.a().b();
        }
        super.onDestroy();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Intent intent = new Intent(getBaseContext(), (Class<?>) MainActivity.class);
        if (this.c.get(i).equals(this.d)) {
            intent.putExtra("productName", 1);
        } else if (this.c.get(i).equals(this.e)) {
            intent.putExtra("productName", 2);
        } else if (this.c.get(i).equals(this.g)) {
            intent.putExtra("productName", 3);
        } else {
            intent.putExtra("productName", 4);
        }
        startActivity(intent);
    }
}
