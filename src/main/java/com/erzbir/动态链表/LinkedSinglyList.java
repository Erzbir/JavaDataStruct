package com.erzbir.动态链表;

import com.erzbir.接口.List;

import java.util.Comparator;
import java.util.Iterator;

public class LinkedSinglyList<E> implements List<E> {
    private Node head;
    private Node tail;
    private int size;

    public LinkedSinglyList() {
        head = null;
        tail = null;
        size = 0;
    }

    public static void main(String[] args) {
        LinkedSinglyList<Integer> list = new LinkedSinglyList<>();
        list.add(1);
        list.add(564);
        list.add(434);
        list.add(43);
        list.add(523);
        list.add(45);
        list.add(45);
        list.add(78);
        list.add(46);
        list.sort(Comparator.comparingInt(o -> o));
        System.out.println(list);
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
        } else if (index == size) {
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
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of range");
        }
        E ret;
        Node p = head;
        if (size == 1) {
            ret = head.data;
            head = null;
            tail = null;
        } else if (index == 0) {
            ret = head.data;
            head = head.next;
            p.next = null;
        } else if (index == size - 1) {
            ret = tail.data;
            while (p.next != tail) {
                p = p.next;
            }
            p.next = null;
            tail = p;
        } else {
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            Node temp = p.next;
            ret = temp.data;
            p.next = temp.next;
            temp.next = null;

        }
        size--;
        return ret;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of range");
        }
        if (index == 0) {
            return head.data;
        } else if (index == size - 1) {
            return tail.data;
        } else {
            Node p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p.data;
        }
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index out of range");
        }
        E ret;
        if (index == 0) {
            ret = head.data;
            head.data = element;
        } else if (index == size - 1) {
            ret = tail.data;
            tail.data = element;
        } else {
            Node p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            ret = p.data;
            p.data = element;
        }
        return ret;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(E element) {
        if (isEmpty()) {
            throw new IllegalArgumentException("null");
        }
        Node p = head;
        int index = 0;
        while (p != null) {
            if (p.data == element) {
                return index;
            }
            p = p.next;
            index++;
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && head == tail && tail == null;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void sort(Comparator<E> c) {
        if (c == null) {
            throw new IllegalArgumentException("Comparator can not be null");
        }
        if (size == 0 || size == 1) {
            return;
        }
        Node nodeA = head;
        Node nodeB = nodeA.next;
        while (nodeA.next != null) {
            while (nodeB != null) {
                if (c.compare(nodeA.data, nodeB.data) > 0) {
                    swap(nodeA, nodeB);
                }
                nodeB = nodeB.next;
            }
            nodeA = nodeA.next;
            nodeB = nodeA.next;
        }
    }

    @Override
    public List<E> sublist(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex >= toIndex) {
            throw new IllegalArgumentException("Illegal");
        }
        LinkedSinglyList<E> sublist = new LinkedSinglyList<>();
        Node nodeA = head;
        int i = 0;
        for (; i < fromIndex; i++) {
            nodeA = nodeA.next;
        }
        Node nodeB = nodeA;
        for (; i < toIndex; i++) {
            nodeB = nodeB.next;
        }
        Node p = nodeA;
        while (p != null) {
            sublist.add(p.data);
            p = p.next;
        }
        return sublist;
    }

    private void swap(Node A, Node B) {
        E temp = A.data;
        A.data = B.data;
        B.data = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node p = head;
        while (p != null) {
            sb.append(p.data);
            if (p != tail) {
                sb.append(", ");
            }
            p = p.next;
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
        if (o instanceof LinkedSinglyList) {
            LinkedSinglyList<E> other = (LinkedSinglyList<E>) o;
            if (size == other.size) {
                Node p1 = head;
                Node p2 = other.head;
                while (p1 != null) {
                    if (!p1.data.equals(p2.data)) {
                        return false;
                    }
                    p1 = p1.next;
                    p2 = p2.next;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedSinglyListIterator();
    }

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

    private class LinkedSinglyListIterator implements Iterator<E> {
        Node cur = head;

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public E next() {
            E ret = cur.data;
            cur = cur.next;
            return ret;
        }
    }
}
