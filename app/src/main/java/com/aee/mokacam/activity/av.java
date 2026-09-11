package com.aee.mokacam.activity;

import android.media.MediaScannerConnection;
import com.aee.mokacam.constants.AeeConstants;
import java.io.File;
import org.xutils.common.Callback;

/* JADX INFO: loaded from: classes.dex */
class av implements Callback.CommonCallback<File>, Callback.ProgressCallback<File> {
    final /* synthetic */ DownLoadActivity a;
    private int b;

    public av(DownLoadActivity downLoadActivity, int i) {
        this.a = downLoadActivity;
        this.b = i;
    }

    @Override // org.xutils.common.Callback.CommonCallback
    public void onCancelled(Callback.CancelledException cancelledException) {
        ((com.aee.mokacam.bean.g) this.a.c.get(this.b)).a(6);
        this.a.d.notifyDataSetChanged();
        this.a.a(this.b);
    }

    @Override // org.xutils.common.Callback.CommonCallback
    public void onError(Throwable th, boolean z) {
        ((com.aee.mokacam.bean.g) this.a.c.get(this.b)).a(5);
        this.a.d.notifyDataSetChanged();
        this.a.a(this.b);
    }

    @Override // org.xutils.common.Callback.CommonCallback
    public void onFinished() {
    }

    @Override // org.xutils.common.Callback.ProgressCallback
    public void onLoading(long j, long j2, boolean z) {
        if (z) {
            ((com.aee.mokacam.bean.g) this.a.c.get(this.b)).a(2);
        }
        ((com.aee.mokacam.bean.g) this.a.c.get(this.b)).c = j;
        ((com.aee.mokacam.bean.g) this.a.c.get(this.b)).d = j2;
        this.a.d.notifyDataSetChanged();
    }

    @Override // org.xutils.common.Callback.ProgressCallback
    public void onStarted() {
    }

    @Override // org.xutils.common.Callback.CommonCallback
    public void onSuccess(File file) {
        ((com.aee.mokacam.bean.g) this.a.c.get(this.b)).a(3);
        this.a.d.notifyDataSetChanged();
        ((com.aee.mokacam.bean.g) this.a.c.get(this.b)).e = null;
        MediaScannerConnection.scanFile(this.a, new String[]{String.valueOf(AeeConstants.a) + File.separator + ((com.aee.mokacam.bean.g) this.a.c.get(this.b)).a()}, null, null);
    }

    @Override // org.xutils.common.Callback.ProgressCallback
    public void onWaiting() {
        ((com.aee.mokacam.bean.g) this.a.c.get(this.b)).a(1);
    }
}
