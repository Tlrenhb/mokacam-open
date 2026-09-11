package com.nostra13.universalimageloader.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
class b implements ThreadFactory {
    private static final AtomicInteger a = new AtomicInteger(1);
    private final String d;
    private final int e;
    private final AtomicInteger c = new AtomicInteger(1);
    private final ThreadGroup b = Thread.currentThread().getThreadGroup();

    b(int i, String str) {
        this.e = i;
        this.d = str + a.getAndIncrement() + "-thread-";
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.b, runnable, this.d + this.c.getAndIncrement(), 0L);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        thread.setPriority(this.e);
        return thread;
    }
}
