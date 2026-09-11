package com.aee.mokacam.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.aee.mokacam.photoview.LazyViewPager;

/* JADX INFO: loaded from: classes.dex */
public class NoScrollViewPager extends LazyViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.aee.mokacam.photoview.LazyViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}
