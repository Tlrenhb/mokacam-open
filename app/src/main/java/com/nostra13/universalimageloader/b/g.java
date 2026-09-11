package com.nostra13.universalimageloader.b;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
final class g implements Comparator<String> {
    g() {
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(String str, String str2) {
        return str.substring(0, str.lastIndexOf("_")).compareTo(str2.substring(0, str2.lastIndexOf("_")));
    }
}
