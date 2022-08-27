package 动态链表;

import 接口.List;

import java.util.Comparator;
import java.util.Iterator;

public class LinkedSinglyCircularList<E> implements List<E> {
    private Node head;  //头指针
    private Node tail;  //尾指针
    private int size;   //元素个数

    public LinkedSinglyCircularList() {
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
            tail.next = head;       //new code
        } else if (index == 0) {    //表头添加
            node.next = head;
            head = node;
            tail.next = head;       //new code
        } else if (index == size) { //表尾添加
            node.next = tail.next;  //new code
            tail.next = node;
            tail = node;
        } else {                //在表中添加
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
            throw new IllegalArgumentException("remove index out of range");
        }
        E ret = null;
        if (size == 1) {    //只剩一个
            ret = head.data;
            head = null;
            tail = null;
        } else if (index == 0) {    //删除头
            ret = head.data;
            Node node = head;
            head = head.next;
            node.next = null;
            tail.next = head;   //new code
        } else if (index == size - 1) { //删除尾
            ret = tail.data;
            Node p = head;
            while (p.next != tail) {
                p = p.next;
            }
            p.next = head;      //change code
            tail.next = null;   //new code
            tail = p;
        } else {    //删除中间
            Node p = head;
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            Node del = p.next;
            ret = del.data;
            p.next = del.next;
            del.next = null;
        }
        size--;
        return ret;
    }

    @Override
    public E get(int index) {   //O(n)
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
    public E set(int index, E element) {    //O(n)
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
    public int size() {
        return size;
    }

    @Override
    public int indexOf(E element) {
        if (isEmpty()) {
            throw new IllegalArgumentException("list is empty");
        }
        Node p = head;
        int index = 0;
        while (!p.data.equals(element)) {
            p = p.next;
            index++;
            if (p == head) {    //change code
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
        LinkedSinglyList<E> sublist = new LinkedSinglyList<>();
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
        if (o instanceof LinkedSinglyCircularList) {
            LinkedSinglyCircularList<E> other = (LinkedSinglyCircularList<E>) o;
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
        return new LinkedSinglyCircularListIterator();
    }

    // 将单项循环链表进行约瑟夫环化
    public void josephusLoop() {
        if (size < 2) {
            return;
        }
        Node p = head;
        while (size > 2) {
            p = p.next;
            Node del = p.next;
            if (del == head) {
                head = del.next;
            } else if (del == tail) {
                tail = p;
            }
            p.next = del.next;
            del.next = null;
            p = p.next;
            size--;
        }
    }

    private class Node {
        public E data;     //数据域
        public Node next;  //指针域

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

    private class LinkedSinglyCircularListIterator implements Iterator<E> {
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