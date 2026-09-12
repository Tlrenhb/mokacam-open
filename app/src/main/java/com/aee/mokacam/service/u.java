package com.aee.mokacam.service;

import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;
import com.aee.mokacam.utils.ResolveJson;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
class u implements Runnable {
    final /* synthetic */ o a;
    private final JsonStreamDecoder decoder = new JsonStreamDecoder();

    u(o oVar) {
        this.a = oVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.a.b) {
            try {
                if (o.e == null) {
                    o.e = this.a.d.getInputStream();
                }
                while (true) {
                    if (!this.a.b) {
                        break;
                    }
                    if (this.a.i) {
                        TimeUnit.SECONDS.sleep(1L);
                        break;
                    }
                    int iAvailable = o.e.available();
                    if (iAvailable <= 0) {
                        TimeUnit.MILLISECONDS.sleep(20L);
                        continue;
                    }
                    byte[] bArr = new byte[Math.min(iAvailable, 8192)];
                    int count = o.e.read(bArr);
                    if (count <= 0) continue;
                    decoder.append(new String(bArr, 0, count, java.nio.charset.StandardCharsets.UTF_8));
                    String json;
                    while ((json = decoder.poll()) != null) {
                        ReceiveMsg receiveMsg = (ReceiveMsg) ResolveJson.resolveNormalInfo(json, ReceiveMsg.class);
                        if (receiveMsg == null) continue;
                        if (this.a.c(receiveMsg)) {
                            AeeApplication.a().m = true;
                        } else if (this.a.b(receiveMsg)) {
                            AeeApplication.a().i = true;
                        } else if (this.a.a(receiveMsg)) {
                            AeeApplication.a().j = true;
                        } else if (this.a.d(receiveMsg) != 0) {
                            AeeApplication.a().k = this.a.d(receiveMsg);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
