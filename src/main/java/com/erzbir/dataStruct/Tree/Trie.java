package com.erzbir.dataStruct.Tree;

/**
 * @Author: Erzbir
 * @Date: 2022/9/5 16:36
 */
//前缀树
public class Trie {
    private final Node root;
    private int size;   //不重复的单词有几个
    private int sum;    //所有单词的个数（含重复）

    public Trie() {
        root = new Node();
        size = 0;
        sum = 0;
    }

    public int size() {
        return size;
    }

    public int sum() {
        return sum;
    }

    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            cur.count = 1;
            size++;
        } else {
            cur.count++;
        }
        sum++;
    }

    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    public int count(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return 0;
            }
            cur = cur.next.get(c);
        }
        return cur.count;
    }

    private static class Node {
        public boolean isWord;
        public int count;   //某一个单词出现的次数
        public AVLTreeMap<Character, Node> next = new AVLTreeMap<>();
    }
}