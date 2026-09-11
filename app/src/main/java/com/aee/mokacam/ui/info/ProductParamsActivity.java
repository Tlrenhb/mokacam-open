package com.aee.mokacam.ui.info;

import android.os.Bundle;
import android.widget.ListView;

import com.aee.mokacam.R;
import com.aee.mokacam.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Product specification table. Replacement of
 * com.aee.zone.activity.ProductParamsActivity (Mokacam specs).
 */
public class ProductParamsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_params);
        ListView list = find(R.id.list_params);
        List<String[]> params = new ArrayList<>();
        params.add(new String[]{getString(R.string.p_param_model), "Mokacam"});
        params.add(new String[]{getString(R.string.p_param_sensor), "1/2.3\" CMOS"});
        params.add(new String[]{getString(R.string.p_param_photo), "12M (4000x3000)"});
        params.add(new String[]{getString(R.string.p_param_video), "1080P 60fps"});
        params.add(new String[]{getString(R.string.p_param_screen), "1.5\" LCD"});
        params.add(new String[]{getString(R.string.p_param_battery), "850 mAh"});
        params.add(new String[]{getString(R.string.p_param_wifi), "802.11 b/g/n"});
        params.add(new String[]{getString(R.string.p_param_weight), "45 g"});
        list.setAdapter(new android.widget.ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, params) {
            @Override
            public android.view.View getView(int position, View convertView, android.view.ViewGroup parent) {
                android.view.View v = convertView;
                if (v == null) {
                    v = getLayoutInflater().inflate(R.layout.item_param, parent, false);
                }
                String[] row = getItem(position);
                TextView k = v.findViewById(R.id.tv_param_key);
                TextView val = v.findViewById(R.id.tv_param_value);
                k.setText(row[0]);
                val.setText(row[1]);
                return v;
            }
        });
        find(R.id.iv_params_back).setOnClickListener(v -> finish());
    }
}
