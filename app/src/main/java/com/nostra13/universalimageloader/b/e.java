package com.nostra13.universalimageloader.b;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class e {
    private static volatile boolean a = false;
    private static volatile boolean b = true;

    private static void a(int i, Throwable th, String str, Object... objArr) {
        if (b) {
            String message = objArr.length > 0 ? String.format(str, objArr) : str;
            if (th != null) {
                if (message == null) {
                    message = th.getMessage();
                }
                message = String.format("%1$s\n%2$s", message, Log.getStackTraceString(th));
            }
            Log.println(i, com.nostra13.universalimageloader.core.g.a, message);
        }
    }

    public static void a(String str, Object... objArr) {
        if (a) {
            a(3, null, str, objArr);
        }
    }

    public static void a(Throwable th) {
        a(6, th, null, new Object[0]);
    }

    public static void a(boolean z) {
        a = z;
    }

    public static void b(String str, Object... objArr) {
        a(4, null, str, objArr);
    }

    public static void c(String str, Object... objArr) {
        a(5, null, str, objArr);
    }

    public static void d(String str, Object... objArr) {
        a(6, null, str, objArr);
    }
}
