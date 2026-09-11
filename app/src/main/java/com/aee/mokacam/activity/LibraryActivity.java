package com.aee.mokacam.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Message;
import androidx.collection.LruCache;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.constants.AeeConstants;
import com.aee.mokacam.widget.DotSelectedNumView;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.xutils.BuildConfig;
import wseemann.media.FFmpegMediaMetadataRetriever;

/* JADX INFO: loaded from: classes.dex */
public class LibraryActivity extends BaseActivity {
    public static int a = 0;
    List<com.aee.mokacam.bean.l> A;
    Bitmap C;
    FFmpegMediaMetadataRetriever D;
    protected com.aee.mokacam.widget.c e;
    GridView f;
    ImageView g;
    ImageView h;
    TextView k;
    DotSelectedNumView n;
    bn p;
    Dialog q;
    String r;
    LruCache<String, Bitmap> t;
    com.aee.mokacam.utils.e u;
    ImageView v;
    TextView w;
    com.nostra13.universalimageloader.core.DisplayImageOptions x;
    com.nostra13.universalimageloader.core.ImageLoader y;
    com.aee.mokacam.utils.z z;
    boolean l = false;
    boolean m = true;
    int o = 0;
    List<com.aee.mokacam.bean.g> s = new ArrayList();
    HashMap<Integer, bm> b = new HashMap<>();
    public int c = 0;
    public int d = 0;
    List<com.aee.mokacam.bean.l> B = new ArrayList();
    boolean E = false;

    /* JADX INFO: Access modifiers changed from: */
    public String a(boolean z, int i) {
        if (!z) {
            return String.valueOf(AeeApplication.a().h) + this.s.get(i).b + this.s.get(i).a();
        }
        File file = new File(this.s.get(i).a());
        return file.exists() ? file.getAbsolutePath() : BuildConfig.FLAVOR;
    }

    void a(TextView textView, TextView textView2) {
        textView.setOnClickListener(new bf(this));
        textView2.setOnClickListener(new bg(this));
    }

    /* JADX INFO: Access modifiers changed from: */
    public void a(com.aee.mokacam.bean.g gVar, String str) {
        if (!str.endsWith(".JPG")) {
            Message message = new Message();
            message.what = 32792;
            message.obj = str;
            this.i.sendMessage(message);
            return;
        }
        this.y.b();
        File fileA = com.aee.mokacam.utils.t.a(this, this.z.generate(str));
        if (fileA.exists()) {
            fileA.delete();
        }
    }

    void a(String str, ImageView imageView, int i) {
        bm bmVar = new bm(this, i);
        bmVar.execute(str);
        this.b.put(Integer.valueOf(i), bmVar);
    }

    /* JADX INFO: Access modifiers changed from: */
    public void a(String str, ImageView imageView, ImageView imageView2, int i) {
        Bitmap bitmap = this.t.get(str);
        if (bitmap == null) {
            a(str, imageView2, i);
        } else {
            imageView.setImageBitmap(bitmap);
            imageView2.setVisibility(0);
        }
    }

    boolean a(List<com.aee.mokacam.bean.g> list) {
        com.aee.mokacam.bean.l lVar;
        if (list == null || list.size() == 0) {
            return false;
        }
        this.B.clear();
        try {
            boolean z = false;
            for (com.aee.mokacam.bean.g gVar : list) {
                if ("camera_lib".equals(this.r)) {
                    String[] strArrSplit = gVar.b().split(" ")[0].split("-");
                    lVar = new com.aee.mokacam.bean.l(gVar.c(), String.valueOf(strArrSplit[0]) + "-" + strArrSplit[1] + "-" + strArrSplit[2]);
                } else {
                    lVar = new com.aee.mokacam.bean.l(gVar.c(), gVar.b());
                }
                this.B.add(lVar);
                z = true;
            }
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    Bitmap b(String str) {
        try {
            try {
                this.D = new FFmpegMediaMetadataRetriever();
                this.D.setDataSource(str);
                Bitmap frameAtTime = this.D.getFrameAtTime(1000L, 2);
                if (frameAtTime != null && frameAtTime.getWidth() > 640) {
                    this.C = ThumbnailUtils.extractThumbnail(frameAtTime, 80, 80, 2);
                }
                return this.C;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                this.D.release();
                return null;
            }
        } finally {
            this.D.release();
        }
    }

    List<com.aee.mokacam.bean.l> b(List<com.aee.mokacam.bean.l> list) {
        HashMap map = new HashMap();
        ListIterator<com.aee.mokacam.bean.l> listIterator = list.listIterator();
        int i = 1;
        while (listIterator.hasNext()) {
            com.aee.mokacam.bean.l next = listIterator.next();
            String strA = next.a();
            if (map.containsKey(strA)) {
                next.a(((Integer) map.get(strA)).intValue());
            } else {
                next.a(i);
                map.put(strA, Integer.valueOf(i));
                i++;
            }
        }
        return list;
    }

    void b() {
        this.f = (GridView) findViewById(R.id.library_gv);
        this.g = (ImageView) findViewById(R.id.library_delete_iv);
        this.h = (ImageView) findViewById(R.id.library_right_iv);
        this.k = (TextView) findViewById(R.id.select_tv);
        this.n = (DotSelectedNumView) findViewById(R.id.dotSelectedNumView);
        this.v = (ImageView) findViewById(R.id.iv_lib_back);
        this.w = (TextView) findViewById(R.id.tv_done);
    }

    void c() {
        e();
        d();
        this.x = new com.nostra13.universalimageloader.core.DisplayImageOptions.Builder().showStubImage(R.drawable.loading_libpic).imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2).cacheInMemory(true).cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).build();
        this.y = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        this.z = new com.aee.mokacam.utils.z();
        this.r = getIntent().getStringExtra("fromWhere");
        if ("camera_lib".equals(this.r)) {
            this.s = AeeApplication.a().r;
            this.h.setImageResource(R.drawable.btn_download_n);
        } else {
            this.h.setImageResource(R.drawable.library_share_unpressed);
            AeeApplication.a().s = com.aee.mokacam.utils.t.a(AeeConstants.a);
            this.s = AeeApplication.a().s;
        }
        if (a(this.s)) {
            this.A = b(this.B);
            this.p = new bn(this, null);
            this.f.setAdapter((ListAdapter) this.p);
        }
    }

    void d() {
        File fileA = com.aee.mokacam.utils.t.a(this.j, "videoThumbnailCache");
        if (!fileA.exists()) {
            fileA.mkdirs();
        }
        try {
            this.u = com.aee.mokacam.utils.e.a(fileA, com.aee.mokacam.utils.a.c(this.j), 1, 10485760L);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void e() {
        this.t = new ay(this, ((int) Runtime.getRuntime().maxMemory()) / 8);
    }

    void f() {
        n();
        m();
        j();
        i();
        h();
        g();
    }

    void g() {
        this.w.setOnClickListener(new bb(this));
    }

    void h() {
        this.v.setOnClickListener(new bc(this));
    }

    void i() {
        this.h.setOnClickListener(new bd(this));
    }

    void j() {
        this.g.setOnClickListener(new be(this));
    }

    /* JADX INFO: Access modifiers changed from: */
    public void k() {
        View viewInflate = View.inflate(this, R.layout.deletedialog_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(viewInflate);
        this.q = builder.create();
        Window window = this.q.getWindow();
        window.setGravity(80);
        window.setWindowAnimations(R.style.DeleteDialogStyle);
        this.q.show();
        a((TextView) viewInflate.findViewById(R.id.confirmdelete_tv), (TextView) viewInflate.findViewById(R.id.canceldelete_tv));
    }

    void l() {
        if (this.e == null) {
            this.e = new com.aee.mokacam.widget.c(this.j);
        }
        this.e.setCanceledOnTouchOutside(false);
        this.e.a(this.o);
        this.e.b(0);
        this.e.show();
    }

    void m() {
        this.f.setOnItemClickListener(new bk(this));
        this.f.setOnScrollListener(new com.nostra13.universalimageloader.core.listener.PauseOnScrollListener(this.y, false, true));
        this.f.setOnItemLongClickListener(new az(this));
    }

    void n() {
        this.k.setOnClickListener(new ba(this));
    }

    /* JADX INFO: Access modifiers changed from: */
    public void o() {
        if (this.o == 0) {
            this.g.setImageResource(R.drawable.library_delete_unpressed);
            if ("camera_lib".equals(this.r)) {
                this.h.setImageResource(R.drawable.btn_download_n);
                return;
            } else {
                this.h.setImageResource(R.drawable.library_share_unpressed);
                return;
            }
        }
        this.g.setImageResource(R.drawable.library_delete_pressed);
        if ("camera_lib".equals(this.r)) {
            this.h.setImageResource(R.drawable.btn_download_h);
        } else if (this.o == 1) {
            this.h.setImageResource(R.drawable.library_share_pressed);
        } else {
            this.h.setImageResource(R.drawable.library_share_unpressed);
        }
    }

    protected void a() {
        bm bmVar;
        l();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.s.size()) {
                new Thread(new bh(this)).start();
                return;
            }
            com.aee.mokacam.bean.g gVar = this.s.get(i2);
            if (gVar.a().endsWith(".JPG") && gVar.d() && gVar.h != null) {
                this.y.a(gVar.h);
            } else if (gVar.a().endsWith(".MP4") && gVar.d() && (bmVar = this.b.get(Integer.valueOf(i2))) != null) {
                bmVar.cancel(true);
            }
            i = i2 + 1;
        }
    }

    public void a(String str) {
        try {
            this.t.evictAll();
            this.u.c(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean a(String str, OutputStream outputStream) {
        Bitmap bitmapDecodeStream = null;
        try {
            com.aee.mokacam.utils.j jVarA = this.u.a(com.aee.mokacam.utils.o.a(str));
            bitmapDecodeStream = jVarA != null ? BitmapFactory.decodeStream(jVarA.a(0)) : b(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmapExtractThumbnail = ThumbnailUtils.extractThumbnail(bitmapDecodeStream, 80, 80, 2);
        boolean zCompress = bitmapExtractThumbnail != null ? bitmapExtractThumbnail.compress(Bitmap.CompressFormat.PNG, 100, outputStream) : false;
        if (bitmapDecodeStream != null) {
            bitmapDecodeStream.recycle();
        }
        return zCompress;
    }

    public boolean b(String str, OutputStream outputStream) {
        return ThumbnailUtils.extractThumbnail(b(str), 80, 80, 2).compress(Bitmap.CompressFormat.PNG, 100, outputStream);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 32792:
                a(com.aee.mokacam.utils.o.a((String) message.obj));
                break;
            case 32793:
                if (this.d != 0) {
                    Toast.makeText(this, String.valueOf(getResources().getString(R.string.delete_failed)) + " " + this.d, 0).show();
                    this.d = 0;
                }
                Iterator<com.aee.mokacam.bean.g> it = this.s.iterator();
                while (it.hasNext()) {
                    if (it.next().d()) {
                        it.remove();
                    }
                }
                if (this.e.isShowing()) {
                    this.e.hide();
                }
                this.B.clear();
                if (this.s.size() == 0) {
                    this.p.notifyDataSetChanged();
                } else if (a(this.s)) {
                    this.A = b(this.B);
                    this.p = new bn(this, null);
                    this.f.setAdapter((ListAdapter) this.p);
                }
                if (this.o != 0) {
                    this.n.setSelectedNum(this.o);
                } else {
                    this.n.setVisibility(8);
                    this.g.setImageResource(R.drawable.library_delete_unpressed);
                    if (!"camera_lib".equals(this.r)) {
                        this.h.setImageResource(R.drawable.library_share_unpressed);
                    } else {
                        this.h.setImageResource(R.drawable.btn_download_n);
                    }
                }
                break;
        }
        return super.handleMessage(message);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (com.aee.mokacam.utils.a.b(this)) {
            this.f.setNumColumns(6);
        } else {
            this.f.setNumColumns(4);
        }
        this.p.notifyDataSetChanged();
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_library);
        b();
        c();
        f();
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
        bm bmVar;
        if (this.s != null) {
            for (int i = 0; i < this.s.size(); i++) {
                this.s.get(i).g = false;
            }
            for (int i2 = 0; i2 < this.s.size(); i2++) {
                com.aee.mokacam.bean.g gVar = this.s.get(i2);
                if ((gVar.a.endsWith(".JPG") || gVar.a.endsWith(".jpg")) && gVar.h != null) {
                    this.y.a(gVar.h);
                } else if ((gVar.a.endsWith(".MP4") || gVar.a.endsWith(".mp4")) && (bmVar = this.b.get(Integer.valueOf(i2))) != null) {
                    bmVar.cancel(true);
                }
            }
        }
        a = 0;
        this.y.b();
        super.onDestroy();
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (com.aee.mokacam.utils.a.b(this)) {
            this.f.setNumColumns(6);
        } else {
            this.f.setNumColumns(4);
        }
        if (this.E) {
            return;
        }
        this.E = false;
        this.B.clear();
        AeeApplication.a().s = com.aee.mokacam.utils.t.a(AeeConstants.a);
        if ("local_lib".equals(this.r)) {
            this.s = AeeApplication.a().s;
            if (AeeApplication.a().s.size() == 0 && this.p != null) {
                this.A.clear();
                this.p.notifyDataSetChanged();
                return;
            }
        } else if (AeeApplication.a().r != null && AeeApplication.a().r.size() == 0 && this.p != null) {
            this.A.clear();
            this.p.notifyDataSetChanged();
            return;
        }
        if (a(this.s)) {
            this.A = b(this.B);
            this.p = new bn(this, null);
            this.f.setColumnWidth(6);
            this.f.setAdapter((ListAdapter) this.p);
            this.f.setSelection(a);
        }
    }
}
