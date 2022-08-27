package 接口;

public interface Queue<E> extends Iterable<E> {
    public int size();

    public boolean isEmpty();

    public void offer(E element);

    public E poll();

    public E element();

    public void clear();
}
