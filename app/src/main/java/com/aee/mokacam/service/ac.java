package com.aee.mokacam.service;

import android.content.Context;
import com.aee.mokacam.AeeApplication;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
public class ac {
    static ac b;
    static String c = "192.168.1.1";
    static int d = 8888;
    static InputStream f;
    static OutputStream g;
    static al h;
    static Context i;
    Socket e;
    boolean j;
    public boolean a = false;
    boolean k = false;
    boolean l = false;
    boolean m = false;

    public ac() {
        this.j = false;
        this.j = true;
    }

    public static ac a() {
        if (b == null) {
            b = new ac();
            AeeApplication.a();
            AeeApplication.a();
            i = AeeApplication.b();
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: */
    public void i() {
        new Thread(new af(this)).start();
    }

    synchronized boolean a(byte[] bArr) {
        boolean z;
        try {
            if (g == null && this.e != null) {
                g = this.e.getOutputStream();
            }
            g.write(bArr);
            g.flush();
            z = true;
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        }
        return z;
    }

    public synchronized void b() {
        new Thread(new ad(this)).start();
    }

    public void c() {
        new Thread(new ae(this)).start();
    }

    void d() {
        this.j = false;
        AeeApplication.a().bi = false;
        try {
            if (f != null) {
                f.close();
                f = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (g != null) {
                g.close();
                g = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            if (this.e != null) {
                this.e.close();
                this.e = null;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
