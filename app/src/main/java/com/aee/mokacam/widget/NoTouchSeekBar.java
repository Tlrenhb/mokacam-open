package com.aee.mokacam.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

/* JADX INFO: loaded from: classes.dex */
public class NoTouchSeekBar extends SeekBar {
    public NoTouchSeekBar(Context context) {
        super(context);
    }

    public NoTouchSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NoTouchSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}
