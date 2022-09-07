package com.erzbir.test;

import com.erzbir.排序与查找.QuickSortThreeWay;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @Author: Erzbir
 * @Date: 2022/9/7 10:55
 */
public class TestPriorityQueue {
    public static void main(String[] args) {
        //JavaAPI自带的 本身底层是由最小堆实现的
        /*
        PriorityQueue<DiRen> queue = new PriorityQueue<>(new Comparator<DiRen>() {
            @Override
            public int compare(DiRen o1, DiRen o2) {
                return o2.level - o1.level;
            }
        });
        queue.offer(new DiRen("大蛇丸", 30));
        queue.offer(new DiRen("比克大魔王", 50));
        queue.offer(new DiRen("全魔", 30));
        queue.offer(new DiRen("黑暗钢铁海龙兽", 25));
        queue.offer(new DiRen("小丑皇", 40));
        queue.offer(new DiRen("灭霸", 999));
        queue.offer(new DiRen("泷泽萝拉", 100000));
        System.out.println(queue);*/
        /*
        //自己写的
        PriorityQueue<DiRen> queue = new PriorityQueue();
        queue.offer(new DiRen("大蛇丸", 30));
        queue.offer(new DiRen("比克大魔王", 50));
        queue.offer(new DiRen("全魔", 30));
        queue.offer(new DiRen("黑暗钢铁海龙兽", 25));
        queue.offer(new DiRen("小丑皇", 40));
        queue.offer(new DiRen("灭霸", 999));
        queue.offer(new DiRen("泷泽萝拉", 100000));
        System.out.println(queue);*/

        //什么时候用到优先队列？
        //在n个数据中 求前k个数据的问题
        int n = 1000000;
        int k = 10000;
        Random random = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100000);
        }


        int[] arr2 = copy(arr);
        ArrayList<Integer> l1 = test01(k, arr);     //三路快速
        ArrayList<Integer> l2 = test02(k, arr2);    //优先队列
        System.out.println(l1.equals(l2));
    }

    private static ArrayList<Integer> test02(int k, int[] arr) {
        long start = System.currentTimeMillis();
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int j : arr) {  //O(nlogn)
            queue.add(j);                  //O(logn) 最差
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {           //O(klogk)
            list.add(queue.poll());             //O(logk)
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");
        return list;
    }

    private static ArrayList<Integer> test01(int k, int[] arr) {
        long start = System.currentTimeMillis();
        QuickSortThreeWay.quickSort(arr, 0, arr.length - 1);    //O(nlogn) 最好
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {                               //O(k)
            list.add(arr[i]);//O(1)
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");
        return list;
    }


    public static int[] copy(int[] arr) {
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        return temp;
    }
}

class DiRen implements Comparable<DiRen> {
    public String name;
    public int level;

    public DiRen(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public String toString() {
        return name + ":" + level;
    }

    /*
    最大堆 level-o.level 最大的在前面
           o.level-level 最小的在前面 -> 最小堆
    最小堆 level-o.level 最小的在前面
           o.level-level 最大的在前面 -> 最大堆
    */
    @Override
    public int compareTo(DiRen o) {
        return o.level - level;
    }
}