package com.aee.mokacam.activity;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class bf implements View.OnClickListener {
    final /* synthetic */ LibraryActivity a;

    bf(LibraryActivity libraryActivity) {
        this.a = libraryActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.a();
        this.a.q.dismiss();
    }
}
