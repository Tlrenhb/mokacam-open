package com.facebook.rebound;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class l {
    static final l a = new l(true);
    final Map<k, String> b = new HashMap();

    l(boolean z) {
        if (z) {
            a(k.c, "default config");
        }
    }

    public static l a() {
        return a;
    }

    public boolean a(k kVar, String str) {
        if (kVar == null) {
            throw new IllegalArgumentException("springConfig is required");
        }
        if (str == null) {
            throw new IllegalArgumentException("configName is required");
        }
        if (this.b.containsKey(kVar)) {
            return false;
        }
        this.b.put(kVar, str);
        return true;
    }

    public Map<k, String> b() {
        return Collections.unmodifiableMap(this.b);
    }
}
