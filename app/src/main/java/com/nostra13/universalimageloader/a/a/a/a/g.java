package com.nostra13.universalimageloader.a.a.a.a;

import java.io.Closeable;
import java.io.File;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Closeable {
    final /* synthetic */ a a;
    private final String b;
    private final long c;
    private File[] d;
    private final InputStream[] e;
    private final long[] f;

    private g(a aVar, String str, long j, File[] fileArr, InputStream[] inputStreamArr, long[] jArr) {
        this.a = aVar;
        this.b = str;
        this.c = j;
        this.d = fileArr;
        this.e = inputStreamArr;
        this.f = jArr;
    }

    /* synthetic */ g(a aVar, String str, long j, File[] fileArr, InputStream[] inputStreamArr, long[] jArr, b bVar) {
        this(aVar, str, j, fileArr, inputStreamArr, jArr);
    }

    public File a(int i) {
        return this.d[i];
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        for (InputStream inputStream : this.e) {
            k.a(inputStream);
        }
    }
}
