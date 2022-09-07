package com.erzbir.树;

import com.erzbir.动态数组.ArrayList;
import com.erzbir.接口.List;
import com.erzbir.接口.Map;
import com.erzbir.接口.Set;

import java.util.Iterator;

/**
 * @Author: Erzbir
 * @Date: 2022/9/7 15:10
 */
public class HashTable<K extends Comparable<K>, V> implements Map<K, V> {
    //预设容量
    private static final int[] capacity = {
            53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 768433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741
    };
    private static final int upperTol = 10000; //每个桶的元素上限
    private static final int lowerTol = 10; //每个桶的元素下限
    private static int capacityIndex = 7;   //标记桶的个数 哈希表的长度
    private AVLTreeMap<K, V>[] hashTable;      //哈希表的容器
    private int size;   //当前哈希表中的元素个数
    private int M;      //当前哈希表的长度

    public HashTable() {
        M = capacity[capacityIndex];
        hashTable = new AVLTreeMap[M];
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = new AVLTreeMap<>();
        }
        size = 0;
    }

    //哈希函数-链地址法-将哈希值和桶的角标进行对应
    private int hash(K key) {
        return key.hashCode() & 0x7fffffff % M;
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key);
        AVLTreeMap<K, V> map = hashTable[index];
        if (map.contains(key)) {
            map.put(key, value);    //修改
        } else {
            map.put(key, value);    //添加
            size++;
            //扩容
            if (size > upperTol * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    @Override
    public V remove(K key) {
        int index = hash(key);
        AVLTreeMap<K, V> map = hashTable[index];
        V ret = null;
        if (map.contains(key)) {
            ret = map.remove(key);
            size--;
            //缩容
            if (size < lowerTol * M && capacityIndex > 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    private void resize(int newM) {
        AVLTreeMap<K, V>[] newHashTable = new AVLTreeMap[newM];
        for (int i = 0; i < newHashTable.length; i++) {
            newHashTable[i] = new AVLTreeMap<>();
        }
        M = newM;
        for (AVLTreeMap<K, V> map : hashTable) {
            for (K key : map.keySet()) {
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }
        hashTable = newHashTable;
    }

    @Override
    public boolean contains(K key) {
        int index = hash(key);
        AVLTreeMap<K, V> map = hashTable[index];
        return map.contains(key);
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        AVLTreeMap<K, V> map = hashTable[index];
        return map.get(key);
    }

    @Override
    public V set(K key, V value) {
        int index = hash(key);
        AVLTreeMap<K, V> map = hashTable[index];
        return map.set(key, value);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<>();
        for (AVLTreeMap<K, V> map : hashTable) {
            for (K key : map.keySet()) {
                set.add(key);
            }
        }
        return set;
    }

    @Override
    public List<V> values() {
        ArrayList<V> list = new ArrayList<>();
        for (AVLTreeMap<K, V> map : hashTable) {
            for (V value : map.values()) {
                list.add(value);
            }
        }
        return list;
    }

    private LinkedSet<Entry<K, V>> toSet() {
        LinkedSet<Entry<K, V>> set = new LinkedSet<>();
        for (AVLTreeMap<K, V> map : hashTable) {
            for (Entry<K, V> entry : map.entrySet()) {
                set.add(entry);
            }
        }
        return set;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return toSet();
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return toSet().iterator();
    }
}