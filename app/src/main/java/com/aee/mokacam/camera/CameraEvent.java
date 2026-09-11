package com.aee.mokacam.camera;

/**
 * Asynchronous notification pushed by the camera over the command channel.
 */
public class CameraEvent {

    public static final int TYPE_RECORD_STATUS = 1;
    public static final int TYPE_SD_EVENT = 2;
    public static final int TYPE_CAPTURE_DONE = 3;
    public static final int TYPE_EXTENDED = 4;

    public final int type;
    public final CameraResponse response;

    public CameraEvent(int type, CameraResponse response) {
        this.type = type;
        this.response = response;
    }
}
