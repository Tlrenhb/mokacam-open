package com.aee.mokacam.widget;

import android.animation.ValueAnimator;
import android.graphics.Matrix;

/* JADX INFO: loaded from: classes.dex */
class o extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ PinchImageView a;
    float[] b;
    float[] c;
    float[] d;

    public o(PinchImageView pinchImageView, Matrix matrix, Matrix matrix2) {
        this(pinchImageView, matrix, matrix2, 200L);
    }

    public o(PinchImageView pinchImageView, Matrix matrix, Matrix matrix2, long j) {
        this.a = pinchImageView;
        this.b = new float[9];
        this.c = new float[9];
        this.d = new float[9];
        setFloatValues(0.0f, 1.0f);
        setDuration(j);
        addUpdateListener(this);
        matrix.getValues(this.b);
        matrix2.getValues(this.c);
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        for (int i = 0; i < 9; i++) {
            this.d[i] = this.b[i] + ((this.c[i] - this.b[i]) * fFloatValue);
        }
        this.a.c.setValues(this.d);
        this.a.a();
        this.a.invalidate();
    }
}
