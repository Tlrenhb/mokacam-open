package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.constants.AeeConstants;
import java.util.concurrent.TimeUnit;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
class y implements Runnable {
    final /* synthetic */ x a;
    private boolean b = false;

    y(x xVar) {
        this.a = xVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        String strB = BuildConfig.FLAVOR;
        x.I = new al(x.J);
        long jCurrentTimeMillis = 0;
        while (this.a.l) {
            try {
                if (x.I == null) {
                    x.I = new al(x.J);
                }
                x.I.c();
                if (System.currentTimeMillis() - jCurrentTimeMillis > 5000) {
                    jCurrentTimeMillis = System.currentTimeMillis();
                }
                AeeApplication.a().X = Thread.currentThread().getId();
                if (x.I.a()) {
                    this.a.m = true;
                    AeeApplication.a().Q = x.I.b();
                    if (strB != null && x.I.b() != null && strB.length() != x.I.b().length()) {
                        this.a.v = true;
                        strB = x.I.b();
                        int iA = AeeApplication.a().a(AeeApplication.a().Q);
                        if (iA > 0) {
                            AeeApplication.a().U = iA;
                        }
                    }
                } else {
                    this.a.m = false;
                    AeeApplication.a().aa = 0L;
                }
                if (this.a.m) {
                    this.a.n = true;
                } else {
                    AeeApplication.a().bi = false;
                    this.a.n = false;
                }
                if (this.a.n && !this.a.o && !AeeApplication.a().bi) {
                    this.a.g();
                    try {
                        TimeUnit.MILLISECONDS.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.a.t();
                if (AeeApplication.a().bi) {
                    if (!this.a.p && this.a.e()) {
                        com.aee.mokacam.utils.m.b("FlightControl", "20161010---HeartBeat run");
                        this.a.p = true;
                        this.a.u = true;
                        this.a.r();
                    }
                    if (!this.a.q && this.a.d()) {
                        this.a.q = true;
                        if (AeeApplication.a().F == AeeConstants.DataAction.receive) {
                            this.a.u = false;
                        }
                        this.a.s();
                        com.aee.mokacam.utils.m.b("FlightControl", "20160223---ReceiveThread run");
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(500L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                this.a.m = false;
                AeeApplication.a().aa = 0L;
                this.a.g();
                try {
                    TimeUnit.MILLISECONDS.sleep(2000L);
                } catch (InterruptedException e4) {
                    e4.printStackTrace();
                }
            }
        }
        AeeApplication.a().X = 0L;
    }
}
