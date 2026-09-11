package com.facebook.rebound.ui;

import android.widget.SeekBar;
import com.facebook.rebound.g;

/* JADX INFO: loaded from: classes.dex */
class c implements SeekBar.OnSeekBarChangeListener {
    final /* synthetic */ SpringConfiguratorView a;

    private c(SpringConfiguratorView springConfiguratorView) {
        this.a = springConfiguratorView;
    }

    /* synthetic */ c(SpringConfiguratorView springConfiguratorView, c cVar) {
        this(springConfiguratorView);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (seekBar == this.a.i) {
            float f = ((200.0f * i) / 100000.0f) + 0.0f;
            this.a.n.b = g.a(f);
            this.a.m.setText("T:" + SpringConfiguratorView.a.format(f));
        }
        if (seekBar == this.a.j) {
            float f2 = ((i * 50.0f) / 100000.0f) + 0.0f;
            this.a.n.a = g.c(f2);
            this.a.l.setText("F:" + SpringConfiguratorView.a.format(f2));
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
