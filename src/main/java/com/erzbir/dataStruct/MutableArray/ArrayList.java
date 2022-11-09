package com.erzbir.dataStruct.MutableArray;

import com.erzbir.dataStruct.inter.List;

import java.util.Comparator;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private E[] data;

    public ArrayList() {
        this.size = 0;
        this.data = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(E[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("arr is null");
        }
        this.data = (E[]) new Object[DEFAULT_CAPACITY];
        for (E e : arr) {
            add(e);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(3, 999999);
        System.out.println(list.size);
        System.out.println(list);
        list.remove(1);
        list.sort(Comparator.comparingInt(o -> o));
        System.out.println(list);
        System.out.println(list.sublist(0, 4));
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }

    // 扩容缩容
    public void resize(int newLen) {
        E[] newData = (E[]) new Object[newLen];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public void remove(E element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        E ret = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        if (data.length > 10 && size == data.length >> 2) {
            resize(data.length / 2);
        }
        return ret;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return data[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        data[index] = element;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) { // 用已存在的跟传入比较, 避免传入的值是空
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
    }


    @Override
    public void sort(Comparator<E> c) {
        if (c == null) {
            throw new IllegalArgumentException("Comparator can not be null");
        }
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (c.compare(data[i], data[j]) > 0) {
                    swap(i, j);
                }
            }
        }
    }

    @Override
    public List<E> sublist(int fromIndex, int toIndex) {
        // 左闭右开 [fromIndex, toIndex)
        if (fromIndex < 0 || toIndex > size || fromIndex >= toIndex) {
            throw new IllegalArgumentException("Illegal");
        }
        ArrayList<E> sublist = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            sublist.add(data[i]);
        }
        return sublist;
    }

    public void swap(int i, int j) {
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o instanceof ArrayList) {
            ArrayList<E> other = (ArrayList<E>) o;
            if (size == other.size) {
                E[] v1 = data;
                E[] v2 = other.data;
                int i = 0;
                while (i < size) {
                    if (!v1[i].equals(v2[2])) {
                        return false;
                    }
                    i++;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                sb.append(data[i]);
                if (i != size - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private class ArrayListIterator implements Iterator<E> {
        private int cur = 0;

        @Override
        public boolean hasNext() {
            return cur < size;
        }

        @Override
        public E next() {
            return data[cur++];
        }
    }
}
