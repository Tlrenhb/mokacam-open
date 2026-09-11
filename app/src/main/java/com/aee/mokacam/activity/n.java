package com.aee.mokacam.activity;

import android.text.TextUtils;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
class n implements Runnable {
    final /* synthetic */ m a;

    n(m mVar) {
        this.a = mVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (TextUtils.isEmpty(this.a.a.D) || this.a.a.D.equals("No in photo mode!")) {
            AeeCameraActivty.t = 0;
            AeeCameraActivty.Y.setText(AeeCameraActivty.u);
        } else {
            AeeCameraActivty.t = 1;
            if (AeeApplication.aT == R.id.tv_brust_mode) {
                this.a.a.V.setImageResource(R.drawable.btn_burst_h);
            } else if (AeeApplication.aT == R.id.tv_delay_mode) {
                this.a.a.V.setImageResource(R.drawable.btn_time_lapse_h);
            } else {
                this.a.a.V.setImageResource(R.drawable.btn_photo_h);
            }
            this.a.a.X.setImageResource(R.drawable.btn_operate);
            this.a.a.W.setImageResource(R.drawable.btn_record_n);
            AeeCameraActivty.Y.setText(this.a.a.s.substring(0, this.a.a.s.length() - 16));
        }
        this.a.a.j();
        this.a.a.f = false;
    }
}
