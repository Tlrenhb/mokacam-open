package com.aee.mokacam.camera;

import android.util.Log;

import com.aee.mokacam.constants.AeeConstants;
import com.aee.mokacam.util.Md5Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Uploads a local file to the camera over the dedicated upload channel
 * (192.168.42.1:8787), and packages the firmware update flow (msg 1286).
 *
 * <p>Behaviour recovered from com.aee.zone.service.o.b(String, long) and
 * com.aee.zone.service.a.a(Handler) / service/g:</p>
 * <ol>
 *   <li>send msg 1286 JSON: param = "firmware.bin", offset = 0, size, md5sum,</li>
 *   <li>open a new socket to port 8787 and stream the raw file bytes,</li>
 *   <li>read the reply; the transfer is complete when type
 *       "put_file_complete" is received and the md5 matches,</li>
 *   <li>the camera reboots into the updater (do not power off).</li>
 * </ol>
 *
 * <p>Replacement of the original com.aee.zone.bean.h + service/o.b(String,long).</p>
 */
public final class FirmwareUploader {

    private static final String TAG = "FirmwareUploader";

    public interface ProgressListener {
        void onProgress(int percent);

        void onDone(boolean success, String message);
    }

    private FirmwareUploader() {
    }

    /**
     * Upload an arbitrary file to the camera SD card over port 8787.
     *
     * @param localPath absolute path of the local file
     * @param listener  progress callback (may be null)
     * @return true when the camera acknowledged with put_file_complete + md5
     */
    public static boolean putFile(String localPath, ProgressListener listener) {
        File f = new File(localPath);
        if (!f.exists() || f.length() == 0) {
            notifyDone(listener, false, "file missing or empty");
            return false;
        }
        Socket socket = null;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(AeeConstants.CAMERA_HOST,
                    AeeConstants.CAMERA_UPLOAD_PORT), 5000);
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            Thread.sleep(200L);

            String md5 = Md5Utils.fileMd5(f);
            FileInputStream fin = new FileInputStream(f);
            byte[] buf = new byte[8192];
            long total = f.length();
            long sent = 0;
            int n;
            while ((n = fin.read(buf)) > 0) {
                out.write(buf, 0, n);
                out.flush();
                sent += n;
                if (listener != null) {
                    listener.onProgress((int) (sent * 100 / total));
                }
            }
            fin.close();

            // Wait for the acknowledgement, original app waited up to 100 s.
            long deadline = System.currentTimeMillis() + 100_000L;
            StringBuilder sb = new StringBuilder();
            boolean acked = false;
            while (System.currentTimeMillis() < deadline) {
                int avail = in.available();
                if (avail > 0) {
                    byte[] rb = new byte[avail];
                    int r = in.read(rb, 0, avail);
                    if (r > 0) {
                        sb.append(new String(rb, 0, r, StandardCharsets.UTF_8));
                    }
                }
                String acc = sb.toString();
                if (acc.contains("put_file_complete")) {
                    CameraResponse resp = CameraResponse.parse(acc);
                    acked = resp.getMd5sum() == null || md5 == null
                            || md5.equalsIgnoreCase(resp.getMd5sum());
                    break;
                }
                Thread.sleep(100L);
            }
            notifyDone(listener, acked, acked ? "ok" : "no acknowledgement from camera");
            return acked;
        } catch (Exception e) {
            Log.w(TAG, "putFile failed: " + e.getMessage());
            notifyDone(listener, false, e.getMessage());
            return false;
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

    /**
     * Full firmware update: announce msg 1286, then push the file.
     *
     * @param localPath local firmware.bin
     * @param listener  progress callback (may be null)
     */
    public static boolean uploadFirmware(String localPath, ProgressListener listener) {
        File f = new File(localPath);
        if (!f.exists()) {
            notifyDone(listener, false, "firmware file missing");
            return false;
        }
        CameraMessage msg = new CameraMessage(AeeConstants.MSG_UPLOAD_FIRMWARE, 0, "firmware.bin", null)
                .setOffset(0L)
                .setSize(f.length())
                .setMd5sum(Md5Utils.fileMd5(f));
        CameraResponse r = CameraSession.get().request(msg, 5000);
        if (r == null || r.getRval() < 0) {
            notifyDone(listener, false, "camera rejected firmware upload");
            return false;
        }
        return putFile(localPath, listener);
    }

    private static void notifyDone(ProgressListener l, boolean ok, String msg) {
        if (l != null) {
            l.onDone(ok, msg);
        }
    }
}
