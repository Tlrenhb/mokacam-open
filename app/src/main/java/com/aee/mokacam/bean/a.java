package com.aee.mokacam.bean;

import androidx.core.view.MotionEventCompat;
import com.aee.mokacam.AeeApplication;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static final byte[] a = {-2, 18, 98, -1, 2};
    public static final byte[] b = {-2, -1, -1, 1, 1};
    public static final int[] c = {16, 70, 15, 154};
    public byte[] d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public byte[] l;
    public int m;
    public int n;

    public a() {
        this.g = 0;
        this.h = 1500;
        this.i = 1500;
        this.j = 1500;
        this.k = 1500;
        this.m = 12;
        this.n = 5;
    }

    public a(int i, byte[] bArr) {
        this.g = 0;
        this.h = 1500;
        this.i = 1500;
        this.j = 1500;
        this.k = 1500;
        this.m = 12;
        this.n = 5;
        this.d = a;
        this.f = i;
        if (bArr == null) {
            this.e = 8;
        } else {
            this.l = bArr;
            this.e = this.l.length + 8;
        }
    }

    public void a(int i) {
        this.d[4] = (byte) (i & 15);
    }

    public void a(byte[] bArr) {
        this.l = bArr;
        this.e = this.l.length + 8;
    }

    public byte[] a() {
        byte[] bArr = new byte[18];
        System.arraycopy(com.aee.mokacam.utils.c.a(this.j), 0, bArr, 0, 2);
        System.arraycopy(com.aee.mokacam.utils.c.a(this.i), 0, bArr, 2, 2);
        System.arraycopy(com.aee.mokacam.utils.c.a(this.k), 0, bArr, 4, 2);
        System.arraycopy(com.aee.mokacam.utils.c.a(this.h), 0, bArr, 6, 2);
        for (int i = 8; i < 18; i++) {
            bArr[i] = 0;
        }
        return bArr;
    }

    public int b(byte[] bArr) {
        return AeeApplication.a().GetCrc(bArr);
    }

    public byte[] b() {
        a(a());
        byte[] bArrC = c();
        com.aee.mokacam.utils.m.a("20151018", "----" + toString() + "\n" + Arrays.toString(bArrC) + "\n---" + toString());
        return bArrC;
    }

    public byte[] c() {
        byte b2;
        byte b3 = 0;
        byte[] bArr = new byte[this.e];
        System.arraycopy(this.d, 0, bArr, 0, 5);
        System.arraycopy(new byte[]{(byte) this.f}, 0, bArr, 5, 1);
        System.arraycopy(this.l, 0, bArr, 6, this.l.length);
        int iB = b(bArr);
        if (iB >= 0) {
            b3 = (byte) (iB & 255);
            b2 = (byte) ((iB & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
        } else {
            b2 = 0;
        }
        bArr[this.e - 2] = b3;
        bArr[this.e - 1] = b2;
        return bArr;
    }

    public String toString() {
        return "PTZData [head=" + Arrays.toString(this.d) + ", length=" + this.e + ", cmdType=" + this.f + ", cmdContent=" + Arrays.toString(this.l) + "]";
    }
}
