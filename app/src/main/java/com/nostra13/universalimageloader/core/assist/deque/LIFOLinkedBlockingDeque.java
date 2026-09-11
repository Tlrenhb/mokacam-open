package com.nostra13.universalimageloader.core.assist.deque;

/* JADX INFO: loaded from: classes.dex */
public class LIFOLinkedBlockingDeque<T> extends LinkedBlockingDeque<T> {
    private static final long serialVersionUID = -4114786347960826192L;

    @Override // com.nostra13.universalimageloader.core.assist.deque.LinkedBlockingDeque, java.util.Queue, java.util.concurrent.BlockingQueue
    public boolean offer(T t) {
        return super.offerFirst(t);
    }

    @Override // com.nostra13.universalimageloader.core.assist.deque.LinkedBlockingDeque, java.util.AbstractQueue, java.util.Queue
    public T remove() {
        return (T) super.removeFirst();
    }
}
