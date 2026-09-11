package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;
import java.net.InetSocketAddress;
import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
class q implements Runnable {
    final /* synthetic */ o a;

    q(o oVar) {
        this.a = oVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.e();
        this.a.b = true;
        try {
            this.a.d = new Socket();
            this.a.d.connect(new InetSocketAddress("192.168.42.1", 7878), 5000);
            this.a.d.setKeepAlive(true);
            o.e = this.a.d.getInputStream();
            o.f = this.a.d.getOutputStream();
            this.a.d();
            AeeApplication.a().b = true;
            this.a.c();
        } catch (Exception e) {
            AeeApplication.a();
            AeeApplication.a().b = false;
            e.printStackTrace();
        }
        this.a.g();
    }
}
