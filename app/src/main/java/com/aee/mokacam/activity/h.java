package com.aee.mokacam.activity;

import android.widget.PopupWindow;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
class h implements PopupWindow.OnDismissListener {
    final /* synthetic */ AeeCameraActivty a;

    h(AeeCameraActivty aeeCameraActivty) {
        this.a = aeeCameraActivty;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        if (AeeApplication.aT == R.id.tv_signal_shot) {
            this.a.V.setImageResource(R.drawable.btn_photo_h);
        } else if (AeeApplication.aT == R.id.tv_brust_mode) {
            this.a.V.setImageResource(R.drawable.btn_burst_h);
        } else if (AeeApplication.aT == R.id.tv_delay_mode) {
            this.a.V.setImageResource(R.drawable.btn_time_lapse_h);
        }
    }
}
