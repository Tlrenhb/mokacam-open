package com.aee.mokacam.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
public class AppSingleRocker extends SurfaceView implements SurfaceHolder.Callback {
    public Point a;
    int b;
    Bitmap c;
    Bitmap d;
    int e;
    private SurfaceHolder f;
    private Paint g;
    private Point h;
    private int i;
    private int j;
    private a k;
    private Point l;
    private SensorEventListener m;
    private SensorManager n;
    private int o;
    private int p;
    private TextPaint q;
    private int r;
    private float s;
    private float t;
    private float u;

    public AppSingleRocker(Context context) {
        super(context);
        this.b = 0;
        this.k = null;
        this.r = 0;
        this.u = 1.0f;
        c();
    }

    public AppSingleRocker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 0;
        this.k = null;
        this.r = 0;
        this.u = 1.0f;
        c();
    }

    public AppSingleRocker(Context context, View view) {
        super(context);
        this.b = 0;
        this.k = null;
        this.r = 0;
        this.u = 1.0f;
        c();
    }

    private int a(float f) {
        int iRound = (int) Math.round((((double) f) / 3.141592653589793d) * 180.0d);
        return iRound < 0 ? -iRound : (180 - iRound) + 180;
    }

    private void c() {
        setKeepScreenOn(true);
        this.f = getHolder();
        this.f.addCallback(this);
        this.g = new Paint();
        this.g.setColor(-1);
        this.g.setTextSize(18.0f);
        this.g.setAntiAlias(true);
        this.q = new TextPaint();
        this.q.setARGB(255, 255, 255, 255);
        this.q.setTextSize(20.0f);
        this.q.setAntiAlias(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setZOrderOnTop(true);
        this.f.setFormat(-2);
        this.c = BitmapFactory.decodeResource(getResources(), R.drawable.rocker_control_frame);
        this.d = BitmapFactory.decodeResource(getResources(), R.drawable.btn_rocker_control);
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        this.j = this.d.getWidth() / 2;
        this.i = this.c.getWidth() / 2;
        this.h = new Point(width, height);
        this.l = new Point(this.h.x + this.i, this.h.y + this.i);
        this.a = new Point(this.l.x - this.j, this.l.y - this.j);
    }

    public void a() {
        if (this.n != null) {
            this.n.unregisterListener(this.m);
            this.a = new Point(this.l.x - this.j, this.l.y - this.j);
            this.s = 0.0f;
            this.t = 0.0f;
            a(3, 0, 0.0f);
            b();
            this.n = null;
        }
    }

    public void a(int i, int i2, float f) {
        if (this.k != null) {
            this.k.a(this.r, i, i2, f);
        }
    }

    public void b() {
        Canvas canvasLockCanvas = null;
        try {
            try {
                canvasLockCanvas = this.f.lockCanvas();
                canvasLockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                canvasLockCanvas.drawBitmap(this.c, this.h.x, this.h.y, this.g);
                canvasLockCanvas.drawBitmap(this.d, this.a.x, this.a.y, this.g);
                if (canvasLockCanvas != null) {
                    this.f.unlockCanvasAndPost(canvasLockCanvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (canvasLockCanvas != null) {
                    this.f.unlockCanvasAndPost(canvasLockCanvas);
                }
            }
        } catch (Throwable th) {
            if (canvasLockCanvas != null) {
                this.f.unlockCanvasAndPost(canvasLockCanvas);
            }
            throw th;
        }
    }

    public int getRockerType() {
        return this.r;
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        this.o = View.MeasureSpec.getSize(i) / 2;
        this.p = View.MeasureSpec.getSize(i2) / 2;
        this.s = this.o - this.j;
        this.t = this.p - this.j;
        this.j = this.d.getWidth() / 2;
        this.i = this.c.getWidth() / 2;
        this.h = new Point(this.o - this.i, this.p - this.i);
        this.l = new Point(this.h.x + this.i, this.h.y + this.i);
        this.a = new Point(this.l.x - this.j, this.l.y - this.j);
        super.onMeasure(i, i2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:7:0x000c A[Catch: Exception -> 0x0098, TryCatch #0 {Exception -> 0x0098, blocks: (B:3:0x0001, B:5:0x0005, B:6:0x0009, B:7:0x000c, B:9:0x0015, B:12:0x0034, B:14:0x0059, B:15:0x0075, B:17:0x0079, B:20:0x009b, B:22:0x0103, B:23:0x010a, B:25:0x0115, B:26:0x011b, B:28:0x0130, B:27:0x012d, B:29:0x0132, B:30:0x014f), top: B:32:0x0001 }] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float fSqrt;
        try {
            if (this.b == 0) {
                switch (motionEvent.getAction()) {
                    case 0:
                        this.e = com.aee.mokacam.utils.n.a(this.l.x, this.l.y, motionEvent.getX(), motionEvent.getY());
                        if (this.e <= this.i) {
                            b();
                            Thread.sleep(30L);
                        }
                        break;
                    case 1:
                        this.a = new Point(this.l.x - this.j, this.l.y - this.j);
                        a(3, 0, 0.0f);
                        b();
                        Thread.sleep(30L);
                        break;
                    case 2:
                        this.e = com.aee.mokacam.utils.n.a(this.l.x, this.l.y, motionEvent.getX(), motionEvent.getY());
                        if (this.e <= this.i * this.u) {
                            this.a.set(((int) motionEvent.getX()) - this.j, ((int) motionEvent.getY()) - this.j);
                            fSqrt = this.e / this.i;
                        } else {
                            this.a = com.aee.mokacam.utils.n.a(this.l, new Point((int) motionEvent.getX(), (int) motionEvent.getY()), (int) (this.i * this.u));
                            this.a = new Point(this.a.x - this.j, this.a.y - this.j);
                            int iA = com.aee.mokacam.utils.n.a(this.l.x, motionEvent.getY(), motionEvent.getX(), motionEvent.getY());
                            int iA2 = com.aee.mokacam.utils.n.a(motionEvent.getX(), this.l.y, motionEvent.getX(), motionEvent.getY());
                            float f = ((float) iA) >= ((float) this.i) * this.u ? this.i * this.u : iA;
                            float f2 = ((float) iA2) >= ((float) this.i) * this.u ? this.i * this.u : iA2;
                            fSqrt = (((float) Math.sqrt((f2 * f2) + (f * f))) / this.i) * this.u;
                        }
                        if (this.k != null) {
                            a(1, a(com.aee.mokacam.utils.n.a(this.l, new Point((int) motionEvent.getX(), (int) motionEvent.getY()))), fSqrt);
                        }
                        b();
                        Thread.sleep(30L);
                        break;
                }
            } else {
                Thread.sleep(40L);
            }
        } catch (Exception e) {
        }
        return true;
    }

    public void setRockerType(int i) {
        this.r = i;
    }

    public void setSingleRudderListener(a aVar) {
        this.k = aVar;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        b();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        a();
    }
}
