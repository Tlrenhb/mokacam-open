package com.nostra13.universalimageloader.core.a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import com.alibaba.fastjson.asm.Opcodes;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class a implements d {
    protected final boolean a;

    public a(boolean z) {
        this.a = z;
    }

    private boolean a(String str, String str2) {
        return "image/jpeg".equalsIgnoreCase(str2) && ImageDownloader.Scheme.ofUri(str) == ImageDownloader.Scheme.FILE;
    }

    protected Bitmap a(Bitmap bitmap, e eVar, int i, boolean z) {
        Matrix matrix = new Matrix();
        ImageScaleType imageScaleTypeD = eVar.d();
        if (imageScaleTypeD == ImageScaleType.EXACTLY || imageScaleTypeD == ImageScaleType.EXACTLY_STRETCHED) {
            com.nostra13.universalimageloader.core.assist.c cVar = new com.nostra13.universalimageloader.core.assist.c(bitmap.getWidth(), bitmap.getHeight(), i);
            float fB = com.nostra13.universalimageloader.b.a.b(cVar, eVar.c(), eVar.e(), imageScaleTypeD == ImageScaleType.EXACTLY_STRETCHED);
            if (Float.compare(fB, 1.0f) != 0) {
                matrix.setScale(fB, fB);
                if (this.a) {
                    com.nostra13.universalimageloader.b.e.a("Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]", cVar, cVar.a(fB), Float.valueOf(fB), eVar.a());
                }
            }
        }
        if (z) {
            matrix.postScale(-1.0f, 1.0f);
            if (this.a) {
                com.nostra13.universalimageloader.b.e.a("Flip image horizontally [%s]", eVar.a());
            }
        }
        if (i != 0) {
            matrix.postRotate(i);
            if (this.a) {
                com.nostra13.universalimageloader.b.e.a("Rotate image on %1$d° [%2$s]", Integer.valueOf(i), eVar.a());
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (bitmapCreateBitmap != bitmap) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    @Override // com.nostra13.universalimageloader.core.a.d
    public Bitmap a(e eVar) {
        InputStream inputStreamB = b(eVar);
        if (inputStreamB == null) {
            com.nostra13.universalimageloader.b.e.d("No stream for image [%s]", eVar.a());
            return null;
        }
        try {
            c cVarA = a(inputStreamB, eVar);
            inputStreamB = b(inputStreamB, eVar);
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamB, null, a(cVarA.a, eVar));
            if (bitmapDecodeStream != null) {
                return a(bitmapDecodeStream, eVar, cVarA.b.a, cVarA.b.b);
            }
            com.nostra13.universalimageloader.b.e.d("Image can't be decoded [%s]", eVar.a());
            return bitmapDecodeStream;
        } finally {
            com.nostra13.universalimageloader.b.c.a((Closeable) inputStreamB);
        }
    }

    protected BitmapFactory.Options a(com.nostra13.universalimageloader.core.assist.c cVar, e eVar) {
        int iA;
        ImageScaleType imageScaleTypeD = eVar.d();
        if (imageScaleTypeD == ImageScaleType.NONE) {
            iA = 1;
        } else if (imageScaleTypeD == ImageScaleType.NONE_SAFE) {
            iA = com.nostra13.universalimageloader.b.a.a(cVar);
        } else {
            iA = com.nostra13.universalimageloader.b.a.a(cVar, eVar.c(), eVar.e(), imageScaleTypeD == ImageScaleType.IN_SAMPLE_POWER_OF_2);
        }
        if (iA > 1 && this.a) {
            com.nostra13.universalimageloader.b.e.a("Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]", cVar, cVar.a(iA), Integer.valueOf(iA), eVar.a());
        }
        BitmapFactory.Options optionsI = eVar.i();
        optionsI.inSampleSize = iA;
        return optionsI;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    protected b a(String str) {
        int i = 0;
        boolean z = true;
        try {
        } catch (IOException e) {
            com.nostra13.universalimageloader.b.e.c("Can't read EXIF tags from file [%s]", str);
        }
        switch (new ExifInterface(ImageDownloader.Scheme.FILE.crop(str)).getAttributeInt("Orientation", 1)) {
            case 1:
            default:
                z = false;
                break;
            case 2:
                break;
            case 3:
                z = false;
                i = Opcodes.GETFIELD;
                break;
            case 4:
                i = Opcodes.GETFIELD;
                break;
            case 5:
                i = 270;
                break;
            case 6:
                z = false;
                i = 90;
                break;
            case 7:
                i = 90;
                break;
            case 8:
                z = false;
                i = 270;
                break;
        }
        return new b(i, z);
    }

    protected c a(InputStream inputStream, e eVar) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        String strB = eVar.b();
        b bVarA = (eVar.h() && a(strB, options.outMimeType)) ? a(strB) : new b();
        return new c(new com.nostra13.universalimageloader.core.assist.c(options.outWidth, options.outHeight, bVarA.a), bVarA);
    }

    protected InputStream b(e eVar) {
        return eVar.f().a(eVar.b(), eVar.g());
    }

    protected InputStream b(InputStream inputStream, e eVar) {
        if (inputStream.markSupported()) {
            try {
                inputStream.reset();
                return inputStream;
            } catch (IOException e) {
            }
        }
        com.nostra13.universalimageloader.b.c.a((Closeable) inputStream);
        return b(eVar);
    }
}
