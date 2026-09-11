package com.aee.mokacam.utils;

import java.io.File;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
class u implements Comparator<File> {
    u() {
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(File file, File file2) {
        long jLastModified = file2.lastModified() - file.lastModified();
        if (jLastModified > 0) {
            return 1;
        }
        return jLastModified == 0 ? 0 : -1;
    }
}
