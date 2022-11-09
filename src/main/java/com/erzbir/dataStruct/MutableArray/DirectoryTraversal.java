package com.erzbir.dataStruct.MutableArray;

import java.io.File;

public class DirectoryTraversal {
    public static void main(String[] args) {
        String path = "/Users/erzbir/Desktop/IntelliJ Java/JavaDataStruct";
        File dir = new File(path);
        ArrayDeque<File> queue = new ArrayDeque<>();
        queue.offer(dir);
        while (!queue.isEmpty()) {
            File curDir = queue.poll();
            System.out.println("[" + curDir.getName() + "]");
            File[] files = curDir.listFiles();
            if (files != null && files.length != 0) {
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println(file.getName());
                    } else {
                        queue.offer(file);
                    }
                }
            }
        }
    }
}
