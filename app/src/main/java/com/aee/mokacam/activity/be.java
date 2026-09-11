package com.aee.mokacam.activity;

import android.view.View;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
class be implements View.OnClickListener {
    final /* synthetic */ LibraryActivity a;

    be(LibraryActivity libraryActivity) {
        this.a = libraryActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.a.o == 0) {
            com.aee.mokacam.utils.w.a(R.string.library_delete, true);
        } else {
            this.a.k();
        }
    }
}
