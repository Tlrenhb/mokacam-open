package com.nostra13.universalimageloader.b;

import android.content.Context;
import android.os.Environment;
import com.iflytek.cloud.SpeechEvent;
import java.io.File;
import java.io.IOException;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public final class h {
    public static File a(Context context) {
        return a(context, true);
    }

    public static File a(Context context, String str) {
        File fileA = a(context);
        File file = new File(fileA, str);
        return (file.exists() || file.mkdir()) ? file : fileA;
    }

    public static File a(Context context, boolean z) {
        String externalStorageState;
        File cacheDir = null;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (IncompatibleClassChangeError e) {
            externalStorageState = BuildConfig.FLAVOR;
        } catch (NullPointerException e2) {
            externalStorageState = BuildConfig.FLAVOR;
        }
        if (z && "mounted".equals(externalStorageState) && d(context)) {
            cacheDir = c(context);
        }
        if (cacheDir == null) {
            cacheDir = context.getCacheDir();
        }
        if (cacheDir != null) {
            return cacheDir;
        }
        String str = "/data/data/" + context.getPackageName() + "/cache/";
        e.c("Can't define system cache directory! '%s' will be used.", str);
        return new File(str);
    }

    public static File b(Context context) {
        return a(context, "uil-images");
    }

    private static File c(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), SpeechEvent.KEY_EVENT_RECORD_DATA), context.getPackageName()), "cache");
        if (file.exists()) {
            return file;
        }
        if (!file.mkdirs()) {
            e.c("Unable to create external cache directory", new Object[0]);
            return null;
        }
        try {
            new File(file, ".nomedia").createNewFile();
            return file;
        } catch (IOException e) {
            e.b("Can't create \".nomedia\" file in application external cache directory", new Object[0]);
            return file;
        }
    }

    private static boolean d(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
}
