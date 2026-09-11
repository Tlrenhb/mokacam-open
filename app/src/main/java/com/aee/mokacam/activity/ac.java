package com.aee.mokacam.activity;

import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
class ac implements View.OnClickListener {
    final /* synthetic */ AeeCameraSettingActivity a;
    private final /* synthetic */ PopupWindow b;

    ac(AeeCameraSettingActivity aeeCameraSettingActivity, PopupWindow popupWindow) {
        this.a = aeeCameraSettingActivity;
        this.b = popupWindow;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.b.dismiss();
        for (int i = 0; i < this.a.ap.size(); i++) {
            ((RelativeLayout) this.a.ap.get(i)).setBackgroundColor(0);
        }
    }
}
