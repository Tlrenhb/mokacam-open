package com.aee.mokacam.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
class bm extends AsyncTask<String, Void, Bitmap> {
    final /* synthetic */ LibraryActivity a;
    String b = null;
    String c = BuildConfig.FLAVOR;
    int d;

    public bm(LibraryActivity libraryActivity, int i) {
        this.a = libraryActivity;
        this.d = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0092 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Bitmap doInBackground(String... strArr) {
        OutputStream outputStreamA;
        Bitmap bitmapDecodeStream = null;
        this.b = strArr[0];
        String strA = com.aee.mokacam.utils.o.a(this.b);
        try {
            if (this.a.u.a(strA) == null) {
                com.aee.mokacam.utils.g gVarB = this.a.u.b(strA);
                if (gVarB != null) {
                    outputStreamA = gVarB.a(0);
                    try {
                            if (new File(this.b).exists()) {
                                if (this.a.b(this.b, outputStreamA)) {
                                    gVarB.a();
                                } else {
                                    gVarB.b();
                                }
                            } else if (this.a.a(this.b, outputStreamA)) {
                                gVarB.a();
                            } else {
                                gVarB.a();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (outputStreamA != null) {
                                try {
                                    outputStreamA.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                    }
                } else {
                    outputStreamA = null;
                }
                this.a.u.a();
            } else {
                outputStreamA = null;
            }
            com.aee.mokacam.utils.j jVarA = this.a.u.a(strA);
            bitmapDecodeStream = jVarA != null ? BitmapFactory.decodeStream(jVarA.a(0)) : null;
        } catch (Exception e4) {
            outputStreamA = null;
        }
        if (bitmapDecodeStream == null) {
            if (outputStreamA != null) {
                try {
                    outputStreamA.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            return null;
        }
        this.a.t.put(strA, bitmapDecodeStream);
        if (outputStreamA == null) {
            return bitmapDecodeStream;
        }
        try {
            outputStreamA.close();
            return bitmapDecodeStream;
        } catch (IOException e6) {
            e6.printStackTrace();
            return bitmapDecodeStream;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        ImageView imageView = (ImageView) this.a.f.findViewWithTag(this.b);
        ImageView imageView2 = (ImageView) this.a.f.findViewWithTag(String.valueOf(this.b) + "play");
        if (imageView != null && bitmap != null && this.d < this.a.s.size()) {
            imageView.setImageBitmap(bitmap);
            imageView2.setVisibility(0);
            ((com.aee.mokacam.bean.g) this.a.s.get(this.d)).f = bitmap;
        }
        this.a.b.remove(Integer.valueOf(this.d));
    }
}
