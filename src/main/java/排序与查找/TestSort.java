package 排序与查找;

import java.util.Random;

/**
 * @Author: Erzbir
 * @Date: 2022/9/1 10:11
 */
/*
升序排列
排序的时间 除了和算法自身的策略有关之外 还和数据的分布情况有关
(1)完全随机 冒泡>=选择>插入>希尔>=归并>=堆排≈单路快排>=双路快排 >= 三路快排
(2)趋于平稳 冒泡>选择>插入>单路快排>希尔>=归并>=堆排>=双路快排 >= 三路快排
(3)大致升序 选择>冒泡>堆排>=归并>=单路快排>=双路快排≈希尔>=插入 >= 三路快排
(4)大致降序 选择>冒泡>插入>归并>=单路快排≈堆排>=希尔>=双路快排 >= 三路快排
*/
public class TestSort {
    public static void main(String[] args) {
        int[] arr = Data.getData04();
        int[] arr1 = Data.copy(arr);
        int[] arr2 = Data.copy(arr);
        int[] arr3 = Data.copy(arr);
        int[] arr4 = Data.copy(arr);
        int[] arr5 = Data.copy(arr);
        int[] arr6 = Data.copy(arr);
        int[] arr7 = Data.copy(arr);
        int[] arr8 = Data.copy(arr);
        int[] arr9 = Data.copy(arr);
        int[] arr10 = Data.copy(arr);
        int[] arr11 = Data.copy(arr);
        int[] arr12 = Data.copy(arr);

        testSelectionSort(arr1);
        testBubbleSort(arr2);
        testInsertionSort(arr3);
        testShellSort(arr4);
        testMergeSort(arr5);
        testHeapSort(arr6);
        testQuickSortOneWay(arr7);
        testQuickSortTwoWay(arr8);
        testQuickSortThreeWay(arr9);
        testCountingSort(arr10);
        testRadixSort(arr11);
        testBucketSort(arr12);
    }

    private static void testBucketSort(int[] arr) {
        long start = System.currentTimeMillis();
        BucketSort.bucketSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("高并发桶排序:" + (end - start) + "ms");
    }

    private static void testRadixSort(int[] arr) {
        long start = System.currentTimeMillis();
        RadixSort.radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("基数排序:" + (end - start) + "ms");
    }

    private static void testCountingSort(int[] arr) {
        long start = System.currentTimeMillis();
        CountingSort.countingSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("计数排序:" + (end - start) + "ms");
    }

    private static void testQuickSortThreeWay(int[] arr) {
        long start = System.currentTimeMillis();
        QuickSortThreeWay.quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("三路快排:" + (end - start) + "ms");
    }

    private static void testQuickSortTwoWay(int[] arr) {
        long start = System.currentTimeMillis();
        QuickSortTwoWay.quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("双路快排:" + (end - start) + "ms");
    }

    private static void testQuickSortOneWay(int[] arr) {
        long start = System.currentTimeMillis();
        QuickSortOneWay.quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("单路快排:" + (end - start) + "ms");
    }

    private static void testHeapSort(int[] arr) {
        long start = System.currentTimeMillis();
        HeapSort.heapSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("堆排序:" + (end - start) + "ms");
    }

    private static void testMergeSort(int[] arr) {
        long start = System.currentTimeMillis();
        MergeSort.mergeSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("归并排序:" + (end - start) + "ms");
    }

    private static void testShellSort(int[] arr) {
        long start = System.currentTimeMillis();
        ShellSort.shellSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("希尔排序:" + (end - start) + "ms");
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
            arr[i] = i * 10000 + random.nextInt(2000);
        }
        return arr;
    }

    //获取大致降序
    public static int[] getData04() {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr.length - i) * 10000 + random.nextInt(2000);
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