package com.aee.mokacam.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.aee.mokacam.R;
import com.aee.mokacam.activity.MainActivity;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static void a(Context context) {
        a(context, 0);
    }

    public static void a(Context context, int i) {
        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
            b(context, i);
        } else if (Build.MANUFACTURER.toLowerCase().contains("sony")) {
            a(context, new StringBuilder(String.valueOf(i)).toString());
        } else {
            c(context, i);
        }
    }

    private static void a(Context context, String str) {
        boolean z = "0".equals(str) ? false : true;
        Intent intent = new Intent();
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", z);
        intent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", b(context));
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", str);
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", context.getPackageName());
        context.sendBroadcast(intent);
    }

    private static String b(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setPackage(context.getPackageName());
        intent.addCategory("android.intent.category.LAUNCHER");
        ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, 65536);
        if (resolveInfoResolveActivity == null) {
            resolveInfoResolveActivity = packageManager.resolveActivity(intent, 0);
        }
        return resolveInfoResolveActivity.activityInfo.name;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00c8 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void b(Context context, int i) throws Throwable {
        Notification notification;
        boolean z;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        Notification notificationBuild = null;
        try {
            try {
                try {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                    builder.setContentTitle(context.getResources().getString(R.string.version_update));
                    builder.setTicker(context.getResources().getString(R.string.version_update_info));
                    builder.setAutoCancel(true);
                    builder.setSmallIcon(R.drawable.ic_launcher);
                    builder.setDefaults(4);
                    notificationBuild = builder.build();
                    notificationBuild.flags |= 16;
                    notificationBuild.contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, (Class<?>) MainActivity.class), 0);
                    Object objNewInstance = Class.forName("android.app.MiuiNotification").newInstance();
                    Field declaredField = objNewInstance.getClass().getDeclaredField("messageCount");
                    declaredField.setAccessible(true);
                    declaredField.set(objNewInstance, Integer.valueOf(i));
                    notificationBuild.getClass().getField("extraNotification").set(notificationBuild, objNewInstance);
                    if (notificationBuild != null) {
                        notificationManager.notify(101010, notificationBuild);
                    }
                } catch (Throwable th) {
                    th = th;
                    notification = null;
                    z = true;
                    if (notification != null && z) {
                        notificationManager.notify(101010, notification);
                    }
                    throw th;
                }
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Intent intent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
                    intent.putExtra("android.intent.extra.update_application_component_name", String.valueOf(context.getPackageName()) + "/" + b(context));
                    intent.putExtra("android.intent.extra.update_application_message_text", i);
                    context.sendBroadcast(intent);
                    if (0 != 0) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    notification = null;
                    z = false;
                    if (notification != null) {
                        notificationManager.notify(101010, notification);
                    }
                    throw th;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            notification = notificationBuild;
            z = true;
            if (notification != null) {
            }
            throw th;
        }
    }

    private static void c(Context context, int i) {
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", i);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", b(context));
        context.sendBroadcast(intent);
    }
}
