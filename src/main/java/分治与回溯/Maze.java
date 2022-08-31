package 分治与回溯;

import 动态链表.LinkedList;

/**
 * @Author: Erzbir
 * @Date: 2022/8/30 09:35
 */
public class Maze {
    // 1为障碍
    private static int[][] maze = {
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
    private static int enterX = 1;
    private static int enterY = 0;
    private static int exitX = 7;
    private static int exitY = 8;
    // 方向数据: 上右下左
    private static int[][] direction = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };
    // 路径状态, true为走过
    private static boolean[][] visited = new boolean[9][9];
    //存储路径
    private static LinkedList<String> stack = new LinkedList<>();

    public static void main(String[] args) {
        if (go(enterX, enterY)) {
            System.out.println("找到一条路径");
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[0].length; j++) {
                    if (maze[i][j] == 1) {
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
        for (int i = 0; i < direction.length; i++) {
            int newX = direction[i][0] + x;
            int newY = direction[i][1] + y;
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
