package com.aee.mokacam.bean;

/* JADX INFO: loaded from: classes.dex */
public class ReceiveMsg {
    private Object listing;
    private String md5sum;
    private int msg_id;
    private String options;
    private Object param;
    private String permission;
    private int rval;
    private String type;

    public Object getListing() {
        return this.listing;
    }

    public String getMd5sum() {
        return this.md5sum;
    }

    public int getMsg_id() {
        return this.msg_id;
    }

    public String getOptions() {
        return this.options;
    }

    public Object getParam() {
        return this.param;
    }

    public String getPermission() {
        return this.permission;
    }

    public int getRval() {
        return this.rval;
    }

    public String getType() {
        return this.type;
    }

    public void setListing(Object obj) {
        this.listing = obj;
    }

    public void setMd5sum(String str) {
        this.md5sum = str;
    }

    public void setMsg_id(int i) {
        this.msg_id = i;
    }

    public void setOptions(String str) {
        this.options = str;
    }

    public void setParam(Object obj) {
        this.param = obj;
    }

    public void setPermission(String str) {
        this.permission = str;
    }

    public void setRval(int i) {
        this.rval = i;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String toString() {
        return "ReceiveMsg [msg_id=" + this.msg_id + ", rval=" + this.rval + ", param=" + this.param + ", listing=" + this.listing + ", type=" + this.type + ", options=" + this.options + ", permission=" + this.permission + "]";
    }
}
