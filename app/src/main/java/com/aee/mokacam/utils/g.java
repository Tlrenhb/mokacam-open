package com.aee.mokacam.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    final /* synthetic */ e a;
    final i b;
    boolean c;

    g(e eVar, i iVar) {
        this.a = eVar;
        this.b = iVar;
    }

    /* synthetic */ g(e eVar, i iVar, g gVar) {
        this(eVar, iVar);
    }

    public OutputStream a(int i) {
        h hVar;
        synchronized (this.a) {
            if (this.b.e != this) {
                throw new IllegalStateException();
            }
            hVar = new h(this, new FileOutputStream(this.b.b(i)), null);
        }
        return hVar;
    }

    public void a() {
        if (!this.c) {
            this.a.a(this, true);
        } else {
            this.a.a(this, false);
            this.a.c(this.b.b);
        }
    }

    public void b() {
        this.a.a(this, false);
    }
}
