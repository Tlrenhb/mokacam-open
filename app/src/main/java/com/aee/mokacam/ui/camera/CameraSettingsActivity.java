package com.aee.mokacam.ui.camera;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aee.mokacam.R;
import com.aee.mokacam.camera.CameraClient;
import com.aee.mokacam.camera.FirmwareUploader;
import com.aee.mokacam.ui.BaseActivity;
import com.aee.mokacam.util.ToastUtils;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Camera settings screen. Replacement of
 * com.aee.zone.activity.AeeCameraSettingActivity (and its A10 sibling):
 *
 * <ul>
 *   <li>video resolution / photo size / shot mode / continuous interval,</li>
 *   <li>stamps, loop record, beep, status LED, TV mode, language,</li>
 *   <li>clock sync, format SD card, factory reset,</li>
 *   <li>WiFi SSID / password change,</li>
 *   <li>firmware update (msg 1286 + 8787 upload).</li>
 * </ul>
 */
public class CameraSettingsActivity extends BaseActivity {

    private static final String[] VIDEO_RESOLUTIONS = {
            "1920x1080 60P 16:9", "1920x1080 30P 16:9", "1280x720 60P 16:9",
            "1280x720 30P 16:9", "1280x720 120P 16:9"
    };
    private static final String[] PHOTO_SIZES = {
            "12M(4000x3000 4:3)", "8M(3840x2160 16:9)", "5M(2592x1944 4:3)"
    };
    private static final String[] SHOT_MODES = {"1张/秒", "3张/秒", "5张/秒"};
    private static final String[] ON_OFF = {"off", "on"};
    private static final String[] LANGUAGES = {"简体中文", "English"};

    private Spinner spVideoRes;
    private Spinner spPhotoSize;
    private Spinner spShotMode;
    private Spinner spBeep;
    private Spinner spLed;
    private Spinner spTvMode;
    private Spinner spLanguage;
    private EditText etSsid;
    private EditText etPassword;
    private TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_settings);
        bindViews();
        loadCurrentSettings();
    }

    private void bindViews() {
        spVideoRes = find(R.id.sp_video_resolution);
        spPhotoSize = find(R.id.sp_photo_size);
        spShotMode = find(R.id.sp_photo_shot_mode);
        spBeep = find(R.id.sp_beep);
        spLed = find(R.id.sp_status_led);
        spTvMode = find(R.id.sp_tv_mode);
        spLanguage = find(R.id.sp_language);
        etSsid = find(R.id.et_wifi_ssid);
        etPassword = find(R.id.et_wifi_password);
        tvVersion = find(R.id.tv_dv_version);
        Button btnFormat = find(R.id.btn_format);
        Button btnReset = find(R.id.btn_factory_reset);
        Button btnClock = find(R.id.btn_clock);
        Button btnFirmware = find(R.id.btn_firmware);
        Button btnApply = find(R.id.btn_apply);
        Button btnBack = find(R.id.btn_back);

        initSpinner(spVideoRes, VIDEO_RESOLUTIONS);
        initSpinner(spPhotoSize, PHOTO_SIZES);
        initSpinner(spShotMode, SHOT_MODES);
        initSpinner(spBeep, ON_OFF);
        initSpinner(spLed, ON_OFF);
        initSpinner(spTvMode, new String[]{"NTSC", "PAL"});
        initSpinner(spLanguage, LANGUAGES);

        btnFormat.setOnClickListener(v -> confirmThen(getString(R.string.sure_format), this::doFormat));
        btnReset.setOnClickListener(v -> confirmThen(getString(R.string.sure_nesignation), this::doFactoryReset));
        btnClock.setOnClickListener(v -> execAsync(() -> CameraClient.get().syncCameraClock()));
        btnFirmware.setOnClickListener(v -> confirmThen(
                getString(R.string.appsetting_firmwareupdate) + "?", this::doFirmwareUpdate));
        btnApply.setOnClickListener(v -> applyAll());
        btnBack.setOnClickListener(v -> finish());
    }

    private void initSpinner(Spinner sp, String[] items) {
        sp.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items));
    }

    private void loadCurrentSettings() {
        execAsync(() -> {
            final String video = CameraClient.get().getSetting("video_resolution");
            final String photo = CameraClient.get().getSetting("photo_size");
            runOnUiThread(() -> {
                if (video != null) {
                    tvVersion.setText(getString(R.string.dv_version) + " " + video);
                }
            });
        });
    }

    private void applyAll() {
        execAsync(() -> {
            CameraClient c = CameraClient.get();
            c.setSetting("video_resolution", spinnerSelection(spVideoRes, VIDEO_RESOLUTIONS));
            c.setSetting("photo_size", spinnerSelection(spPhotoSize, PHOTO_SIZES));
            c.setSetting("Beep", spinnerSelection(spBeep, ON_OFF));
            c.setSetting("Status_LED", spinnerSelection(spLed, ON_OFF));
            c.setSetting("TV_Mode", spinnerSelection(spTvMode, new String[]{"NTSC", "PAL"}));
            c.setSetting("Language", spinnerSelection(spLanguage, LANGUAGES));
            runOnUiThread(() -> ToastUtils.show(this, R.string.set_success));
        });
    }

    private static String spinnerSelection(Spinner sp, String[] values) {
        int pos = sp.getSelectedItemPosition();
        return (pos >= 0 && pos < values.length) ? values[pos] : values[0];
    }

    private void doFormat() {
        execAsync(() -> {
            boolean ok = CameraClient.get().formatSdCard();
            runOnUiThread(() -> ToastUtils.show(this,
                    ok ? R.string.set_success : R.string.set_failed));
        });
    }

    private void doFactoryReset() {
        execAsync(() -> {
            boolean ok = CameraClient.get().factoryReset();
            runOnUiThread(() -> ToastUtils.show(this,
                    ok ? R.string.set_success : R.string.set_failed));
        });
    }

    private void doFirmwareUpdate() {
        final AtomicReference<String> path = new AtomicReference<>(
                getExternalFilesDir(null) + "/Mokacam/fwupdate/firmware.bin");
        execAsync(() -> {
            boolean ok = FirmwareUploader.uploadFirmware(path.get(), new FirmwareUploader.ProgressListener() {
                @Override
                public void onProgress(int percent) {
                }

                @Override
                public void onDone(boolean success, String message) {
                    runOnUiThread(() -> ToastUtils.show(CameraSettingsActivity.this,
                            success ? R.string.set_success : R.string.set_failed));
                }
            });
            if (!ok) {
                runOnUiThread(() -> ToastUtils.show(this, R.string.set_failed));
            }
        });
    }

    private void confirmThen(String message, Runnable action) {
        new android.app.AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(R.string.sure, (d, w) -> action.run())
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    private void execAsync(final CameraJob job) {
        new Thread(() -> job.run(), "camera-setting").start();
    }

    private interface CameraJob {
        void run();
    }
}
