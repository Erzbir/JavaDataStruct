package 排序与查找;

import java.util.Arrays;

/**
 * @Author: Erzbir
 * @Date: 2022/9/1 16:01
 */
//堆排序
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {9, 2, 8, 3, 6, 4, 5, 10, 7, 1};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    private static int len = 0;
    public static void heapSort(int[] arr) {
        len = arr.length;
        //先把数组按照全长度进行堆化处理
        heapify(arr);
        //把每一次len区间中的最大值和当前区间的最后一个元素进行交换
        //区间len-1
        //重新对len-1的区间进行堆化处理
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            len--;
            siftDown(arr,0);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void heapify(int[] arr) {
        //从后往前 判断每一个元素是否需要下沉
        for (int i = len - 1; i >= 0; i--) {
            siftDown(arr, i);
        }
    }

    private static void siftDown(int[] arr, int k) {
        while (2 * k + 1 < len) {    //有左孩子 才可能往下移动
            //找左右孩子谁大
            int j = 2 * k + 1;
            if (j + 1 < len && arr[j] < arr[j + 1]) {
                j = j + 1;
            }
            //父节点小于孩子结点 交换位置 父节点k下移到j 继续循环
            if (arr[k] < arr[j]) {
                int temp = arr[k];
                arr[k] = arr[j];
                arr[j] = temp;
                k = j;
            } else {
                //如果父节点k比两个孩子都大 则不下沉
                break;
            }
        }
    }
}
