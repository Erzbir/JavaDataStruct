package com.erzbir.分治与回溯;

import java.util.Scanner;

/**
 * @Author: Erzbir
 * @Date: 2022/8/29 14:10
 */
public class ChessBoardCoverage {
    private static final int BOARD_SIZE = 8; // 棋盘大小
    private static final int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    private static int title = 0; // 定义骨牌编号

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("输入特殊方格的位置:");
        int dr = scan.nextInt(); // 横座标
        int dc = scan.nextInt(); // 纵座
        chessBoard(0, 0, dr, dc, BOARD_SIZE);
        for (int[] i : board) {
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }

    // tc-tc 定位某一个区域的座标->左上角座标
    // dr-dc 特殊方格所在的座标
    // size 当前要处理的棋盘区域的大小
    private static void chessBoard(int tr, int tc, int dr, int dc, int size) {
        // 大小是1则不再切割
        if (size == 1) {
            return;
        }
        int num = ++title; // 当前层级中 三个区域需要被填充的数字
        int s = size / 2; // 被切割后每个区域的边长
        // 开始切割区域 四个
        // 左上角
        if (dr < tr + s && dc < tc + s) {
            chessBoard(tr, tc, dr, dc, s);
        } else {
            // 区域内不包含特殊方格 在右下角添加
            board[tr + s - 1][tc + s - 1] = num;
            chessBoard(tr, tc, tr + s - 1, tc + s - 1, s);
        }
        // 右上角
        if (dr < tr + s && dc >= tc + s) {
            chessBoard(tr, tc + s, dr, dc, s);
        } else {
            // 区域不包含, 左下角添加
            board[tr + s - 1][tc + s] = num;
            chessBoard(tr, tc + s, tr + s - 1, tc + s, s);
        }
        // 左下角
        if (dr >= tr + s && dc < tc + s) {
            chessBoard(tr + s, tc, dr, dc, s);
        } else {
            // 区域不包含, 在右上角添加
            board[tr + s][tc + s - 1] = num;
            chessBoard(tr + s, tc, tr + s, tc + s - 1, s);
        }
        // 右下角
        if (dr >= tr + s && dc >= tc + s) {
            chessBoard(tr + s, tc + s, dr, dc, s);
        } else {
            // 区域不包含, 在左上角添加
            board[tr + s][tc + s] = num;
            chessBoard(tr + s, tc + s, tr + s, tc + s, s);
        }
    }

}
