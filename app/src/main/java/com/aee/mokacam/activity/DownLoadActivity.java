package com.aee.mokacam.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.constants.AeeConstants;
import java.io.File;
import java.util.List;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

/* JADX INFO: loaded from: classes.dex */
public class DownLoadActivity extends BaseActivity {
    private ImageView a;
    private ListView b;
    private List<com.aee.mokacam.bean.g> c;
    private as d;
    private View e;

    private Callback.Cancelable a(String str, String str2, Callback.CommonCallback<File> commonCallback) {
        if (new File(str2).exists()) {
            return null;
        }
        RequestParams requestParams = new RequestParams(str);
        requestParams.setSaveFilePath(str2);
        return org.xutils.x.http().get(requestParams, commonCallback);
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.iv_download_back);
        this.b = (ListView) findViewById(R.id.download_file_list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        File file = new File(String.valueOf(AeeConstants.a) + File.separator + this.c.get(i).a() + ".tmp");
        if (file.exists()) {
            file.delete();
        }
        File file2 = new File(String.valueOf(AeeConstants.a) + File.separator + this.c.get(i).a());
        if (file2.exists()) {
            file2.delete();
        }
    }

    private void b() {
        this.c = AeeApplication.a().t;
        this.d = new as(this, null);
        this.b.setAdapter((ListAdapter) this.d);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.c.size()) {
                return;
            }
            String str = String.valueOf(AeeConstants.a) + File.separator + this.c.get(i2).a();
            if (new File(str).exists()) {
                this.c.get(i2).a(3);
            } else {
                this.c.get(i2).e = a(String.valueOf(AeeApplication.a().h) + this.c.get(i2).b + this.c.get(i2).a, str, new av(this, i2));
            }
            i = i2 + 1;
        }
    }

    private void c() {
        this.a.setOnClickListener(new aq(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        com.aee.mokacam.widget.e eVar = new com.aee.mokacam.widget.e((Context) this, false);
        eVar.show();
        eVar.d(R.string.cancel);
        eVar.c(R.string.sure);
        eVar.b(R.string.download_cancel_warning);
        eVar.a(new ar(this, eVar));
    }

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.support.v4.app.BaseFragmentActivityDonut, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = View.inflate(this, R.layout.activity_download, null);
        setContentView(this.e);
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

    @Override // com.aee.mokacam.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.c.clear();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 >= this.c.size()) {
                    break;
                }
                if (this.c.get(i3).g() == 2) {
                    d();
                    return super.onKeyDown(i, keyEvent);
                }
                i2 = i3 + 1;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }
}
