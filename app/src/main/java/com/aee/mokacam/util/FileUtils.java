package com.aee.mokacam.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * File/IO helpers. Replacement of the original com.aee.zone.utils.e/g/h/i/r/u.
 */
public final class FileUtils {

    private FileUtils() {
    }

    public static void ensureDirs(String... paths) {
        if (paths == null) {
            return;
        }
        for (String p : paths) {
            if (p != null) {
                File f = new File(p);
                if (!f.exists()) {
                    f.mkdirs();
                }
            }
        }
    }

    public static long dirSize(File dir) {
        if (dir == null || !dir.exists()) {
            return 0;
        }
        if (dir.isFile()) {
            return dir.length();
        }
        long size = 0;
        File[] children = dir.listFiles();
        if (children != null) {
            for (File c : children) {
                size += dirSize(c);
            }
        }
        return size;
    }

    public static void deleteRecursive(File f) {
        if (f == null || !f.exists()) {
            return;
        }
        if (f.isDirectory()) {
            File[] children = f.listFiles();
            if (children != null) {
                for (File c : children) {
                    deleteRecursive(c);
                }
            }
        }
        f.delete();
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[8192];
        int n;
        while ((n = in.read(buf)) > 0) {
            out.write(buf, 0, n);
        }
    }

    public static boolean copyFile(String src, String dst) {
        try {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst);
            copy(in, out);
            in.close();
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /** Human readable size, used by the cache view. */
    public static String humanSize(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        }
        double kb = bytes / 1024.0;
        if (kb < 1024) {
            return String.format(java.util.Locale.US, "%.1f KB", kb);
        }
        double mb = kb / 1024.0;
        if (mb < 1024) {
            return String.format(java.util.Locale.US, "%.1f MB", mb);
        }
        return String.format(java.util.Locale.US, "%.2f GB", mb / 1024.0);
    }
}
