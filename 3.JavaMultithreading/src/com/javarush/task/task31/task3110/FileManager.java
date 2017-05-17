package com.javarush.task.task31.task3110;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by elena.slinkova on 17.05.2017.
 * 1. Создай класс FileManager с конструктором FileManager(Path rootPath) throws IOException
 2. Объяви и проинициализируй приватные переменные класса:
 2.1. Path rootPath – корневой путь директории, файлы которой нас интересуют
 2.2. List<Path> fileList – список относительных путей файлов внутри rootPath
 3. Создай геттер для fileList
 4. Реализуй метод void collectFileList(Path path) throws IOException, который должен:
 4.1. Проверить, если переданный путь path является обычным файлом (используй метод Files.isRegularFile), то получить его относительный путь относительно rootPath и добавить его в список fileList.
 4.2. Если переданный путь path, является директорией (узнать это поможет метод Files.isDirectory), то пройтись по всему содержимому директории и вызвать collectFileList(Path path), передав в path обнаруженные элементы.
 Пройтись по всему содержимому директории можно предварительно получив DirectoryStream с помощью метода newDirectoryStream класса Files. Не забудь закрыть созданный DirectoryStream.
 5. Добавь вызов метода collectFileList(rootPath) в конструкторе FileManager.
 6. Примени все свои знания об инкапсуляции к этому классу.
 Выполняя это задание, ты написал алгоритм, который обходит дерево файлов. Но в Java есть специальный интерфейс FileVisitor для этих целей. Очень рекомендую разобраться как им пользоваться.


 */
public class FileManager {
    private Path rootPath;
    private List<Path> fileList;
    public FileManager(Path rootPath) throws IOException {
        this.rootPath = rootPath;
    }
}
