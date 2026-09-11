package com.aee.mokacam.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import com.aee.mokacam.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DroneInSupportActivity extends BaseActivity {
    private ExpandableListView a;
    private List<String> b = new ArrayList();
    private List<String> c = new ArrayList();
    private List<List<String>> d = new ArrayList();
    private ImageView e;

    private void a() {
        this.a = (ExpandableListView) findViewById(R.id.expand_listview);
        this.e = (ImageView) findViewById(R.id.iv_dro_back);
    }

    private void b() {
        this.b.add("What do I have to bear in mind before my first flight?");
        this.b.add("There is no response when drone is powered on: no sound,indicator light not on.");
        this.b.add("The power light is on, but the drone does not operatre.");
        this.b.add("Four notors of the drone do not operate with the same pace.");
        this.b.add("Remote controll fails to start the drone?");
        this.b.add("My drone does not take video or pictures.");
        this.c.add("1.Make sure you are in an open and safe environment\n2.Make sure the drone is placed on a flat surface");
        this.d.add(this.c);
        this.d.add(this.c);
        this.d.add(this.c);
        this.d.add(this.c);
        this.d.add(this.c);
        this.d.add(this.c);
        this.a.setAdapter(new aw(this, null));
    }

    private void c() {
        this.e.setOnClickListener(this);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_dro_back /* 2131427533 */:
                finish();
                break;
        }
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_droneinsupport);
        a();
        b();
        c();
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityHoneycomb, android.app.Activity, android.view.LayoutInflater.Factory2
    public /* bridge */ /* synthetic */ View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(view, str, context, attributeSet);
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity, android.view.LayoutInflater.Factory
    public /* bridge */ /* synthetic */ View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return super.onCreateView(str, context, attributeSet);
    }
}
