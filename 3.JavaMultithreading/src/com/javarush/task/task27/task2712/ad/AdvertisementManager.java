package com.javarush.task.task27.task2712.ad;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by elena.slinkova on 04.07.2017.

 Этот набор должен удовлетворять следующим требованиям:
 1. сумма денег, полученная от показов, должна быть максимальной из всех возможных вариантов
 2. общее время показа рекламных роликов НЕ должно превышать время приготовления блюд для текущего заказа;
 3. для одного заказа любой видео-ролик показывается не более одного раза;
 4. если существует несколько вариантов набора видео-роликов с одинаковой суммой денег, полученной от показов, то:
 4.1. выбрать тот вариант, у которого суммарное время максимальное;
 4.2. если суммарное время у этих вариантов одинаковое, то выбрать вариант с минимальным количеством роликов;
 5. количество показов у любого рекламного ролика из набора — положительное число.

 При локальном тестировании учитывайте, что необходимо отобразить все рекламные ролики, отобранные для показа,
 в порядке уменьшения стоимости показа одного рекламного ролика в копейках.

 Также не забудь реализовать п.2.4 из предыдущего задания (вывести на экран все подходящие ролики).

 Для каждого показанного видео-ролика должен быть вызван метод revalidate().

 */
public class AdvertisementManager {

    public void processVideos() throws NoVideoAvailableException {
    if (storage.list().size() == 0)
        throw new NoVideoAvailableException();
    else {
    }

}
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private List<Advertisement> videoToShow = new ArrayList<>();

    private int totalAdvertismentTime = 0;


    private int timeSeconds;
    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }


}
