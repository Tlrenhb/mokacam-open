package com.aee.mokacam.ui.info;

import android.os.Bundle;
import android.widget.TextView;

import com.aee.mokacam.R;
import com.aee.mokacam.ui.BaseActivity;

/**
 * Product intro page. Replacement of com.aee.zone.activity.ProductActivity:
 * the original showed marketing images from the APK resources; the rewrite
 * keeps the same informational purpose with the product facts as text.
 */
public class ProductActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        TextView body = find(R.id.tv_product_body);
        body.setText(R.string.product_intro_text);
        find(R.id.iv_product_back).setOnClickListener(v -> finish());
    }
}
