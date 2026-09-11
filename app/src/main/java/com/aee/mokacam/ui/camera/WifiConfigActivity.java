package com.aee.mokacam.ui.camera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.aee.mokacam.MokacamApp;
import com.aee.mokacam.R;
import com.aee.mokacam.camera.CameraClient;
import com.aee.mokacam.ui.BaseActivity;
import com.aee.mokacam.util.ToastUtils;

/**
 * One-shot WiFi configuration of the camera (msg 2049, param "ssid$password").
 * Replacement of com.aee.zone.activity.AeeCameraWifiConfigActivity.
 */
public class WifiConfigActivity extends BaseActivity {

    private EditText etSsid;
    private EditText etPassword;
    private EditText etConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_config);
        etSsid = find(R.id.et_wifi_name);
        etPassword = find(R.id.et_wifi_pwd);
        etConfirm = find(R.id.et_wifi_pwd_confirm);
        find(R.id.btn_save).setOnClickListener(this::onSave);
        find(R.id.btn_back).setOnClickListener(v -> finish());
    }

    private void onSave(View v) {
        String ssid = etSsid.getText().toString().trim();
        String pwd = etPassword.getText().toString();
        String confirm = etConfirm.getText().toString();
        if (ssid.isEmpty()) {
            ToastUtils.show(this, R.string.new_ssid_is_empty);
            return;
        }
        if (pwd.isEmpty()) {
            ToastUtils.show(this, R.string.new_password_is_empty);
            return;
        }
        if (confirm.isEmpty()) {
            ToastUtils.show(this, R.string.confirm_password_is_empty);
            return;
        }
        if (pwd.length() < 8) {
            ToastUtils.show(this, R.string.password_is_too_short);
            return;
        }
        if (!pwd.equals(confirm)) {
            ToastUtils.show(this, R.string.password_is_not_consistent);
            return;
        }
        final String fSsid = ssid;
        final String fPwd = pwd;
        new Thread(() -> {
            boolean ok = CameraClient.get().configWifi(fSsid, fPwd);
            runOnUiThread(() -> {
                if (ok) {
                    ToastUtils.show(WifiConfigActivity.this, R.string.wifi_restart);
                    MokacamApp.get().cameraConnected = false;
                    startActivity(new Intent(WifiConfigActivity.this,
                            com.aee.mokacam.ui.MainActivity.class));
                    finish();
                } else {
                    ToastUtils.show(WifiConfigActivity.this, R.string.wifi_setting_fail);
                }
            });
        }, "wifi-config").start();
    }
}
