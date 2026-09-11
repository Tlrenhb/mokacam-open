package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
class ad implements Runnable {
    final /* synthetic */ ac a;

    ad(ac acVar) {
        this.a = acVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.aee.mokacam.utils.m.b("test", "20161031--------mainThread run!");
        while (this.a.a) {
            ac.h = new al(ac.i);
            if (ac.h.a()) {
                AeeApplication.a().Q = ac.h.b();
                if (ac.h.b() != null && ac.h.b() != null) {
                    this.a.k = true;
                }
            } else {
                this.a.k = false;
            }
            if (this.a.k && !this.a.m && !AeeApplication.a().bi) {
                com.aee.mokacam.utils.m.b("test", "20161031---------main  bConnectDrone=" + AeeApplication.a().bi);
                this.a.c();
                try {
                    TimeUnit.MILLISECONDS.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!this.a.l && AeeApplication.a().bi) {
                this.a.l = true;
                this.a.i();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        AeeApplication.a().X = 0L;
        com.aee.mokacam.utils.m.b("test", "20161031--------mainThread run end !");
    }
}
