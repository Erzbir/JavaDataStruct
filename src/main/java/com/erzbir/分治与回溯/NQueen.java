package com.erzbir.分治与回溯;

/**
 * @Author: Erzbir
 * @Date: 2022/8/30 11:11
 */
public class NQueen {
    private static final int SIZE = 4;
    private static final int[][] arr = new int[SIZE][SIZE];
    private static int count = 0; // 解的个数

    public static void main(String[] args) {
        nQueen(0);
    }

    private static void nQueen(int row) {
        if (row == SIZE) {
            count++;
            System.out.println("第" + count + "种解");
            for (int[] ints : arr) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.print(ints[j] + " ");
                }
                System.out.println();
            }
        } else {
            // 遍历当前列
            for (int col = 0; col < SIZE; col++) {
                if (noDangerous(row, col)) {
                    arr[row][col] = 1;
                    nQueen(row + 1);
                }
                // 失败则清除
                arr[row][col] = 0;
            }
        }
    }

    // 判断上 左上 右上
    private static boolean noDangerous(int row, int col) {
        // 正上
        for (int r = row - 1; r >= 0; r--) {
            if (arr[r][col] == 1) {
                return false;
            }
        }
        //左上方
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            if (arr[r][c] == 1) {
                return false;
            }
        }
        //右上
        for (int r = row - 1, c = col + 1; r >= 0 && c < SIZE; r--, c++) {
            if (arr[r][c] == 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Author: Erzbir
     * @Date: 2022/8/30 16:05
     */
    //对比二分查找与插值查找
    public static class TestBinaryAndInterpolation {
        private static int bCount = 0;
        private static int iCount = 0;

        public static void main(String[] args) {
            //分布很均匀
            //        int[] arr = new int[10];
            //        for (int i = 0; i < arr.length; i++) {
            //            arr[i] = 2 * i;
            //        }

            //极度分布不均
            int[] arr = {1, 12, 112, 236, 5432, 98765, 100000, 123456, 6666666, 99999999, 101010101};
            int key = 100000;
            int index = binarySearchRecursion(arr, 0, arr.length - 1, key);
            System.out.println(index);
            index = interpolationSearchRecursion(arr, 0, arr.length - 1, key);
            System.out.println(index);
            System.out.println("bCount = " + bCount);
            System.out.println("iCount = " + iCount);
        }

        private static int interpolationSearchRecursion(int[] arr, int low, int high, int key) {
            iCount++;
            if (high < low) {
                return -1;
            }
            int mid = low + (int) (1.0 * (key - arr[low]) / (arr[high] - arr[low]) * (high - low));
            System.out.println("mid = " + mid);
            if (mid > high || mid < low) {
                return -1;
            }
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                return interpolationSearchRecursion(arr, mid + 1, high, key);
            } else {
                return interpolationSearchRecursion(arr, low, mid - 1, key);
            }
        }

        private static int binarySearchRecursion(int[] arr, int low, int high, int key) {
            bCount++;
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
    }
}
