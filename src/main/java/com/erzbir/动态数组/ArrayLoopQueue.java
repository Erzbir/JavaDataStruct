package com.erzbir.动态数组;

import com.erzbir.接口.Queue;

import java.util.Iterator;

public class ArrayLoopQueue<E> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int front;
    private int rear;
    private int size;

    public ArrayLoopQueue() {
        data = (E[]) new Object[DEFAULT_CAPACITY + 1];
        front = 0;
        rear = 0;
        size = 0;
    }

    public static void main(String[] args) {
        ArrayLoopQueue<Integer> queue = new ArrayLoopQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(5);
        queue.offer(5);
        queue.offer(5);
        queue.offer(5);
        queue.offer(5);
        queue.offer(5);
        queue.offer(5);
        queue.offer(5);
        queue.offer(5);
        queue.offer(5);
        queue.offer(5);
        queue.offer(5);
        queue.offer(5);
        queue.offer(5);
        queue.offer(5);
        System.out.println(queue);
        queue.poll();
        queue.poll();
        queue.poll();
        queue.poll();
        queue.poll();
        System.out.println(queue);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && front == rear;
    }

    @Override
    public void offer(E element) {
        if ((rear + 1) % data.length == front) {
            resize(data.length * 2 - 1);
        }
        data[rear] = element;
        rear = (rear + 1) % data.length;
        size++;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        E ret = data[front];
        front = (front + 1) % data.length;
        size--;
        if (size == ((data.length) - 1) / 4 && data.length - 1 > DEFAULT_CAPACITY) {
            resize(data.length / 2 + 1);
        }
        return ret;
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
    public E element() {
        if (isEmpty()) {
            throw new IllegalArgumentException("null");
        }
        return data[front];
    }

    @Override
    public void clear() {
        data = (E[]) new Object[DEFAULT_CAPACITY + 1];
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
        if (o instanceof ArrayLoopQueue) {
            ArrayLoopQueue<E> other = (ArrayLoopQueue<E>) o;
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
        return new ArrayLoopQueueIterator();
    }

    private class ArrayLoopQueueIterator implements Iterator<E> {
        private int cur = front;

        @Override
        public boolean hasNext() {
            return cur != rear;
        }

        @Override
        public E next() {
            E ret = data[cur];
            cur = (cur + 1) % data.length;
            return ret;
        }
    }
}
