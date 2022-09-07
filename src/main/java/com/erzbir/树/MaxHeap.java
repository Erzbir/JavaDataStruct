package com.erzbir.树;

import com.erzbir.动态数组.ArrayList;

import java.util.Iterator;

/**
 * @Author: Erzbir
 * @Date: 2022/9/7 09:52
 */
public class MaxHeap<E extends Comparable<E>> implements Iterable<E> {
    private final ArrayList<E> data;

    public MaxHeap() {
        data = new ArrayList<>();
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("no parent");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void clear() {
        data.clear();
    }

    public void add(E e) {
        data.add(e);
        shiftUp(data.size() - 1);
    }

    private void shiftUp(int i) {
        while (i > 0 && data.get(i).compareTo(data.get(parent(i))) > 0) {
            data.swap(i, parent(i));
            i = parent(i);
        }
    }

    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("MaxHeap is empty");
        }
        return data.get(0);
    }

    public E findMin() {
        if (isEmpty()) {
            throw new IllegalArgumentException("MaxHeap is empty");
        }
        E min = data.get(0);
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i).compareTo(min) < 0) {
                min = data.get(i);
            }
        }
        return min;
    }

    public E extractMax() {
        E max = findMax();
        data.swap(0, data.size() - 1);
        data.remove(data.size() - 1);
        shiftDown(0);
        return max;
    }

    private void shiftDown(int i) {
        while (leftChild(i) < data.size()) {
            int j = leftChild(i);
            if (j + 1 < data.size() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(i);
            }
            if (data.get(i).compareTo(data.get(j)) < 0) {
                data.swap(i, j);
                i = j;
            } else {
                break;
            }
        }
    }

    public E replace(E e) {
        E res = findMax();
        data.set(0, e);
        shiftDown(0);
        return res;
    }

    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
