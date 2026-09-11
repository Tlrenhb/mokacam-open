package com.aee.mokacam.service;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.aee.mokacam.R;
import com.aee.mokacam.constants.AeeConstants;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

/* JADX INFO: loaded from: classes.dex */
public class UpdateManager {
    HashMap<String, String> a;
    int c;
    Context e;
    ProgressBar f;
    TextView h;
    ak i;
    com.aee.mokacam.widget.p j;
    com.aee.mokacam.widget.p k;
    Callback.Cancelable l;
    boolean d = false;
    public boolean b = true;
    Handler g = new ag(this);

    public UpdateManager(Context context) {
        this.e = context;
    }

    int a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static InputStream a(String str, String str2) throws ProtocolException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setReadTimeout(6000);
        if (httpURLConnection.getResponseCode() == 200) {
            return httpURLConnection.getInputStream();
        }
        return null;
    }

    Callback.Cancelable a(String str, String str2, Callback.CommonCallback<File> commonCallback) {
        if (new File(str2).exists()) {
            e();
            return null;
        }
        RequestParams requestParams = new RequestParams(str);
        requestParams.setSaveFilePath(str2);
        return org.xutils.x.http().get(requestParams, commonCallback);
    }

    void b() {
        this.j = new com.aee.mokacam.widget.p(this.e, new ah(this), 1, R.string.soft_update_title, R.string.network_err1);
        this.j.setTitle(R.string.soft_update_title);
        this.j.a(String.valueOf(this.e.getResources().getString(R.string.soft_update_info)) + "\n" + this.a.get("info"));
        this.j.b(R.string.soft_update_updatebtn);
        this.j.a(R.string.soft_update_later);
    }

    /* JADX INFO: Access modifiers changed from: */
    public void c() {
        View viewInflate = LayoutInflater.from(this.e).inflate(R.layout.softupdate_progress, (ViewGroup) null);
        this.f = (ProgressBar) viewInflate.findViewById(R.id.update_progress);
        this.h = (TextView) viewInflate.findViewById(R.id.update_progress_textView);
        this.k = new com.aee.mokacam.widget.p(this.e, new ai(this), viewInflate, 3, R.string.soft_updating);
    }

    /* JADX INFO: Access modifiers changed from: */
    public void d() {
        this.l = a(this.a.get("url"), String.valueOf(AeeConstants.e) + "/" + this.a.get("name"), new aj(this, null));
    }

    /* JADX INFO: Access modifiers changed from: */
    public void e() {
        File file = new File(AeeConstants.e, this.a.get("name"));
        if (file.exists()) {
            System.out.println("installApk");
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse("file://" + file.toString()), "application/vnd.android.package-archive");
            this.e.startActivity(intent);
        }
    }

    public int a() {
        int i;
        try {
            int iA = a(this.e);
            try {
                this.a = new com.aee.mokacam.utils.r().a(a(AeeConstants.v, "utf-8"));
                i = (this.a == null || Integer.valueOf(this.a.get("version")).intValue() <= iA) ? 1 : 0;
            } catch (Exception e) {
                e.printStackTrace();
                i = 3;
            }
            return i;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 2;
        }
    }

    public void a(ak akVar) {
        this.i = akVar;
    }

    public void a(boolean z) {
        try {
            if (a() == 0 && this.b) {
                b();
                com.aee.mokacam.utils.b.a(this.e, 1);
            } else if (a() != 1) {
                if (a() != 2) {
                    a();
                }
            } else {
                if (z) {
                    Toast.makeText(this.e, "无更新", 0).show();
                }
                this.i.a(1);
                com.aee.mokacam.utils.b.a(this.e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkUpdateCallback() {
        a(true);
        this.i.a(1);
    }

    public void checkUpdateCallback(ak akVar, boolean z) {
        a(akVar);
        a(z);
    }
}
