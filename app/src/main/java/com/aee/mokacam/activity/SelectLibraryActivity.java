package com.aee.mokacam.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Message;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.SendMsg;
import com.aee.mokacam.constants.AeeConstants;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import wseemann.media.FFmpegMediaMetadataRetriever;

/* JADX INFO: loaded from: classes.dex */
public class SelectLibraryActivity extends BaseActivity {
    boolean a;
    RelativeLayout b;
    RelativeLayout c;
    com.aee.mokacam.widget.g d;
    ImageView e;
    List<com.aee.mokacam.bean.g> f;
    List<com.aee.mokacam.bean.g> g;
    ImageView h;
    ImageView k;
    com.nostra13.universalimageloader.core.ImageLoader l;
    com.aee.mokacam.utils.e m;
    ImageView n;
    ImageView o;
    Bitmap p;
    List<com.aee.mokacam.bean.g> q = new ArrayList();
    String r;

    void a() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
    }

    /* JADX INFO: Access modifiers changed from: */
    public void a(List<com.aee.mokacam.bean.g> list, int i) {
        String strB = b(list, i);
        try {
            com.aee.mokacam.utils.j jVarA = this.m.a(com.aee.mokacam.utils.o.a(strB));
            Bitmap bitmapDecodeStream = jVarA != null ? BitmapFactory.decodeStream(jVarA.a(0)) : c(strB);
            Message messageObtainMessage = this.i.obtainMessage();
            messageObtainMessage.what = 1;
            messageObtainMessage.obj = bitmapDecodeStream;
            this.i.sendMessage(messageObtainMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: */
    public String b(List<com.aee.mokacam.bean.g> list, int i) {
        File file = new File(list.get(i).a());
        return file.exists() ? file.getAbsolutePath() : String.valueOf(AeeApplication.a().h) + this.r + list.get(i).a();
    }

    void b() {
        this.b = (RelativeLayout) findViewById(R.id.rl_camera_library);
        this.c = (RelativeLayout) findViewById(R.id.rl_local_library);
        this.e = (ImageView) findViewById(R.id.iv_selectlib_back);
        this.h = (ImageView) findViewById(R.id.iv_camera_library);
        this.k = (ImageView) findViewById(R.id.iv_local_library);
        this.n = (ImageView) findViewById(R.id.iv_cameralib_play);
        this.o = (ImageView) findViewById(R.id.iv_locallib_play);
    }

    void b(String str) {
        com.aee.mokacam.service.a.a().a(new cd(this, str), new SendMsg(AeeConstants.m, "/tmp/SD0/moka/" + str, null));
        try {
            TimeUnit.MILLISECONDS.sleep(300L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Bitmap c(String str) {
        try {
            FFmpegMediaMetadataRetriever fFmpegMediaMetadataRetriever = new FFmpegMediaMetadataRetriever();
            fFmpegMediaMetadataRetriever.setDataSource(str);
            Bitmap frameAtTime = fFmpegMediaMetadataRetriever.getFrameAtTime(1000L, 2);
            if (frameAtTime != null && frameAtTime.getWidth() > 640) {
                this.p = ThumbnailUtils.extractThumbnail(frameAtTime, 80, 80, 2);
            }
            return this.p;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    void c() {
        com.aee.mokacam.service.a.a().d();
        this.l = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        h();
        g();
    }

    void d() {
        this.b.setOnClickListener(new bz(this));
        this.c.setOnClickListener(new ca(this));
        this.e.setOnClickListener(new cb(this));
    }

    void e() {
        AeeApplication.a();
        try {
            com.aee.mokacam.service.a.a().a(new cc(this), new SendMsg(AeeConstants.m, "/tmp/SD0/moka/", null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.c.setVisibility(0);
    }

    void f() {
        this.f = null;
        this.f = AeeApplication.a().r;
        if (this.f == null || this.f.size() == 0) {
            this.n.setVisibility(8);
            this.h.setImageResource(R.drawable.icon_lib_bg);
        } else {
            if (!this.f.get(0).a().endsWith(".JPG")) {
                new ce(this).start();
                return;
            }
            this.l.displayImage(String.valueOf(AeeApplication.a().h) + this.r + this.f.get(0).a(), this.h);
            this.n.setVisibility(8);
        }
    }

    void g() {
        this.g = com.aee.mokacam.utils.t.a(AeeConstants.a);
        if (this.g == null || this.g.size() == 0) {
            this.o.setVisibility(8);
            this.k.setImageResource(R.drawable.icon_lib_bg);
        } else if (!this.g.get(0).a().endsWith(".JPG")) {
            new cg(this).start();
            this.o.setVisibility(0);
        } else {
            this.l.displayImage("file:///" + this.g.get(0).a(), this.k);
            this.o.setVisibility(8);
        }
    }

    void h() {
        File fileA = com.aee.mokacam.utils.t.a(this.j, "videoThumbnailCache");
        if (!fileA.exists()) {
            fileA.mkdirs();
        }
        try {
            this.m = com.aee.mokacam.utils.e.a(fileA, com.aee.mokacam.utils.a.c(this.j), 1, 10485760L);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap a(String str) {
        Bitmap bitmapDecodeStream = null;
        try {
            com.aee.mokacam.utils.j jVarA = this.m.a(com.aee.mokacam.utils.o.a(str));
            bitmapDecodeStream = jVarA != null ? BitmapFactory.decodeStream(jVarA.a(0)) : c(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmapDecodeStream;
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.k.setImageBitmap((Bitmap) message.obj);
                break;
            case 2:
                List<com.aee.mokacam.bean.g> listA = com.aee.mokacam.bean.g.a(message.obj);
                if (listA.size() == 0) {
                    this.d.dismiss();
                    this.b.setVisibility(0);
                    this.c.setVisibility(0);
                    if (com.aee.mokacam.utils.t.a(this.j, "videoThumbnailCache").delete()) {
                        h();
                    }
                    this.l.clearMemoryCache();
                    this.l.clearDiscCache();
                } else {
                    this.r = listA.get(0).a();
                    if (AeeApplication.a().r != null) {
                        AeeApplication.a().r.clear();
                    }
                    for (int i = 0; i < listA.size(); i++) {
                        b(listA.get(i).a());
                    }
                    this.d.dismiss();
                    this.b.setVisibility(0);
                    this.c.setVisibility(0);
                    f();
                }
                break;
            case 3:
                this.d.dismiss();
                break;
        }
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        g();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_selectlibrary);
        a();
        b();
        c();
        d();
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityHoneycomb, android.app.Activity, android.view.LayoutInflater.Factory2
    public /* bridge */ /* synthetic */ View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(view, str, context, attributeSet);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity, android.view.LayoutInflater.Factory
    public /* bridge */ /* synthetic */ View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.d = new com.aee.mokacam.widget.g(this);
        this.d.show();
        e();
    }
}
