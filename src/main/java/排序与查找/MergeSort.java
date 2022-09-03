package 排序与查找;

import java.util.Arrays;

/**
 * @Author: Erzbir
 * @Date: 2022/9/1 14:57
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 6, 78, 1, 9, 3};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int mid = L + (R - L) / 2;
        mergeSort(arr, L, mid);
        mergeSort(arr, mid + 1, R);
        if (arr[mid] > arr[mid + 1]) {
            merge(arr, L, mid, R);
        }
    }

    private static void merge(int[] arr, int L, int mid, int R) {
        int[] aux = new int[R - L + 1];
        for (int i = 0; i < aux.length; i++) {
            aux[i] = arr[i + L];
        }
        int i = 0;
        // 例如L==4,R==7,mid==5,这是一个下标从4开始的区间到7结束  这种情况下需要将mid + 1映射到新建的数组,
        // 而新建的数组的下标从零开始, 所以减去L
        int j = mid + 1 - L;
        for (int k = L; k <= R; k++) {
            if (i > mid - L) {
                arr[k] = aux[j];
                j++;
            } else if (j > R - L) {
                arr[k] = aux[i];
                i++;
            } else if (aux[i] <= aux[j]) {
                arr[k] = aux[i];
                i++;
            } else {
                arr[k] = aux[j];
                j++;
            }
        }
    }
}
