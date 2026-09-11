package com.aee.mokacam.camera;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Outgoing camera protocol message.
 *
 * <p>Wire format (UTF-8 JSON, no framing - messages are streamed back to
 * back on the TCP channel):</p>
 * <pre>
 *   {"msg_id": 257, "token": 0}
 *   {"msg_id": 9,   "token": 123, "param": "video_resolution"}
 *   {"msg_id": 2,   "token": 123, "param": "1080P60", "type": "video_resolution"}
 * </pre>
 *
 * <p>Replacement of the original obfuscated class com.aee.zone.bean.SendMsg.</p>
 */
public class CameraMessage {

    private final int msgId;
    private final int token;
    private final String param;
    private final String type;
    /** Sequence number used by the original firmware logging (NO field). */
    private int no;
    /** Optional firmware upload extension fields (msg 1286). */
    private long offset = -1;
    private long size = -1;
    private String md5sum;

    public CameraMessage(int msgId, int token) {
        this.msgId = msgId;
        this.token = token;
        this.param = null;
        this.type = null;
    }

    public CameraMessage(int msgId, int token, String param) {
        this.msgId = msgId;
        this.token = token;
        this.param = param;
        this.type = null;
    }

    public CameraMessage(int msgId, int token, String param, String type) {
        this.msgId = msgId;
        this.token = token;
        this.param = param;
        this.type = type;
    }

    public CameraMessage setNo(int no) {
        this.no = no;
        return this;
    }

    public CameraMessage setOffset(long offset) {
        this.offset = offset;
        return this;
    }

    public CameraMessage setSize(long size) {
        this.size = size;
        return this;
    }

    public CameraMessage setMd5sum(String md5sum) {
        this.md5sum = md5sum;
        return this;
    }

    public int getMsgId() {
        return msgId;
    }

    public int getToken() {
        return token;
    }

    public String getParam() {
        return param;
    }

    public String getType() {
        return type;
    }

    public String toJson() {
        JSONObject o = new JSONObject();
        try {
            o.put("msg_id", msgId);
            o.put("token", token);
            if (param != null) {
                o.put("param", param);
            }
            if (type != null) {
                o.put("type", type);
            }
            if (offset >= 0) {
                o.put("offset", offset);
            }
            if (size >= 0) {
                o.put("size", size);
            }
            if (md5sum != null) {
                o.put("md5sum", md5sum);
            }
        } catch (JSONException e) {
            return "{}";
        }
        return o.toString();
    }

    @Override
    public String toString() {
        return "CameraMessage{msg_id=" + msgId + ", token=" + token
                + ", param=" + param + ", type=" + type + "}";
    }
}
