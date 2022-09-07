package com.erzbir.接口;

public interface Stack<E> extends Iterable<E> {
    int size();

    boolean isEmpty();

    void push(E element);

    E pop();

    E peek();

    void clear();
}
