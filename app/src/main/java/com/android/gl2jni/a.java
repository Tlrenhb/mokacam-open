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
import com.aee.mokacam.constants.AeeConstants;

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
    private PlayerView playerView;
    private boolean started;

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
        playerView = new PlayerView(getContext());
        playerView.setUseController(false);
        addView(playerView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        player = new ExoPlayer.Builder(getContext()).build();
        playerView.setPlayer(player);
        player.addListener(new androidx.media3.common.Player.Listener() {
            @Override
            public void onPlayerError(androidx.media3.common.PlaybackException error) {
                if (!started) return;
                player.release();
                player = new ExoPlayer.Builder(getContext()).build();
                playerView.setPlayer(player);
                MediaSource retry = new RtspMediaSource.Factory()
                        .setForceUseRtpTcp(true)
                        .setTimeoutMs(8000)
                        .createMediaSource(MediaItem.fromUri(AeeConstants.g));
                player.setMediaSource(retry);
                player.prepare();
                player.setPlayWhenReady(true);
            }
        });
        MediaSource source = new RtspMediaSource.Factory()
                .setForceUseRtpTcp(true)
                .setTimeoutMs(8000)
                .createMediaSource(MediaItem.fromUri(AeeConstants.g));
        player.setMediaSource(source);
        player.prepare();
        player.setPlayWhenReady(true);
        started = true;
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
