package com.aee.mokacam.ui.library;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.OptIn;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import com.aee.mokacam.R;
import com.aee.mokacam.download.DownloadManager;
import com.aee.mokacam.ui.BaseActivity;
import com.aee.mokacam.util.ToastUtils;

import java.io.File;

/**
 * Photo / video viewer.
 *
 * <p>Replacement of com.aee.zone.activity.ShowPicOrVideoActivity: photos use
 * a pinch-zoom view, videos play through Media3 (the original shipped
 * Vitamio). A download button stores the camera file into the local album.</p>
 */
public class ShowMediaActivity extends BaseActivity {

    public static final String EXTRA_URL = "url";
    public static final String EXTRA_IS_VIDEO = "is_video";
    public static final String EXTRA_NAME = "name";

    private boolean isVideo;
    private String url;
    private String name;
    private ExoPlayer player;

    @OptIn(markerClass = UnstableApi.class)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_media);
        url = getIntent().getStringExtra(EXTRA_URL);
        isVideo = getIntent().getBooleanExtra(EXTRA_IS_VIDEO, false);
        name = getIntent().getStringExtra(EXTRA_NAME);

        View photoView = findViewById(R.id.pinch_image);
        PlayerView videoView = findViewById(R.id.video_player);
        TextView tvName = findViewById(R.id.tv_media_name);
        if (name != null) {
            tvName.setText(name);
        }

        if (isVideo) {
            photoView.setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);
            player = new ExoPlayer.Builder(this).build();
            videoView.setPlayer(player);
            player.setMediaItem(MediaItem.fromUri(Uri.parse(url)));
            player.prepare();
            player.setPlayWhenReady(true);
        } else {
            videoView.setVisibility(View.GONE);
            photoView.setVisibility(View.VISIBLE);
            com.aee.mokacam.view.PinchImageView pinch = findViewById(R.id.pinch_image);
            new Thread(() -> {
                android.graphics.Bitmap bmp = loadBitmap(url);
                runOnUiThread(() -> {
                    if (bmp != null) {
                        pinch.setImageBitmap(bmp);
                    } else {
                        ToastUtils.show(this, R.string.network_err);
                    }
                });
            }, "media-load").start();
        }

        findViewById(R.id.btn_media_back).setOnClickListener(v -> finish());
        findViewById(R.id.btn_media_download).setOnClickListener(v -> download());
    }

    private android.graphics.Bitmap loadBitmap(String target) {
        try {
            java.net.URL u = new java.net.URL(target);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) u.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(15000);
            java.io.InputStream in = conn.getInputStream();
            android.graphics.Bitmap bmp = android.graphics.BitmapFactory.decodeStream(in);
            in.close();
            conn.disconnect();
            return bmp;
        } catch (Exception e) {
            return null;
        }
    }

    private void download() {
        if (url == null) {
            return;
        }
        File destDir = new File(getExternalFilesDir(null), "DCIM/Mokacam");
        String dest = new File(destDir,
                name == null ? ("download_" + System.currentTimeMillis()) : name)
                .getAbsolutePath();
        boolean queued = DownloadManager.get().enqueue(url, dest);
        ToastUtils.show(this, queued
                ? R.string.singlestartDownlad : R.string.singleisdownloadedwarning);
    }

    @Override
    protected void onDestroy() {
        if (player != null) {
            player.release();
            player = null;
        }
        super.onDestroy();
    }
}
