package com.aee.mokacam.constants;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class AeeConstants {
    /** Populated from Application.onCreate so scoped storage is resolved with context. */
    public static String a = "";
    public static String b = "";
    public static String c = "";
    public static String d = "";
    public static String e = "";
    public static String f = "";

    public static synchronized void init(Context context) {
        String root = com.aee.mokacam.utils.a.storageRoot(context);
        a = root + "/DCIM/Mokacam";
        b = root + "/DCIM/NiloxCameraS91";
        c = root + "/Mokacam/temp";
        d = root + "/Mokacam";
        e = root + "/Mokacam/updateApk";
        f = root + "/Mokacam/fwupdate";
        p = root + "/Nilox";
    }
    public static String g = "rtsp://192.168.42.1/live";
    public static String h = "rtsp://192.168.3.60:8554/mk10";
    public static int i = 9;
    public static int j = 259;
    public static int k = 1281;
    public static int l = 1283;
    public static int m = 1282;
    public static int n = 4;
    public static int o = 258;
    public static String p = "";
    public static int q = 254;
    public static String r = "/tmp/fuse_d/DCIM/100MEDIA/";
    public static String s = "/tmp/fuse_d/DCIM/101MEDIA/";
    public static String t = "http://192.168.42.1/DCIM/100MEDIA/";
    public static String u = "http://192.168.42.1/DCIM/101MEDIA/";
    public static String v = "http://www.aee.com/app/android/zone/version_android.xml";

    public enum DataAction {
        noaction,
        receive,
        send,
        receive_send,
        sendheart;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static DataAction[] valuesCustom() {
            DataAction[] dataActionArrValuesCustom = values();
            int length = dataActionArrValuesCustom.length;
            DataAction[] dataActionArr = new DataAction[length];
            System.arraycopy(dataActionArrValuesCustom, 0, dataActionArr, 0, length);
            return dataActionArr;
        }
    }

}
