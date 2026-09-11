package com.aee.mokacam.utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class i {
    final /* synthetic */ e a;
    private final String b;
    private final long[] c;
    private boolean d;
    private g e;
    private long f;

    private i(e eVar, String str) {
        this.a = eVar;
        this.b = str;
        this.c = new long[eVar.g];
    }

    /* synthetic */ i(e eVar, String str, i iVar) {
        this(eVar, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String[] strArr) throws IOException {
        if (strArr.length != this.a.g) {
            throw b(strArr);
        }
        for (int i = 0; i < strArr.length; i++) {
            try {
                this.c[i] = Long.parseLong(strArr[i]);
            } catch (NumberFormatException e) {
                throw b(strArr);
            }
        }
    }

    private IOException b(String[] strArr) throws IOException {
        throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
    }

    public File a(int i) {
        return new File(this.a.b, String.valueOf(this.b) + "." + i);
    }

    public String a() {
        StringBuilder sb = new StringBuilder();
        for (long j : this.c) {
            sb.append(' ').append(j);
        }
        return sb.toString();
    }

    public File b(int i) {
        return new File(this.a.b, String.valueOf(this.b) + "." + i + ".tmp");
    }
}
