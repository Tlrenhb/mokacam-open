package com.aee.mokacam.bean;

import androidx.core.view.MotionEventCompat;

/* JADX INFO: loaded from: classes.dex */
public class k extends m {
    public static final byte[] a = {-2, 53, 98, -1, 2};
    private static int b = 53;

    @Override // com.aee.mokacam.bean.m
    public byte[] b() {
        byte b2;
        byte b3 = 0;
        byte[] bArr = new byte[this.x];
        System.arraycopy(this.w, 0, bArr, 0, 5);
        System.arraycopy(new byte[]{(byte) this.y}, 0, bArr, 5, 1);
        System.arraycopy(this.z, 0, bArr, 6, this.z.length);
        int iB = b(bArr);
        com.aee.mokacam.utils.m.a("send_ptz", "发送数据1 icrc=：" + iB);
        if (iB >= 0) {
            b3 = (byte) (iB & 255);
            b2 = (byte) ((iB & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
            com.aee.mokacam.utils.m.a("send_ptz", "发送数据1 icrc1=：" + ((int) b3));
            com.aee.mokacam.utils.m.a("send_ptz", "发送数据1 icrc2=：" + ((int) b2));
        } else {
            b2 = 0;
        }
        bArr[this.x - 2] = b3;
        bArr[this.x - 1] = b2;
        com.aee.mokacam.utils.m.a("send_ptz", "发送数据：" + com.aee.mokacam.utils.k.a(com.aee.mokacam.utils.k.a(bArr).toUpperCase(), 2));
        return bArr;
    }
}
