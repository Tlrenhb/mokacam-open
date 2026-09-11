package com.aee.mokacam.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;
import com.aee.mokacam.bean.SendMsg;

/* JADX INFO: loaded from: classes.dex */
public class SelectSimpleActivity extends BaseActivity {
    protected String[] a;
    protected SendMsg b;
    protected ReceiveMsg c;
    protected boolean d = false;
    protected String[] e;
    private TextView f;
    private int g;
    private ListView h;
    private String k;

    private void a() {
        this.f = (TextView) findViewById(R.id.simple_set_title);
        this.h = (ListView) findViewById(R.id.setting_detail);
        this.h.setDivider(new ColorDrawable(-1));
        this.h.setDividerHeight(1);
        this.g = getIntent().getIntExtra("fromFlag", 0);
        new Thread(new ch(this)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.k = AeeApplication.a().bh;
        switch (this.g) {
            case 32774:
                this.f.setText(R.string.resolution);
                break;
            case 32776:
                this.f.setText(R.string.resolution);
                break;
            case 32777:
                this.f.setText(R.string.photo_shot_mode);
                this.k = com.aee.mokacam.utils.q.a("photo_shot_mode", this.k);
                break;
            case 32783:
                this.f.setText(R.string.photo_tlm);
                this.k = com.aee.mokacam.utils.q.a("photo_tlm", this.k);
                break;
            case 32784:
                this.f.setText(R.string.setup_key_tone);
                this.k = com.aee.mokacam.utils.q.a("Beep", this.k);
                break;
            case 32785:
                this.f.setText(R.string.setup_selflamp);
                this.k = com.aee.mokacam.utils.q.a("Status_LED", this.k);
                break;
            case 32788:
                this.f.setText(R.string.setup_system_type);
                break;
            case 32789:
                this.f.setText(R.string.dv_language);
                this.k = com.aee.mokacam.utils.q.a("Language", this.k);
                break;
        }
        c();
    }

    private void c() {
        this.h.setAdapter((ListAdapter) new cj(this, this, android.R.layout.simple_list_item_1, this.a));
    }

    private void d() {
        this.h.setOnItemClickListener(new ck(this));
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activty_aeecamera_selectsimple);
        a();
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
