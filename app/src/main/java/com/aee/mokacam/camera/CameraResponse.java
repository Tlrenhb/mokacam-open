package com.aee.mokacam.camera;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Incoming camera protocol message.
 *
 * <p>Typical response:</p>
 * <pre>
 *   {"msg_id": 257, "rval": 0, "param": 823561242, "type": "token"}
 *   {"msg_id": 1283, "rval": 0, "listing": [{"IMG_0001.JPG": "a 121234 2017-01-01 10:00:00"}]}
 * </pre>
 *
 * <p>Replacement of the original com.aee.zone.bean.ReceiveMsg and
 * com.aee.zone.utils.ResolveJson.</p>
 */
public class CameraResponse {

    private int msgId = -1;
    private int rval = Integer.MIN_VALUE;
    private String param;
    private String type;
    private String options;
    private String permission;
    private String md5sum;
    private String listingRaw;

    public static CameraResponse parse(String json) {
        CameraResponse r = new CameraResponse();
        if (json == null) {
            return r;
        }
        // The camera may emit multiple concatenated JSON objects; use the last
        // complete one (the original implementation did the same by splitting).
        String usable = json;
        int last = usable.lastIndexOf('{');
        if (last > 0 && usable.indexOf('{') != last) {
            usable = usable.substring(last);
        }
        try {
            JSONObject o = new JSONObject(usable);
            r.msgId = o.optInt("msg_id", -1);
            r.rval = o.optInt("rval", Integer.MIN_VALUE);
            r.param = o.has("param") ? o.get("param").toString() : null;
            r.type = o.optString("type", null);
            r.options = o.optString("options", null);
            r.permission = o.optString("permission", null);
            r.md5sum = o.optString("md5sum", null);
            if (o.has("listing")) {
                Object l = o.get("listing");
                r.listingRaw = l instanceof JSONArray ? l.toString() : l.toString();
            }
        } catch (JSONException ignored) {
            // Incomplete/invalid chunk - caller re-reads until timeout.
        }
        return r;
    }

    /** True when this response has a valid rval and matches the request id. */
    public boolean ok(int expectedMsgId) {
        return rval == 0 && msgId == expectedMsgId;
    }

    public boolean hasRval() {
        return rval != Integer.MIN_VALUE;
    }

    public int getMsgId() {
        return msgId;
    }

    public int getRval() {
        return rval;
    }

    public String getParam() {
        return param;
    }

    public String getType() {
        return type;
    }

    public String getOptions() {
        return options;
    }

    public String getPermission() {
        return permission;
    }

    public String getMd5sum() {
        return md5sum;
    }

    public String getListingRaw() {
        return listingRaw;
    }

    @Override
    public String toString() {
        return "CameraResponse{msg_id=" + msgId + ", rval=" + rval
                + ", param=" + param + ", type=" + type + "}";
    }
}
