package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;
import com.javarush.task.task31.task3110.exception.WrongZipFileException;
import com.sun.org.apache.regexp.internal.RE;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by elena.slinkova on 16.05.2017.
 * <p>
 * 2. Перепиши метод main():
 * 2.1. Объяви локальную переменную типа Operation
 * 2.2. В цикле запрашивай новое значение для переменной п.2.1.
 * с помощью метода askOperation() и вызывай выполнение операции с помощью CommandExecutor.execute()
 * 2.3. Обеспечь выход из цикла, если пользователь выбрал операцию Operation.EXIT
 * 2.4. Оберни вызов askOperation() и execute(operation) в блок try-catch. Если произойдет исключение WrongZipFileException выведи сообщение
 * «Вы не выбрали файл архива или выбрали неверный файл.» с помощью ConsoleHelper,
 * при любых других исключениях выводи «Произошла ошибка. Проверьте введенные данные.».
 * 2.5. Проследи, чтобы программа продолжила свою работу (перешла на новый шаг цикла), после обработки исключений.
 * 3. Запусти программу и проверь, что команда “выход” работает.
 */
public class Archiver {
    public static Operation askOperation() throws IOException {
        ConsoleHelper.writeMessage("Выберите операцию:\n" +
                "0 - упаковать файлы в архив\n" +
                "1 - добавить файл в архив\n" +
                "2 - удалить файл из архива\n" +
                "3 - распаковать архив\n" +
                "4 - просмотреть содержимое архива\n" +
                "5 – выход");

        int operationSelected = ConsoleHelper.readInt();

        return Operation.values()[operationSelected];
    }

    public static void main(String[] args) {
        Operation operation = null;
        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }

        } while (operation != Operation.EXIT);


       /* System.out.println("Enter the full path to the file (with its name), where to unzip the file");
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

        }*/
    }
}
