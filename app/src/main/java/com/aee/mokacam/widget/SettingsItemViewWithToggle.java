package com.aee.mokacam.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.aee.mokacam.R;
import com.zcw.togglebutton.ToggleButton;

/* JADX INFO: loaded from: classes.dex */
public class SettingsItemViewWithToggle extends RelativeLayout {
    Context a;
    String b;
    TextView c;
    ToggleButton d;

    public SettingsItemViewWithToggle(Context context) {
        super(context);
    }

    public SettingsItemViewWithToggle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SettingsItemViewWithToggle);
        this.b = typedArrayObtainStyledAttributes.getString(0);
        typedArrayObtainStyledAttributes.recycle();
        a();
    }

    public SettingsItemViewWithToggle(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    void a() {
        View viewInflate = View.inflate(this.a, R.layout.item_settings_toggle, this);
        this.c = (TextView) viewInflate.findViewById(R.id.tv_itemtoggle);
        this.d = (ToggleButton) viewInflate.findViewById(R.id.toggle_item_setting);
        this.c.setText(this.b);
    }

    public void setText(int i) {
        this.c.setText(i);
    }
}
