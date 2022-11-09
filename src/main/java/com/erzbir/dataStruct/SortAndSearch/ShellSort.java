package com.erzbir.dataStruct.SortAndSearch;

import java.util.Arrays;

/**
 * @Author: Erzbir
 * @Date: 2022/9/1 11:31
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {9, 2, 8, 3, 1, 3, 6, 7};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap >= 1; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j - gap >= 0 && arr[j - gap] > arr[j]; j -= gap) {
                    swap(arr, j, j - gap);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
