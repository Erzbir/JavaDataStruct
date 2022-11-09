package com.erzbir.test;

import com.erzbir.dataStruct.inter.Set;
import com.erzbir.dataStruct.Tree.FileOperation;
import com.erzbir.dataStruct.Tree.LinkedSet;
import com.erzbir.dataStruct.Tree.TreeSet;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @Author: Erzbir
 * @Date: 2022/9/4 16:22
 */
public class TestSet {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt", words);
        words.sort(Comparator.naturalOrder());

        System.out.println("LinkedSet:");   ////O(mn)
        LinkedSet<String> set1 = new LinkedSet<>();
        test(set1, words);

        TreeSet<String> set2 = new TreeSet<>(); //O(mlogn)
        System.out.println("TreeSet:");
        test(set2, words);
    }

    private static void test(Set<String> set, ArrayList<String> words) {
        long start = System.currentTimeMillis();
        for (String word : words) {
            set.add(word);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");
        System.out.println(set.size());
    }
}