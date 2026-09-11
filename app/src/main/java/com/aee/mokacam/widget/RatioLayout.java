package com.aee.mokacam.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
public class RatioLayout extends FrameLayout {
    float a;
    int b;

    public RatioLayout(Context context) {
        this(context, null);
    }

    public RatioLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RatioLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 0.0f;
        this.b = 1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RatioLayout);
        this.a = typedArrayObtainStyledAttributes.getFloat(0, 1.0f);
        this.b = typedArrayObtainStyledAttributes.getInt(1, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode == 1073741824 && this.a != 0.0f && this.b == 0) {
            int size = View.MeasureSpec.getSize(i);
            int i3 = (int) ((size / this.a) + 0.5f);
            measureChildren(View.MeasureSpec.makeMeasureSpec((size - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((i3 - getPaddingBottom()) - getPaddingTop(), 1073741824));
            setMeasuredDimension(size, i3);
            return;
        }
        if (mode2 != 1073741824 || this.a == 0.0f || this.b != 1) {
            super.onMeasure(i, i2);
            return;
        }
        int size2 = View.MeasureSpec.getSize(i2);
        int i4 = (int) ((this.a * size2) + 0.5f);
        measureChildren(View.MeasureSpec.makeMeasureSpec((i4 - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((size2 - getPaddingBottom()) - getPaddingTop(), 1073741824));
        setMeasuredDimension(i4, size2);
    }

    public void setPicRatio(float f) {
        this.a = f;
    }
}
