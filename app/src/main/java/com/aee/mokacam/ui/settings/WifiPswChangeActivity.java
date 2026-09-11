package com.aee.mokacam.ui.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.aee.mokacam.MokacamApp;
import com.aee.mokacam.R;
import com.aee.mokacam.camera.CameraClient;
import com.aee.mokacam.ui.BaseActivity;
import com.aee.mokacam.util.ToastUtils;

/**
 * Camera WiFi SSID / password change. Replacement of
 * com.aee.zone.activity.WifiPswChangeActivity + WifiPswChangeView:
 * msg 2 with type "wifi_ssid" / "wifi_password".
 */
public class WifiPswChangeActivity extends BaseActivity {

    private EditText etSsid;
    private EditText etPassword;
    private EditText etConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_psw_change);
        etSsid = find(R.id.et_new_ssid);
        etPassword = find(R.id.et_new_password);
        etConfirm = find(R.id.et_confirm_password);
        find(R.id.btn_wifi_save).setOnClickListener(v -> save());
        find(R.id.iv_wifipsw_back).setOnClickListener(v -> finish());
    }

    private void save() {
        String ssid = etSsid.getText().toString().trim();
        String pwd = etPassword.getText().toString();
        String confirm = etConfirm.getText().toString();
        if (!ssid.isEmpty() && !pwd.isEmpty() && pwd.length() < 8) {
            ToastUtils.show(this, R.string.password_is_too_short);
            return;
        }
        if (!pwd.isEmpty() && !pwd.equals(confirm)) {
            ToastUtils.show(this, R.string.password_is_not_consistent);
            return;
        }
        if (ssid.isEmpty() && pwd.isEmpty()) {
            ToastUtils.show(this, R.string.new_ssid_is_empty);
            return;
        }
        final String fSsid = ssid;
        final String fPwd = pwd;
        new Thread(() -> {
            CameraClient c = CameraClient.get();
            boolean ok = true;
            if (!fSsid.isEmpty()) {
                ok &= c.setWifiSsid(fSsid);
            }
            if (!fPwd.isEmpty()) {
                ok &= c.setWifiPassword(fPwd);
            }
            final boolean success = ok;
            runOnUiThread(() -> {
                if (success) {
                    ToastUtils.show(WifiPswChangeActivity.this, R.string.wifi_restart);
                    MokacamApp.get().cameraConnected = false;
                } else {
                    ToastUtils.show(WifiPswChangeActivity.this, R.string.wifi_setting_fail);
                }
            });
        }, "wifi-change").start();
    }
}
