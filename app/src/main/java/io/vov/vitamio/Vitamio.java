package io.vov.vitamio;

import android.content.Context;

/**
 * Compatibility shim for the subset of Vitamio used by the original app.
 * The framework MediaPlayer covers the same playback surface on modern
 * Android, so no native Vitamio bundle is required.
 */
public final class Vitamio {

    private Vitamio() {
    }

    public static boolean isInitialized(Context ctx) {
        return true;
    }

    public static boolean initialize(Context ctx) {
        return true;
    }
}
