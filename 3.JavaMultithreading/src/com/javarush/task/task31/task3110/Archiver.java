package com.javarush.task.task31.task3110;

import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by elena.slinkova on 16.05.2017.
 * 5. Создай класс Archiver и добавь в него метод main.
 6. В методе main:
 6.1 Запроси пользователя ввести полный путь архива с клавиатуры. Не забудь, что имя тоже входит в состав полного пути.
 6.2 Создай объект класса ZipFileManager, передав в него имя файла архива. Разберись, как из String получить Path.

 Подсказка: изучи метод get() класса Paths.

 6.3 Запроси пользователя ввести путь к файлу, который будем архивировать. Не путай это с файлом архива, который мы уже ввели.
 На этот раз нам нужен файл, который мы будем сжимать, а не в котором хранить сжатые данные.
 6.4 Вызови метод createZip у объекта ZipFileManager, передав в него путь для архивации.

 */
public class Archiver {
    public static void main(String[] args){
        System.out.println("Enter the full path to the file (with its name), where to unzip the file");
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        ZipFileManager archivePath = new ZipFileManager(Paths.get(scanner.nextLine()));
        System.out.println("Enter the full path to the file (with its name) you want to archive");
        try {
            archivePath.createZip(Paths.get(scanner.nextLine()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
