package com.aee.mokacam;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.aee.mokacam.R;
import com.aee.mokacam.activity.ProductActivity;
import com.aee.mokacam.bean.c;
import com.aee.mokacam.bean.e;
import com.aee.mokacam.bean.f;
import com.aee.mokacam.bean.g;
import com.aee.mokacam.bean.i;
import com.aee.mokacam.bean.k;
import com.aee.mokacam.bean.n;
import com.aee.mokacam.bean.o;
import com.aee.mokacam.bean.q;
import com.aee.mokacam.constants.AeeConstants;
import com.aee.mokacam.utils.p;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.xutils.BuildConfig;
import org.xutils.x;

/* JADX INFO: loaded from: classes.dex */
public class AeeApplication extends Application {
    private static AeeApplication bp;
    public c A;
    public e B;
    public n C;
    public boolean M;
    public o O;
    public List<q> aM;
    public List<Activity> aX;
    public k ad;
    public Map<String, String> af;
    public i ar;
    public boolean ba;
    public String bh;
    private String br;
    public Map<String, String[]> p;
    public Map<String, String> q;
    public List<g> r;
    public List<g> s;
    public List<g> t;
    public f z;
    public static int at = 1;
    public static int aw = 0;
    public static boolean ax = false;
    public static float ay = 1.0f;
    public static boolean az = false;
    public static double aA = -1.0d;
    public static boolean aB = true;
    public static String aC = BuildConfig.FLAVOR;
    public static boolean aD = false;
    public static boolean aE = true;
    public static boolean aF = true;
    public static boolean aG = false;
    public static boolean aH = false;
    public static boolean aI = false;
    public static boolean aJ = false;
    public static boolean aK = false;
    public static double aR = 52.35987755982988d;
    public static double aS = 3.141592653589793d;
    public static int aT = R.id.tv_signal_shot;
    public static String aU = "off";
    public static String aV = "off";
    public static String aW = "off";
    public int a = -1;
    public boolean b = false;
    public boolean c = false;
    public int d = 0;
    public int e = -1000;
    public boolean f = false;
    public String g = "/tmp/fuse_d/DCIM/100MEDIA/";
    public String h = "http://192.168.42.1/SD/moka/";
    public boolean i = false;
    public boolean j = false;
    public int k = 0;
    public boolean l = false;
    public boolean m = false;
    public boolean n = false;
    public int o = 0;
    public int u = 0;
    public int v = 0;
    public boolean w = false;
    public boolean x = false;
    public int y = 0;
    public boolean D = false;
    public boolean E = false;
    public AeeConstants.DataAction F = AeeConstants.DataAction.receive_send;
    public int G = 0;
    public long H = 0;
    public boolean I = false;
    public long J = 0;
    public boolean K = false;
    public boolean L = true;
    public boolean N = false;
    public boolean P = false;
    public String Q = BuildConfig.FLAVOR;
    public byte R = -1;
    public long S = 0;
    public int T = 0;
    public int U = 0;
    public boolean V = false;
    public boolean W = false;
    public long X = 0;
    public AeeConstants.DroneState Y = AeeConstants.DroneState.NORMAL;
    public int[] Z = new int[8];
    public long aa = 0;
    public int[] ab = {30, 10};
    public boolean ac = false;
    public boolean ae = false;
    public boolean ag = false;
    public boolean ah = false;
    public boolean ai = false;
    public boolean aj = false;
    public long ak = 0;
    public int al = 0;
    public int am = 0;
    public int an = 0;
    public boolean ao = false;
    public boolean ap = false;
    public byte[] aq = new byte[18];
    public boolean as = false;
    public boolean au = true;
    private boolean bq = false;
    public ArrayList<String> av = new ArrayList<>();
    public int aL = 0;
    public int aN = 100;
    public int aO = 0;
    public boolean aP = false;
    public boolean aQ = false;
    public boolean aY = false;
    public boolean aZ = false;
    public boolean bb = false;
    public boolean bc = false;
    public boolean bd = false;
    public boolean be = false;
    public boolean bf = false;
    public boolean bg = false;
    public boolean bi = false;
    public boolean bj = false;
    public boolean bk = false;
    public boolean bl = false;
    public boolean bm = true;
    public boolean bn = false;
    public String bo = BuildConfig.FLAVOR;

    public static AeeApplication a() {
        if (bp == null) {
            try {
                throw new Exception("Application is null!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bp;
    }

    public static Context b() {
        return bp;
    }

    private void f() {
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
    }

    private void g() {
        File file = new File(AeeConstants.a);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(AeeConstants.c);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        File file3 = new File(AeeConstants.e);
        if (!file3.exists()) {
            file3.mkdirs();
        }
        File file4 = new File(AeeConstants.f);
        if (file4.exists()) {
            return;
        }
        file4.mkdirs();
    }

    public native int DeviceInit(int i, String str, String str2);

    public native int DeviceLogout(int i);

    public native int GetCrc(byte[] bArr);

    public native int[] GetUpdateInfo();

    public native int SetCommand(String str, int i, int i2);

    public native String UpdateInit(String str);

    public int a(String str) {
        int i = 2;
        int i2 = 0;
        if (str.length() <= 10) {
            i = i2;
        } else if (str.contains("AEE_CONDOR")) {
            this.R = (byte) 2;
        } else {
            if (str.contains("AEE_RC_CON")) {
                i2 = 3;
                this.R = (byte) 2;
            }
            i = i2;
        }
        if (i == 0) {
            this.R = (byte) -1;
        }
        return i;
    }

    public void a(long j) {
        byte[] bArr = new byte[32];
        for (int i = 0; i < 32; i++) {
            if (((byte) ((j >>> i) & 1)) == 1) {
                bArr[i] = 1;
            } else {
                bArr[i] = 0;
            }
        }
        if (bArr[29] == 1) {
            this.Y = AeeConstants.DroneState.GPS;
        } else if (bArr[30] == 1) {
            this.Y = AeeConstants.DroneState.VISVAL;
        } else if (bArr[31] == 1) {
            this.Y = AeeConstants.DroneState.NORMAL;
        }
        for (int i2 = 0; i2 < 8; i2++) {
            this.Z[i2] = bArr[i2 + 21];
        }
    }

    public void a(Activity activity) {
        if (this.aX == null) {
            this.aX = new ArrayList();
        }
        this.aX.add(activity);
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public void b(String str) {
        this.br = str;
    }

    public boolean c() {
        return getResources().getConfiguration().locale.getLanguage().endsWith("zh");
    }

    public void d() {
        for (Activity activity : this.aX) {
            if (activity != null && !activity.getClass().getName().contains("MainActivity")) {
                activity.finish();
            }
        }
    }

    public void e() {
        for (Activity activity : this.aX) {
            if (!(activity instanceof ProductActivity)) {
                activity.finish();
            }
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        bp = this;
        g();
        x.Ext.init(this);
        f();
        g();
        p.a().a(this);
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
    }
}
