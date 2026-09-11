package com.aee.mokacam.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import com.aee.mokacam.R;
import com.aee.mokacam.AeeApplication;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class bk implements AdapterView.OnItemClickListener {
    final /* synthetic */ LibraryActivity a;

    bk(LibraryActivity libraryActivity) {
        this.a = libraryActivity;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ImageView imageView = (ImageView) view.findViewById(R.id.item_selected_iv);
        if (!this.a.m) {
            if (AeeApplication.a().t == null) {
                AeeApplication.a().t = new ArrayList();
            }
            if (((com.aee.mokacam.bean.g) this.a.s.get(i)).e()) {
                imageView.setVisibility(8);
                ((com.aee.mokacam.bean.g) this.a.s.get(i)).b(false);
                LibraryActivity libraryActivity = this.a;
                libraryActivity.o--;
                if (this.a.o == 0) {
                    this.a.n.setVisibility(8);
                } else {
                    this.a.n.setSelectedNum(this.a.o);
                }
            } else {
                ((com.aee.mokacam.bean.g) this.a.s.get(i)).b(true);
                imageView.setVisibility(0);
                this.a.o++;
                if (this.a.o != 0) {
                    this.a.n.setVisibility(0);
                    this.a.n.setSelectedNum(this.a.o);
                }
            }
        } else if ("camera_lib".equals(this.a.r)) {
            Intent intent = new Intent(this.a, (Class<?>) ShowPicOrVideoActivity.class);
            intent.putExtra("position", i);
            intent.putExtra("fromWhere", "camera_lib");
            this.a.startActivity(intent);
        } else {
            Intent intent2 = new Intent(this.a, (Class<?>) ShowPicOrVideoActivity.class);
            intent2.putExtra("position", i);
            intent2.putExtra("fromWhere", "local_lib");
            this.a.startActivity(intent2);
        }
        this.a.o();
    }
}
