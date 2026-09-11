package com.android.gl2jni;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.OptIn;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.rtsp.RtspMediaSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.ui.PlayerView;

/**
 * The original class was a GLSurfaceView driven by the closed-source
 * libAeePlayer.so JNI player. This rewrite keeps the same class name and
 * lifecycle surface (onPause/onResume) but hosts a Media3 PlayerView
 * rendering rtsp://192.168.42.1/live.
 */
@OptIn(markerClass = UnstableApi.class)
public class a extends FrameLayout {

    private static final String RTSP_URL = "rtsp://192.168.42.1/live";

    private ExoPlayer player;

    public a(Context context) {
        super(context);
        init();
    }

    public a(Context context, Activity activity) {
        super(context);
        init();
    }

    public a(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        PlayerView view = new PlayerView(getContext());
        view.setUseController(false);
        addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        player = new ExoPlayer.Builder(getContext()).build();
        view.setPlayer(player);
        MediaSource source = new RtspMediaSource.Factory()
                .setTimeoutMs(8000)
                .createMediaSource(MediaItem.fromUri(RTSP_URL));
        player.setMediaSource(source);
        player.prepare();
        player.setPlayWhenReady(true);
    }


    public void onPause() {
        if (player != null) {
            player.pause();
        }
    }


    public void onResume() {
        if (player != null) {
            player.play();
        }
    }

    public void release() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
