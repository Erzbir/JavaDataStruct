package com.erzbir.dataStruct.Tree;


import com.erzbir.dataStruct.MutableLinkedList.LinkedList;
import com.erzbir.dataStruct.inter.Set;

import java.util.Iterator;

/**
 * @Author: Erzbir
 * @Date: 2022/9/4 16:14
 */
//底层由链表实现一个集合的功能
public class LinkedSet<E> implements Set<E> {
    private final LinkedList<E> list;

    public LinkedSet() {
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!list.contains(e)) {
            list.add(e);
        }
    }

    @Override
    public void remove(E e) {
        list.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}