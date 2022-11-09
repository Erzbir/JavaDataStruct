package com.erzbir.test;

import com.erzbir.dataStruct.Tree.BinarySearchTree;

/**
 * @Author: Erzbir
 * @Date: 2022/9/4 11:43
 */
public class TestBST {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(20);
        bst.add(16);
        bst.add(28);
        bst.add(8);
        bst.add(22);
        bst.add(30);
        bst.add(4);
        bst.add(12);
        bst.add(26);
        bst.add(9);
        bst.preOrder();
        bst.preOrderNR();
        bst.inOrder();
        bst.inOrderNR();
        bst.postOrder();
        bst.levelOrder();
        System.out.println(bst.minimum());
        System.out.println(bst.maximum());
        System.out.println(bst.removeMin());
        System.out.println(bst.removeMax());
        bst.inOrderNR();
        bst.remove(20);
        bst.inOrderNR();
        System.out.println(bst);
        int[] arr = {8, 9, 12, 16, 22, 26, 28};
        BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();
        for (int j : arr) {
            bst2.add(j);
        }
        System.out.println(bst.equals(bst2));
        for (Integer num : bst) {
            System.out.println(num);
        }
    }
}