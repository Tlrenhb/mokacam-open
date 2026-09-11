package com.aee.mokacam.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.util.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class p implements Thread.UncaughtExceptionHandler {
    static p a;

    public static p a() {
        if (a == null) {
            a = new p();
        }
        return a;
    }

    public void a(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    @SuppressLint({"SimpleDateFormat"})
    public void uncaughtException(Thread thread, Throwable th) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            String str = String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()) + "/Nilox错误日志Log";
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                FileWriter fileWriter = new FileWriter(String.valueOf(str) + File.separator + new SimpleDateFormat("[yy-MM-dd hh:mm:ss:SSS]: ").format(new Date()) + "NiloxErrorlog.log", true);
                fileWriter.write(new Date() + "错误原因：\n");
                StackTraceElement[] stackTrace = th.getStackTrace();
                fileWriter.write(String.valueOf(th.getMessage()) + "\n");
                for (int i = 0; i < stackTrace.length; i++) {
                    fileWriter.write("file:" + stackTrace[i].getFileName() + " class:" + stackTrace[i].getClassName() + " method:" + stackTrace[i].getMethodName() + " line:" + stackTrace[i].getLineNumber() + "\n");
                }
                fileWriter.write("\n");
                fileWriter.close();
            } catch (IOException e) {
                Log.e("crash handler", "load file failed...", e.getCause());
            }
        }
        th.printStackTrace();
        Process.killProcess(Process.myPid());
        System.exit(1);
    }
}
