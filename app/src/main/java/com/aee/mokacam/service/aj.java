package com.aee.mokacam.service;

import com.aee.mokacam.constants.AeeConstants;
import java.io.File;
import org.xutils.common.Callback;

/* JADX INFO: loaded from: classes.dex */
class aj implements Callback.CommonCallback<File>, Callback.ProgressCallback<File> {
    final /* synthetic */ UpdateManager a;

    aj(UpdateManager updateManager) {
        this.a = updateManager;
    }

    /* synthetic */ aj(UpdateManager updateManager, aj ajVar) {
        this(updateManager);
    }

    @Override // org.xutils.common.Callback.CommonCallback
    public void onCancelled(Callback.CancelledException cancelledException) {
        File file = new File(AeeConstants.e, this.a.a.get("name"));
        if (file.exists()) {
            file.delete();
        }
        this.a.l = null;
    }

    @Override // org.xutils.common.Callback.CommonCallback
    public void onError(Throwable th, boolean z) {
    }

    @Override // org.xutils.common.Callback.CommonCallback
    public void onFinished() {
    }

    @Override // org.xutils.common.Callback.ProgressCallback
    public void onLoading(long j, long j2, boolean z) {
        this.a.f.setMax((int) j);
        this.a.f.setProgress((int) j2);
        this.a.h.setText(String.valueOf((100 * j2) / j) + "%");
    }

    @Override // org.xutils.common.Callback.ProgressCallback
    public void onStarted() {
    }

    @Override // org.xutils.common.Callback.CommonCallback
    public void onSuccess(File file) {
        this.a.e();
        this.a.k.dismiss();
    }

    @Override // org.xutils.common.Callback.ProgressCallback
    public void onWaiting() {
    }
}
