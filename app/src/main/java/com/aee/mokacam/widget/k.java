package com.aee.mokacam.widget;

import android.graphics.Matrix;

/* JADX INFO: loaded from: classes.dex */
class k extends l<Matrix> {
    public k(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.aee.mokacam.widget.l
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Matrix b() {
        return new Matrix();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.aee.mokacam.widget.l
    public Matrix a(Matrix matrix) {
        matrix.reset();
        return matrix;
    }
}
