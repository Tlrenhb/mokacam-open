package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class ab extends Thread {
    final /* synthetic */ x a;

    ab(x xVar) {
        this.a = xVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (x.D == null) {
            try {
                x.D = this.a.C.getInputStream();
            } catch (IOException e) {
            e4.printStackTrace();
            }
        }
        long j = 0;
        com.aee.mokacam.utils.m.b("FlightControl", "20160425---ReceiveThread-- =" + Thread.currentThread().getId());
        while (this.a.F) {
            try {
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            if (this.a.a) {
                Thread.sleep(1000L);
                com.aee.mokacam.utils.m.b("FlightControl", "20160223---ReceiveThread()--isPause=" + Thread.currentThread().getId());
                break;
            }
            long jCurrentTimeMillis = System.currentTimeMillis() - j > 2000 ? System.currentTimeMillis() : j;
            try {
                if (!AeeApplication.a().bi) {
                    this.a.q = false;
                    break;
                }
                int iAvailable = x.D.available();
                byte[] bArr = new byte[iAvailable];
                for (int i = 0; i < iAvailable; i += x.D.read(bArr, i, iAvailable - i)) {
                }
                if (iAvailable > 0) {
                    AeeApplication.a().ak = System.currentTimeMillis();
                    if (x.e + iAvailable < x.d) {
                        System.arraycopy(bArr, 0, x.c, x.e, iAvailable);
                        x.e = iAvailable + x.e;
                    } else {
                        x.e = 0;
                        if (iAvailable > x.d) {
                            iAvailable = x.d;
                        }
                        System.arraycopy(bArr, 0, x.c, x.e, iAvailable);
                        x.e = iAvailable;
                    }
                    if (this.a.b(x.c, x.e) > 0) {
                        AeeApplication.a().aa = System.currentTimeMillis();
                        int i2 = x.e;
                        byte[] bArr2 = new byte[i2];
                        for (int i3 = 0; i3 < i2; i3++) {
                            bArr2[i3] = x.c[i3];
                        }
                        List<com.aee.mokacam.bean.m> listC = com.aee.mokacam.bean.m.c(bArr2);
                        if (listC.size() > 0) {
                            for (com.aee.mokacam.bean.m mVar : listC) {
                                int i4 = mVar.y;
                                if (com.aee.mokacam.bean.m.v[0] == i4) {
                                    AeeApplication.a().z = com.aee.mokacam.bean.f.a(mVar);
                                    AeeApplication.a().aN = AeeApplication.a().z.c();
                                    this.a.i = true;
                                    AeeApplication.a().K = true;
                                    AeeApplication.a().ac = true;
                                    long jA = AeeApplication.a().z.a();
                                    if (AeeApplication.a().bm) {
                                        this.a.N = jA;
                                        AeeApplication.a().a(this.a.N);
                                    }
                                    if (AeeApplication.a().aj) {
                                        this.a.u();
                                    }
                                } else if (com.aee.mokacam.bean.m.v[1] != i4) {
                                    if (com.aee.mokacam.bean.m.v[2] == i4) {
                                        AeeApplication.a().A = com.aee.mokacam.bean.c.a(mVar);
                                    } else if (com.aee.mokacam.bean.m.v[3] == i4) {
                                        AeeApplication.a().B = com.aee.mokacam.bean.e.a(mVar);
                                        AeeApplication.a().B.g();
                                        AeeApplication.a().al = AeeApplication.a().B.d();
                                        AeeApplication.a().am = AeeApplication.a().B.f();
                                        AeeApplication.a().an = AeeApplication.a().B.c();
                                        AeeApplication.a().y = AeeApplication.a().B.e();
                                        AeeApplication.a().bm = true;
                                        AeeApplication.a().M = true;
                                        AeeApplication.a().ao = true;
                                        this.a.a(36866, AeeApplication.a().B);
                                    } else if (com.aee.mokacam.bean.m.v[4] == i4) {
                                        com.aee.mokacam.bean.j jVarA = com.aee.mokacam.bean.j.a(mVar);
                                        jVarA.a();
                                        this.a.a(36865, jVarA);
                                        AeeApplication.a().af.put(jVarA.c(), String.valueOf(jVarA.d()));
                                        AeeApplication.a().N = true;
                                    } else if (com.aee.mokacam.bean.m.v[5] == i4) {
                                        AeeApplication.a().C = com.aee.mokacam.bean.n.a(mVar);
                                        AeeApplication.a().ah = true;
                                    } else if (i4 == 39) {
                                        new com.aee.mokacam.bean.q();
                                        com.aee.mokacam.bean.q qVarA = com.aee.mokacam.bean.q.a(mVar);
                                        int iD = qVarA.d();
                                        com.aee.mokacam.utils.m.b("test", "20161017--lat=" + qVarA.a() + "\n");
                                        com.aee.mokacam.utils.m.b("test", "20161017--lng=" + qVarA.c() + "\n");
                                        if (iD == 0) {
                                            AeeApplication.a().aM.clear();
                                        }
                                        AeeApplication.a().aM.add(qVarA);
                                        this.a.a(36867, qVarA);
                                    } else if (i4 == 40) {
                                        AeeApplication.a().ai = true;
                                    } else if (i4 == 42) {
                                        com.aee.mokacam.utils.m.b("test", "20161013--get  tseq=" + com.aee.mokacam.utils.k.a(com.aee.mokacam.utils.k.a(bArr2).toUpperCase(), 2));
                                    } else if (i4 == 44) {
                                        AeeApplication.a().aL = com.aee.mokacam.bean.p.a(mVar).a();
                                    } else if (i4 == 47) {
                                        mVar.b();
                                        if (com.aee.mokacam.bean.r.a(mVar).a() == 0) {
                                            AeeApplication.a().ap = true;
                                        }
                                    }
                                }
                            }
                        }
                        x.e = 0;
                        j = jCurrentTimeMillis;
                    } else {
                        int i5 = x.e;
                        byte[] bArr3 = new byte[i5];
                        for (int i6 = 0; i6 < i5; i6++) {
                            bArr3[i6] = x.c[i6];
                        }
                        j = jCurrentTimeMillis;
                    }
                } else {
                    j = jCurrentTimeMillis;
                }
            } catch (IOException e4) {
                j = jCurrentTimeMillis;
                                e4.printStackTrace();
                com.aee.mokacam.utils.m.b("FlightControl", "20160223---ReceiveThread() error=" + Thread.currentThread().getId());
            } catch (Exception e5) {
                j = jCurrentTimeMillis;
                                e4.printStackTrace();
                com.aee.mokacam.utils.m.b("FlightControl", "20160223---ReceiveThread() error1=" + e.getMessage());
            }
        }
        this.a.q = false;
    }
}
