package com.nostra13.universalimageloader.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
class ImageLoaderEngine {
    final j a;
    private Executor b;
    private Executor c;
    private final Map<Integer, String> e = Collections.synchronizedMap(new HashMap());
    private final Map<String, ReentrantLock> f = new WeakHashMap();
    private final AtomicBoolean g = new AtomicBoolean(false);
    private final AtomicBoolean h = new AtomicBoolean(false);
    private final AtomicBoolean i = new AtomicBoolean(false);
    private final Object j = new Object();
    private Executor d = a.a();

    ImageLoaderEngine(j jVar) {
        this.a = jVar;
        this.b = jVar.g;
        this.c = jVar.h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (!this.a.i && ((ExecutorService) this.b).isShutdown()) {
            this.b = h();
        }
        if (this.a.j || !((ExecutorService) this.c).isShutdown()) {
            return;
        }
        this.c = h();
    }

    private Executor h() {
        return a.a(this.a.k, this.a.l, this.a.m);
    }

    String a(com.nostra13.universalimageloader.core.c.a aVar) {
        return this.e.get(Integer.valueOf(aVar.f()));
    }

    ReentrantLock a(String str) {
        ReentrantLock reentrantLock = this.f.get(str);
        if (reentrantLock != null) {
            return reentrantLock;
        }
        ReentrantLock reentrantLock2 = new ReentrantLock();
        this.f.put(str, reentrantLock2);
        return reentrantLock2;
    }

    void a() {
        this.g.set(true);
    }

    void a(com.nostra13.universalimageloader.core.c.a aVar, String str) {
        this.e.put(Integer.valueOf(aVar.f()), str);
    }

    void a(q qVar) {
        this.d.execute(new o(this, qVar));
    }

    void a(v vVar) {
        g();
        this.c.execute(vVar);
    }

    void b() {
        this.g.set(false);
        synchronized (this.j) {
            this.j.notifyAll();
        }
    }

    void b(com.nostra13.universalimageloader.core.c.a aVar) {
        this.e.remove(Integer.valueOf(aVar.f()));
    }

    AtomicBoolean c() {
        return this.g;
    }

    Object d() {
        return this.j;
    }

    boolean e() {
        return this.h.get();
    }

    boolean f() {
        return this.i.get();
    }

    void fireCallback(Runnable runnable) {
        this.d.execute(runnable);
    }
}
