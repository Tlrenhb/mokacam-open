package com.aee.mokacam.camera;

import com.aee.mokacam.constants.AeeConstants;

import java.util.Locale;

/**
 * One entry of a camera directory listing.
 *
 * <p>The listing array of msg 1283 has the form
 * {@code [{"IMG_0001.JPG": "a 121234 2017-01-01 10:00:00"}]} where the value
 * carries attributes: attribute-flags, size in bytes, and modification time.
 *
 * <p>Replacement of the original com.aee.zone.bean.g.</p>
 */
public class CameraFile {

    private final String name;
    private final String attrs;
    private final String dir;
    private final boolean directory;
    private long size;
    private String date;

    public CameraFile(String name, String attrs, String dir) {
        this.name = name;
        this.attrs = attrs;
        this.dir = dir;
        this.directory = name.endsWith("/");
        parseAttrs(attrs);
    }

    private void parseAttrs(String attrs) {
        if (attrs == null) {
            return;
        }
        // Attribute string form: "<flags> <size> <yyyy-MM-dd> <HH:mm:ss>"
        String[] parts = attrs.trim().split("\\s+");
        if (parts.length >= 2) {
            try {
                size = Long.parseLong(parts[1]);
            } catch (NumberFormatException ignored) {
            }
        }
        if (parts.length >= 4) {
            date = parts[2] + " " + parts[3];
        } else if (parts.length == 3) {
            date = parts[2];
        } else if (parts.length == 1) {
            date = parts[0];
        }
    }

    public String getName() {
        return name;
    }

    public String getAttrs() {
        return attrs;
    }

    public String getDir() {
        return dir;
    }

    public boolean isDirectory() {
        return directory;
    }

    public long getSize() {
        return size;
    }

    public String getDate() {
        return date;
    }

    public boolean isPhoto() {
        return name.toUpperCase(Locale.US).endsWith(".JPG");
    }

    public boolean isVideo() {
        String u = name.toUpperCase(Locale.US);
        return u.endsWith(".MP4") || u.endsWith(".MOV");
    }

    /** Absolute path of this entry on the camera file system. */
    public String getCameraPath() {
        return dir + name;
    }

    /** HTTP URL for downloading the full file. */
    public String getDownloadUrl() {
        return CameraClient.downloadUrl(name);
    }

    /** HTTP URL for the thumbnail served by the camera. */
    public String getThumbnailUrl() {
        return CameraClient.thumbnailUrl(name, attrs);
    }

    @Override
    public String toString() {
        return "CameraFile{" + name + ", size=" + size + ", date=" + date + "}";
    }
}
