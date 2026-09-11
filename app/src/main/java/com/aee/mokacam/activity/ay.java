package com.aee.mokacam.activity;

import android.graphics.Bitmap;
import androidx.collection.LruCache;

/* JADX INFO: loaded from: classes.dex */
class ay extends LruCache<String, Bitmap> {
    final /* synthetic */ LibraryActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ay(LibraryActivity libraryActivity, int i) {
        super(i);
        this.a = libraryActivity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.collection.LruCache
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int sizeOf(String str, Bitmap bitmap) {
        return bitmap.getByteCount();
    }
}
