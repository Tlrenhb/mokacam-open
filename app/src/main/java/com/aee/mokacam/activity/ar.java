package com.aee.mokacam.activity;

/* JADX INFO: loaded from: classes.dex */
class ar implements com.aee.mokacam.widget.f {
    final /* synthetic */ DownLoadActivity a;
    final /* synthetic */ com.aee.mokacam.widget.e b;

    ar(DownLoadActivity downLoadActivity, com.aee.mokacam.widget.e eVar) {
        this.a = downLoadActivity;
        this.b = eVar;
    }

    @Override // com.aee.mokacam.widget.f
    public void a() {
        this.b.dismiss();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.a.c.size()) {
                this.a.finish();
                return;
            }
            if (((com.aee.mokacam.bean.g) this.a.c.get(i2)).g() != 3 && ((com.aee.mokacam.bean.g) this.a.c.get(i2)).e != null) {
                ((com.aee.mokacam.bean.g) this.a.c.get(i2)).e.cancel();
                ((com.aee.mokacam.bean.g) this.a.c.get(i2)).e = null;
            }
            i = i2 + 1;
        }
    }

    @Override // com.aee.mokacam.widget.f
    public void b() {
        this.b.dismiss();
    }
}
