package com.aee.mokacam.service;

import android.view.View;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
class ai implements View.OnClickListener {
    final /* synthetic */ UpdateManager a;

    ai(UpdateManager updateManager) {
        this.a = updateManager;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_button_cancel /* 2131427701 */:
                this.a.k.dismiss();
                if (this.a.l != null) {
                    this.a.l.cancel();
                }
                break;
        }
    }
}
