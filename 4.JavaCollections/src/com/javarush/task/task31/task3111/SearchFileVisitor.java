package com.javarush.task.task31.task3111;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 1 условие отключено или выполняется -> файл проходит дальше
 2 условие отключено или выполняется -> файл проходит дальше
 3 условие 0 или меньше -> дальше
 4 условие 0 или больше -> добавили в выборку.
 */

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles = new ArrayList<>();


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        File createdFile = new File(String.valueOf(file));
        String buff = new String(Files.readAllBytes(file));

        if (partOfName == null || createdFile.getName().contains(partOfName)){
            if (partOfContent == null || buff.contains(partOfContent)){
                if (maxSize == 0 || Files.size(file) < maxSize){
                   if (minSize == 0 || Files.size(file) > minSize){
                        foundFiles.add(file);
                    }

                }
            }
        }



        return super.visitFile(file, attrs);
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}
