package com.aee.mokacam.ui.camera;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.OptIn;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.rtsp.RtspMediaSource;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.ui.PlayerView;

import com.aee.mokacam.MokacamApp;
import com.aee.mokacam.R;
import com.aee.mokacam.camera.CameraClient;
import com.aee.mokacam.constants.AeeConstants;
import com.aee.mokacam.ui.BaseActivity;
import com.aee.mokacam.ui.library.SelectLibraryActivity;
import com.aee.mokacam.util.ToastUtils;

import java.util.Locale;

/**
 * Live camera screen. Replacement of com.aee.zone.activity.AeeCameraActivty:
 *
 * <ul>
 *   <li>RTSP live preview. The original shipped a closed-source JNI player
 *       (libAeePlayer.so) bound to a GLSurfaceView; this rewrite uses
 *       Media3's RTSP module instead.</li>
 *   <li>Video mode: record start/stop (msg 513/514) with a duration timer.</li>
 *   <li>Photo mode: single / burst / delay capture (msg 769 with delayed
 *       response handling), mode switch (msg 2 "Switch_mode").</li>
 *   <li>Entries to the library and camera settings.</li>
 * </ul>
 */
public class CameraLiveActivity extends BaseActivity implements View.OnClickListener {

    private static final int MODE_VIDEO = 0;
    private static final int MODE_PHOTO = 1;

    /** Photo shot modes, matching AeeApplication.aT in the original. */
    private static final int SHOT_SINGLE = 0;
    private static final int SHOT_BURST = 1;
    private static final int SHOT_DELAY = 2;

    private PlayerView playerHost;
    private ExoPlayer player;
    private TextView tvVideoTime;
    private ImageView btnOperate;
    private ImageView btnPhotoMode;
    private ImageView btnVideoMode;
    private View recordDot;
    private View photoModePopup;

    private int mode = MODE_VIDEO;
    private int shotMode = SHOT_SINGLE;
    private boolean recording;
    private boolean capturing;
    private long recordSeconds;
    private boolean appInForeground = true;

    private final Handler ui = new Handler(Looper.getMainLooper());
    private final Runnable timerTick = new Runnable() {
        @Override
        public void run() {
            if (recording && appInForeground) {
                recordSeconds++;
                tvVideoTime.setText(formatTime(recordSeconds));
                ui.postDelayed(this, 1000L);
            }
        }
    };

    @OptIn(markerClass = UnstableApi.class)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_camera_live);
        bindViews();
        startPreview();
    }

    private void bindViews() {
        playerHost = find(R.id.rtsp_surface);
        tvVideoTime = find(R.id.tv_video_time);
        btnOperate = find(R.id.iv_operate);
        btnPhotoMode = find(R.id.iv_photo_mode);
        btnVideoMode = find(R.id.iv_video_mode);
        recordDot = find(R.id.record_point);
        photoModePopup = find(R.id.photo_mode_window);

        btnOperate.setOnClickListener(this);
        btnPhotoMode.setOnClickListener(this);
        btnVideoMode.setOnClickListener(this);
        photoModePopup.findViewById(R.id.tv_signal_shot).setOnClickListener(this);
        photoModePopup.findViewById(R.id.tv_brust_mode).setOnClickListener(this);
        photoModePopup.findViewById(R.id.tv_delay_mode).setOnClickListener(this);
        find(R.id.iv_cam_cack).setOnClickListener(this);
        find(R.id.iv_cam_home).setOnClickListener(this);
        find(R.id.iv_libary).setOnClickListener(this);
        find(R.id.iv_camera_setting).setOnClickListener(this);
    }

    @OptIn(markerClass = UnstableApi.class)
    private void startPreview() {
        player = new ExoPlayer.Builder(this).build();
        playerHost.setPlayer(player);
        MediaSource source = new RtspMediaSource.Factory()
                .setTimeoutMs(8000)
                .createMediaSource(MediaItem.fromUri(AeeConstants.CAMERA_RTSP_URL));
        player.setMediaSource(source);
        player.prepare();
        player.setPlayWhenReady(true);
        player.addListener(new Player.Listener() {
            @Override
            public void onPlayerError(androidx.media3.PlaybackException error) {
                runOnUiThread(() ->
                        ToastUtils.show(CameraLiveActivity.this, R.string.no_rtsp));
            }
        });
    }

    private void stopPreview() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_cam_cack) {
            finish();
        } else if (id == R.id.iv_cam_home) {
            MokacamApp.get().finishAllButMain();
        } else if (id == R.id.iv_libary) {
            openLibrary();
        } else if (id == R.id.iv_camera_setting) {
            openSettings();
        } else if (id == R.id.iv_video_mode) {
            switchToVideo();
        } else if (id == R.id.iv_photo_mode) {
            switchToPhoto();
        } else if (id == R.id.tv_signal_shot) {
            setShotMode(SHOT_SINGLE);
        } else if (id == R.id.tv_brust_mode) {
            setShotMode(SHOT_BURST);
        } else if (id == R.id.tv_delay_mode) {
            setShotMode(SHOT_DELAY);
        } else if (id == R.id.iv_operate) {
            onOperate();
        }
    }

    private void openLibrary() {
        if (capturing) {
            ToastUtils.show(this, R.string.please_stop_continous);
            return;
        }
        startActivity(new Intent(this, SelectLibraryActivity.class));
    }

    private void openSettings() {
        if (recording) {
            ToastUtils.show(this, R.string.please_stop_record);
            return;
        }
        if (capturing) {
            ToastUtils.show(this, R.string.please_stop_continous);
            return;
        }
        Intent i = new Intent(this, CameraSettingsActivity.class);
        i.putExtra("currentMode", mode);
        startActivity(i);
    }

    private void switchToVideo() {
        mode = MODE_VIDEO;
        execAsync(() -> CameraClient.get().switchMode());
        btnVideoMode.setImageResource(R.drawable.btn_record_h);
        btnOperate.setImageResource(R.drawable.btn_video_mode);
        tvVideoTime.setVisibility(View.VISIBLE);
        recordDot.setVisibility(View.INVISIBLE);
        photoModePopup.setVisibility(View.GONE);
    }

    private void switchToPhoto() {
        mode = MODE_PHOTO;
        execAsync(() -> CameraClient.get().switchMode());
        btnOperate.setImageResource(R.drawable.btn_operate);
        btnVideoMode.setImageResource(R.drawable.btn_record_n);
        tvVideoTime.setVisibility(View.GONE);
        photoModePopup.setVisibility(photoModePopup.getVisibility() == View.VISIBLE
                ? View.GONE : View.VISIBLE);
    }

    private void setShotMode(int shot) {
        shotMode = shot;
        photoModePopup.setVisibility(View.GONE);
    }

    private void onOperate() {
        if (mode == MODE_PHOTO) {
            if (capturing) {
                return;
            }
            capturing = true;
            btnOperate.setImageResource(R.drawable.operate_pressed);
            execAsync(() -> {
                boolean ok = CameraClient.get().capturePhoto();
                ui.post(() -> {
                    capturing = false;
                    btnOperate.setImageResource(R.drawable.btn_operate);
                    if (ok) {
                        ToastUtils.show(this, R.string.take_photo_success);
                    } else {
                        ToastUtils.show(this, R.string.take_photo_failed_sd_full);
                    }
                });
            });
        } else {
            toggleRecord();
        }
    }

    private void toggleRecord() {
        if (capturing) {
            return;
        }
        recording = !recording;
        execAsync(() -> {
            boolean ok = recording
                    ? CameraClient.get().startRecord()
                    : CameraClient.get().stopRecord();
            if (!ok) {
                ui.post(() -> {
                    recording = false;
                    recordDot.setVisibility(View.INVISIBLE);
                    ToastUtils.show(this, R.string.set_failed);
                });
            }
        });
        if (recording) {
            recordSeconds = 0;
            tvVideoTime.setText(formatTime(0));
            recordDot.setVisibility(View.VISIBLE);
            ui.postDelayed(timerTick, 1000L);
        } else {
            recordDot.setVisibility(View.INVISIBLE);
            ui.removeCallbacks(timerTick);
            recordSeconds = 0;
            tvVideoTime.setText("00:00:00");
        }
    }

    private static String formatTime(long seconds) {
        long h = seconds / 3600;
        long m = (seconds % 3600) / 60;
        long s = seconds % 60;
        return String.format(Locale.US, "%02d:%02d:%02d", h, m, s);
    }

    private void execAsync(final CameraJob job) {
        new Thread(() -> job.run(), "camera-cmd").start();
    }

    private interface CameraJob {
        void run();
    }

    @Override
    protected void onResume() {
        super.onResume();
        appInForeground = true;
    }

    @Override
    protected void onPause() {
        appInForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // The original app stopped a running recording when leaving.
        if (recording) {
            execAsync(() -> CameraClient.get().stopRecord());
        }
        ui.removeCallbacks(timerTick);
        stopPreview();
        super.onDestroy();
    }
}
