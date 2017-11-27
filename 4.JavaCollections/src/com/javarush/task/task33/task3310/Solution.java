package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Solution {
    public static void main(String[] args){
        StorageStrategy strategy = new HashMapStorageStrategy();
        testStrategy(strategy, 1000);
        StorageStrategy strategy1 = new FileStorageStrategy();
        testStrategy(strategy1, 1000);
        StorageStrategy strategy2 = new OurHashMapStorageStrategy();
        testStrategy(strategy2, 1000);
        StorageStrategy strategy3 = new OurHashBiMapStorageStrategy();
        testStrategy(strategy3, 1000);
        StorageStrategy strategy4 = new HashBiMapStorageStrategy();
        testStrategy(strategy4, 1000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> resultSet = new HashSet<>();

        for (String s : strings) {
            resultSet.add(shortener.getId(s));
        }
        return resultSet;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> resultSet = new HashSet<>();

        for (Long l : keys) {
            resultSet.add(shortener.getString(l));
        }
        return resultSet;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());
        //Генерировать тестовое множество строк, используя Helper и заданное количество элементов elementsNumber
        Set<String> testSetStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testSetStrings.add(Helper.generateRandomString());
        }
        //Создавать объект типа Shortener, используя переданную стратегию
        Shortener shortener = new Shortener(strategy);

        Set<Long> idsSet;

        Date startTime1 = new Date();
        idsSet = getIds(shortener, testSetStrings);
        Date finishTime1 = new Date();

        long msDelay1 = finishTime1.getTime() - startTime1.getTime();
        Helper.printMessage(Long.toString(msDelay1));

        Set<String> stringSet;
        Date startTime2 = new Date();
        stringSet = getStrings(shortener, idsSet);
        Date finishTime2 = new Date();

        long msDelay2 = finishTime2.getTime() - startTime2.getTime();
        Helper.printMessage(Long.toString(msDelay2));


        if (testSetStrings.equals(stringSet)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
