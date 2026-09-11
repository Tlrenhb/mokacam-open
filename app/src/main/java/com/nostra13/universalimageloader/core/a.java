package com.nostra13.universalimageloader.core;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.deque.LIFOLinkedBlockingDeque;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class a {
    @TargetApi(11)
    private static int a(ActivityManager activityManager) {
        return activityManager.getLargeMemoryClass();
    }

    public static com.nostra13.universalimageloader.a.a.a a(Context context, com.nostra13.universalimageloader.a.a.b.a aVar, long j, int i) {
        File fileB = b(context);
        if (j > 0 || i > 0) {
            try {
                return new com.nostra13.universalimageloader.a.a.a.a.h(com.nostra13.universalimageloader.b.h.b(context), fileB, aVar, j, i);
            } catch (IOException e) {
                com.nostra13.universalimageloader.b.e.a(e);
            }
        }
        return new com.nostra13.universalimageloader.a.a.a.b(com.nostra13.universalimageloader.b.h.a(context), fileB, aVar);
    }

    public static com.nostra13.universalimageloader.a.b.a a(Context context, int i) {
        if (i == 0) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            i = (((d() && c(context)) ? a(activityManager) : activityManager.getMemoryClass()) * 1048576) / 8;
        }
        return new com.nostra13.universalimageloader.a.b.a.b(i);
    }

    public static com.nostra13.universalimageloader.core.a.d a(boolean z) {
        return new com.nostra13.universalimageloader.core.a.a(z);
    }

    public static ImageDownloader a(Context context) {
        return new com.nostra13.universalimageloader.core.download.a(context);
    }

    public static Executor a() {
        return Executors.newCachedThreadPool(a(5, "uil-pool-d-"));
    }

    public static Executor a(int i, int i2, QueueProcessingType queueProcessingType) {
        return new ThreadPoolExecutor(i, i, 0L, TimeUnit.MILLISECONDS, (BlockingQueue<Runnable>) (queueProcessingType == QueueProcessingType.LIFO ? new LIFOLinkedBlockingDeque() : new LinkedBlockingQueue()), a(i2, "uil-pool-"));
    }

    private static ThreadFactory a(int i, String str) {
        return new b(i, str);
    }

    public static com.nostra13.universalimageloader.a.a.b.a b() {
        return new com.nostra13.universalimageloader.a.a.b.b();
    }

    private static File b(Context context) {
        File fileA = com.nostra13.universalimageloader.b.h.a(context, false);
        File file = new File(fileA, "uil-images");
        return (file.exists() || file.mkdir()) ? file : fileA;
    }

    public static com.nostra13.universalimageloader.core.b.a c() {
        return new com.nostra13.universalimageloader.core.b.b();
    }

    @TargetApi(11)
    private static boolean c(Context context) {
        return (context.getApplicationInfo().flags & 1048576) != 0;
    }

    private static boolean d() {
        return Build.VERSION.SDK_INT >= 11;
    }
}
