package org.xutils;

import android.content.Context;

/**
 * Minimal compatibility shim over the subset of the original xUtils 3 HTTP
 * stack that this app used (x.Ext.init, x.http().get(RequestParams, callback)).
 * Implemented on HttpURLConnection.
 */
public final class x {

    public static final class Ext {
        public static void init(Context context) {
        }

        private Ext() {
        }
    }

    private x() {
    }

    public static org.xutils.http.Http http() {
        return org.xutils.http.Http.INSTANCE;
    }
}
