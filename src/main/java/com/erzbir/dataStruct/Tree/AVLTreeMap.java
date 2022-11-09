package com.erzbir.dataStruct.Tree;

import com.erzbir.dataStruct.MutableArray.ArrayList;
import com.erzbir.dataStruct.MutableLinkedList.LinkedList;
import com.erzbir.dataStruct.inter.List;
import com.erzbir.dataStruct.inter.Map;
import com.erzbir.dataStruct.inter.Set;

import java.util.Iterator;

/**
 * @Author: Erzbir
 * @Date: 2022/9/5 14:45
 */
//实现了AVL自平衡的二分搜索树所封装出来的映射map
public class AVLTreeMap<K extends Comparable<K>, V> implements Map<K, V> {
    private int size;
    private Node root;

    public AVLTreeMap() {
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

    //获取某一个结点的高度
    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //获取某一个结点的平衡因子 左子树高度-右子树高度
    public int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    //验证是一颗二分搜索树
    public boolean isBST() {
        ArrayList<K> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node.key);
        inOrder(node.right, list);
    }

    //验证是否每一个结点都是平衡的
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1 || balanceFactor < -1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    //LL旋转-右旋转
    private Node rightRotate(Node node) {
        Node x = node.left;
        Node T3 = x.right;
        x.right = node;
        node.left = T3;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    //RR旋转-左旋转
    public Node leftRotate(Node node) {
        Node x = node.right;
        Node T3 = x.left;
        x.left = node;
        node.right = T3;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
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
        //更新node的高度
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        //获取平衡因子
        int balancedFactor = getBalanceFactor(node);

        //左侧的左侧
        if (balancedFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        //左侧的右侧
        if (balancedFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //右侧的右侧
        if (balancedFactor < -1 && getBalanceFactor(node.right) < 0) {
            return leftRotate(node);
        }
        //右侧的左侧
        if (balancedFactor < -1 && getBalanceFactor(node.right) >= 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
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
        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        //返回的结点有可能为空
        if (retNode == null) {
            return null;
        }

        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;
        int balancedFactor = getBalanceFactor(retNode);

        //左侧的左侧
        if (balancedFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }
        //左侧的右侧
        if (balancedFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //右侧的右侧
        if (balancedFactor < -1 && getBalanceFactor(retNode.right) < 0) {
            return leftRotate(retNode);
        }
        //右侧的左侧
        if (balancedFactor < -1 && getBalanceFactor(retNode.right) >= 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
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
        set.add(new AVLTreeMapEntry<>(node.key, node.value));
        inOrderEntrySet(node.right, set);
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new AVLTreeMapIterator();
    }

    public record AVLTreeMapEntry<K extends Comparable<K>, V>(K key, V value) implements Entry<K, V> {

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
        public int height;

        public Node() {
            this(null, null);
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private class AVLTreeMapIterator implements Iterator<Entry<K, V>> {
        private final Iterator<Entry<K, V>> it;

        public AVLTreeMapIterator() {
            LinkedList<Entry<K, V>> list = new LinkedList<>();
            inOrderEntry(root, list);
            it = list.iterator();
        }

        private void inOrderEntry(Node node, LinkedList<Entry<K, V>> list) {
            if (node == null) {
                return;
            }
            inOrderEntry(node.left, list);
            list.add(new AVLTreeMapEntry<>(node.key, node.value));
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