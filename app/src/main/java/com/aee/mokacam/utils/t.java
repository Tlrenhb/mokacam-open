package com.aee.mokacam.utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class t {
    public static File a(Context context) {
        return new File(("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) ? context.getExternalCacheDir().getPath() : context.getCacheDir().getPath());
    }

    public static File a(Context context, String str) {
        return new File(String.valueOf(("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) ? context.getExternalCacheDir().getPath() : context.getCacheDir().getPath()) + File.separator + str);
    }

    public static List<com.aee.mokacam.bean.g> a(String str) {
        ArrayList arrayList = new ArrayList();
        File[] fileArrB = b(str);
        for (int i = 0; i < fileArrB.length; i++) {
            arrayList.add(new com.aee.mokacam.bean.g(fileArrB[i].getName(), fileArrB[i].getAbsolutePath(), new SimpleDateFormat("yyyy-MM-dd").format(new Date(fileArrB[i].lastModified()))));
        }
        return arrayList;
    }

    public static List<com.aee.mokacam.bean.g> a(List<com.aee.mokacam.bean.g> list) {
        Collections.sort(list, new v());
        return list;
    }

    static File[] b(String str) {
        File[] fileArrListFiles = new File[0];
        if (str != null) {
            File file = new File(str);
            if (file.exists() && (!file.isDirectory() || file.listFiles().length != 0)) {
                fileArrListFiles = file.listFiles();
                if (file.listFiles().length > 0) {
                    Arrays.sort(fileArrListFiles, new u());
                }
            }
        }
        return fileArrListFiles;
    }
}
