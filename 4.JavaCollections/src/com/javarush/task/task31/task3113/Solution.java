package com.javarush.task.task31.task3113;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Что внутри папки?
Первым делом считай путь к папке с консоли.
Если введенный путь не является директорией — выведи «[полный путь] — не папка» и заверши работу.
Затем посчитай и выведи следующую информацию:

Всего папок — [количество папок в директории]
Всего файлов — [количество файлов в директории и поддиректориях]
Общий размер — [общее количество байт, которое хранится в директории]

Используй только классы и методы из пакета java.nio.

Квадратные скобки [ ] выводить на экран не нужно.
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        int fileNum = 0;
        int dirNum = 0;
        long size = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = reader.readLine();
        Path filePath = Paths.get(path);

        if (!Files.isDirectory(filePath)) {
            System.out.println(path + " - не папка");
            reader.close();
            System.exit(1);
        }
        for (File file : new File(path).listFiles()){
            if (file.isFile()){
                fileNum++;
                size+=file.length();
            }
            else if (file.isDirectory()) {
                dirNum++;
            }
        }

        System.out.println("Всего файлов - " + fileNum);
        System.out.println("Всего папок - " + dirNum);
        System.out.println("Общий размер - " + size);

    }
}
