package com.nostra13.universalimageloader.a.a.a.a;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
final class f {
    final /* synthetic */ a a;
    private final String b;
    private final long[] c;
    private boolean d;
    private d e;
    private long f;

    private f(a aVar, String str) {
        this.a = aVar;
        this.b = str;
        this.c = new long[aVar.j];
    }

    /* synthetic */ f(a aVar, String str, b bVar) {
        this(aVar, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String[] strArr) throws IOException {
        if (strArr.length != this.a.j) {
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
        return new File(this.a.c, this.b + BuildConfig.FLAVOR + i);
    }

    public String a() {
        StringBuilder sb = new StringBuilder();
        for (long j : this.c) {
            sb.append(' ').append(j);
        }
        return sb.toString();
    }

    public File b(int i) {
        return new File(this.a.c, this.b + BuildConfig.FLAVOR + i + ".tmp");
    }
}
