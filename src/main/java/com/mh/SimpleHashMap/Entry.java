package com.mh.SimpleHashMap;
/**
 * @author mh
 * */
public class Entry<K,V> {
    private final int hash;
    private final K key;
    private V value;
    private Entry<K,V> next;

    public Entry(int hash, K key, V value, Entry<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public int getHash() {
        return hash;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Entry<K, V> getNext() {
        return next;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setNext(Entry<K, V> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.hash==obj.hashCode()){
            return true;
        }
        return false;
    }
    public boolean equals(int hashcode) {
        if(this.hash==hashcode){
            return true;
        }
        return false;
    }
}
