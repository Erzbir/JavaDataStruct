package com.erzbir.dataStruct.Tree;

import com.erzbir.dataStruct.MutableLinkedList.LinkedList;

import java.util.Iterator;

/**
 * @Author: Erzbir
 * @Date: 2022/9/4 10:51
 */
//二分搜索树
public class BinarySearchTree<E extends Comparable<E>> implements Iterable<E> {
    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 && root == null;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    //把元素e添加到以node为根的树中，并返回新树的根 O(logn)~O(n)
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    //以node为根节点的树中查找元素e是否存在 并返回结果
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    //前序遍历-递归
    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.e + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    //前序遍历-迭代
    public void preOrderNR() {
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.e + " ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        System.out.println();
    }

    //中序遍历-递归
    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.e + " ");
        inOrder(node.right);
    }

    //中序遍历-迭代
    public void inOrderNR() {
        LinkedList<Node> stack = new LinkedList<>();
        Node p = root;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.e + " ");
            if (cur.right != null) {
                p = cur.right;
                while (p != null) {
                    stack.push(p);
                    p = p.left;
                }
            }
        }
        System.out.println();
    }

    //后序遍历-递归 后序遍历-迭代 下去自己搜
    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e + " ");
    }

    //层序遍历-迭代
    public void levelOrder() {
        if (isEmpty()) {
            System.out.println("");
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print(cur.e + " ");
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        System.out.println();
    }

    //获取最大值
    public E maximum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("bst is empty");
        }
        return maximum(root).e;
    }

    //获取最大值所在的结点
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    //获取最小值
    public E minimum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("bst is empty");
        }
        return minimum(root).e;
    }

    //获取最小值所在的结点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    //删除最小值
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    //删除以node为根的树中的最小值，并返回更新后的新树的根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    //删除最大值
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    //删除指定值
    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            Node successor = maximum(node.left);
            successor.left = removeMax(node.left);
            successor.right = node.right;
            node.left = node.right = null;
            return successor;
        }
    }

    @Override
    public String toString() {
        LinkedList<E> list = new LinkedList<>();
        inOrderWithList(root, list);
        return list.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof BinarySearchTree) {
            BinarySearchTree<E> other = (BinarySearchTree<E>) o;
            if (size == other.size) {
                return toString().equals(other.toString());
            }
            return false;
        }
        return false;
    }

    private void inOrderWithList(Node node, LinkedList<E> list) {
        if (node == null) {
            return;
        }
        inOrderWithList(node.left, list);
        list.add(node.e);
        inOrderWithList(node.right, list);
    }

    @Override
    public Iterator<E> iterator() {
        return new BSTIterator();
    }

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private class BSTIterator implements Iterator<E> {
        private final Iterator<E> it;

        public BSTIterator() {
            LinkedList<E> list = new LinkedList<>();
            inOrderWithList(root, list);
            it = list.iterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            return it.next();
        }
    }
}