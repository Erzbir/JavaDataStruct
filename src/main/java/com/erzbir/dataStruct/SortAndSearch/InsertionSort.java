package com.erzbir.dataStruct.SortAndSearch;

import java.util.Arrays;

/**
 * @Author: Erzbir
 * @Date: 2022/9/1 10:03
 */
//插入排序 O(n^2) S(1) 稳定
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {8, 2, 5, 3, 6, 4, 7, 9, 1};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j - 1] > arr[j]; j--) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
            }
        }
    }
}