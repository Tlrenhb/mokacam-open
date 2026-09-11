package com.nostra13.universalimageloader.b;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public static void a(InputStream inputStream) {
        do {
            try {
            } catch (IOException e) {
                return;
            } finally {
                a((Closeable) inputStream);
            }
        } while (inputStream.read(new byte[32768], 0, 32768) != -1);
    }

    private static boolean a(d dVar, int i, int i2) {
        return (dVar == null || dVar.a(i, i2) || (i * 100) / i2 >= 75) ? false : true;
    }

    public static boolean a(InputStream inputStream, OutputStream outputStream, d dVar, int i) throws IOException {
        int iAvailable = inputStream.available();
        if (iAvailable <= 0) {
            iAvailable = 512000;
        }
        byte[] bArr = new byte[i];
        if (a(dVar, 0, iAvailable)) {
            return false;
        }
        int i2 = 0;
        do {
            int i3 = inputStream.read(bArr, 0, i);
            if (i3 == -1) {
                outputStream.flush();
                return true;
            }
            outputStream.write(bArr, 0, i3);
            i2 += i3;
        } while (!a(dVar, i2, iAvailable));
        return false;
    }
}
