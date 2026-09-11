package com.aee.mokacam.service;

import android.view.View;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
class ah implements View.OnClickListener {
    final /* synthetic */ UpdateManager a;

    ah(UpdateManager updateManager) {
        this.a = updateManager;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.j.cancel();
        switch (view.getId()) {
            case R.id.dialog_button_ok /* 2131427699 */:
                this.a.d();
                this.a.c();
                break;
        }
    }
}
