package com.aee.mokacam.photoview;

import android.annotation.TargetApi;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(14)
public class j extends h {
    public j(Context context) {
        super(context);
    }

    @Override // com.aee.mokacam.photoview.h, com.aee.mokacam.photoview.ab
    public boolean a() {
        return this.a.computeScrollOffset();
    }
}
