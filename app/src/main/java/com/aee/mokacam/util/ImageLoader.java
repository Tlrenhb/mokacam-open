package com.aee.mokacam.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Minimal image loader for camera thumbnails served over plain HTTP.
 *
 * <p>Replacement of the Universal Image Loader usage of the original app
 * (AeeApplication.f() / com.nostra13.universalimageloader). The camera serves
 * small JPEG thumbnails, so an in-memory LRU cache plus a fixed thread pool
 * is sufficient.</p>
 */
public final class ImageLoader {

    private static final int CACHE_SIZE = 12 * 1024 * 1024; // 12 MB
    private static final ImageLoader INSTANCE = new ImageLoader();

    private final LruCache<String, Bitmap> cache;
    private final ExecutorService pool = Executors.newFixedThreadPool(4);
    private final Handler main = new Handler(Looper.getMainLooper());

    private ImageLoader() {
        cache = new LruCache<String, Bitmap>(CACHE_SIZE) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public static ImageLoader get() {
        return INSTANCE;
    }

    public void load(String url, ImageView target) {
        Bitmap cached = cache.get(url);
        if (cached != null) {
            target.setImageBitmap(cached);
            target.setTag(null);
            return;
        }
        target.setTag(url);
        target.setImageDrawable(null);
        pool.execute(() -> {
            Bitmap bmp = fetch(url);
            if (bmp != null) {
                cache.put(url, bmp);
            }
            main.post(() -> {
                if (url.equals(target.getTag())) {
                    target.setImageBitmap(bmp);
                }
            });
        });
    }

    private Bitmap fetch(String url) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(4000);
            conn.setReadTimeout(8000);
            if (conn.getResponseCode() != 200) {
                return null;
            }
            InputStream in = conn.getInputStream();
            Bitmap bmp = BitmapFactory.decodeStream(in);
            in.close();
            return bmp;
        } catch (Exception e) {
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
