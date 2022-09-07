package com.erzbir.树;


import com.erzbir.动态链表.LinkedList;
import com.erzbir.接口.List;
import com.erzbir.接口.Map;
import com.erzbir.接口.Set;

import java.util.Iterator;

/**
 * @Author: Erzbir
 * @Date: 2022/9/5 9:36
 */
//底层由二分搜索树实现的映射Map
public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {
    private int size;
    private Node root;

    public TreeMap() {
        size = 0;
        root = null;
    }

    //辅助函数 获取指定key所在的结点
    //在以node为根的子树中查找key所存在的结点 并返回
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }

    //如果键值对不存在 则添加；否则 修改值
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    //在以node为根的子树中 添加/修改 键值对，并返回更新后新树的根
    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    @Override
    public V remove(K key) {
        Node delNode = getNode(root, key);
        if (delNode != null) {
            root = remove(root, key);
            return delNode.value;
        }
        return null;
    }

    //在以node为根的子树中，删除key指定的结点，并返回更新后新树的根
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
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
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

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

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return minimum(node.left);
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public V set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException("key is not exists");
        }
        V ret = node.value;
        node.value = value;
        return ret;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && root == null;
    }

    @Override
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<>();
        inOrderKeySet(root, set);
        return set;
    }

    private void inOrderKeySet(Node node, TreeSet<K> set) {
        if (node == null) {
            return;
        }
        inOrderKeySet(node.left, set);
        set.add(node.key);
        inOrderKeySet(node.right, set);
    }

    @Override
    public List<V> values() {
        LinkedList<V> list = new LinkedList<>();
        inOrderValues(root, list);
        return list;
    }

    private void inOrderValues(Node node, LinkedList<V> list) {
        if (node == null) {
            return;
        }
        inOrderValues(node.left, list);
        list.add(node.value);
        inOrderValues(node.right, list);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        TreeSet<Entry<K, V>> set = new TreeSet<>();
        inOrderEntrySet(root, set);
        return set;
    }

    private void inOrderEntrySet(Node node, TreeSet<Entry<K, V>> set) {
        if (node == null) {
            return;
        }
        inOrderEntrySet(node.left, set);
        set.add(new TreeMapEntry<>(node.key, node.value));
        inOrderEntrySet(node.right, set);
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new TreeMapIterator();
    }

    public record TreeMapEntry<K extends Comparable<K>, V>(K key, V value) implements Entry<K, V> {

        @Override
        public int compareTo(Entry<K, V> o) {
            return key.compareTo(o.key());
        }

        @Override
        public String toString() {
            return "(" + key.toString() + "," + value.toString() + ")";
        }
    }

    //定义结点
    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node() {
            this(null, null);
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private class TreeMapIterator implements Iterator<Entry<K, V>> {
        private final Iterator<Entry<K, V>> it;

        public TreeMapIterator() {
            LinkedList<Entry<K, V>> list = new LinkedList<>();
            inOrderEntry(root, list);
            it = list.iterator();
        }

        private void inOrderEntry(Node node, LinkedList<Entry<K, V>> list) {
            if (node == null) {
                return;
            }
            inOrderEntry(node.left, list);
            list.add(new TreeMapEntry<>(node.key, node.value));
            inOrderEntry(node.right, list);
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Entry<K, V> next() {
            return it.next();
        }
    }
}