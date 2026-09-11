package com.aee.mokacam.utils;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
class v implements Comparator<com.aee.mokacam.bean.g> {
    v() {
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(com.aee.mokacam.bean.g gVar, com.aee.mokacam.bean.g gVar2) {
        return gVar2.b().compareTo(gVar.b());
    }
}
