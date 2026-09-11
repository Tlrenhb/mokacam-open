package com.aee.mokacam.utils;

import java.io.Closeable;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class j implements Closeable {
    final /* synthetic */ e a;
    final String b;
    final long c;
    final InputStream[] d;

    j(e eVar, String str, long j, InputStream[] inputStreamArr) {
        this.a = eVar;
        this.b = str;
        this.c = j;
        this.d = inputStreamArr;
    }

    /* synthetic */ j(e eVar, String str, long j, InputStream[] inputStreamArr, j jVar) {
        this(eVar, str, j, inputStreamArr);
    }

    public InputStream a(int i) {
        return this.d[i];
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        for (InputStream inputStream : this.d) {
            e.a((Closeable) inputStream);
        }
    }
}
