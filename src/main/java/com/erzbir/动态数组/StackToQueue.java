package com.erzbir.动态数组;

import com.erzbir.接口.Queue;

import java.util.Iterator;

public class StackToQueue<E> {
    public static void main(String[] args) {

    }

}

class QueueImplByStack<E> implements Queue<E> {
    private ArrayStack<E> stackA;
    private ArrayStack<E> stackB;

    public QueueImplByStack() {
        stackA = new ArrayStack<>();
        stackB = new ArrayStack<>();
    }

    @Override
    public int size() {
        return stackA.size();
    }

    @Override
    public boolean isEmpty() {
        return stackA.isEmpty();
    }

    @Override
    public void offer(E element) {
        stackA.push(element);
    }

    @Override
    public E poll() {
        while (!stackA.isEmpty()) {
            stackB.push(stackA.pop());
        }
        E ret = stackB.pop();
        while (!stackB.isEmpty()) {
            stackA.push(stackB.pop());
        }
        return ret;
    }

    @Override
    public E element() {
        while (!stackA.isEmpty()) {
            stackB.push(stackA.pop());
        }
        E ret = stackB.peek();
        while (!stackB.isEmpty()) {
            stackA.push(stackB.pop());
        }
        return ret;
    }

    @Override
    public void clear() {
        stackA.clear();
    }

    @Override
    public Iterator<E> iterator() {
        return stackA.iterator();
    }
}
