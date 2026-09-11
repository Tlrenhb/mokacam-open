package com.nostra13.universalimageloader.core.assist;

import com.alibaba.fastjson.asm.Opcodes;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private final int a;
    private final int b;

    public c(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public c(int i, int i2, int i3) {
        if (i3 % Opcodes.GETFIELD == 0) {
            this.a = i;
            this.b = i2;
        } else {
            this.a = i2;
            this.b = i;
        }
    }

    public int a() {
        return this.a;
    }

    public c a(float f) {
        return new c((int) (this.a * f), (int) (this.b * f));
    }

    public c a(int i) {
        return new c(this.a / i, this.b / i);
    }

    public int b() {
        return this.b;
    }

    public String toString() {
        return new StringBuilder(9).append(this.a).append("x").append(this.b).toString();
    }
}
