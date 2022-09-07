package com.erzbir.接口;

public interface Queue<E> extends Iterable<E> {
    int size();

    boolean isEmpty();

    void offer(E element);

    E poll();

    E element();

    void clear();
}
