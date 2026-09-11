package com.aee.mokacam.service;

import android.os.Handler;
import androidx.core.view.InputDeviceCompat;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;
import com.aee.mokacam.bean.SendMsg;
import com.aee.mokacam.constants.AeeConstants;
import com.aee.mokacam.utils.ResolveJson;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static o a;
    static a b;
    String c = "000";
    boolean d = false;
    int e = 0;

    a() {
        a = o.a();
    }

    public static a a() {
        if (b == null) {
            b = new a();
        }
        if (!AeeApplication.a().b) {
            a.b();
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: */
    public boolean g() {
        String strSubstring;
        AeeApplication.a().c = true;
        if (AeeApplication.a().d > 5) {
            AeeApplication.a().b = false;
        } else {
            try {
                String json = new SendMsg(257, 0, null, null).toJson();
                String strC = a().c(json);
                if (strC.length() > 15) {
                    strSubstring = strC.indexOf("rval") <= 2 ? "{" + strC.split("\\{")[1] : "{" + strC.split("\\{")[2];
                } else {
                    try {
                        Thread.sleep(1080L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String strC2 = a().c(json);
                    strSubstring = strC2.substring(strC2.lastIndexOf("{"));
                }
                AeeApplication.a().c = false;
                ReceiveMsg receiveMsg = (ReceiveMsg) ResolveJson.resolveNormalInfo(strSubstring, ReceiveMsg.class);
                if (ResolveJson.checkRval(receiveMsg, 257)) {
                    AeeApplication.a().e = Integer.parseInt(receiveMsg.getParam().toString());
                    AeeApplication.a().d = 0;
                    AeeApplication.a().f = true;
                } else {
                    AeeApplication.a().d++;
                    AeeApplication.a().f = false;
                }
            } catch (Exception e2) {
                AeeApplication.a().d++;
                AeeApplication.a().f = false;
                e2.printStackTrace();
            }
        }
        return false;
    }

    boolean h() {
        try {
            ReceiveMsg receiveMsgA = a().a(new SendMsg(3, AeeApplication.a().e, null, null));
            if (receiveMsgA == null || receiveMsgA.getRval() < 0 || receiveMsgA.getMsg_id() != 3) {
                return false;
            }
            AeeApplication.a().q = com.aee.mokacam.utils.a.a(receiveMsgA.getParam().toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ReceiveMsg a(SendMsg sendMsg) {
        if (!(AeeApplication.a().e == -1000 ? g() : true)) {
            return null;
        }
        try {
            receiveMsg = (ReceiveMsg) ResolveJson.resolveNormalInfo(a().c(sendMsg.toJson()), ReceiveMsg.class);
            try {
                return !ResolveJson.checkRval(receiveMsg, sendMsg.getMsg_id()) ? (ReceiveMsg) ResolveJson.resolveNormalInfo(a().c(sendMsg.toJson()), ReceiveMsg.class) : receiveMsg;
            } catch (Exception e2) {
                e2.printStackTrace();
                return receiveMsg;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public void a(Handler handler) {
        new Thread(new g(this, String.valueOf(AeeConstants.a) + File.separator + "firmware.bin", handler)).start();
    }

    public void a(Handler handler, int i, boolean z) {
        a().a(new m(this, handler, i), 769, z);
    }

    public void a(n nVar) {
        new Thread(new b(this, nVar)).start();
    }

    public void a(n nVar, int i) {
        new Thread(new h(this, i, nVar)).start();
    }

    public void a(n nVar, int i, boolean z) {
        new Thread(new j(this, i, nVar)).start();
    }

    public void a(n nVar, SendMsg sendMsg) {
        new Thread(new k(this, sendMsg, nVar)).start();
    }

    public boolean a(String str) {
        a().a(new f(this), new SendMsg(261, str, "TCP"));
        try {
            TimeUnit.MILLISECONDS.sleep(280L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return AeeApplication.a().bk;
    }

    public boolean a(boolean z, Handler handler, boolean z2) {
        if (AeeApplication.a().f) {
            a().a(new l(this, handler), z ? InputDeviceCompat.SOURCE_DPAD : 514, z2);
        }
        return this.d;
    }

    public void b(n nVar) {
        new Thread(new i(this)).start();
        try {
            TimeUnit.MILLISECONDS.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean b() {
        return h();
    }

    public boolean b(String str) {
        return a.b(str, 100000L);
    }

    public String c(String str) {
        return a.a(str, 3000L);
    }

    public boolean c() {
        a(new c(this), new SendMsg(AeeConstants.j, AeeApplication.a().e, "none_force", null));
        return AeeApplication.a().aY;
    }

    public boolean d() {
        a().a(new d(this), 260);
        try {
            TimeUnit.MILLISECONDS.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return AeeApplication.a().ba;
    }

    public boolean e() {
        a().a(new e(this), new SendMsg(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), "camera_clock"));
        try {
            TimeUnit.MILLISECONDS.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return AeeApplication.a().bf;
    }

    public void f() {
        if (a != null) {
            a.e();
        }
        if (b != null) {
            b = null;
        }
    }
}
