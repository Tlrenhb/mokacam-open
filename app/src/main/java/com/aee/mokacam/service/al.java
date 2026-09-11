package com.aee.mokacam.service;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/* JADX INFO: loaded from: classes.dex */
public class al {
    WifiManager a;
    WifiInfo b;

    public al(Context context) {
        this.a = (WifiManager) context.getSystemService("wifi");
        this.b = this.a.getConnectionInfo();
    }

    public boolean a() {
        return this.a.isWifiEnabled();
    }

    public String b() {
        if (this.b == null) {
            return null;
        }
        return this.b.getSSID();
    }

    public int c() {
        return (this.b == null ? null : Integer.valueOf(this.b.getIpAddress())).intValue();
    }
}
