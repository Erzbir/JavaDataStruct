package com.erzbir.dataStruct.MutableLinkedList;

import java.util.ArrayList;

public class JosephusLoop {

    public static void main(String[] args) {
        LinkedSinglyCircularList<Integer> list1 = new LinkedSinglyCircularList<>();
        for (int i = 1; i <= 41; i++) {
            list1.add(i);
        }
        // 从底层结构角度而言, 操作节点实现
        list1.josephusLoop();
        System.out.println(list1);

        // 从位置角度而言 操作角标来实现 从位置而言可以用链表和顺序表实现
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int i = 1; i <= 41; i++) {
            list2.add(i);
        }
        int i = 0;
        while (list2.size() != 2) {
            i = (i + 2) % list2.size();
            list2.remove(i);
        }
        System.out.println(list2);
    }
}
