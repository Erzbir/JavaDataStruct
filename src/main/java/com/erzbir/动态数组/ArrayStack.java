package com.erzbir.动态数组;

import com.erzbir.接口.Stack;

import java.util.Iterator;

public class ArrayStack<E> implements Stack<E> {
    private final ArrayList<E> list;

    public ArrayStack() {
        list = new ArrayList<>();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
        System.out.println(stack);
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
    public void push(E element) {
        list.add(element);
    }

    @Override
    public E pop() {
        return list.remove(list.size() - 1);
    }

    @Override
    public E peek() {
        return list.get(list.size() - 1);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
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
        if (o instanceof ArrayStack) {
            ArrayStack<E> other = (ArrayStack<E>) o;
            return list.equals(other.list);
        }
        return false;
    }
}
