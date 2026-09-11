package com.aee.mokacam.activity;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.aee.mokacam.AeeApplication;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
class l implements Runnable {
    final /* synthetic */ AeeCameraActivty a;

    l(AeeCameraActivty aeeCameraActivty) {
        this.a = aeeCameraActivty;
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.a.H) {
            try {
                WifiInfo connectionInfo = ((WifiManager) this.a.getSystemService("wifi")).getConnectionInfo();
                this.a.I = connectionInfo.getRssi();
                int ipAddress = connectionInfo.getIpAddress();
                if (!this.a.b.equalsIgnoreCase(this.a.a(ipAddress))) {
                    this.a.b = this.a.a(ipAddress);
                    if (this.a.b(ipAddress)) {
                        this.a.q = false;
                    } else {
                        this.a.q = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (this.a.q) {
                AeeApplication.a().f = false;
                AeeApplication.a().b = false;
                this.a.finish();
                return;
            } else {
                if (this.a.I == -200) {
                    AeeApplication.a().b = false;
                    AeeApplication.a().e = -1000;
                    this.a.H = false;
                    this.a.finish();
                    return;
                }
                if (this.a.C) {
                    this.a.i();
                }
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
