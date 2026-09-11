package com.aee.mokacam.ui.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aee.mokacam.MokacamApp;
import com.aee.mokacam.R;
import com.aee.mokacam.ui.BaseActivity;
import com.aee.mokacam.util.ToastUtils;

/**
 * Chooser between the camera album and the local album.
 *
 * <p>Replacement of com.aee.zone.activity.SelectLibraryActivity /
 * SelectSimpleActivity.</p>
 */
public class SelectLibraryActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvCamera;
    private TextView tvLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_library);
        tvCamera = find(R.id.rl_camera_lib);
        tvLocal = find(R.id.rl_local_lib);
        RelativeLayout back = find(R.id.iv_library_back);
        tvCamera.setOnClickListener(this);
        tvLocal.setOnClickListener(this);
        if (back != null) {
            back.setOnClickListener(v -> finish());
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent i = new Intent(this, LibraryActivity.class);
        if (id == R.id.rl_camera_lib) {
            if (!MokacamApp.get().cameraConnected) {
                ToastUtils.show(this, R.string.connect_wifi);
                return;
            }
            i.putExtra(LibraryActivity.EXTRA_SOURCE, LibraryActivity.SOURCE_CAMERA);
        } else if (id == R.id.rl_local_lib) {
            i.putExtra(LibraryActivity.EXTRA_SOURCE, LibraryActivity.SOURCE_LOCAL);
        } else {
            return;
        }
        startActivity(i);
    }
}
