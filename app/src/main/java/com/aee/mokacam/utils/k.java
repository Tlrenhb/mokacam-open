package com.aee.mokacam.utils;

/* JADX INFO: loaded from: classes.dex */
public class k {
    static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static float a(int i) {
        return i >= 32768 ? (float) (((double) (i - 32768)) * (-0.1d)) : (float) (0.1d * ((double) i));
    }

    public static int a(int i, byte[] bArr) {
        int iRound = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            iRound += Math.round((bArr[i + i2] & 255) << (i2 * 8));
        }
        return (int) (iRound < 0 ? ((double) iRound) + Math.pow(2.0d, 32.0d) : iRound);
    }

    public static int a(byte[] bArr, long j, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i + i3] = (byte) j;
            j >>= 8;
        }
        return i2;
    }

    public static long a(byte[] bArr, int i) {
        int[] iArr = new int[4];
        for (int i2 = 0; i2 < 4; i2++) {
            iArr[i2] = bArr[i2 + i] & 255;
        }
        return ((long) (iArr[0] | (iArr[1] << 8) | (iArr[2] << 16) | (iArr[3] << 24))) & 4294967295L;
    }

    public static String a(String str, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length() / i;
        int length2 = str.length() % i;
        for (int i2 = 0; i2 < length; i2++) {
            stringBuffer.append(String.valueOf(str.substring(i2 * i, (i2 + 1) * i)) + " ");
        }
        if (length2 != 0) {
            stringBuffer.append(String.valueOf(str.substring(length * i, str.length())) + " ");
        }
        return stringBuffer.toString();
    }

    public static String a(byte[] bArr) {
        return a(bArr, true);
    }

    public static String a(byte[] bArr, boolean z) {
        return b(bArr, z ? a : b);
    }

    protected static char[] a(byte[] bArr, char[] cArr) {
        int i = 0;
        if (bArr == null) {
            return new char[0];
        }
        int length = bArr.length;
        char[] cArr2 = new char[length << 1];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & 15];
        }
        return cArr2;
    }

    public static float b(int i) {
        return (float) (0.1d * ((double) i));
    }

    public static int b(int i, byte[] bArr) {
        int iRound = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            iRound += Math.round((bArr[i + i2] & 255) << (i2 * 8));
        }
        return (int) (iRound < 0 ? ((double) iRound) + Math.pow(2.0d, 16.0d) : iRound);
    }

    public static int b(byte[] bArr, int i) {
        return (short) (((((byte) (bArr[i + 1] & 255)) & 255) << 8) | (((byte) (bArr[i] & 255)) & 255));
    }

    protected static String b(byte[] bArr, char[] cArr) {
        return new String(a(bArr, cArr));
    }

    public static float c(int i, byte[] bArr) {
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, i, bArr2, 0, 4);
        return c.a(bArr2);
    }

    public static String c(byte[] bArr, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = (char) bArr[i2];
        }
        stringBuffer.append(cArr);
        return stringBuffer.toString();
    }

    public static int d(int i, byte[] bArr) {
        return 0 + Math.round(bArr[i] & 255);
    }
}
