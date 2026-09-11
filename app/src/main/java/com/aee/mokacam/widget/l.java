package com.aee.mokacam.widget;

import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
abstract class l<T> {
    private int a;
    private Queue<T> b = new LinkedList();

    public l(int i) {
        this.a = i;
    }

    protected abstract T a(T t);

    protected abstract T b();

    public void b(T t) {
        if (t == null || this.b.size() >= this.a) {
            return;
        }
        this.b.offer(t);
    }

    public T c() {
        return this.b.size() == 0 ? b() : a(this.b.poll());
    }
}
