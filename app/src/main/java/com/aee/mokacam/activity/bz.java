package com.aee.mokacam.activity;

import android.content.Intent;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class bz implements View.OnClickListener {
    final /* synthetic */ SelectLibraryActivity a;

    bz(SelectLibraryActivity selectLibraryActivity) {
        this.a = selectLibraryActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Intent intent = new Intent(this.a, (Class<?>) LibraryActivity.class);
        intent.putExtra("fromWhere", "camera_lib");
        this.a.startActivityForResult(intent, 0);
    }
}
