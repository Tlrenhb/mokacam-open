package com.aee.mokacam.bean;

import java.text.DecimalFormat;

/* JADX INFO: loaded from: classes.dex */
public class f extends m {
    private int a;
    private long b;
    private int c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;

    public static f a(m mVar) {
        new DecimalFormat("##.0000");
        f fVar = new f();
        fVar.w = mVar.w;
        fVar.x = mVar.x;
        fVar.y = mVar.y;
        fVar.z = mVar.z;
        fVar.h(com.aee.mokacam.utils.k.a(com.aee.mokacam.utils.k.b(0, fVar.z)));
        fVar.g(com.aee.mokacam.utils.k.a(com.aee.mokacam.utils.k.b(2, fVar.z)));
        fVar.f(com.aee.mokacam.utils.k.a(com.aee.mokacam.utils.k.b(4, fVar.z)));
        fVar.b(com.aee.mokacam.utils.k.b(com.aee.mokacam.utils.k.b(fVar.z, 6)));
        fVar.a(com.aee.mokacam.utils.k.a(com.aee.mokacam.utils.k.d(8, fVar.z)));
        fVar.c(com.aee.mokacam.utils.k.b(com.aee.mokacam.utils.k.b(fVar.z, 9)));
        fVar.d((float) (com.aee.mokacam.utils.k.a(fVar.z, 12) / Math.pow(10.0d, 7.0d)));
        fVar.e((float) (com.aee.mokacam.utils.k.a(fVar.z, 16) / Math.pow(10.0d, 7.0d)));
        fVar.b(com.aee.mokacam.utils.k.d(20, fVar.z));
        fVar.a(com.aee.mokacam.utils.k.d(21, fVar.z));
        fVar.a(com.aee.mokacam.utils.k.a(fVar.z, 22));
        return fVar;
    }

    public long a() {
        return this.b;
    }

    public void a(float f) {
        this.d = f;
    }

    public void a(int i) {
        this.c = i;
    }

    public void a(long j) {
        this.b = j;
    }

    public void b(float f) {
        this.e = f;
    }

    public void b(int i) {
        this.m = i;
    }

    public int c() {
        return this.c;
    }

    public void c(float f) {
        this.f = f;
    }

    public float d() {
        return this.g;
    }

    public void d(float f) {
        this.g = f;
    }

    public float e() {
        return this.h;
    }

    public void e(float f) {
        this.h = f;
    }

    public void f(float f) {
        this.i = f;
    }

    public void g(float f) {
        this.j = f;
    }

    public void h(float f) {
        this.k = f;
    }

    @Override // com.aee.mokacam.bean.m
    public String toString() {
        return "DataPackage3 [cmdPackage1Head=" + this.a + ", statuWord=" + this.b + ", voltage=" + this.c + ", speed=" + this.d + ", distance=" + this.e + ", height=" + this.f + ", lng=" + this.g + ", lat=" + this.h + ", yaw=" + this.i + ", pitch=" + this.j + ", roll=" + this.k + ", temperature=" + this.l + ", gpsNum=" + this.m + ", ptz_yaw=" + this.n + ", ptz_pitch=" + this.o + ", ptz_roll=" + this.p + ", package2Reversed4=" + this.q + ", package2Reversed5=" + this.r + "]";
    }
}
