package com.nostra13.universalimageloader.a.a.a.a;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
final class a implements Closeable {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,64}");
    private static final OutputStream r = new c();
    private final File c;
    private final File d;
    private final File e;
    private final File f;
    private final int g;
    private long h;
    private int i;
    private final int j;
    private Writer m;
    private int o;
    private long k = 0;
    private int l = 0;
    private final LinkedHashMap<String, f> n = new LinkedHashMap<>(0, 0.75f, true);
    private long p = 0;
    final ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final Callable<Void> q = new b(this);

    private a(File file, int i, int i2, long j, int i3) {
        this.c = file;
        this.g = i;
        this.d = new File(file, "journal");
        this.e = new File(file, "journal.tmp");
        this.f = new File(file, "journal.bkp");
        this.j = i2;
        this.h = j;
        this.i = i3;
    }

    public static a a(File file, int i, int i2, long j, int i3) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i3 <= 0) {
            throw new IllegalArgumentException("maxFileCount <= 0");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                a(file2, file3, false);
            }
        }
        a aVar = new a(file, i, i2, j, i3);
        if (aVar.d.exists()) {
            try {
                aVar.f();
                aVar.g();
                aVar.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(aVar.d, true), k.a));
                return aVar;
            } catch (IOException e) {
                System.out.println("DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                aVar.d();
            }
        }
        file.mkdirs();
        a aVar2 = new a(file, i, i2, j, i3);
        aVar2.h();
        return aVar2;
    }

    private synchronized d a(String str, long j) {
        f fVar;
        d dVar;
        j();
        e(str);
        f fVar2 = this.n.get(str);
        if (j == -1 || (fVar2 != null && fVar2.f == j)) {
            if (fVar2 == null) {
                f fVar3 = new f(this, str, null);
                this.n.put(str, fVar3);
                fVar = fVar3;
            } else if (fVar2.e != null) {
                dVar = null;
            } else {
                fVar = fVar2;
            }
            dVar = new d(this, fVar, null);
            fVar.e = dVar;
            this.m.write("DIRTY " + str + '\n');
            this.m.flush();
        } else {
            dVar = null;
        }
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005c A[Catch: all -> 0x0012, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x000c, B:7:0x0011, B:12:0x0017, B:15:0x001e, B:17:0x0022, B:19:0x002a, B:20:0x0045, B:21:0x0046, B:23:0x0050, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x008c, B:35:0x008f, B:36:0x0093, B:38:0x00a4, B:40:0x00d4, B:41:0x00de, B:43:0x00eb, B:45:0x00f1, B:47:0x00f7, B:48:0x0100), top: B:50:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a4 A[Catch: all -> 0x0012, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x000c, B:7:0x0011, B:12:0x0017, B:15:0x001e, B:17:0x0022, B:19:0x002a, B:20:0x0045, B:21:0x0046, B:23:0x0050, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x008c, B:35:0x008f, B:36:0x0093, B:38:0x00a4, B:40:0x00d4, B:41:0x00de, B:43:0x00eb, B:45:0x00f1, B:47:0x00f7, B:48:0x0100), top: B:50:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00f7 A[Catch: all -> 0x0012, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x000c, B:7:0x0011, B:12:0x0017, B:15:0x001e, B:17:0x0022, B:19:0x002a, B:20:0x0045, B:21:0x0046, B:23:0x0050, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x008c, B:35:0x008f, B:36:0x0093, B:38:0x00a4, B:40:0x00d4, B:41:0x00de, B:43:0x00eb, B:45:0x00f1, B:47:0x00f7, B:48:0x0100), top: B:50:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0100 A[Catch: all -> 0x0012, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x000c, B:7:0x0011, B:12:0x0017, B:15:0x001e, B:17:0x0022, B:19:0x002a, B:20:0x0045, B:21:0x0046, B:23:0x0050, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x008c, B:35:0x008f, B:36:0x0093, B:38:0x00a4, B:40:0x00d4, B:41:0x00de, B:43:0x00eb, B:45:0x00f1, B:47:0x00f7, B:48:0x0100), top: B:50:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized void a(d dVar, boolean z) {
        synchronized (this) {
            f fVar = dVar.b;
            if (fVar.e != dVar) {
                throw new IllegalStateException();
            }
            if (!z || fVar.d) {
                for (int i = 0; i < this.j; i++) {
                    File fileB = fVar.b(i);
                    if (!z) {
                        a(fileB);
                    } else if (fileB.exists()) {
                        File fileA = fVar.a(i);
                        fileB.renameTo(fileA);
                        long j = fVar.c[i];
                        long length = fileA.length();
                        fVar.c[i] = length;
                        this.k = (this.k - j) + length;
                        this.l++;
                    }
                }
                this.o++;
                fVar.e = null;
                if (fVar.d || z) {
                    this.n.remove(fVar.b);
                    this.m.write("REMOVE " + fVar.b + '\n');
                } else {
                    fVar.d = true;
                    this.m.write("CLEAN " + fVar.b + fVar.a() + '\n');
                    if (z) {
                        long j2 = this.p;
                        this.p = 1 + j2;
                        fVar.f = j2;
                    }
                }
                this.m.flush();
                if (this.k <= this.h || this.l > this.i || i()) {
                    this.b.submit(this.q);
                }
            } else {
                for (int i2 = 0; i2 < this.j; i2++) {
                    if (!dVar.c[i2]) {
                        dVar.b();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                    }
                    if (!fVar.b(i2).exists()) {
                        dVar.b();
                        break;
                    }
                }
                while (i < this.j) {
                }
                this.o++;
                fVar.e = null;
                if (!(fVar.d | z)) {
                }
                this.m.flush();
                if (this.k <= this.h) {
                    this.b.submit(this.q);
                }
            }
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    private void d(String str) throws IOException {
        String strSubstring;
        b bVar = null;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i);
        if (iIndexOf2 == -1) {
            String strSubstring2 = str.substring(i);
            if (iIndexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
                this.n.remove(strSubstring2);
                return;
            }
            strSubstring = strSubstring2;
        } else {
            strSubstring = str.substring(i, iIndexOf2);
        }
        f fVar = this.n.get(strSubstring);
        if (fVar == null) {
            fVar = new f(this, strSubstring, bVar);
            this.n.put(strSubstring, fVar);
        }
        if (iIndexOf2 != -1 && iIndexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
            fVar.d = true;
            fVar.e = null;
            fVar.a(strArrSplit);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            fVar.e = new d(this, fVar, bVar);
        } else if (iIndexOf2 != -1 || iIndexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void e(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + str + "\"");
        }
    }

    private void f() {
        i iVar = new i(new FileInputStream(this.d), k.a);
        try {
            String strA = iVar.a();
            String strA2 = iVar.a();
            String strA3 = iVar.a();
            String strA4 = iVar.a();
            String strA5 = iVar.a();
            if (!"libcore.io.DiskLruCache".equals(strA) || !"1".equals(strA2) || !Integer.toString(this.g).equals(strA3) || !Integer.toString(this.j).equals(strA4) || !BuildConfig.FLAVOR.equals(strA5)) {
                throw new IOException("unexpected journal header: [" + strA + ", " + strA2 + ", " + strA4 + ", " + strA5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    d(iVar.a());
                    i++;
                } catch (EOFException e) {
                    this.o = i - this.n.size();
                    k.a(iVar);
                    return;
                }
            }
        } catch (Throwable th) {
            k.a(iVar);
            throw th;
        }
    }

    private void g() throws IOException {
        a(this.e);
        Iterator<f> it = this.n.values().iterator();
        while (it.hasNext()) {
            f next = it.next();
            if (next.e == null) {
                for (int i = 0; i < this.j; i++) {
                    this.k += next.c[i];
                    this.l++;
                }
            } else {
                next.e = null;
                for (int i2 = 0; i2 < this.j; i2++) {
                    a(next.a(i2));
                    a(next.b(i2));
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void h() {
        if (this.m != null) {
            this.m.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), k.a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.j));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (f fVar : this.n.values()) {
                if (fVar.e != null) {
                    bufferedWriter.write("DIRTY " + fVar.b + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + fVar.b + fVar.a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.d.exists()) {
                a(this.d, this.f, true);
            }
            a(this.e, this.d, false);
            this.f.delete();
            this.m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), k.a));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i() {
        return this.o >= 2000 && this.o >= this.n.size();
    }

    private void j() {
        if (this.m == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        while (this.k > this.h) {
            c(this.n.entrySet().iterator().next().getKey());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        while (this.l > this.i) {
            c(this.n.entrySet().iterator().next().getKey());
        }
    }

    public synchronized g a(String str) {
        g gVar = null;
        synchronized (this) {
            j();
            e(str);
            f fVar = this.n.get(str);
            if (fVar != null && fVar.d) {
                File[] fileArr = new File[this.j];
                InputStream[] inputStreamArr = new InputStream[this.j];
                for (int i = 0; i < this.j; i++) {
                    try {
                        File fileA = fVar.a(i);
                        fileArr[i] = fileA;
                        inputStreamArr[i] = new FileInputStream(fileA);
                    } catch (FileNotFoundException e) {
                        for (int i2 = 0; i2 < this.j && inputStreamArr[i2] != null; i2++) {
                            k.a(inputStreamArr[i2]);
                        }
                    }
                }
                this.o++;
                this.m.append((CharSequence) ("READ " + str + '\n'));
                if (i()) {
                    this.b.submit(this.q);
                }
                gVar = new g(this, str, fVar.f, fileArr, inputStreamArr, fVar.c, null);
            }
        }
        return gVar;
    }

    public File a() {
        return this.c;
    }

    public synchronized long b() {
        return this.h;
    }

    public d b(String str) {
        return a(str, -1L);
    }

    public synchronized int c() {
        return this.i;
    }

    public synchronized boolean c(String str) {
        boolean z;
        synchronized (this) {
            j();
            e(str);
            f fVar = this.n.get(str);
            if (fVar == null || fVar.e != null) {
                z = false;
            } else {
                for (int i = 0; i < this.j; i++) {
                    File fileA = fVar.a(i);
                    if (fileA.exists() && !fileA.delete()) {
                        throw new IOException("failed to delete " + fileA);
                    }
                    this.k -= fVar.c[i];
                    this.l--;
                    fVar.c[i] = 0;
                }
                this.o++;
                this.m.append((CharSequence) ("REMOVE " + str + '\n'));
                this.n.remove(str);
                if (i()) {
                    this.b.submit(this.q);
                }
                z = true;
            }
        }
        return z;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.m != null) {
            for (f fVar : new ArrayList(this.n.values())) {
                if (fVar.e != null) {
                    fVar.e.b();
                }
            }
            k();
            l();
            this.m.close();
            this.m = null;
        }
    }

    public void d() throws IOException {
        close();
        k.a(this.c);
    }
}
