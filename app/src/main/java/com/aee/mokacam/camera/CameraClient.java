package com.aee.mokacam.camera;

import android.util.Log;

import com.aee.mokacam.constants.AeeConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * High level camera API. Each method maps to one protocol command.
 *
 * <p>Replacement of the original com.aee.zone.service.a facade and its
 * anonymous worker classes (service/b..x).</p>
 */
public class CameraClient {

    private static final String TAG = "CameraClient";
    private static volatile CameraClient sInstance;

    private CameraClient() {
    }

    public static CameraClient get() {
        if (sInstance == null) {
            synchronized (CameraClient.class) {
                if (sInstance == null) {
                    sInstance = new CameraClient();
                }
            }
        }
        return sInstance;
    }

    private CameraSession session() {
        return CameraSession.get();
    }

    // ------------------------------------------------------------------
    // Status
    // ------------------------------------------------------------------

    /** Query the whole app status; returns e.g. "record" / "vf" / "idle". */
    public String getAppStatus() {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_GET_ALL_INFO, 0, null, "app_status"), 3000);
        if (r != null && r.ok(AeeConstants.MSG_GET_ALL_INFO) && "app_status".equals(r.getType())) {
            return r.getParam();
        }
        return null;
    }

    /** Query remaining SD card space, type "free". */
    public String getSdFreeSpace() {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_GET_SD_FREE, 0, null, "free"), 3000);
        return r != null ? r.getParam() : null;
    }

    /** Query battery, type "video_bat". */
    public String getBattery() {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_GET_BATTERY, 0, null, "video_bat"), 3000);
        return r != null ? r.getParam() : null;
    }

    /** Query current record duration, type "video_time". */
    public String getRecordTime() {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_GET_RECORD_TIME, 0, null, "video_time"), 3000);
        return r != null ? r.getParam() : null;
    }

    // ------------------------------------------------------------------
    // Settings (msg 9)
    // ------------------------------------------------------------------

    /** Read one camera setting by key. */
    public String getSetting(String key) {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_SETTING, 0, key, null), 3000);
        return r != null ? r.getParam() : null;
    }

    /** Write one camera setting (msg 2 with type=key). */
    public boolean setSetting(String key, String value) {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_SET_PARAM, 0, value, key), 3000);
        return r != null && r.ok(AeeConstants.MSG_SET_PARAM);
    }

    // ------------------------------------------------------------------
    // Capture
    // ------------------------------------------------------------------

    /** Switch the camera between video/photo mode (msg 2, "Switch_mode"). */
    public boolean switchMode() {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_SET_PARAM, 0, "nil", AeeConstants.KEY_SWITCH_MODE), 3000);
        return r != null && r.ok(AeeConstants.MSG_SET_PARAM);
    }

    /** Take a single photo. */
    public boolean capturePhoto() {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_CAPTURE, session().getToken(), "none_force", null), 5000);
        return r != null && r.getRval() == AeeConstants.RVAL_OK;
    }

    /** Start video recording (msg 513). */
    public boolean startRecord() {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_RECORD_START, 0), 5000);
        return r != null && r.getRval() == AeeConstants.RVAL_OK;
    }

    /** Stop video recording (msg 514). */
    public boolean stopRecord() {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_RECORD_STOP, 0), 5000);
        return r != null && r.getRval() == AeeConstants.RVAL_OK;
    }

    // ------------------------------------------------------------------
    // Maintenance
    // ------------------------------------------------------------------

    /** Sync the camera clock to the phone time (msg 2, "camera_clock"). */
    public boolean syncCameraClock() {
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date());
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_SET_PARAM, 0, now, AeeConstants.KEY_CAMERA_CLOCK), 3000);
        return r != null && r.ok(AeeConstants.MSG_SET_PARAM);
    }

    /** Format the camera SD card (msg 8). */
    public boolean formatSdCard() {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_FORMAT_SD, 0), 5000);
        return r != null && r.getRval() == AeeConstants.RVAL_OK;
    }

    /** Factory reset of the camera (msg 2, "default_setting" = "on"). */
    public boolean factoryReset() {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_SET_PARAM, 0, "on", AeeConstants.KEY_DEFAULT_SETTING), 5000);
        return r != null && r.ok(AeeConstants.MSG_SET_PARAM);
    }

    /** Change the camera WiFi AP name (msg 2, "wifi_ssid"). */
    public boolean setWifiSsid(String ssid) {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_SET_PARAM, 0, ssid, AeeConstants.KEY_WIFI_SSID), 3000);
        return r != null && r.ok(AeeConstants.MSG_SET_PARAM);
    }

    /** Change the camera WiFi password (msg 2, "wifi_password"). */
    public boolean setWifiPassword(String password) {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_SET_PARAM, 0, password, AeeConstants.KEY_WIFI_PASSWORD), 3000);
        return r != null && r.ok(AeeConstants.MSG_SET_PARAM);
    }

    /**
     * Configure the camera WiFi in one step (msg 2049), param = "ssid$password".
     */
    public boolean configWifi(String ssid, String password) {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_WIFI_CONFIG, 0, ssid + "$" + password, null), 3000);
        return r != null && r.ok(AeeConstants.MSG_WIFI_CONFIG);
    }

    // ------------------------------------------------------------------
    // File system
    // ------------------------------------------------------------------

    /**
     * List one camera directory (msg 1283).
     *
     * @param path absolute camera path, e.g. /tmp/SD0/moka/
     * @return file entries parsed from the "listing" array
     */
    public List<CameraFile> listDir(String path) {
        List<CameraFile> out = new ArrayList<>();
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_LIST_DIR, 0, path, null), 5000);
        if (r == null || r.getListingRaw() == null) {
            return out;
        }
        try {
            JSONArray array = new JSONArray(r.getListingRaw());
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                Iterator<String> keys = o.keys();
                while (keys.hasNext()) {
                    String name = keys.next();
                    String attr = o.getString(name);
                    if (name.endsWith("_thm.mp4") || name.endsWith("_thm.MP4")) {
                        continue; // the original app hides stream thumbnails
                    }
                    out.add(new CameraFile(name, attr, path));
                }
            }
        } catch (Exception e) {
            Log.w(TAG, "listDir parse failed: " + e.getMessage());
        }
        return out;
    }

    /** Delete one file on the camera (msg 1281). */
    public boolean deleteFile(String cameraPath) {
        CameraResponse r = session().request(new CameraMessage(
                AeeConstants.MSG_DELETE_FILE, 0, cameraPath, null), 3000);
        return r != null && r.ok(AeeConstants.MSG_DELETE_FILE);
    }

    /** File download URL on the camera HTTP server. */
    public static String downloadUrl(String fileName) {
        return AeeConstants.HTTP_SD_MOKA + fileName;
    }

    /** Thumbnail URL on the camera HTTP server (attrs appended as query blob). */
    public static String thumbnailUrl(String fileName, String attrs) {
        return AeeConstants.HTTP_SD_MOKA + fileName + "@@22@@" + attrs;
    }

    /** Firmware upload (msg 1286 + file push over the command channel). */
    public boolean uploadFirmware(String localPath, FirmwareUploader.ProgressListener l) {
        return FirmwareUploader.uploadFirmware(localPath, l);
    }
}
