package com.facebook.rebound.ui;

import com.facebook.rebound.i;
import com.facebook.rebound.m;

/* JADX INFO: loaded from: classes.dex */
class b implements m {
    final /* synthetic */ SpringConfiguratorView a;

    private b(SpringConfiguratorView springConfiguratorView) {
        this.a = springConfiguratorView;
    }

    /* synthetic */ b(SpringConfiguratorView springConfiguratorView, b bVar) {
        this(springConfiguratorView);
    }

    @Override // com.facebook.rebound.m
    public void a(i iVar) {
        float fB = (float) iVar.b();
        float f = this.a.f;
        this.a.setTranslationY((fB * (this.a.e - f)) + f);
    }

    @Override // com.facebook.rebound.m
    public void b(i iVar) {
    }

    @Override // com.facebook.rebound.m
    public void c(i iVar) {
    }

    @Override // com.facebook.rebound.m
    public void d(i iVar) {
    }
}
