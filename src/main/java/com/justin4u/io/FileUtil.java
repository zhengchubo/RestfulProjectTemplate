package com.justin4u.io;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileUtil {

    public static void main(String[] args) {
        FileUtil instance = new FileUtil();
        File file = new File("/tmp/");
        instance.walkDirectory(0, file);
        Map<String, String> map = new HashMap<>(30);
    }

    public void walkDirectory(int level, File file) {
        for (int i = 0; i < level; i++) {
            System.out.print("-");
            if (i == level - 1) {
                System.out.print(" ");
            }
        }
        System.out.println(file.getName());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                walkDirectory(level + 2, f);
            }
        }
    }
}
