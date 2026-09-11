package com.aee.mokacam.widget;

import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
class d extends Handler {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        int progress = this.a.a.getProgress();
        int max = this.a.a.getMax();
        if (this.a.e != null) {
            this.a.c.setText(String.format(this.a.e, Integer.valueOf(progress), Integer.valueOf(max)));
        } else {
            this.a.c.setText(BuildConfig.FLAVOR);
        }
        if (this.a.f == null) {
            this.a.b.setText(BuildConfig.FLAVOR);
            return;
        }
        SpannableString spannableString = new SpannableString(this.a.f.format(1.0d - (((double) progress) / ((double) max))));
        spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 33);
        this.a.b.setText(spannableString);
    }
}
