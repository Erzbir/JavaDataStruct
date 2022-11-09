package com.erzbir.test;

import com.erzbir.dataStruct.inter.Map;
import com.erzbir.dataStruct.Tree.AVLTreeMap;
import com.erzbir.dataStruct.Tree.FileOperation;
import com.erzbir.dataStruct.Tree.TreeMap;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @Author: Erzbir
 * @Date: 2022/9/5 15:47
 */
public class TestAVLTreeMap {
    public static void main(String[] args) {
        AVLTreeMap<Integer, String> map = new AVLTreeMap<>();
        map.put(8, "eight");
        map.put(4, "four");
        map.put(6, "six");
        map.put(3, "three");
        map.put(5, "five");
        map.put(1, "one");
        map.put(9, "nine");
        map.put(2, "two");
        map.put(7, "seven");

        System.out.println(map.isBST());
        System.out.println(map.isBalanced());
        for (Object entry : map) {
            System.out.print(entry + " ");
        }
        System.out.println();

        map.remove(8);
        map.remove(6);
        System.out.println(map.isBST());
        System.out.println(map.isBalanced());
        for (Object entry : map) {
            System.out.print(entry + " ");
        }
        System.out.println();

        TreeMap<String, Integer> dic1 = new TreeMap<>();
        AVLTreeMap<String, Integer> dic2 = new AVLTreeMap<>();
        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt", words);

        words.sort(Comparator.naturalOrder());
        test(dic2, words);
        test(dic1, words);

    }

    private static void test(Map<String, Integer> dic, ArrayList<String> words) {
        long start = System.currentTimeMillis();
        for (String word : words) {
            if (!dic.contains(word)) {
                dic.put(word, 1);
            } else {
                dic.set(word, dic.get(word) + 1);
            }
        }
        System.out.println("size = " + dic.size());
        for (String word : words) {
            dic.remove(word);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");
    }
}