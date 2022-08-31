package 分治与回溯;

/**
 * @Author: Erzbir
 * @Date: 2022/8/30 11:11
 */
public class NQueen {
    private static int count = 0; // 解的个数
    private static int SIZE = 4;
    private static int[][] arr = new int[SIZE][SIZE];

    public static void main(String[] args) {
        nQueen(0);
    }

    private static void nQueen(int row) {
        if (row == SIZE) {
            count++;
            System.out.println("第" + count + "种解");
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.print(arr[i][j] + " ");
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
}
