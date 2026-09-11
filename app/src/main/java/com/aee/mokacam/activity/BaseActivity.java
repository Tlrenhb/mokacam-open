package com.aee.mokacam.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.fragment.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
public class BaseActivity extends FragmentActivity implements Handler.Callback, View.OnClickListener {
    public Handler i;
    public Context j;

    public boolean handleMessage(Message message) {
        return false;
    }

    public void onClick(View view) {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.i = new Handler(this);
        this.j = this;
        AeeApplication.a().a(this);
        d();
    }

    /* Runtime permissions required on API 23+: storage for the local album
     * and fine location for reading the Wi-Fi SSID (camera detection). */
    void d() {
        if (android.os.Build.VERSION.SDK_INT < 23) {
            return;
        }
        java.util.ArrayList<String> needed = new java.util.ArrayList<>();
        if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0
                && android.os.Build.VERSION.SDK_INT < 29) {
            needed.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0
                && android.os.Build.VERSION.SDK_INT < 33) {
            needed.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        if (checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
            needed.add("android.permission.ACCESS_FINE_LOCATION");
        }
        if (!needed.isEmpty()) {
            requestPermissions(needed.toArray(new String[0]), 1001);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityHoneycomb, android.app.Activity, android.view.LayoutInflater.Factory2
    public /* bridge */ /* synthetic */ View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(view, str, context, attributeSet);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity, android.view.LayoutInflater.Factory
    public /* bridge */ /* synthetic */ View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        try {
            if (com.aee.mokacam.service.a.a != null) {
                com.aee.mokacam.service.a.a.b(this.i);
            }
        } catch (Exception e) {
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            if (com.aee.mokacam.service.a.a != null) {
                com.aee.mokacam.service.a.a.a(this.i);
            }
        } catch (Exception e) {
        }
    }
}
