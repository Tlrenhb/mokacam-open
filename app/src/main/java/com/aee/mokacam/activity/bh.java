package com.aee.mokacam.activity;

import android.os.SystemClock;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;
import com.aee.mokacam.bean.SendMsg;
import com.aee.mokacam.constants.AeeConstants;
import java.io.File;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
class bh implements Runnable {
    final /* synthetic */ LibraryActivity a;

    bh(LibraryActivity libraryActivity) {
        this.a = libraryActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        if ("local_lib".equals(this.a.r)) {
            Iterator it = this.a.s.iterator();
            int i = this.a.o;
            while (it.hasNext()) {
                com.aee.mokacam.bean.g gVar = (com.aee.mokacam.bean.g) it.next();
                if (gVar.e() && new File(gVar.a()).delete()) {
                    LibraryActivity libraryActivity = this.a;
                    libraryActivity.o--;
                    it.remove();
                    this.a.e.b(i - this.a.o);
                    this.a.a(gVar, gVar.a());
                }
            }
            this.a.i.sendEmptyMessage(32793);
            return;
        }
        if (AeeApplication.a().b && AeeApplication.a().f) {
            this.a.c = 0;
            for (com.aee.mokacam.bean.g gVar2 : this.a.s) {
                if (!AeeApplication.a().f || !AeeApplication.a().b) {
                    break;
                }
                if (gVar2.d()) {
                    this.a.c++;
                    String strA = gVar2.a();
                    this.a.a(gVar2, String.valueOf(AeeApplication.a().h) + gVar2.b + strA);
                    ReceiveMsg receiveMsgA = com.aee.mokacam.service.a.a().a(new SendMsg(AeeConstants.k, "/tmp/SD0/moka/" + gVar2.b + strA, null));
                    if (receiveMsgA == null || receiveMsgA.getRval() != 0) {
                        this.a.d++;
                    } else {
                        LibraryActivity libraryActivity2 = this.a;
                        libraryActivity2.o--;
                    }
                    this.a.i.post(new bi(this));
                    SystemClock.sleep(1000L);
                }
            }
            this.a.i.post(new bj(this));
        }
    }
}
