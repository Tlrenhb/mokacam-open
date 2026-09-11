package com.aee.mokacam.activity;

import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.aee.mokacam.bean.SendMsg;
import com.aee.mokacam.constants.AeeConstants;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
class y implements View.OnClickListener {
    final /* synthetic */ AeeCameraSettingActivity a;
    private final /* synthetic */ PopupWindow b;
    private final /* synthetic */ int c;

    y(AeeCameraSettingActivity aeeCameraSettingActivity, PopupWindow popupWindow, int i) {
        this.a = aeeCameraSettingActivity;
        this.b = popupWindow;
        this.c = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.b.dismiss();
        for (int i = 0; i < this.a.ap.size(); i++) {
            ((RelativeLayout) this.a.ap.get(i)).setBackgroundColor(0);
        }
        if (this.c != 0) {
            com.aee.mokacam.service.a.a().a(new ab(this), new SendMsg("恢复出厂设置 ", 2, "on", "default_setting", 21));
            return;
        }
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().clearMemoryCache();
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().clearDiscCache();
        File fileA = com.aee.mokacam.utils.t.a(this.a.j, "videoThumbnailCache");
        if (fileA.exists()) {
            fileA.delete();
        }
        this.a.O.show();
        com.aee.mokacam.service.a.a().a(new z(this), new SendMsg(AeeConstants.n, "C", null));
    }
}
