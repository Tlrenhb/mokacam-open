package com.aee.mokacam.utils;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static float a(byte[] bArr) {
        if (bArr == null || bArr.length != 4) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是4位!");
        }
        return Float.intBitsToFloat(b(bArr));
    }

    public static byte[] a(int i) {
        return new byte[]{(byte) (i & 255), (byte) (i >> 8)};
    }

    public static int b(byte[] bArr) {
        if (bArr == null || bArr.length != 4) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是4位!");
        }
        return ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[1] & 255) << 8) | (bArr[0] & 255);
    }
}
