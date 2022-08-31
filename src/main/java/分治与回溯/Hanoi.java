package 分治与回溯;

/**
 * @Author: Erzbir
 * @Date: 2022/8/29 10:41
 */
public class Hanoi {


    public static void main(String[] args) {
        String start = "X";
        String mid = "Y";
        String end = "Z";
        hanoi(64, start, mid, end);
    }

    private static void hanoi(int level, String start, String mid, String end) {
        if (level == 1) {
            System.out.println(start + "->" + end); // 只剩一个时进行移动, 目标始终是移动到最终的目标位置
        } else {
            hanoi(level - 1, start, end, mid); // 要移动n个需要将n - 1个一移动到中间柱
            System.out.println(start + "->" + end);
            hanoi(level - 1, mid, start, end); // 将中间柱的都移动到目标柱, 中间柱始终都需要移动n - 1个, 因为到这里时已经将最下面的盘子移动到了目标柱
        }
    }
}
