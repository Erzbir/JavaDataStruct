package com.erzbir.分治与回溯;

import java.util.Scanner;

/**
 * @Author: Erzbir
 * @Date: 2022/8/30 14:12
 */
public class Sudoku {
    /*
005300000
800000020
070010500
400005300
010070006
003200080
060500009
004000030
000009700
*/

    private static int[][] board = new int[9][9];

    public static void main(String[] args) {
        System.out.println("请输入数独的数据：");
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            //"005300000"
            String line = input.nextLine();
            for (int j = 0; j < line.length(); j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }
        solve(0, 0);
    }

    private static void solve(int row, int col) {
        if (row == 9) {
            printBoard();
            System.out.println("======================");
        } else {
            if (board[row][col] == 0) {
                for (int num = 1; num <= 9; num++) {
                    if (!isExist(row, col, num)) {
                        board[row][col] = num;
                        solve(row + (col + 1) / 9, (col + 1) % 9);
                    }
                    board[row][col] = 0;//为了避免和上一层的数据冲突 清空
                }
            } else {
                solve(row + (col + 1) / 9, (col + 1) % 9);
            }
        }
    }

    private static boolean isExist(int row, int col, int num) {
        //同行
        for (int c = 0; c < 9; c++) {
            if (board[row][c] == num) {
                return true;
            }
        }

        //同列
        for (int r = 0; r < 9; r++) {
            if (board[r][col] == num) {
                return true;
            }
        }

        //同3×3
        int rowMin = 0;
        int colMin = 0;
        int rowMax = 0;
        int colMax = 0;
        if (row <= 2) {
            rowMin = 0;
            rowMax = 2;
        }
        if (row >= 3 && row <= 5) {
            rowMin = 3;
            rowMax = 5;
        }
        if (row >= 6 && row <= 8) {
            rowMin = 6;
            rowMax = 8;
        }

        if (col <= 2) {
            colMax = 2;
        }
        if (col >= 3 && col <= 5) {
            colMin = 3;
            colMax = 5;
        }
        if (col >= 6 && col <= 8) {
            colMin = 6;
            colMax = 8;
        }

        for (int i = rowMin; i <= rowMax; i++) {
            for (int j = colMin; j <= colMax; j++) {
                if (board[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}