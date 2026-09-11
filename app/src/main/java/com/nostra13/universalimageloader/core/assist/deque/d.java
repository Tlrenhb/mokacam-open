package com.nostra13.universalimageloader.core.assist.deque;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: Add missing generic type declarations: [E] */
/* JADX INFO: loaded from: classes.dex */
abstract class d<E> implements Iterator<E> {
    g<E> a;
    E b;
    final /* synthetic */ LinkedBlockingDeque c;
    private g<E> d;

    d(LinkedBlockingDeque linkedBlockingDeque) {
        this.c = linkedBlockingDeque;
        ReentrantLock reentrantLock = linkedBlockingDeque.c;
        reentrantLock.lock();
        try {
            this.a = a();
            this.b = this.a == null ? null : this.a.a;
        } finally {
            reentrantLock.unlock();
        }
    }

    private g<E> b(g<E> gVar) {
        while (true) {
            g<E> gVarA = a(gVar);
            if (gVarA == null) {
                return null;
            }
            if (gVarA.a != null) {
                return gVarA;
            }
            if (gVarA == gVar) {
                return a();
            }
            gVar = gVarA;
        }
    }

    abstract g<E> a();

    abstract g<E> a(g<E> gVar);

    void b() {
        ReentrantLock reentrantLock = this.c.c;
        reentrantLock.lock();
        try {
            this.a = b(this.a);
            this.b = this.a == null ? null : this.a.a;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.a != null;
    }

    @Override // java.util.Iterator
    public E next() {
        if (this.a == null) {
            throw new NoSuchElementException();
        }
        this.d = this.a;
        E e = this.b;
        b();
        return e;
    }

    @Override // java.util.Iterator
    public void remove() {
        g<E> gVar = this.d;
        if (gVar == null) {
            throw new IllegalStateException();
        }
        this.d = null;
        ReentrantLock reentrantLock = this.c.c;
        reentrantLock.lock();
        try {
            if (gVar.a != null) {
                this.c.a(gVar);
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}
