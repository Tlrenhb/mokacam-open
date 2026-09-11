package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;
import java.net.InetSocketAddress;
import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
class ae implements Runnable {
    final /* synthetic */ ac a;

    ae(ac acVar) {
        this.a = acVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.m = true;
        this.a.d();
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.a.j = true;
        try {
            this.a.e = new Socket();
            this.a.e.setTcpNoDelay(true);
            this.a.e.connect(new InetSocketAddress(ac.c, ac.d), 5000);
            this.a.e.setKeepAlive(true);
            ac.f = this.a.e.getInputStream();
            ac.g = this.a.e.getOutputStream();
            AeeApplication.a().aa = 0L;
            AeeApplication.a().ag = false;
            com.aee.mokacam.utils.m.b("test", "20161031--------connectServer !");
        } catch (AssertionError e2) {
            e2.printStackTrace();
            AeeApplication.a().bi = false;
        } catch (Exception e3) {
            e3.printStackTrace();
            AeeApplication.a().bi = false;
        }
        if (this.a.e == null || this.a.e.isClosed() || !this.a.e.isConnected()) {
            AeeApplication.a().bi = false;
        } else {
            AeeApplication.a().bi = true;
        }
        this.a.m = false;
    }
}
