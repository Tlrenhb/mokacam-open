package com.aee.mokacam.photoview;

import android.database.DataSetObserver;

/* JADX INFO: loaded from: classes.dex */
class o extends DataSetObserver {
    final /* synthetic */ LazyViewPager a;

    o(LazyViewPager lazyViewPager) {
        this.a = lazyViewPager;
    }

    /* synthetic */ o(LazyViewPager lazyViewPager, o oVar) {
        this(lazyViewPager);
    }

    @Override // android.database.DataSetObserver
    public void onChanged() {
        this.a.b();
    }

    @Override // android.database.DataSetObserver
    public void onInvalidated() {
        this.a.b();
    }
}
