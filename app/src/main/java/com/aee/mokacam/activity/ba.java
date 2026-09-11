package com.aee.mokacam.activity;

import android.view.View;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
class ba implements View.OnClickListener {
    final /* synthetic */ LibraryActivity a;

    ba(LibraryActivity libraryActivity) {
        this.a = libraryActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.a.m) {
            this.a.w.setVisibility(0);
            this.a.k.setTextColor(-7829368);
            this.a.k.setText(R.string.cancel);
            for (int i = 0; i < this.a.s.size(); i++) {
                ((com.aee.mokacam.bean.g) this.a.s.get(i)).g = true;
            }
            if (this.a.s.size() != 0) {
                this.a.p.notifyDataSetChanged();
            }
        } else {
            if (this.a.s.size() != 0) {
                this.a.p.notifyDataSetChanged();
            }
            this.a.w.setVisibility(8);
            this.a.g.setImageResource(R.drawable.library_delete_unpressed);
            if ("camera_lib".equals(this.a.r)) {
                this.a.h.setImageResource(R.drawable.btn_download_n);
            } else {
                this.a.h.setImageResource(R.drawable.library_share_unpressed);
            }
            this.a.k.setTextColor(0xFF000000);
            this.a.k.setText(R.string.lib_select);
            this.a.n.setVisibility(8);
            this.a.o = 0;
            for (int i2 = 0; i2 < this.a.s.size(); i2++) {
                ((com.aee.mokacam.bean.g) this.a.s.get(i2)).b(false);
                ((com.aee.mokacam.bean.g) this.a.s.get(i2)).g = false;
                this.a.p.notifyDataSetChanged();
            }
        }
        this.a.m = this.a.m ? false : true;
    }
}
