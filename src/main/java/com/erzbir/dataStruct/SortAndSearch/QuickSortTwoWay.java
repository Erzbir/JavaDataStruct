package com.erzbir.dataStruct.SortAndSearch;

import java.util.Arrays;

/**
 * @Author: Erzbir
 * @Date: 2022/9/2 11:11
 */
//双路快速排序
public class QuickSortTwoWay {
    public static void main(String[] args) {
        int[] arr = {9, 2, 8, 3, 6, 4, 5, 10, 7, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    //目标 arr[l,p-1] <= arr[p] <= arr[p+1,r]
    //操作 arr[l,i] <= v <= arr[j,r]
    private static int partition(int[] arr, int l, int r) {
        //为了避免产生极端子树的情况 我们可以把中间的元素跟第一个元素换一下
        swap(arr, l, (r - l) / 2 + l);
        int v = arr[l];
        int i = l + 1;
        int j = r;
        while (i <= j) {
            while (i <= j && arr[i] <= v) {
                i++;
            }
            while (i <= j && arr[j] >= v) {
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}