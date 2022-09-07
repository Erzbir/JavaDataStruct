package com.erzbir.树;

import com.erzbir.接口.Queue;

import java.util.Iterator;

/**
 * @Author: Erzbir
 * @Date: 2022/9/7 10:51
 */
//底层由最大堆实现的优先队列
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private final MaxHeap<E> heap;

    public PriorityQueue() {
        heap = new MaxHeap<>();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public void offer(E element) {
        heap.add(element);
    }

    @Override
    public E poll() {
        return heap.extractMax();
    }

    @Override
    public E element() {
        return heap.findMax();
    }

    @Override
    public void clear() {
        heap.clear();
    }

    @Override
    public Iterator<E> iterator() {
        return heap.iterator();
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}