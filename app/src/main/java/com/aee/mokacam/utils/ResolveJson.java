package com.aee.mokacam.utils;

import com.aee.mokacam.bean.ReceiveMsg;
import com.alibaba.fastjson.JSON;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ResolveJson {
    public static boolean checkNotifyMsg(Object obj, int i) {
        if (obj == null) {
            return false;
        }
        try {
            if (obj instanceof ReceiveMsg) {
                if (((ReceiveMsg) obj).getMsg_id() != i) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkRval(Object obj, int i) {
        if (obj == null) {
            return false;
        }
        try {
            if (obj instanceof ReceiveMsg) {
                ReceiveMsg receiveMsg = (ReceiveMsg) obj;
                if (receiveMsg.getRval() != 0 || receiveMsg.getMsg_id() != i) {
                    if (receiveMsg.getMsg_id() == 7) {
                        return true;
                    }
                    if (receiveMsg.getRval() != -4) {
                        return false;
                    }
                    com.aee.mokacam.service.a.a().a((com.aee.mokacam.service.n) null);
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    static <T> List<T> getArrayList(String str, Class<T> cls) {
        return JSON.parseArray(str.toString(), cls);
    }

    static <T> T getClass(String str, Class<T> cls) {
        return (T) JSON.parseObject(str, cls);
    }

    public static <T> T resolveNormalInfo(String str, Class<T> cls) {
        try {
            return (T) getClass(str, cls);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
