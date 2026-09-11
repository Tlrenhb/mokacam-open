package com.aee.mokacam.ui.info;

import android.os.Bundle;
import android.widget.TextView;

import com.aee.mokacam.R;
import com.aee.mokacam.MokacamApp;
import com.aee.mokacam.ui.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Legal / disclaimer screen. Replacement of
 * com.aee.zone.activity.LegalActivity, which loaded
 * assets/cameraLegalContent_*.txt or droneLegalContent_*.txt depending on the
 * selected product; the rewrite reads the same assets.
 */
public class LegalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal);
        TextView body = find(R.id.tv_legal_body);
        find(R.id.iv_legal_back).setOnClickListener(v -> finish());

        boolean chinese = MokacamApp.get().isChineseLocale();
        String cameraFile = chinese ? "cameraLegalContent_zh_cn.txt"
                : "cameraLegalContent_en_cn.txt";
        String droneFile = chinese ? "droneLegalContent_zh_cn.txt"
                : "droneLegalContent_en_cn.txt";
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.legal_camera_title)).append("\n\n");
        sb.append(readAsset(cameraFile)).append("\n\n");
        sb.append(getString(R.string.legal_drone_title)).append("\n\n");
        sb.append(readAsset(droneFile));
        body.setText(sb.toString());
    }

    private String readAsset(String name) {
        try (InputStream in = getAssets().open(name)) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[4096];
            int n;
            while ((n = in.read(buf)) > 0) {
                out.write(buf, 0, n);
            }
            return new String(out.toByteArray(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "";
        }
    }
}
