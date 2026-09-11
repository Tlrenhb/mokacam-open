package com.aee.mokacam.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
class bq extends BroadcastReceiver {
    final /* synthetic */ MainActivity a;

    bq(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
            switch (intent.getIntExtra("wifi_state", -1)) {
                case 1:
                    this.a.a = false;
                    AeeApplication.a().b = false;
                    break;
                case 3:
                    this.a.a = true;
                    break;
            }
        }
    }
}
