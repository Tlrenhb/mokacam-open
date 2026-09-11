package com.nostra13.universalimageloader.core.d;

import android.widget.AbsListView;
import com.nostra13.universalimageloader.core.g;

/* JADX INFO: loaded from: classes.dex */
public class c implements AbsListView.OnScrollListener {
    private g a;
    private final boolean b;
    private final boolean c;
    private final AbsListView.OnScrollListener d;

    public c(g gVar, boolean z, boolean z2) {
        this(gVar, z, z2, null);
    }

    public c(g gVar, boolean z, boolean z2, AbsListView.OnScrollListener onScrollListener) {
        this.a = gVar;
        this.b = z;
        this.c = z2;
        this.d = onScrollListener;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.d != null) {
            this.d.onScroll(absListView, i, i2, i3);
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            case 0:
                this.a.e();
                break;
            case 1:
                if (this.b) {
                    this.a.d();
                }
                break;
            case 2:
                if (this.c) {
                    this.a.d();
                }
                break;
        }
        if (this.d != null) {
            this.d.onScrollStateChanged(absListView, i);
        }
    }
}
