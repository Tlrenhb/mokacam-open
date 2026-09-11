package com.aee.mokacam.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import com.aee.mokacam.R;
import com.aee.mokacam.bean.SendMsg;

/* JADX INFO: loaded from: classes.dex */
public class AeeCameraWifiConfigActivity extends BaseActivity {
    private EditText a;
    private EditText b;
    private EditText c;

    private void a() {
        this.a = (EditText) findViewById(R.id.new_ssid);
        this.b = (EditText) findViewById(R.id.new_password);
        this.c = (EditText) findViewById(R.id.confirm_password);
    }

    private void b() {
        String string = this.a.getText().toString();
        String string2 = this.b.getText().toString();
        String string3 = this.c.getText().toString();
        if (TextUtils.isEmpty(string)) {
            com.aee.mokacam.utils.w.a(R.string.new_ssid_is_empty, true);
            return;
        }
        if (TextUtils.isEmpty(string2)) {
            com.aee.mokacam.utils.w.a(R.string.new_password_is_empty, true);
            return;
        }
        if (TextUtils.isEmpty(string3)) {
            com.aee.mokacam.utils.w.a(R.string.confirm_password_is_empty, true);
            return;
        }
        if (string2.length() < 8 || string3.length() < 8) {
            com.aee.mokacam.utils.w.a(R.string.password_is_too_short, true);
        } else if (!TextUtils.equals(string3, string2)) {
            com.aee.mokacam.utils.w.a(R.string.password_is_not_consistent, true);
        } else {
            com.aee.mokacam.service.a.a().a(new ap(this), new SendMsg(2049, String.valueOf(string) + "$" + string2, null));
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.save /* 2131427433 */:
                b();
                break;
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_aeecamera_wificonfig);
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
}
