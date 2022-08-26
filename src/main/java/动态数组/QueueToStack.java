package 动态数组;


import 接口.Queue;
import 接口.Stack;

import java.util.Iterator;

public class QueueToStack {
    public static void main(String[] args) {
        StackImplByQueue<Integer> stack = new StackImplByQueue<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
    }
}

class StackImplByQueue<E> implements Stack<E> {
    private ArrayQueue<E> queueA;
    private ArrayQueue<E> queueB;

    public StackImplByQueue() {
        queueA = new ArrayQueue<>();
        queueB = new ArrayQueue<>();
    }

    @Override
    public int size() {
        return queueA.size() + queueB.size();
    }

    @Override
    public boolean isEmpty() {
        return queueA.isEmpty() && queueB.isEmpty();
    }

    @Override
    public void push(E element) {
        if (isEmpty()) {
            queueA.offer(element);
        }
        if (queueA.isEmpty()) {
            queueB.offer(element);
        } else {
            queueA.offer(element);
        }
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("stack is null");
        }
        if (queueB.isEmpty()) {
            while (queueA.size() != 1) {
                queueB.offer(queueA.poll());
            }
            return queueA.poll();
        } else {
            while (queueB.size() != 1) {
                queueA.offer(queueB.poll());
            }
            return queueB.poll();
        }
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("stack is null");
        }
        if (queueB.isEmpty()) {
            while (queueA.size() != 1) {
                queueB.offer(queueA.poll());
            }
            E ret = queueA.poll();
            queueB.offer(ret);
            return ret;
        } else {
            while (queueB.size() != 1) {
                queueA.offer(queueB.poll());
            }
            E ret = queueB.poll();
            queueA.offer(ret);
            return ret;
        }
    }

    @Override
    public void clear() {
        queueA.clear();
        queueB.clear();
    }

    @Override
    public Iterator<E> iterator() {
        if (queueB.isEmpty()) {
            return queueA.iterator();
        } else {
            return queueB.iterator();
        }
    }

    @Override
    public String toString() {
        if (queueA.isEmpty()) {
            return queueB.toString();
        } else {
            return queueA.toString();
        }
    }
}