package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;

import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by elena.slinkova on 16.05.2017.
 6. В самом конце метода main в класса Archiver добавь код, который создает объект типа ExitCommand и вызывает у него метод execute()
 7. Попробуй, как это все работает
 Обрати внимание, что все файлы проекта должны быть в кодировке UTF-8. Кодировку в IntelliJ IDEA можно задать через пункты меню Settings -> Editor -> File Encodings. Проверь, что все три поля отвечающие за кодировку выставлены в UTF-8.


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
        try {
            new ExitCommand().execute();
        } catch (Exception e) {

        }
    }
}
