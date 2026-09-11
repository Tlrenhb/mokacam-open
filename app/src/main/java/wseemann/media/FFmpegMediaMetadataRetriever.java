package wseemann.media;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

/**
 * Shim over the framework MediaMetadataRetriever for the small subset of the
 * FFmpegMediaMetadataRetriever API used by LibraryActivity (video thumbnails).
 */
public class FFmpegMediaMetadataRetriever {

    public static final int OPTION_CLOSEST_SYNC = MediaMetadataRetriever.OPTION_CLOSEST_SYNC;
    public static final int OPTION_CLOSEST = MediaMetadataRetriever.OPTION_CLOSEST;
    public static final int OPTION_PREVIOUS_SYNC = MediaMetadataRetriever.OPTION_PREVIOUS_SYNC;
    public static final int OPTION_NEXT_SYNC = MediaMetadataRetriever.OPTION_NEXT_SYNC;
    public static final int OPTION_BEST = MediaMetadataRetriever.OPTION_BEST;

    private final MediaMetadataRetriever retriever = new MediaMetadataRetriever();

    public void setDataSource(String path) {
        retriever.setDataSource(path);
    }

    public Bitmap getFrameAtTime(long timeUs, int option) {
        return retriever.getFrameAtTime(timeUs, option);
    }

    public Bitmap getFrameAtTime(long timeUs) {
        return retriever.getFrameAtTime(timeUs);
    }

    public Bitmap getFrameAtTime() {
        return retriever.getFrameAtTime();
    }

    public void release() {
        retriever.release();
    }
}
