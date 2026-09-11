package com.aee.mokacam.activity;

import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.constants.AeeConstants;
import com.aee.mokacam.widget.PinchImageView;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class da extends PagerAdapter {
    final /* synthetic */ ShowPicOrVideoActivity a;
    private List<com.aee.mokacam.bean.g> b;

    public da(ShowPicOrVideoActivity showPicOrVideoActivity, List<com.aee.mokacam.bean.g> list) {
        this.a = showPicOrVideoActivity;
        this.b = list;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        this.a.b = false;
        this.a.i();
        this.a.h();
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        if (this.b != null) {
            return this.b.size();
        }
        return 0;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        this.a.f = i;
        View viewInflate = View.inflate(this.a, R.layout.item_showpicorvideopager, null);
        FrameLayout frameLayout = (FrameLayout) viewInflate.findViewById(R.id.fl_video);
        FrameLayout frameLayout2 = (FrameLayout) viewInflate.findViewById(R.id.fl_image);
        this.a.R = (ProgressBar) viewInflate.findViewById(R.id.video_loading_pb);
        String strA = this.b.get(i).a();
        this.a.p = String.valueOf(AeeConstants.a) + File.separator + this.b.get(i).f();
        if (strA.endsWith(".JPG")) {
            frameLayout.setVisibility(8);
            this.a.R.setVisibility(8);
            frameLayout2.setVisibility(0);
            this.a.Z.setVisibility(0);
            this.a.F.setVisibility(0);
            this.a.N.setVisibility(8);
            this.a.P.setVisibility(8);
            this.a.X.setVisibility(0);
            this.a.Y.setVisibility(0);
            PinchImageView pinchImageView = (PinchImageView) viewInflate.findViewById(R.id.pv_show);
            if ("camera_lib".equals(this.a.G)) {
                this.a.U.setImageResource(R.drawable.btn_download_h);
            } else {
                this.a.U.setImageResource(R.drawable.library_share_pressed);
            }
            if (new File(this.a.p).exists()) {
                com.nostra13.universalimageloader.core.g.a().a("file:///" + this.a.p, pinchImageView);
            } else {
                com.nostra13.universalimageloader.core.g.a().a(String.valueOf(AeeApplication.a().h) + this.b.get(i).b + strA, pinchImageView);
            }
            this.a.d = true;
            this.a.a(pinchImageView, this.a.T, this.a.U, this.a.V);
        } else {
            this.a.Z.setVisibility(0);
            this.a.F.setVisibility(0);
            this.a.N.setVisibility(0);
            this.a.P.setVisibility(0);
            this.a.X.setVisibility(8);
            this.a.Y.setVisibility(8);
            this.a.k.setProgress(0);
            this.a.l.setText("00:00");
            this.a.o.setImageResource(R.drawable.video_play);
            this.a.w = true;
            this.a.d = false;
            this.a.W.setOnClickListener(new db(this));
            if ("camera_lib".equals(this.a.G)) {
                this.a.S = String.valueOf(AeeApplication.a().h) + this.b.get(i).b + strA;
            } else {
                this.a.S = strA;
            }
            this.a.initVideoPlay(viewInflate);
        }
        viewGroup.addView(viewInflate);
        return viewInflate;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
