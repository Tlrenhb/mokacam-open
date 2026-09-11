package com.aee.mokacam.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
public class BidirSlidingLayout extends RelativeLayout implements View.OnTouchListener {
    int a;
    int b;
    int c;
    float d;
    float e;
    float f;
    float g;
    float h;
    boolean i;
    boolean j;
    boolean k;
    View l;
    View m;
    View n;
    View o;
    ViewGroup.MarginLayoutParams p;
    ViewGroup.MarginLayoutParams q;
    RelativeLayout.LayoutParams r;
    VelocityTracker s;

    public BidirSlidingLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
        this.c = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    void a(int i, int i2) {
        if (this.j) {
            if (this.k || Math.abs(i) < this.c || i <= 0) {
                return;
            }
            this.k = true;
            this.a = 4;
            return;
        }
        if (this.k || Math.abs(i) < this.c || i >= 0 || Math.abs(i2) >= this.c) {
            return;
        }
        this.k = true;
        this.a = 2;
        c();
    }

    /* JADX INFO: Access modifiers changed from: */
    public void a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void a(MotionEvent motionEvent) {
        if (this.s == null) {
            this.s = VelocityTracker.obtain();
        }
        this.s.addMovement(motionEvent);
    }

    void d() {
        if (this.r.leftMargin > 0) {
            this.r.leftMargin = 0;
        } else if (this.r.leftMargin < (-this.q.width)) {
            this.r.leftMargin = -this.q.width;
        }
    }

    boolean e() {
        return this.d - this.h > ((float) (this.q.width / 2)) || getScrollVelocity() > 200;
    }

    boolean f() {
        return this.h - this.d > ((float) (this.q.width / 2)) || getScrollVelocity() > 200;
    }

    void g() {
        this.s.recycle();
        this.s = null;
    }

    int getScrollVelocity() {
        this.s.computeCurrentVelocity(1000);
        return Math.abs((int) this.s.getXVelocity());
    }

    /* JADX INFO: Access modifiers changed from: */
    public void h() {
        if (this.o != null) {
            this.o.setPressed(false);
            this.o.setFocusable(false);
            this.o.setFocusableInTouchMode(false);
        }
    }

    public void a() {
        new b(this).execute(-30);
    }

    public void b() {
        new b(this).execute(30);
    }

    public void c() {
        this.r.leftMargin = 0;
        this.r.addRule(11, 0);
        this.r.addRule(9);
        this.n.setLayoutParams(this.r);
        this.m.setVisibility(0);
        this.l.setVisibility(8);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            this.l = getChildAt(0);
            this.p = (ViewGroup.MarginLayoutParams) this.l.getLayoutParams();
            this.m = getChildAt(1);
            this.q = (ViewGroup.MarginLayoutParams) this.m.getLayoutParams();
            this.n = getChildAt(2);
            this.r = (RelativeLayout.LayoutParams) this.n.getLayoutParams();
            this.r.width = this.b;
            this.n.setLayoutParams(this.r);
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        a(motionEvent);
        switch (motionEvent.getAction()) {
            case 0:
                this.d = motionEvent.getRawX();
                this.e = motionEvent.getRawY();
                this.a = 0;
                break;
            case 1:
                this.h = motionEvent.getRawX();
                int i = (int) (this.h - this.d);
                if (this.k) {
                    switch (this.a) {
                        case 2:
                            if (!e()) {
                                b();
                            } else {
                                a();
                            }
                            break;
                        case 4:
                            if (!f()) {
                                a();
                            } else {
                                b();
                            }
                            break;
                    }
                } else if (i < this.c && this.j) {
                    b();
                }
                g();
                break;
            case 2:
                this.f = motionEvent.getRawX();
                this.g = motionEvent.getRawY();
                int i2 = (int) (this.f - this.d);
                a(i2, (int) (this.g - this.e));
                switch (this.a) {
                    case 2:
                        this.r.leftMargin = i2;
                        d();
                        this.n.setLayoutParams(this.r);
                        break;
                    case 4:
                        this.r.leftMargin = i2 + (-this.q.width);
                        d();
                        this.n.setLayoutParams(this.r);
                        break;
                }
                break;
        }
        if (!view.isEnabled()) {
            return true;
        }
        if (!this.k) {
            return this.i || this.j;
        }
        h();
        return true;
    }

    public void setScrollEvent(View view) {
        this.o = view;
        this.o.setOnTouchListener(this);
    }
}
