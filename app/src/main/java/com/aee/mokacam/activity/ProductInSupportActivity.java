package com.aee.mokacam.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import com.aee.mokacam.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ProductInSupportActivity extends BaseActivity {
    private ExpandableListView a;
    private ImageView c;
    private TextView e;
    private List<String> b = new ArrayList();
    private HashMap<Integer, String> d = new HashMap<>();

    private void a() {
        this.a = (ExpandableListView) findViewById(R.id.expand_listview);
        this.c = (ImageView) findViewById(R.id.iv_dro_back);
        this.e = (TextView) findViewById(R.id.tv_supportproduct_title);
    }

    private void b() {
        this.b.clear();
        this.d.clear();
        this.e.setText(R.string.support_camera);
        this.b.add(getResources().getString(R.string.camera_question1));
        this.b.add(getResources().getString(R.string.camera_question2));
        this.b.add(getResources().getString(R.string.camera_question3));
        this.b.add(getResources().getString(R.string.camera_question4));
        this.b.add(getResources().getString(R.string.camera_question5));
        this.d.put(0, getResources().getString(R.string.camera_answer1));
        this.d.put(1, getResources().getString(R.string.camera_answer2));
        this.d.put(2, getResources().getString(R.string.camera_answer3));
        this.d.put(3, getResources().getString(R.string.camera_answer4));
        this.d.put(4, getResources().getString(R.string.camera_answer5));
        this.a.setAdapter(new bt(this, null));
    }

    private void c() {
        this.c.setOnClickListener(this);
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
