package com.erzbir.dataStruct.Tree;


import com.erzbir.dataStruct.inter.Set;

import java.util.Iterator;

/**
 * @Author: Erzbir
 * @Date: 2022/9/4 16:18
 */
//底层由BST实现的一个集合功能
public class TreeSet<E extends Comparable<E>> implements Set<E> {
    private final BinarySearchTree<E> bst;

    public TreeSet() {
        bst = new BinarySearchTree<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int size() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return bst.iterator();
    }

    @Override
    public String toString() {
        return bst.toString();
    }
}