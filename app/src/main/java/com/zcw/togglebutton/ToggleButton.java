package com.zcw.togglebutton;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.aee.mokacam.R;
import com.facebook.rebound.h;
import com.facebook.rebound.i;
import com.facebook.rebound.k;
import com.facebook.rebound.o;
import com.facebook.rebound.q;

/* JADX INFO: loaded from: classes.dex */
public class ToggleButton extends View {
    h a;
    private o b;
    private i c;
    private float d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private Paint j;
    private boolean k;
    private int l;
    private float m;
    private float n;
    private float o;
    private float p;
    private float q;
    private int r;
    private float s;
    private float t;
    private RectF u;
    private boolean v;
    private boolean w;
    private c x;

    private ToggleButton(Context context) {
        super(context);
        this.e = Color.parseColor("#4ebb7f");
        this.f = Color.parseColor("#dadbda");
        this.g = Color.parseColor("#ffffff");
        this.h = Color.parseColor("#ffffff");
        this.i = this.f;
        this.k = false;
        this.l = 2;
        this.u = new RectF();
        this.v = true;
        this.w = false;
        this.a = new a(this);
    }

    public ToggleButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = Color.parseColor("#4ebb7f");
        this.f = Color.parseColor("#dadbda");
        this.g = Color.parseColor("#ffffff");
        this.h = Color.parseColor("#ffffff");
        this.i = this.f;
        this.k = false;
        this.l = 2;
        this.u = new RectF();
        this.v = true;
        this.w = false;
        this.a = new a(this);
        setup(attributeSet);
    }

    public ToggleButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = Color.parseColor("#4ebb7f");
        this.f = Color.parseColor("#dadbda");
        this.g = Color.parseColor("#ffffff");
        this.h = Color.parseColor("#ffffff");
        this.i = this.f;
        this.k = false;
        this.l = 2;
        this.u = new RectF();
        this.v = true;
        this.w = false;
        this.a = new a(this);
        setup(attributeSet);
    }

    private int a(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(double d) {
        this.s = (float) q.a(d, 0.0d, 1.0d, this.p, this.q);
        this.t = (float) q.a(1.0d - d, 0.0d, 1.0d, 10.0d, this.r);
        int iBlue = Color.blue(this.e);
        int iRed = Color.red(this.e);
        int iGreen = Color.green(this.e);
        int iBlue2 = Color.blue(this.f);
        int iRed2 = Color.red(this.f);
        int iGreen2 = Color.green(this.f);
        int iA = (int) q.a(1.0d - d, 0.0d, 1.0d, iBlue, iBlue2);
        this.i = Color.rgb(a((int) q.a(1.0d - d, 0.0d, 1.0d, iRed, iRed2), 0, 255), a((int) q.a(1.0d - d, 0.0d, 1.0d, iGreen, iGreen2), 0, 255), a(iA, 0, 255));
        postInvalidate();
    }

    private void b(boolean z) {
        if (z) {
            this.c.b(this.k ? 1 : 0);
        } else {
            this.c.a(this.k ? 1 : 0);
            a(this.k ? 1 : 0);
        }
    }

    public void a() {
        b();
        if (this.x != null) {
            this.x.a(this.k);
        }
    }

    public void a(boolean z) {
        this.k = !this.k;
        b(z);
        if (this.x != null) {
            this.x.a(this.k);
        }
    }

    public void b() {
        setToggleOn(true);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.u.set(0.0f, 0.0f, getWidth(), getHeight());
        this.j.setColor(this.i);
        canvas.drawRoundRect(this.u, this.d, this.d, this.j);
        if (this.t > 0.0f) {
            float f = this.t * 0.5f;
            this.u.set(this.s - f, this.m - f, this.o + f, this.m + f);
            this.j.setColor(this.g);
            canvas.drawRoundRect(this.u, f, f, this.j);
        }
        this.u.set((this.s - 1.0f) - this.d, this.m - this.d, this.s + 1.1f + this.d, this.m + this.d);
        this.j.setColor(this.i);
        canvas.drawRoundRect(this.u, this.d, this.d, this.j);
        float f2 = this.r * 0.5f;
        this.u.set(this.s - f2, this.m - f2, this.s + f2, this.m + f2);
        this.j.setColor(this.h);
        canvas.drawRoundRect(this.u, f2, f2, this.j);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.c.a(this.a);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.c.b(this.a);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int width = getWidth();
        int height = getHeight();
        this.d = Math.min(width, height) * 0.5f;
        this.m = this.d;
        this.n = this.d;
        this.o = width - this.d;
        this.p = this.n + this.l;
        this.q = this.o - this.l;
        this.r = height - (this.l * 4);
        this.s = this.k ? this.q : this.p;
        this.t = 0.0f;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        View.MeasureSpec.getSize(i);
        int size = View.MeasureSpec.getSize(i2);
        Resources system = Resources.getSystem();
        if (mode == 0 || mode == Integer.MIN_VALUE) {
            i = View.MeasureSpec.makeMeasureSpec((int) TypedValue.applyDimension(1, 50.0f, system.getDisplayMetrics()), 1073741824);
        }
        if (mode2 == 0 || size == Integer.MIN_VALUE) {
            i2 = View.MeasureSpec.makeMeasureSpec((int) TypedValue.applyDimension(1, 30.0f, system.getDisplayMetrics()), 1073741824);
        }
        super.onMeasure(i, i2);
    }

    public void setAnimate(boolean z) {
        this.v = z;
    }

    public void setOnToggleChanged(c cVar) {
        this.x = cVar;
    }

    public void setToggleOff(boolean z) {
        this.k = false;
        b(z);
    }

    public void setToggleOn(boolean z) {
        this.k = true;
        b(z);
    }

    public void setup(AttributeSet attributeSet) {
        this.j = new Paint(1);
        this.j.setStyle(Paint.Style.FILL);
        this.j.setStrokeCap(Paint.Cap.ROUND);
        this.b = o.c();
        this.c = this.b.b();
        this.c.a(k.a(50.0d, 7.0d));
        setOnClickListener(new b(this));
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ToggleButton);
        this.f = typedArrayObtainStyledAttributes.getColor(1, this.f);
        this.e = typedArrayObtainStyledAttributes.getColor(3, this.e);
        this.h = typedArrayObtainStyledAttributes.getColor(4, this.h);
        this.g = typedArrayObtainStyledAttributes.getColor(2, this.g);
        this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, this.l);
        this.v = typedArrayObtainStyledAttributes.getBoolean(5, this.v);
        this.w = typedArrayObtainStyledAttributes.getBoolean(6, this.w);
        typedArrayObtainStyledAttributes.recycle();
        this.i = this.f;
        if (this.w) {
            a();
        }
    }
}
