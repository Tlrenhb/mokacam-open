package com.aee.mokacam.activity;

import android.view.View;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
class aj implements View.OnClickListener {
    final /* synthetic */ AeeCameraSettingActivity a;

    aj(AeeCameraSettingActivity aeeCameraSettingActivity) {
        this.a = aeeCameraSettingActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.R.setVisibility(8);
        for (int i = 0; i < this.a.ap.size(); i++) {
            ((RelativeLayout) this.a.ap.get(i)).setBackgroundColor(0);
        }
    }
}
