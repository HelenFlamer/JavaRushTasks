package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elena.slinkova on 04.07.2017.
 * Опишем его.
 1. Видео должно где-то храниться, пусть это будет список.
 Создадим поле videos и инициализируем его пустым листом.
 Подумай, должно ли поле videos иметь возможность менять свое значение?

 2. Чтобы как-то работать с видео, создай публичные методы:
 2.1. list() — который вернет список всех существующих доступных видео.
 2.2. add(Advertisement advertisement) — который добавит новое видео в список videos.

 3. В конструкторе класса добавим в список videos какие-то данные. У меня это:
 Object someContent = new Object();
 new Advertisement(someContent, «First Video», 5000, 100, 3 * 60) // 3 min
 new Advertisement(someContent, «Second Video», 100, 10, 15 * 60) //15 min
 new Advertisement(someContent, «Third Video», 400, 2, 10 * 60) //10 min
 */
public class AdvertisementStorage {
    private static AdvertisementStorage instance = new AdvertisementStorage();

    private AdvertisementStorage(){
        Object someContent = new Object();
        new Advertisement(someContent, "First Video", 5000, 100, 3 * 60); // 3 min
        new Advertisement(someContent, "Second Video", 100, 10, 15 * 60); //15 min
        new Advertisement(someContent, "Third Video", 400, 2, 10 * 60); //10 min
    }

    public static AdvertisementStorage getInstance(){
        if (instance == null)
            instance = new AdvertisementStorage();
        return instance;
    }

    private final List<Object> videos = new ArrayList();

    public List<Object> list(){
        return videos;
    }

    public void add(Advertisement advertisement){
        videos.add(advertisement);
    }
}
