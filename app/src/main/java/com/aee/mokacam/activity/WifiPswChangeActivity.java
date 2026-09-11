package com.aee.mokacam.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.widget.WifiPswChangeView;

/* JADX INFO: loaded from: classes.dex */
public class WifiPswChangeActivity extends BaseActivity {
    WifiPswChangeView a;
    WifiPswChangeView b;
    ImageView c;
    TextView d;
    String e;
    String f;
    String g;
    String h;

    void a() {
        this.a = (WifiPswChangeView) findViewById(R.id.remotecontroll);
        this.b = (WifiPswChangeView) findViewById(R.id.wflightcontrol);
        this.c = (ImageView) findViewById(R.id.iv_backup);
        this.d = (TextView) findViewById(R.id.tv_finish);
    }

    void b() {
        this.a.setProductTitle(R.string.remotecontroll);
    }

    void c() {
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_backup /* 2131427655 */:
                finish();
                break;
            case R.id.tv_finish /* 2131427656 */:
                this.f = this.b.getConfirmPassword();
                this.e = this.b.getNewPassword();
                this.g = this.a.getNewPassword();
                this.h = this.a.getConfirmPassword();
                if (this.e.length() < 8 || this.f.length() < 8) {
                    com.aee.mokacam.utils.w.a(R.string.password_is_too_short, true);
                } else if (this.f.length() != this.e.length() && !TextUtils.equals(this.e, this.f)) {
                    com.aee.mokacam.utils.w.a(R.string.password_is_not_consistent, true);
                } else if (AeeApplication.a().U != 2) {
                    if (AeeApplication.a().U == 3) {
                        int iSetCommand = AeeApplication.a().SetCommand(this.f, this.f.length(), 4);
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if ((iSetCommand > 0 ? AeeApplication.a().SetCommand(this.f, this.f.length(), 2) : 0) > 0) {
                            com.aee.mokacam.utils.w.a(R.string.reset_device, true);
                            AeeApplication.a().DeviceLogout(1);
                        }
                    }
                } else if (AeeApplication.a().SetCommand(this.f, this.f.length(), 2) > 0) {
                    com.aee.mokacam.utils.w.a(R.string.reset_device, true);
                    AeeApplication.a().DeviceLogout(1);
                }
                break;
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wifi_pswchange);
        a();
        b();
        c();
        if (AeeApplication.a().U == 2) {
            AeeApplication.a().DeviceInit(101, "AEE_CONDOR", "192.168.3.2");
        } else if (AeeApplication.a().U == 3) {
            AeeApplication.a().DeviceInit(102, "AEE_RC_CON", "192.168.3.3");
        } else {
            com.aee.mokacam.utils.w.a(R.string.connect_wifi, true);
        }
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
