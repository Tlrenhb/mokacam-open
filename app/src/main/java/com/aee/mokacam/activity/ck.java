package com.aee.mokacam.activity;

import android.view.View;
import android.widget.AdapterView;
import com.aee.mokacam.bean.SendMsg;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
class ck implements AdapterView.OnItemClickListener {
    String a = BuildConfig.FLAVOR;
    String b = BuildConfig.FLAVOR;
    final /* synthetic */ SelectSimpleActivity c;

    ck(SelectSimpleActivity selectSimpleActivity) {
        this.c = selectSimpleActivity;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        switch (this.c.g) {
            case 32774:
                this.a = this.c.a[i];
                this.b = "video_resolution";
                break;
            case 32776:
                this.a = this.c.a[i];
                this.b = "photo_size";
                break;
            case 32777:
                this.a = this.c.e[i];
                this.b = "photo_shot_mode";
                break;
            case 32783:
                this.a = this.c.e[i];
                this.b = "photo_tlm";
                break;
            case 32784:
                this.a = this.c.e[i];
                this.b = "Beep";
                break;
            case 32785:
                this.a = this.c.e[i];
                this.b = "Status_LED";
                break;
            case 32788:
                this.a = this.c.a[i];
                this.b = "TV_Mode";
                break;
            case 32789:
                this.a = this.c.e[i];
                this.b = "Language";
                break;
        }
        com.aee.mokacam.service.a.a().a(new cl(this), new SendMsg(2, this.a, this.b));
    }
}
