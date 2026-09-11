package com.aee.mokacam.utils;

import com.aee.mokacam.AeeApplication;

/* JADX INFO: loaded from: classes.dex */
public class q {
    public static final String[] a = {"record_mode_vid", "record_mode_voi", "record_mode_lap", "record_mode_pre", "record_mode_sel", "record_mode_piv"};
    public static final String[] b = {"photo_cap_mode_nor", "photo_cap_mode_fas", "photo_cap_mode_cfa", "photo_cap_mode_tlm", "photo_cap_mode_lop", "photo_cap_mode_sel"};

    public static int a(boolean z, String str, String str2) {
        String[] stringArray = AeeApplication.a().getApplicationContext().getResources().getStringArray(z ? AeeApplication.a().getApplicationContext().getResources().getIdentifier(String.valueOf(str) + "_name", "array", AeeApplication.a().getPackageName()) : AeeApplication.a().getApplicationContext().getResources().getIdentifier(String.valueOf(str) + "_value", "array", AeeApplication.a().getPackageName()));
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals(str2)) {
                return i;
            }
        }
        return -100;
    }

    public static String a(String str, String str2) {
        try {
            String[] stringArray = AeeApplication.a().getApplicationContext().getResources().getStringArray(AeeApplication.a().getApplicationContext().getResources().getIdentifier(String.valueOf(str) + "_name", "array", AeeApplication.a().getPackageName()));
            int iA = a(false, str, str2);
            return (iA < 0 || iA >= stringArray.length) ? str2 : stringArray[iA];
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static String[] a(String str, String[] strArr) {
        int length = strArr.length;
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < length; i++) {
            strArr2[i] = a(str, strArr[i]);
        }
        return strArr2;
    }
}
