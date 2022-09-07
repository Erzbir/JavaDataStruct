package com.erzbir.排序与查找;

import com.erzbir.动态链表.LinkedList;

import java.util.Random;

/**
 * @Author: Erzbir
 * @Date: 2022/9/2 14:59
 */
//基数排序 O(nm) S(?) 稳定
public class RadixSort {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000);
        }
        radixSort(arr);
        int count = 0;
        for (Integer num : arr) {
            System.out.print(num + "\t");
            count++;
            if (count % 10 == 0) {
                System.out.println();
            }
        }
    }

    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int radix = (max + "").length();

        LinkedList<Integer>[] lists = new LinkedList[10];

        for (int i = 0; i < lists.length; i++) {
            lists[i] = new LinkedList<>();
        }

        for (int r = 1; r <= radix; r++) {
            //分类
            for (int j : arr) {
                lists[getIndex(j, r)].offer(j);
            }

            //聚合
            int k = 0;
            for (LinkedList<Integer> list : lists) {
                while (!list.isEmpty()) {
                    arr[k++] = list.poll();
                }
            }
        }

    }

    private static int getIndex(int num, int r) {
        String s = num + "";
        if (r > s.length()) {
            return 0;
        }
        //"123456"
        return s.charAt(s.length() - r) - '0';
/*        int mod = 0;
        for (int i = 1; i <= r; i++) {
            mod = num % 10;
            num /= 10;
        }
        return mod;*/
        /*
        123 % 10 = 3

        123 / 10 = 12
        12 % 10 = 2

        12 / 10 = 1
        1 % 10 = 1

        1 / 10 = 0
        0 % 10 = 0

        0 / 10 = 0
        0 % 10 = 0
        */
    }
}