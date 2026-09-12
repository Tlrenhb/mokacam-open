package com.aee.mokacam.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a() {
        if (com.aee.mokacam.AeeApplication.b() != null) {
            return storageRoot(com.aee.mokacam.AeeApplication.b());
        }
        return Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory().toString() : Environment.getDownloadCacheDirectory().toString();
    }

    /** Resolves storage with an explicit context, safe during Application startup. */
    public static String storageRoot(Context context) {
        if (context != null) {
            java.io.File dir = context.getExternalFilesDir(null);
            if (dir != null) {
                return dir.toString();
            }
        }
        return Environment.getExternalStorageState().equals("mounted")
                ? Environment.getExternalStorageDirectory().toString()
                : Environment.getDownloadCacheDirectory().toString();
    }

    public static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(File file) {
        if (file == null || !file.isFile()) {
            return null;
        }
        byte[] bArr = new byte[8192];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                int i;
                while ((i = fileInputStream.read(bArr)) != -1) {
                    messageDigest.update(bArr, 0, i);
                }
            }
            byte[] digest = messageDigest.digest();
            StringBuilder result = new StringBuilder(digest.length * 2);
            for (byte value : digest) {
                result.append(String.format(java.util.Locale.US, "%02x", value & 255));
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, String> a(String str) {
        HashMap map = new HashMap();
        int iIndexOf = str.indexOf("[");
        if (str.endsWith("]") && iIndexOf == 0) {
            for (String str2 : str.replace("[", BuildConfig.FLAVOR).replace("]", BuildConfig.FLAVOR).split(",")) {
                String strReplace = str2.replace("{", BuildConfig.FLAVOR).replace("}", BuildConfig.FLAVOR);
                map.put(strReplace.split("\":")[0].replace("\"", BuildConfig.FLAVOR), strReplace.split("\":")[1].replace("\"", BuildConfig.FLAVOR));
            }
        }
        return map;
    }

    public static String b(String str) {
        return str.contains("3840") ? "4K/" + str.split(" ")[1].replace("P", BuildConfig.FLAVOR) : "1280x720  25P SuperView".equals(str) ? "720P/25" : "1280x720  30P SuperView".equals(str) ? "720P/30" : str.split("x")[1].split("P")[0].replace(" ", "P/");
    }

    public static boolean b(Context context) {
        int i = context.getResources().getConfiguration().orientation;
        if (i == 2) {
            return true;
        }
        return i == 1 ? false : false;
    }

    public static int c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static String[] c(String str) {
        String[] strArr = new String[0];
        return (str.endsWith("]") && str.indexOf("[") == 0) ? str.replace("[", BuildConfig.FLAVOR).replace("]", BuildConfig.FLAVOR).replace("\"", BuildConfig.FLAVOR).split(",") : strArr;
    }
}
