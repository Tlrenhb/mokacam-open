package com.aee.mokacam.ui.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import com.aee.mokacam.MokacamApp;
import com.aee.mokacam.R;
import com.aee.mokacam.camera.CameraClient;
import com.aee.mokacam.camera.CameraFile;
import com.aee.mokacam.constants.AeeConstants;
import com.aee.mokacam.download.DownloadManager;
import com.aee.mokacam.ui.BaseActivity;
import com.aee.mokacam.util.ImageLoader;
import com.aee.mokacam.util.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Album grid with selection, download, delete and share.
 *
 * <p>Replacement of com.aee.zone.activity.LibraryActivity and its anonymous
 * worker classes. Two sources are supported, matching the original tabs:</p>
 * <ul>
 *   <li>camera album: entries come from msg 1283 on /tmp/SD0/moka/,
 *       thumbnails from the camera HTTP server,</li>
 *   <li>local album: entries come from DCIM/Mokacam on the phone.</li>
 * </ul>
 */
public class LibraryActivity extends BaseActivity {

    public static final String EXTRA_SOURCE = "source";
    public static final String SOURCE_CAMERA = "camera_lib";
    public static final String SOURCE_LOCAL = "local_lib";

    private String source = SOURCE_CAMERA;
    private GridView grid;
    private TextView tvCount;
    private View actionDelete;
    private View actionDownload;
    private View actionShare;
    private View btnSelectAll;
    private View btnBack;
    private final List<Row> rows = new ArrayList<>();
    private final List<Row> selected = new ArrayList<>();
    private GridAdapter adapter;

    public static class Row {
        public CameraFile cameraFile;
        public File localFile;
        public boolean selected;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        source = getIntent().getStringExtra(EXTRA_SOURCE);
        if (source == null) {
            source = SOURCE_CAMERA;
        }
        bindViews();
        setTitle(source.equals(SOURCE_CAMERA)
                ? R.string.camera_lib : R.string.local_lib);
        if (SOURCE_CAMERA.equals(source)) {
            loadCameraFiles();
        } else {
            loadLocalFiles();
        }
    }

    private void bindViews() {
        grid = find(R.id.grid_library);
        tvCount = find(R.id.tv_selected_num);
        actionDelete = find(R.id.btn_delete);
        actionDownload = find(R.id.btn_download);
        actionShare = find(R.id.btn_share);
        btnSelectAll = find(R.id.btn_select_all);
        btnBack = find(R.id.iv_library_back);

        actionDelete.setOnClickListener(v -> deleteSelected());
        actionDownload.setOnClickListener(v -> downloadSelected());
        actionShare.setOnClickListener(v -> shareSelected());
        btnSelectAll.setOnClickListener(v -> toggleSelectAll());
        btnBack.setOnClickListener(v -> finish());
        grid.setOnItemClickListener((parent, view, position, id) -> {
            Row row = rows.get(position);
            row.selected = !row.selected;
            view.setActivated(row.selected);
            updateSelectionUi();
        });
        grid.setOnItemLongClickListener((parent, view, position, id) -> {
            openViewer(rows.get(position));
            return true;
        });

        boolean cameraSource = SOURCE_CAMERA.equals(source);
        actionDownload.setVisibility(cameraSource ? View.VISIBLE : View.GONE);
        actionShare.setVisibility(View.VISIBLE);
    }

    private void loadCameraFiles() {
        new Thread(() -> {
            final List<CameraFile> files = CameraClient.get().listDir(AeeConstants.CAM_PATH_MOKA);
            runOnUiThread(() -> {
                rows.clear();
                for (CameraFile f : files) {
                    Row r = new Row();
                    r.cameraFile = f;
                    rows.add(r);
                }
                if (adapter == null) {
                    adapter = new GridAdapter();
                    grid.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
                tvCount.setText(String.valueOf(rows.size()));
            });
        }, "camera-list").start();
    }

    private void loadLocalFiles() {
        File dir = new File(getExternalFilesDir(null), AeeConstants.LOCAL_DCIM);
        File[] files = dir.listFiles();
        rows.clear();
        if (files != null) {
            for (File f : files) {
                Row r = new Row();
                r.localFile = f;
                rows.add(r);
            }
        }
        adapter = new GridAdapter();
        grid.setAdapter(adapter);
        tvCount.setText(String.valueOf(rows.size()));
    }

    private void updateSelectionUi() {
        selected.clear();
        for (Row r : rows) {
            if (r.selected) {
                selected.add(r);
            }
        }
        tvCount.setText(selected.isEmpty()
                ? String.valueOf(rows.size()) : getString(R.string.done) + " " + selected.size());
    }

    private void toggleSelectAll() {
        boolean anyUnselected = false;
        for (Row r : rows) {
            if (!r.selected) {
                anyUnselected = true;
                break;
            }
        }
        for (Row r : rows) {
            r.selected = anyUnselected;
        }
        adapter.notifyDataSetChanged();
        updateSelectionUi();
    }

    private void downloadSelected() {
        if (selected.isEmpty()) {
            ToastUtils.show(this, R.string.library_download);
            return;
        }
        File destDir = new File(getExternalFilesDir(null), AeeConstants.LOCAL_DCIM);
        boolean queuedAny = false;
        for (Row r : selected) {
            if (r.cameraFile == null) {
                continue;
            }
            String url = r.cameraFile.getDownloadUrl();
            String dest = new File(destDir, r.cameraFile.getName()).getAbsolutePath();
            if (new File(dest).exists()) {
                ToastUtils.show(this, R.string.singleisdownloadedwarning);
                continue;
            }
            queuedAny |= DownloadManager.get().enqueue(url, dest);
        }
        if (queuedAny) {
            ToastUtils.show(this, R.string.singlestartDownlad);
            startActivity(new Intent(this, DownloadActivity.class));
        }
    }

    private void deleteSelected() {
        if (selected.isEmpty()) {
            ToastUtils.show(this, R.string.library_delete);
            return;
        }
        if (SOURCE_CAMERA.equals(source)) {
            new Thread(() -> {
                CameraClient c = CameraClient.get();
                int fail = 0;
                for (Row r : new ArrayList<>(selected)) {
                    if (r.cameraFile != null && !c.deleteFile(r.cameraFile.getCameraPath())) {
                        fail++;
                    }
                }
                final int failures = fail;
                runOnUiThread(() -> {
                    if (failures > 0) {
                        ToastUtils.show(LibraryActivity.this,
                                getString(R.string.delete_failed) + " " + failures);
                    } else {
                        ToastUtils.show(LibraryActivity.this, R.string.delete_success);
                    }
                    loadCameraFiles();
                });
            }, "camera-delete").start();
        } else {
            for (Row r : new ArrayList<>(selected)) {
                if (r.localFile != null) {
                    r.localFile.delete();
                }
            }
            ToastUtils.show(this, R.string.delete_success);
            loadLocalFiles();
        }
        updateSelectionUi();
    }

    private void shareSelected() {
        if (selected.isEmpty()) {
            ToastUtils.show(this, R.string.library_share);
            return;
        }
        ArrayList<android.net.Uri> uris = new ArrayList<>();
        for (Row r : selected) {
            File f = resolveShareable(r);
            if (f != null && f.exists()) {
                uris.add(FileProvider.getUriForFile(this,
                        getPackageName() + ".fileprovider", f));
            }
        }
        if (uris.isEmpty()) {
            ToastUtils.show(this, R.string.download_warning);
            return;
        }
        android.content.Intent share = new android.content.Intent();
        share.setAction(android.content.Intent.ACTION_SEND_MULTIPLE);
        share.putParcelableArrayListExtra(android.content.Intent.EXTRA_STREAM, uris);
        share.setType("*/*");
        share.addFlags(android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(android.content.Intent.createChooser(share, getString(R.string.share)));
        // The original app used Mob ShareSDK (WeChat / QQ / Weibo); on modern
        // Android the system share sheet covers the same destinations.
    }

    private File resolveShareable(Row r) {
        if (r.localFile != null) {
            return r.localFile;
        }
        if (r.cameraFile != null) {
            File local = new File(new File(getExternalFilesDir(null), AeeConstants.LOCAL_DCIM),
                    r.cameraFile.getName());
            return local.exists() ? local : null;
        }
        return null;
    }

    private void openViewer(Row row) {
        Intent i = new Intent(this, ShowMediaActivity.class);
        if (row.cameraFile != null) {
            i.putExtra(ShowMediaActivity.EXTRA_URL, row.cameraFile.getDownloadUrl());
            i.putExtra(ShowMediaActivity.EXTRA_IS_VIDEO, row.cameraFile.isVideo());
            i.putExtra(ShowMediaActivity.EXTRA_NAME, row.cameraFile.getName());
        } else if (row.localFile != null) {
            i.putExtra(ShowMediaActivity.EXTRA_URL,
                    android.net.Uri.fromFile(row.localFile).toString());
            i.putExtra(ShowMediaActivity.EXTRA_IS_VIDEO, row.localFile.getName().endsWith(".mp4")
                    || row.localFile.getName().endsWith(".MP4"));
            i.putExtra(ShowMediaActivity.EXTRA_NAME, row.localFile.getName());
        }
        startActivity(i);
    }

    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return rows.size();
        }

        @Override
        public Row getItem(int position) {
            return rows.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                v = LayoutInflater.from(LibraryActivity.this)
                        .inflate(R.layout.item_library_cell, parent, false);
            }
            ImageView img = v.findViewById(R.id.iv_thumb);
            ImageView mark = v.findViewById(R.id.iv_selected);
            Row row = rows.get(position);
            if (row.cameraFile != null) {
                ImageLoader.get().load(row.cameraFile.getThumbnailUrl(), img);
            } else if (row.localFile != null) {
                android.graphics.Bitmap bmp = android.graphics.ThumbnailUtils
                        .createVideoThumbnail(row.localFile.getAbsolutePath(),
                                android.provider.MediaStore.Video.Thumbnails.MINI_KIND);
                if (bmp != null) {
                    img.setImageBitmap(bmp);
                } else {
                    img.setImageResource(R.drawable.ic_photo_placeholder);
                }
            }
            mark.setVisibility(row.selected ? View.VISIBLE : View.GONE);
            v.setActivated(row.selected);
            return v;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SOURCE_CAMERA.equals(source) && MokacamApp.get().cameraConnected) {
            loadCameraFiles();
        }
    }
}
