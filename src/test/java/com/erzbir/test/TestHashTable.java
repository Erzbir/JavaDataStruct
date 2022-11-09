package com.erzbir.test;


import com.erzbir.dataStruct.Tree.*;
import com.erzbir.树.*;

import java.util.ArrayList;

/**
 * @Author: Erzbir
 * @Date: 2022/9/7 14:02
 */
public class TestHashTable {
    public static void main(String[] args) {
        //TreeMap
        //AVLTreeMap
        //Trie
        //HashTable
        //添加 查询
        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt", words);

        TreeMap<String, Integer> map1 = new TreeMap<>();
        testTreeMap(words, map1);

        AVLTreeMap<String, Integer> map2 = new AVLTreeMap<>();
        testAVLTreeMap(words, map2);

        HashTable<String, Integer> map3 = new HashTable<>();
        testHashTable(words, map3);

        Trie trie = new Trie();
        testTrie(words, trie);
    }

    private static void testTrie(ArrayList<String> words, Trie trie) {
        System.out.println("Trie");
        //1.测试添加
        long start = System.currentTimeMillis();
        for (String word : words) {
            trie.add(word);
        }
        long end = System.currentTimeMillis();
        System.out.println("size = " + trie.size());
        System.out.println("pride = " + trie.count("pride"));
        System.out.println("添加耗时为:" + (end - start) + "ms");
        //2.测试查询
        start = System.currentTimeMillis();
        for (String word : words) {
            trie.contains(word);
        }
        end = System.currentTimeMillis();
        System.out.println("查询耗时为:" + (end - start) + "ms");
        System.out.println("===========================");
    }

    private static void testHashTable(ArrayList<String> words, HashTable<String, Integer> map) {
        System.out.println("HashTable");
        //1.测试添加
        long start = System.currentTimeMillis();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("size = " + map.size());
        System.out.println("pride = " + map.get("pride"));
        System.out.println("添加耗时为:" + (end - start) + "ms");
        //2.测试查询
        start = System.currentTimeMillis();
        for (String word : words) {
            map.get(word);
        }
        end = System.currentTimeMillis();
        System.out.println("查询耗时为:" + (end - start) + "ms");
        System.out.println("===========================");
    }

    private static void testAVLTreeMap(ArrayList<String> words, AVLTreeMap<String, Integer> map) {
        System.out.println("AVLTreeMap");
        //1.测试添加
        long start = System.currentTimeMillis();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("size = " + map.size());
        System.out.println("pride = " + map.get("pride"));
        System.out.println("添加耗时为:" + (end - start) + "ms");
        //2.测试查询
        start = System.currentTimeMillis();
        for (String word : words) {
            map.get(word);
        }
        end = System.currentTimeMillis();
        System.out.println("查询耗时为:" + (end - start) + "ms");
        System.out.println("===========================");
    }

    private static void testTreeMap(ArrayList<String> words, TreeMap<String, Integer> map) {
        System.out.println("TreeMap");
        //1.测试添加
        long start = System.currentTimeMillis();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("size = " + map.size());
        System.out.println("pride = " + map.get("pride"));
        System.out.println("添加耗时为:" + (end - start) + "ms");
        //2.测试查询
        start = System.currentTimeMillis();
        for (String word : words) {
            map.get(word);
        }
        end = System.currentTimeMillis();
        System.out.println("查询耗时为:" + (end - start) + "ms");
        System.out.println("===========================");
    }
}