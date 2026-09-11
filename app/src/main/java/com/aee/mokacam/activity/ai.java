package com.aee.mokacam.activity;

import android.view.View;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
class ai implements View.OnClickListener {
    final /* synthetic */ AeeCameraSettingActivity a;

    ai(AeeCameraSettingActivity aeeCameraSettingActivity) {
        this.a = aeeCameraSettingActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        for (int i = 0; i < this.a.ap.size(); i++) {
            ((RelativeLayout) this.a.ap.get(i)).setBackgroundColor(0);
        }
        String string = this.a.aA.getText().toString();
        if (this.a.a(string)) {
            this.a.b(string);
        }
    }
}
