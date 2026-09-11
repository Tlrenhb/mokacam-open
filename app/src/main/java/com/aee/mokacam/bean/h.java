package com.aee.mokacam.bean;

import com.aee.mokacam.AeeApplication;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class h extends SendMsg {
    private int a;
    private String b;

    public h(int i, String str, String str2, int i2, String str3) {
        super(i, str, str2);
        this.a = i2;
        this.b = str3;
    }

    @Override // com.aee.mokacam.bean.SendMsg
    public String toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("msg_id", this.msg_id);
            jSONObject.put("token", AeeApplication.a().e);
            if (this.param != null) {
                jSONObject.put("param", this.param);
            }
            jSONObject.put("offset", this.a);
            File file = new File(this.b);
            if (file.exists()) {
                jSONObject.put("size", file.length());
                jSONObject.put("md5sum", com.aee.mokacam.utils.a.a(file));
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }
}
