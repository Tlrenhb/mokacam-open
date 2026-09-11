package com.nostra13.universalimageloader.b;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static String a(String str, com.nostra13.universalimageloader.core.assist.c cVar) {
        return str + "_" + cVar.a() + "x" + cVar.b();
    }

    public static Comparator<String> a() {
        return new g();
    }
}
