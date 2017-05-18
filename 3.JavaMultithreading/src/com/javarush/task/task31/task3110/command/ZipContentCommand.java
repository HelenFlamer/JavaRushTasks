package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.FileProperties;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.util.List;

/**
 * Created by elena.slinkova on 17.05.2017.
 * 1. Выведи сообщение «Просмотр содержимого архива.»
 2. Создай объект класса ZipFileManager с помощью метода getZipFileManager()
 3. Выведи сообщение «Содержимое архива:»
 4. Получи список файлов архива с помощью метода getFilesList()
 5. Выведи свойства каждого файла в консоль. Тут нам и пригодится ранее реализованный метод toString() класса FileProperties
 6. Выведи сообщение «Содержимое архива прочитано.»
 7. Запусти программу и проверь, что команда «просмотреть содержимое архива» работает


 */
public class ZipContentCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Просмотр содержимого архива.");
        ZipFileManager zipFileManager = getZipFileManager();
        ConsoleHelper.writeMessage("Содержимое архива:");
        List<FileProperties> propertiesList = zipFileManager.getFilesList();
        for (FileProperties properties : propertiesList){
            ConsoleHelper.writeMessage(properties.toString());
        }
        ConsoleHelper.writeMessage("Содержимое архива прочитано.");

    }
}
