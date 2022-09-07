package com.erzbir.test;


import com.erzbir.接口.Map;
import com.erzbir.树.FileOperation;
import com.erzbir.树.TreeMap;

import java.util.ArrayList;

/**
 * @Author: Erzbir
 * @Date: 2022/9/5 11:10
 */
public class TestTreeMap {
    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("cat", 12);
        map.put("zoo", 12);
        map.put("abandon", 10);
        map.put("banana", 10);
        map.put("dog", 13);
        System.out.println(map.keySet());
        System.out.println(map.values());
        System.out.println(map.entrySet());
        for (Object entry : map) {
            System.out.println(entry);
        }
        map.remove("cat");
        map.set("zoo", 30);
        for (Map.Entry<String, Integer> stringIntegerEntry : map) {
            System.out.println(stringIntegerEntry);
        }

        TreeMap<String, Integer> map2 = new TreeMap<>();
        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt", words);

        for (String word : words) {
            if (map2.contains(word)) {
                map2.set(word, map2.get(word) + 1);
            } else {
                map2.put(word, 1);
            }
        }
        System.out.println("All words:" + map2.size());
        for (Object entry : map2) {
            System.out.println(entry);
        }
        System.out.println(map2.get("pride"));
    }
}