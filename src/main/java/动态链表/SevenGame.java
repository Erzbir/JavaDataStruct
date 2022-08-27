package 动态链表;

import java.util.ArrayList;
import java.util.Scanner;

public class SevenGame {
    /*
    几个人?
    谁先?
    先说什么?
     */

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入玩家个数:");
        int players = scan.nextInt();
        System.out.print("请输入哪个玩家开始:");
        int index = scan.nextInt() - 1;
        System.out.print("请输入开始的数字:");
        int num = scan.nextInt();
        ArrayList<ArrayList<String>> lists = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            lists.add(new ArrayList<>());
        }
        for (int k = num; k <= 50; k++) {
            lists.get(index++ % players).add(getAnswer(k));
        }
        for (int i = 0; i < lists.size(); i++) {
            System.out.println("第" + (i + 1) + "个玩家:");
            System.out.println(lists.get(i));
        }
    }

    private static String getAnswer(int k) {
        if (k % 7 == 0) {
            return "pass";
        }
        if ((k + "").contains("7")) {
            return "pass";
        } else {
            return k + "";
        }
    }
}
