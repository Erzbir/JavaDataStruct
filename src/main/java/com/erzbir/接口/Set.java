package com.erzbir.接口;

import java.util.Iterator;

/**
 * @Author: Erzbir
 * @Date: 2022/9/4 16:16
 */
public interface Set<E> extends Iterable<E> {
    void add(E e);

    void remove(E e);

    boolean contains(E e);

    boolean isEmpty();

    int size();

    Iterator<E> iterator();
}
