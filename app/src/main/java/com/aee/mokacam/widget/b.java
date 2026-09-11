package com.aee.mokacam.widget;

import android.os.AsyncTask;

/* JADX INFO: loaded from: classes.dex */
class b extends AsyncTask<Integer, Integer, Integer> {
    final /* synthetic */ BidirSlidingLayout a;

    b(BidirSlidingLayout bidirSlidingLayout) {
        this.a = bidirSlidingLayout;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Integer... numArr) {
        int i;
        int iIntValue = this.a.r.leftMargin;
        while (true) {
            iIntValue += numArr[0].intValue();
            if (iIntValue < (-this.a.q.width)) {
                i = -this.a.q.width;
                break;
            }
            if (iIntValue > 0) {
                i = 0;
                break;
            }
            publishProgress(Integer.valueOf(iIntValue));
            this.a.a(15L);
        }
        if (numArr[0].intValue() > 0) {
            this.a.j = false;
        } else {
            this.a.j = true;
        }
        this.a.k = false;
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        this.a.r.leftMargin = num.intValue();
        this.a.n.setLayoutParams(this.a.r);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onProgressUpdate(Integer... numArr) {
        this.a.r.leftMargin = numArr[0].intValue();
        this.a.n.setLayoutParams(this.a.r);
        this.a.h();
    }
}
