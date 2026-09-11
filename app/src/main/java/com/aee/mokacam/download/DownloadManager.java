package com.aee.mokacam.download;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.aee.mokacam.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Single-queue HTTP downloader for camera files.
 *
 * <p>The original app (com.aee.zone.activity.DownLoadActivity and the
 * DownloadActivity flow of ShowPicOrVideoActivity) downloaded files from
 * http://192.168.42.1/... with xUtils' http stack and showed progress in the
 * notification bar; only one task was active at a time and entering the
 * viewer cancelled other work. This rewrite keeps the single-task queue and
 * the notification progress but uses HttpURLConnection.</p>
 */
public class DownloadManager {

    private static final String TAG = "DownloadManager";
    private static final String CHANNEL_ID = "mokacam_download";
    private static final int NOTIFY_ID = 1001;

    public enum State {WAITING, RUNNING, DONE, CANCELLED, FAILED}

    public interface Listener {
        void onStateChanged(DownloadTask task);
    }

    public static class DownloadTask {
        public final String url;
        public final String destFile;
        public State state = State.WAITING;
        public int percent;

        DownloadTask(String url, String destFile) {
            this.url = url;
            this.destFile = destFile;
        }
    }

    private static volatile DownloadManager sInstance;
    private final List<Listener> listeners = new CopyOnWriteArrayList<>();
    private final java.util.Deque<DownloadTask> queue = new java.util.concurrent.ConcurrentLinkedDeque<>();
    private DownloadTask current;
    private Thread worker;
    private Context appContext;

    public static DownloadManager get() {
        if (sInstance == null) {
            synchronized (DownloadManager.class) {
                if (sInstance == null) {
                    sInstance = new DownloadManager();
                }
            }
        }
        return sInstance;
    }

    private DownloadManager() {
    }

    public void init(Context context) {
        this.appContext = context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager nm = (NotificationManager)
                    appContext.getSystemService(Context.NOTIFICATION_SERVICE);
            if (nm != null && nm.getNotificationChannel(CHANNEL_ID) == null) {
                nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID,
                        appContext.getString(R.string.download_notification_channel),
                        NotificationManager.IMPORTANCE_LOW));
            }
        }
    }

    public void addListener(Listener l) {
        if (l != null) {
            listeners.add(l);
        }
    }

    public void removeListener(Listener l) {
        listeners.remove(l);
    }

    /** Queue a download; returns false when the URL is already queued/done. */
    public boolean enqueue(String url, String destFile) {
        for (DownloadTask t : queue) {
            if (t.destFile.equals(destFile) && t.state != State.FAILED && t.state != State.CANCELLED) {
                return false;
            }
        }
        if (current != null && current.destFile.equals(destFile)
                && current.state != State.FAILED && current.state != State.CANCELLED) {
            return false;
        }
        DownloadTask task = new DownloadTask(url, destFile);
        queue.add(task);
        ensureWorker();
        return true;
    }

    /** Cancel everything (used when leaving the library screens). */
    public void cancelAll() {
        queue.clear();
        current = null;
    }

    public DownloadTask currentTask() {
        return current;
    }

    private synchronized void ensureWorker() {
        if (worker != null && worker.isAlive()) {
            return;
        }
        worker = new Thread(() -> {
            while ((current = queue.poll()) != null) {
                current.state = State.RUNNING;
                notifyState();
                boolean ok = run(current);
                current.state = ok ? State.DONE : State.FAILED;
                notifyState();
            }
        }, "mokacam-download");
        worker.setDaemon(true);
        worker.start();
    }

    private boolean run(DownloadTask task) {
        File dest = new File(task.destFile);
        File tmp = new File(task.destFile + ".part");
        try {
            File parent = dest.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            HttpURLConnection conn = (HttpURLConnection) new URL(task.url).openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(15000);
            int code = conn.getResponseCode();
            if (code != 200) {
                Log.w(TAG, "download http " + code);
                return false;
            }
            long total = conn.getContentLength();
            InputStream in = conn.getInputStream();
            FileOutputStream out = new FileOutputStream(tmp);
            byte[] buf = new byte[16384];
            long done = 0;
            int n;
            while ((n = in.read(buf)) > 0) {
                out.write(buf, 0, n);
                done += n;
                if (total > 0) {
                    int percent = (int) (done * 100 / total);
                    if (percent != task.percent) {
                        task.percent = percent;
                        notifyState();
                        updateNotification(percent);
                    }
                }
            }
            out.close();
            in.close();
            conn.disconnect();
            return tmp.renameTo(dest);
        } catch (Exception e) {
            Log.w(TAG, "download failed: " + e.getMessage());
            tmp.delete();
            return false;
        }
    }

    private void notifyState() {
        for (Listener l : listeners) {
            l.onStateChanged(current);
        }
    }

    private void updateNotification(int percent) {
        if (appContext == null) {
            return;
        }
        NotificationCompat.Builder b = new NotificationCompat.Builder(appContext, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_sys_download)
                .setContentTitle(appContext.getString(R.string.load_loading))
                .setOnlyAlertOnce(true)
                .setProgress(100, percent, false);
        NotificationManager nm = (NotificationManager)
                appContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm != null) {
            nm.notify(NOTIFY_ID, b.build());
        }
    }
}
