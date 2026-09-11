package com.aee.mokacam.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class o {
    public static String a(String str) {
        try {
            return b(a(str.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public static String a(String str, char c, int i) {
        char[] cArr = new char[i];
        Arrays.fill(cArr, c);
        System.arraycopy(str.toCharArray(), 0, cArr, cArr.length - str.length(), str.length());
        return new String(cArr);
    }

    public static byte[] a(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(a(Integer.toHexString(b & 255), '0', 2));
        }
        return sb.toString();
    }
}
