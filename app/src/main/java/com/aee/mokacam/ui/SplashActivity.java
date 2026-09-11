package com.aee.mokacam.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.aee.mokacam.R;
import com.aee.mokacam.util.WifiUtils;

/**
 * Splash screen. Replacement of com.aee.zone.activity.SplashActivity +
 * dc: brief branding pause, then straight to MainActivity.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (isFinishing()) {
                return;
            }
            if (!WifiUtils.isWifiEnabled(this)) {
                // Match the original: it did not auto-enable WiFi, the main
                // screen shows "please enable WiFi" instead.
                startActivity(new Intent(this, MainActivity.class));
            } else {
                startActivity(new Intent(this, MainActivity.class));
            }
            finish();
        }, 1200L);
    }
}
