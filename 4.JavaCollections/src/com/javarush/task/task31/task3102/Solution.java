package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<File> fileList = new ArrayList<>();
        List<File> directoryList = new ArrayList<>();

        File rootFile = new File(root);
        File [] files = rootFile.listFiles();
        for (File s : files) {
            if (s.isFile())
                fileList.add(s);
            else if (s.isDirectory())
                directoryList.add(s);
        }

        for (File dir : directoryList) {

        }
        return null;

    }
}
