package com.aee.mokacam.bean;

import com.aee.mokacam.AeeApplication;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class SendMsg {
    public int NO;
    public String msgName;
    public int msg_id;
    public String param;
    public int token;
    public String type;

    public SendMsg() {
    }

    public SendMsg(int i, int i2, String str, String str2) {
        this.msg_id = i;
        this.token = i2;
        this.param = str;
        this.type = str2;
    }

    public SendMsg(int i, String str, String str2) {
        this.msg_id = i;
        this.param = str;
        this.type = str2;
    }

    public SendMsg(String str, int i) {
        this.msgName = str;
        this.msg_id = i;
    }

    public SendMsg(String str, int i, int i2, String str2, String str3) {
        this.msgName = str;
        this.msg_id = i;
        this.token = i2;
        this.param = str2;
        this.type = str3;
    }

    public SendMsg(String str, int i, String str2, String str3, int i2) {
        this.msgName = str;
        this.msg_id = i;
        this.param = str2;
        this.type = str3;
        this.NO = i2;
    }

    public String getMsgName() {
        return this.msgName;
    }

    public int getMsg_id() {
        return this.msg_id;
    }

    public String getParam() {
        return this.param;
    }

    public int getToken() {
        return this.token;
    }

    public String getType() {
        return this.type;
    }

    public void setMsgName(String str) {
        this.msgName = str;
    }

    public void setMsg_id(int i) {
        this.msg_id = i;
    }

    public void setParam(String str) {
        this.param = str;
    }

    public void setToken(int i) {
        this.token = i;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("msg_id", this.msg_id);
            jSONObject.put("token", AeeApplication.a().e);
            if (this.param != null) {
                jSONObject.put("param", this.param);
            }
            if (this.type != null) {
                jSONObject.put("type", this.type);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public String toSpecialJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("msg_id", this.msg_id);
            jSONObject.put("token", AeeApplication.a().e);
            if (this.param != null) {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(this.param);
                jSONObject.put("param", jSONArray);
            }
            if (this.type != null) {
                jSONObject.put("type", this.type);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public String toString() {
        return "SendMsg [msgName=" + this.msgName + ", msg_id=" + this.msg_id + ", token=" + this.token + ", param=" + this.param + ", type=" + this.type + "]";
    }
}
