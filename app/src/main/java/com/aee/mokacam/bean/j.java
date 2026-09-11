package com.aee.mokacam.bean;

import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class j extends m {
    public static final byte[] a = {-2, 25, 98, -1, 2};
    private float d;
    public int b = 0;
    private String c = BuildConfig.FLAVOR;
    private int e = 0;

    public static j a(m mVar) {
        j jVar = new j();
        jVar.w = mVar.w;
        jVar.z = mVar.z;
        jVar.x = mVar.x;
        return jVar;
    }

    public int a(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i && bArr[i3] != 0; i3++) {
            i2++;
        }
        return i2;
    }

    public void a() {
        byte[] bArr = new byte[16];
        System.arraycopy(this.z, 8, bArr, 0, 16);
        this.c = com.aee.mokacam.utils.k.c(bArr, a(bArr, 16));
        this.d = com.aee.mokacam.utils.k.c(0, this.z);
    }

    public String c() {
        return this.c;
    }

    public float d() {
        return this.d;
    }
}
