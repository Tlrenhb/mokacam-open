package com.aee.mokacam.bean;

import com.aee.mokacam.utils.s;

/* JADX INFO: loaded from: classes.dex */
public class e extends m {
    public static final byte[] a = {-2, 13, 98, -1, 2};
    public static int c = 26;
    public byte[] d;
    public float b = 0.0f;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private long m = 0;
    private int n = 0;
    private int o = 0;
    private int p = 0;
    private int q = 0;
    private int r = 0;
    private int s = 0;
    private int t = 0;
    private int C = 0;
    private int D = 0;
    private int E = 0;
    private int F = 0;
    private int G = 0;

    public static e a(m mVar) {
        e eVar = new e();
        eVar.w = mVar.w;
        eVar.x = mVar.x;
        eVar.d = mVar.z;
        int iD = com.aee.mokacam.utils.k.d(0, eVar.d);
        eVar.a(iD);
        eVar.e = iD;
        eVar.f = com.aee.mokacam.utils.k.d(1, eVar.d);
        eVar.g = com.aee.mokacam.utils.k.d(2, eVar.d);
        int iD2 = com.aee.mokacam.utils.k.d(3, eVar.d);
        eVar.h = iD2;
        System.out.println("iCurrentStation-------------->" + iD2);
        eVar.i = com.aee.mokacam.utils.k.b(4, eVar.d);
        eVar.j = com.aee.mokacam.utils.k.b(6, eVar.d);
        int iB = com.aee.mokacam.utils.k.b(8, eVar.d);
        eVar.k = iB;
        eVar.b(iB);
        eVar.l = com.aee.mokacam.utils.k.b(10, eVar.d);
        eVar.m = com.aee.mokacam.utils.k.a(eVar.d, 12);
        eVar.n = com.aee.mokacam.utils.k.d(16, eVar.d);
        com.aee.mokacam.utils.s.a("video_resolution_id", eVar.n);
        eVar.o = com.aee.mokacam.utils.k.d(17, eVar.d);
        com.aee.mokacam.utils.s.a("photo_size_id", eVar.o);
        eVar.p = com.aee.mokacam.utils.k.d(18, eVar.d);
        eVar.q = com.aee.mokacam.utils.k.d(19, eVar.d);
        com.aee.mokacam.utils.s.a("tv_output", eVar.q);
        System.out.println("N制---P制----》iTVOutput：" + eVar.q);
        eVar.r = com.aee.mokacam.utils.k.d(20, eVar.d);
        System.out.println("画面显示时间-----》iVideoStamp：" + eVar.r);
        eVar.s = com.aee.mokacam.utils.k.d(21, eVar.d);
        eVar.t = com.aee.mokacam.utils.k.d(22, eVar.d);
        eVar.C = com.aee.mokacam.utils.k.d(23, eVar.d);
        eVar.D = com.aee.mokacam.utils.k.d(24, eVar.d);
        System.out.println("录影内容循环覆盖----》iVideoLoop：" + eVar.D);
        eVar.E = com.aee.mokacam.utils.k.d(25, eVar.d);
        return eVar;
    }

    public int a() {
        return this.G;
    }

    public void a(int i) {
        this.G = i;
    }

    public void b(int i) {
        this.F = i;
    }

    public int c() {
        return this.p;
    }

    public int d() {
        return this.n;
    }

    public int e() {
        return this.h;
    }

    public int f() {
        return this.q;
    }

    public int g() {
        return this.t;
    }
}
