package 动态链表;

import org.jetbrains.annotations.NotNull;
import 接口.List;

import java.util.Comparator;
import java.util.Iterator;

public class LinkedSinglyList<E> implements List<E> {
    private class Node {
        E data;
        Node next;

        public Node() {
            this(null, null);
        }

        public Node(E data) {
            this(data, null);
        }

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedSinglyList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index out of range");
        }
        Node node = new Node(element);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else if (index == 0) {
            node.next = head;
            head = node;
        } else if (index == size){
            tail.next = node;
            tail = node;
        } else {
            Node p = head;
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            node.next = p.next;
            p.next = node;
        }
        size++;
    }

    @Override
    public void remove(E element) {

    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of range");
        }
        E ret = null;
        if (size == 1) {
            ret = head.data;
            head = null;
            tail = null;
        } else if (index == 0) {
            ret = head.data;
            Node p = head;
            head = head.next;
            p.next = null;
        } else if (index == size - 1) {
            ret = tail.data;
            Node p = head;
            while (p.next != tail) {
                p = p.next;
            }
            p.next = null;
            tail = p;
        } else {
            Node p = head;
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            Node temp = p.next;
            ret = temp.data;
            p.next = temp.next;
            temp.next = null;

        }
        return ret;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void sort(Comparator<E> c) {

    }

    @Override
    public List<E> sublist(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public void swap(int i, int j) {

    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
