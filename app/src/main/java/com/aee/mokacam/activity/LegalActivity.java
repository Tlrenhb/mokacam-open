package com.aee.mokacam.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.aee.mokacam.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes.dex */
public class LegalActivity extends BaseActivity {
    ImageView a;
    TextView b;
    TextView c;
    TextView d;
    String e;

    void a() {
        this.e = getResources().getConfiguration().locale.getLanguage();
    }

    void b() {
        this.a = (ImageView) findViewById(R.id.iv_legal_back);
        this.b = (TextView) findViewById(R.id.tv_legal_content);
        this.c = (TextView) findViewById(R.id.tv_content_title);
        this.d = (TextView) findViewById(R.id.tv_legal_title);
    }

    void c() {
        this.c.setText(R.string.legal_camera_title);
        this.d.setText(R.string.disclaimer);
        if (this.e.equals("zh")) {
            this.b.setText(a("cameraLegalContent_zh_cn.txt"));
        } else {
            this.b.setText(a("cameraLegalContent_en_cn.txt"));
        }
    }

    void d() {
        this.a.setOnClickListener(new ax(this));
    }

    public String a(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            InputStream inputStreamOpen = getAssets().open(str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOpen));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            bufferedReader.close();
            inputStreamOpen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_legal);
        a();
        b();
        c();
        d();
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
