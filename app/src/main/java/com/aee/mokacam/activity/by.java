package com.aee.mokacam.activity;

import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
class by extends PagerAdapter {
    final /* synthetic */ RegisterActivity a;

    by(RegisterActivity registerActivity) {
        this.a = registerActivity;
    }

    /* synthetic */ by(RegisterActivity registerActivity, by byVar) {
        this(registerActivity);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return 3;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                this.a.k = 0;
                if (this.a.b == null) {
                    this.a.b = View.inflate(this.a, R.layout.registerpage_one, null);
                }
                this.a.a();
                this.a.f();
                viewGroup.addView(this.a.b);
                return this.a.b;
            case 1:
                this.a.h.setVisibility(0);
                this.a.k = 1;
                if (this.a.c == null) {
                    this.a.c = View.inflate(this.a, R.layout.registerpage_two, null);
                }
                this.a.b();
                this.a.d();
                viewGroup.addView(this.a.c);
                return this.a.c;
            case 2:
                this.a.h.setVisibility(8);
                this.a.k = 2;
                if (this.a.d == null) {
                    this.a.d = View.inflate(this.a, R.layout.registerpage_three, null);
                }
                this.a.c();
                this.a.e();
                viewGroup.addView(this.a.d);
                return this.a.d;
            default:
                return null;
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
