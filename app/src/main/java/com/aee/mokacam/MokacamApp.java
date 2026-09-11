package com.aee.mokacam;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.aee.mokacam.download.DownloadManager;
import com.aee.mokacam.util.LogUtils;
import com.aee.mokacam.util.WifiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Application state holder.
 *
 * <p>Replacement of the original com.aee.zone.AeeApplication. The original
 * class carried ~100 mutable fields for camera/drone runtime state; this
 * rewrite keeps the connection state that the UI actually uses and moves
 * protocol state into CameraSession / DroneLink.</p>
 */
public class MokacamApp extends Application {

    private static MokacamApp instance;

    /** True after a successful token handshake with the camera. */
    public volatile boolean cameraConnected;
    /** SSID of the WiFi network the phone is currently on. */
    public volatile String currentSsid = "";
    /** Track activities for the "finish all" behaviour of the original app. */
    private final List<Activity> activities = new ArrayList<>();

    public static MokacamApp get() {
        return instance;
    }

    public static Context app() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LogUtils.installCrashHandler(this);
        DownloadManager.get().init(this);
    }

    public synchronized void registerActivity(Activity a) {
        activities.add(a);
    }

    public synchronized void unregisterActivity(Activity a) {
        activities.remove(a);
    }

    /** Finish every activity except MainActivity (original AeeApplication.d()). */
    public synchronized void finishAllButMain() {
        for (Activity a : activities) {
            if (a != null && !a.getClass().getSimpleName().equals("MainActivity")) {
                a.finish();
            }
        }
    }

    /** True when the locale is Chinese (original AeeApplication.c()). */
    public boolean isChineseLocale() {
        return getResources().getConfiguration().locale.getLanguage().endsWith("zh");
    }

    /** Camera SSID as seen by the original app. */
    public boolean onCameraNetwork() {
        return WifiUtils.onCameraNetwork(this);
    }
}
