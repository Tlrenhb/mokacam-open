package com.aee.mokacam.activity;

/* JADX INFO: loaded from: classes.dex */
class cw implements com.aee.mokacam.widget.f {
    final /* synthetic */ ShowPicOrVideoActivity a;
    private final /* synthetic */ com.aee.mokacam.widget.e b;

    cw(ShowPicOrVideoActivity showPicOrVideoActivity, com.aee.mokacam.widget.e eVar) {
        this.a = showPicOrVideoActivity;
        this.b = eVar;
    }

    @Override // com.aee.mokacam.widget.f
    public void a() {
        this.b.dismiss();
        if ("camera_lib".equals(this.a.G)) {
            this.a.a("/tmp/SD0/moka/" + ((com.aee.mokacam.bean.g) this.a.g.get(this.a.f)).b + ((com.aee.mokacam.bean.g) this.a.g.get(this.a.f)).a());
        } else {
            this.a.a(((com.aee.mokacam.bean.g) this.a.g.get(this.a.f)).a());
        }
        this.a.K = true;
    }

    @Override // com.aee.mokacam.widget.f
    public void b() {
        this.b.dismiss();
    }
}
