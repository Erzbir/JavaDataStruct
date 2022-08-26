package 动态数组;

import org.jetbrains.annotations.NotNull;
import 接口.Queue;

import java.util.Iterator;

public class ArrayQueue<E> implements Queue<E> {
    private ArrayList<E> list;



    public ArrayQueue() {
        list = new ArrayList<>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void offer(E element) {
        list.add(element);
    }

    @Override
    public E poll() {
        return list.remove(0);
    }

    @Override
    public E element() {
        return list.get(0);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o instanceof ArrayQueue) {
            ArrayQueue<E> other = (ArrayQueue<E>) o;
            return list.equals(other.list);
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
