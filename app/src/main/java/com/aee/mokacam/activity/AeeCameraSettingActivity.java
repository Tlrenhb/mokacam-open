package com.aee.mokacam.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;
import com.aee.mokacam.bean.SendMsg;
import com.aee.mokacam.constants.AeeConstants;
import com.zcw.togglebutton.ToggleButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class AeeCameraSettingActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView E;
    private TextView F;
    private TextView G;
    private TextView H;
    private TextView I;
    private TextView J;
    private TextView K;
    private TextView L;
    private TextView M;
    private TextView N;
    private com.aee.mokacam.widget.g O;
    private ListView P;
    private LinearLayout R;
    private LinearLayout S;
    private int T;
    private int U;
    private float V;
    private float W;
    private View X;
    private RelativeLayout Y;
    private RelativeLayout Z;
    protected String[] a;
    private EditText aA;
    private EditText aB;
    private EditText aC;
    private RelativeLayout aa;
    private RelativeLayout ab;
    private RelativeLayout ac;
    private RelativeLayout ad;
    private RelativeLayout ae;
    private RelativeLayout af;
    private RelativeLayout ag;
    private RelativeLayout ah;
    private RelativeLayout ai;
    private RelativeLayout aj;
    private RelativeLayout ak;
    private RelativeLayout al;
    private RelativeLayout am;
    private RelativeLayout an;
    private RelativeLayout ao;
    private ToggleButton aq;
    private LinearLayout ar;
    private TextView as;
    private TextView at;
    private TextView au;
    private TextView av;
    private TextView aw;
    private int ax;
    private int ay;
    private ImageView az;
    protected SendMsg b;
    protected ReceiveMsg c;
    protected String[] e;
    private String k = BuildConfig.FLAVOR;
    private String l = BuildConfig.FLAVOR;
    private String m = BuildConfig.FLAVOR;
    private String n = BuildConfig.FLAVOR;
    private String o = BuildConfig.FLAVOR;
    private String p = BuildConfig.FLAVOR;
    private String q = BuildConfig.FLAVOR;
    private String r = BuildConfig.FLAVOR;
    private String s = BuildConfig.FLAVOR;
    private String t = BuildConfig.FLAVOR;
    private String u = BuildConfig.FLAVOR;
    private String v = BuildConfig.FLAVOR;
    private String w = BuildConfig.FLAVOR;
    private String x = BuildConfig.FLAVOR;
    private String y = BuildConfig.FLAVOR;
    private String z = BuildConfig.FLAVOR;
    private String A = BuildConfig.FLAVOR;
    private String B = BuildConfig.FLAVOR;
    private String C = BuildConfig.FLAVOR;
    private String D = BuildConfig.FLAVOR;
    protected boolean d = false;
    boolean f = false;
    private int Q = -1;
    boolean g = false;
    private List<RelativeLayout> ap = new ArrayList();
    protected boolean h = false;

    private void a(int i) {
        String string = getString(R.string.sure_format);
        String string2 = getString(R.string.sure);
        String string3 = getString(R.string.cancel);
        if (i == 0) {
            a(string, string2, string3, i);
        } else {
            a(getString(R.string.sure_nesignation), string2, string3, i);
        }
    }

    private void a(String str, String str2, String str3) {
        new Thread(new x(this, str, str2, str3)).start();
        try {
            TimeUnit.MILLISECONDS.sleep(300L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void a(String str, String str2, String str3, int i) {
        View viewInflate = View.inflate(this, R.layout.dialog_warning, null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.warn_tv);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.sure_tv);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.cancel_tv);
        textView.setText(str);
        textView2.setText(str2);
        textView3.setText(str3);
        PopupWindow popupWindow = new PopupWindow(viewInflate, -2, -2);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(this.X, 17, com.aee.mokacam.utils.d.a(this, 105.0f), 0);
        textView2.setOnClickListener(new y(this, popupWindow, i));
        textView3.setOnClickListener(new ac(this, popupWindow));
    }

    private boolean a(int i, String str, String str2) {
        com.aee.mokacam.service.a.a().a(new ag(this, i, str2), new SendMsg(2, str, str2));
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        if (!TextUtils.equals(str, str2)) {
            com.aee.mokacam.utils.w.a(R.string.password_is_not_consistent, true);
            return false;
        }
        if (str.length() >= 8) {
            return true;
        }
        com.aee.mokacam.utils.w.a(R.string.password_is_too_short, true);
        return false;
    }

    private void b() {
        this.ay = getIntent().getIntExtra("currentProduct", 0);
        this.ax = getIntent().getIntExtra("currentMode", 0);
    }

    private void b(String str, String str2) {
        new Thread(new ao(this, str, str2)).start();
        try {
            TimeUnit.MILLISECONDS.sleep(300L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(String str) {
        com.aee.mokacam.service.a.a().a(new am(this), new SendMsg(2, str, "wifi_ssid"));
        return this.g;
    }

    private void c() {
        this.Y = (RelativeLayout) findViewById(R.id.video_resolution_bg);
        this.Z = (RelativeLayout) findViewById(R.id.rl_bitrate);
        this.aa = (RelativeLayout) findViewById(R.id.rl_video_stamp);
        this.ab = (RelativeLayout) findViewById(R.id.rl_photo_stamp);
        this.ac = (RelativeLayout) findViewById(R.id.rl_loop_record);
        this.ad = (RelativeLayout) findViewById(R.id.photo_resolution_bg);
        this.ae = (RelativeLayout) findViewById(R.id.photo_shot_mode_bg);
        this.af = (RelativeLayout) findViewById(R.id.photo_delay_bg);
        this.aq = (ToggleButton) findViewById(R.id.setup_loop_back);
        this.ag = (RelativeLayout) findViewById(R.id.setup_key_tone_bg);
        this.ah = (RelativeLayout) findViewById(R.id.setup_system_type_bg);
        this.ai = (RelativeLayout) findViewById(R.id.dv_language_bg);
        this.aj = (RelativeLayout) findViewById(R.id.setup_time_bg);
        this.ao = (RelativeLayout) findViewById(R.id.online_upgrade_bg);
        this.ak = (RelativeLayout) findViewById(R.id.format_bg);
        this.al = (RelativeLayout) findViewById(R.id.nesignation_bg);
        this.am = (RelativeLayout) findViewById(R.id.set_ssid_bg);
        this.an = (RelativeLayout) findViewById(R.id.set_psw_bg);
        this.ar = (LinearLayout) findViewById(R.id.ll_right_listcontent);
        this.as = (TextView) findViewById(R.id.tv_listTitle);
        this.at = (TextView) findViewById(R.id.tv_bitrate);
        this.au = (TextView) findViewById(R.id.tv_video_stamp);
        this.av = (TextView) findViewById(R.id.tv_photo_stamp);
        this.aw = (TextView) findViewById(R.id.tv_loop_record);
        this.az = (ImageView) findViewById(R.id.iv_back_camerasetting);
        this.G = (TextView) findViewById(R.id.my_video_resoultion);
        this.H = (TextView) findViewById(R.id.my_photo_resolutions);
        this.I = (TextView) findViewById(R.id.my_photo_shot_mode);
        this.J = (TextView) findViewById(R.id.my_photo_delay);
        this.K = (TextView) findViewById(R.id.my_setup_key_tone);
        this.L = (TextView) findViewById(R.id.my_setup_system_type);
        this.M = (TextView) findViewById(R.id.my_dv_language);
        this.N = (TextView) findViewById(R.id.my_setup_time);
        this.E = (TextView) findViewById(R.id.dv_pid);
        this.F = (TextView) findViewById(R.id.dv_version);
        this.R = (LinearLayout) findViewById(R.id.wifisetting_ll);
        this.S = (LinearLayout) findViewById(R.id.wifi_psw_setting_ll);
        this.ap.add(this.Y);
        this.ap.add(this.Z);
        this.ap.add(this.aa);
        this.ap.add(this.ad);
        this.ap.add(this.ae);
        this.ap.add(this.af);
        this.ap.add(this.ab);
        this.ap.add(this.ac);
        this.ap.add(this.ag);
        this.ap.add(this.ah);
        this.ap.add(this.ai);
        this.ap.add(this.aj);
        this.ap.add(this.ao);
        this.ap.add(this.ak);
        this.ap.add(this.al);
        this.ap.add(this.am);
        this.ap.add(this.an);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(String str) {
        com.aee.mokacam.service.a.a().a(new an(this), new SendMsg(2, str, "wifi_password"));
        return this.g;
    }

    private void d() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.T = displayMetrics.widthPixels;
        this.U = displayMetrics.heightPixels;
    }

    private void e() {
        this.Y.setOnClickListener(this);
        this.Z.setOnClickListener(this);
        this.aa.setOnClickListener(this);
        this.ad.setOnClickListener(this);
        this.ae.setOnClickListener(this);
        this.af.setOnClickListener(this);
        this.ag.setOnClickListener(this);
        this.ah.setOnClickListener(this);
        this.ai.setOnClickListener(this);
        this.aj.setOnClickListener(this);
        this.ao.setOnClickListener(this);
        this.ak.setOnClickListener(this);
        this.al.setOnClickListener(this);
        this.am.setOnClickListener(this);
        this.az.setOnClickListener(this);
        this.ab.setOnClickListener(this);
        this.ac.setOnClickListener(this);
        this.an.setOnClickListener(this);
    }

    private void f() {
        new Thread(new v(this)).start();
        try {
            TimeUnit.MILLISECONDS.sleep(289L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.G.setText(this.k);
        this.at.setText(com.aee.mokacam.utils.q.a("video_quality", this.m));
        this.au.setText(com.aee.mokacam.utils.q.a("video_stamp", this.p));
        this.av.setText(com.aee.mokacam.utils.q.a("photo_stamp", this.D));
        this.aw.setText(com.aee.mokacam.utils.q.a("Loop_Record", this.t));
        this.H.setText(this.n);
        if (AeeApplication.aU.equals("off")) {
            this.I.setText(com.aee.mokacam.utils.q.a("photo_burstRate", "3p"));
        } else {
            this.I.setText(com.aee.mokacam.utils.q.a("photo_burstRate", AeeApplication.aU));
        }
        if (AeeApplication.aV.equals("off")) {
            this.J.setText(com.aee.mokacam.utils.q.a("photo_selfTimer", "3s"));
        } else {
            this.J.setText(com.aee.mokacam.utils.q.a("photo_selfTimer", AeeApplication.aV));
        }
        this.K.setText(com.aee.mokacam.utils.q.a("Beep", this.u));
        this.L.setText(this.y);
        this.M.setText(com.aee.mokacam.utils.q.a("Language", this.B));
        this.N.setText(this.A);
        this.F.setText(this.o);
    }

    private void h() {
        AeeApplication.a().a = -1;
        com.aee.mokacam.service.a.a().b(new ae(this));
        com.aee.mokacam.service.a.a().f();
        AeeApplication.a().f = false;
    }

    private void i() {
        this.P.setAdapter((ListAdapter) new af(this, this, android.R.layout.simple_list_item_1, this.a));
    }

    protected void a() {
        Map<String, String> map = AeeApplication.a().q;
        if (map != null) {
            this.k = map.get("video_resolution");
            if (this.k == null) {
                this.k = BuildConfig.FLAVOR;
            }
            this.l = map.get("video_fov");
            if (this.l == null) {
                this.l = BuildConfig.FLAVOR;
            }
            this.m = map.get("video_quality");
            if (this.m == null) {
                this.m = BuildConfig.FLAVOR;
            }
            this.n = map.get("photo_size");
            if (this.n == null) {
                this.n = BuildConfig.FLAVOR;
            }
            this.o = map.get("sw_version");
            if (this.o == null) {
                this.o = BuildConfig.FLAVOR;
            }
            this.p = map.get("video_stamp");
            if (this.p == null) {
                this.p = BuildConfig.FLAVOR;
            }
            this.q = map.get("photo_burstRate");
            if (this.q == null) {
                this.q = BuildConfig.FLAVOR;
            }
            this.r = map.get("photo_selftimer");
            if (this.r == null) {
                this.r = BuildConfig.FLAVOR;
            }
            this.s = map.get("photo_selfTimer");
            if (this.s == null) {
                this.s = BuildConfig.FLAVOR;
            }
            this.t = map.get("Loop_Record");
            if (this.t == null) {
                this.t = BuildConfig.FLAVOR;
            }
            this.u = map.get("Beep");
            if (this.u == null) {
                this.u = BuildConfig.FLAVOR;
            }
            this.v = map.get("Status_LED");
            if (this.v == null) {
                this.v = BuildConfig.FLAVOR;
            }
            this.w = map.get("setup_osd");
            if (this.w == null) {
                this.w = BuildConfig.FLAVOR;
            }
            this.x = map.get("setup_poweroff");
            if (this.x == null) {
                this.x = BuildConfig.FLAVOR;
            }
            this.y = map.get("TV_Mode");
            if (this.y == null) {
                this.y = BuildConfig.FLAVOR;
            }
            this.z = map.get("setup_time");
            if (this.z == null) {
                this.z = BuildConfig.FLAVOR;
            }
            this.A = map.get("camera_clock");
            if (this.A == null) {
                this.A = BuildConfig.FLAVOR;
            }
            this.B = map.get("Language");
            if (this.B == null) {
                this.B = BuildConfig.FLAVOR;
            }
            this.C = map.get("timelapse_video");
            if (this.C == null) {
                this.C = BuildConfig.FLAVOR;
            }
            this.D = map.get("photo_stamp");
            if (this.D == null) {
                this.D = BuildConfig.FLAVOR;
            }
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 110:
                i();
                break;
            case 111:
                String str = (String) message.obj;
                f();
                if ("video_resolution".equals(str) && this.f) {
                    Message messageObtainMessage = AeeCameraActivty.a.obtainMessage();
                    messageObtainMessage.obj = this.a[message.arg1];
                    AeeCameraActivty.a.sendMessage(messageObtainMessage);
                } else if ("video_quality".equals(str) && this.f) {
                    this.at.setText(com.aee.mokacam.utils.q.a("video_quality", this.a[message.arg1]));
                } else if ("video_stamp".equals(str) && this.f) {
                    this.au.setText(com.aee.mokacam.utils.q.a("video_stamp", this.a[message.arg1]));
                } else if ("photo_size".equals(str) && this.f) {
                    Message messageObtainMessage2 = AeeCameraActivty.a.obtainMessage();
                    messageObtainMessage2.obj = this.a[message.arg1];
                    AeeCameraActivty.a.sendMessage(messageObtainMessage2);
                } else if ("photo_burstRate".equals(str) && this.f) {
                    AeeApplication.aU = this.e[message.arg1];
                    this.I.setText(com.aee.mokacam.utils.q.a("photo_burstRate", this.a[message.arg1 - 1]));
                } else if ("photo_selfTimer".equals(str) && this.f) {
                    AeeApplication.aV = this.e[message.arg1];
                    this.J.setText(com.aee.mokacam.utils.q.a("photo_selfTimer", this.a[message.arg1 - 1]));
                } else if ("photo_stamp".equals(str) && this.f) {
                    this.av.setText(com.aee.mokacam.utils.q.a("photo_stamp", this.a[message.arg1]));
                } else if ("Loop_Record".equals(str) && this.f) {
                    AeeApplication.aW = this.e[message.arg1];
                    this.aw.setText(com.aee.mokacam.utils.q.a("Loop_Record", this.a[message.arg1]));
                } else if ((!"Beep".equals(str) || !this.f) && (!"Status_LED".equals(str) || !this.f)) {
                    if ("TV_Mode".equals(str) && this.f) {
                        Message messageObtainMessage3 = AeeCameraActivty.a.obtainMessage();
                        messageObtainMessage3.obj = this.k;
                        AeeCameraActivty.a.sendMessage(messageObtainMessage3);
                    } else if ("Language".equals(str) && this.f) {
                        this.M.setText(com.aee.mokacam.utils.q.a("TV_Mode", this.a[message.arg1]));
                    }
                }
                break;
            case 112:
                if (!this.g) {
                    this.aA.setText(BuildConfig.FLAVOR);
                    this.aB.setText(BuildConfig.FLAVOR);
                    this.aC.setText(BuildConfig.FLAVOR);
                    com.aee.mokacam.utils.w.a(R.string.wifi_setting_fail, true);
                } else {
                    this.R.setVisibility(8);
                    this.S.setVisibility(8);
                    AeeApplication.a().f = false;
                    com.aee.mokacam.service.a.a().f();
                    AeeApplication.a().d();
                }
                break;
            case 32799:
                h();
                AeeApplication.a().bn = true;
                AeeApplication.a().d();
                break;
            case 32800:
                com.aee.mokacam.service.a.a().a(new ad(this), new SendMsg(8, null, null));
                this.O.dismiss();
                break;
        }
        return super.handleMessage(message);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        if (getResources().getConfiguration().orientation != 2) {
            switch (view.getId()) {
                case R.id.video_resolution /* 2131427352 */:
                    Intent intent = new Intent(getBaseContext(), (Class<?>) SelectSimpleActivity.class);
                    intent.putExtra("fromFlag", 32774);
                    AeeApplication.a().bh = this.k;
                    startActivity(intent);
                    break;
                case R.id.photo_resolution /* 2131427356 */:
                    if (AeeApplication.a().bg) {
                        com.aee.mokacam.utils.w.a(R.string.fast_not_support_resolution, true);
                    } else {
                        new Intent(getBaseContext(), (Class<?>) SelectSimpleActivity.class).putExtra("fromFlag", 32776);
                    }
                    AeeApplication.a().bh = this.n;
                    break;
                case R.id.photo_shot_mode /* 2131427358 */:
                    Intent intent2 = new Intent(getBaseContext(), (Class<?>) SelectSimpleActivity.class);
                    intent2.putExtra("fromFlag", 32777);
                    AeeApplication.a().bh = this.q;
                    startActivity(intent2);
                    break;
                case R.id.setup_key_tone /* 2131427364 */:
                    Intent intent3 = new Intent(getBaseContext(), (Class<?>) SelectSimpleActivity.class);
                    intent3.putExtra("fromFlag", 32784);
                    AeeApplication.a().bh = this.u;
                    startActivity(intent3);
                    break;
                case R.id.setup_selflamp /* 2131427366 */:
                    Intent intent4 = new Intent(getBaseContext(), (Class<?>) SelectSimpleActivity.class);
                    intent4.putExtra("fromFlag", 32785);
                    AeeApplication.a().bh = this.v;
                    startActivity(intent4);
                    break;
                case R.id.setup_system_type /* 2131427368 */:
                    Intent intent5 = new Intent(getBaseContext(), (Class<?>) SelectSimpleActivity.class);
                    intent5.putExtra("fromFlag", 32788);
                    AeeApplication.a().bh = this.y;
                    startActivity(intent5);
                    break;
                case R.id.dv_language /* 2131427370 */:
                    Intent intent6 = new Intent(getBaseContext(), (Class<?>) SelectSimpleActivity.class);
                    intent6.putExtra("fromFlag", 32789);
                    AeeApplication.a().bh = this.B;
                    startActivity(intent6);
                    break;
                case R.id.setup_time /* 2131427372 */:
                    if (com.aee.mokacam.service.a.a().e()) {
                        com.aee.mokacam.utils.w.a(R.string.set_success, true);
                    } else {
                        com.aee.mokacam.utils.w.a(R.string.set_failed, true);
                    }
                    f();
                    break;
                case R.id.format /* 2131427374 */:
                    a(0);
                    break;
                case R.id.nesignation /* 2131427375 */:
                    a(1);
                    break;
                case R.id.set_ssid /* 2131427380 */:
                    if (AeeApplication.a().x) {
                        com.aee.mokacam.utils.w.a(R.string.no_sdCard, true);
                    } else {
                        startActivity(new Intent(this.j, (Class<?>) AeeCameraWifiConfigActivity.class));
                    }
                    break;
                case R.id.photo_delay /* 2131427400 */:
                    Intent intent7 = new Intent(getBaseContext(), (Class<?>) SelectSimpleActivity.class);
                    intent7.putExtra("fromFlag", 32783);
                    AeeApplication.a().bh = this.s;
                    startActivity(intent7);
                    break;
            }
        }
        if (this.P == null) {
            this.P = (ListView) findViewById(R.id.right_listview);
            this.P.setSelector(new ColorDrawable(0));
            this.P.setDivider(new ColorDrawable(-1));
            this.P.setDividerHeight(1);
            this.P.setOnItemClickListener(this);
        }
        for (int i = 0; i < this.ap.size(); i++) {
            this.ap.get(i).setBackgroundColor(0);
        }
        switch (view.getId()) {
            case R.id.iv_back_camerasetting /* 2131427382 */:
                finish();
                break;
            case R.id.video_resolution_bg /* 2131427389 */:
                if (this.ax == 0) {
                    this.Q = 1;
                    this.Y.setBackgroundResource(R.drawable.settings_item_pressed);
                    this.ar.setVisibility(0);
                    this.R.setVisibility(8);
                    this.S.setVisibility(8);
                    this.as.setText(R.string.video_resolutions);
                    b("获取主机视频参数  配置", "video_resolution");
                } else {
                    Toast.makeText(this, getResources().getString(R.string.change_video_mode), 0).show();
                }
                break;
            case R.id.rl_bitrate /* 2131427390 */:
                if (this.ax == 0) {
                    this.Q = 2;
                    this.Z.setBackgroundResource(R.drawable.settings_item_pressed);
                    this.ar.setVisibility(0);
                    this.R.setVisibility(8);
                    this.S.setVisibility(8);
                    this.as.setText(R.string.bit_rate);
                    b("获取主机视频参数配置", "video_quality");
                } else {
                    Toast.makeText(this, getResources().getString(R.string.change_video_mode), 0).show();
                }
                break;
            case R.id.rl_video_stamp /* 2131427393 */:
                if (this.ax == 0) {
                    this.Q = 3;
                    this.aa.setBackgroundResource(R.drawable.settings_item_pressed);
                    this.ar.setVisibility(0);
                    this.R.setVisibility(8);
                    this.S.setVisibility(8);
                    this.as.setText(R.string.video_stamp);
                    b("获取主机视频参数配置", "video_stamp");
                } else {
                    Toast.makeText(this, getResources().getString(R.string.change_video_mode), 0).show();
                }
                break;
            case R.id.photo_resolution_bg /* 2131427397 */:
                if (this.ax == 1) {
                    this.Q = 7;
                    this.ad.setBackgroundResource(R.drawable.settings_item_pressed);
                    this.ar.setVisibility(0);
                    this.R.setVisibility(8);
                    this.S.setVisibility(8);
                    this.as.setText(R.string.photo_resolution);
                    b("获取主机视频参数配置", "photo_size");
                } else {
                    Toast.makeText(this, getResources().getString(R.string.change_photo_mode), 0).show();
                }
                break;
            case R.id.photo_shot_mode_bg /* 2131427398 */:
                if (this.ax == 1) {
                    this.Q = 8;
                    this.ae.setBackgroundResource(R.drawable.settings_item_pressed);
                    this.ar.setVisibility(0);
                    this.R.setVisibility(8);
                    this.S.setVisibility(8);
                    this.as.setText(R.string.photo_shot_mode);
                    a("快拍设置配置", "photo_burstRate", (String) null);
                } else {
                    Toast.makeText(this, getResources().getString(R.string.change_photo_mode), 0).show();
                }
                break;
            case R.id.photo_delay_bg /* 2131427399 */:
                if (this.ax == 1) {
                    this.Q = 9;
                    this.af.setBackgroundResource(R.drawable.settings_item_pressed);
                    this.ar.setVisibility(0);
                    this.R.setVisibility(8);
                    this.S.setVisibility(8);
                    this.as.setText(R.string.photo_delay);
                    a("延迟拍照配置", "photo_selfTimer", (String) null);
                } else {
                    Toast.makeText(this, getResources().getString(R.string.change_photo_mode), 0).show();
                }
                break;
            case R.id.rl_photo_stamp /* 2131427402 */:
                if (this.ax == 1) {
                    this.Q = 10;
                    this.aa.setBackgroundResource(R.drawable.settings_item_pressed);
                    this.ar.setVisibility(0);
                    this.R.setVisibility(8);
                    this.S.setVisibility(8);
                    this.as.setText(R.string.video_stamp);
                    b("获取主机视频参数配置", "photo_stamp");
                } else {
                    Toast.makeText(this, getResources().getString(R.string.change_photo_mode), 0).show();
                }
                break;
            case R.id.rl_loop_record /* 2131427405 */:
                this.Q = 11;
                this.aa.setBackgroundResource(R.drawable.settings_item_pressed);
                this.ar.setVisibility(0);
                this.R.setVisibility(8);
                this.S.setVisibility(8);
                this.as.setText(R.string.setup_loop_back);
                a("获取主机循环录像", "Loop_Record", (String) null);
                break;
            case R.id.setup_key_tone_bg /* 2131427408 */:
                this.Q = 15;
                this.ag.setBackgroundResource(R.drawable.settings_item_pressed);
                this.ar.setVisibility(0);
                this.R.setVisibility(8);
                this.S.setVisibility(8);
                this.as.setText(R.string.setup_key_tone);
                a("设置提示音", "Beep", (String) null);
                break;
            case R.id.setup_system_type_bg /* 2131427410 */:
                this.Q = 17;
                this.ah.setBackgroundResource(R.drawable.settings_item_pressed);
                this.ar.setVisibility(0);
                this.R.setVisibility(8);
                this.S.setVisibility(8);
                this.as.setText(R.string.setup_system_type);
                this.a = new String[]{"NTSC", "PAL"};
                this.i.sendEmptyMessage(110);
                break;
            case R.id.dv_language_bg /* 2131427411 */:
                this.Q = 18;
                this.ai.setBackgroundResource(R.drawable.settings_item_pressed);
                this.ar.setVisibility(0);
                this.R.setVisibility(8);
                this.S.setVisibility(8);
                this.as.setText(R.string.dv_language);
                a("DV端语言设置", "Language", (String) null);
                break;
            case R.id.setup_time_bg /* 2131427412 */:
                this.R.setVisibility(8);
                this.ar.setVisibility(8);
                this.S.setVisibility(8);
                if (com.aee.mokacam.service.a.a().e()) {
                    com.aee.mokacam.utils.w.a(R.string.set_success, true);
                } else {
                    com.aee.mokacam.utils.w.a(R.string.set_failed, true);
                }
                f();
                break;
            case R.id.online_upgrade_bg /* 2131427413 */:
                this.O.show();
                SendMsg sendMsg = new SendMsg(AeeConstants.l, "/tmp/SD0", null);
                com.aee.mokacam.utils.w.a(R.string.upgrading, true);
                com.aee.mokacam.service.a.a().a(new ah(this), sendMsg);
                try {
                    TimeUnit.MILLISECONDS.sleep(200L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int ipAddress = ((WifiManager) getSystemService("wifi")).getConnectionInfo().getIpAddress();
                String str = BuildConfig.FLAVOR;
                if (ipAddress != 0) {
                    str = String.valueOf(ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
                }
                com.aee.mokacam.service.a.a().a(str);
                try {
                    TimeUnit.MILLISECONDS.sleep(220L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                com.aee.mokacam.service.a.a().a(this.i);
                break;
            case R.id.format_bg /* 2131427415 */:
                this.ak.setBackgroundResource(R.drawable.settings_item_pressed);
                this.R.setVisibility(8);
                this.ar.setVisibility(8);
                this.S.setVisibility(8);
                a(0);
                break;
            case R.id.nesignation_bg /* 2131427416 */:
                this.al.setBackgroundResource(R.drawable.settings_item_pressed);
                this.R.setVisibility(8);
                this.ar.setVisibility(8);
                this.S.setVisibility(8);
                a(1);
                break;
            case R.id.set_ssid_bg /* 2131427419 */:
                this.am.setBackgroundResource(R.drawable.settings_item_pressed);
                if (AeeApplication.a().x) {
                    com.aee.mokacam.utils.w.a(R.string.no_sdCard, true);
                } else {
                    Button button = (Button) findViewById(R.id.wifisetting_cancel);
                    Button button2 = (Button) findViewById(R.id.wifisetting_confirm);
                    this.aA = (EditText) findViewById(R.id.new_ssid);
                    this.aA.setText(BuildConfig.FLAVOR);
                    this.R.setVisibility(0);
                    this.S.setVisibility(8);
                    this.ar.setVisibility(8);
                    button2.setOnClickListener(new ai(this));
                    button.setOnClickListener(new aj(this));
                }
                break;
            case R.id.set_psw_bg /* 2131427420 */:
                this.an.setBackgroundResource(R.drawable.settings_item_pressed);
                if (AeeApplication.a().x) {
                    com.aee.mokacam.utils.w.a(R.string.no_sdCard, true);
                } else {
                    Button button3 = (Button) findViewById(R.id.wifi_psw_setting_cancel);
                    Button button4 = (Button) findViewById(R.id.wifi_psw_setting_confirm);
                    this.aB = (EditText) findViewById(R.id.new_password);
                    this.aC = (EditText) findViewById(R.id.confirm_password);
                    this.aB.setText(BuildConfig.FLAVOR);
                    this.aC.setText(BuildConfig.FLAVOR);
                    this.R.setVisibility(8);
                    this.S.setVisibility(0);
                    this.ar.setVisibility(8);
                    button4.setOnClickListener(new ak(this));
                    button3.setOnClickListener(new al(this));
                }
                break;
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.X = View.inflate(this, R.layout.activity_aeecamera_setting, null);
        setContentView(this.X);
        this.O = new com.aee.mokacam.widget.g(this);
        b();
        c();
        d();
        e();
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityHoneycomb, android.app.Activity, android.view.LayoutInflater.Factory2
    public /* bridge */ /* synthetic */ View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(view, str, context, attributeSet);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity, android.view.LayoutInflater.Factory
    public /* bridge */ /* synthetic */ View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.ar.setVisibility(4);
        for (int i2 = 0; i2 < this.ap.size(); i2++) {
            this.ap.get(i2).setBackgroundColor(0);
        }
        switch (this.Q) {
            case 1:
                a(i, this.a[i], "video_resolution");
                break;
            case 2:
                a(i, this.a[i], "video_quality");
                break;
            case 3:
                a(i, this.a[i], "video_stamp");
                break;
            case 7:
                a(i, this.a[i], "photo_size");
                break;
            case 8:
                a(i + 1, this.e[i + 1], "photo_burstRate");
                break;
            case 9:
                a(i + 1, this.e[i + 1], "photo_selfTimer");
                break;
            case 10:
                a(i, this.a[i], "photo_stamp");
                break;
            case 11:
                a(i, this.e[i], "Loop_Record");
                break;
            case 15:
                a(i, this.e[i], "Beep");
                break;
            case 16:
                a(i, this.e[i], "Status_LED");
                break;
            case 17:
                a(i, this.a[i], "TV_Mode");
                break;
            case 18:
                a(i, this.e[i], "Language");
                break;
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        com.aee.mokacam.service.a.a().d();
        try {
            TimeUnit.MILLISECONDS.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        f();
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.V = motionEvent.getRawX();
                this.W = motionEvent.getRawY();
                break;
            case 1:
                System.out.println("MotionEvent.ACTION_UP");
                float rawX = motionEvent.getRawX();
                float rawY = motionEvent.getRawY();
                if (Math.abs(rawX - this.V) <= 5.0f && Math.abs(rawY - this.W) <= 5.0f) {
                    finish();
                    break;
                }
                break;
        }
        return true;
    }
}
