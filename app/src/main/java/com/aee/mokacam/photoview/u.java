package com.aee.mokacam.photoview;

import android.content.Context;
import android.graphics.RectF;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
class u implements Runnable {
    final /* synthetic */ r a;
    private final ab b;
    private int c;
    private int d;

    public u(r rVar, Context context) {
        this.a = rVar;
        this.b = ab.a(context);
    }

    public void a() {
        this.b.a(true);
    }

    public void a(int i, int i2, int i3, int i4) {
        int iRound;
        int i5;
        int iRound2;
        int i6;
        RectF rectFB = this.a.b();
        if (rectFB == null) {
            return;
        }
        int iRound3 = Math.round(-rectFB.left);
        if (i < rectFB.width()) {
            iRound = Math.round(rectFB.width() - i);
            i5 = 0;
        } else {
            iRound = iRound3;
            i5 = iRound3;
        }
        int iRound4 = Math.round(-rectFB.top);
        if (i2 < rectFB.height()) {
            iRound2 = Math.round(rectFB.height() - i2);
            i6 = 0;
        } else {
            iRound2 = iRound4;
            i6 = iRound4;
        }
        this.c = iRound3;
        this.d = iRound4;
        if (iRound3 == iRound && iRound4 == iRound2) {
            return;
        }
        this.b.a(iRound3, iRound4, i3, i4, i5, iRound, i6, iRound2, 0, 0);
    }

    @Override // java.lang.Runnable
    public void run() {
        ImageView imageViewC;
        if (this.b.b() || (imageViewC = this.a.c()) == null || !this.b.a()) {
            return;
        }
        int iC = this.b.c();
        int iD = this.b.d();
        this.a.o.postTranslate(this.c - iC, this.d - iD);
        this.a.b(this.a.o());
        this.c = iC;
        this.d = iD;
        a.a(imageViewC, this);
    }
}
