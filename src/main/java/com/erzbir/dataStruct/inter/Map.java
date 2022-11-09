package com.erzbir.dataStruct.inter;

public interface Map<K, V> extends Iterable<Map.Entry<K, V>> {
    void put(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    V set(K key, V value);

    int size();

    boolean isEmpty();

    Set<K> keySet();

    List<V> values();

    Set<Entry<K, V>> entrySet();

    interface Entry<K, V> extends Comparable<Entry<K, V>> {
        K key();

        V value();
    }
}
