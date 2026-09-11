package com.facebook.rebound;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
public class i {
    private static int a = 0;
    private k b;
    private boolean c;
    private final String d;
    private double h;
    private double i;
    private final f o;
    private final j e = new j(null);
    private final j f = new j(null);
    private final j g = new j(null);
    private boolean j = true;
    private double k = 0.005d;
    private double l = 0.005d;
    private CopyOnWriteArraySet<m> m = new CopyOnWriteArraySet<>();
    private double n = 0.0d;

    i(f fVar) {
        if (fVar == null) {
            throw new IllegalArgumentException("Spring cannot be created outside of a BaseSpringSystem");
        }
        this.o = fVar;
        StringBuilder sb = new StringBuilder("spring:");
        int i = a;
        a = i + 1;
        this.d = sb.append(i).toString();
        this.b = k.c;
    }

    private double a(j jVar) {
        return Math.abs(this.i - jVar.a);
    }

    private void e(double d) {
        this.e.a = (this.e.a * d) + (this.f.a * (1.0d - d));
        this.e.b = (this.e.b * d) + (this.f.b * (1.0d - d));
    }

    public i a(double d) {
        this.h = d;
        this.e.a = d;
        this.o.a(a());
        Iterator<m> it = this.m.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
        return this;
    }

    public i a(k kVar) {
        if (kVar == null) {
            throw new IllegalArgumentException("springConfig is required");
        }
        this.b = kVar;
        return this;
    }

    public i a(m mVar) {
        if (mVar == null) {
            throw new IllegalArgumentException("newListener is required");
        }
        this.m.add(mVar);
        return this;
    }

    public String a() {
        return this.d;
    }

    public double b() {
        return this.e.a;
    }

    public i b(double d) {
        if (this.i != d || !g()) {
            this.h = b();
            this.i = d;
            this.o.a(a());
            Iterator<m> it = this.m.iterator();
            while (it.hasNext()) {
                it.next().d(this);
            }
        }
        return this;
    }

    public i b(m mVar) {
        if (mVar == null) {
            throw new IllegalArgumentException("listenerToRemove is required");
        }
        this.m.remove(mVar);
        return this;
    }

    public double c() {
        return this.i;
    }

    public i c(double d) {
        this.e.b = d;
        this.o.a(a());
        return this;
    }

    void d(double d) {
        boolean z;
        boolean z2;
        boolean zG = g();
        if (zG && this.j) {
            return;
        }
        if (d > 0.064d) {
            d = 0.064d;
        }
        this.n += d;
        double d2 = this.b.b;
        double d3 = this.b.a;
        double d4 = this.e.a;
        double d5 = this.e.b;
        double d6 = this.g.a;
        double d7 = this.g.b;
        while (this.n >= 0.001d) {
            this.n -= 0.001d;
            if (this.n < 0.001d) {
                this.f.a = d4;
                this.f.b = d5;
            }
            double d8 = ((this.i - d6) * d2) - (d3 * d5);
            double d9 = d5 + (0.001d * d8 * 0.5d);
            double d10 = ((this.i - (((0.001d * d5) * 0.5d) + d4)) * d2) - (d3 * d9);
            double d11 = d5 + (0.001d * d10 * 0.5d);
            double d12 = ((this.i - (((0.001d * d9) * 0.5d) + d4)) * d2) - (d3 * d11);
            d6 = d4 + (0.001d * d11);
            d7 = (0.001d * d12) + d5;
            d4 += (((d9 + d11) * 2.0d) + d5 + d7) * 0.16666666666666666d * 0.001d;
            d5 += (d8 + ((d10 + d12) * 2.0d) + (((this.i - d6) * d2) - (d3 * d7))) * 0.16666666666666666d * 0.001d;
        }
        this.g.a = d6;
        this.g.b = d7;
        this.e.a = d4;
        this.e.b = d5;
        if (this.n > 0.0d) {
            e(this.n / 0.001d);
        }
        if (g() || (this.c && d())) {
            this.h = this.i;
            this.e.a = this.i;
            c(0.0d);
            z = true;
        } else {
            z = zG;
        }
        if (this.j) {
            this.j = false;
            z2 = true;
        } else {
            z2 = false;
        }
        boolean z3 = false;
        if (z) {
            this.j = true;
            z3 = true;
        }
        for (m mVar : this.m) {
            if (z2) {
                mVar.c(this);
            }
            mVar.a(this);
            if (z3) {
                mVar.b(this);
            }
        }
    }

    public boolean d() {
        return (this.h < this.i && b() > this.i) || (this.h > this.i && b() < this.i);
    }

    public boolean e() {
        return (g() && f()) ? false : true;
    }

    public boolean f() {
        return this.j;
    }

    public boolean g() {
        return Math.abs(this.e.b) <= this.k && a(this.e) <= this.l;
    }
}
