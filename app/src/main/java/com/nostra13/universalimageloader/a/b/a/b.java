package com.nostra13.universalimageloader.a.b.a;

import android.graphics.Bitmap;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class b implements com.nostra13.universalimageloader.a.b.a {
    private final LinkedHashMap<String, Bitmap> a;
    private final int b;
    private int c;

    public b(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.b = i;
        this.a = new LinkedHashMap<>(0, 0.75f, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0031, code lost:
    
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void a(int i) {
        while (true) {
            synchronized (this) {
                if (this.c < 0 || (this.a.isEmpty() && this.c != 0)) {
                    break;
                }
                if (this.c <= i || this.a.isEmpty()) {
                    break;
                }
                Map.Entry<String, Bitmap> next = this.a.entrySet().iterator().next();
                if (next == null) {
                    return;
                }
                String key = next.getKey();
                Bitmap value = next.getValue();
                this.a.remove(key);
                this.c -= b(key, value);
            }
        }
    }

    private int b(String str, Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    @Override // com.nostra13.universalimageloader.a.b.a
    public final Bitmap a(String str) {
        Bitmap bitmap;
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            bitmap = this.a.get(str);
        }
        return bitmap;
    }

    @Override // com.nostra13.universalimageloader.a.b.a
    public Collection<String> a() {
        HashSet hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.a.keySet());
        }
        return hashSet;
    }

    @Override // com.nostra13.universalimageloader.a.b.a
    public final boolean a(String str, Bitmap bitmap) {
        if (str == null || bitmap == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.c += b(str, bitmap);
            Bitmap bitmapPut = this.a.put(str, bitmap);
            if (bitmapPut != null) {
                this.c -= b(str, bitmapPut);
            }
        }
        a(this.b);
        return true;
    }

    @Override // com.nostra13.universalimageloader.a.b.a
    public final Bitmap b(String str) {
        Bitmap bitmapRemove;
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            bitmapRemove = this.a.remove(str);
            if (bitmapRemove != null) {
                this.c -= b(str, bitmapRemove);
            }
        }
        return bitmapRemove;
    }

    @Override // com.nostra13.universalimageloader.a.b.a
    public void b() {
        a(-1);
    }

    public final synchronized String toString() {
        return String.format("LruCache[maxSize=%d]", Integer.valueOf(this.b));
    }
}
