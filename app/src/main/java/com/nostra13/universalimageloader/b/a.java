package com.nostra13.universalimageloader.b;

import android.opengl.GLES10;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    private static com.nostra13.universalimageloader.core.assist.c a;

    static {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        int iMax = Math.max(iArr[0], 2048);
        a = new com.nostra13.universalimageloader.core.assist.c(iMax, iMax);
    }

    private static int a(int i, int i2, int i3, boolean z) {
        int iA = a.a();
        int iB = a.b();
        while (true) {
            if (i / i3 <= iA && i2 / i3 <= iB) {
                return i3;
            }
            i3 = z ? i3 * 2 : i3 + 1;
        }
    }

    public static int a(com.nostra13.universalimageloader.core.assist.c cVar) {
        int iA = cVar.a();
        int iB = cVar.b();
        return Math.max((int) Math.ceil(iA / a.a()), (int) Math.ceil(iB / a.b()));
    }

    public static int a(com.nostra13.universalimageloader.core.assist.c cVar, com.nostra13.universalimageloader.core.assist.c cVar2, ViewScaleType viewScaleType, boolean z) {
        int iMin;
        int iA = cVar.a();
        int iB = cVar.b();
        int iA2 = cVar2.a();
        int iB2 = cVar2.b();
        switch (viewScaleType) {
            case FIT_INSIDE:
                if (!z) {
                    iMin = Math.max(iA / iA2, iB / iB2);
                } else {
                    int i = iA / 2;
                    int i2 = iB / 2;
                    iMin = 1;
                    while (true) {
                        if (i / iMin > iA2 || i2 / iMin > iB2) {
                            iMin *= 2;
                        }
                    }
                }
                break;
            case CROP:
                if (!z) {
                    iMin = Math.min(iA / iA2, iB / iB2);
                } else {
                    int i3 = iA / 2;
                    int i4 = iB / 2;
                    iMin = 1;
                    while (i3 / iMin > iA2 && i4 / iMin > iB2) {
                        iMin *= 2;
                    }
                }
                break;
            default:
                iMin = 1;
                break;
        }
        return a(iA, iB, iMin >= 1 ? iMin : 1, z);
    }

    public static com.nostra13.universalimageloader.core.assist.c a(com.nostra13.universalimageloader.core.c.a aVar, com.nostra13.universalimageloader.core.assist.c cVar) {
        int iA = aVar.a();
        if (iA <= 0) {
            iA = cVar.a();
        }
        int iB = aVar.b();
        if (iB <= 0) {
            iB = cVar.b();
        }
        return new com.nostra13.universalimageloader.core.assist.c(iA, iB);
    }

    public static float b(com.nostra13.universalimageloader.core.assist.c cVar, com.nostra13.universalimageloader.core.assist.c cVar2, ViewScaleType viewScaleType, boolean z) {
        int i;
        int i2;
        int iA = cVar.a();
        int iB = cVar.b();
        int iA2 = cVar2.a();
        int iB2 = cVar2.b();
        float f = iA / iA2;
        float f2 = iB / iB2;
        if ((viewScaleType != ViewScaleType.FIT_INSIDE || f < f2) && (viewScaleType != ViewScaleType.CROP || f >= f2)) {
            i = (int) (iA / f2);
            i2 = iB2;
        } else {
            int i3 = (int) (iB / f);
            i = iA2;
            i2 = i3;
        }
        if ((z || i >= iA || i2 >= iB) && (!z || i == iA || i2 == iB)) {
            return 1.0f;
        }
        return i / iA;
    }
}
