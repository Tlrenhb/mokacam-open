package com.aee.mokacam.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;
import com.aee.mokacam.bean.SendMsg;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class AeeCameraActivty extends BaseActivity implements View.OnClickListener {
    static TextView Y;
    static String u;
    static String v;
    MediaPlayer A;
    MediaPlayer B;
    String D;
    String E;
    boolean H;
    int I;
    String J;
    String K;
    String L;
    String M;
    boolean S;
    ImageView V;
    ImageView W;
    ImageView X;
    TextView Z;
    TextView aa;
    ImageView ab;
    ImageView ac;
    ImageView ad;
    PopupWindow ae;
    ImageView l;
    LinearLayout m;
    TextView n;
    TextView o;
    FrameLayout p;
    com.android.gl2jni.a r;
    String s;
    Timer w;
    TimerTask x;
    static int t = 0;
    public static Handler a = new e();
    boolean f = true;
    boolean g = true;
    boolean h = false;
    boolean k = false;
    boolean q = false;
    int y = 0;
    int z = 0;
    boolean C = true;
    boolean F = false;
    boolean G = true;
    public String b = "0.0.0.0";
    int N = 999;
    int O = 999;
    int P = 0;
    public final int c = 60000;
    int Q = 0;
    public int d = 0;
    boolean bRecording = false;
    long T = 0;
    long U = 0;
    boolean af = false;
    View.OnClickListener e = new k(this);

    /* JADX INFO: Access modifiers changed from: */
    public void a(String str, String str2) {
        com.aee.mokacam.service.a.a().a(new i(this), new SendMsg(2, str, str2));
    }

    void a(boolean z) {
        this.af = false;
        if (this.bRecording) {
            com.aee.mokacam.utils.w.a(R.string.please_stop_record, true);
        } else {
            if (this.S) {
                com.aee.mokacam.utils.w.a(R.string.please_stop_continous, true);
                return;
            }
            AeeApplication.a().bg = false;
            this.Q = 0;
            this.d = 0;
        }
    }

    void d(int i) {
        this.X.setImageResource(R.drawable.operate_pressed);
        this.m.setAlpha(0.5f);
        this.X.setEnabled(false);
        this.V.setEnabled(false);
        this.W.setEnabled(false);
        this.ad.setEnabled(false);
        com.aee.mokacam.service.a.a().a(this.i, i, true);
        this.A.start();
    }

    void f() {
        this.o = (TextView) findViewById(R.id.tv_video_time);
        ImageView imageView = (ImageView) findViewById(R.id.iv_cam_home);
        Y = (TextView) findViewById(R.id.tv_cam_resolution);
        this.Z = (TextView) findViewById(R.id.tv_video_time);
        this.aa = (TextView) findViewById(R.id.tv_warm);
        this.n = (TextView) findViewById(R.id.tv_warn);
        this.l = (ImageView) findViewById(R.id.record_point);
        this.ab = (ImageView) findViewById(R.id.iv_wifi_signal);
        this.ac = (ImageView) findViewById(R.id.iv_camera_battery);
        ImageView imageView2 = (ImageView) findViewById(R.id.iv_camera_setting);
        ImageView imageView3 = (ImageView) findViewById(R.id.iv_cam_cack);
        imageView.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        this.m = (LinearLayout) findViewById(R.id.right_frame);
        this.V = (ImageView) findViewById(R.id.iv_photo_mode);
        this.W = (ImageView) findViewById(R.id.iv_video_mode);
        this.X = (ImageView) findViewById(R.id.iv_operate);
        this.ad = (ImageView) findViewById(R.id.iv_libary);
        this.V.setOnClickListener(this);
        this.W.setOnClickListener(this);
        this.X.setOnClickListener(this);
        this.ad.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        this.p = (FrameLayout) findViewById(R.id.rtsp_surface);
        this.r = new com.android.gl2jni.a(this, this);
        this.p.addView(this.r);
    }

    void g() {
        this.z = 0;
        this.A = MediaPlayer.create(this, R.raw.captureburst);
        this.B = MediaPlayer.create(this, R.raw.camera_timer);
        a();
    }

    void h() {
        this.H = true;
        new Thread(new l(this)).start();
    }

    /* JADX INFO: Access modifiers changed from: */
    public void i() {
        this.U = System.currentTimeMillis();
        if (this.T == 0) {
            this.T = this.U;
        }
        if (this.U - this.T > 4000) {
            this.T = this.U;
            j();
        }
    }

    /* JADX INFO: Access modifiers changed from: */
    public void j() {
        new o(this).start();
    }

    /* JADX INFO: Access modifiers changed from: */
    public void k() {
        if (this.w == null) {
            this.w = new Timer();
            this.x = new p(this);
            this.w.schedule(this.x, 1000L, 1000L);
        }
    }

    void l() {
        if (this.w != null) {
            this.w.cancel();
            this.x.cancel();
            this.x = null;
            this.w = null;
        }
        this.y = 0;
        this.o.setText("00:00:00");
        this.l.setVisibility(4);
    }

    void m() {
        com.aee.mokacam.service.a.a().a(new r(this), new SendMsg(2, "nil", "Switch_mode"));
    }

    /* JADX INFO: Access modifiers changed from: */
    public void n() {
        ReceiveMsg receiveMsgA = com.aee.mokacam.service.a.a().a(new SendMsg("获取主机所有信息", 1, (String) null, "app_status", 21));
        if (receiveMsgA == null || receiveMsgA.getRval() < 0 || !receiveMsgA.getType().equals("app_status")) {
            return;
        }
        String string = receiveMsgA.getParam().toString();
        if (!this.F && "record".equals(string)) {
            this.i.sendEmptyMessage(103);
        } else {
            if ((this.G || !"vf".equals(string)) && (this.G || !"idle".equals(string))) {
                return;
            }
            this.i.sendEmptyMessage(104);
        }
    }

    void o() {
        new f(this).start();
    }

    void p() {
        if (this.w == null) {
            this.w = new Timer();
            this.x = new g(this);
            this.w.schedule(this.x, 0L, 920L);
        }
    }

    @SuppressLint({"InflateParams"})
    void q() {
        View viewInflate = getLayoutInflater().inflate(R.layout.photo_mode_window, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_signal_shot);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_brust_mode);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.tv_delay_mode);
        textView.setOnClickListener(this.e);
        textView2.setOnClickListener(this.e);
        textView3.setOnClickListener(this.e);
        this.ae = new PopupWindow(viewInflate, -2, -2);
        this.ae.setFocusable(true);
        this.ae.setOutsideTouchable(true);
        this.ae.setBackgroundDrawable(new BitmapDrawable());
        this.ae.setAnimationStyle(R.style.right2left_popstyle);
        this.ae.showAsDropDown(this.V, com.aee.mokacam.utils.d.a(this, -210.0f), com.aee.mokacam.utils.d.a(this, -45.0f));
        this.ae.setOnDismissListener(new h(this));
    }

    void r() {
        com.aee.mokacam.service.a.a().b(new j(this));
        AeeApplication.a().f = false;
        com.aee.mokacam.service.a.a().f();
        try {
            TimeUnit.MICROSECONDS.sleep(155L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String a(int i) {
        return i <= 0 ? "0.0.0.0" : (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    protected void a() {
        new Thread(new m(this)).start();
    }

    public boolean b(int i) {
        return i > 0 && new StringBuilder().append(i & 255).append(".").append((i >> 8) & 255).append(".").append((i >> 16) & 255).append(".").append((i >> 24) & 255).toString().contains("192.168.42");
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 100:
                if ("adapter".equals(this.K)) {
                    this.ac.setImageResource(R.drawable.battery_charge);
                } else if (this.J.length() < 2) {
                    int i = Integer.parseInt(this.J);
                    if (i == 1) {
                        this.ac.setImageResource(R.drawable.battery_0);
                    } else if (i == 2) {
                        this.ac.setImageResource(R.drawable.battery_1);
                    } else if (i == 3) {
                        this.ac.setImageResource(R.drawable.battery_2);
                    } else if (i == 4) {
                        this.ac.setImageResource(R.drawable.battery_3);
                    } else if (i == 5) {
                        this.ac.setImageResource(R.drawable.icn_battery_100);
                    }
                }
                new s(this).start();
                break;
            case 101:
                int i2 = message.arg1;
                if (this.z % 2 == 0) {
                    this.G = true;
                    this.F = false;
                    this.X.setImageResource(R.drawable.btn_video_mode);
                    l();
                    this.V.setEnabled(true);
                    this.W.setEnabled(true);
                    this.ad.setEnabled(true);
                } else if (i2 == 0) {
                    this.F = true;
                    this.G = false;
                    this.X.setImageResource(R.drawable.btn_stop);
                    if (AeeApplication.aW.equals("off")) {
                        k();
                    } else {
                        p();
                    }
                    this.V.setEnabled(false);
                    this.W.setEnabled(false);
                    this.ad.setEnabled(false);
                } else if (i2 == -17) {
                    Toast.makeText(this, getResources().getString(R.string.take_photo_failed_sd_full), 0).show();
                    this.bRecording = false;
                    this.z = 0;
                } else if (i2 != -30) {
                    Toast.makeText(this, getResources().getString(R.string.failed), 0).show();
                    this.bRecording = false;
                    this.z = 0;
                } else {
                    Toast.makeText(this, getResources().getString(R.string.no_sdCard), 0).show();
                    this.bRecording = false;
                    this.z = 0;
                }
                break;
            case 102:
                Toast.makeText(this, getResources().getString(R.string.card_full), 0).show();
                if (t != 0) {
                    this.X.setImageResource(R.drawable.btn_operate);
                } else {
                    this.X.setImageResource(R.drawable.btn_video_mode);
                    l();
                }
                break;
            case 103:
                if (!this.bRecording) {
                    this.z++;
                    if (AeeApplication.aW.equals("off")) {
                        o();
                    } else {
                        p();
                    }
                    this.X.setImageResource(R.drawable.btn_stop);
                    this.bRecording = true;
                    this.F = false;
                    this.G = false;
                }
                break;
            case 104:
                if (this.bRecording) {
                    this.z++;
                    l();
                    this.X.setImageResource(R.drawable.btn_video_mode);
                    this.bRecording = false;
                    this.G = true;
                    this.F = false;
                }
                break;
            case 105:
                if (this.w != null) {
                    if (this.y % 2 == 0) {
                        this.l.setVisibility(4);
                    } else {
                        this.l.setVisibility(0);
                    }
                    this.o.setText(com.a.a.a.a.a(this.y));
                }
                break;
            case 32773:
                this.X.setImageResource(R.drawable.btn_operate);
                this.X.setEnabled(true);
                this.V.setEnabled(true);
                this.W.setEnabled(true);
                this.ad.setEnabled(true);
                this.m.setAlpha(1.0f);
                if (message.arg1 == 0) {
                    Toast.makeText(this, getResources().getString(R.string.take_photo_success), 0).show();
                } else if (message.arg1 == -17) {
                    Toast.makeText(this, getResources().getString(R.string.take_photo_failed_sd_full), 0).show();
                } else if (message.arg1 == -30) {
                    Toast.makeText(this, getResources().getString(R.string.no_sdCard), 0).show();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.failed), 0).show();
                }
                this.C = true;
                break;
        }
        return super.handleMessage(message);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.S) {
            Toast.makeText(this, getResources().getString(R.string.please_stop_continous), 0).show();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_camera_setting /* 2131427388 */:
                if (this.bRecording) {
                    com.aee.mokacam.utils.w.a(R.string.please_stop_record, true);
                } else if (!this.S) {
                    Intent intent = new Intent();
                    intent.putExtra("currentMode", t);
                    intent.putExtra("currentProduct", 0);
                    intent.setClass(this, AeeCameraSettingActivity.class);
                    startActivity(intent);
                } else {
                    com.aee.mokacam.utils.w.a(R.string.please_stop_continous, true);
                }
                break;
            case R.id.iv_cam_cack /* 2131427489 */:
                finish();
                break;
            case R.id.iv_cam_home /* 2131427490 */:
                AeeApplication.a().e();
                break;
            case R.id.iv_operate /* 2131427497 */:
                if (t != 0) {
                    this.C = false;
                    if (AeeApplication.aT == R.id.tv_signal_shot) {
                        d(0);
                    } else if (AeeApplication.aT == R.id.tv_brust_mode) {
                        d(1);
                    } else if (AeeApplication.aT == R.id.tv_delay_mode) {
                        d(2);
                    }
                } else {
                    this.bRecording = this.bRecording ? false : true;
                    com.aee.mokacam.service.a.a().a(this.bRecording, this.i, true);
                    this.B.start();
                    this.z++;
                }
                break;
            case R.id.iv_video_mode /* 2131427498 */:
                m();
                t = 0;
                Y.setText(u);
                a(true);
                this.h = false;
                this.X.setImageResource(R.drawable.btn_video_mode);
                this.W.setImageResource(R.drawable.btn_record_h);
                this.o.setVisibility(0);
                if (AeeApplication.aT == R.id.tv_signal_shot) {
                    this.V.setImageResource(R.drawable.btn_photo_n);
                } else if (AeeApplication.aT == R.id.tv_brust_mode) {
                    this.V.setImageResource(R.drawable.btn_burst_n);
                } else if (AeeApplication.aT == R.id.tv_delay_mode) {
                    this.V.setImageResource(R.drawable.btn_time_lapse_n);
                }
                break;
            case R.id.iv_photo_mode /* 2131427499 */:
                m();
                t = 1;
                if (this.g) {
                    v = this.s.substring(0, this.s.length() - 16);
                    this.g = false;
                }
                Y.setText(v);
                this.X.setImageResource(R.drawable.btn_operate);
                this.W.setImageResource(R.drawable.btn_record_n);
                this.o.setVisibility(4);
                q();
                break;
            case R.id.iv_libary /* 2131427500 */:
                if (this.bRecording) {
                    com.aee.mokacam.utils.w.a(R.string.please_stop_record, true);
                } else if (!this.S) {
                    Intent intent2 = new Intent();
                    intent2.setClass(getBaseContext(), SelectLibraryActivity.class);
                    startActivity(intent2);
                } else {
                    com.aee.mokacam.utils.w.a(R.string.please_stop_continous, true);
                }
                break;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        setContentView(R.layout.activity_camera);
        f();
        g();
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
    protected void onDestroy() {
        if (t == 0 && this.bRecording) {
            this.bRecording = false;
            com.aee.mokacam.service.a.a().a(this.bRecording, this.i, true);
            this.z++;
            try {
                TimeUnit.MILLISECONDS.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        r();
        t = 0;
        if (this.r != null) {
            this.r.release();
        }
        super.onDestroy();
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        try {
            this.f = false;
            this.H = false;
            this.r.onPause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        this.q = false;
        com.aee.mokacam.service.a.a().c();
        h();
        this.r.onResume();
        super.onResume();
    }
}
