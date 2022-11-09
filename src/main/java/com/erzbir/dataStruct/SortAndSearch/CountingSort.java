package com.erzbir.dataStruct.SortAndSearch;

import java.util.Arrays;

/**
 * @Author: Erzbir
 * @Date: 2022/9/2 14:07
 */
//计数排序 O(n) S(m) 稳定
public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {9, 2, 8, 3, 6, 4, 5, 10, 7, 1, -3, -3, -2, 3, 1, 1, 1, 0, 0, 2, 2, 6, 7, 5, 9, 6, 6, 3, 5, 2, 4,};
        countingSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void countingSort(int[] arr) {
        //1.找最大最小值
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //2.创建计数数组
        int[] counts = new int[max - min + 1];

        //3.遍历原数组 统计次数在计数数组中
        for (int j : arr) {
            counts[j - min]++;
        }

        //4.遍历计数数组 将数字的出现情况回填到原数组中
        int k = 0;
        for (int index = 0; index < counts.length; index++) {
            while (counts[index] > 0) {
                arr[k++] = index + min;
                counts[index]--;
            }
        }
    }
}