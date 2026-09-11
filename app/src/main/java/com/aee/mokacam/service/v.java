package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
public class v {
    public static x b;
    private static v d;
    protected final String a = "FlightCMD";
    public boolean c = false;
    private long e = 0;
    private long f = 100;

    public v() {
        b = x.c();
    }

    public static v a() {
        if (d == null) {
            d = new v();
            if (!AeeApplication.a().bi) {
                b.l = true;
                b.f();
                com.aee.mokacam.utils.m.b("test", "20161009--------FlightCMD getInstance()");
            }
        }
        return d;
    }

    public void b() {
        if (b != null) {
            b.j();
        }
        b.l = false;
        if (d != null) {
            d = null;
        }
        if (b != null) {
            b = null;
        }
    }
}
