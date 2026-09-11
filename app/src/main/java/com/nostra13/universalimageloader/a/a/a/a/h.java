package com.nostra13.universalimageloader.a.a.a.a;

import android.graphics.Bitmap;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class h implements com.nostra13.universalimageloader.a.a.a {
    public static final Bitmap.CompressFormat a = Bitmap.CompressFormat.PNG;
    protected a b;
    protected final com.nostra13.universalimageloader.a.a.b.a c;
    protected int d = 32768;
    protected Bitmap.CompressFormat e = a;
    protected int f = 100;
    private File g;

    public h(File file, File file2, com.nostra13.universalimageloader.a.a.b.a aVar, long j, int i) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("cacheMaxSize argument must be positive number");
        }
        if (i < 0) {
            throw new IllegalArgumentException("cacheMaxFileCount argument must be positive number");
        }
        if (aVar == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        }
        long j2 = j == 0 ? Long.MAX_VALUE : j;
        int i2 = i == 0 ? Integer.MAX_VALUE : i;
        this.g = file2;
        this.c = aVar;
        a(file, file2, j2, i2);
    }

    private void a(File file, File file2, long j, int i) throws IOException {
        try {
            this.b = a.a(file, 1, 1, j, i);
        } catch (IOException e) {
            com.nostra13.universalimageloader.b.e.a(e);
            if (file2 != null) {
                a(file2, null, j, i);
            }
            if (this.b == null) {
                throw e;
            }
        }
    }

    private String b(String str) {
        return this.c.a(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0029  */
    @Override // com.nostra13.universalimageloader.a.a.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public File a(String str) throws Throwable {
        g gVarA;
        Throwable th;
        File fileA = null;
        try {
            gVarA = this.b.a(b(str));
            if (gVarA != null) {
                try {
                    try {
                        fileA = gVarA.a(0);
                    } catch (IOException e) {
                        e = e;
                        com.nostra13.universalimageloader.b.e.a(e);
                        if (gVarA != null) {
                            gVarA.close();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (gVarA != null) {
                        gVarA.close();
                    }
                    throw th;
                }
            }
            if (gVarA != null) {
                gVarA.close();
            }
        } catch (IOException e2) {
            e = e2;
            gVarA = null;
        } catch (Throwable th3) {
            gVarA = null;
            th = th3;
            if (gVarA != null) {
            }
            throw th;
        }
        return fileA;
    }

    @Override // com.nostra13.universalimageloader.a.a.a
    public void a() {
        try {
            this.b.d();
        } catch (IOException e) {
            com.nostra13.universalimageloader.b.e.a(e);
        }
        try {
            a(this.b.a(), this.g, this.b.b(), this.b.c());
        } catch (IOException e2) {
            com.nostra13.universalimageloader.b.e.a(e2);
        }
    }

    @Override // com.nostra13.universalimageloader.a.a.a
    public boolean a(String str, Bitmap bitmap) {
        boolean zCompress = false;
        d dVarB = this.b.b(b(str));
        if (dVarB != null) {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(dVarB.a(0), this.d);
            try {
                zCompress = bitmap.compress(this.e, this.f, bufferedOutputStream);
                if (zCompress) {
                    dVarB.a();
                } else {
                    dVarB.b();
                }
            } finally {
                com.nostra13.universalimageloader.b.c.a(bufferedOutputStream);
            }
        }
        return zCompress;
    }

    @Override // com.nostra13.universalimageloader.a.a.a
    public boolean a(String str, InputStream inputStream, com.nostra13.universalimageloader.b.d dVar) {
        boolean zA = false;
        d dVarB = this.b.b(b(str));
        if (dVarB != null) {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(dVarB.a(0), this.d);
            try {
                zA = com.nostra13.universalimageloader.b.c.a(inputStream, bufferedOutputStream, dVar, this.d);
                com.nostra13.universalimageloader.b.c.a(bufferedOutputStream);
                if (zA) {
                    dVarB.a();
                } else {
                    dVarB.b();
                }
            } catch (Throwable th) {
                com.nostra13.universalimageloader.b.c.a(bufferedOutputStream);
                dVarB.b();
                throw th;
            }
        }
        return zA;
    }
}
