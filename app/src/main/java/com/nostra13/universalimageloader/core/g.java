package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;

/* JADX INFO: loaded from: classes.dex */
public class g {
    public static final String a = g.class.getSimpleName();
    private static volatile g e;
    private j b;
    private ImageLoaderEngine c;
    private com.nostra13.universalimageloader.core.d.a d = new com.nostra13.universalimageloader.core.d.d();

    protected g() {
    }

    private static Handler a(d dVar) {
        Handler handlerR = dVar.r();
        if (dVar.s()) {
            return null;
        }
        return (handlerR == null && Looper.myLooper() == Looper.getMainLooper()) ? new Handler() : handlerR;
    }

    public static g a() {
        if (e == null) {
            synchronized (g.class) {
                if (e == null) {
                    e = new g();
                }
            }
        }
        return e;
    }

    private void f() {
        if (this.b == null) {
            throw new IllegalStateException("ImageLoader must be init with configuration before using");
        }
    }

    public Bitmap a(String str, com.nostra13.universalimageloader.core.assist.c cVar) {
        return a(str, cVar, null);
    }

    public Bitmap a(String str, com.nostra13.universalimageloader.core.assist.c cVar, d dVar) {
        if (dVar == null) {
            dVar = this.b.r;
        }
        d dVarA = new f().a(dVar).c(true).a();
        i iVar = new i();
        a(str, cVar, dVarA, iVar);
        return iVar.a();
    }

    public void a(com.nostra13.universalimageloader.core.c.a aVar) {
        this.c.b(aVar);
    }

    public synchronized void a(j jVar) {
        if (jVar == null) {
            throw new IllegalArgumentException("ImageLoader configuration can not be initialized with null");
        }
        if (this.b == null) {
            com.nostra13.universalimageloader.b.e.a("Initialize ImageLoader with configuration", new Object[0]);
            this.c = new ImageLoaderEngine(jVar);
            this.b = jVar;
        } else {
            com.nostra13.universalimageloader.b.e.c("Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.", new Object[0]);
        }
    }

    public void a(String str, ImageView imageView) {
        a(str, new com.nostra13.universalimageloader.core.c.b(imageView), (d) null, (com.nostra13.universalimageloader.core.d.a) null, (com.nostra13.universalimageloader.core.d.b) null);
    }

    public void a(String str, ImageView imageView, d dVar, com.nostra13.universalimageloader.core.d.a aVar) {
        a(str, imageView, dVar, aVar, (com.nostra13.universalimageloader.core.d.b) null);
    }

    public void a(String str, ImageView imageView, d dVar, com.nostra13.universalimageloader.core.d.a aVar, com.nostra13.universalimageloader.core.d.b bVar) {
        a(str, new com.nostra13.universalimageloader.core.c.b(imageView), dVar, aVar, bVar);
    }

    public void a(String str, com.nostra13.universalimageloader.core.assist.c cVar, d dVar, com.nostra13.universalimageloader.core.d.a aVar) {
        a(str, cVar, dVar, aVar, (com.nostra13.universalimageloader.core.d.b) null);
    }

    public void a(String str, com.nostra13.universalimageloader.core.assist.c cVar, d dVar, com.nostra13.universalimageloader.core.d.a aVar, com.nostra13.universalimageloader.core.d.b bVar) {
        f();
        if (cVar == null) {
            cVar = this.b.a();
        }
        a(str, new com.nostra13.universalimageloader.core.c.c(str, cVar, ViewScaleType.CROP), dVar == null ? this.b.r : dVar, aVar, bVar);
    }

    public void a(String str, com.nostra13.universalimageloader.core.c.a aVar, d dVar, com.nostra13.universalimageloader.core.assist.c cVar, com.nostra13.universalimageloader.core.d.a aVar2, com.nostra13.universalimageloader.core.d.b bVar) {
        f();
        if (aVar == null) {
            throw new IllegalArgumentException("Wrong arguments were passed to displayImage() method (ImageView reference must not be null)");
        }
        com.nostra13.universalimageloader.core.d.a aVar3 = aVar2 == null ? this.d : aVar2;
        d dVar2 = dVar == null ? this.b.r : dVar;
        if (TextUtils.isEmpty(str)) {
            this.c.b(aVar);
            aVar3.b(str, aVar.d());
            if (dVar2.b()) {
                aVar.a(dVar2.b(this.b.a));
            } else {
                aVar.a((Drawable) null);
            }
            aVar3.a(str, aVar.d(), (Bitmap) null);
            return;
        }
        com.nostra13.universalimageloader.core.assist.c cVarA = cVar == null ? com.nostra13.universalimageloader.b.a.a(aVar, this.b.a()) : cVar;
        String strA = com.nostra13.universalimageloader.b.f.a(str, cVarA);
        this.c.a(aVar, strA);
        aVar3.b(str, aVar.d());
        Bitmap bitmapA = this.b.n.a(strA);
        if (bitmapA == null || bitmapA.isRecycled()) {
            if (dVar2.a()) {
                aVar.a(dVar2.a(this.b.a));
            } else if (dVar2.g()) {
                aVar.a((Drawable) null);
            }
            q qVar = new q(this.c, new p(str, aVar, cVarA, strA, dVar2, aVar3, bVar, this.c.a(str)), a(dVar2));
            if (dVar2.s()) {
                qVar.run();
                return;
            } else {
                this.c.a(qVar);
                return;
            }
        }
        com.nostra13.universalimageloader.b.e.a("Load image from memory cache [%s]", strA);
        if (!dVar2.e()) {
            dVar2.q().a(bitmapA, aVar, LoadedFrom.MEMORY_CACHE);
            aVar3.a(str, aVar.d(), bitmapA);
            return;
        }
        v vVar = new v(this.c, bitmapA, new p(str, aVar, cVarA, strA, dVar2, aVar3, bVar, this.c.a(str)), a(dVar2));
        if (dVar2.s()) {
            vVar.run();
        } else {
            this.c.a(vVar);
        }
    }

    public void a(String str, com.nostra13.universalimageloader.core.c.a aVar, d dVar, com.nostra13.universalimageloader.core.d.a aVar2) {
        a(str, aVar, dVar, aVar2, (com.nostra13.universalimageloader.core.d.b) null);
    }

    public void a(String str, com.nostra13.universalimageloader.core.c.a aVar, d dVar, com.nostra13.universalimageloader.core.d.a aVar2, com.nostra13.universalimageloader.core.d.b bVar) {
        a(str, aVar, dVar, null, aVar2, bVar);
    }

    public void b() {
        f();
        this.b.n.b();
    }

    public void c() {
        f();
        this.b.o.a();
    }

    public void d() {
        this.c.a();
    }

    public void e() {
        this.c.b();
    }
}
