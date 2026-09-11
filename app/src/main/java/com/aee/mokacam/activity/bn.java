package com.aee.mokacam.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
class bn extends BaseAdapter implements com.tonicartos.widget.stickygridheaders.t {
    final /* synthetic */ LibraryActivity a;

    private bn(LibraryActivity libraryActivity) {
        this.a = libraryActivity;
    }

    /* synthetic */ bn(LibraryActivity libraryActivity, bn bnVar) {
        this(libraryActivity);
    }

    @Override // com.tonicartos.widget.stickygridheaders.t
    public long a(int i) {
        return ((com.aee.mokacam.bean.l) this.a.A.get(i)).b();
    }

    @Override // com.tonicartos.widget.stickygridheaders.t
    public View a(int i, View view, ViewGroup viewGroup) {
        bl blVar;
        if (view == null) {
            bl blVar2 = new bl();
            view = View.inflate(this.a.j, R.layout.header, null);
            blVar2.a = (TextView) view.findViewById(R.id.header);
            view.setTag(blVar2);
            blVar = blVar2;
        } else {
            blVar = (bl) view.getTag();
        }
        blVar.a.setText(((com.aee.mokacam.bean.l) this.a.A.get(i)).a());
        return view;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.a.s != null) {
            return this.a.s.size();
        }
        return 0;
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
        bp bpVar;
        if (view == null) {
            view = View.inflate(this.a, R.layout.item_library, null);
            bpVar = new bp(this, null);
            bpVar.a = (ImageView) view.findViewById(R.id.item_selected_iv);
            bpVar.b = (ImageView) view.findViewById(R.id.item_selectedpre_iv);
            bpVar.c = (ImageView) view.findViewById(R.id.item_content_iv);
            bpVar.d = (ImageView) view.findViewById(R.id.iv_play);
            view.setTag(bpVar);
        } else {
            bpVar = (bp) view.getTag();
        }
        bpVar.d.setVisibility(8);
        bpVar.c.setImageResource(R.drawable.loading_libpic);
        if (((com.aee.mokacam.bean.g) this.a.s.get(i)).g) {
            bpVar.b.setVisibility(0);
        } else {
            bpVar.b.setVisibility(8);
        }
        if ("camera_lib".equals(this.a.r)) {
            String str = String.valueOf(AeeApplication.a().h) + ((com.aee.mokacam.bean.g) this.a.s.get(i)).b + ((com.aee.mokacam.bean.g) this.a.s.get(i)).a;
            if (str.endsWith(".JPG")) {
                bpVar.d.setVisibility(8);
                com.nostra13.universalimageloader.core.imageaware.ImageViewAware bVar = new com.nostra13.universalimageloader.core.imageaware.ImageViewAware(bpVar.c);
                ((com.aee.mokacam.bean.g) this.a.s.get(i)).h = bVar;
                this.a.y.displayImage(str, bVar, this.a.x, new bo(this, i));
            } else {
                String strA = this.a.a(false, i);
                bpVar.c.setTag(strA);
                bpVar.d.setTag(String.valueOf(strA) + "play");
                this.a.a(strA, bpVar.c, bpVar.d, i);
            }
        } else {
            String strA2 = ((com.aee.mokacam.bean.g) this.a.s.get(i)).a();
            if (strA2.endsWith(".JPG")) {
                bpVar.d.setVisibility(8);
                this.a.y.a("file:///" + strA2, bpVar.c, this.a.x, new bo(this, i));
            } else {
                String strA3 = this.a.a(true, i);
                bpVar.c.setTag(strA3);
                bpVar.d.setTag(String.valueOf(strA3) + "play");
                this.a.a(strA3, bpVar.c, bpVar.d, i);
            }
        }
        if (((com.aee.mokacam.bean.g) this.a.s.get(i)).e()) {
            bpVar.a.setVisibility(0);
        } else {
            bpVar.a.setVisibility(8);
        }
        return view;
    }
}
