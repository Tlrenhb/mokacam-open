package com.aee.mokacam.activity;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class aq implements View.OnClickListener {
    final /* synthetic */ DownLoadActivity a;

    aq(DownLoadActivity downLoadActivity) {
        this.a = downLoadActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.a.c.size()) {
                this.a.finish();
                return;
            } else {
                if (((com.aee.mokacam.bean.g) this.a.c.get(i2)).g() == 2) {
                    this.a.d();
                    return;
                }
                i = i2 + 1;
            }
        }
    }
}
