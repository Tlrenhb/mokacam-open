package com.aee.mokacam.ui.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aee.mokacam.MokacamApp;
import com.aee.mokacam.R;
import com.aee.mokacam.camera.CameraClient;
import com.aee.mokacam.ui.BaseActivity;
import com.aee.mokacam.ui.camera.WifiConfigActivity;
import com.aee.mokacam.util.FileUtils;
import com.aee.mokacam.util.LogUtils;
import com.aee.mokacam.util.ToastUtils;
import com.aee.mokacam.update.AppUpdateChecker;

import java.io.File;

/**
 * App settings. Replacement of com.aee.zone.activity.AeeAppSettingActivity:
 * clear app cache, offline map cache, WiFi password change, firmware update,
 * app version, self update check.
 */
public class AppSettingsActivity extends BaseActivity {

    private TextView tvVersion;
    private TextView tvCacheSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);
        tvVersion = find(R.id.tv_version);
        tvCacheSize = find(R.id.tv_appcachesize);

        tvVersion.setText(getString(R.string.version) + " "
                + AppUpdateChecker.currentVersionCode(this));
        refreshCacheSize();

        find(R.id.iv_settingback).setOnClickListener(v -> finish());
        find(R.id.clear_appcache).setOnClickListener(v -> clearCache());
        find(R.id.rl_changewifi).setOnClickListener(v ->
                startActivity(new android.content.Intent(this, WifiPswChangeActivity.class)));
        find(R.id.rl_downloadmap).setOnClickListener(v ->
                ToastUtils.show(this, R.string.coming_soon));
        find(R.id.rl_firmware_update).setOnClickListener(v -> confirmFirmwareUpdate());
        find(R.id.rl_version).setOnClickListener(v -> checkUpdate());
    }

    private void refreshCacheSize() {
        long size = FileUtils.dirSize(getCacheDir())
                + FileUtils.dirSize(getExternalCacheDir())
                + LogUtils.logDirSize(this);
        tvCacheSize.setText(FileUtils.humanSize(size));
    }

    private void clearCache() {
        FileUtils.deleteRecursive(getCacheDir());
        FileUtils.deleteRecursive(getExternalCacheDir());
        LogUtils.clearLogs(this);
        refreshCacheSize();
        ToastUtils.show(this, R.string.clearcache_finish);
    }

    private void confirmFirmwareUpdate() {
        new android.app.AlertDialog.Builder(this)
                .setMessage(getString(R.string.appsetting_firmwareupdate) + "?")
                .setPositiveButton(R.string.sure, (d, w) -> runFirmwareUpdate())
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    private void runFirmwareUpdate() {
        if (!MokacamApp.get().cameraConnected) {
            ToastUtils.show(this, R.string.connect_wifi);
            return;
        }
        File fw = new File(getExternalFilesDir(null), "Mokacam/fwupdate/firmware.bin");
        if (!fw.exists()) {
            ToastUtils.show(this, R.string.no_sdCard);
            return;
        }
        ToastUtils.show(this, R.string.load_loading);
        new Thread(() -> {
            boolean ok = com.aee.mokacam.camera.FirmwareUploader.uploadFirmware(
                    fw.getAbsolutePath(), null);
            runOnUiThread(() -> ToastUtils.show(this,
                    ok ? R.string.set_success : R.string.set_failed));
        }, "fw-update").start();
    }

    private void checkUpdate() {
        ToastUtils.show(this, R.string.soft_updating_check);
        AppUpdateChecker.checkAsync(this, info -> runOnUiThread(() -> {
            if (info != null && info.newerThanCurrent) {
                ToastUtils.show(this, R.string.version_update_info);
            } else {
                ToastUtils.show(this, R.string.network_err1);
            }
        }));
    }
}
