package com.aee.mokacam.camera;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.aee.mokacam.constants.AeeConstants;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TCP session with the camera (192.168.42.1:7878).
 *
 * <p>Behaviour recovered from the original classes
 * com.aee.zone.service.o / q / r / u:</p>
 * <ul>
 *   <li>one long-lived socket for commands,</li>
 *   <li>the very first command is msg 257 (get token); the token value is then
 *       attached to every subsequent request,</li>
 *   <li>responses may be split across TCP segments, the reader loops until a
 *       complete JSON object is available or the timeout expires,</li>
 *   <li>the camera also pushes asynchronous notifications (record status 769,
 *       storage event 513, capture done 514, extended events 1798/1799) which
 *       are dispatched to registered listeners.</li>
 * </ul>
 *
 * <p>Replacement of the original com.aee.zone.service.o singleton.</p>
 */
public class CameraSession {

    private static final String TAG = "CameraSession";
    /** Timeout used by the original app for a request/response round trip. */
    public static final long DEFAULT_TIMEOUT_MS = 3000L;

    public interface Listener {
        void onEvent(CameraEvent event);
    }

    private static volatile CameraSession sInstance;

    private final List<Listener> listeners = new CopyOnWriteArrayList<>();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private volatile boolean connected;
    private volatile boolean busy;
    private int token = -1000;
    private int tokenRetries;

    private final StringBuilder notificationBuffer = new StringBuilder();

    public static CameraSession get() {
        if (sInstance == null) {
            synchronized (CameraSession.class) {
                if (sInstance == null) {
                    sInstance = new CameraSession();
                }
            }
        }
        return sInstance;
    }

    private CameraSession() {
    }

    // ------------------------------------------------------------------
    // Connection management
    // ------------------------------------------------------------------

    /** Connect to the command port. Safe to call repeatedly. */
    public synchronized boolean connect() {
        if (connected) {
            return true;
        }
        close();
        try {
            socket = new Socket();
            socket.setKeepAlive(true);
            socket.connect(new InetSocketAddress(AeeConstants.CAMERA_HOST,
                    AeeConstants.CAMERA_CMD_PORT), 5000);
            in = socket.getInputStream();
            out = socket.getOutputStream();
            connected = true;
            token = -1000;
            tokenRetries = 0;
            startNotificationReader();
            Log.i(TAG, "connected to camera command channel");
        } catch (Exception e) {
            Log.w(TAG, "connect failed: " + e.getMessage());
            connected = false;
        }
        return connected;
    }

    public synchronized void close() {
        connected = false;
        try {
            if (in != null) {
                in.close();
                in = null;
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (socket != null) {
                socket.close();
                socket = null;
            }
        } catch (IOException ignored) {
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public int getToken() {
        return token;
    }

    public boolean hasToken() {
        return token != -1000;
    }

    public void addListener(Listener l) {
        if (l != null && !listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void removeListener(Listener l) {
        listeners.remove(l);
    }

    // ------------------------------------------------------------------
    // Request / response
    // ------------------------------------------------------------------

    /**
     * Send a request and wait for the matching response.
     *
     * @param msg     request to send (token is filled in automatically when known)
     * @param timeout max time to wait for a complete response, ms
     */
    public synchronized CameraResponse request(CameraMessage msg, long timeout) {
        if (!connected && !connect()) {
            return null;
        }
        if (!hasToken() && msg.getMsgId() != AeeConstants.MSG_GET_TOKEN) {
            if (!requestToken()) {
                return null;
            }
        }
        if (msg.getToken() == 0 && msg.getMsgId() != AeeConstants.MSG_GET_TOKEN && hasToken()) {
            msg = rewire(msg, token);
        }
        if (busy) {
            return null;
        }
        busy = true;
        try {
            write(msg.toJson());
            return readResponse(msg.getMsgId(), timeout);
        } catch (Exception e) {
            Log.w(TAG, "request failed: " + e.getMessage());
            connected = false;
            return null;
        } finally {
            busy = false;
        }
    }

    private static CameraMessage rewire(CameraMessage src, int newToken) {
        return new CameraMessage(src.getMsgId(), newToken, src.getParam(), src.getType());
    }

    private synchronized boolean write(String json) throws IOException {
        if (out == null) {
            return false;
        }
        out.write(json.getBytes(StandardCharsets.UTF_8));
        out.flush();
        return true;
    }

    /**
     * Reads from the socket until a JSON object with a rval matching the
     * expected msg id arrives, or the deadline expires. Notification objects
     * that arrive in between are re-queued into the notification buffer.
     */
    private CameraResponse readResponse(int expectedMsgId, long timeout) throws IOException {
        long deadline = System.currentTimeMillis() + timeout;
        StringBuilder sb = new StringBuilder();
        while (System.currentTimeMillis() < deadline) {
            if (in == null) {
                break;
            }
            int available = in.available();
            if (available > 0) {
                byte[] buf = new byte[available];
                int read = in.read(buf, 0, available);
                if (read > 0) {
                    sb.append(new String(buf, 0, read, StandardCharsets.UTF_8));
                }
            }
            String acc = sb.toString();
            int start = acc.lastIndexOf('{');
            if (start >= 0) {
                String candidate = acc.substring(start);
                CameraResponse r = CameraResponse.parse(candidate);
                if (r.hasRval()) {
                    if (r.getMsgId() == expectedMsgId) {
                        // Anything before this object was an async notification.
                        if (start > 0) {
                            forwardNotification(acc.substring(0, start));
                        }
                        return r;
                    }
                }
            }
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                break;
            }
        }
        return null;
    }

    /** Request a fresh session token (msg 257). */
    public synchronized boolean requestToken() {
        if (!connected && !connect()) {
            return false;
        }
        busy = true;
        try {
            write(new CameraMessage(AeeConstants.MSG_GET_TOKEN, 0).toJson());
            long timeout = DEFAULT_TIMEOUT_MS;
            while (tokenRetries <= 5) {
                CameraResponse r = readResponse(AeeConstants.MSG_GET_TOKEN, timeout);
                if (r != null && r.ok(AeeConstants.MSG_GET_TOKEN) && r.getParam() != null) {
                    try {
                        token = Integer.parseInt(r.getParam().trim());
                        tokenRetries = 0;
                        return true;
                    } catch (NumberFormatException ignored) {
                    }
                }
                tokenRetries++;
                timeout = 1080L; // original waited ~1s between retries
            }
            return false;
        } catch (Exception e) {
            Log.w(TAG, "token request failed: " + e.getMessage());
            connected = false;
            return false;
        } finally {
            tokenRetries = 0;
            busy = false;
        }
    }

    // ------------------------------------------------------------------
    // Async notification stream
    // ------------------------------------------------------------------

    private void startNotificationReader() {
        Thread t = new Thread(() -> {
            byte[] buf = new byte[4096];
            while (connected) {
                try {
                    if (busy) {
                        // While a synchronous request owns the stream, skip.
                        Thread.sleep(50L);
                        continue;
                    }
                    if (in == null) {
                        break;
                    }
                    int n = in.read(buf);
                    if (n > 0) {
                        notificationBuffer.append(new String(buf, 0, n, StandardCharsets.UTF_8));
                        drainNotifications();
                    } else if (n < 0) {
                        connected = false;
                    }
                } catch (Exception e) {
                    if (connected) {
                        Log.d(TAG, "notify reader: " + e.getMessage());
                    }
                }
            }
        }, "camera-notify-reader");
        t.setDaemon(true);
        t.start();
    }

    private void drainNotifications() {
        String acc = notificationBuffer.toString();
        int open = acc.lastIndexOf('{');
        if (open < 0) {
            notificationBuffer.setLength(0);
            return;
        }
        String complete = acc.substring(0, open);
        notificationBuffer.setLength(0);
        notificationBuffer.append(acc.substring(open));
        forwardNotification(complete);
    }

    private void forwardNotification(String json) {
        if (json == null || json.trim().isEmpty()) {
            return;
        }
        CameraResponse r = CameraResponse.parse(json);
        if (!r.hasRval() || r.getMsgId() < 0) {
            return;
        }
        int code;
        switch (r.getMsgId()) {
            case AeeConstants.NOTIFY_RECORD_STATUS:
                code = CameraEvent.TYPE_RECORD_STATUS;
                break;
            case AeeConstants.NOTIFY_SD_EVENT:
                code = CameraEvent.TYPE_SD_EVENT;
                break;
            case AeeConstants.NOTIFY_CAPTURE_DONE:
                code = CameraEvent.TYPE_CAPTURE_DONE;
                break;
            case AeeConstants.NOTIFY_EVENT_A:
            case AeeConstants.NOTIFY_EVENT_B:
                code = CameraEvent.TYPE_EXTENDED;
                break;
            default:
                return;
        }
        final CameraEvent ev = new CameraEvent(code, r);
        mainHandler.post(() -> {
            for (Listener l : listeners) {
                l.onEvent(ev);
            }
        });
    }
}
