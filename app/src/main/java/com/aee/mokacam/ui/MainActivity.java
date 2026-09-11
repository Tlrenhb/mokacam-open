package com.aee.mokacam.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import com.aee.mokacam.MokacamApp;
import com.aee.mokacam.R;
import com.aee.mokacam.camera.CameraClient;
import com.aee.mokacam.camera.CameraSession;
import com.aee.mokacam.constants.AeeConstants;
import com.aee.mokacam.ui.camera.CameraLiveActivity;
import com.aee.mokacam.ui.info.LegalActivity;
import com.aee.mokacam.ui.info.ProductParamsActivity;
import com.aee.mokacam.ui.info.SupportActivity;
import com.aee.mokacam.ui.library.SelectLibraryActivity;
import com.aee.mokacam.ui.settings.AppSettingsActivity;
import com.aee.mokacam.util.ToastUtils;
import com.aee.mokacam.util.WifiUtils;

/**
 * Main hub screen. Replacement of com.aee.zone.activity.MainActivity:
 *
 * <ul>
 *   <li>"Connect camera" button: checks the phone is on 192.168.42.x,
 *       connects the TCP channel and hands the token handshake, then opens
 *       the live view,</li>
 *   <li>Library entry (camera + local albums),</li>
 *   <li>Sliding menu: product specs, shop, settings, support, legal.</li>
 * </ul>
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final int MSG_CONNECTED = 32771;
    private static final int MSG_OPEN_LIBRARY = 7;

    private TextView tvConnect;
    private TextView tvLibrary;
    private TextView tvShop;
    private TextView tvSetting;
    private TextView tvSupport;
    private TextView tvLegal;
    private TextView productSpecs;
    private View connectButton;
    private View libraryButton;
    private View menuButton;
    private View backArrow;
    private View leftPanel;
    private boolean menuOpen;
    private boolean connecting;
    private final Handler ui = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(android.os.Message msg) {
            if (msg.what == MSG_CONNECTED) {
                onConnectCheckDone(true);
            } else if (msg.what == MSG_OPEN_LIBRARY) {
                startActivity(new Intent(MainActivity.this, SelectLibraryActivity.class));
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private void bindViews() {
        tvConnect = find(R.id.tv_main_connect);
        tvLibrary = find(R.id.tv_libary);
        tvShop = find(R.id.tv_shop);
        tvSetting = find(R.id.tv_setting);
        tvSupport = find(R.id.tv_support);
        tvLegal = find(R.id.tv_legal);
        productSpecs = find(R.id.product_specs);
        connectButton = find(R.id.rl_main_connect);
        libraryButton = find(R.id.iv_library);
        menuButton = find(R.id.menu);
        backArrow = find(R.id.iv_arrow_back);
        leftPanel = find(R.id.left_frame);

        connectButton.setOnClickListener(this);
        libraryButton.setOnClickListener(this);
        menuButton.setOnClickListener(this);
        backArrow.setOnClickListener(this);
        tvShop.setOnClickListener(this);
        tvSetting.setOnClickListener(this);
        tvSupport.setOnClickListener(this);
        tvLegal.setOnClickListener(this);
        productSpecs.setOnClickListener(this);
        leftPanel.setOnClickListener(v -> toggleMenu());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rl_main_connect) {
            tryConnectCamera();
        } else if (id == R.id.iv_library) {
            openLibrary();
        } else if (id == R.id.menu || id == R.id.iv_arrow_back || id == R.id.left_frame) {
            toggleMenu();
        } else if (id == R.id.product_specs) {
            startActivity(new Intent(this, ProductParamsActivity.class));
        } else if (id == R.id.tv_shop) {
            openShop();
        } else if (id == R.id.tv_setting) {
            Intent i = new Intent(this, AppSettingsActivity.class);
            i.putExtra("main_go", "main_camera");
            startActivity(i);
        } else if (id == R.id.tv_support) {
            startActivity(new Intent(this, SupportActivity.class));
        } else if (id == R.id.tv_legal) {
            startActivity(new Intent(this, LegalActivity.class));
        }
    }

    private void toggleMenu() {
        menuOpen = !menuOpen;
        View sliding = findViewById(R.id.sliding_menu);
        if (sliding != null) {
            sliding.setVisibility(menuOpen ? View.VISIBLE : View.GONE);
        }
    }

    private void openShop() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    android.net.Uri.parse(getString(R.string.shop_link))));
        } catch (Exception e) {
            ToastUtils.show(this, R.string.network_err);
        }
    }

    private void openLibrary() {
        if (MokacamApp.get().cameraConnected) {
            startActivity(new Intent(this, SelectLibraryActivity.class));
            return;
        }
        if (WifiUtils.onCameraNetwork(this)) {
            connectInBackground();
            ui.sendEmptyMessageDelayed(MSG_OPEN_LIBRARY, 2500L);
        } else {
            startActivity(new Intent(this, SelectLibraryActivity.class));
        }
    }

    private void tryConnectCamera() {
        if (!WifiUtils.isWifiEnabled(this)) {
            tvConnect.setText(R.string.please_open_wifi);
            return;
        }
        if (!WifiUtils.onCameraNetwork(this)) {
            tvConnect.setText(R.string.pleaseconnect);
            return;
        }
        tvConnect.setText(R.string.connecting);
        connecting = true;
        connectInBackground();
        MokacamApp.get().currentSsid = WifiUtils.getSSID(this);
        ui.sendEmptyMessageDelayed(MSG_CONNECTED, 2500L);
    }

    private void connectInBackground() {
        new Thread(() -> {
            boolean ok = CameraSession.get().connect();
            if (ok) {
                ok = CameraSession.get().requestToken();
            }
            if (ok) {
                CameraClient.get().syncCameraClock();
            }
            MokacamApp.get().cameraConnected = ok;
        }, "camera-connect").start();
    }

    private void onConnectCheckDone(boolean fromTimer) {
        connecting = false;
        if (MokacamApp.get().cameraConnected) {
            tvConnect.setTextColor(0xFF000000);
            tvConnect.setText(R.string.isconnected);
            Intent i = new Intent(this, CameraLiveActivity.class);
            startActivity(i);
        } else {
            tvConnect.setText(R.string.connect_error);
            ToastUtils.show(this, R.string.connect_error);
        }
    }

    @Override
    public void onBackPressed() {
        if (connecting) {
            ToastUtils.show(this, R.string.connecting_notexit);
            return;
        }
        if (menuOpen) {
            toggleMenu();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MokacamApp app = MokacamApp.get();
        if (!app.cameraConnected && !connecting) {
            tvConnect.setText(R.string.connect);
        } else if (app.cameraConnected) {
            tvConnect.setText(R.string.isconnected);
        }
    }
}
