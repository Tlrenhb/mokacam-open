package com.aee.mokacam.bean;

/* JADX INFO: loaded from: classes.dex */
public class r extends m {
    public static final byte[] a = {-2, 3, 98, -1, 2};
    private int b;
    private int c;
    private int d = -1;

    public static r a(m mVar) {
        r rVar = new r();
        rVar.w = mVar.w;
        rVar.x = mVar.x;
        rVar.z = mVar.z;
        rVar.b = mVar.z[0];
        rVar.c = mVar.z[1];
        rVar.d = mVar.z[2];
        return rVar;
    }

    public int a() {
        return this.d;
    }
}
