package com.nostra13.universalimageloader.core.download;

import java.io.InputStream;
import java.util.Locale;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public interface ImageDownloader {

    public enum Scheme {
        HTTP("http"),
        HTTPS("https"),
        FILE("file"),
        CONTENT("content"),
        ASSETS("assets"),
        DRAWABLE("drawable"),
        UNKNOWN(BuildConfig.FLAVOR);

        private String a;
        private String b;

        Scheme(String str) {
            this.a = str;
            this.b = str + "://";
        }

        private boolean a(String str) {
            return str.toLowerCase(Locale.US).startsWith(this.b);
        }

        public static Scheme ofUri(String str) {
            if (str != null) {
                for (Scheme scheme : values()) {
                    if (scheme.a(str)) {
                        return scheme;
                    }
                }
            }
            return UNKNOWN;
        }

        public String crop(String str) {
            if (a(str)) {
                return str.substring(this.b.length());
            }
            throw new IllegalArgumentException(String.format("URI [%1$s] doesn't have expected scheme [%2$s]", str, this.a));
        }

        public String wrap(String str) {
            return this.b + str;
        }
    }

    InputStream a(String str, Object obj);
}
