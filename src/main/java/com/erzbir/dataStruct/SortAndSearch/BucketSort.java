package com.erzbir.dataStruct.SortAndSearch;

import com.erzbir.dataStruct.MutableArray.ArrayList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * @Author: Erzbir
 * @Date: 2022/9/2 15:59
 */
//桶排序
public class BucketSort {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000);
        }
        bucketSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bucketSort(int[] num) {

        // 遍历原始数组，找到数组中的最大值
        int max = num[0];
        int min = num[0];
        for (int k : num) {
            if (k > max) {
                max = k;
            }
            if (k < min) {
                min = k;
            }
        }

        // 创建一个下标为原始数组中最大值的桶数组,该桶数组的下标代表元素，该数组下标所对应的值代表这个值出现的次数

        int[] bucketArray = new int[max - min + 1];

        // 再次遍历原始数组，得到原数组中存在的各个元素，以及出现的次数
        for (int k : num) {
            bucketArray[k - min]++;
        }
        int[] num2 = num.clone();
        Arrays.sort(num2);
        System.out.println(Arrays.toString(num2));
        // 遍历桶数组,外层循环从桶的第一位开始（即下表为零）；内层循环遍历桶数组中下标为i的值出现的次数
        int index = 0;
        for (int i = 0; i < bucketArray.length; i++) {
            for (int j = 0; j < bucketArray[i]; j++) {
                num[index++] = i + min;
            }
        }
    }
}

class SortRunnable implements Runnable {
    private final ArrayList<Integer> list;

    public SortRunnable(ArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        list.sort(Comparator.comparingInt(o -> o));
    }
}