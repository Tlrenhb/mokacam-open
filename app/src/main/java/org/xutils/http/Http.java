package org.xutils.http;

import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.x;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * HttpURLConnection implementation of the x.http().get(file) flow used by
 * the app: download to params.getSaveFilePath(), report progress, then
 * deliver the File to the callback.
 */
public final class Http {

    public static final Http INSTANCE = new Http();
    private static final String TAG = "xutils-shim";
    private final ExecutorService pool = Executors.newCachedThreadPool();

    private Http() {
    }

    public <T> Callback.Cancelable get(final RequestParams params, final Callback.CommonCallback<T> callback) {
        final AtomicBoolean cancelled = new AtomicBoolean(false);
        final FutureCancel cancelable = new FutureCancel(cancelled);
        pool.execute(() -> {
            try {
                if (callback instanceof Callback.ProgressCallback) {
                    ((Callback.ProgressCallback) callback).onWaiting();
                    ((Callback.ProgressCallback) callback).onStarted();
                }
                URL url = new URL(params.getUri());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(8000);
                conn.setReadTimeout(20000);
                int code = conn.getResponseCode();
                if (code < 200 || code >= 300) {
                    conn.disconnect();
                    if (callback != null) {
                        callback.onError(new IllegalStateException("HTTP " + code), false);
                        callback.onFinished();
                    }
                    return;
                }
                long total = conn.getContentLength();
                InputStream in = conn.getInputStream();
                String path = params.getSaveFilePath();
                File out = path != null ? new File(path)
                        : File.createTempFile("xutils_", ".tmp");
                File parent = out.getParentFile();
                if (parent != null && !parent.exists()) {
                    parent.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(out);
                byte[] buf = new byte[16384];
                long done = 0;
                int n;
                while ((n = in.read(buf)) > 0 && !cancelled.get()) {
                    fos.write(buf, 0, n);
                    done += n;
                    if (callback instanceof Callback.ProgressCallback) {
                        ((Callback.ProgressCallback) callback).onLoading(total, done, true);
                    }
                }
                fos.close();
                in.close();
                conn.disconnect();
                if (cancelled.get()) {
                    out.delete();
                    if (callback != null) {
                        callback.onCancelled(new Callback.CancelledException("user"));
                        callback.onFinished();
                    }
                    return;
                }
                if (callback != null) {
                    callback.onSuccess((T) out);
                    callback.onFinished();
                }
            } catch (Exception e) {
                Log.w(TAG, "get failed: " + e.getMessage());
                if (callback != null) {
                    callback.onError(e, false);
                    callback.onFinished();
                }
            }
        });
        return cancelable;
    }

    private static final class FutureCancel implements Callback.Cancelable {
        private final AtomicBoolean cancelled;

        FutureCancel(AtomicBoolean cancelled) {
            this.cancelled = cancelled;
        }

        @Override
        public void cancel() {
            cancelled.set(true);
        }
    }
}
