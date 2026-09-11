package com.aee.mokacam.drone;

import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

/**
 * Flight-control TCP link (192.168.1.1:8888).
 *
 * <p>Recovered from com.aee.zone.service.ac / ae / af / w: the app keeps a
 * persistent TCP socket to the drone and pushes a {@link DronePacket} frame
 * every 30 ms (this doubles as the heartbeat). If more than 20 consecutive
 * sends fail the link is declared lost.</p>
 *
 * <p>The shipped 1.08 build contains this protocol layer but no drone UI
 * (those activities were stripped from the manifest-built product); the class
 * is provided so the full functionality of the original code base is
 * preserved.</p>
 */
public class DroneLink {

    private static final String TAG = "DroneLink";
    private static final int CONNECT_TIMEOUT_MS = 5000;
    private static final int MAX_SEND_FAILURES = 20;

    public interface FrameListener {
        void onFrame(byte[] data, int len);
    }

    private Socket socket;
    private OutputStream out;
    private InputStream in;
    private volatile boolean running;
    private volatile boolean linkUp;
    private Thread sendThread;
    private Thread recvThread;
    private FrameListener listener;
    private volatile DronePacket lastPacket = new DronePacket(
            DronePacket.NEUTRAL, DronePacket.NEUTRAL, DronePacket.NEUTRAL,
            DronePacket.NEUTRAL, 0);

    public synchronized boolean connect(String host, int port) {
        disconnect();
        try {
            socket = new Socket();
            socket.setTcpNoDelay(true);
            socket.setKeepAlive(true);
            socket.connect(new InetSocketAddress(host, port), CONNECT_TIMEOUT_MS);
            out = socket.getOutputStream();
            in = socket.getInputStream();
            running = true;
            linkUp = true;
            startSendLoop();
            startRecvLoop();
            Log.i(TAG, "flight-control link up");
            return true;
        } catch (Exception e) {
            Log.w(TAG, "connect failed: " + e.getMessage());
            linkUp = false;
            disconnect();
            return false;
        }
    }

    public synchronized void disconnect() {
        running = false;
        linkUp = false;
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
        } catch (Exception ignored) {
        }
    }

    public boolean isLinkUp() {
        return linkUp;
    }

    public void setFrameListener(FrameListener l) {
        this.listener = l;
    }

    /** Update the stick/flag values that the send loop streams. */
    public void setPacket(DronePacket packet) {
        if (packet != null) {
            this.lastPacket = packet;
        }
    }

    /** Fire-and-forget single frame. */
    public synchronized boolean sendNow(byte[] frame) {
        if (!linkUp || out == null) {
            return false;
        }
        try {
            out.write(frame);
            out.flush();
            return true;
        } catch (Exception e) {
            linkUp = false;
            return false;
        }
    }

    private void startSendLoop() {
        sendThread = new Thread(() -> {
            int failures = 0;
            while (running) {
                if (sendNow(lastPacket.toBytes())) {
                    failures = 0;
                } else {
                    failures++;
                    if (failures > MAX_SEND_FAILURES) {
                        linkUp = false;
                        Log.w(TAG, "flight-control link lost");
                        break;
                    }
                }
                try {
                    Thread.sleep(DronePacket.SEND_INTERVAL_MS);
                } catch (InterruptedException e) {
                    break;
                }
            }
            linkUp = false;
        }, "drone-send");
        sendThread.setDaemon(true);
        sendThread.start();
    }

    private void startRecvLoop() {
        recvThread = new Thread(() -> {
            byte[] buf = new byte[512];
            while (running) {
                try {
                    if (in == null) {
                        break;
                    }
                    int n = in.read(buf);
                    if (n > 0 && listener != null) {
                        listener.onFrame(Arrays.copyOf(buf, n), n);
                    } else if (n < 0) {
                        linkUp = false;
                        break;
                    }
                } catch (Exception e) {
                    if (running) {
                        linkUp = false;
                    }
                    break;
                }
            }
        }, "drone-recv");
        recvThread.setDaemon(true);
        recvThread.start();
    }
}
