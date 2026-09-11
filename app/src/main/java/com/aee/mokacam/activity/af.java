package com.aee.mokacam.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
class af extends ArrayAdapter<String> {
    final /* synthetic */ AeeCameraSettingActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    af(AeeCameraSettingActivity aeeCameraSettingActivity, Context context, int i, String[] strArr) {
        super(context, i, strArr);
        this.a = aeeCameraSettingActivity;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(this.a);
        textView.setTextSize(16.0f);
        textView.setTextColor(-1);
        textView.setGravity(17);
        textView.setPadding(0, 17, 0, 17);
        String string = this.a.a[i];
        if ("TCN".equalsIgnoreCase(string)) {
            string = "TRADITIONAL CHINESE";
        } else if ("sfine".equals(string)) {
            string = this.a.getResources().getString(R.string.s_hight);
        } else if ("fine".equals(string)) {
            string = this.a.getResources().getString(R.string.hight);
        } else if ("normal".equals(string)) {
            string = this.a.getResources().getString(R.string.common);
        } else if ("off".equals(string)) {
            string = this.a.getResources().getString(R.string.setting_off);
        } else if ("date".equals(string)) {
            string = this.a.getResources().getString(R.string.date);
        } else if ("time".equals(string)) {
            string = this.a.getResources().getString(R.string.time);
        } else if ("date/time".equals(string)) {
            string = this.a.getResources().getString(R.string.date_time);
        }
        textView.setText(string);
        return textView;
    }
}
