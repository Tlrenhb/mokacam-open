package com.nostra13.universalimageloader.a.b.a;

import android.graphics.Bitmap;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class a implements com.nostra13.universalimageloader.a.b.a {
    private final com.nostra13.universalimageloader.a.b.a a;
    private final Comparator<String> b;

    public a(com.nostra13.universalimageloader.a.b.a aVar, Comparator<String> comparator) {
        this.a = aVar;
        this.b = comparator;
    }

    @Override // com.nostra13.universalimageloader.a.b.a
    public Bitmap a(String str) {
        return this.a.a(str);
    }

    @Override // com.nostra13.universalimageloader.a.b.a
    public Collection<String> a() {
        return this.a.a();
    }

    @Override // com.nostra13.universalimageloader.a.b.a
    public boolean a(String str, Bitmap bitmap) {
        String next;
        synchronized (this.a) {
            Iterator<String> it = this.a.a().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (this.b.compare(str, next) == 0) {
                    break;
                }
            }
            if (next != null) {
                this.a.b(next);
            }
        }
        return this.a.a(str, bitmap);
    }

    @Override // com.nostra13.universalimageloader.a.b.a
    public Bitmap b(String str) {
        return this.a.b(str);
    }

    @Override // com.nostra13.universalimageloader.a.b.a
    public void b() {
        this.a.b();
    }
}
