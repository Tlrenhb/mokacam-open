package com.aee.mokacam.widget;

import android.graphics.Matrix;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes.dex */
public class j {
    static k a = new k(16);
    static n b = new n(16);

    public static Matrix a() {
        return a.c();
    }

    public static Matrix a(Matrix matrix) {
        Matrix matrixC = a.c();
        if (matrix != null) {
            matrixC.set(matrix);
        }
        return matrixC;
    }

    public static RectF a(float f, float f2, float f3, float f4) {
        RectF rectFC = b.c();
        rectFC.set(f, f2, f3, f4);
        return rectFC;
    }

    public static void a(RectF rectF) {
        b.b(rectF);
    }

    public static float[] a(float[] fArr, Matrix matrix) {
        if (fArr == null || matrix == null) {
            return new float[2];
        }
        float[] fArr2 = new float[2];
        Matrix matrixA = a();
        matrix.invert(matrixA);
        matrixA.mapPoints(fArr2, fArr);
        b(matrixA);
        return fArr2;
    }

    public static float b(float f, float f2, float f3, float f4) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        return (float) Math.sqrt((f5 * f5) + (f6 * f6));
    }

    public static RectF b() {
        return b.c();
    }

    public static void b(Matrix matrix) {
        a.b(matrix);
    }

    public static float[] c(float f, float f2, float f3, float f4) {
        return new float[]{(f + f3) / 2.0f, (f2 + f4) / 2.0f};
    }

    public static float[] c(Matrix matrix) {
        if (matrix == null) {
            return new float[2];
        }
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        return new float[]{fArr[0], fArr[4]};
    }

}
