package com.aee.mokacam.activity;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class bg implements View.OnClickListener {
    final /* synthetic */ LibraryActivity a;

    bg(LibraryActivity libraryActivity) {
        this.a = libraryActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.q.dismiss();
    }
}
