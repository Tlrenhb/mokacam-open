package com.aee.mokacam.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.aee.mokacam.R;

/* JADX INFO: loaded from: classes.dex */
public class g extends Dialog {
    private Animation a;
    private ImageView b;
    private TextView c;

    public g(Context context) {
        super(context, R.style.loading_dialog);
        View viewInflate = getLayoutInflater().inflate(R.layout.dialog_loading, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.dialog_view);
        this.b = (ImageView) viewInflate.findViewById(R.id.loading_img);
        this.c = (TextView) viewInflate.findViewById(R.id.loading_text);
        this.a = AnimationUtils.loadAnimation(context, R.anim.load_animation);
        this.b.startAnimation(this.a);
        setCanceledOnTouchOutside(false);
        setContentView(linearLayout, new LinearLayout.LayoutParams(-1, -1));
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        super.setTitle(i);
        this.c.setText(i);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.c.setText(charSequence);
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        this.b.startAnimation(this.a);
    }
}
