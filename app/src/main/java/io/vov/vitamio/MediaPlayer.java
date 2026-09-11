package io.vov.vitamio;

import android.content.Context;
import android.media.AudioManager;
import android.view.SurfaceHolder;

/**
 * Vitamio-API compatible wrapper around the framework MediaPlayer, matching
 * the exact method surface used by ShowPicOrVideoActivity and ct.java.
 */
public class MediaPlayer {

    public static final int MEDIA_ERROR_UNKNOWN = 1;
    public static final int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = -1010;
    public static final int MEDIA_INFO_BUFFERING_START = 701;
    public static final int MEDIA_INFO_BUFFERING_END = 702;
    public static final int MEDIA_INFO_AUDIO_TRACK_INIT = 704;

    public interface OnBufferingUpdateListener {
        void onBufferingUpdate(MediaPlayer mp, int percent);
    }

    public interface OnCompletionListener {
        void onCompletion(MediaPlayer mp);
    }

    public interface OnPreparedListener {
        void onPrepared(MediaPlayer mp);
    }

    public interface OnVideoSizeChangedListener {
        void onVideoSizeChanged(MediaPlayer mp, int width, int height);
    }

    public interface OnInfoListener {
        boolean onInfo(MediaPlayer mp, int what, int extra);
    }

    private final android.media.MediaPlayer mp;

    public MediaPlayer(Context context) {
        mp = new android.media.MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void setDataSource(String path) {
        try {
            mp.setDataSource(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setDisplay(SurfaceHolder holder) {
        mp.setDisplay(holder);
    }

    public void prepareAsync() {
        try {
            mp.prepareAsync();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        mp.start();
    }

    public void pause() {
        mp.pause();
    }

    public void stop() {
        mp.stop();
    }

    public void release() {
        mp.release();
    }

    public void releaseDisplay() {
        mp.setSurface(null);
    }

    public boolean isPlaying() {
        return mp.isPlaying();
    }

    public int getCurrentPosition() {
        return mp.getCurrentPosition();
    }

    public int getDuration() {
        return mp.getDuration();
    }

    public int getVideoWidth() {
        return mp.getVideoWidth();
    }

    public int getVideoHeight() {
        return mp.getVideoHeight();
    }

    public void seekTo(int msec) {
        mp.seekTo(msec);
    }

    /** Vitamio-specific audio init hooks; no-ops on the framework player. */
    public int audioTrackInit() {
        return 0;
    }

    public void audioInitedOk(int value) {
    }

    public void setOnBufferingUpdateListener(OnBufferingUpdateListener l) {
        mp.setOnBufferingUpdateListener((imp, percent) -> l.onBufferingUpdate(this, percent));
    }

    public void setOnCompletionListener(OnCompletionListener l) {
        mp.setOnCompletionListener(imp -> l.onCompletion(this));
    }

    public void setOnPreparedListener(OnPreparedListener l) {
        mp.setOnPreparedListener(imp -> l.onPrepared(this));
    }

    public void setOnVideoSizeChangedListener(OnVideoSizeChangedListener l) {
        mp.setOnVideoSizeChangedListener((imp, w, h) -> l.onVideoSizeChanged(this, w, h));
    }

    public void setOnInfoListener(OnInfoListener l) {
        mp.setOnInfoListener((imp, what, extra) -> l.onInfo(this, what, extra));
    }
}
