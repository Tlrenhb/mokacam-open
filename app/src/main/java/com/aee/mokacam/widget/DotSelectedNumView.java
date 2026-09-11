package com.aee.mokacam.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
public class DotSelectedNumView extends RelativeLayout {
    TextView a;

    public DotSelectedNumView(Context context) {
        this(context, null);
    }

    public DotSelectedNumView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DotSelectedNumView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = (TextView) ((RelativeLayout) View.inflate(context, R.layout.layout_dot_selectnum, this)).findViewById(R.id.num_selected_tv);
    }

    public void setSelectedNum(int i) {
        this.a.setText(new StringBuilder(String.valueOf(i)).toString());
    }
}
