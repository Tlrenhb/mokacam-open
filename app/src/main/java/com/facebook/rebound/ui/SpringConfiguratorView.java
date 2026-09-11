package com.facebook.rebound.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import com.alibaba.fastjson.asm.Opcodes;
import com.facebook.rebound.g;
import com.facebook.rebound.i;
import com.facebook.rebound.k;
import com.facebook.rebound.l;
import com.facebook.rebound.o;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SpringConfiguratorView extends FrameLayout {
    private static final DecimalFormat a = new DecimalFormat("#.#");
    private final d b;
    private final List<k> c;
    private final i d;
    private final float e;
    private final float f;
    private final l g;
    private final int h;
    private SeekBar i;
    private SeekBar j;
    private Spinner k;
    private TextView l;
    private TextView m;
    private k n;

    public SpringConfiguratorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @TargetApi(11)
    public SpringConfiguratorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new ArrayList();
        this.h = Color.argb(255, 225, 225, 225);
        o oVarC = o.c();
        this.g = l.a();
        this.b = new d(this, context);
        Resources resources = getResources();
        this.f = f.a(40.0f, resources);
        this.e = f.a(280.0f, resources);
        this.d = oVarC.b();
        this.d.a(1.0d).b(1.0d).a(new b(this, null));
        addView(a(context));
        c cVar = new c(this, 0 == true ? 1 : 0);
        this.i.setMax(100000);
        this.i.setOnSeekBarChangeListener(cVar);
        this.j.setMax(100000);
        this.j.setOnSeekBarChangeListener(cVar);
        this.k.setAdapter((SpinnerAdapter) this.b);
        this.k.setOnItemSelectedListener(new e(this, 0 == true ? 1 : 0));
        a();
        setTranslationY(this.e);
    }

    private View a(Context context) {
        Resources resources = getResources();
        int iA = f.a(5.0f, resources);
        int iA2 = f.a(10.0f, resources);
        int iA3 = f.a(20.0f, resources);
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(0, -2, 1.0f);
        layoutParams.setMargins(0, 0, iA, 0);
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(f.a(-1, f.a(300.0f, resources)));
        FrameLayout frameLayout2 = new FrameLayout(context);
        FrameLayout.LayoutParams layoutParamsA = f.a();
        layoutParamsA.setMargins(0, iA3, 0, 0);
        frameLayout2.setLayoutParams(layoutParamsA);
        frameLayout2.setBackgroundColor(Color.argb(100, 0, 0, 0));
        frameLayout.addView(frameLayout2);
        this.k = new Spinner(context, 0);
        FrameLayout.LayoutParams layoutParamsB = f.b();
        layoutParamsB.gravity = 48;
        layoutParamsB.setMargins(iA2, iA2, iA2, 0);
        this.k.setLayoutParams(layoutParamsB);
        frameLayout2.addView(this.k);
        LinearLayout linearLayout = new LinearLayout(context);
        FrameLayout.LayoutParams layoutParamsB2 = f.b();
        layoutParamsB2.setMargins(0, 0, 0, f.a(80.0f, resources));
        layoutParamsB2.gravity = 80;
        linearLayout.setLayoutParams(layoutParamsB2);
        linearLayout.setOrientation(1);
        frameLayout2.addView(linearLayout);
        LinearLayout linearLayout2 = new LinearLayout(context);
        FrameLayout.LayoutParams layoutParamsB3 = f.b();
        layoutParamsB3.setMargins(iA2, iA2, iA2, iA3);
        linearLayout2.setPadding(iA2, iA2, iA2, iA2);
        linearLayout2.setLayoutParams(layoutParamsB3);
        linearLayout2.setOrientation(0);
        linearLayout.addView(linearLayout2);
        this.i = new SeekBar(context);
        this.i.setLayoutParams(layoutParams);
        linearLayout2.addView(this.i);
        this.m = new TextView(getContext());
        this.m.setTextColor(this.h);
        FrameLayout.LayoutParams layoutParamsA2 = f.a(f.a(50.0f, resources), -1);
        this.m.setGravity(19);
        this.m.setLayoutParams(layoutParamsA2);
        this.m.setMaxLines(1);
        linearLayout2.addView(this.m);
        LinearLayout linearLayout3 = new LinearLayout(context);
        FrameLayout.LayoutParams layoutParamsB4 = f.b();
        layoutParamsB4.setMargins(iA2, iA2, iA2, iA3);
        linearLayout3.setPadding(iA2, iA2, iA2, iA2);
        linearLayout3.setLayoutParams(layoutParamsB4);
        linearLayout3.setOrientation(0);
        linearLayout.addView(linearLayout3);
        this.j = new SeekBar(context);
        this.j.setLayoutParams(layoutParams);
        linearLayout3.addView(this.j);
        this.l = new TextView(getContext());
        this.l.setTextColor(this.h);
        FrameLayout.LayoutParams layoutParamsA3 = f.a(f.a(50.0f, resources), -1);
        this.l.setGravity(19);
        this.l.setLayoutParams(layoutParamsA3);
        this.l.setMaxLines(1);
        linearLayout3.addView(this.l);
        View view = new View(context);
        FrameLayout.LayoutParams layoutParamsA4 = f.a(f.a(60.0f, resources), f.a(40.0f, resources));
        layoutParamsA4.gravity = 49;
        view.setLayoutParams(layoutParamsA4);
        view.setOnTouchListener(new a(this, null));
        view.setBackgroundColor(Color.argb(255, 0, Opcodes.IF_ICMPLE, 209));
        frameLayout.addView(view);
        return frameLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(k kVar) {
        int iRound = Math.round(((((float) g.b(kVar.b)) - 0.0f) * 100000.0f) / 200.0f);
        int iRound2 = Math.round(((((float) g.d(kVar.a)) - 0.0f) * 100000.0f) / 50.0f);
        this.i.setProgress(iRound);
        this.j.setProgress(iRound2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.d.b(this.d.c() == 1.0d ? 0 : 1);
    }

    public void a() {
        Map<k, String> mapB = this.g.b();
        this.b.a();
        this.c.clear();
        for (Map.Entry<k, String> entry : mapB.entrySet()) {
            if (entry.getKey() != k.c) {
                this.c.add(entry.getKey());
                this.b.a(entry.getValue());
            }
        }
        this.c.add(k.c);
        this.b.a(mapB.get(k.c));
        this.b.notifyDataSetChanged();
        if (this.c.size() > 0) {
            this.k.setSelection(0);
        }
    }
}
