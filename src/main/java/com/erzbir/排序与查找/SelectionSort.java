package com.erzbir.排序与查找;

import java.util.Arrays;

/**
 * @Author: Erzbir
 * @Date: 2022/9/1 9:26
 */
//选择排序 O(n^2) S(1) 不稳定
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {8, 2, 5, 3, 6, 4, 7, 9, 1};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}