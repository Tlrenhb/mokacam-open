package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
public class w {
    public static ac b;
    static w d;
    protected final String a = "FlightCMD";
    public boolean c = false;

    public w() {
        b = ac.a();
    }

    public static w a() {
        if (d == null) {
            d = new w();
            if (!AeeApplication.a().bi) {
                b.a = true;
                b.b();
                com.aee.mokacam.utils.m.b("test", "20161009--------FlightCMD getInstance()");
            }
        }
        return d;
    }

    public void b() {
        if (b != null) {
            b.d();
        }
        b.a = false;
        if (d != null) {
            d = null;
        }
        if (b != null) {
            b = null;
        }
    }
}
