package com.aee.mokacam.activity;

import android.view.View;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
class k implements View.OnClickListener {
    final /* synthetic */ AeeCameraActivty a;

    k(AeeCameraActivty aeeCameraActivty) {
        this.a = aeeCameraActivty;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.af = false;
        this.a.h = true;
        switch (view.getId()) {
            case R.id.tv_signal_shot /* 2131427819 */:
                if (AeeApplication.aT != R.id.tv_signal_shot) {
                    this.a.V.setImageResource(R.drawable.btn_photo_h);
                    if (AeeApplication.aT == R.id.tv_brust_mode) {
                        this.a.a("off", "photo_burstRate");
                    } else if (AeeApplication.aT == R.id.tv_delay_mode) {
                        this.a.a("off", "photo_selfTimer");
                    }
                    AeeApplication.aT = R.id.tv_signal_shot;
                }
                this.a.ae.dismiss();
                break;
            case R.id.tv_brust_mode /* 2131427820 */:
                if ("off".equals(AeeApplication.aU)) {
                    AeeApplication.aU = "3p";
                }
                this.a.a(AeeApplication.aU, "photo_burstRate");
                AeeApplication.aT = R.id.tv_brust_mode;
                this.a.ae.dismiss();
                break;
            case R.id.tv_delay_mode /* 2131427821 */:
                if ("off".equals(AeeApplication.aV)) {
                    AeeApplication.aV = "3s";
                }
                this.a.a(AeeApplication.aV, "photo_selfTimer");
                AeeApplication.aT = R.id.tv_delay_mode;
                this.a.ae.dismiss();
                break;
        }
    }
}
