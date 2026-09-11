package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;
import java.net.InetSocketAddress;
import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
class z implements Runnable {
    final /* synthetic */ x a;

    z(x xVar) {
        this.a = xVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.o = true;
        this.a.j();
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.a.F = true;
        try {
            this.a.C = new Socket();
            this.a.C.connect(new InetSocketAddress(x.A, x.B), 5000);
            this.a.C.setKeepAlive(true);
            x.D = this.a.C.getInputStream();
            x.E = this.a.C.getOutputStream();
            AeeApplication.a().aa = 0L;
            AeeApplication.a().ag = false;
        } catch (AssertionError e2) {
            e2.printStackTrace();
            AeeApplication.a().bi = false;
        } catch (Exception e3) {
            e3.printStackTrace();
            AeeApplication.a().bi = false;
        }
        this.a.R = true;
        if (this.a.C == null || this.a.C.isClosed() || !this.a.C.isConnected()) {
            AeeApplication.a().bi = false;
        } else {
            AeeApplication.a().bi = true;
        }
        this.a.o = false;
    }
}
