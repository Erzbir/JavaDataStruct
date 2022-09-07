package com.erzbir.分治与回溯;

import java.io.File;

/**
 * @Author: Erzbir
 * @Date: 2022/8/29 8:53
 */
public class Recursion {
    public static void main(String[] args) {
//        show(); //演示栈内存溢出和堆内存溢出
        //用迭代实现前n项求和
        test01();
        //用递归实现前n项求和
        test02();
        //用迭代实现fibo
        test04();
        //用递归实现fibo
        test03();

        //递归实现文件夹遍历
        File dir = new File("C:\\Users\\HENG\\Desktop\\JavaDSA");
        showDir(dir);
    }

    private static void showDir(File dir) {
        System.out.println("【" + dir.getName() + "】");
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return;
        }
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName());
            } else {
                showDir(file);
            }
        }
    }

    private static void test04() {
        /*
        1 1 2 3 5
            a
              b
                c
        */
        int num = getFibo(42);
        System.out.println(num);
    }

    private static int getFibo(int i) {
        if (i == 1 || i == 2) {
            return 1;
        }
        int[] arr = new int[i];
        arr[0] = 1;
        arr[1] = 1;
        for (int k = 2; k < arr.length; k++) {
            arr[k] = arr[k - 1] + arr[k - 2];
        }
        return arr[arr.length - 1];
/*
        int a = 1;
        int b = 1;
        int c = 0;
        for (int k = 3; k <= i; k++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
*/
    }

    private static void test03() {
        int num = fibo(42);  //O(2^n)
        System.out.println(num);
    }

    private static int fibo(int i) {
        if (i == 1 || i == 2) {
            return 1;
        }
        return fibo(i - 1) + fibo(i - 2);
    }

    private static void test02() {
        int sum = getSum(100);
        System.out.println(sum);
    }

    private static int getSum(int i) {
        if (i == 1) {
            return 1;
        }
        return getSum(i - 1) + i;
    }

    private static void test01() {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

    private static void show() {
        long[] arr = new long[10000000];
        show();
    }
}