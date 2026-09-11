package com.aee.mokacam.activity;

import android.graphics.Bitmap;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/** ImageLoadingListener capturing decoded bitmaps into the album model. */
public class bo implements ImageLoadingListener {
    final /* synthetic */ bn a;
    int b;

    public bo(bn bnVar, int i) {
        this.a = bnVar;
        this.b = i;
    }

    @Override // ImageLoadingListener
    public void onLoadingStarted(String imageUri, View view) {
    }

    @Override // ImageLoadingListener
    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
    }

    @Override // ImageLoadingListener
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        if (this.b <= this.a.a.s.size()) {
            ((com.aee.mokacam.bean.g) this.a.a.s.get(this.b)).f = loadedImage;
        }
    }

    @Override // ImageLoadingListener
    public void onLoadingCancelled(String imageUri, View view) {
    }
}
