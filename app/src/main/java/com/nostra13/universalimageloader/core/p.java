package com.nostra13.universalimageloader.core;

import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
final class p {
    final String a;
    final String b;
    final com.nostra13.universalimageloader.core.c.a c;
    final com.nostra13.universalimageloader.core.assist.c d;
    final d e;
    final com.nostra13.universalimageloader.core.d.a f;
    final com.nostra13.universalimageloader.core.d.b g;
    final ReentrantLock h;

    public p(String str, com.nostra13.universalimageloader.core.c.a aVar, com.nostra13.universalimageloader.core.assist.c cVar, String str2, d dVar, com.nostra13.universalimageloader.core.d.a aVar2, com.nostra13.universalimageloader.core.d.b bVar, ReentrantLock reentrantLock) {
        this.a = str;
        this.c = aVar;
        this.d = cVar;
        this.e = dVar;
        this.f = aVar2;
        this.g = bVar;
        this.h = reentrantLock;
        this.b = str2;
    }
}
