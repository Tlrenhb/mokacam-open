package com.aee.mokacam.activity;

import android.R;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.constants.AeeConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class AeeAppSettingActivity extends BaseActivity {
    public ProgressDialog b;
    RelativeLayout d;
    RelativeLayout e;
    TextView f;
    TextView g;
    ImageView h;
    RelativeLayout k;
    String l;
    RelativeLayout m;
    RelativeLayout n;
    String o = BuildConfig.FLAVOR;
    int[] a = new int[10];
    public boolean c = false;

    void b(String str) {
        long jA = a();
        if (this.b == null) {
            this.b = new ProgressDialog(this);
        }
        this.b.setIcon(R.drawable.ic_delete);
        this.b.setTitle(str);
        this.b.setProgressStyle(1);
        this.b.setCanceledOnTouchOutside(false);
        this.b.setMax((int) jA);
        this.b.setProgress(0);
        this.b.show();
    }

    /* JADX INFO: Access modifiers changed from: */
    public void c() {
        b();
        new Thread(new a(this)).start();
    }

    void c(String str) {
        com.aee.mokacam.widget.e eVar = new com.aee.mokacam.widget.e((Context) this, false);
        eVar.show();
        eVar.d(com.aee.mokacam.R.string.cancel);
        eVar.c(com.aee.mokacam.R.string.sure);
        eVar.a(str);
        eVar.a(new c(this, eVar));
    }

    void d() {
        this.d = (RelativeLayout) findViewById(com.aee.mokacam.R.id.rl_version);
        this.e = (RelativeLayout) findViewById(com.aee.mokacam.R.id.clear_appcache);
        this.f = (TextView) findViewById(com.aee.mokacam.R.id.tv_version);
        this.g = (TextView) findViewById(com.aee.mokacam.R.id.tv_appcachesize);
        this.h = (ImageView) findViewById(com.aee.mokacam.R.id.iv_settingback);
        this.k = (RelativeLayout) findViewById(com.aee.mokacam.R.id.rl_changewifi);
        this.m = (RelativeLayout) findViewById(com.aee.mokacam.R.id.rl_downloadmap);
        this.n = (RelativeLayout) findViewById(com.aee.mokacam.R.id.rl_firmware_update);
        if (AeeApplication.a().c()) {
            this.m.setVisibility(0);
        } else {
            this.m.setVisibility(4);
        }
    }

    void e() {
        try {
            this.g.setText(b(com.aee.mokacam.utils.t.a(this)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.l = getIntent().getStringExtra("main_go");
        if (this.l.equals("main_drone")) {
            this.k.setVisibility(0);
        } else if (this.l.equals("a10_drone")) {
            this.k.setVisibility(8);
            this.m.setVisibility(8);
            this.n.setVisibility(8);
        } else if (this.l.equals("main_camera")) {
            this.k.setVisibility(8);
            this.m.setVisibility(8);
        } else {
            this.k.setVisibility(8);
            this.m.setVisibility(8);
        }
        this.f.setText("V" + com.aee.mokacam.utils.a.a(this));
    }

    void f() {
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.n.setOnClickListener(this);
    }

    public long a() {
        String[] strArr = {"aee_encode.bin", "aee_Remoter.bin", "aee_app.bin", "aee_addtion.bin"};
        long jA = 0;
        for (int i = 0; i < 4; i++) {
            jA += a(String.valueOf(AeeConstants.f) + "/" + strArr[i]);
        }
        return (jA / 1) + 1;
    }

    public long a(File file) {
        long jA;
        Exception e;
        try {
            File[] fileArrListFiles = file.listFiles();
            jA = 0;
            for (int i = 0; i < fileArrListFiles.length; i++) {
                try {
                    jA += fileArrListFiles[i].isDirectory() ? a(fileArrListFiles[i]) : fileArrListFiles[i].length();
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                }
            }
        } catch (Exception e3) {
            jA = 0;
            e = e3;
        }
        return jA;
    }

    public long a(String str) {
        long size = 0;
        FileChannel channel = null;
        try {
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                channel = new FileInputStream(file).getChannel();
                size = channel.size();
            }
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                }
            }
        } catch (FileNotFoundException e2) {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e3) {
                }
            }
        } catch (IOException e4) {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e5) {
                }
            }
        } catch (Throwable th) {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e6) {
                }
            }
            throw th;
        }
        return size;
    }

    public String a(double d) {
        double d2 = d / 1024.0d;
        if (d2 < 1.0d) {
            return String.valueOf(d2) + "KB";
        }
        double d3 = d2 / 1024.0d;
        if (d3 < 1.0d) {
            return String.valueOf(new BigDecimal(Double.toString(d2)).setScale(2, 4).toPlainString()) + "KB";
        }
        double d4 = d3 / 1024.0d;
        if (d4 < 1.0d) {
            return String.valueOf(new BigDecimal(Double.toString(d3)).setScale(2, 4).toPlainString()) + "MB";
        }
        double d5 = d4 / 1024.0d;
        return d5 < 1.0d ? String.valueOf(new BigDecimal(Double.toString(d4)).setScale(2, 4).toPlainString()) + "GB" : String.valueOf(new BigDecimal(d5).setScale(2, 4).toPlainString()) + "TB";
    }

    public String b(File file) {
        return a(a(file));
    }

    public void b() {
        b(getString(com.aee.mokacam.R.string.appsetting_firmwareupdate));
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.view.View.OnClickListener
    @SuppressLint({"ShowToast"})
    public void onClick(View view) {
        switch (view.getId()) {
            case com.aee.mokacam.R.id.iv_settingback /* 2131427477 */:
                if (AeeApplication.a().bi) {
                    if (AeeApplication.a().a == 2) {
                        com.aee.mokacam.service.w.a().b();
                    } else {
                        com.aee.mokacam.service.v.a().b();
                    }
                }
                finish();
                break;
            case com.aee.mokacam.R.id.clear_appcache /* 2131427478 */:
                try {
                    com.nostra13.universalimageloader.core.ImageLoader.getInstance().clearMemoryCache();
                    File[] fileArrListFiles = com.aee.mokacam.utils.t.a(this, "videoThumbnailCache").listFiles();
                    if (fileArrListFiles.length != 0) {
                        for (File file : fileArrListFiles) {
                            file.delete();
                        }
                    }
                    this.g.setText(b(com.aee.mokacam.utils.t.a(this)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                com.aee.mokacam.utils.w.a(com.aee.mokacam.R.string.clearcache_finish, true);
                break;
            case com.aee.mokacam.R.id.rl_changewifi /* 2131427482 */:
                if (AeeApplication.a().U > 0) {
                    startActivity(new Intent(this, (Class<?>) WifiPswChangeActivity.class));
                }
                break;
            case com.aee.mokacam.R.id.rl_firmware_update /* 2131427483 */:
                if (AeeApplication.a().a != 2 && AeeApplication.a().aQ) {
                    c(String.valueOf(getString(com.aee.mokacam.R.string.appsetting_firmwareupdate)) + "?");
                    break;
                }
                break;
            case com.aee.mokacam.R.id.rl_version /* 2131427484 */:
                Toast.makeText(this, com.aee.mokacam.R.string.soft_updating_check, 0).show();
                new Thread(new d(this)).start();
                break;
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(com.aee.mokacam.R.layout.activity_appsetting);
        d();
        e();
        f();
        if (AeeApplication.a().a == 2) {
            com.aee.mokacam.service.w.a();
        } else {
            if (AeeApplication.a().bi) {
                return;
            }
            com.aee.mokacam.service.v.a();
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityHoneycomb, android.app.Activity, android.view.LayoutInflater.Factory2
    public /* bridge */ /* synthetic */ View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(view, str, context, attributeSet);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity, android.view.LayoutInflater.Factory
    public /* bridge */ /* synthetic */ View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }
}
