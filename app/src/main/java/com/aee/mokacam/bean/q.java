package com.aee.mokacam.bean;

import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes.dex */
public class q extends m {
    private float d;
    private float e;
    private float f;
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private float g = 20.0f;
    private float h = 3.0f;

    public static float a(byte[] bArr, int i) {
        return Float.intBitsToFloat((int) (((long) (((int) (((long) (((int) (((long) (bArr[i + 0] & 255)) | (((long) bArr[i + 1]) << 8))) & 65535)) | (((long) bArr[i + 2]) << 16))) & ViewCompat.MEASURED_SIZE_MASK)) | (((long) bArr[i + 3]) << 24)));
    }

    public static q a(m mVar) {
        q qVar = new q();
        qVar.w = mVar.w;
        qVar.x = mVar.x;
        qVar.y = mVar.y;
        qVar.a(mVar.z);
        com.aee.mokacam.utils.m.b("test", "20161017----GetTaskInfo ttttttt=" + com.aee.mokacam.utils.k.a(com.aee.mokacam.utils.k.a(qVar.z).toUpperCase(), 2) + "\n");
        qVar.a(com.aee.mokacam.utils.k.c(0, qVar.z));
        com.aee.mokacam.utils.k.c(16, qVar.z);
        byte[] bArr = new byte[4];
        System.arraycopy(qVar.z, 16, bArr, 0, 4);
        com.aee.mokacam.utils.m.b("test", "20161017----GetTaskInfo ttttttt1=" + com.aee.mokacam.utils.k.a(com.aee.mokacam.utils.k.a(bArr).toUpperCase(), 2) + "\n");
        qVar.b(a(bArr, 0));
        qVar.c(com.aee.mokacam.utils.k.c(20, qVar.z));
        qVar.d(com.aee.mokacam.utils.k.c(24, qVar.z));
        qVar.a(com.aee.mokacam.utils.k.b(28, qVar.z));
        return qVar;
    }

    public float a() {
        return this.e;
    }

    public void a(float f) {
        this.h = f;
    }

    public void a(int i) {
        this.b = i;
    }

    public void b(float f) {
        this.e = f;
    }

    public float c() {
        return this.d;
    }

    public void c(float f) {
        this.d = f;
    }

    public int d() {
        return this.b;
    }

    public void d(float f) {
        this.f = f;
    }
}
