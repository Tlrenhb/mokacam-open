package com.aee.mokacam.util;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Crash logger + debug log helper.
 *
 * <p>Replacement of the original com.aee.zone.utils.p (crash handler that
 * wrote "Nilox错误日志Log" files) and com.aee.zone.utils.m (debug logging).</p>
 */
public final class LogUtils {

    private static final String TAG = "Mokacam";
    private static final String LOG_DIR = "Mokacam/log";
    private static boolean debug = Log.isLoggable(TAG, Log.DEBUG);

    private LogUtils() {
    }

    public static void d(String msg) {
        if (debug) {
            Log.d(TAG, msg);
        }
    }

    public static void w(String msg) {
        Log.w(TAG, msg);
    }

    public static void installCrashHandler(Context context) {
        final File dir = new File(context.getExternalFilesDir(null), LOG_DIR);
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            try {
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String name = new SimpleDateFormat("yy-MM-dd-hh-mm-ss", Locale.US)
                        .format(new Date()) + ".log";
                FileWriter fw = new FileWriter(new File(dir, name), true);
                fw.write(Log.getStackTraceString(throwable));
                fw.close();
            } catch (Exception ignored) {
            }
            // Chain to the default handler so the system dialog still works.
            android.os.Process.killProcess(android.os.Process.myPid());
        });
    }

    public static long logDirSize(Context context) {
        return FileUtils.dirSize(new File(context.getExternalFilesDir(null), LOG_DIR));
    }

    public static void clearLogs(Context context) {
        FileUtils.deleteRecursive(new File(context.getExternalFilesDir(null), LOG_DIR));
    }
}
