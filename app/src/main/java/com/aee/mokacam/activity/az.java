package com.aee.mokacam.activity;

import android.view.View;
import android.widget.AdapterView;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
class az implements AdapterView.OnItemLongClickListener {
    final /* synthetic */ LibraryActivity a;

    az(LibraryActivity libraryActivity) {
        this.a = libraryActivity;
    }

    @Override // android.widget.AdapterView.OnItemLongClickListener
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.w.setVisibility(0);
        this.a.k.setTextColor(-7829368);
        this.a.k.setText(R.string.cancel);
        this.a.m = !this.a.m;
        for (int i2 = 0; i2 < this.a.s.size(); i2++) {
            ((com.aee.mokacam.bean.g) this.a.s.get(i2)).g = true;
        }
        this.a.p.notifyDataSetChanged();
        return true;
    }
}
