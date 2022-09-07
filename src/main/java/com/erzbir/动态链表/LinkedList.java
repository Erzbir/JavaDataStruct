package com.erzbir.动态链表;

import com.erzbir.接口.Deque;
import com.erzbir.接口.List;
import com.erzbir.接口.Queue;
import com.erzbir.接口.Stack;

import java.util.Comparator;
import java.util.Iterator;

//双向循环链表
public class LinkedList<E> implements List<E>, Deque<E>, Stack<E>, Queue<E> {

    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
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
            throw new IllegalArgumentException("add index out of range");
        }
        Node node = new Node(element);
        if (isEmpty()) {            //为空时
            head = node;
            tail = node;
            tail.next = head;
            head.pre = tail;
        } else if (index == 0) {    //表头
            node.pre = head.pre;
            node.next = head;
            head.pre = node;
            head = node;
            tail.next = head;
        } else if (index == size) { //表尾
            node.next = tail.next;
            tail.next = node;
            node.pre = tail;
            tail = node;
            head.pre = tail;
        } else {                    //中间
            Node p, q;
            if (index <= size / 2) {
                p = head;
                for (int i = 0; i < index - 1; i++) {
                    p = p.next;
                }
                q = p.next;
                p.next = node;
                node.pre = p;
                q.pre = node;
                node.next = q;
            } else {
                p = tail;
                for (int i = size - 1; i > index; i--) {
                    p = p.pre;
                }
                q = p.pre;
                q.next = node;
                node.pre = q;
                p.pre = node;
                node.next = p;
            }
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
            throw new IllegalArgumentException("remove index out of range");
        }
        E ret;

        if (size == 1) {                //为1时
            ret = head.data;
            head = null;
            tail = null;
        } else if (index == 0) {        //删表头
            ret = head.data;
            Node node = head.next;
            head.next = null;
            node.pre = head.pre;
            head.pre = null;
            head = node;
            tail.next = head;
        } else if (index == size - 1) { //删表尾
            ret = tail.data;
            Node node = tail.pre;
            tail.pre = null;
            node.next = tail.next;
            tail.next = null;
            tail = node;
            head.pre = tail;
        } else {                        //删中间
            Node p, q, r;
            if (index <= size / 2) {
                p = head;
                for (int i = 0; i < index - 1; i++) {
                    p = p.next;
                }
                q = p.next;
                r = q.next;
                ret = q.data;
                p.next = r;
                r.pre = p;
                q.pre = null;
                q.next = null;
            } else {
                p = tail;
                for (int i = size - 1; i > index + 1; i--) {
                    p = p.pre;
                }
                q = p.pre;
                r = q.pre;
                ret = q.data;
                r.next = p;
                p.pre = r;
                q.pre = null;
                q.next = null;
            }
        }
        size--;
        return ret;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get index out of range");
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
            throw new IllegalArgumentException("set index out of range");
        }
        E ret = null;
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
    public void addFirst(E element) {
        add(0, element);
    }

    @Override
    public void addLast(E element) {
        add(element);
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public E getFirst() {
        return get(0);
    }

    @Override
    public E getLast() {
        return get(size - 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(E element) {
        if (isEmpty()) {
            return -1;
        }
        Node p = head;
        int index = 0;
        while (!p.data.equals(element)) {
            p = p.next;
            index++;
            if (p == head) {
                return -1;
            }
        }
        return index;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && head == null && tail == null;
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
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void sort(Comparator<E> c) {
        if (c == null) {
            throw new IllegalArgumentException("c can not be null");
        }
        if (size == 0 || size == 1) {
            return;
        }
        Node nodeA = head;
        Node nodeB = nodeA.next;
        while (true) {
            while (true) {
                if (c.compare(nodeA.data, nodeB.data) > 0) {
                    swap(nodeA, nodeB);
                }
                if (nodeB == tail) {
                    break;
                }
                nodeB = nodeB.next;
            }
            if (nodeA.next == tail) {
                break;
            }
            nodeA = nodeA.next;
            nodeB = nodeA.next;
        }
    }

    private void swap(Node A, Node B) {
        E temp = A.data;
        A.data = B.data;
        B.data = temp;
    }

    @Override
    public List<E> sublist(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex >= toIndex) {
            throw new IllegalArgumentException("0 <= fromIndex < toIndex <= size");
        }
        LinkedList<E> sublist = new LinkedList<>();
        Node nodeA = head;
        for (int i = 0; i < fromIndex; i++) {
            nodeA = nodeA.next;
        }
        Node nodeB = head;
        for (int i = 0; i < toIndex; i++) {
            nodeB = nodeB.next;
        }
        Node p = nodeA;
        while (true) {
            sublist.add(p.data);
            p = p.next;
            if (p == nodeB) {
                break;
            }
        }
        return sublist;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node p = head;
        while (true) {
            sb.append(p.data);
            if (p != tail) {
                sb.append(", ");
                break;
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
        if (o instanceof LinkedList) {
            LinkedList<E> other = (LinkedList<E>) o;
            if (size == other.size) {
                if (size == 0) {
                    return true;
                }
                Node p1 = head;
                Node p2 = other.head;
                while (true) {
                    if (!p1.data.equals(p2.data)) {
                        return false;
                    }
                    p1 = p1.next;
                    p2 = p2.next;
                    if (p1 == head) {
                        break;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class Node {
        E data;
        Node pre;   //前驱指针
        Node next;  //后继指针

        public Node() {
            this(null, null, null);
        }

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node pre, Node next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node cur = head;
        private boolean flag = true;    //没有转一圈

        @Override
        public boolean hasNext() {
            if (isEmpty()) {
                return false;
            }
            return flag;
        }

        @Override
        public E next() {
            E ret = cur.data;
            cur = cur.next;
            if (cur == head) {
                flag = false;
            }
            return ret;
        }
    }
}