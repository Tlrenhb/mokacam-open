package com.aee.mokacam.service;

import android.content.Context;
import com.aee.mokacam.AeeApplication;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
public class ac {
    private static ac b;
    private static String c = "192.168.1.1";
    private static int d = 8888;
    private static InputStream f;
    private static OutputStream g;
    private static al h;
    private static Context i;
    private Socket e;
    private boolean j;
    public boolean a = false;
    private boolean k = false;
    private boolean l = false;
    private boolean m = false;

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

    /* JADX INFO: Access modifiers changed from: private */
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
