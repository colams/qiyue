package com.foxconn.sw.data.dto.entity;

public class TupleValue<T, V> {

    private T item;
    private V otherItem;

    public TupleValue() {
    }

    public TupleValue(T item, V otherItem) {
        this.item = item;
        this.otherItem = otherItem;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public V getOtherItem() {
        return otherItem;
    }

    public void setOtherItem(V otherItem) {
        this.otherItem = otherItem;
    }
}
