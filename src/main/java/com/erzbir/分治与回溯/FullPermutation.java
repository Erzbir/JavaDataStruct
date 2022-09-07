package com.erzbir.分治与回溯;

import java.util.TreeSet;

/**
 * @Author: Erzbir
 * @Date: 2022/8/29 15:59
 */
public class FullPermutation {
    public static void main(String[] args) {
        String s = "ABC";
        char[] arr = s.toCharArray();
        TreeSet<String> set = new TreeSet<>();
        permutation(arr, 0, arr.length - 1, set);
        for (String str : set) {
            System.out.println(str);
        }
    }

    private static void permutation(char[] arr, int from, int to, TreeSet<String> set) {
        if (from == to) {
            set.add(String.valueOf(arr));
        } else {
            for (int i = from; i <= to; i++) {
                swap(arr, i, from);
                permutation(arr, from + 1, to, set);
                swap(arr, i, from);
            }
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
