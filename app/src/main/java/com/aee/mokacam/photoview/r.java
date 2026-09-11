package com.aee.mokacam.photoview;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class r implements View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, i, q {
    private static /* synthetic */ int[] G;
    private int A;
    private u B;
    private int C;
    private float D;
    private boolean E;
    private ImageView.ScaleType F;
    int a;
    private Interpolator d;
    private float e;
    private float f;
    private float g;
    private boolean h;
    private boolean i;
    private WeakReference<ImageView> j;
    private GestureDetector k;
    private g l;
    private final Matrix m;
    private final Matrix n;
    private final Matrix o;
    private final RectF p;
    private final float[] q;
    private v r;
    private w s;
    private z t;
    private View.OnLongClickListener u;
    private x v;
    private y w;
    private int x;
    private int y;
    private int z;
    private static final boolean c = Log.isLoggable("PhotoViewAttacher", 3);
    static int b = 1;

    public r(ImageView imageView) {
        this(imageView, true);
    }

    public r(ImageView imageView, boolean z) {
        this.d = new AccelerateDecelerateInterpolator();
        this.a = -1010;
        this.e = 1.0f;
        this.f = 1.75f;
        this.g = 3.0f;
        this.h = true;
        this.i = false;
        this.m = new Matrix();
        this.n = new Matrix();
        this.o = new Matrix();
        this.p = new RectF();
        this.q = new float[9];
        this.C = 2;
        this.F = ImageView.ScaleType.FIT_CENTER;
        this.j = new WeakReference<>(imageView);
        imageView.setDrawingCacheEnabled(true);
        imageView.setOnTouchListener(this);
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        b(imageView);
        if (imageView.isInEditMode()) {
            return;
        }
        this.l = ac.a(imageView.getContext(), this);
        this.k = new GestureDetector(imageView.getContext(), new s(this));
        this.k.setOnDoubleTapListener(new c(this));
        this.D = 0.0f;
        b(z);
    }

    private float a(Matrix matrix, int i) {
        matrix.getValues(this.q);
        return this.q[i];
    }

    private RectF a(Matrix matrix) {
        Drawable drawable;
        ImageView imageViewC = c();
        if (imageViewC == null || (drawable = imageViewC.getDrawable()) == null) {
            return null;
        }
        this.p.set(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        matrix.mapRect(this.p);
        return this.p;
    }

    private void a(Drawable drawable) {
        ImageView imageViewC = c();
        if (imageViewC == null || drawable == null) {
            return;
        }
        float fC = c(imageViewC);
        float fD = d(imageViewC);
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        this.m.reset();
        float f = fC / intrinsicWidth;
        float f2 = fD / intrinsicHeight;
        if (this.F != ImageView.ScaleType.CENTER) {
            if (this.F != ImageView.ScaleType.CENTER_CROP) {
                if (this.F != ImageView.ScaleType.CENTER_INSIDE) {
                    RectF rectF = new RectF(0.0f, 0.0f, intrinsicWidth, intrinsicHeight);
                    RectF rectF2 = new RectF(0.0f, 0.0f, fC, fD);
                    if (((int) this.D) % 180 != 0) {
                        rectF = new RectF(0.0f, 0.0f, intrinsicHeight, intrinsicWidth);
                    }
                    switch (n()[this.F.ordinal()]) {
                        case 4:
                            this.m.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
                            break;
                        case 5:
                            this.m.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.END);
                            break;
                        case 6:
                            this.m.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.START);
                            break;
                        case 7:
                            this.m.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
                            break;
                    }
                } else {
                    float fMin = Math.min(1.0f, Math.min(f, f2));
                    this.m.postScale(fMin, fMin);
                    this.m.postTranslate((fC - (intrinsicWidth * fMin)) / 2.0f, (fD - (fMin * intrinsicHeight)) / 2.0f);
                }
            } else {
                float fMax = Math.max(f, f2);
                this.m.postScale(fMax, fMax);
                this.m.postTranslate((fC - (intrinsicWidth * fMax)) / 2.0f, (fD - (fMax * intrinsicHeight)) / 2.0f);
            }
        } else {
            this.m.postTranslate((fC - intrinsicWidth) / 2.0f, (fD - intrinsicHeight) / 2.0f);
        }
        t();
    }

    private static boolean a(ImageView imageView) {
        return (imageView == null || imageView.getDrawable() == null) ? false : true;
    }

    private static void b(float f, float f2, float f3) {
        if (f >= f2) {
            throw new IllegalArgumentException("Minimum zoom has to be less than Medium zoom. Call setMinimumZoom() with a more appropriate value");
        }
        if (f2 >= f3) {
            throw new IllegalArgumentException("Medium zoom has to be less than Maximum zoom. Call setMaximumZoom() with a more appropriate value");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Matrix matrix) {
        RectF rectFA;
        ImageView imageViewC = c();
        if (imageViewC != null) {
            r();
            imageViewC.setImageMatrix(matrix);
            if (this.r == null || (rectFA = a(matrix)) == null) {
                return;
            }
            this.r.a(rectFA);
        }
    }

    private static void b(ImageView imageView) {
        if (imageView == null || (imageView instanceof i) || ImageView.ScaleType.MATRIX.equals(imageView.getScaleType())) {
            return;
        }
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
    }

    private static boolean b(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        switch (n()[scaleType.ordinal()]) {
            case 8:
                throw new IllegalArgumentException(String.valueOf(scaleType.name()) + " is not supported in PhotoView");
            default:
                return true;
        }
    }

    private int c(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    private int d(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }

    static /* synthetic */ int[] n() {
        int[] iArr = G;
        if (iArr == null) {
            iArr = new int[ImageView.ScaleType.values().length];
            try {
                iArr[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ImageView.ScaleType.FIT_START.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            G = iArr;
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Matrix o() {
        this.n.set(this.m);
        this.n.postConcat(this.o);
        return this.n;
    }

    private void p() {
        if (this.B != null) {
            this.B.a();
            this.B = null;
        }
    }

    private void q() {
        if (s()) {
            b(o());
        }
    }

    private void r() {
        ImageView imageViewC = c();
        if (imageViewC != null && !(imageViewC instanceof i) && !ImageView.ScaleType.MATRIX.equals(imageViewC.getScaleType())) {
            throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher. You should call setScaleType on the PhotoViewAttacher instead of on the ImageView");
        }
    }

    private boolean s() {
        RectF rectFA;
        float f;
        float f2 = 0.0f;
        ImageView imageViewC = c();
        if (imageViewC != null && (rectFA = a(o())) != null) {
            float fHeight = rectFA.height();
            float fWidth = rectFA.width();
            int iD = d(imageViewC);
            if (fHeight <= iD) {
                switch (n()[this.F.ordinal()]) {
                    case 5:
                        f = (iD - fHeight) - rectFA.top;
                        break;
                    case 6:
                        f = -rectFA.top;
                        break;
                    default:
                        f = ((iD - fHeight) / 2.0f) - rectFA.top;
                        break;
                }
            } else {
                f = rectFA.top > 0.0f ? -rectFA.top : rectFA.bottom < ((float) iD) ? iD - rectFA.bottom : 0.0f;
            }
            int iC = c(imageViewC);
            if (fWidth <= iC) {
                switch (n()[this.F.ordinal()]) {
                    case 5:
                        f2 = (iC - fWidth) - rectFA.left;
                        break;
                    case 6:
                        f2 = -rectFA.left;
                        break;
                    default:
                        f2 = ((iC - fWidth) / 2.0f) - rectFA.left;
                        break;
                }
                this.C = 2;
            } else if (rectFA.left > 0.0f) {
                this.C = 0;
                f2 = -rectFA.left;
            } else if (rectFA.right < iC) {
                f2 = iC - rectFA.right;
                this.C = 1;
            } else {
                this.C = -1;
            }
            this.o.postTranslate(f2, f);
            return true;
        }
        return false;
    }

    private void t() {
        this.o.reset();
        b(this.D);
        b(o());
        s();
    }

    public void a() {
        if (this.j == null) {
            return;
        }
        ImageView imageView = this.j.get();
        if (imageView != null) {
            ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
            if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                viewTreeObserver.removeGlobalOnLayoutListener(this);
            }
            imageView.setOnTouchListener(null);
            p();
        }
        if (this.k != null) {
            this.k.setOnDoubleTapListener(null);
        }
        this.r = null;
        this.s = null;
        this.t = null;
        this.j = null;
    }

    public void a(float f) {
        this.o.setRotate(f % 360.0f);
        q();
    }

    @Override // com.aee.mokacam.photoview.q
    public void a(float f, float f2) {
        if (this.l.a()) {
            return;
        }
        ImageView imageViewC = c();
        this.o.postTranslate(f, f2);
        q();
        ViewParent parent = imageViewC.getParent();
        if (!this.h || this.l.a() || this.i) {
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        } else if ((this.C == 2 || ((this.C == 0 && f >= 1.0f) || (this.C == 1 && f <= -1.0f))) && parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
    }

    @Override // com.aee.mokacam.photoview.q
    public void a(float f, float f2, float f3) {
        if (g() < this.g || f < 1.0f) {
            if (g() > this.e || f > 1.0f) {
                if (this.v != null) {
                    this.v.a(f, f2, f3);
                }
                this.o.postScale(f, f, f2, f3);
                q();
            }
        }
    }

    @Override // com.aee.mokacam.photoview.q
    public void a(float f, float f2, float f3, float f4) {
        ImageView imageViewC = c();
        this.B = new u(this, imageViewC.getContext());
        this.B.a(c(imageViewC), d(imageViewC), (int) f3, (int) f4);
        imageViewC.post(this.B);
    }

    public void a(float f, float f2, float f3, boolean z) {
        ImageView imageViewC = c();
        if (imageViewC == null || f < this.e || f > this.g) {
            return;
        }
        if (z) {
            imageViewC.post(new t(this, g(), f, f2, f3));
        } else {
            this.o.setScale(f, f, f2, f3);
            q();
        }
    }

    public void a(float f, boolean z) {
        if (c() != null) {
            a(f, r0.getRight() / 2, r0.getBottom() / 2, z);
        }
    }

    public void a(int i) {
        if (i < 0) {
            i = -1010;
        }
        this.a = i;
    }

    public void a(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        if (onDoubleTapListener != null) {
            this.k.setOnDoubleTapListener(onDoubleTapListener);
        } else {
            this.k.setOnDoubleTapListener(new c(this));
        }
    }

    public void a(View.OnLongClickListener onLongClickListener) {
        this.u = onLongClickListener;
    }

    public void a(ImageView.ScaleType scaleType) {
        if (!b(scaleType) || scaleType == this.F) {
            return;
        }
        this.F = scaleType;
        k();
    }

    public void a(v vVar) {
        this.r = vVar;
    }

    public void a(w wVar) {
        this.s = wVar;
    }

    public void a(x xVar) {
        this.v = xVar;
    }

    public void a(y yVar) {
        this.w = yVar;
    }

    public void a(z zVar) {
        this.t = zVar;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public RectF b() {
        s();
        return a(o());
    }

    public void b(float f) {
        this.o.postRotate(f % 360.0f);
        q();
    }

    public void b(boolean z) {
        this.E = z;
        k();
    }

    public ImageView c() {
        ImageView imageView = this.j != null ? this.j.get() : null;
        if (imageView == null) {
            a();
        }
        return imageView;
    }

    public void c(float f) {
        b(f, this.f, this.g);
        this.e = f;
    }

    public float d() {
        return this.e;
    }

    public void d(float f) {
        b(this.e, f, this.g);
        this.f = f;
    }

    public float e() {
        return this.f;
    }

    public void e(float f) {
        b(this.e, this.f, f);
        this.g = f;
    }

    public float f() {
        return this.g;
    }

    public void f(float f) {
        a(f, false);
    }

    public float g() {
        return (float) Math.sqrt(((float) Math.pow(a(this.o, 0), 2.0d)) + ((float) Math.pow(a(this.o, 3), 2.0d)));
    }

    public ImageView.ScaleType h() {
        return this.F;
    }

    w i() {
        return this.s;
    }

    z j() {
        return this.t;
    }

    public void k() {
        ImageView imageViewC = c();
        if (imageViewC != null) {
            if (!this.E) {
                t();
            } else {
                b(imageViewC);
                a(imageViewC.getDrawable());
            }
        }
    }

    public Matrix l() {
        return this.n;
    }

    public Bitmap m() {
        ImageView imageViewC = c();
        if (imageViewC == null) {
            return null;
        }
        return imageViewC.getDrawingCache();
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        ImageView imageViewC = c();
        if (imageViewC != null) {
            if (!this.E) {
                a(imageViewC.getDrawable());
                return;
            }
            int top = imageViewC.getTop();
            int right = imageViewC.getRight();
            int bottom = imageViewC.getBottom();
            int left = imageViewC.getLeft();
            if (top == this.x && bottom == this.z && left == this.A && right == this.y) {
                return;
            }
            a(imageViewC.getDrawable());
            this.x = top;
            this.y = right;
            this.z = bottom;
            this.A = left;
        }
    }

    @Override // android.view.View.OnTouchListener
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean zC;
        RectF rectFB;
        boolean z = false;
        if (!this.E || !a((ImageView) view)) {
            return false;
        }
        ViewParent parent = view.getParent();
        switch (motionEvent.getAction()) {
            case 0:
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                p();
                zC = false;
                break;
            case 1:
            case 3:
                if (g() < this.e && (rectFB = b()) != null) {
                    view.post(new t(this, g(), this.e, rectFB.centerX(), rectFB.centerY()));
                    zC = true;
                } else {
                    zC = false;
                }
                break;
            case 2:
            default:
                zC = false;
                break;
        }
        if (this.l != null) {
            boolean zA = this.l.a();
            boolean zB = this.l.b();
            zC = this.l.c(motionEvent);
            boolean z2 = (zA || this.l.a()) ? false : true;
            boolean z3 = (zB || this.l.b()) ? false : true;
            if (z2 && z3) {
                z = true;
            }
            this.i = z;
        }
        if (this.k == null || !this.k.onTouchEvent(motionEvent)) {
            return zC;
        }
        return true;
    }
}
