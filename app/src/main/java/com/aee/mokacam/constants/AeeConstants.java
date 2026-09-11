package com.aee.mokacam.constants;

/**
 * Global constants of the Mokacam companion app.
 *
 * <p>All values were recovered from the original APK (AEE Mokacam 1.08,
 * package com.aee.mokacam) by static analysis of
 * com.aee.zone.constants.AeeConstants and the surrounding service layer.</p>
 */
public final class AeeConstants {

    private AeeConstants() {
    }

    // ---------------------------------------------------------------------
    // Camera device endpoints
    // ---------------------------------------------------------------------

    /** Camera AP gateway address. */
    public static final String CAMERA_HOST = "192.168.42.1";

    /** TCP port used for the JSON command channel. */
    public static final int CAMERA_CMD_PORT = 7878;

    /** TCP port used for file upload (put_file) channel. */
    public static final int CAMERA_UPLOAD_PORT = 8787;

    /** RTSP live preview stream of the camera. */
    public static final String CAMERA_RTSP_URL = "rtsp://192.168.42.1/live";

    /** Secondary stream used by another camera model (kept for reference). */
    public static final String RTSP_MK10 = "rtsp://192.168.3.60:8554/mk10";

    /** HTTP base for browsing camera SD card files (Moka mount). */
    public static final String HTTP_SD_MOKA = "http://192.168.42.1/SD/moka/";

    /** HTTP base for browsing camera SD card files (DCIM 100MEDIA). */
    public static final String HTTP_DCIM_100 = "http://192.168.42.1/DCIM/100MEDIA/";

    /** HTTP base for browsing camera SD card files (DCIM 101MEDIA). */
    public static final String HTTP_DCIM_101 = "http://192.168.42.1/DCIM/101MEDIA/";

    /** Absolute path of the photo folder on the camera file system. */
    public static final String CAM_PATH_MOKA = "/tmp/SD0/moka/";

    /** Absolute path of the DCIM/100MEDIA folder on the camera. */
    public static final String CAM_PATH_100MEDIA = "/tmp/fuse_d/DCIM/100MEDIA/";

    /** Absolute path of the DCIM/101MEDIA folder on the camera. */
    public static final String CAM_PATH_101MEDIA = "/tmp/fuse_d/DCIM/101MEDIA/";

    // ---------------------------------------------------------------------
    // Drone endpoints (flight controller link; UI was stripped from the
    // shipped build but the protocol code is still present in the APK).
    // ---------------------------------------------------------------------

    /** Drone TCP command gateway. */
    public static final String DRONE_HOST = "192.168.1.1";

    /** Drone TCP command port. */
    public static final int DRONE_PORT = 8888;

    // ---------------------------------------------------------------------
    // Camera JSON protocol message ids (msg_id field)
    // ---------------------------------------------------------------------

    /** Query all runtime status of the camera, type "app_status". */
    public static final int MSG_GET_ALL_INFO = 1;

    /** Generic "set parameter" command, param = "value", type = key. */
    public static final int MSG_SET_PARAM = 2;

    /** Generic "query parameter" command. */
    public static final int MSG_QUERY_PARAM = 3;

    /** Unknown command observed with param "C". */
    public static final int MSG_CMD_C = 4;

    /** Query free space of the SD card, type "free". */
    public static final int MSG_GET_SD_FREE = 5;

    /** Format the SD card. */
    public static final int MSG_FORMAT_SD = 8;

    /** Get / set a camera setting, param = key (get) or param = value, type = key (set). */
    public static final int MSG_SETTING = 9;

    /** Query battery, type "video_bat". */
    public static final int MSG_GET_BATTERY = 13;

    /** Obtain a session token; param of the response is the token (int). */
    public static final int MSG_GET_TOKEN = 257;

    /** Send a file to the camera over the TCP channel, type "TCP". */
    public static final int MSG_SEND_FILE = 261;

    /** Cancel current capture. */
    public static final int MSG_CANCEL_CAPTURE = 260;

    /** Start video recording (no extra param). */
    public static final int MSG_RECORD_START = 513;

    /** Stop video recording. */
    public static final int MSG_RECORD_STOP = 514;

    /** Query the record duration, type "video_time". */
    public static final int MSG_GET_RECORD_TIME = 515;

    /** Take a photo; param "none_force" or "force". */
    public static final int MSG_CAPTURE = 769;

    /** Delete a file; param = absolute camera path. */
    public static final int MSG_DELETE_FILE = 1281;

    /** Single-file operation; param = absolute camera path. */
    public static final int MSG_FILE_ACTION = 1282;

    /** List a directory; param = absolute camera path, response carries "listing". */
    public static final int MSG_LIST_DIR = 1283;

    /** Upload a firmware file; extra fields: offset / size / md5sum. */
    public static final int MSG_UPLOAD_FIRMWARE = 1286;

    /** Configure the camera WiFi AP; param = "ssid$password". */
    public static final int MSG_WIFI_CONFIG = 2049;

    // ---------------------------------------------------------------------
    // Camera settings keys (msg 9 / msg 2)
    // ---------------------------------------------------------------------

    public static final String KEY_VIDEO_RESOLUTION = "video_resolution";
    public static final String KEY_PHOTO_SIZE = "photo_size";
    public static final String KEY_PHOTO_SHOT_MODE = "photo_shot_mode";
    public static final String KEY_PHOTO_TLM = "photo_tlm";
    public static final String KEY_PHOTO_DELAY = "photo_delay";
    public static final String KEY_PHOTO_STAMP = "photo_stamp";
    public static final String KEY_VIDEO_STAMP = "video_stamp";
    public static final String KEY_LOOP_RECORD = "loop_back";
    public static final String KEY_BEEP = "Beep";
    public static final String KEY_STATUS_LED = "Status_LED";
    public static final String KEY_TV_MODE = "TV_Mode";
    public static final String KEY_LANGUAGE = "Language";
    public static final String KEY_CAMERA_CLOCK = "camera_clock";
    public static final String KEY_WIFI_SSID = "wifi_ssid";
    public static final String KEY_WIFI_PASSWORD = "wifi_password";
    public static final String KEY_SWITCH_MODE = "Switch_mode";
    public static final String KEY_DEFAULT_SETTING = "default_setting";

    // ---------------------------------------------------------------------
    // Async notifications pushed by the camera (type strings)
    // ---------------------------------------------------------------------

    /** Record status changed (msg 769). */
    public static final int NOTIFY_RECORD_STATUS = 769;
    /** Storage / SD card event (msg 513). */
    public static final int NOTIFY_SD_EVENT = 513;
    /** Capture completed event (msg 514). */
    public static final int NOTIFY_CAPTURE_DONE = 514;
    /** Extended camera events (msg 1798 / 1799). */
    public static final int NOTIFY_EVENT_A = 1798;
    public static final int NOTIFY_EVENT_B = 1799;

    // ---------------------------------------------------------------------
    // Protocol result codes carried in "rval"
    // ---------------------------------------------------------------------

    public static final int RVAL_OK = 0;
    public static final int RVAL_SD_FULL = -17;
    public static final int RVAL_NO_SD = -30;

    // ---------------------------------------------------------------------
    // Local storage layout
    // ---------------------------------------------------------------------

    /** Root of the app media folder on the phone SD card. */
    public static final String LOCAL_DCIM = "DCIM/Mokacam";
    /** Temp folder used while downloading from the camera. */
    public static final String LOCAL_TEMP = "Mokacam/temp";
    /** Folder used to store downloaded app update APKs. */
    public static final String LOCAL_UPDATE_APK = "Mokacam/updateApk";
    /** Folder used to stage camera firmware files. */
    public static final String LOCAL_FW_UPDATE = "Mokacam/fwupdate";

    // ---------------------------------------------------------------------
    // App self update
    // ---------------------------------------------------------------------

    /** Version descriptor of the original vendor. */
    public static final String UPDATE_XML_URL =
            "http://www.aee.com/app/android/zone/version_android.xml";

    /** Vendor website. */
    public static final String SHOP_URL = "http://www.aee.com";

    // ---------------------------------------------------------------------
    // Camera WiFi SSID prefixes used for auto-detection
    // ---------------------------------------------------------------------

    public static final String SSID_PREFIX = "192.168.42";
    /** Drone remote-controller SSID fragment. */
    public static final String SSID_DRONE_CONDOR = "AEE_CONDOR";
    /** Drone RC link SSID fragment. */
    public static final String SSID_DRONE_RC = "AEE_RC_CON";
}
