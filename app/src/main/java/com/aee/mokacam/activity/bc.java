package com.aee.mokacam.activity;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class bc implements View.OnClickListener {
    final /* synthetic */ LibraryActivity a;

    bc(LibraryActivity libraryActivity) {
        this.a = libraryActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.a.s.size() != 0) {
            for (int i = 0; i < this.a.s.size(); i++) {
                ((com.aee.mokacam.bean.g) this.a.s.get(i)).g = false;
                ((com.aee.mokacam.bean.g) this.a.s.get(i)).b(false);
            }
        }
        this.a.finish();
    }
}
