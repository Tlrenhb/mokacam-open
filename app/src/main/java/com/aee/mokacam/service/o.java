package com.aee.mokacam.service;

import android.os.Handler;
import android.os.Message;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.bean.ReceiveMsg;
import com.aee.mokacam.utils.ResolveJson;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
import org.xutils.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class o {
    private static o c;
    private static InputStream e;
    private static OutputStream f;
    private static int l = 8787;
    private boolean b;
    private Socket d;
    private List<Handler> g;
    private boolean j;
    private boolean k;
    private Handler h = new Handler();
    private boolean i = false;
    Runnable a = new p(this);

    private o() {
        this.b = false;
        this.b = true;
    }

    public static o a() {
        if (c == null) {
            c = new o();
        }
        return c;
    }

    private void a(int i, Object obj) {
        if (this.g != null) {
            for (Handler handler : this.g) {
                if (handler != null) {
                    Message messageObtainMessage = handler.obtainMessage();
                    messageObtainMessage.what = i;
                    messageObtainMessage.obj = obj;
                    handler.sendMessage(messageObtainMessage);
                }
            }
        }
    }

    public static boolean a(String str) {
        try {
            new JSONObject(str);
            return true;
        } catch (Exception e2) {
            return false;
        }
    }

    private String c(String str, long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        while (!a(str) && System.currentTimeMillis() - jCurrentTimeMillis <= j) {
            try {
                int iAvailable = e.available();
                if (iAvailable > 0) {
                    byte[] bArr = new byte[iAvailable];
                    for (int i = 0; i < iAvailable; i += e.read(bArr, i, iAvailable - i)) {
                    }
                    String str2 = new String(bArr);
                    a(32780, str2);
                    str = String.valueOf(str) + str2;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int d(ReceiveMsg receiveMsg) {
        String type = receiveMsg.getType();
        int msg_id = receiveMsg.getMsg_id();
        if (msg_id == 1798 && type.contains("z09")) {
            return msg_id;
        }
        if (msg_id == 1799 && type.contains("z09")) {
            return msg_id;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        a(32778, (Object) null);
    }

    public synchronized String a(String str, long j) {
        String str2;
        String str3;
        Exception e2;
        byte[] bArr;
        str2 = null;
        if (!this.j) {
            this.i = true;
            byte[] bArr2 = new byte[1024];
            try {
                System.out.println("sendMessegeForResult--->sendmsg" + str);
                b(str);
                if (e == null) {
                    e = this.d.getInputStream();
                }
                this.k = false;
                this.h.postDelayed(this.a, j);
                while (true) {
                    if (this.k) {
                        this.k = false;
                        bArr = bArr2;
                        break;
                    }
                    int iAvailable = e.available();
                    bArr2 = new byte[iAvailable];
                    for (int i = 0; i < iAvailable; i += e.read(bArr2, i, iAvailable - i)) {
                    }
                    if (iAvailable != 0) {
                        bArr = bArr2;
                        break;
                    }
                }
                this.h.removeCallbacks(this.a);
                str3 = new String(bArr);
                try {
                    a(32780, str3);
                    str3 = c(str3, j);
                    System.out.println("sendMessegeForResult--->receive" + str3);
                    str2 = str3;
                } catch (Exception e3) {
                    e2 = e3;
                    e2.printStackTrace();
                    AeeApplication.a().b = false;
                    str2 = str3;
                }
            } catch (Exception e4) {
                str3 = null;
                e2 = e4;
            }
            this.i = false;
        }
        return str2;
    }

    public void a(Handler handler) {
        if (this.g == null) {
            this.g = new ArrayList();
        }
        if (handler == null && this.g.contains(handler)) {
            return;
        }
        this.g.add(handler);
    }

    protected boolean a(ReceiveMsg receiveMsg) {
        if (receiveMsg == null) {
            return false;
        }
        return receiveMsg.getMsg_id() == 514 && receiveMsg.getType().contains("z09");
    }

    public void b() {
        new Thread(new q(this)).start();
    }

    public void b(Handler handler) {
        this.g.remove(handler);
    }

    protected boolean b(ReceiveMsg receiveMsg) {
        if (receiveMsg == null) {
            return false;
        }
        return receiveMsg.getMsg_id() == 513 && receiveMsg.getType().contains("z09");
    }

    synchronized boolean b(String str) {
        boolean z;
        try {
            if (f == null && this.d != null) {
                f = this.d.getOutputStream();
            }
            f.write(str.getBytes("utf-8"));
            f.flush();
            a(32779, str);
            if (AeeApplication.a().c) {
                Thread.sleep(1200L);
            }
            z = true;
        } catch (Exception e2) {
            e2.printStackTrace();
            z = false;
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized boolean b(String str, long j) {
        ReceiveMsg receiveMsg;
        boolean z = true;
        synchronized (this) {
            String str2 = BuildConfig.FLAVOR;
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress("192.168.42.1", l), 5000);
                System.out.println("connect------>sendFile:" + socket.isConnected());
                OutputStream outputStream = socket.getOutputStream();
                Thread.sleep(200L);
                this.k = false;
                this.h.postDelayed(this.a, j);
                FileInputStream fileInputStream = new FileInputStream(str);
                int iAvailable = fileInputStream.available();
                System.out.println("availen------------>" + iAvailable);
                if (iAvailable > 0) {
                    byte[] bArr = new byte[iAvailable];
                    int i = 0;
                    while (i < iAvailable) {
                        i += fileInputStream.read(bArr, i, iAvailable - i);
                        outputStream.write(bArr);
                        outputStream.flush();
                        str2 = String.valueOf(str2) + new String(bArr, "utf-8");
                    }
                }
                fileInputStream.close();
                outputStream.close();
                socket.close();
                this.i = true;
                receiveMsg = (ReceiveMsg) ResolveJson.resolveNormalInfo(str2, ReceiveMsg.class);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (receiveMsg == null || !receiveMsg.getType().equals("put_file_complete")) {
                z = false;
                this.i = false;
                try {
                    TimeUnit.MILLISECONDS.sleep(280L);
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
            } else {
                File file = new File(str);
                if (file.exists()) {
                    if (!com.aee.mokacam.utils.a.a(file).equals(receiveMsg.getMd5sum())) {
                    }
                    this.i = false;
                    TimeUnit.MILLISECONDS.sleep(280L);
                }
            }
        }
        return z;
    }

    protected void c() {
        new Thread(new r(this)).start();
    }

    protected synchronized boolean c(ReceiveMsg receiveMsg) {
        boolean z = false;
        synchronized (this) {
            if (receiveMsg != null) {
                String type = receiveMsg.getType();
                if (receiveMsg.getMsg_id() == 769) {
                    if (type.contains("z09")) {
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    protected void d() {
        new Thread(new u(this)).start();
    }

    protected void e() {
        this.b = false;
        AeeApplication.a().b = false;
        try {
            if (e != null) {
                e.close();
                e = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            if (f != null) {
                f.close();
                f = null;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            if (this.d != null) {
                this.d.close();
                this.d = null;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }
}
