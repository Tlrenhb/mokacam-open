package com.aee.mokacam.activity;

import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class m implements Runnable {
    final /* synthetic */ AeeCameraActivty a;

    m(AeeCameraActivty aeeCameraActivty) {
        this.a = aeeCameraActivty;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (com.aee.mokacam.service.a.a().b()) {
            Map<String, String> map = AeeApplication.a().q;
            if (map != null) {
                this.a.L = map.get("video_time");
                this.a.M = map.get("video_resolution");
                this.a.s = map.get("photo_size");
                this.a.D = map.get("photo_mode");
                this.a.E = map.get("rec_mode");
                if ("Loop_Record".equals(this.a.E)) {
                    AeeApplication.aW = "60s";
                }
                String str = map.get("photo_burstRate");
                if (!"off".equals(str)) {
                    AeeApplication.aT = R.id.tv_brust_mode;
                    AeeApplication.aU = str;
                }
                String str2 = map.get("photo_selfTimer");
                if (!"off".equals(str2)) {
                    AeeApplication.aT = R.id.tv_delay_mode;
                    AeeApplication.aV = str2;
                }
                AeeCameraActivty.u = com.aee.mokacam.utils.a.b(this.a.M);
            }
            this.a.i.post(new n(this));
        }
    }
}
