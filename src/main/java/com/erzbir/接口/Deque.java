package com.erzbir.接口;

public interface Deque<E> extends Queue<E> {
    void addFirst(E element);

    void addLast(E element);

    E removeFirst();

    E removeLast();

    E getFirst();

    E getLast();

    int size();

    boolean isEmpty();

    void clear();
}
