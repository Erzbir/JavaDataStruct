package 接口;

import java.util.Iterator;

public interface Stack<E> extends Iterable<E> {
    public int size();
    public boolean isEmpty();
    public void push(E element);
    public E pop();
    public E peek();
    public void clear();
}
