package com.aee.mokacam.bean;

import android.graphics.Bitmap;
import android.view.View;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.utils.t;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import org.xutils.common.Callback;

/* JADX INFO: loaded from: classes.dex */
public class g {
    public String a;
    public String b;
    public long c;
    public long d;
    public Callback.Cancelable e;
    public Bitmap f;
    public boolean g;
    public com.nostra13.universalimageloader.core.imageaware.ImageViewAware h;
    private String i;
    private boolean j;
    private String k;
    private boolean l;
    private View m;
    private boolean n;
    private boolean o;
    private String p;
    private boolean q;
    private int r;

    public g(String str, String str2, String str3) {
        this.n = false;
        this.o = false;
        this.r = 0;
        this.c = 0L;
        this.d = 0L;
        this.g = false;
        this.p = str;
        this.a = str2;
        a(str2);
        this.i = str3;
        a(false);
        c(true);
        this.l = false;
    }

    public g(String str, String str2, boolean z) {
        this.n = false;
        this.o = false;
        this.r = 0;
        this.c = 0L;
        this.d = 0L;
        this.g = false;
        this.a = str;
        this.i = str2;
        a(String.valueOf(AeeApplication.a().h) + str + "@@22@@" + str2);
        a(z);
        c(false);
        d(true);
        b(String.valueOf(str2.replace(" ", "_").replace(":", "_")) + "_" + str);
        this.l = false;
    }

    public g(String str, String str2, boolean z, String str3, int i) {
        this.n = false;
        this.o = false;
        this.r = 0;
        this.c = 0L;
        this.d = 0L;
        this.g = false;
        this.a = str;
        this.i = str2;
        this.j = z;
        this.b = str3;
        this.l = false;
    }

    public static List<g> a(Object obj) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray array = JSON.parseArray(obj.toString());
            int size = array.size();
            for (int i = 0; i < size; i++) {
                JSONObject jSONObject = (JSONObject) array.get(i);
                for (String str : jSONObject.keySet()) {
                    g gVar = new g(str, jSONObject.getString(str), str.endsWith("/"));
                    if (!gVar.a().endsWith("_thm.mp4") && !gVar.a().endsWith("_thm.MP4")) {
                        arrayList.add(gVar);
                    }
                }
            }
            t.a(arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static List<g> a(Object obj, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray array = JSON.parseArray(obj.toString());
            int size = array.size();
            for (int i = 0; i < size; i++) {
                JSONObject jSONObject = (JSONObject) array.get(i);
                for (String str2 : jSONObject.keySet()) {
                    g gVar = new g(str2, jSONObject.getString(str2), str2.endsWith("/"), str, 0);
                    if (!gVar.a().endsWith("_thm.mp4") && !gVar.a().endsWith("_thm.MP4")) {
                        arrayList.add(gVar);
                    }
                }
            }
            t.a(arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static void a(String str, List<g> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            g gVar = list.get(i);
            if (str.equals("/tmp/SD0/moka/" + gVar.b + gVar.a)) {
                list.remove(i);
                return;
            }
        }
    }

    public String a() {
        return this.a;
    }

    public void a(int i) {
        this.r = i;
    }

    public void a(String str) {
        this.k = str;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public String b() {
        return this.i;
    }

    public void b(String str) {
        this.p = str;
    }

    public void b(boolean z) {
        this.l = z;
    }

    public String c() {
        return this.k;
    }

    public void c(boolean z) {
        this.n = z;
    }

    public void d(boolean z) {
        this.o = z;
    }

    public boolean d() {
        return this.l;
    }

    public boolean e() {
        return this.l;
    }

    public String f() {
        return this.p;
    }

    public int g() {
        return this.r;
    }

    public String toString() {
        return "FileAttr [name=" + this.a + ", folderName=" + this.b + ", createTime=" + this.i + ", isFolder=" + this.j + ", uri=" + this.k + ", isSelected=" + this.l + ", selectedView=" + this.m + ", isLocalFile=" + this.n + ", isRemoteEnter=" + this.o + ", saveName=" + this.p + ", saveFileExists=" + this.q + ", downLoadStatus=" + this.r + "]";
    }
}
