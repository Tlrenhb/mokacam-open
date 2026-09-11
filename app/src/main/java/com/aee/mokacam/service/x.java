package com.aee.mokacam.service;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.constants.AeeConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class x {
    private static InputStream D;
    private static OutputStream E;
    private static List<Handler> G;
    private static al I;
    private static Context J;
    public static byte[] c;
    private static x z;
    private Socket C;
    private boolean F;
    private com.aee.mokacam.bean.a K;
    private long N;
    private static String A = "192.168.3.60";
    private static int B = 7978;
    public static int d = 4096;
    public static int e = 0;
    public static int x = 0;
    protected boolean a = false;
    private long H = 0;
    public int b = 0;
    public String f = BuildConfig.FLAVOR;
    public boolean g = false;
    public String h = BuildConfig.FLAVOR;
    public boolean i = false;
    public boolean j = true;
    public int k = 0;
    public boolean l = true;
    public boolean m = false;
    public boolean n = false;
    public boolean o = false;
    public boolean p = false;
    public boolean q = false;
    public Socket r = null;
    public int s = 0;
    public long t = 0;
    public boolean u = false;
    private boolean L = false;
    public boolean v = false;
    public int w = 0;
    private boolean M = false;
    private boolean O = true;
    private long P = 0;
    private long Q = 0;
    private boolean R = false;
    private boolean S = false;
    public String y = BuildConfig.FLAVOR;

    public x() {
        this.F = false;
        this.F = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Object obj) {
        for (Handler handler : G) {
            if (handler != null) {
                Message message = new Message();
                message.what = i;
                message.obj = obj;
                handler.sendMessage(message);
            }
        }
    }

    private int[] a(byte[] bArr, int i) {
        int i2 = 0;
        int[] iArr = new int[10];
        byte b = (byte) AeeConstants.q;
        for (int i3 = 0; i3 < i; i3++) {
            if (bArr[i3] == b && i2 < 10) {
                if (i3 + 1 >= i) {
                    break;
                }
                if (bArr[i3 + 1] + 8 + i3 <= i) {
                    iArr[i2] = i3 + 1;
                    i2++;
                }
            } else {
                if (i2 >= 10) {
                    break;
                }
            }
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(byte[] bArr, int i) {
        int[] iArr = new int[10];
        int[] iArrA = a(bArr, i);
        for (int i2 = 0; i2 < 10; i2++) {
            if (iArrA[i2] > 0) {
                return iArrA[i2];
            }
        }
        return -1;
    }

    private void b(int i, Object obj) {
        if (G != null) {
            try {
                for (Handler handler : G) {
                    if (handler != null) {
                        Message messageObtainMessage = handler.obtainMessage();
                        messageObtainMessage.what = i;
                        messageObtainMessage.obj = obj;
                        handler.sendMessage(messageObtainMessage);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void b(byte[] bArr) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            Log.d("TestFile", "SD card is not avaiable/writeable right now.");
            return;
        }
        try {
            String str = String.valueOf(AeeConstants.d) + "/";
            String str2 = String.valueOf(AeeApplication.a().Q) + ".txt";
            File file = new File(str);
            File file2 = new File(String.valueOf(str) + str2);
            if (!file.exists()) {
                Log.d("TestFile", "Create the path:" + str);
                file.mkdir();
            }
            if (!file2.exists()) {
                Log.d("TestFile", "Create the file:" + str2);
                file2.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file2, true);
            fileOutputStream.write(bArr);
            fileOutputStream.close();
        } catch (Exception e2) {
            Log.e("TestFile", "Error on writeFilToSD.");
            e2.printStackTrace();
        }
    }

    public static x c() {
        if (z == null) {
            z = new x();
            c = new byte[d];
            AeeApplication.a();
            J = AeeApplication.b();
        }
        return z;
    }

    public static boolean i() {
        AeeApplication.a().getApplicationContext();
        return ((KeyguardManager) AeeApplication.a().getApplicationContext().getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        com.aee.mokacam.bean.d dVar = new com.aee.mokacam.bean.d(2);
        if (AeeApplication.a().ao) {
            dVar.a(AeeApplication.a().B.a());
        }
        a(dVar.b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        com.aee.mokacam.bean.d dVar = new com.aee.mokacam.bean.d(2);
        dVar.a();
        byte[] bArrB = dVar.b();
        a(bArrB);
        com.aee.mokacam.utils.m.b("test", "20161117- msg1 = " + com.aee.mokacam.utils.k.a(com.aee.mokacam.utils.k.a(bArrB).toUpperCase(), 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        new Thread(new aa(this)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        new ab(this).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        b(32778, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if ((this.Q == 0 || jCurrentTimeMillis - this.Q > 1000) && AeeApplication.a().aj) {
            try {
                String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) new java.sql.Date(System.currentTimeMillis()));
                b((String.valueOf(str) + "LATLONG:" + AeeApplication.a().z.e() + "," + AeeApplication.a().z.d() + "\n").getBytes());
                this.Q = jCurrentTimeMillis;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    synchronized boolean a(byte[] bArr) {
        boolean z2;
        try {
            if (E == null && this.C != null) {
                E = this.C.getOutputStream();
            }
            com.aee.mokacam.utils.m.a("FlightControl", "20160908---发送1--->" + com.aee.mokacam.utils.k.a(com.aee.mokacam.utils.k.a(bArr).toUpperCase(), 2));
            E.write(bArr);
            E.flush();
            b(32794, bArr);
            this.H = System.currentTimeMillis();
            z2 = true;
        } catch (Exception e2) {
            e2.printStackTrace();
            z2 = false;
        }
        return z2;
    }

    public byte[] a() {
        byte[] bArr = new byte[18];
        System.arraycopy(com.aee.mokacam.utils.c.a(1500), 0, bArr, 0, 2);
        System.arraycopy(com.aee.mokacam.utils.c.a(1500), 0, bArr, 2, 2);
        System.arraycopy(com.aee.mokacam.utils.c.a(1500), 0, bArr, 4, 2);
        System.arraycopy(com.aee.mokacam.utils.c.a(1500), 0, bArr, 6, 2);
        for (int i = 8; i < 18; i++) {
            bArr[i] = 0;
        }
        return bArr;
    }

    public void b() {
        this.K = new com.aee.mokacam.bean.a(com.aee.mokacam.bean.a.c[1], a());
        this.K.b();
    }

    public boolean d() {
        return AeeApplication.a().F == AeeConstants.DataAction.receive || AeeApplication.a().F == AeeConstants.DataAction.receive_send;
    }

    public boolean e() {
        return AeeApplication.a().F == AeeConstants.DataAction.send || AeeApplication.a().F == AeeConstants.DataAction.receive_send;
    }

    public synchronized void f() {
        new Thread(new y(this)).start();
    }

    public void g() {
        new Thread(new z(this)).start();
    }

    public byte[] h() {
        com.aee.mokacam.bean.o oVar = AeeApplication.a().O;
        oVar.d();
        return oVar.c();
    }

    void j() {
        this.F = false;
        AeeApplication.a().bi = false;
        AeeApplication.a().ac = false;
        AeeApplication.a().ao = false;
        try {
            if (D != null) {
                D.close();
                D = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            if (E != null) {
                E.close();
                E = null;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            if (this.C != null) {
                this.C.close();
                this.C = null;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }
}
