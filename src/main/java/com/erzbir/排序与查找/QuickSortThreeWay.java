package com.erzbir.排序与查找;

import java.util.Arrays;

/**
 * @Author: Erzbir
 * @Date: 2022/9/2 11:45
 */
//三路快速排序
public class QuickSortThreeWay {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 1, 2, 1, 2, 3, 1, 3, 2, 1, 4, 6, 5, 7, 4, 3, 2, 4, 1, 3, 5, 6, 4, 6, 2, 2, 5, 3, 5, 6, 2, 6, 3, 6, 4, 6, 8, 9, 6, 5, 3, 1, 2, 3, 3, 2, 4, 1, 4, 3, 3, 1, 5, 1, 3, 1, 5, 2, 5, 2, 5};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        swap(arr, l, (r - l) / 2 + l);
        int v = arr[l];
        int lt = l;         //arr[l+1,lt] < v
        int gt = r + 1;     //arr[gt,r] > v
        int i = l + 1;      //arr[lt+1, gt-1] == v
        while (i < gt) {
            if (arr[i] < v) {
                swap(arr, lt + 1, i);
                lt++;
                i++;
            } else if (arr[i] > v) {
                swap(arr, gt - 1, i);
                gt--;
            } else {
                i++;
            }
        }
        swap(arr, l, lt);
        quickSort(arr, l, lt - 1);
        quickSort(arr, gt, r);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}