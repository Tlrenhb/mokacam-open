package com.nostra13.universalimageloader.core.c;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.b.e;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class b extends d {
    public b(ImageView imageView) {
        super(imageView);
    }

    private static int a(Object obj, String str) {
        try {
            Field declaredField = ImageView.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            int iIntValue = ((Integer) declaredField.get(obj)).intValue();
            if (iIntValue > 0 && iIntValue < Integer.MAX_VALUE) {
                return iIntValue;
            }
        } catch (Exception e) {
            e.a(e);
        }
        return 0;
    }

    @Override // com.nostra13.universalimageloader.core.c.d, com.nostra13.universalimageloader.core.c.a
    public int a() {
        ImageView imageView;
        int iA = super.a();
        return (iA > 0 || (imageView = (ImageView) this.a.get()) == null) ? iA : a(imageView, "mMaxWidth");
    }

    @Override // com.nostra13.universalimageloader.core.c.d
    protected void a(Bitmap bitmap, View view) {
        ((ImageView) view).setImageBitmap(bitmap);
    }

    @Override // com.nostra13.universalimageloader.core.c.d
    protected void a(Drawable drawable, View view) {
        ((ImageView) view).setImageDrawable(drawable);
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        }
    }

    @Override // com.nostra13.universalimageloader.core.c.d, com.nostra13.universalimageloader.core.c.a
    public int b() {
        ImageView imageView;
        int iB = super.b();
        return (iB > 0 || (imageView = (ImageView) this.a.get()) == null) ? iB : a(imageView, "mMaxHeight");
    }

    @Override // com.nostra13.universalimageloader.core.c.d, com.nostra13.universalimageloader.core.c.a
    public ViewScaleType c() {
        ImageView imageView = (ImageView) this.a.get();
        return imageView != null ? ViewScaleType.fromImageView(imageView) : super.c();
    }

    @Override // com.nostra13.universalimageloader.core.c.d, com.nostra13.universalimageloader.core.c.a
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public ImageView d() {
        return (ImageView) super.d();
    }
}
