package com.aee.mokacam.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

/**
 * WiFi helpers. Replacement of the original com.aee.zone.utils.aa / al and
 * the SSID detection in AeeApplication / MainActivity.
 */
public final class WifiUtils {

    private WifiUtils() {
    }

    public static boolean isWifiEnabled(Context context) {
        WifiManager wm = (WifiManager) context.getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        return wm != null && wm.isWifiEnabled();
    }

    /** Current SSID, without quotes, or null. */
    public static String getSSID(Context context) {
        WifiManager wm = (WifiManager) context.getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        if (wm == null) {
            return null;
        }
        WifiInfo info = wm.getConnectionInfo();
        if (info == null || info.getSSID() == null) {
            return null;
        }
        String ssid = info.getSSID();
        if (ssid.startsWith("\"") && ssid.endsWith("\"") && ssid.length() >= 2) {
            ssid = ssid.substring(1, ssid.length() - 1);
        }
        return ssid;
    }

    /** Dotted IP of the current connection, or "0.0.0.0". */
    public static String getIp(Context context) {
        WifiManager wm = (WifiManager) context.getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        if (wm == null) {
            return "0.0.0.0";
        }
        WifiInfo info = wm.getConnectionInfo();
        int ip = (info == null) ? 0 : info.getIpAddress();
        if (ip <= 0) {
            return "0.0.0.0";
        }
        return (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF)
                + "." + ((ip >> 24) & 0xFF);
    }

    /** True when the phone is on the camera AP network (192.168.42.x). */
    public static boolean onCameraNetwork(Context context) {
        String ip = getIp(context);
        return !TextUtils.isEmpty(ip) && ip.contains("192.168.42");
    }
}
