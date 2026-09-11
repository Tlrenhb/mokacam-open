package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
class aa implements Runnable {
    final /* synthetic */ x a;

    aa(x xVar) {
        this.a = xVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        long j;
        int i;
        Exception e;
        long jCurrentTimeMillis = 0;
        int i2 = 0;
        while (true) {
            if (!this.a.F) {
                break;
            }
            if (this.a.K == null) {
                this.a.b();
            }
            if (this.a.v) {
                this.a.v = false;
                this.a.K.a(AeeApplication.a().R);
            }
            if (AeeApplication.a().au) {
                try {
                    Thread.sleep(100L);
                } catch (Exception e2) {
                    j = jCurrentTimeMillis;
                    i = i2;
                    e = e2;
                }
                if (System.currentTimeMillis() - this.a.H >= 100 && !x.i()) {
                    if (AeeApplication.a().ah && this.a.R) {
                        this.a.p();
                        this.a.R = false;
                    } else {
                        if (System.currentTimeMillis() - jCurrentTimeMillis > 1000) {
                            jCurrentTimeMillis = System.currentTimeMillis();
                            if (AeeApplication.a().U == 2) {
                                this.a.q();
                            }
                        }
                        if (this.a.S) {
                            com.aee.mokacam.utils.m.b("FlightControl", "20161010---send stop!");
                        } else {
                            byte[] bArrC = this.a.K.c();
                            if (this.a.C == null || this.a.C.isClosed() || !this.a.C.isConnected()) {
                                com.aee.mokacam.utils.m.b("FlightControl", "20161010---socket send error");
                                AeeApplication.a().bi = false;
                                i2++;
                            } else {
                                if (this.a.O) {
                                    this.a.a(bArrC);
                                    Thread.sleep(10L);
                                    this.a.a(this.a.h());
                                }
                                if (AeeApplication.a().ae) {
                                    Thread.sleep(20L);
                                    if (System.currentTimeMillis() - this.a.P >= 200) {
                                        this.a.P = System.currentTimeMillis();
                                        this.a.a(AeeApplication.a().ad.b());
                                    }
                                }
                                try {
                                    if (this.a.k > 100000) {
                                        this.a.k = 0;
                                        i2 = 0;
                                    } else {
                                        this.a.k++;
                                        i2 = 0;
                                    }
                                } catch (Exception e3) {
                                    e = e3;
                                    j = jCurrentTimeMillis;
                                    i = 0;
                                    AeeApplication.a().bi = false;
                                    e.printStackTrace();
                                    com.aee.mokacam.utils.m.b("FlightControl", "20161010---socket try send error=" + e.getMessage());
                                    i2 = i + 1;
                                    jCurrentTimeMillis = j;
                                }
                            }
                            if (i2 > 10 || AeeApplication.a().bi) {
                                if (i2 > 10) {
                                    AeeApplication.a().bi = false;
                                    this.a.k = 0;
                                    this.a.p = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            } else {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e4) {
                    e4.printStackTrace();
                }
            }
        }
        this.a.k = 0;
        this.a.p = false;
    }
}
