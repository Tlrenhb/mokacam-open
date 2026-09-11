package com.aee.mokacam.drone;

import java.util.BitSet;

/**
 * Flight state decoded from the 32-bit status word delivered on the
 * flight-control link (recovered from AeeApplication.a(long) of the original
 * app).
 *
 * <p>Bits 21..28 carry the armed/takeoff/landing/RTL/guided/circle/simple/
 * super-simple action flags; bit 29 = GPS positioning, bit 30 = optical-flow /
 * visual positioning, bit 31 = normal (barometer only).</p>
 */
public class DroneState {

    public enum PositionMode { GPS, VISUAL, NORMAL }

    /** Status words from the original STATUS_WORD enum. */
    public enum StatusWord {
        SELF_DIAGNOSTICS, IMU_HEALTHY, BARO_HEALTHY, INTELLIGENT_BATTERY,
        BARO_EKF_ALT_DISPARITY, SYSTEM_PREHEAT, IMU_CALIBRATION,
        IMU_CALIBRATION_SUCCESS, IMU_CALIBRATION_REQUIRED, IMU_INCONSISTENT,
        COMPASS_HEALTHY, COMPASS_CALIBRATED, COMPASS_CALIBRATING,
        COMPASS_OFFSETS, COMPASS_FIELD, COMPASS_INCONSISTENT,
        LOW_BATTERY_WARNING, CRITICAL_BATTERY_WARNING, RC_LOST_AND_RTL,
        GPS_HEALTHY, FLOW_HEALTHY, ARMED, TAKEOFF, LANDING, RTL, GUIDED,
        CIRCLE, SIMPLE, SUPER_SIMPLE, SAFE_FLIGHT_WITH_GPS,
        SAFE_FLIGHT_WITH_FLOW, HALF_SAFE_FLIGHT
    }

    private final PositionMode positionMode;
    private final boolean[] actionFlags = new boolean[8];

    public DroneState(long statusWord) {
        BitSet bits = toBits(statusWord);
        if (bits.get(29)) {
            positionMode = PositionMode.GPS;
        } else if (bits.get(30)) {
            positionMode = PositionMode.VISUAL;
        } else {
            positionMode = PositionMode.NORMAL;
        }
        for (int i = 0; i < 8; i++) {
            actionFlags[i] = bits.get(i + 21);
        }
    }

    private static BitSet toBits(long v) {
        BitSet b = new BitSet(32);
        for (int i = 0; i < 32; i++) {
            if (((v >>> i) & 1L) == 1L) {
                b.set(i);
            }
        }
        return b;
    }

    public PositionMode getPositionMode() {
        return positionMode;
    }

    /** Flag of the 8 action bits (21..28 of the status word). */
    public boolean getActionFlag(int index) {
        return index >= 0 && index < 8 && actionFlags[index];
    }

    public boolean isArmed() {
        return getActionFlag(1);
    }

    public boolean isTakeingOff() {
        return getActionFlag(2);
    }

    public boolean isLanding() {
        return getActionFlag(3);
    }

    public boolean isRtl() {
        return getActionFlag(4);
    }

    @Override
    public String toString() {
        return "DroneState{mode=" + positionMode + ", flags=" + java.util.Arrays.toString(actionFlags) + "}";
    }
}
