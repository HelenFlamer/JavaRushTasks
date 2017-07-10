package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент — полный путь к файлу fileName.
Второй аргумент — путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию ‘new‘.
Если в архиве есть файл с таким именем, то заменить его.

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри, а потом записать в архив все энтри вместе с добавленным файлом.

*/

public class Solution {

    public static Map <ZipEntry, byte[]> entryMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        File zipArchive = new File(args[1]);
        File addedFile = new File(args[0]);

        zipToMap(zipArchive);
        addNewFileToZIP(addedFile, zipArchive);
    }


    public static void zipToMap(File file)  {

        // Записываем содержимое архива в карту
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file))) {

            ZipEntry zipEntry;

            // перебираем все zipEntries
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int count;

                while ((count = zipInputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, count);
                }
                byte[] bytes = byteArrayOutputStream.toByteArray();
                entryMap.put(zipEntry, bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void addNewFileToZIP(File addedFile, File zipArchive) {

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipArchive)); FileInputStream fileInputStream = new FileInputStream(addedFile)) {

            //Маркер наличия добавляемого файла в архиве
            boolean isExist = false;

            //Сохраняем сразу в отдельный zipEntry добавляемый файл для последующего сравнения
            ZipEntry fileNameComparator = new ZipEntry(addedFile.getName());


            //Копируем zipEntry с entryMap в архив
            for (Map.Entry<ZipEntry, byte[]> zipEntry : entryMap.entrySet()) {

                //Подрезаем путь файла для сравненияс добавляемым файлом
                Path path = Paths.get(zipEntry.getKey().getName());

                //Сравниваем...
                //Если имя текущего файла в zipEntry НЕ совпадает с добавлемым файлом
                if(!(path.getFileName().toString().equals(fileNameComparator.getName()))) {

                    //Записываем в архив
                    zipOutputStream.putNextEntry(new ZipEntry(zipEntry.getKey().getName()));
                    zipOutputStream.write(zipEntry.getValue());

                }
                //Если же файл с таким названием присутствует в архиве...
                else {
                    isExist = true;
                }
            }

            //Если в процессе прохождения цикла в архиве нашелся файл с таким-же именем как и добавляемый..
            if (isExist) {
                //Добавляем файл в папку new
                ZipEntry addingFileEntry = new ZipEntry("new/" + addedFile.getName());
                zipOutputStream.putNextEntry(addingFileEntry);

                Files.copy(addedFile.toPath(), zipOutputStream);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}