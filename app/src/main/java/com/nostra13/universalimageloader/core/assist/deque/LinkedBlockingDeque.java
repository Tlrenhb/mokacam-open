package com.nostra13.universalimageloader.core.assist.deque;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class LinkedBlockingDeque<E> extends AbstractQueue<E> implements a<E>, Serializable {
    private static final long serialVersionUID = -387911632671998426L;
    transient g<E> a;
    transient g<E> b;
    final ReentrantLock c;
    private transient int d;
    private final int e;
    private final Condition f;
    private final Condition g;

    public LinkedBlockingDeque() {
        this(Integer.MAX_VALUE);
    }

    public LinkedBlockingDeque(int i) {
        this.c = new ReentrantLock();
        this.f = this.c.newCondition();
        this.g = this.c.newCondition();
        if (i <= 0) {
            throw new IllegalArgumentException();
        }
        this.e = i;
    }

    public LinkedBlockingDeque(Collection<? extends E> collection) {
        this(Integer.MAX_VALUE);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            for (E e : collection) {
                if (e == null) {
                    throw new NullPointerException();
                }
                if (!c(new g<>(e))) {
                    throw new IllegalStateException("Deque full");
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    private E a() {
        g<E> gVar = this.a;
        if (gVar == null) {
            return null;
        }
        g<E> gVar2 = gVar.c;
        E e = gVar.a;
        gVar.a = null;
        gVar.c = gVar;
        this.a = gVar2;
        if (gVar2 == null) {
            this.b = null;
        } else {
            gVar2.b = null;
        }
        this.d--;
        this.g.signal();
        return e;
    }

    private E b() {
        g<E> gVar = this.b;
        if (gVar == null) {
            return null;
        }
        g<E> gVar2 = gVar.b;
        E e = gVar.a;
        gVar.a = null;
        gVar.b = gVar;
        this.b = gVar2;
        if (gVar2 == null) {
            this.a = null;
        } else {
            gVar2.c = null;
        }
        this.d--;
        this.g.signal();
        return e;
    }

    private boolean b(g<E> gVar) {
        if (this.d >= this.e) {
            return false;
        }
        g<E> gVar2 = this.a;
        gVar.c = gVar2;
        this.a = gVar;
        if (this.b == null) {
            this.b = gVar;
        } else {
            gVar2.b = gVar;
        }
        this.d++;
        this.f.signal();
        return true;
    }

    private boolean c(g<E> gVar) {
        if (this.d >= this.e) {
            return false;
        }
        g<E> gVar2 = this.b;
        gVar.b = gVar2;
        this.b = gVar;
        if (this.a == null) {
            this.a = gVar;
        } else {
            gVar2.c = gVar;
        }
        this.d++;
        this.f.signal();
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.d = 0;
        this.a = null;
        this.b = null;
        while (true) {
            Object object = objectInputStream.readObject();
            if (object == null) {
                return;
            } else {
                add(object);
            }
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            objectOutputStream.defaultWriteObject();
            for (g<E> gVar = this.a; gVar != null; gVar = gVar.c) {
                objectOutputStream.writeObject(gVar.a);
            }
            objectOutputStream.writeObject(null);
        } finally {
            reentrantLock.unlock();
        }
    }

    void a(g<E> gVar) {
        g<E> gVar2 = gVar.b;
        g<E> gVar3 = gVar.c;
        if (gVar2 == null) {
            a();
            return;
        }
        if (gVar3 == null) {
            b();
            return;
        }
        gVar2.c = gVar3;
        gVar3.b = gVar2;
        gVar.a = null;
        this.d--;
        this.g.signal();
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, java.util.Queue, java.util.concurrent.BlockingQueue
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public void addFirst(E e) {
        if (!offerFirst(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    public void addLast(E e) {
        if (!offerLast(e)) {
            throw new IllegalStateException("Deque full");
        }
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            g<E> gVar = this.a;
            while (gVar != null) {
                gVar.a = null;
                g<E> gVar2 = gVar.c;
                gVar.b = null;
                gVar.c = null;
                gVar = gVar2;
            }
            this.b = null;
            this.a = null;
            this.d = 0;
            this.g.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            for (g<E> gVar = this.a; gVar != null; gVar = gVar.c) {
                if (obj.equals(gVar.a)) {
                    return true;
                }
            }
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public Iterator<E> descendingIterator() {
        return new e(this);
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection) {
        return drainTo(collection, Integer.MAX_VALUE);
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection, int i) {
        if (collection == null) {
            throw new NullPointerException();
        }
        if (collection == this) {
            throw new IllegalArgumentException();
        }
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            int iMin = Math.min(i, this.d);
            for (int i2 = 0; i2 < iMin; i2++) {
                collection.add(this.a.a);
                a();
            }
            return iMin;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.util.AbstractQueue, java.util.Queue
    public E element() {
        return getFirst();
    }

    public E getFirst() {
        E ePeekFirst = peekFirst();
        if (ePeekFirst == null) {
            throw new NoSuchElementException();
        }
        return ePeekFirst;
    }

    public E getLast() {
        E ePeekLast = peekLast();
        if (ePeekLast == null) {
            throw new NoSuchElementException();
        }
        return ePeekLast;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new f(this);
    }

    public boolean offer(E e) {
        return offerLast(e);
    }

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e, long j, TimeUnit timeUnit) {
        return offerLast(e, j, timeUnit);
    }

    public boolean offerFirst(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        g<E> gVar = new g<>(e);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            return b(gVar);
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean offerFirst(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        g<E> gVar = new g<>(e);
        long nanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lockInterruptibly();
        while (!b(gVar)) {
            try {
                if (nanos <= 0) {
                    return false;
                }
                nanos = this.g.awaitNanos(nanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        return true;
    }

    public boolean offerLast(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        g<E> gVar = new g<>(e);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            return c(gVar);
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean offerLast(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        g<E> gVar = new g<>(e);
        long nanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lockInterruptibly();
        while (!c(gVar)) {
            try {
                if (nanos <= 0) {
                    return false;
                }
                nanos = this.g.awaitNanos(nanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        return true;
    }

    @Override // java.util.Queue
    public E peek() {
        return peekFirst();
    }

    public E peekFirst() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            return this.a == null ? null : this.a.a;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E peekLast() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            return this.b == null ? null : this.b.a;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.util.Queue
    public E poll() {
        return pollFirst();
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long j, TimeUnit timeUnit) {
        return pollFirst(j, timeUnit);
    }

    public E pollFirst() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            return a();
        } finally {
            reentrantLock.unlock();
        }
    }

    public E pollFirst(long j, TimeUnit timeUnit) throws InterruptedException {
        long nanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lockInterruptibly();
        while (true) {
            try {
                long j2 = nanos;
                E eA = a();
                if (eA != null) {
                    return eA;
                }
                if (j2 <= 0) {
                    return null;
                }
                nanos = this.f.awaitNanos(j2);
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public E pollLast() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            return b();
        } finally {
            reentrantLock.unlock();
        }
    }

    public E pollLast(long j, TimeUnit timeUnit) throws InterruptedException {
        long nanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lockInterruptibly();
        while (true) {
            try {
                long j2 = nanos;
                E eB = b();
                if (eB != null) {
                    return eB;
                }
                if (j2 <= 0) {
                    return null;
                }
                nanos = this.f.awaitNanos(j2);
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public E pop() {
        return removeFirst();
    }

    public void push(E e) {
        addFirst(e);
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e) {
        putLast(e);
    }

    public void putFirst(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        g<E> gVar = new g<>(e);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        while (!b(gVar)) {
            try {
                this.g.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public void putLast(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        g<E> gVar = new g<>(e);
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        while (!c(gVar)) {
            try {
                this.g.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            return this.e - this.d;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.util.AbstractQueue, java.util.Queue
    public E remove() {
        return removeFirst();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean remove(Object obj) {
        return removeFirstOccurrence(obj);
    }

    public E removeFirst() {
        E ePollFirst = pollFirst();
        if (ePollFirst == null) {
            throw new NoSuchElementException();
        }
        return ePollFirst;
    }

    public boolean removeFirstOccurrence(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            for (g<E> gVar = this.a; gVar != null; gVar = gVar.c) {
                if (obj.equals(gVar.a)) {
                    a(gVar);
                    return true;
                }
            }
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    public E removeLast() {
        E ePollLast = pollLast();
        if (ePollLast == null) {
            throw new NoSuchElementException();
        }
        return ePollLast;
    }

    public boolean removeLastOccurrence(Object obj) {
        if (obj == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            for (g<E> gVar = this.b; gVar != null; gVar = gVar.b) {
                if (obj.equals(gVar.a)) {
                    a(gVar);
                    return true;
                }
            }
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            return this.d;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public E take() {
        return takeFirst();
    }

    public E takeFirst() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        while (true) {
            try {
                E eA = a();
                if (eA != null) {
                    return eA;
                }
                this.f.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public E takeLast() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        while (true) {
            try {
                E eB = b();
                if (eB != null) {
                    return eB;
                }
                this.f.await();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            Object[] objArr = new Object[this.d];
            int i = 0;
            g<E> gVar = this.a;
            while (gVar != null) {
                int i2 = i + 1;
                objArr[i] = gVar.a;
                gVar = gVar.c;
                i = i2;
            }
            return objArr;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            if (tArr.length < this.d) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.d));
            }
            int i = 0;
            g<E> gVar = this.a;
            while (gVar != null) {
                tArr[i] = gVar.a;
                gVar = gVar.c;
                i++;
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        String string;
        ReentrantLock reentrantLock = this.c;
        reentrantLock.lock();
        try {
            g<E> gVar = this.a;
            if (gVar == null) {
                string = "[]";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append('[');
                while (true) {
                    g<E> gVar2 = gVar;
                    Object obj = gVar2.a;
                    if (obj == this) {
                        obj = "(this Collection)";
                    }
                    sb.append(obj);
                    gVar = gVar2.c;
                    if (gVar == null) {
                        break;
                    }
                    sb.append(',').append(' ');
                }
                string = sb.append(']').toString();
            }
            return string;
        } finally {
            reentrantLock.unlock();
        }
    }
}
