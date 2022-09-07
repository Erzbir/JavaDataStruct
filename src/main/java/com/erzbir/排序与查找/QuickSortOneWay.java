package com.erzbir.排序与查找;

import java.util.Arrays;

/**
 * @Author: Erzbir
 * @Date: 2022/9/2 10:51
 */
public class QuickSortOneWay {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 6, 9, 3, 2, 1, 7};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int p = position(arr, L, R);
        quickSort(arr, L, p - 1);
        quickSort(arr, p + 1, R);
    }

    private static int position(int[] arr, int L, int R) {
        swap(arr, L, (R - L) / 2 + L);
        int key = arr[L];
        int i = L + 1;
        int j = L;
        while (i <= R) {
            if (arr[i] <= key) {
                swap(arr, i, j + 1);
                j++;
            }
            i++;
        }
        swap(arr, L, j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
