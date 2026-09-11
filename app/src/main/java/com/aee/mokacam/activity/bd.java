package com.aee.mokacam.activity;

import android.content.Intent;
import android.view.View;
import android.net.Uri;
import androidx.core.content.FileProvider;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import com.aee.mokacam.constants.AeeConstants;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
class bd implements View.OnClickListener {
    final /* synthetic */ LibraryActivity a;

    bd(LibraryActivity libraryActivity) {
        this.a = libraryActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        bm bmVar;
        if (!"camera_lib".equals(this.a.r)) {
            if (this.a.o == 0) {
                com.aee.mokacam.utils.w.a(R.string.library_share, true);
                return;
            }
            if (this.a.o == 1) {
                for (com.aee.mokacam.bean.g gVar : this.a.s) {
                    if (gVar.e()) {
                        this.a.E = true;
                        String str = String.valueOf(AeeConstants.a) + File.separator + gVar.f();
                        File shareFile = new File(str);
                        Uri shareUri = FileProvider.getUriForFile(this.a,
                                this.a.getPackageName() + ".fileprovider", shareFile);
                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.setType("image/*");
                        share.putExtra(Intent.EXTRA_STREAM, shareUri);
                        share.putExtra(Intent.EXTRA_TEXT, gVar.f());
                        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        this.a.startActivity(Intent.createChooser(share,
                                this.a.getString(R.string.share)));
                    }
                }
                return;
            }
            return;
        }
        if (this.a.o == 0) {
            com.aee.mokacam.utils.w.a(R.string.library_download, true);
            return;
        }
        AeeApplication.a().t.clear();
        for (int i = 0; i < this.a.s.size(); i++) {
            if (((com.aee.mokacam.bean.g) this.a.s.get(i)).e()) {
                AeeApplication.a().t.add((com.aee.mokacam.bean.g) this.a.s.get(i));
            }
        }
        for (int i2 = 0; i2 < this.a.s.size(); i2++) {
            com.aee.mokacam.bean.g gVar2 = (com.aee.mokacam.bean.g) this.a.s.get(i2);
            if (gVar2.a.endsWith(".JPG") && gVar2.h != null) {
                this.a.y.cancelDisplayTask(gVar2.h);
            } else if (gVar2.a.endsWith(".MP4") && (bmVar = this.a.b.get(Integer.valueOf(i2))) != null) {
                bmVar.cancel(true);
            }
        }
        this.a.startActivity(new Intent(this.a, (Class<?>) DownLoadActivity.class));
    }
}
