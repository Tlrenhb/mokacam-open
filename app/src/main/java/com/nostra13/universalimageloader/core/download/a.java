package com.nostra13.universalimageloader.core.download;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import com.iflytek.cloud.util.AudioDetector;
import com.nostra13.universalimageloader.b.c;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class a implements ImageDownloader {
    protected final Context a;
    protected final int b;
    protected final int c;

    public a(Context context) {
        this(context, 5000, AudioDetector.DEF_EOS);
    }

    public a(Context context, int i, int i2) {
        this.a = context.getApplicationContext();
        this.b = i;
        this.c = i2;
    }

    @TargetApi(8)
    private InputStream a(String str) {
        Bitmap bitmapCreateVideoThumbnail;
        if (Build.VERSION.SDK_INT < 8 || (bitmapCreateVideoThumbnail = ThumbnailUtils.createVideoThumbnail(str, 2)) == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapCreateVideoThumbnail.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    private boolean b(Uri uri) {
        String type = this.a.getContentResolver().getType(uri);
        return type != null && type.startsWith("video/");
    }

    private boolean b(String str) {
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str));
        return mimeTypeFromExtension != null && mimeTypeFromExtension.startsWith("video/");
    }

    @TargetApi(14)
    protected InputStream a(Uri uri) {
        ContentResolver contentResolver = this.a.getContentResolver();
        return Build.VERSION.SDK_INT >= 14 ? ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri, true) : ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri);
    }

    @Override // com.nostra13.universalimageloader.core.download.ImageDownloader
    public InputStream a(String str, Object obj) {
        switch (ImageDownloader.Scheme.ofUri(str)) {
            case HTTP:
            case HTTPS:
                return b(str, obj);
            case FILE:
                return d(str, obj);
            case CONTENT:
                return e(str, obj);
            case ASSETS:
                return f(str, obj);
            case DRAWABLE:
                return g(str, obj);
            default:
                return h(str, obj);
        }
    }

    protected boolean a(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getResponseCode() == 200;
    }

    protected InputStream b(String str, Object obj) throws IOException {
        HttpURLConnection httpURLConnectionC = c(str, obj);
        for (int i = 0; httpURLConnectionC.getResponseCode() / 100 == 3 && i < 5; i++) {
            httpURLConnectionC = c(httpURLConnectionC.getHeaderField("Location"), obj);
        }
        try {
            InputStream inputStream = httpURLConnectionC.getInputStream();
            if (a(httpURLConnectionC)) {
                return new com.nostra13.universalimageloader.core.assist.a(new BufferedInputStream(inputStream, 32768), httpURLConnectionC.getContentLength());
            }
            c.a((Closeable) inputStream);
            throw new IOException("Image request failed with response code " + httpURLConnectionC.getResponseCode());
        } catch (IOException e) {
            c.a(httpURLConnectionC.getErrorStream());
            throw e;
        }
    }

    protected HttpURLConnection c(String str, Object obj) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(Uri.encode(str, "@#&=*+-_.,:!?()/~'%")).openConnection();
        httpURLConnection.setConnectTimeout(this.b);
        httpURLConnection.setReadTimeout(this.c);
        return httpURLConnection;
    }

    protected InputStream d(String str, Object obj) {
        String strCrop = ImageDownloader.Scheme.FILE.crop(str);
        return b(str) ? a(strCrop) : new com.nostra13.universalimageloader.core.assist.a(new BufferedInputStream(new FileInputStream(strCrop), 32768), (int) new File(strCrop).length());
    }

    protected InputStream e(String str, Object obj) {
        ContentResolver contentResolver = this.a.getContentResolver();
        Uri uri = Uri.parse(str);
        if (b(uri)) {
            Bitmap thumbnail = MediaStore.Video.Thumbnails.getThumbnail(contentResolver, Long.valueOf(uri.getLastPathSegment()).longValue(), 1, null);
            if (thumbnail != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
        } else if (str.startsWith("content://com.android.contacts/")) {
            return a(uri);
        }
        return contentResolver.openInputStream(uri);
    }

    protected InputStream f(String str, Object obj) {
        return this.a.getAssets().open(ImageDownloader.Scheme.ASSETS.crop(str));
    }

    protected InputStream g(String str, Object obj) {
        return this.a.getResources().openRawResource(Integer.parseInt(ImageDownloader.Scheme.DRAWABLE.crop(str)));
    }

    protected InputStream h(String str, Object obj) {
        throw new UnsupportedOperationException(String.format("UIL doesn't support scheme(protocol) by default [%s]. You should implement this support yourself (BaseImageDownloader.getStreamFromOtherSource(...))", str));
    }
}
