package com.erzbir.排序与查找;

import java.util.Arrays;

/**
 * @Author: Erzbir
 * @Date: 2022/9/1 9:31
 */
//冒泡排序 O(n^2) S(1) 稳定
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {8, 2, 5, 3, 6, 4, 7, 9, 1};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}