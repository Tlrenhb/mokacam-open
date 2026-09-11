package com.aee.mokacam.ui.camera;

import android.os.Bundle;
import android.widget.TextView;

import com.aee.mokacam.R;
import com.aee.mokacam.camera.CameraClient;
import com.aee.mokacam.camera.CameraFile;
import com.aee.mokacam.constants.AeeConstants;
import com.aee.mokacam.ui.BaseActivity;

import java.util.List;

/**
 * Camera playback: triggers the camera-side playback listing (msg 1283 on
 * /tmp/SD0/moka/) and shows the newest clip count.
 *
 * <p>Replacement of com.aee.zone.activity.AeeCameraPlaybackActivity, which
 * chained msg 1283 + msg 1282 against the camera playback engine.</p>
 */
public class CameraPlaybackActivity extends BaseActivity {

    private TextView tvSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_playback);
        tvSummary = find(R.id.tv_playback_summary);
        find(R.id.btn_back).setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(() -> {
            List<CameraFile> files = CameraClient.get().listDir(AeeConstants.CAM_PATH_MOKA);
            runOnUiThread(() -> tvSummary.setText(getString(R.string.camera_lib)
                    + ": " + files.size()));
        }, "playback-list").start();
    }
}
