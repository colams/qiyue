package com.foxconn.sw.data.dto.entity;

public class KvPairs<T,V> {

    private T text;
    private V attach;

    public KvPairs() {
    }

    public KvPairs(T text, V attach) {
        this.text = text;
        this.attach = attach;
    }

    public T getText() {
        return text;
    }

    public void setText(T text) {
        this.text = text;
    }

    public V getAttach() {
        return attach;
    }

    public void setAttach(V attach) {
        this.attach = attach;
    }
}
