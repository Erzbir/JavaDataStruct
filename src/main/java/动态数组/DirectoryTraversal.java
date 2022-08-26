package 动态数组;

import java.io.File;

public class DirectoryTraversal {
    public static void main(String[] args) {
        String path = "/Users/erzbir/Desktop/IntelliJ Java/JavaDataStruct";
        File dir = new File(path);
        ArrayQueue<File> queue = new ArrayQueue<>();
        queue.offer(dir);
        while (!queue.isEmpty()) {
            File curDir = queue.poll();
            File[] files = curDir.listFiles();

        }
    }
}
