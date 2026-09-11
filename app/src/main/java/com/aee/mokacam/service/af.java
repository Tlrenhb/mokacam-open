package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
class af implements Runnable {
    final /* synthetic */ ac a;

    af(ac acVar) {
        this.a = acVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i = 0;
        while (true) {
            if (!this.a.j) {
                break;
            }
            if (AeeApplication.a().au) {
                if (this.a.e == null || this.a.e.isClosed() || !this.a.e.isConnected()) {
                    i++;
                } else {
                    byte[] bArrB = AeeApplication.a().ar.b();
                    this.a.a(bArrB);
                    com.aee.mokacam.utils.m.b("FlightControl", "20161031---HeartBeat send=" + com.aee.mokacam.utils.k.a(com.aee.mokacam.utils.k.a(bArrB).toUpperCase(), 2));
                    i = 0;
                }
                try {
                    Thread.sleep(30L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i > 20 || AeeApplication.a().bi) {
                    if (i > 20) {
                        AeeApplication.a().bi = false;
                        this.a.l = false;
                        break;
                    }
                }
            } else {
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                com.aee.mokacam.utils.m.b("FlightControl", "20161010---HeartBeat---isHeart=true");
            }
        }
        this.a.l = false;
    }
}
