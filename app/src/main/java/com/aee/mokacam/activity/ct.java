package com.aee.mokacam.activity;

import io.vov.vitamio.MediaPlayer;

/* JADX INFO: loaded from: classes.dex */
class ct implements MediaPlayer.OnInfoListener {
    final /* synthetic */ ShowPicOrVideoActivity a;

    ct(ShowPicOrVideoActivity showPicOrVideoActivity) {
        this.a = showPicOrVideoActivity;
    }

    @Override // io.vov.vitamio.MediaPlayer.OnInfoListener
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        switch (i) {
            case 704:
                this.a.t.audioInitedOk(this.a.t.audioTrackInit());
                break;
        }
        return true;
    }
}
