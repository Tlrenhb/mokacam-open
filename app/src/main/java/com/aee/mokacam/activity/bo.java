package com.aee.mokacam.activity;

import android.graphics.Bitmap;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.FailReason;

/* JADX INFO: loaded from: classes.dex */
public class bo implements com.nostra13.universalimageloader.core.d.a {
    final /* synthetic */ bn a;
    private int b;

    public bo(bn bnVar, int i) {
        this.a = bnVar;
        this.b = i;
    }

    @Override // com.nostra13.universalimageloader.core.d.a
    public void a(String str, View view) {
    }

    @Override // com.nostra13.universalimageloader.core.d.a
    public void a(String str, View view, Bitmap bitmap) {
        if (this.b <= this.a.a.s.size()) {
            ((com.aee.mokacam.bean.g) this.a.a.s.get(this.b)).f = bitmap;
        }
    }

    @Override // com.nostra13.universalimageloader.core.d.a
    public void a(String str, View view, FailReason failReason) {
    }

    @Override // com.nostra13.universalimageloader.core.d.a
    public void b(String str, View view) {
    }
}
