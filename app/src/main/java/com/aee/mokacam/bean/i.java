package com.aee.mokacam.bean;

/* JADX INFO: loaded from: classes.dex */
public class i {
    public static final byte[] a = {-52, -128, -128, -128, -128, 0};
    private byte[] b;

    public int a() {
        int i = 0;
        int i2 = 0;
        while (i2 < 5) {
            i = i2 == 0 ? this.b[i2 + 1] & 255 : i ^ (this.b[i2 + 1] & 255);
            i2++;
        }
        return i;
    }

    public byte[] b() {
        byte[] bArr = new byte[8];
        this.b[6] = (byte) (a() & 255);
        System.arraycopy(this.b, 0, bArr, 0, this.b.length);
        this.b[7] = 51;
        return bArr;
    }
}
