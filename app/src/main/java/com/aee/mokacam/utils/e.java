package com.aee.mokacam.utils;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Closeable {
    private static final Charset a = Charset.forName("UTF-8");
    private final File b;
    private final File c;
    private final File d;
    private final int e;
    private final long f;
    private final int g;
    private Writer i;
    private int k;
    private long h = 0;
    private final LinkedHashMap<String, i> j = new LinkedHashMap<>(0, 0.75f, true);
    private long l = 0;
    private final ExecutorService m = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final Callable<Void> n = new f(this);

    private e(File file, int i, int i2, long j) {
        this.b = file;
        this.e = i;
        this.c = new File(file, "journal");
        this.d = new File(file, "journal.tmp");
        this.g = i2;
        this.f = j;
    }

    public static e a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        e eVar = new e(file, i, i2, j);
        if (eVar.c.exists()) {
            try {
                eVar.c();
                eVar.d();
                eVar.i = new BufferedWriter(new FileWriter(eVar.c, true), 8192);
                return eVar;
            } catch (IOException e) {
                eVar.b();
            }
        }
        file.mkdirs();
        e eVar2 = new e(file, i, i2, j);
        eVar2.e();
        return eVar2;
    }

    private synchronized g a(String str, long j) {
        i iVar;
        g gVar;
        g();
        e(str);
        i iVar2 = this.j.get(str);
        if (j == -1 || (iVar2 != null && iVar2.f == j)) {
            if (iVar2 == null) {
                i iVar3 = new i(this, str, null);
                this.j.put(str, iVar3);
                iVar = iVar3;
            } else if (iVar2.e != null) {
                gVar = null;
            } else {
                iVar = iVar2;
            }
            gVar = new g(this, iVar, null);
            iVar.e = gVar;
            this.i.write("DIRTY " + str + '\n');
            this.i.flush();
        } else {
            gVar = null;
        }
        return gVar;
    }

    public static String a(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder(80);
        while (true) {
            int i = inputStream.read();
            if (i == -1) {
                throw new EOFException();
            }
            if (i == 10) {
                int length = sb.length();
                if (length > 0 && sb.charAt(length - 1) == '\r') {
                    sb.setLength(length - 1);
                }
                return sb.toString();
            }
            sb.append((char) i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(g gVar, boolean z) {
        synchronized (this) {
            i iVar = gVar.b;
            if (iVar.e != gVar) {
                throw new IllegalStateException();
            }
            if (z && !iVar.d) {
                for (int i = 0; i < this.g; i++) {
                    if (!iVar.b(i).exists()) {
                        gVar.b();
                        throw new IllegalStateException("edit didn't create file " + i);
                    }
                }
            }
            for (int i2 = 0; i2 < this.g; i2++) {
                File fileB = iVar.b(i2);
                if (!z) {
                    b(fileB);
                } else if (fileB.exists()) {
                    File fileA = iVar.a(i2);
                    fileB.renameTo(fileA);
                    long j = iVar.c[i2];
                    long length = fileA.length();
                    iVar.c[i2] = length;
                    this.h = (this.h - j) + length;
                }
            }
            this.k++;
            iVar.e = null;
            if (iVar.d || z) {
                iVar.d = true;
                this.i.write("CLEAN " + iVar.b + iVar.a() + '\n');
                if (z) {
                    long j2 = this.l;
                    this.l = 1 + j2;
                    iVar.f = j2;
                }
            } else {
                this.j.remove(iVar.b);
                this.i.write("REMOVE " + iVar.b + '\n');
            }
            if (this.h > this.f || f()) {
                this.m.submit(this.n);
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    public static void a(File file) throws IOException {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            throw new IllegalArgumentException("not a directory: " + file);
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                a(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }

    private static <T> T[] a(T[] tArr, int i, int i2) {
        int length = tArr.length;
        if (i > i2) {
            throw new IllegalArgumentException();
        }
        if (i < 0 || i > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - i;
        int iMin = Math.min(i3, length - i);
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i3));
        System.arraycopy(tArr, i, tArr2, 0, iMin);
        return tArr2;
    }

    private static void b(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private void c() {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(this.c), 8192);
        try {
            String strA = a((InputStream) bufferedInputStream);
            String strA2 = a((InputStream) bufferedInputStream);
            String strA3 = a((InputStream) bufferedInputStream);
            String strA4 = a((InputStream) bufferedInputStream);
            String strA5 = a((InputStream) bufferedInputStream);
            if (!"libcore.io.DiskLruCache".equals(strA) || !"1".equals(strA2) || !Integer.toString(this.e).equals(strA3) || !Integer.toString(this.g).equals(strA4) || !BuildConfig.FLAVOR.equals(strA5)) {
                throw new IOException("unexpected journal header: [" + strA + ", " + strA2 + ", " + strA4 + ", " + strA5 + "]");
            }
            while (true) {
                try {
                    d(a((InputStream) bufferedInputStream));
                } catch (EOFException e) {
                    return;
                }
            }
        } finally {
            a((Closeable) bufferedInputStream);
        }
    }

    private void d() throws IOException {
        b(this.d);
        Iterator<i> it = this.j.values().iterator();
        while (it.hasNext()) {
            i next = it.next();
            if (next.e == null) {
                for (int i = 0; i < this.g; i++) {
                    this.h += next.c[i];
                }
            } else {
                next.e = null;
                for (int i2 = 0; i2 < this.g; i2++) {
                    b(next.a(i2));
                    b(next.b(i2));
                }
                it.remove();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void d(String str) throws IOException {
        i iVar;
        i iVar2 = null;
        Object[] objArr = 0;
        String[] strArrSplit = str.split(" ");
        if (strArrSplit.length < 2) {
            throw new IOException("unexpected journal line: " + str);
        }
        String str2 = strArrSplit[1];
        if (strArrSplit[0].equals("REMOVE") && strArrSplit.length == 2) {
            this.j.remove(str2);
            return;
        }
        i iVar3 = this.j.get(str2);
        if (iVar3 == null) {
            i iVar4 = new i(this, str2, iVar2);
            this.j.put(str2, iVar4);
            iVar = iVar4;
        } else {
            iVar = iVar3;
        }
        if (strArrSplit[0].equals("CLEAN") && strArrSplit.length == this.g + 2) {
            iVar.d = true;
            iVar.e = null;
            iVar.a((String[]) a(strArrSplit, 2, strArrSplit.length));
        } else if (strArrSplit[0].equals("DIRTY") && strArrSplit.length == 2) {
            iVar.e = new g(this, iVar, objArr == true ? 1 : 0);
        } else if (!strArrSplit[0].equals("READ") || strArrSplit.length != 2) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void e() {
        if (this.i != null) {
            this.i.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.d), 8192);
        bufferedWriter.write("libcore.io.DiskLruCache");
        bufferedWriter.write("\n");
        bufferedWriter.write("1");
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.e));
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.g));
        bufferedWriter.write("\n");
        bufferedWriter.write("\n");
        for (i iVar : this.j.values()) {
            if (iVar.e != null) {
                bufferedWriter.write("DIRTY " + iVar.b + '\n');
            } else {
                bufferedWriter.write("CLEAN " + iVar.b + iVar.a() + '\n');
            }
        }
        bufferedWriter.close();
        this.d.renameTo(this.c);
        this.i = new BufferedWriter(new FileWriter(this.c, true), 8192);
    }

    private void e(String str) {
        if (str.contains(" ") || str.contains("\n") || str.contains("\r")) {
            throw new IllegalArgumentException("keys must not contain spaces or newlines: \"" + str + "\"");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        return this.k >= 2000 && this.k >= this.j.size();
    }

    private void g() {
        if (this.i == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        while (this.h > this.f) {
            c(this.j.entrySet().iterator().next().getKey());
        }
    }

    public synchronized j a(String str) {
        j jVar = null;
        synchronized (this) {
            g();
            e(str);
            i iVar = this.j.get(str);
            if (iVar != null && iVar.d) {
                InputStream[] inputStreamArr = new InputStream[this.g];
                for (int i = 0; i < this.g; i++) {
                    try {
                        inputStreamArr[i] = new FileInputStream(iVar.a(i));
                    } catch (FileNotFoundException e) {
                    }
                }
                this.k++;
                this.i.append((CharSequence) ("READ " + str + '\n'));
                if (f()) {
                    this.m.submit(this.n);
                }
                jVar = new j(this, str, iVar.f, inputStreamArr, null);
            }
        }
        return jVar;
    }

    public synchronized void a() {
        g();
        h();
        this.i.flush();
    }

    public g b(String str) {
        return a(str, -1L);
    }

    public void b() throws IOException {
        close();
        a(this.b);
    }

    public synchronized boolean c(String str) {
        boolean z;
        synchronized (this) {
            g();
            e(str);
            i iVar = this.j.get(str);
            if (iVar == null || iVar.e != null) {
                z = false;
            } else {
                for (int i = 0; i < this.g; i++) {
                    File fileA = iVar.a(i);
                    if (!fileA.delete()) {
                        throw new IOException("failed to delete " + fileA);
                    }
                    this.h -= iVar.c[i];
                    iVar.c[i] = 0;
                }
                this.k++;
                this.i.append((CharSequence) ("REMOVE " + str + '\n'));
                this.j.remove(str);
                if (f()) {
                    this.m.submit(this.n);
                }
                z = true;
            }
        }
        return z;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.i != null) {
            for (i iVar : new ArrayList(this.j.values())) {
                if (iVar.e != null) {
                    iVar.e.b();
                }
            }
            h();
            this.i.close();
            this.i = null;
        }
    }
}
