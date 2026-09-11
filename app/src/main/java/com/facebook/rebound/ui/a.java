package com.facebook.rebound.ui;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class a implements View.OnTouchListener {
    final /* synthetic */ SpringConfiguratorView a;

    private a(SpringConfiguratorView springConfiguratorView) {
        this.a = springConfiguratorView;
    }

    /* synthetic */ a(SpringConfiguratorView springConfiguratorView, a aVar) {
        this(springConfiguratorView);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return true;
        }
        this.a.c();
        return true;
    }
}
