package com.aee.mokacam.bean;

import androidx.core.view.MotionEventCompat;
import com.aee.mokacam.AeeApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class m {
    public int A = 5;
    public byte[] w;
    public int x;
    public int y;
    public byte[] z;
    public static final byte[] u = {-2, -1, -1, 1, 1};
    public static final int[] v = {16, 70, 147, 154, 22, -1010, 86, 47, 44, 39};
    public static int B = 100;

    public m() {
    }

    public m(byte[] bArr) {
        int i = bArr[1] & 255;
        byte[] bArr2 = new byte[this.A];
        for (int i2 = 0; i2 < this.A; i2++) {
            bArr2[i2] = bArr[i2];
        }
        this.w = u;
        System.arraycopy(bArr2, 0, this.w, 0, this.A);
        this.y = bArr[this.A] & 255;
        this.z = new byte[i];
        System.arraycopy(bArr, this.A + 1, this.z, 0, i);
        this.x = i + 8;
    }

    private static int[] a(byte[] bArr, int i) {
        int i2 = 0;
        int[] iArr = new int[B];
        for (int i3 = 0; i3 < i; i3++) {
            if (bArr[i3] == -2 && i2 < B) {
                if (i3 + 1 >= i) {
                    break;
                }
                if (bArr[i3 + 1] + 8 + i3 <= i) {
                    iArr[i2] = i3 + 1;
                    i2++;
                }
            } else {
                if (i2 >= B) {
                    break;
                }
            }
        }
        return iArr;
    }

    public static int b(byte[] bArr) {
        return AeeApplication.a().GetCrc(bArr);
    }

    public static List<m> c(byte[] bArr) {
        int length = bArr.length;
        ArrayList arrayList = new ArrayList();
        if (bArr == null) {
            return arrayList;
        }
        int[] iArr = new int[B];
        int[] iArrA = a(bArr, length);
        for (int i = 0; i < B; i++) {
            if (iArrA[i] > 0) {
                int i2 = iArrA[i] - 1;
                int i3 = bArr[iArrA[i]] & 255;
                byte[] bArr2 = new byte[i3 + 8];
                if (i2 + i3 + 8 <= length) {
                    System.arraycopy(bArr, i2, bArr2, 0, i3 + 8);
                    if (AeeApplication.a().bm && d(bArr2)) {
                        m mVar = new m(bArr2);
                        com.aee.mokacam.utils.m.a("test", "20160428--mvdata" + com.aee.mokacam.utils.k.a(com.aee.mokacam.utils.k.a(bArr2).toUpperCase(), 2));
                        arrayList.add(mVar);
                    } else {
                        arrayList.add(new m(bArr2));
                    }
                }
                if (i > 20) {
                    com.aee.mokacam.utils.m.b("test", "20161114--mvdata" + com.aee.mokacam.utils.k.a(com.aee.mokacam.utils.k.a(bArr).toUpperCase(), 2));
                }
            }
        }
        return arrayList;
    }

    private static boolean d(byte[] bArr) {
        int i;
        int i2;
        int length = bArr.length;
        int iB = b(bArr);
        if (iB >= 0) {
            i2 = iB & 255;
            i = (iB & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        } else {
            i = 0;
            i2 = 0;
        }
        return bArr[length + (-2)] == i2 && bArr[length + (-1)] == i;
    }

    public void a(byte[] bArr) {
        this.z = bArr;
    }

    public byte[] b() {
        byte b;
        byte b2 = 0;
        byte[] bArr = new byte[this.x];
        System.arraycopy(this.w, 0, bArr, 0, 5);
        System.arraycopy(new byte[]{(byte) this.y}, 0, bArr, 5, 1);
        System.arraycopy(this.z, 0, bArr, 6, this.z.length);
        int iB = b(bArr);
        com.aee.mokacam.utils.m.a("send_ptz", "发送数据1 icrc=：" + iB);
        if (iB >= 0) {
            b2 = (byte) (iB & 255);
            b = (byte) ((iB & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
            com.aee.mokacam.utils.m.a("send_ptz", "发送数据1 icrc1=：" + ((int) b2));
            com.aee.mokacam.utils.m.a("send_ptz", "发送数据1 icrc2=：" + ((int) b));
        } else {
            b = 0;
        }
        bArr[this.x - 2] = b2;
        bArr[this.x - 1] = b;
        com.aee.mokacam.utils.m.a("send_ptz", "发送数据：" + com.aee.mokacam.utils.k.a(com.aee.mokacam.utils.k.a(bArr).toUpperCase(), 2));
        return bArr;
    }

    public String toString() {
        return "MVLinkData [head=" + Arrays.toString(this.w) + ", length=" + this.x + ", cmdType=" + this.y + ", cmdContent=" + Arrays.toString(this.z) + "]";
    }
}
