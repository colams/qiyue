package com.foxconn.sw.data.dto.entity;

public class ThreePairs<T, V, P> {

    private T text;
    private V spareValue;
    private P attach;

    public ThreePairs() {
    }

    public ThreePairs(T text, V spareValue, P attach) {
        this.text = text;
        this.spareValue = spareValue;
        this.attach = attach;
    }

    public T getText() {
        return text;
    }

    public void setText(T text) {
        this.text = text;
    }

    public V getSpareValue() {
        return spareValue;
    }

    public void setSpareValue(V spareValue) {
        this.spareValue = spareValue;
    }

    public P getAttach() {
        return attach;
    }

    public void setAttach(P attach) {
        this.attach = attach;
    }
}
