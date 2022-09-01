package 分治与回溯;

/**
 * @Author: Erzbir
 * @Date: 2022/8/30 15:51
 */
//插值查找+迭代+递归
public class InterpolationSearch {
    public static void main(String[] args) {
        int[] arr = {1, 5, 12, 21, 30, 32, 55, 68, 80, 89, 90, 99};
        int key = 30;
        int index = interpolationSearch(arr, key);
        System.out.println(index);
        index = interpolationSearchRecursion(arr, 0, arr.length - 1, key);
        System.out.println(index);
    }

    private static int interpolationSearchRecursion(int[] arr, int low, int high, int key) {
        if (high < low) {
            return -1;
        }
        int mid = low + (int) (1.0 * (key - arr[low]) / (arr[high] - arr[low]) * (high - low));
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

    private static int interpolationSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (true) {
            int mid = low + (int) (1.0 * (key - arr[low]) / (arr[high] - arr[low]) * (high - low));
            if (mid > high || mid < low) {
                return -1;
            }
            if (arr[mid] < key) {
                low = mid + 1;
            } else if (key < arr[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
            if (high < low) {
                return -1;
            }
        }
    }
}