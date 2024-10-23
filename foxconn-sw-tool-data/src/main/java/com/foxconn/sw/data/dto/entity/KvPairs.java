package com.foxconn.sw.data.dto.entity;

public class KvPairs<T,V> {

    private T name;
    private V attach;

    public KvPairs() {
    }

    public KvPairs(T name, V attach) {
        this.name = name;
        this.attach = attach;
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public V getAttach() {
        return attach;
    }

    public void setAttach(V attach) {
        this.attach = attach;
    }
}
