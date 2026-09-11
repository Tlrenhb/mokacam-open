package com.aee.mokacam.update;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.util.Xml;

import com.aee.mokacam.constants.AeeConstants;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * App self-update checker.
 *
 * <p>The original vendor UpdateManager (com.aee.zone.service.UpdateManager)
 * fetched a small XML descriptor
 * (http://www.aee.com/app/android/zone/version_android.xml) with fields
 * version / name / url and, when the remote version was newer, downloaded the
 * APK to Mokacam/updateApk and installed it. This rewrite keeps the same
 * descriptor format. The vendor server is long dead, so failures are
 * non-fatal; the UI exposes the check as part of "About / version".</p>
 */
public class AppUpdateChecker {

    private static final String TAG = "AppUpdateChecker";

    public static class UpdateInfo {
        public int version;
        public String name;
        public String url;
        public boolean newerThanCurrent;
    }

    public interface Callback {
        void onUpdateInfo(UpdateInfo info);
    }

    public static int currentVersionCode(Context context) {
        try {
            PackageInfo pi = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }

    /** Fetch and parse the vendor descriptor on a worker thread. */
    public static void checkAsync(Context context, Callback cb) {
        final Context app = context.getApplicationContext();
        new Thread(() -> {
            UpdateInfo info = fetch(app);
            if (cb != null && info != null) {
                cb.onUpdateInfo(info);
            }
        }, "app-update-check").start();
    }

    private static UpdateInfo fetch(Context context) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(AeeConstants.UPDATE_XML_URL).openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            if (conn.getResponseCode() != 200) {
                return null;
            }
            HashMap<String, String> map = new HashMap<>();
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(conn.getInputStream(), null);
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                if (event == XmlPullParser.START_TAG && "update".equals(parser.getName())) {
                    map.put(parser.getAttributeValue(null, "key"), parser.nextText());
                }
                event = parser.next();
            }
            UpdateInfo info = new UpdateInfo();
            info.version = Integer.parseInt(map.getOrDefault("version", "0"));
            info.name = map.get("name");
            info.url = map.get("url");
            info.newerThanCurrent = info.version > currentVersionCode(context);
            return info;
        } catch (Exception e) {
            Log.d(TAG, "update check failed (expected, vendor server offline): " + e.getMessage());
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
