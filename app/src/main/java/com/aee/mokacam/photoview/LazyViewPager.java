package com.aee.mokacam.photoview;

import android.content.Context;
import android.util.AttributeSet;

/**
 * The original app vendored the full support ViewPager with a lazy-loading
 * flag. On androidx the same behaviour is provided by ViewPager itself, so
 * this thin subclass keeps the original class name and constructor surface
 * used across the app (NoScrollViewPager, ShowPicOrVideoActivity).
 */
public class LazyViewPager extends androidx.viewpager.widget.ViewPager {

    public LazyViewPager(Context context) {
        super(context);
    }

    public LazyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /** Obfuscated alias used by the original code (setCurrentItem). */
    public void a(int item, boolean smoothScroll) {
        setCurrentItem(item, smoothScroll);
    }
}
