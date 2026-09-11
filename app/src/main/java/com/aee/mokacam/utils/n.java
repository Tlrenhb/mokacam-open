package com.aee.mokacam.utils;

import android.graphics.Point;

/* JADX INFO: loaded from: classes.dex */
public class n {
    public static float a(Point point, Point point2) {
        float f = point2.x - point.x;
        float f2 = point2.y - point.y;
        return (point2.y < point.y ? -1 : 1) * ((float) Math.acos(f / ((float) Math.sqrt((f2 * f2) + (f * f)))));
    }

    public static int a(float f, float f2, float f3, float f4) {
        return (int) Math.sqrt(Math.pow(f - f3, 2.0d) + Math.pow(f2 - f4, 2.0d));
    }

    public static Point a(Point point, Point point2, int i) {
        float fA = a(point, point2);
        return new Point(point.x + ((int) (((double) i) * Math.cos(fA))), ((int) (((double) i) * Math.sin(fA))) + point.y);
    }
}
