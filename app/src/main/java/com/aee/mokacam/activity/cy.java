package com.aee.mokacam.activity;

import android.app.NotificationManager;
import android.media.MediaScannerConnection;
import androidx.core.app.NotificationCompat;
import android.widget.RemoteViews;
import com.aee.mokacam.R;
import com.aee.mokacam.constants.AeeConstants;
import java.io.File;
import org.xutils.common.Callback;

/* JADX INFO: loaded from: classes.dex */
class cy implements Callback.CommonCallback<File>, Callback.ProgressCallback<File> {
    final /* synthetic */ ShowPicOrVideoActivity a;
    RemoteViews b;
    NotificationManager c;
    NotificationCompat.Builder d;

    public cy(ShowPicOrVideoActivity showPicOrVideoActivity, RemoteViews remoteViews, NotificationManager notificationManager, NotificationCompat.Builder builder) {
        this.a = showPicOrVideoActivity;
        this.b = remoteViews;
        this.c = notificationManager;
        this.d = builder;
    }

    @Override // org.xutils.common.Callback.CommonCallback
    public void onCancelled(Callback.CancelledException cancelledException) {
        for (int i = 0; i < this.a.g.size(); i++) {
            this.a.a(i);
        }
    }

    @Override // org.xutils.common.Callback.CommonCallback
    public void onError(Throwable th, boolean z) {
    }

    @Override // org.xutils.common.Callback.CommonCallback
    public void onFinished() {
        if (this.a.J == null || this.a.I == null) {
            return;
        }
        this.a.J.cancel();
        this.a.I = null;
        this.a.J = null;
    }

    @Override // org.xutils.common.Callback.ProgressCallback
    public void onLoading(long j, long j2, boolean z) {
        if (this.a.q) {
            this.a.q = false;
        }
        ((com.aee.mokacam.bean.g) this.a.g.get(this.a.f)).a(2);
        this.b.setProgressBar(R.id.pb_notification, (int) j, (int) j2, false);
        this.b.setTextViewText(R.id.tv_notification_progress, String.valueOf((100 * j2) / j) + "%");
        this.c.notify(this.a.r, this.d.build());
    }

    @Override // org.xutils.common.Callback.ProgressCallback
    public void onStarted() {
    }

    @Override // org.xutils.common.Callback.CommonCallback
    public void onSuccess(File file) {
        this.a.q = true;
        ((com.aee.mokacam.bean.g) this.a.g.get(this.a.f)).a(3);
        MediaScannerConnection.scanFile(this.a, new String[]{String.valueOf(AeeConstants.a) + File.separator + ((com.aee.mokacam.bean.g) this.a.g.get(this.a.f)).f()}, null, null);
        com.aee.mokacam.utils.w.a(R.string.singledownloadfinish, true);
    }

    @Override // org.xutils.common.Callback.ProgressCallback
    public void onWaiting() {
    }
}
