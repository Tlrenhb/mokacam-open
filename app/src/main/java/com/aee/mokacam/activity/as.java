package com.aee.mokacam.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
class as extends BaseAdapter {
    final /* synthetic */ DownLoadActivity a;
    private int b;

    private as(DownLoadActivity downLoadActivity) {
        this.a = downLoadActivity;
        this.b = 0;
    }

    /* synthetic */ as(DownLoadActivity downLoadActivity, as asVar) {
        this(downLoadActivity);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.a.c.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        au auVar;
        if (view == null) {
            auVar = new au(this, null);
            view = View.inflate(this.a, R.layout.item_list_download, null);
            auVar.a = (ImageView) view.findViewById(R.id.iv_file_image);
            auVar.b = (TextView) view.findViewById(R.id.tv_file_name);
            auVar.c = (TextView) view.findViewById(R.id.tv_file_state);
            auVar.d = (TextView) view.findViewById(R.id.tv_file_progress);
            auVar.e = (ProgressBar) view.findViewById(R.id.savetolocation_progressbar);
            auVar.f = (ImageView) view.findViewById(R.id.download_cancel);
            view.setTag(auVar);
        } else {
            auVar = (au) view.getTag();
        }
        String str = String.valueOf(AeeApplication.a().h) + ((com.aee.mokacam.bean.g) this.a.c.get(i)).b + ((com.aee.mokacam.bean.g) this.a.c.get(i)).a;
        auVar.b.setText(((com.aee.mokacam.bean.g) this.a.c.get(i)).a);
        if (str.endsWith(".JPG")) {
            com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(str, auVar.a);
        } else {
            auVar.a.setImageResource(R.drawable.download_video_pic);
        }
        auVar.f.setVisibility(0);
        int iG = ((com.aee.mokacam.bean.g) this.a.c.get(i)).g();
        if (iG == 2) {
            auVar.e.setVisibility(0);
            auVar.d.setVisibility(0);
            auVar.e.setMax((int) ((com.aee.mokacam.bean.g) this.a.c.get(i)).c);
            auVar.e.setProgress((int) ((com.aee.mokacam.bean.g) this.a.c.get(i)).d);
            auVar.d.setText(String.valueOf((((com.aee.mokacam.bean.g) this.a.c.get(i)).d * 100) / ((com.aee.mokacam.bean.g) this.a.c.get(i)).c) + "%");
            auVar.c.setText(R.string.load_loading);
        } else if (iG == 3) {
            auVar.e.setVisibility(8);
            auVar.d.setVisibility(8);
            auVar.c.setText(R.string.load_finish);
            auVar.f.setVisibility(8);
        } else if (iG == 1) {
            auVar.e.setVisibility(8);
            auVar.d.setVisibility(8);
            auVar.c.setText(R.string.load_waitting);
        } else if (iG == 6) {
            auVar.e.setVisibility(8);
            auVar.d.setVisibility(8);
            auVar.c.setText(R.string.load_cancel);
            auVar.f.setVisibility(8);
        } else if (iG == 5) {
            auVar.e.setVisibility(8);
            auVar.d.setVisibility(8);
            auVar.c.setText(R.string.load_fail);
            auVar.f.setVisibility(8);
        }
        auVar.f.setOnClickListener(new at(this, i));
        return view;
    }
}
