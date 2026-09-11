package com.aee.mokacam.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/* JADX INFO: loaded from: classes.dex */
public class SlidingMenuView extends ViewGroup {
    private View a;
    private View b;
    private int c;
    private int d;
    private int e;
    private Scroller f;
    private boolean g;

    public SlidingMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = 0;
        this.g = false;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.e = displayMetrics.widthPixels;
        this.f = new Scroller(context);
    }

    public void a() {
        a(this.c);
        this.g = true;
    }

    public void a(int i) {
        int scrollX = getScrollX();
        this.f.startScroll(scrollX, 0, i - scrollX, 0, 500);
        invalidate();
    }

    public void b() {
        a(0);
        this.g = false;
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.f.computeScrollOffset()) {
            scrollTo(this.f.getCurrX(), 0);
            invalidate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.a.layout(0, 0, this.a.getMeasuredWidth(), this.a.getMeasuredHeight());
        this.b.layout(this.d, 0, this.d + this.b.getMeasuredWidth(), this.b.getMeasuredHeight());
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        this.a = getChildAt(0);
        this.b = getChildAt(1);
        this.b.measure(View.MeasureSpec.makeMeasureSpec(this.b.getLayoutParams().width, 1073741824), i2);
        this.a.measure(i, i2);
        this.c = this.e - this.b.getMeasuredWidth();
        this.d = this.e;
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }
}
