package 排序与查找;

import java.util.Random;

/**
 * @Author: Erzbir
 * @Date: 2022/9/1 10:11
 */
/*
排序的时间 除了和算法自身的策略有关之外 还和数据的分布情况有关
(1)完全随机 冒泡>=选择>插入
(2)趋于平稳 冒泡>选择>插入
(3)大致升序 选择>冒泡>插入
(4)大致降序 选择>冒泡>插入
*/
public class TestSort {
    public static void main(String[] args) {
        int[] arr = Data.getData04();
        int[] arr1 = Data.copy(arr);
        int[] arr2 = Data.copy(arr);
        int[] arr3 = Data.copy(arr);

        testSelectionSort(arr1);
        testBubbleSort(arr2);
        testInsertionSort(arr3);
    }

    private static void testInsertionSort(int[] arr) {
        long start = System.currentTimeMillis();
        InsertionSort.insertionSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("插入排序:" + (end - start) + "ms");
    }

    private static void testBubbleSort(int[] arr) {
        long start = System.currentTimeMillis();
        BubbleSort.bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("冒泡排序:" + (end - start) + "ms");
    }

    private static void testSelectionSort(int[] arr) {
        long start = System.currentTimeMillis();
        SelectionSort.selectionSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("选择排序:" + (end - start) + "ms");
    }
}

class Data {
    private static int size = 10000;
    private static Random random = new Random();

    //获取完全随机的数据
    public static int[] getData01() {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100000);
        }
        return arr;
    }

    //获取大致平稳的数据
    public static int[] getData02() {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 10000 + random.nextInt(100);
        }
        return arr;
    }

    //获取大致升序
    public static int[] getData03() {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 1000 + random.nextInt(2000);
        }
        return arr;
    }

    //获取大致降序
    public static int[] getData04() {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr.length - i) * 1000 + random.nextInt(2000);
        }
        return arr;
    }

    public static int[] copy(int[] arr) {
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        return temp;
    }
}