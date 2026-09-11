package com.nostra13.universalimageloader.a.a.a.a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    final /* synthetic */ a a;
    private final f b;
    private final boolean[] c;
    private boolean d;
    private boolean e;

    private d(a aVar, f fVar) {
        this.a = aVar;
        this.b = fVar;
        this.c = fVar.d ? null : new boolean[aVar.j];
    }

    /* synthetic */ d(a aVar, f fVar, b bVar) {
        this(aVar, fVar);
    }

    public OutputStream a(int i) {
        OutputStream eVar;
        FileOutputStream fileOutputStream;
        synchronized (this.a) {
            if (this.b.e != this) {
                throw new IllegalStateException();
            }
            if (!this.b.d) {
                this.c[i] = true;
            }
            File fileB = this.b.b(i);
            try {
                fileOutputStream = new FileOutputStream(fileB);
            } catch (FileNotFoundException e) {
                this.a.c.mkdirs();
                try {
                    fileOutputStream = new FileOutputStream(fileB);
                } catch (FileNotFoundException e2) {
                    eVar = a.r;
                }
            }
            eVar = new e(this, fileOutputStream, null);
        }
        return eVar;
    }

    public void a() {
        if (this.d) {
            this.a.a(this, false);
            this.a.c(this.b.b);
        } else {
            this.a.a(this, true);
        }
        this.e = true;
    }

    public void b() {
        this.a.a(this, false);
    }
}
