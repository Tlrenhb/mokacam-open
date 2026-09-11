package com.aee.mokacam.drone;

/**
 * One control/telemetry frame of the AEE flight controller link.
 *
 * <p>Frame format recovered from com.aee.zone.bean.i and the heartbeat thread
 * com.aee.zone.service.af:</p>
 * <pre>
 *   byte 0      0xCC          frame header
 *   byte 1..4   channels      0x80 = neutral stick (4 channels: throttle,
 *                             roll, pitch, yaw)
 *   byte 5      flags         bitmask of aux switches / actions
 *   byte 6      crc           XOR of bytes 1..5
 *   byte 7      0x33          frame tail
 * </pre>
 * <p>The heartbeat/control thread sends this frame every 30 ms while the
 * flight-control link (TCP 192.168.1.1:8888) is up.</p>
 */
public class DronePacket {

    public static final byte HEADER = (byte) 0xCC;
    public static final byte TAIL = 0x33;
    public static final int LENGTH = 8;
    /** Stick neutral value. */
    public static final int NEUTRAL = 0x80;
    /** Interval of the control/heartbeat thread, ms. */
    public static final int SEND_INTERVAL_MS = 30;

    /** Neutral heartbeat frame of the original app. */
    public static final byte[] IDLE = {(byte) 0xCC, (byte) 0x80, (byte) 0x80,
            (byte) 0x80, (byte) 0x80, 0x00, 0x00, TAIL};

    private final int throttle;
    private final int roll;
    private final int pitch;
    private final int yaw;
    private final int flags;

    public DronePacket(int throttle, int roll, int pitch, int yaw, int flags) {
        this.throttle = clamp(throttle);
        this.roll = clamp(roll);
        this.pitch = clamp(pitch);
        this.yaw = clamp(yaw);
        this.flags = flags & 0xFF;
    }

    private static int clamp(int v) {
        return v < 0 ? 0 : (Math.min(v, 255));
    }

    /** CRC = XOR over channel bytes and flags. */
    public int crc() {
        return (roll ^ pitch ^ throttle ^ yaw ^ flags) & 0xFF;
    }

    public byte[] toBytes() {
        byte[] f = new byte[LENGTH];
        f[0] = HEADER;
        f[1] = (byte) roll;
        f[2] = (byte) pitch;
        f[3] = (byte) throttle;
        f[4] = (byte) yaw;
        f[5] = (byte) flags;
        f[6] = (byte) crc();
        f[7] = TAIL;
        return f;
    }

    @Override
    public String toString() {
        return String.format("DronePacket{thr=%d roll=%d pitch=%d yaw=%d flags=0x%02X crc=0x%02X}",
                throttle, roll, pitch, yaw, flags, crc());
    }
}
