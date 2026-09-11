package com.aee.mokacam.activity;

import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
class a implements Runnable {
    final /* synthetic */ AeeAppSettingActivity a;

    a(AeeAppSettingActivity aeeAppSettingActivity) {
        this.a = aeeAppSettingActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        this.a.a = AeeApplication.a().GetUpdateInfo();
        long jCurrentTimeMillis = System.currentTimeMillis();
        while (this.a.a[0] == 0) {
            this.a.a = AeeApplication.a().GetUpdateInfo();
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (System.currentTimeMillis() - jCurrentTimeMillis > 5000) {
                break;
            }
        }
        this.a.a = AeeApplication.a().GetUpdateInfo();
        if (this.a.a[0] == 1 && this.a.a[1] == 1) {
            this.a.b.setMax((this.a.a[3] / 1024) + 1);
            z = true;
        } else {
            z = false;
        }
        while (this.a.a[0] == 1) {
            this.a.a = AeeApplication.a().GetUpdateInfo();
            int i = this.a.a[2];
            if (i < this.a.b.getMax()) {
                this.a.b.setProgress(i);
            }
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        this.a.i.post(new b(this));
        if (z) {
            com.aee.mokacam.utils.w.a(this.a.getString(R.string.appsetting_updatesuccess), true);
        }
    }
}
