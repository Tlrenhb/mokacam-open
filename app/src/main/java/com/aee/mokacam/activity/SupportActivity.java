package com.aee.mokacam.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.aee.mokacam.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class SupportActivity extends BaseActivity {
    private ImageView a;
    private ListView b;
    private ListView c;
    private Dialog d;

    private void a() {
        this.a = (ImageView) findViewById(R.id.iv_support_back);
        this.b = (ListView) findViewById(R.id.support_listview1);
        this.c = (ListView) findViewById(R.id.support_listview2);
        this.b.setDivider(new ColorDrawable(Color.parseColor("#ADAAAD")));
        this.b.setDividerHeight(1);
        this.c.setDivider(new ColorDrawable(Color.parseColor("#ADAAAD")));
        this.c.setDividerHeight(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(TextView textView) {
        String str = textView.getText().toString().split("\\+")[1];
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:" + str));
        startActivity(intent);
    }

    private void a(TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        textView.setOnClickListener(new dh(this, textView));
        textView2.setOnClickListener(new di(this, textView2));
        textView3.setOnClickListener(new dj(this, textView3));
        textView4.setOnClickListener(new dk(this));
    }

    private void b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getResources().getString(R.string.support_camera));
        arrayList.add(getResources().getString(R.string.support_app));
        this.b.setAdapter((ListAdapter) new dl(this, arrayList));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(getResources().getString(R.string.support_contact));
        this.c.setAdapter((ListAdapter) new de(this, this, android.R.layout.simple_list_item_1, arrayList2, arrayList2));
    }

    private void c() {
        this.a.setOnClickListener(this);
        this.b.setOnItemClickListener(new df(this));
        this.c.setOnItemClickListener(new dg(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        View viewInflate = View.inflate(this, R.layout.contactdialog_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(viewInflate);
        this.d = builder.create();
        Window window = this.d.getWindow();
        window.setGravity(80);
        window.setWindowAnimations(R.style.DeleteDialogStyle);
        this.d.show();
        a((TextView) viewInflate.findViewById(R.id.tv_contact_one), (TextView) viewInflate.findViewById(R.id.tv_contact_two), (TextView) viewInflate.findViewById(R.id.tv_contact_three), (TextView) viewInflate.findViewById(R.id.tv_cancelcontact));
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_support_back /* 2131427652 */:
                finish();
                break;
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_support);
        a();
        b();
        c();
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
