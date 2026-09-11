package com.aee.mokacam.ui.info;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.aee.mokacam.R;
import com.aee.mokacam.ui.BaseActivity;
import com.aee.mokacam.util.ToastUtils;

import java.io.File;
import java.io.FileWriter;

/**
 * Product registration wizard. Replacement of
 * com.aee.zone.activity.RegisterActivity (a 2-page ViewPager: user info +
 * product serial, "submit" posted to the vendor backend). The vendor backend
 * is offline, so the rewrite performs local validation only and stores the
 * record in app storage for reference.
 */
public class RegisterActivity extends BaseActivity {

    private ViewPager2 pager;
    private TextView btnBack;
    private View pageUserInfo;
    private View pageProductInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pager = find(R.id.vp_register);
        btnBack = find(R.id.iv_register_back);
        pager.setAdapter(new PageAdapter());
        pager.post(() -> {
            // ViewPager2 recycles; grab the live children once bound.
            if (pager.getChildCount() >= 1) {
                pageUserInfo = pager.getChildAt(0);
            }
        });
        btnBack.setOnClickListener(v -> {
            if (pager.getCurrentItem() == 0) {
                finish();
            } else {
                pager.setCurrentItem(0);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void submit(View page) {
        EditText name = page.findViewById(R.id.et_reg_name);
        EditText serial = page.findViewById(R.id.et_reg_serial);
        if (name == null || serial == null
                || name.getText().toString().trim().isEmpty()
                || serial.getText().toString().trim().isEmpty()) {
            ToastUtils.show(this, R.string.failed);
            return;
        }
        try {
            File dir = new File(getExternalFilesDir(null), "Mokacam/register");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileWriter w = new FileWriter(new File(dir, "registration.txt"), true);
            w.append(name.getText().toString().trim())
                    .append(" | ")
                    .append(serial.getText().toString().trim())
                    .append(" | ").append(String.valueOf(System.currentTimeMillis()))
                    .append("\n");
            w.close();
        } catch (Exception ignored) {
        }
        ToastUtils.show(this, R.string.set_success);
        finish();
    }

    private class PageAdapter extends RecyclerView.Adapter<PageHolder> {

        @NonNull
        @Override
        public PageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            int layout = viewType == 0
                    ? R.layout.page_register_info : R.layout.page_register_product;
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(layout, parent, false);
            if (viewType == 0) {
                View next = v.findViewById(R.id.tv_next);
                if (next != null) {
                    next.setOnClickListener(x -> pager.setCurrentItem(1));
                }
            } else {
                View submit = v.findViewById(R.id.tv_submit);
                if (submit != null) {
                    submit.setOnClickListener(x -> submit(v));
                }
            }
            return new PageHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull PageHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }

    private static class PageHolder extends RecyclerView.ViewHolder {
        PageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
