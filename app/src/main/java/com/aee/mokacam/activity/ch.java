package com.aee.mokacam.activity;

import com.aee.mokacam.bean.SendMsg;
import com.aee.mokacam.constants.AeeConstants;

/* JADX INFO: loaded from: classes.dex */
class ch implements Runnable {
    final /* synthetic */ SelectSimpleActivity a;

    ch(SelectSimpleActivity selectSimpleActivity) {
        this.a = selectSimpleActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.a = new String[0];
        try {
            switch (this.a.g) {
                case 32774:
                    this.a.b = new SendMsg("获取主机视频参数  配置", AeeConstants.i, "video_resolution", (String) null, 18);
                    this.a.c = com.aee.mokacam.service.a.a().a(this.a.b);
                    if (this.a.c != null && this.a.c.getRval() >= 0 && this.a.c.getParam().equals("video_resolution")) {
                        this.a.a = com.aee.mokacam.utils.a.c(this.a.c.getOptions());
                        if (!this.a.c.getPermission().equals("settable")) {
                            this.a.d = false;
                        } else {
                            this.a.d = true;
                        }
                    }
                    break;
                case 32776:
                    this.a.b = new SendMsg("获取主机拍照参数  配置", AeeConstants.i, "photo_size", (String) null, 18);
                    this.a.c = com.aee.mokacam.service.a.a().a(this.a.b);
                    if (this.a.c != null && this.a.c.getRval() >= 0 && this.a.c.getParam().equals("photo_size")) {
                        this.a.a = com.aee.mokacam.utils.a.c(this.a.c.getOptions());
                        if (!this.a.c.getPermission().equals("settable")) {
                            this.a.d = false;
                        } else {
                            this.a.d = true;
                        }
                    }
                    break;
                case 32777:
                    this.a.b = new SendMsg("快拍设置  配置", AeeConstants.i, "photo_shot_mode", (String) null, 18);
                    this.a.c = com.aee.mokacam.service.a.a().a(this.a.b);
                    if (this.a.c != null && this.a.c.getRval() >= 0 && this.a.c.getParam().equals("photo_shot_mode")) {
                        this.a.e = com.aee.mokacam.utils.a.c(this.a.c.getOptions());
                        this.a.a = com.aee.mokacam.utils.q.a("photo_shot_mode", this.a.e);
                        if (!this.a.c.getPermission().equals("settable")) {
                            this.a.d = false;
                        } else {
                            this.a.d = true;
                        }
                    }
                    break;
                case 32783:
                    this.a.b = new SendMsg("自动持续连拍  配置", AeeConstants.i, "photo_tlm", (String) null, 18);
                    this.a.c = com.aee.mokacam.service.a.a().a(this.a.b);
                    if (this.a.c != null && this.a.c.getRval() >= 0 && this.a.c.getParam().equals("photo_selftimer")) {
                        this.a.a = com.aee.mokacam.utils.q.a("photo_selftimer", this.a.e);
                        if (!this.a.c.getPermission().equals("settable")) {
                            this.a.d = false;
                        } else {
                            this.a.d = true;
                        }
                    }
                    break;
                case 32784:
                    this.a.b = new SendMsg("设置提示音", AeeConstants.i, "Beep", (String) null, 18);
                    this.a.c = com.aee.mokacam.service.a.a().a(this.a.b);
                    if (this.a.c != null && this.a.c.getRval() >= 0 && this.a.c.getParam().equals("Beep")) {
                        this.a.e = com.aee.mokacam.utils.a.c(this.a.c.getOptions());
                        this.a.a = com.aee.mokacam.utils.q.a("Beep", this.a.e);
                        if (!this.a.c.getPermission().equals("settable")) {
                            this.a.d = false;
                        } else {
                            this.a.d = true;
                        }
                    }
                    break;
                case 32785:
                    this.a.b = new SendMsg("状态指示灯", AeeConstants.i, "Status_LED", (String) null, 18);
                    this.a.c = com.aee.mokacam.service.a.a().a(this.a.b);
                    if (this.a.c != null && this.a.c.getRval() >= 0 && this.a.c.getParam().equals("Status_LED")) {
                        this.a.e = com.aee.mokacam.utils.a.c(this.a.c.getOptions());
                        this.a.a = com.aee.mokacam.utils.q.a("Status_LED", this.a.e);
                        if (!this.a.c.getPermission().equals("settable")) {
                            this.a.d = false;
                        } else {
                            this.a.d = true;
                        }
                    }
                    break;
                case 32788:
                    this.a.b = new SendMsg("获取视角  配置", AeeConstants.i, "TV_Mode", (String) null, 18);
                    this.a.c = com.aee.mokacam.service.a.a().a(this.a.b);
                    if (this.a.c != null && this.a.c.getRval() >= 0 && this.a.c.getParam().equals("TV_Mode")) {
                        this.a.e = com.aee.mokacam.utils.a.c(this.a.c.getOptions());
                        this.a.a = com.aee.mokacam.utils.q.a("TV_Mode", this.a.e);
                        if (!this.a.c.getPermission().equals("settable")) {
                            this.a.d = false;
                        } else {
                            this.a.d = true;
                        }
                    }
                    break;
                case 32789:
                    this.a.b = new SendMsg("DV端语言设置", AeeConstants.i, "Language", (String) null, 18);
                    this.a.c = com.aee.mokacam.service.a.a().a(this.a.b);
                    if (this.a.c != null && this.a.c.getRval() >= 0 && this.a.c.getParam().equals("Language")) {
                        this.a.e = com.aee.mokacam.utils.a.c(this.a.c.getOptions());
                        this.a.a = com.aee.mokacam.utils.q.a("Language", this.a.e);
                        if (!this.a.c.getPermission().equals("settable")) {
                            this.a.d = false;
                        } else {
                            this.a.d = true;
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.a.d = true;
        this.a.i.post(new ci(this));
    }
}
