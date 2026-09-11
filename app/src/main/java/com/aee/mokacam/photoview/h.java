package com.aee.mokacam.photoview;

import android.annotation.TargetApi;
import android.content.Context;
import android.widget.OverScroller;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(9)
public class h extends ab {
    protected final OverScroller a;

    public h(Context context) {
        this.a = new OverScroller(context);
    }

    @Override // com.aee.mokacam.photoview.ab
    public void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.a.fling(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
    }

    @Override // com.aee.mokacam.photoview.ab
    public void a(boolean z) {
        this.a.forceFinished(z);
    }

    @Override // com.aee.mokacam.photoview.ab
    public boolean a() {
        return this.a.computeScrollOffset();
    }

    @Override // com.aee.mokacam.photoview.ab
    public boolean b() {
        return this.a.isFinished();
    }

    @Override // com.aee.mokacam.photoview.ab
    public int c() {
        return this.a.getCurrX();
    }

    @Override // com.aee.mokacam.photoview.ab
    public int d() {
        return this.a.getCurrY();
    }
}
