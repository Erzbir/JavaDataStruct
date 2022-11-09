package com.erzbir.dataStruct.SortAndSearch;

/**
 * @Author: Erzbir
 * @Date: 2022/8/30 15:21
 */
//二分查找-迭代+递归
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int key = 12;
        int index = binartSearch(arr, key);
        System.out.println(index);

        index = binarySearchRecursion(arr, 0, arr.length - 1, key);
        System.out.println(index);
    }

    private static int binarySearchRecursion(int[] arr, int low, int high, int key) {
        if (high < low) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == key) {
            return mid;
        }
        if (arr[mid] < key) {
            return binarySearchRecursion(arr, mid + 1, high, key);
        } else {
            return binarySearchRecursion(arr, low, mid - 1, key);
        }
    }

    private static int binartSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int mid = low + (high - low) / 2;
        while (arr[mid] != key) {
            if (key < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
            if (high < low) {
                return -1;
            }
            mid = low + (high - low) / 2;
        }
        return mid;
    }
}