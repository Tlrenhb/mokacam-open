package org.xutils.common;

public interface Callback {

    interface Cancelable {
        void cancel();
    }

    class CancelledException extends Exception {
        public CancelledException(String message) {
            super(message);
        }
    }

    interface CommonCallback<T> {
        void onSuccess(T result);

        void onError(Throwable ex, boolean isOnCallback);

        void onCancelled(CancelledException ex);

        void onFinished();
    }

    interface ProgressCallback<T> extends CommonCallback<T> {
        void onWaiting();

        void onStarted();

        void onLoading(long total, long current, boolean isDownloading);
    }
}
