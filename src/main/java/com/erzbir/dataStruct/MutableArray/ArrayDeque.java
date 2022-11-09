package com.erzbir.dataStruct.MutableArray;

import com.erzbir.dataStruct.inter.Deque;
import com.erzbir.dataStruct.inter.Stack;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E>, Stack<E> {
    private static int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int front;
    private int rear;
    private int size;

    public ArrayDeque() {
        data = (E[]) new Object[DEFAULT_CAPACITY + 1];
        front = 0;
        size = 0;
        rear = 0;
    }


    @Override
    public void addFirst(E element) {
        if ((rear + 1) % data.length == front) {
            resize(data.length * 2 - 1);
        }
        front = (front - 1 + data.length) % data.length;
        data[front] = element;
        size++;
    }

    @Override
    public void addLast(E element) {
        if ((rear + 1) % data.length == front) {
            resize(data.length * 2 - 1);
        }
        data[rear] = element;
        rear = (rear + 1) % data.length;
        size++;
    }

    private void resize(int newLen) {
        E[] newData = (E[]) new Object[newLen];
        int index = 0;
        for (int i = front; i != rear; i = (i + 1) % data.length) {
            newData[index++] = data[i];
        }
        front = 0;
        rear = index;
        data = newData;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new IllegalArgumentException("null");
        }
        E ret = data[front];
        front = (front + 1) % data.length;
        size--;
        if (size == (data.length - 1) % 4 && data.length - 1 > DEFAULT_CAPACITY) {
            resize(data.length / 2 + 1);
        }
        return ret;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("null");
        }
        E ret = data[rear];
        rear = (rear - 1) % data.length;
        size--;
        if (size == (data.length - 1) % 4 && data.length - 1 > DEFAULT_CAPACITY) {
            resize(data.length / 2 + 1);
        }
        return ret;
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new IllegalArgumentException("null");
        }
        return data[(front + 1 + data.length) % data.length];
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("null");
        }
        return data[(rear - 1 + data.length) % data.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return data == null && front == rear;
    }

    @Override
    public void push(E element) {
        addLast(element);
    }

    @Override
    public E pop() {
        return removeLast();
    }

    @Override
    public E peek() {
        return getLast();
    }

    @Override
    public void offer(E element) {
        addLast(element);
    }

    @Override
    public E poll() {
        return removeFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public void clear() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
        front = 0;
        size = 0;
        rear = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = front; i != rear; i = (i + 1) % data.length) {
            sb.append(data[i]);
            if ((i + 1) % data.length != rear) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof ArrayDeque<?>) {
            ArrayDeque<E> other = (ArrayDeque<E>) o;
            if (size != other.size) {
                return false;
            }
            E[] v1 = data;
            E[] v2 = other.data;
            int i1 = front;
            int i2 = other.front;
            while (i1 != rear) {
                if (!v1[i1].equals(v2[i2])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<E> {
        private int cur = front;

        @Override
        public boolean hasNext() {
            return cur == rear;
        }

        @Override
        public E next() {
            E ret = data[cur];
            cur = (cur + 1) % data.length;
            return ret;
        }
    }
}
