package com.aee.mokacam.activity;

/* JADX INFO: loaded from: classes.dex */
class cn implements com.aee.mokacam.widget.f {
    final /* synthetic */ ShowPicOrVideoActivity a;
    final /* synthetic */ com.aee.mokacam.widget.e b;

    cn(ShowPicOrVideoActivity showPicOrVideoActivity, com.aee.mokacam.widget.e eVar) {
        this.a = showPicOrVideoActivity;
        this.b = eVar;
    }

    @Override // com.aee.mokacam.widget.f
    public void a() {
        this.b.dismiss();
        if (this.a.J != null && this.a.I != null) {
            this.a.J.cancel();
            this.a.I = null;
            this.a.J = null;
        }
        this.a.finish();
    }

    @Override // com.aee.mokacam.widget.f
    public void b() {
        this.b.dismiss();
    }
}
