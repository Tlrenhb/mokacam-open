package com.aee.mokacam.bean;

import androidx.core.view.MotionEventCompat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class d extends m {
    public static final byte[] a = {-2, 13, 98, -1, 2};
    public static int r = 13;
    public byte[] s;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public int f = 3;
    public int g = 0;
    public int h = 0;
    public int i = 1;
    public int j = 0;
    public int k = 0;
    public int l = 0;
    public int m = 0;
    public int n = 0;
    public boolean o = false;
    public boolean p = false;
    public float q = 0.0f;
    public final int t = 155;

    public d() {
    }

    public d(int i) {
        this.w = a;
        this.y = 155;
        byte[] bArr = new byte[r];
        byte[] bArr2 = new byte[4];
        bArr2[0] = 40;
        bArr[0] = 0;
        bArr[1] = 0;
        bArr[2] = 0;
        bArr[3] = 0;
        System.arraycopy(bArr2, 0, bArr, 4, bArr2.length);
        if (i == 1) {
            bArr[4] = 32;
        } else if (i == 2) {
            bArr[4] = 16;
        } else if (i == 3) {
            bArr[4] = 96;
        }
        for (int i2 = 8; i2 < r; i2++) {
            bArr[i2] = 0;
        }
        this.s = bArr;
        this.x = r + 8;
    }

    public static Date a(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void a() {
        this.w = a;
        this.y = 155;
        byte[] bArr = new byte[r];
        byte[] bArr2 = new byte[4];
        bArr[0] = 0;
        bArr[1] = 0;
        bArr[2] = 0;
        bArr[3] = 0;
        System.arraycopy(bArr2, 0, bArr, 4, bArr2.length);
        this.s = bArr;
        this.x = r + 8;
    }

    public void a(int i) {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        this.w = a;
        this.y = 155;
        byte[] bArr = new byte[r];
        byte[] bArr2 = new byte[4];
        bArr[0] = 0;
        bArr[1] = 0;
        bArr[2] = 0;
        bArr[3] = 0;
        System.arraycopy(bArr2, 0, bArr, 4, bArr2.length);
        bArr[7] = (byte) ((i + 1) * 16);
        com.aee.mokacam.utils.k.a(bArr, jCurrentTimeMillis - (a("2016-01-01 00:00:00").getTime() / 1000), 8, 4);
        this.s = bArr;
        this.x = r + 8;
    }

    @Override // com.aee.mokacam.bean.m
    public byte[] b() {
        byte b;
        byte b2 = 0;
        byte[] bArr = new byte[this.x];
        System.arraycopy(this.w, 0, bArr, 0, 5);
        System.arraycopy(new byte[]{(byte) this.y}, 0, bArr, 5, 1);
        System.arraycopy(this.s, 0, bArr, 6, this.s.length);
        int iB = b(bArr);
        if (iB >= 0) {
            b2 = (byte) (iB & 255);
            b = (byte) ((iB & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
        } else {
            b = 0;
        }
        bArr[this.x - 2] = b2;
        bArr[this.x - 1] = b;
        return bArr;
    }
}
