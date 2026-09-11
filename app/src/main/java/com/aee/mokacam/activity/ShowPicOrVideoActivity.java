package com.aee.mokacam.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import androidx.core.content.FileProvider;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.SendMsg;
import com.aee.mokacam.constants.AeeConstants;
import com.aee.mokacam.photoview.LazyViewPager;
import com.aee.mokacam.widget.PinchImageView;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import org.xutils.BuildConfig;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

/* JADX INFO: loaded from: classes.dex */
public class ShowPicOrVideoActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener {
    Animation A;
    int B;
    int C;
    FrameLayout D;
    da E;
    RelativeLayout F;
    String G;
    TimerTask H;
    cy I;
    Callback.Cancelable J;
    SurfaceHolder L;
    Timer M;
    LinearLayout N;
    boolean O;
    LinearLayout P;
    long Q;
    ProgressBar pb;
    String S;
    ImageView T;
    ImageView U;
    TextView V;
    TextView W;
    RelativeLayout X;
    LinearLayout Y;
    RelativeLayout Z;
    int ad;
    int ae;
    LazyViewPager e;
    int f;
    List<com.aee.mokacam.bean.g> g;
    SurfaceView h;
    SeekBar k;
    TextView l;
    ImageView m;
    ImageView n;
    ImageView o;
    String p;
    com.aee.mokacam.utils.e s;
    MediaPlayer t;
    long u;
    Animation x;
    Animation y;
    Animation z;
    boolean q = true;
    int r = 1;
    int v = 0;
    boolean w = true;
    boolean K = false;
    public boolean a = false;
    public boolean b = false;
    public boolean c = false;
    boolean d = false;
    boolean aa = false;
    boolean ab = false;
    long ac = 0;

    public static String a(long j) {
        boolean z = j < 0;
        long jAbs = Math.abs(j) / 1000;
        int i = (int) (jAbs % 60);
        long j2 = jAbs / 60;
        int i2 = (int) (j2 % 60);
        long j3 = j2 / 60;
        int i3 = (int) j3;
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        decimalFormat.applyPattern("00");
        if (j3 > 0) {
            return String.valueOf(z ? "-" : BuildConfig.FLAVOR) + i3 + ":" + decimalFormat.format(i2) + ":" + decimalFormat.format(i);
        }
        return String.valueOf(z ? "-" : BuildConfig.FLAVOR) + i2 + ":" + decimalFormat.format(i);
    }

    Callback.Cancelable a(String str, String str2, Callback.CommonCallback<File> commonCallback) {
        if (new File(str2).exists()) {
            return null;
        }
        RequestParams requestParams = new RequestParams(str);
        requestParams.setSaveFilePath(str2);
        return org.xutils.x.http().get(requestParams, commonCallback);
    }

    /* JADX INFO: Access modifiers changed from: */
    public void a(int i) {
        File file = new File(String.valueOf(AeeConstants.a) + File.separator + this.g.get(i).f() + ".tmp");
        if (file.exists()) {
            file.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: */
    public void a(boolean z, int i) {
        if (!z) {
            com.aee.mokacam.utils.w.a(R.string.delete_failed, true);
            return;
        }
        com.aee.mokacam.bean.g.a("/tmp/SD0/moka/" + this.g.get(i).b + this.g.get(i).a, AeeApplication.a().r);
        this.v = i;
        if (this.g.size() == 0) {
            finish();
            return;
        }
        if (this.v >= this.g.size()) {
            this.v = 0;
        }
        this.i.sendEmptyMessage(32781);
        com.aee.mokacam.utils.w.a(R.string.delete_success, true);
    }

    String b(int i) {
        String str = String.valueOf(AeeConstants.a) + File.separator + this.g.get(i).a();
        return new File(str).exists() ? "file:///" + str : String.valueOf(AeeApplication.a().h) + this.g.get(i).b + this.g.get(i).a();
    }

    void b() {
        this.e = (LazyViewPager) findViewById(R.id.showpic_viewpager);
        this.T = (ImageView) findViewById(R.id.iv_showPic_del);
        this.U = (ImageView) findViewById(R.id.iv_showPic_download);
        this.V = (TextView) findViewById(R.id.tv_pic_done);
        this.k = (SeekBar) findViewById(R.id.seekbar_video);
        this.o = (ImageView) findViewById(R.id.iv_video_playorpause);
        this.W = (TextView) findViewById(R.id.tv_video_done);
        this.l = (TextView) findViewById(R.id.tv_video_time);
        this.m = (ImageView) findViewById(R.id.iv_video_delete);
        this.n = (ImageView) findViewById(R.id.iv_video_download);
        this.N = (LinearLayout) findViewById(R.id.show_video_title_ll);
        this.P = (LinearLayout) findViewById(R.id.video_play_bottom);
        this.X = (RelativeLayout) findViewById(R.id.show_pic_title_rl);
        this.Y = (LinearLayout) findViewById(R.id.pic_bottom);
        this.Z = (RelativeLayout) findViewById(R.id.rl_title);
        this.F = (RelativeLayout) findViewById(R.id.rl_bottom);
    }

    void c() {
        q();
        this.f = getIntent().getIntExtra("position", 0);
        this.G = getIntent().getStringExtra("fromWhere");
        if ("camera_lib".equals(this.G)) {
            this.g = AeeApplication.a().r;
        } else {
            this.g = AeeApplication.a().s;
        }
        this.E = new da(this, this.g);
        this.e.setAdapter(this.E);
        this.e.setCurrentItem(this.f);
        this.x = AnimationUtils.loadAnimation(this, R.anim.slide_top_in);
        this.y = AnimationUtils.loadAnimation(this, R.anim.slide_top_out);
        this.z = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_in);
        this.A = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_out);
    }

    void d() {
        this.L = this.h.getHolder();
        this.L.addCallback(new cz(this, null));
        this.L.setFormat(1);
    }

    void e() {
        this.o.setOnClickListener(this);
        this.k.setOnSeekBarChangeListener(this);
        this.N.setOnTouchListener(new cs(this));
    }

    void f() {
        int width;
        int height = 0;
        this.O = com.aee.mokacam.utils.a.b(this);
        if (this.O) {
            j();
        } else {
            if (this.D != null) {
                width = this.D.getWidth();
                height = this.D.getHeight();
            } else {
                width = 0;
            }
            float f = width / this.B;
            float f2 = height / this.C;
            if (f < f2) {
                this.L.setFixedSize((int) (this.B * f), (int) (f * this.C));
            } else {
                this.L.setFixedSize((int) (this.B * f2), (int) (f2 * this.C));
            }
            this.h.invalidate();
        }
        this.t.start();
        m();
    }

    void g() {
        i();
        try {
            this.t = new MediaPlayer(this);
            if ("camera_lib".equals(this.G)) {
                this.t.setDataSource(this.S);
            } else {
                this.t.setDataSource(this.p);
            }
            this.t.setDisplay(this.L);
            this.t.prepareAsync();
            this.t.setOnBufferingUpdateListener(this);
            this.t.setOnCompletionListener(this);
            this.t.setOnPreparedListener(this);
            this.t.setOnVideoSizeChangedListener(this);
            setVolumeControlStream(3);
            this.t.setOnInfoListener(new ct(this));
        } catch (Exception e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: */
    public void h() {
        if (this.t != null) {
            this.t.release();
            this.t = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: */
    public void i() {
        this.c = false;
        this.a = false;
    }

    void j() {
        int width;
        int height = 0;
        if (this.D != null) {
            width = this.D.getWidth();
            height = this.D.getHeight();
        } else {
            width = 0;
        }
        float f = width / this.ad;
        float f2 = height / this.ae;
        if (f < f2) {
            this.L.setFixedSize((int) (this.ad * f2), (int) (f2 * this.ae));
        } else {
            this.L.setFixedSize((int) (this.ae * f), (int) (f * this.ad));
        }
        this.h.invalidate();
    }

    void k() {
        int width;
        int height = 0;
        if (this.D != null) {
            width = this.D.getWidth();
            height = this.D.getHeight();
        } else {
            width = 0;
        }
        float f = width / this.ad;
        float f2 = height / this.ae;
        if (f > f2) {
            this.L.setFixedSize((int) (this.ad * f), (int) (f * this.ae));
        } else {
            this.L.setFixedSize((int) (this.ae * f2), (int) (f2 * this.ad));
        }
        this.h.invalidate();
    }

    void l() {
        int height;
        int width = 0;
        if (this.D != null) {
            height = this.D.getHeight();
            width = this.D.getWidth();
        } else {
            height = 0;
        }
        float f = height / this.B;
        float f2 = width / this.C;
        if (f < f2) {
            this.L.setFixedSize((int) (this.B * f), (int) (f * this.C));
        } else {
            this.L.setFixedSize((int) (this.B * f2), (int) (f2 * this.C));
        }
        this.h.invalidate();
    }

    void m() {
        if (this.M == null) {
            this.M = new Timer();
        }
        if (this.H == null) {
            this.H = new cu(this);
            this.M.schedule(this.H, 0L, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: */
    public void n() {
        if (new File(String.valueOf(AeeConstants.a) + File.separator + this.g.get(this.f).a()).exists()) {
            com.aee.mokacam.utils.w.a(R.string.singleisdownloadedwarning, true);
        } else {
            o();
        }
    }

    void o() {
        if (!this.q) {
            com.aee.mokacam.utils.w.a(R.string.singledownloadwarning, true);
            return;
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.remote);
        if (this.g.get(this.f).a().endsWith(".JPG")) {
            remoteViews.setImageViewBitmap(R.id.iv_notification, com.nostra13.universalimageloader.core.ImageLoader.getInstance().loadImageSync(String.valueOf(AeeApplication.a().h) + this.g.get(this.f).b + this.g.get(this.f).a, new com.nostra13.universalimageloader.core.assist.ImageSize(com.aee.mokacam.utils.d.a(this, 61.0f), com.aee.mokacam.utils.d.a(this, 61.0f))));
            remoteViews.setImageViewResource(R.id.vedioflag, 0);
        } else {
            remoteViews.setImageViewBitmap(R.id.iv_notification, p());
            remoteViews.setImageViewResource(R.id.vedioflag, R.drawable.play_lib);
        }
        remoteViews.setTextViewText(R.id.tv_notification_title, this.g.get(this.f).a);
        builder.setSmallIcon(R.drawable.btn_download_h);
        builder.setContent(remoteViews);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        String str = String.valueOf(AeeConstants.a) + File.separator + this.g.get(this.f).a();
        String str2 = String.valueOf(AeeApplication.a().h) + this.g.get(this.f).b + this.g.get(this.f).a;
        this.I = new cy(this, remoteViews, notificationManager, builder);
        this.J = a(str2, str, this.I);
        notificationManager.notify(this.r, builder.build());
        com.aee.mokacam.utils.w.a(R.string.singlestartDownlad, true);
    }

    Bitmap p() {
        try {
            com.aee.mokacam.utils.j jVarA = this.s.a(com.aee.mokacam.utils.o.a(b(this.f)));
            if (jVarA != null) {
                return BitmapFactory.decodeStream(jVarA.a(0));
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    void q() {
        File fileA = com.aee.mokacam.utils.t.a(this.j, "videoThumbnailCache");
        if (!fileA.exists()) {
            fileA.mkdirs();
        }
        try {
            this.s = com.aee.mokacam.utils.e.a(fileA, com.aee.mokacam.utils.a.c(this.j), 1, 10485760L);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void r() {
        com.aee.mokacam.widget.e eVar = new com.aee.mokacam.widget.e((Context) this, false);
        eVar.show();
        eVar.d(R.string.cancel);
        eVar.c(R.string.sure);
        eVar.b(R.string.download_cancel_warning);
        eVar.a(new cn(this, eVar));
    }

    protected void a() {
        String str = String.valueOf(AeeConstants.a) + File.separator + this.g.get(this.f).f();
        String strF = this.g.get(this.f).f();
        // The original used Mob ShareSDK (WeChat/QQ/Weibo one-key share); the
        // system share sheet covers the same destinations on modern Android.
        File shareFile = new File(str);
        Uri shareUri = FileProvider.getUriForFile(this,
                getPackageName() + ".fileprovider", shareFile);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType(shareFile.getName().toUpperCase().endsWith(".MP4")
                ? "video/*" : "image/*");
        share.putExtra(Intent.EXTRA_STREAM, shareUri);
        share.putExtra(Intent.EXTRA_TEXT, strF);
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(share, getString(R.string.share)));
    }

    public void a(PinchImageView pinchImageView, ImageView imageView, ImageView imageView2, TextView textView) {
        pinchImageView.setOnClickListener(new cm(this));
        imageView.setOnClickListener(new co(this));
        imageView2.setOnClickListener(new cq(this));
        textView.setOnClickListener(new cr(this));
    }

    protected void a(String str) {
        if (!this.d && this.t != null) {
            this.t.stop();
            i();
            h();
        }
        if ("camera_lib".equals(this.G)) {
            com.aee.mokacam.service.a.a().a(new cx(this, str), new SendMsg(AeeConstants.k, str, null));
            return;
        }
        if (new File(str).delete()) {
            if (str.endsWith(".JPG")) {
                File fileA = com.aee.mokacam.utils.t.a(this, new com.aee.mokacam.utils.z().a(str));
                if (fileA.exists()) {
                    fileA.delete();
                }
            } else {
                try {
                    this.s.c(com.aee.mokacam.utils.o.a(str));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.g.remove(this.f);
            if (this.g.size() == 0) {
                finish();
                return;
            }
            this.v = this.f;
            this.E = new da(this, this.g);
            this.e.setAdapter(this.E);
            this.e.setCurrentItem(this.v);
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 32781:
                this.E = new da(this, this.g);
                this.e.setAdapter(this.E);
                this.e.setCurrentItem(this.v);
                break;
        }
        return super.handleMessage(message);
    }

    public void initVideoPlay(View view) {
        this.h = (SurfaceView) view.findViewById(R.id.surfaceview_video);
        if ("local_lib".equals(this.G)) {
            this.n.setImageResource(R.drawable.library_share_pressed);
        } else {
            this.n.setImageResource(R.drawable.btn_download_h);
        }
        this.D = (FrameLayout) view.findViewById(R.id.video_frame);
        this.h.getHolder().setKeepScreenOn(true);
        this.h.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.m.setOnClickListener(this);
        d();
        e();
    }

    @Override // io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_video_download /* 2131427611 */:
                if (!"camera_lib".equals(this.G)) {
                    a();
                } else {
                    n();
                }
                break;
            case R.id.iv_video_delete /* 2131427648 */:
                com.aee.mokacam.widget.e eVar = new com.aee.mokacam.widget.e((Context) this, false);
                eVar.show();
                eVar.b(R.string.sure_delete);
                eVar.d(R.string.cancel);
                eVar.c(R.string.sure);
                eVar.a(new cw(this, eVar));
                break;
            case R.id.iv_video_playorpause /* 2131427649 */:
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (this.Q == 0 || jCurrentTimeMillis - this.Q >= 1000) {
                    this.Q = jCurrentTimeMillis;
                    if (!this.b) {
                        this.pb.setVisibility(0);
                        i();
                        d();
                        g();
                        this.o.setImageResource(R.drawable.video_pause);
                        this.o.setEnabled(false);
                    } else if (!this.t.isPlaying()) {
                        if (this.ab) {
                            this.ab = false;
                            i();
                            if (this.t != null) {
                                this.t.releaseDisplay();
                            }
                            h();
                            d();
                            g();
                            this.t.seekTo(this.ac);
                            this.k.setProgress((int) this.ac);
                        } else {
                            this.t.start();
                        }
                        this.o.setImageResource(R.drawable.video_pause);
                    } else {
                        this.t.pause();
                        this.o.setImageResource(R.drawable.video_play);
                    }
                }
                break;
            case R.id.surfaceview_video /* 2131427735 */:
                if (!this.w) {
                    this.Z.startAnimation(this.x);
                    this.F.startAnimation(this.z);
                    this.F.setVisibility(0);
                    this.Z.setVisibility(0);
                    this.w = true;
                } else {
                    this.Z.startAnimation(this.y);
                    this.F.startAnimation(this.A);
                    this.F.setVisibility(8);
                    this.Z.setVisibility(8);
                    this.w = false;
                }
                break;
        }
    }

    @Override // io.vov.vitamio.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.b = false;
        if (this.M != null) {
            this.M.cancel();
            this.M = null;
            this.H.cancel();
            this.H = null;
        }
        this.k.setProgress(0);
        this.o.setImageResource(R.drawable.video_play);
        this.t.stop();
        this.t.release();
        this.t = null;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.O = com.aee.mokacam.utils.a.b(this);
        if (this.O && this.D != null && !this.d) {
            k();
            if (!this.Z.isShown()) {
                this.Z.startAnimation(this.x);
                this.F.startAnimation(this.z);
                this.Z.setVisibility(0);
                this.F.setVisibility(0);
            }
            this.w = true;
            return;
        }
        if (this.d) {
            return;
        }
        l();
        if (!this.Z.isShown()) {
            this.Z.startAnimation(this.x);
            this.F.startAnimation(this.z);
            this.Z.setVisibility(0);
            this.F.setVisibility(0);
        }
        this.N.setVisibility(0);
        this.P.setVisibility(0);
        this.w = true;
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Vitamio.isInitialized(getApplicationContext());
        com.aee.mokacam.utils.a.b(this);
        getWindow().addFlags(128);
        setContentView(R.layout.activity_showpicorvideo);
        b();
        c();
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
        this.Q = 0L;
        i();
        if (this.t != null) {
            this.t.releaseDisplay();
        }
        h();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.J != null) {
            r();
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.t != null && this.t.isPlaying()) {
            this.t.pause();
            this.o.setImageResource(R.drawable.video_play);
            this.ab = true;
            this.ac = this.t.getCurrentPosition();
        }
        if (this.K) {
            LibraryActivity.a = this.v;
        } else {
            LibraryActivity.a = this.f;
        }
    }

    @Override // io.vov.vitamio.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.c = true;
        this.o.setEnabled(true);
        this.ad = this.t.getVideoWidth();
        this.ae = this.t.getVideoHeight();
        if (this.c && this.a) {
            f();
            this.pb.setVisibility(8);
            this.u = this.t.getDuration();
            this.k.setMax((int) this.u);
            if (this.ac == 0) {
                this.l.setText(a(this.u));
            } else {
                this.t.seekTo(this.ac);
                this.k.setProgress((int) this.ac);
                a(this.u - this.ac);
            }
            this.ac = 0L;
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        this.aa = true;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        this.aa = false;
        int progress = seekBar.getProgress();
        if (this.t != null) {
            this.t.seekTo(progress);
        }
    }

    @Override // io.vov.vitamio.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        if (i == 0 || i2 == 0) {
            return;
        }
        this.a = true;
        this.b = true;
        this.B = i;
        this.C = i2;
        if (this.c && this.a) {
            f();
        }
    }
}
