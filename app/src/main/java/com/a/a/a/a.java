package com.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(int i) {
        Integer numValueOf = Integer.valueOf(i / 3600);
        Integer numValueOf2 = Integer.valueOf((i % 3600) / 60);
        Integer numValueOf3 = Integer.valueOf(i % 60);
        String str = String.valueOf(numValueOf.intValue() < 10 ? "0" + numValueOf.toString() : numValueOf.toString()) + ":";
        String str2 = String.valueOf(numValueOf2.intValue() < 10 ? String.valueOf(str) + "0" + numValueOf2.toString() : String.valueOf(str) + numValueOf2.toString()) + ":";
        return numValueOf3.intValue() < 10 ? String.valueOf(str2) + "0" + numValueOf3.toString() : String.valueOf(str2) + numValueOf3.toString();
    }
}
