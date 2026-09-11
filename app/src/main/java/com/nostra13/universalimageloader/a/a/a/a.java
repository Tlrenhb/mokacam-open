package com.nostra13.universalimageloader.a.a.a;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.b.c;
import com.nostra13.universalimageloader.b.d;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements com.nostra13.universalimageloader.a.a.a {
    public static final Bitmap.CompressFormat a = Bitmap.CompressFormat.PNG;
    protected final File b;
    protected final File c;
    protected final com.nostra13.universalimageloader.a.a.b.a d;
    protected int e = 32768;
    protected Bitmap.CompressFormat f = a;
    protected int g = 100;

    public a(File file, File file2, com.nostra13.universalimageloader.a.a.b.a aVar) {
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        }
        if (aVar == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        }
        this.b = file;
        this.c = file2;
        this.d = aVar;
    }

    @Override // com.nostra13.universalimageloader.a.a.a
    public File a(String str) {
        return b(str);
    }

    @Override // com.nostra13.universalimageloader.a.a.a
    public void a() {
        File[] fileArrListFiles = this.b.listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                file.delete();
            }
        }
    }

    @Override // com.nostra13.universalimageloader.a.a.a
    public boolean a(String str, Bitmap bitmap) {
        File fileB = b(str);
        File file = new File(fileB.getAbsolutePath() + ".tmp");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.e);
        try {
            boolean zCompress = bitmap.compress(this.f, this.g, bufferedOutputStream);
            c.a(bufferedOutputStream);
            if (zCompress && !file.renameTo(fileB)) {
                zCompress = false;
            }
            if (!zCompress) {
                file.delete();
            }
            bitmap.recycle();
            return zCompress;
        } catch (Throwable th) {
            c.a(bufferedOutputStream);
            file.delete();
            throw th;
        }
    }

    @Override // com.nostra13.universalimageloader.a.a.a
    public boolean a(String str, InputStream inputStream, d dVar) throws Throwable {
        boolean zA;
        File fileB = b(str);
        File file = new File(fileB.getAbsolutePath() + ".tmp");
        try {
            try {
                zA = c.a(inputStream, new BufferedOutputStream(new FileOutputStream(file), this.e), dVar, this.e);
            } finally {
            }
        } catch (Throwable th) {
            th = th;
            zA = false;
        }
        try {
            if (zA && !file.renameTo(fileB)) {
                zA = false;
            }
            if (!zA) {
                file.delete();
            }
            return zA;
        } catch (Throwable th2) {
            th = th2;
            if (zA && !file.renameTo(fileB)) {
                zA = false;
            }
            if (!zA) {
                file.delete();
            }
            throw th;
        }
    }

    protected File b(String str) {
        String strA = this.d.a(str);
        File file = this.b;
        if (!this.b.exists() && !this.b.mkdirs() && this.c != null && (this.c.exists() || this.c.mkdirs())) {
            file = this.c;
        }
        return new File(file, strA);
    }
}
