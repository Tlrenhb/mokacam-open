package com.facebook.rebound;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
public class f {
    private final n c;
    private final Map<String, i> a = new HashMap();
    private final Set<i> b = new CopyOnWriteArraySet();
    private final CopyOnWriteArraySet<p> d = new CopyOnWriteArraySet<>();
    private boolean e = true;

    public f(n nVar) {
        if (nVar == null) {
            throw new IllegalArgumentException("springLooper is required");
        }
        this.c = nVar;
        this.c.a(this);
    }

    void a(double d) {
        for (i iVar : this.b) {
            if (iVar.e()) {
                iVar.d(d / 1000.0d);
            } else {
                this.b.remove(iVar);
            }
        }
    }

    void a(i iVar) {
        if (iVar == null) {
            throw new IllegalArgumentException("spring is required");
        }
        if (this.a.containsKey(iVar.a())) {
            throw new IllegalArgumentException("spring is already registered");
        }
        this.a.put(iVar.a(), iVar);
    }

    void a(String str) {
        i iVar = this.a.get(str);
        if (iVar == null) {
            throw new IllegalArgumentException("springId " + str + " does not reference a registered spring");
        }
        this.b.add(iVar);
        if (a()) {
            this.e = false;
            this.c.b();
        }
    }

    public boolean a() {
        return this.e;
    }

    public i b() {
        i iVar = new i(this);
        a(iVar);
        return iVar;
    }

    public void b(double d) {
        Iterator<p> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
        a(d);
        if (this.b.isEmpty()) {
            this.e = true;
        }
        Iterator<p> it2 = this.d.iterator();
        while (it2.hasNext()) {
            it2.next().b(this);
        }
        if (this.e) {
            this.c.c();
        }
    }
}
