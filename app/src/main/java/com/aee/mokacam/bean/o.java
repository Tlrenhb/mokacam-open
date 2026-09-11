package com.aee.mokacam.bean;

import androidx.core.view.MotionEventCompat;

/* JADX INFO: loaded from: classes.dex */
public class o extends a {
    public static final byte[] o = {-2, 3, 98, -1, 2};
    public byte p;
    public byte q;
    public byte r;

    @Override // com.aee.mokacam.bean.a
    public void a(byte[] bArr) {
        this.e = bArr.length + 8;
        super.a(bArr);
    }

    @Override // com.aee.mokacam.bean.a
    public byte[] c() {
        byte b;
        byte b2 = 0;
        byte[] bArr = new byte[this.e];
        System.arraycopy(this.d, 0, bArr, 0, 5);
        System.arraycopy(new byte[]{(byte) this.f}, 0, bArr, 5, 1);
        System.arraycopy(this.l, 0, bArr, 6, this.l.length);
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
        bArr[this.e - 2] = b2;
        bArr[this.e - 1] = b;
        com.aee.mokacam.utils.m.a("send_ptz", "发送数据：" + com.aee.mokacam.utils.k.a(com.aee.mokacam.utils.k.a(bArr).toUpperCase(), 2));
        return bArr;
    }

    public byte[] d() {
        byte[] bArr = {this.p, this.q, this.r};
        System.arraycopy(bArr, 0, this.l, 0, bArr.length);
        return bArr;
    }
}
