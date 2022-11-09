package com.erzbir.dataStruct.DivideAndConquerBacktracking;

import com.erzbir.dataStruct.MutableLinkedList.LinkedList;

/**
 * @Author: Erzbir
 * @Date: 2022/8/30 09:35
 */
public class Maze {
    // 1为障碍
    private static final int[][] maze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 0, 0, 0, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 0, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    // 出入口
    private static final int enterX = 1;
    private static final int enterY = 0;
    private static final int exitX = 7;
    private static final int exitY = 8;
    // 方向数据: 上右下左
    private static final int[][] direction = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };
    // 路径状态, true为走过
    private static final boolean[][] visited = new boolean[9][9];
    //存储路径
    private static final LinkedList<String> stack = new LinkedList<>();

    public static void main(String[] args) {
        if (go(enterX, enterY)) {
            System.out.println("找到一条路径");
            for (int[] ints : maze) {
                for (int j = 0; j < maze[0].length; j++) {
                    if (ints[j] == 1) {
                        System.out.print("#\t");
                    } else {
                        System.out.print(".\t");
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("没找到路径");
        }
    }

    private static boolean go(int x, int y) {

        stack.push("(" + x + ", " + y + ")");
        visited[x][y] = true;
        if (x == enterX && y == enterY) {
            return true;
        }
        // 遍历四个方向
        for (int[] ints : direction) {
            int newX = ints[0] + x;
            int newY = ints[1] + y;
            if (isInArea(newX, newY) && maze[newX][newY] != 0 && !visited[newX][newY]) {
                if (go(newX, newY)) {
                    return true;
                }
            }
        }
        stack.pop();
        return false;
    }

    private static boolean isInArea(int x, int y) {
        return x >= 0 && x <= 8 && y >= 0 && y <= 8;
    }
}
