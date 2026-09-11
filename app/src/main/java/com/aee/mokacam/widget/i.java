package com.aee.mokacam.widget;

import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes.dex */
class i extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ PinchImageView a;
    float[] b;

    public i(PinchImageView pinchImageView, float f, float f2) {
        this.a = pinchImageView;
        setFloatValues(0.0f, 1.0f);
        setDuration(1000000L);
        addUpdateListener(this);
        this.b = new float[]{f, f2};
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        boolean zB = this.a.b(this.b[0], this.b[1]);
        float[] fArr = this.b;
        fArr[0] = fArr[0] * 0.9f;
        float[] fArr2 = this.b;
        fArr2[1] = fArr2[1] * 0.9f;
        if (!zB || j.b(0.0f, 0.0f, this.b[0], this.b[1]) < 1.0f) {
            valueAnimator.cancel();
        }
    }
}
