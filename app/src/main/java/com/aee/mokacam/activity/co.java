package com.aee.mokacam.activity;

import android.content.Context;
import android.view.View;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
class co implements View.OnClickListener {
    final /* synthetic */ ShowPicOrVideoActivity a;

    co(ShowPicOrVideoActivity showPicOrVideoActivity) {
        this.a = showPicOrVideoActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        com.aee.mokacam.widget.e eVar = new com.aee.mokacam.widget.e((Context) this.a, false);
        eVar.show();
        eVar.b(R.string.sure_delete);
        eVar.d(R.string.cancel);
        eVar.c(R.string.sure);
        eVar.a(new cp(this, eVar));
    }
}
