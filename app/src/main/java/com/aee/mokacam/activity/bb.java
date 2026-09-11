package com.aee.mokacam.activity;

import android.view.View;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class bb implements View.OnClickListener {
    final /* synthetic */ LibraryActivity a;

    bb(LibraryActivity libraryActivity) {
        this.a = libraryActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.a.m) {
            return;
        }
        if (this.a.l) {
            for (int i = 0; i < this.a.s.size(); i++) {
                ((com.aee.mokacam.bean.g) this.a.s.get(i)).b(false);
            }
            AeeApplication.a().t.clear();
            this.a.p.notifyDataSetChanged();
            this.a.n.setVisibility(8);
            this.a.o = 0;
            this.a.o();
            this.a.w.setText(this.a.getResources().getString(R.string.library_selectAll));
        } else {
            if (this.a.s == null || this.a.s.size() == 0) {
                return;
            }
            if (AeeApplication.a().t == null) {
                AeeApplication.a().t = new ArrayList();
            }
            for (int i2 = 0; i2 < this.a.s.size(); i2++) {
                ((com.aee.mokacam.bean.g) this.a.s.get(i2)).b(true);
                AeeApplication.a().t.add((com.aee.mokacam.bean.g) this.a.s.get(i2));
            }
            this.a.p.notifyDataSetChanged();
            this.a.n.setVisibility(0);
            this.a.n.setSelectedNum(this.a.s.size());
            this.a.o = this.a.s.size();
            this.a.o();
            this.a.w.setText(this.a.getResources().getString(R.string.library_selectNone));
        }
        this.a.l = this.a.l ? false : true;
    }
}
