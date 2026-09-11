package com.aee.mokacam.utils;

import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
public class s {
    public static void a(String str, int i) {
        AeeApplication.b().getSharedPreferences("aee_pref", 0).edit().putInt(str, i).commit();
    }
}
