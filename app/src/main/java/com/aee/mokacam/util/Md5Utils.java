package com.aee.mokacam.util;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * MD5 helpers. Replacement of the original com.aee.zone.utils.o / a.a(File).
 */
public final class Md5Utils {

    private Md5Utils() {
    }

    public static byte[] digest(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(data);
        } catch (Exception e) {
            return new byte[0];
        }
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(Character.forDigit((b >> 4) & 0xF, 16));
            sb.append(Character.forDigit(b & 0xF, 16));
        }
        return sb.toString();
    }

    public static String md5(String text) {
        return bytesToHex(digest(text.getBytes()));
    }

    public static String fileMd5(File file) {
        if (file == null || !file.exists()) {
            return "";
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            InputStream in = new FileInputStream(file);
            byte[] buf = new byte[8192];
            int n;
            while ((n = in.read(buf)) > 0) {
                md.update(buf, 0, n);
            }
            in.close();
            return bytesToHex(md.digest());
        } catch (Exception e) {
            Log.w("Md5Utils", "fileMd5 failed: " + e.getMessage());
            return "";
        }
    }
}
