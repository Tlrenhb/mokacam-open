package com.aee.mokacam.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.CommonSetting;
import com.aee.mokacam.widget.SlidingMenuView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends BaseActivity {
    protected boolean a;
    private ImageView c;
    private ImageView d;
    private ImageView e;
    private TextView h;
    private com.aee.mokacam.widget.g k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private SlidingMenuView r;
    private RelativeLayout s;
    private LinearLayout t;
    private TextView u;
    private RelativeLayout v;
    private boolean f = false;
    private List<CommonSetting> g = new ArrayList();
    private boolean q = false;
    public String b = "0.0.0.0";
    private boolean w = true;
    private BroadcastReceiver x = new bq(this);

    private void a() {
        this.k = new com.aee.mokacam.widget.g(this.j);
        this.d = (ImageView) findViewById(R.id.iv_library);
        this.e = (ImageView) findViewById(R.id.iv_arrow_back);
        this.p = (TextView) findViewById(R.id.tv_main_connect);
        this.h = (TextView) findViewById(R.id.product_specs);
        this.c = (ImageView) findViewById(R.id.menu);
        this.s = (RelativeLayout) findViewById(R.id.rl_main_connect);
        this.u = (TextView) findViewById(R.id.tv_libary);
        this.v = (RelativeLayout) findViewById(R.id.rl_selectproduct);
        this.t = (LinearLayout) findViewById(R.id.left_frame);
        this.l = (TextView) findViewById(R.id.tv_shop);
        this.m = (TextView) findViewById(R.id.tv_setting);
        this.n = (TextView) findViewById(R.id.tv_support);
        this.o = (TextView) findViewById(R.id.tv_legal);
        this.l.setVisibility(8);
        this.n.setVisibility(8);
        this.o.setVisibility(8);
        this.r = (SlidingMenuView) findViewById(R.id.sliding_menu);
        this.p.setTextColor(-7829368);
        this.s.setBackgroundResource(R.drawable.connect);
    }

    private void a(WifiManager wifiManager) {
        if (com.aee.mokacam.utils.aa.a(wifiManager)) {
            if (!AeeApplication.a().b) {
                com.aee.mokacam.service.a.a();
            }
            if (com.aee.mokacam.service.a.a != null) {
                com.aee.mokacam.service.a.a.a(this.i);
            }
        }
    }

    private void b() {
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.t.setOnClickListener(new br(this));
    }

    private void c() {
        AeeApplication.a().b(((WifiManager) getSystemService("wifi")).getConnectionInfo().getSSID());
    }

    private void d() {
        AeeApplication.a().a = -1;
        Process.killProcess(Process.myPid());
    }

    public String a(int i) {
        return i <= 0 ? "0.0.0.0" : (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                this.p.setTextColor(0xFF000000);
                this.p.setText(R.string.isconnected);
                this.s.setBackgroundResource(R.drawable.connected);
                c();
                break;
            case 1:
                this.p.setText(R.string.s91connectfail);
                break;
            case 7:
                startActivity(new Intent(this, (Class<?>) SelectLibraryActivity.class));
                break;
            case 32771:
                this.p.setTextColor(0xFF000000);
                this.p.setText(R.string.isconnected);
                this.s.setBackgroundResource(R.drawable.connected);
                Intent intent = new Intent();
                intent.setClass(this, AeeCameraActivty.class);
                startActivity(intent);
                break;
        }
        return super.handleMessage(message);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.q) {
            this.r.b();
            this.q = this.q ? false : true;
        } else if (this.p.getText().toString().contains("...")) {
            Toast.makeText(this, getResources().getString(R.string.connecting_notexit), 0).show();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        new Intent();
        switch (view.getId()) {
            case R.id.iv_arrow_back /* 2131427763 */:
                if (!this.p.getText().toString().contains("...")) {
                    finish();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.connecting_notexit), 0).show();
                }
                break;
            case R.id.menu /* 2131427764 */:
                if (this.q) {
                    this.r.b();
                } else {
                    this.r.a();
                }
                this.q = !this.q;
                break;
            case R.id.product_specs /* 2131427769 */:
                startActivity(new Intent(this, (Class<?>) ProductParamsActivity.class));
                break;
            case R.id.rl_main_connect /* 2131427770 */:
                WifiManager wifiManager = (WifiManager) getSystemService("wifi");
                String strA = a(wifiManager.getConnectionInfo().getIpAddress());
                if (!com.aee.mokacam.utils.aa.a(wifiManager)) {
                    this.p.setText(R.string.please_open_wifi);
                } else if (!TextUtils.isEmpty(strA) && strA.contains("192.168.42")) {
                    this.p.setText(R.string.connecting);
                    a(wifiManager);
                    if (!AeeApplication.a().bn) {
                        this.i.sendEmptyMessageDelayed(32771, 2500L);
                    } else {
                        AeeApplication.a().bn = false;
                        this.i.sendEmptyMessageDelayed(32771, 3800L);
                    }
                } else {
                    this.p.setText(R.string.pleaseconnect);
                }
                break;
            case R.id.iv_library /* 2131427772 */:
                this.u.setTextColor(0xFF000000);
                Intent intent = new Intent();
                if (!AeeApplication.a().f) {
                    WifiManager wifiManager2 = (WifiManager) getSystemService("wifi");
                    String strA2 = a(wifiManager2.getConnectionInfo().getIpAddress());
                    if (!TextUtils.isEmpty(strA2) && strA2.contains("192.168.42")) {
                        a(wifiManager2);
                        this.i.sendEmptyMessageDelayed(7, 2500L);
                    } else {
                        intent.setClass(this, SelectLibraryActivity.class);
                    }
                } else {
                    intent.setClass(this, SelectLibraryActivity.class);
                }
                startActivity(intent);
                break;
            case R.id.tv_shop /* 2131427814 */:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getResources().getString(R.string.shop_link))));
                break;
            case R.id.tv_setting /* 2131427815 */:
                Intent intent2 = new Intent(this, (Class<?>) AeeAppSettingActivity.class);
                intent2.putExtra("main_go", "main_camera");
                startActivity(intent2);
                break;
            case R.id.tv_support /* 2131427816 */:
                startActivity(new Intent(this, (Class<?>) SupportActivity.class));
                break;
            case R.id.tv_legal /* 2131427817 */:
                startActivity(new Intent(this, (Class<?>) LegalActivity.class));
                break;
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        a();
        b();
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
        super.onDestroy();
        d();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        AeeApplication.a().f = false;
        String strA = a(((WifiManager) getSystemService("wifi")).getConnectionInfo().getIpAddress());
        if (!TextUtils.isEmpty(strA) && strA.contains("192.168.42") && AeeApplication.a().f) {
            this.p.setTextColor(0xFF000000);
            this.p.setText(R.string.isconnected);
            this.s.setBackgroundResource(R.drawable.connected);
        } else {
            this.p.setTextColor(Color.parseColor("#99606060"));
            this.p.setText(R.string.connect);
            this.s.setBackgroundResource(R.drawable.connect);
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.u.setTextColor(getResources().getColor(R.color.text_libary_color));
        if (AeeApplication.a().f) {
            return;
        }
        this.p.setText(R.string.connect);
    }
}
