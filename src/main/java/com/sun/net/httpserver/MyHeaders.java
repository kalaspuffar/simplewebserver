package com.sun.net.httpserver;

import java.util.*;

public class MyHeaders extends Headers {

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public boolean containsKey(Object var1) {
        return this.map.containsKey((String) var1);
    }

    public boolean containsValue(Object var1) {
        return this.map.containsValue(var1);
    }

    public List<String> get(Object var1) {
        return (List)this.map.get((String)var1);
    }

    public String getFirst(String var1) {
        List var2 = (List)this.map.get(var1);
        return var2 == null ? null : (String)var2.get(0);
    }

    public List<String> put(String var1, List<String> var2) {
        return (List)this.map.put(var1, var2);
    }

    public void add(String var1, String var2) {
        String var3 = var1;
        List var4 = (List)this.map.get(var3);
        if (var4 == null) {
            var4 = new LinkedList();
            this.map.put(var3, var4);
        }

        ((List)var4).add(var2);
    }

    public void set(String var1, String var2) {
        LinkedList var3 = new LinkedList();
        var3.add(var2);
        this.put((String)var1, (List)var3);
    }

    public List<String> remove(Object var1) {
        return (List)this.map.remove((String)var1);
    }

    public void putAll(Map<? extends String, ? extends List<String>> var1) {
        this.map.putAll(var1);
    }

    public void clear() {
        this.map.clear();
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    public Collection<List<String>> values() {
        return this.map.values();
    }

    public Set<Entry<String, List<String>>> entrySet() {
        return this.map.entrySet();
    }

    public boolean equals(Object var1) {
        return this.map.equals(var1);
    }

    public int hashCode() {
        return this.map.hashCode();
    }
}
