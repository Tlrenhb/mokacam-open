package com.aee.mokacam.activity;

import com.aee.mokacam.constants.AeeConstants;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
class cp implements com.aee.mokacam.widget.f {
    final /* synthetic */ co a;
    private final /* synthetic */ com.aee.mokacam.widget.e b;

    cp(co coVar, com.aee.mokacam.widget.e eVar) {
        this.a = coVar;
        this.b = eVar;
    }

    @Override // com.aee.mokacam.widget.f
    public void a() {
        this.b.dismiss();
        if ("camera_lib".equals(this.a.a.G)) {
            this.a.a.a("/tmp/SD0/moka/" + ((com.aee.mokacam.bean.g) this.a.a.g.get(this.a.a.f)).b + ((com.aee.mokacam.bean.g) this.a.a.g.get(this.a.a.f)).a());
        } else {
            this.a.a.a(String.valueOf(AeeConstants.a) + File.separator + ((com.aee.mokacam.bean.g) this.a.a.g.get(this.a.a.f)).f());
        }
        this.a.a.K = true;
    }

    @Override // com.aee.mokacam.widget.f
    public void b() {
        this.b.dismiss();
    }
}
