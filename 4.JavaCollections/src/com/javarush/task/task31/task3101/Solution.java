package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

/*
Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый — path — путь к директории, второй — resultFileAbsolutePath — имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его (используй метод FileUtils.deleteFile).
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. Отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке.
2.2.2. Переименовать resultFileAbsolutePath в ‘allFilesContent.txt‘ (используй метод FileUtils.renameFile).
2.2.3. В allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять «n«.
Все файлы имеют расширение txt.
*/
public class Solution {
    public static void main(String[] args) {
        File source = new File(args[1]);
        File destination = new File(source.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(source, destination);      //переименовываем файл
        FileOutputStream fos = null;   //создает поток
        FileInputStream fis = null;
        try {
            fos = new FileOutputStream(destination);

            TreeSet<File> list = new TreeSet<>();    //упорядоченный список для сортировки
            Stack<File> stack = new Stack<>();       //заполняем список
            stack.push(new File(args[0]));
            while (!stack.isEmpty()) {
                File child = stack.pop();
                if (child.isDirectory()) {
                    for (File f : child.listFiles()) stack.push(f);
                } else if (child.isFile()) {
                    long fileSize = Files.size(child.toPath());
                    if (fileSize > 50) {
                        FileUtils.deleteFile(new File(child.getPath()));
                    } else {
                        list.add(new File(child.getPath()));
                    }
                }
            }
            for (File f : list) {
                fis = new FileInputStream(f);
                byte[] bytes = new byte[fis.available()];
                fis.read(bytes);
                fos.write(bytes);
                fos.write("\n".getBytes());
            }
            fos.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                fos.close();
                fis.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }
    }


    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}
