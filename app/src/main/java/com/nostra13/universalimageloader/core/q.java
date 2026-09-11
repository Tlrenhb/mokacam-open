package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
final class q implements com.nostra13.universalimageloader.b.d, Runnable {
    final String a;
    final com.nostra13.universalimageloader.core.c.a b;
    final d c;
    final com.nostra13.universalimageloader.core.d.a d;
    final com.nostra13.universalimageloader.core.d.b e;
    private final ImageLoaderEngine f;
    private final p g;
    private final Handler h;
    private final j i;
    private final ImageDownloader j;
    private final ImageDownloader k;
    private final ImageDownloader l;
    private final com.nostra13.universalimageloader.core.a.d m;
    private final String n;
    private final com.nostra13.universalimageloader.core.assist.c o;
    private final boolean p;
    private LoadedFrom q = LoadedFrom.NETWORK;

    public q(ImageLoaderEngine imageLoaderEngine, p pVar, Handler handler) {
        this.f = imageLoaderEngine;
        this.g = pVar;
        this.h = handler;
        this.i = imageLoaderEngine.a;
        this.j = this.i.p;
        this.k = this.i.s;
        this.l = this.i.t;
        this.m = this.i.q;
        this.a = pVar.a;
        this.n = pVar.b;
        this.b = pVar.c;
        this.o = pVar.d;
        this.c = pVar.e;
        this.d = pVar.f;
        this.e = pVar.g;
        this.p = this.c.s();
    }

    private Bitmap a(String str) {
        return this.m.a(new com.nostra13.universalimageloader.core.a.e(this.n, str, this.a, this.o, this.b.c(), h(), this.c));
    }

    private void a(FailReason.FailType failType, Throwable th) {
        if (this.p || p() || j()) {
            return;
        }
        a(new s(this, failType, th), false, this.h, this.f);
    }

    static void a(Runnable runnable, boolean z, Handler handler, ImageLoaderEngine imageLoaderEngine) {
        if (z) {
            runnable.run();
        } else if (handler == null) {
            imageLoaderEngine.fireCallback(runnable);
        } else {
            handler.post(runnable);
        }
    }

    private boolean b() {
        AtomicBoolean atomicBooleanC = this.f.c();
        if (atomicBooleanC.get()) {
            synchronized (this.f.d()) {
                if (atomicBooleanC.get()) {
                    com.nostra13.universalimageloader.b.e.a("ImageLoader is paused. Waiting...  [%s]", this.n);
                    try {
                        this.f.d().wait();
                        com.nostra13.universalimageloader.b.e.a(".. Resume loading [%s]", this.n);
                    } catch (InterruptedException e) {
                        com.nostra13.universalimageloader.b.e.d("Task was interrupted [%s]", this.n);
                        return true;
                    }
                }
            }
        }
        return j();
    }

    private boolean b(int i, int i2) {
        File fileA = this.i.o.a(this.a);
        if (fileA != null && fileA.exists()) {
            Bitmap bitmapA = this.m.a(new com.nostra13.universalimageloader.core.a.e(this.n, ImageDownloader.Scheme.FILE.wrap(fileA.getAbsolutePath()), this.a, new com.nostra13.universalimageloader.core.assist.c(i, i2), ViewScaleType.FIT_INSIDE, h(), new f().a(this.c).a(ImageScaleType.IN_SAMPLE_INT).a()));
            if (bitmapA != null && this.i.f != null) {
                com.nostra13.universalimageloader.b.e.a("Process image before cache on disk [%s]", this.n);
                bitmapA = this.i.f.a(bitmapA);
                if (bitmapA == null) {
                    com.nostra13.universalimageloader.b.e.d("Bitmap processor for disk cache returned null [%s]", this.n);
                }
            }
            Bitmap bitmap = bitmapA;
            if (bitmap != null) {
                boolean zA = this.i.o.a(this.a, bitmap);
                bitmap.recycle();
                return zA;
            }
        }
        return false;
    }

    private boolean c() {
        if (!this.c.f()) {
            return false;
        }
        com.nostra13.universalimageloader.b.e.a("Delay %d ms before loading...  [%s]", Integer.valueOf(this.c.l()), this.n);
        try {
            Thread.sleep(this.c.l());
            return j();
        } catch (InterruptedException e) {
            com.nostra13.universalimageloader.b.e.d("Task was interrupted [%s]", this.n);
            return true;
        }
    }

    private boolean c(int i, int i2) {
        if (p() || j()) {
            return false;
        }
        if (this.e != null) {
            a(new r(this, i, i2), false, this.h, this.f);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004d A[Catch: u -> 0x00a8, Throwable -> 0x00d1, OutOfMemoryError -> 0x00d3, IOException -> 0x00d5, IllegalStateException -> 0x00d7, TryCatch #7 {u -> 0x00a8, blocks: (B:3:0x0001, B:5:0x000d, B:7:0x0013, B:9:0x001d, B:11:0x0041, B:13:0x0047, B:15:0x004d, B:17:0x0068, B:19:0x006e, B:21:0x007a, B:22:0x0084, B:24:0x008d, B:26:0x0093, B:28:0x0099), top: B:53:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Bitmap d() throws u {
        Bitmap bitmapA;
        Throwable th;
        OutOfMemoryError e;
        IOException e2;
        File fileA;
        try {
            try {
                File fileA2 = this.i.o.a(this.a);
                if (fileA2 == null || !fileA2.exists() || fileA2.length() <= 0) {
                    bitmapA = null;
                } else {
                    com.nostra13.universalimageloader.b.e.a("Load image from disk cache [%s]", this.n);
                    this.q = LoadedFrom.DISC_CACHE;
                    i();
                    bitmapA = a(ImageDownloader.Scheme.FILE.wrap(fileA2.getAbsolutePath()));
                }
                if (bitmapA != null) {
                    try {
                        if (bitmapA.getWidth() <= 0 || bitmapA.getHeight() <= 0) {
                            com.nostra13.universalimageloader.b.e.a("Load image from network [%s]", this.n);
                            this.q = LoadedFrom.NETWORK;
                            String strWrap = this.a;
                            if (this.c.i() && e() && (fileA = this.i.o.a(this.a)) != null) {
                                strWrap = ImageDownloader.Scheme.FILE.wrap(fileA.getAbsolutePath());
                            }
                            i();
                            bitmapA = a(strWrap);
                            if (bitmapA == null || bitmapA.getWidth() <= 0 || bitmapA.getHeight() <= 0) {
                                a(FailReason.FailType.DECODING_ERROR, (Throwable) null);
                            }
                        }
                    } catch (IOException e3) {
                        e2 = e3;
                        com.nostra13.universalimageloader.b.e.a(e2);
                        a(FailReason.FailType.IO_ERROR, e2);
                    } catch (IllegalStateException e4) {
                        a(FailReason.FailType.NETWORK_DENIED, (Throwable) null);
                    } catch (OutOfMemoryError e5) {
                        e = e5;
                        com.nostra13.universalimageloader.b.e.a(e);
                        a(FailReason.FailType.OUT_OF_MEMORY, e);
                    } catch (Throwable th2) {
                        th = th2;
                        com.nostra13.universalimageloader.b.e.a(th);
                        a(FailReason.FailType.UNKNOWN, th);
                    }
                }
            } catch (u e6) {
                throw e6;
            }
        } catch (IOException e7) {
            bitmapA = null;
            e2 = e7;
        } catch (IllegalStateException e8) {
            bitmapA = null;
        } catch (OutOfMemoryError e9) {
            bitmapA = null;
            e = e9;
        } catch (Throwable th3) {
            bitmapA = null;
            th = th3;
        }
        return bitmapA;
    }

    private boolean e() {
        com.nostra13.universalimageloader.b.e.a("Cache image on disk [%s]", this.n);
        try {
            boolean zF = f();
            if (!zF) {
                return zF;
            }
            int i = this.i.d;
            int i2 = this.i.e;
            if (i <= 0 && i2 <= 0) {
                return zF;
            }
            com.nostra13.universalimageloader.b.e.a("Resize image in disk cache [%s]", this.n);
            b(i, i2);
            return zF;
        } catch (IOException e) {
            com.nostra13.universalimageloader.b.e.a(e);
            return false;
        }
    }

    private boolean f() {
        boolean zA = false;
        InputStream inputStreamA = h().a(this.a, this.c.n());
        if (inputStreamA == null) {
            com.nostra13.universalimageloader.b.e.d("No stream for image [%s]", this.n);
        } else {
            try {
                zA = this.i.o.a(this.a, inputStreamA, this);
            } finally {
                com.nostra13.universalimageloader.b.c.a((Closeable) inputStreamA);
            }
        }
        return zA;
    }

    private void g() {
        if (this.p || p()) {
            return;
        }
        a(new t(this), false, this.h, this.f);
    }

    private ImageDownloader h() {
        return this.f.e() ? this.k : this.f.f() ? this.l : this.j;
    }

    private void i() throws u {
        k();
        m();
    }

    private boolean j() {
        return l() || n();
    }

    private void k() throws u {
        if (l()) {
            throw new u(this);
        }
    }

    private boolean l() {
        if (!this.b.e()) {
            return false;
        }
        com.nostra13.universalimageloader.b.e.a("ImageAware was collected by GC. Task is cancelled. [%s]", this.n);
        return true;
    }

    private void m() throws u {
        if (n()) {
            throw new u(this);
        }
    }

    private boolean n() {
        if (!(!this.n.equals(this.f.a(this.b)))) {
            return false;
        }
        com.nostra13.universalimageloader.b.e.a("ImageAware is reused for another image. Task is cancelled. [%s]", this.n);
        return true;
    }

    private void o() throws u {
        if (p()) {
            throw new u(this);
        }
    }

    private boolean p() {
        if (!Thread.interrupted()) {
            return false;
        }
        com.nostra13.universalimageloader.b.e.a("Task was interrupted [%s]", this.n);
        return true;
    }

    String a() {
        return this.a;
    }

    @Override // com.nostra13.universalimageloader.b.d
    public boolean a(int i, int i2) {
        return this.p || c(i, i2);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (b() || c()) {
            return;
        }
        ReentrantLock reentrantLock = this.g.h;
        com.nostra13.universalimageloader.b.e.a("Start display image task [%s]", this.n);
        if (reentrantLock.isLocked()) {
            com.nostra13.universalimageloader.b.e.a("Image already is loading. Waiting... [%s]", this.n);
        }
        reentrantLock.lock();
        try {
            i();
            Bitmap bitmapA = this.i.n.a(this.n);
            if (bitmapA == null || bitmapA.isRecycled()) {
                bitmapA = d();
                if (bitmapA == null) {
                    return;
                }
                i();
                o();
                if (this.c.d()) {
                    com.nostra13.universalimageloader.b.e.a("PreProcess image before caching in memory [%s]", this.n);
                    bitmapA = this.c.o().a(bitmapA);
                    if (bitmapA == null) {
                        com.nostra13.universalimageloader.b.e.d("Pre-processor returned null [%s]", this.n);
                    }
                }
                if (bitmapA != null && this.c.h()) {
                    com.nostra13.universalimageloader.b.e.a("Cache image in memory [%s]", this.n);
                    this.i.n.a(this.n, bitmapA);
                }
            } else {
                this.q = LoadedFrom.MEMORY_CACHE;
                com.nostra13.universalimageloader.b.e.a("...Get cached bitmap from memory after waiting. [%s]", this.n);
            }
            if (bitmapA != null && this.c.e()) {
                com.nostra13.universalimageloader.b.e.a("PostProcess image before displaying [%s]", this.n);
                bitmapA = this.c.p().a(bitmapA);
                if (bitmapA == null) {
                    com.nostra13.universalimageloader.b.e.d("Post-processor returned null [%s]", this.n);
                }
            }
            i();
            o();
            reentrantLock.unlock();
            a(new c(bitmapA, this.g, this.f, this.q), this.p, this.h, this.f);
        } catch (u e) {
            g();
        } finally {
            reentrantLock.unlock();
        }
    }
}
