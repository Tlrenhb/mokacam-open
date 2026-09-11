package com.nostra13.universalimageloader.core.assist.deque;

/* JADX INFO: loaded from: classes.dex */
class e extends LinkedBlockingDeque<E>.d {
    final /* synthetic */ LinkedBlockingDeque d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private e(LinkedBlockingDeque linkedBlockingDeque) {
        super(linkedBlockingDeque);
        this.d = linkedBlockingDeque;
    }

    g<E> a() {
        return this.d.b;
    }

    g<E> a(g<E> gVar) {
        return gVar.b;
    }
}
