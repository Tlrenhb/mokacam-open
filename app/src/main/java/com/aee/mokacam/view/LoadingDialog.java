package com.aee.mokacam.view;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.aee.mokacam.R;

/**
 * Loading dialog. Replacement of the original com.aee.zone.widget.g
 * (dialog_loading + rotating animation).
 */
public class LoadingDialog extends Dialog {

    private TextView text;

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialog);
        setContentView(R.layout.dialog_loading);
        text = findViewById(R.id.loading_text);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void setTitle(int resId) {
        text.setText(resId);
    }

    @Override
    public void setTitle(CharSequence title) {
        text.setText(title);
    }
}
