package com.aee.mokacam.constants;

import com.aee.mokacam.utils.a;

/* JADX INFO: loaded from: classes.dex */
public class AeeConstants {
    public static String a = String.valueOf(a.a()) + "/DCIM/Mokacam";
    public static String b = String.valueOf(a.a()) + "/DCIM/NiloxCameraS91";
    public static String c = String.valueOf(a.a()) + "/Mokacam/temp";
    public static String d = String.valueOf(a.a()) + "/Mokacam";
    public static String e = String.valueOf(a.a()) + "/Mokacam/updateApk";
    public static String f = String.valueOf(a.a()) + "/Mokacam/fwupdate";
    public static String g = "rtsp://192.168.42.1/live";
    public static String h = "rtsp://192.168.3.60:8554/mk10";
    public static int i = 9;
    public static int j = 259;
    public static int k = 1281;
    public static int l = 1283;
    public static int m = 1282;
    public static int n = 4;
    public static int o = 258;
    public static String p = String.valueOf(a.a()) + "/Nilox";
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

    public enum DroneAction {
        INIT,
        ARMED,
        TAKEOFF,
        LANDING,
        RTL,
        GUIDED,
        CIRCLE,
        SIMPLE,
        SUPER_SIMPLE;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static DroneAction[] valuesCustom() {
            DroneAction[] droneActionArrValuesCustom = values();
            int length = droneActionArrValuesCustom.length;
            DroneAction[] droneActionArr = new DroneAction[length];
            System.arraycopy(droneActionArrValuesCustom, 0, droneActionArr, 0, length);
            return droneActionArr;
        }
    }

    public enum DroneState {
        GPS,
        VISVAL,
        NORMAL;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static DroneState[] valuesCustom() {
            DroneState[] droneStateArrValuesCustom = values();
            int length = droneStateArrValuesCustom.length;
            DroneState[] droneStateArr = new DroneState[length];
            System.arraycopy(droneStateArrValuesCustom, 0, droneStateArr, 0, length);
            return droneStateArr;
        }
    }

    public enum STATUS_WORD {
        SELF_DIAGNOSTICE,
        IMU_HEALTHY,
        BARO_HELTHY,
        INTELLIGNECE_BATTERY,
        BARO_EKF_ALT_DISPARITY,
        SYSTEM_PREHEAT,
        IMU_CALIBRATION,
        IMU_CALIBRATION_SUCC,
        IMU_CALIBRATION_REQURIED,
        IMU_INCONSISTENT,
        COMPASS_HEALTHY,
        COMPASS_CALI,
        COMPASS_CALIBRATION,
        COMPASS_OFFSETS,
        COMPASS_FIELD,
        COMPASS_INCONSISTENT,
        LOW_BATTERY_WARNING,
        CRITICAL_BATTERY_WARNING,
        RC_LOST_AND_RTL,
        GPS_HEALTHY1,
        FLOW_HEALTHY,
        STATUS_ARMED,
        STATUS_TAKEOFF,
        STATUS_LANDING,
        STATUS_RTL,
        STATUS_GUIDED,
        STATUS_CIRCLE,
        STATUS_SIMPLE,
        STATUS_SUPER_SIMPLE,
        SAFE_FLIGHT_WITH_GPS,
        SAFE_FLIGHT_WITH_FLOW,
        HALF_SAFE_FLIGHT;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static STATUS_WORD[] valuesCustom() {
            STATUS_WORD[] status_wordArrValuesCustom = values();
            int length = status_wordArrValuesCustom.length;
            STATUS_WORD[] status_wordArr = new STATUS_WORD[length];
            System.arraycopy(status_wordArrValuesCustom, 0, status_wordArr, 0, length);
            return status_wordArr;
        }
    }
}
