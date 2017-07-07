package com.javarush.task.task27.task2712;


import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.io.IOException;

/**
 * Created by elena.slinkova on 09.06.2017.
 * 3. Пишем main.
 Для объекта Observable добавляем свой объект Observer. См. п.2 и описание паттерна в wikipedia
 Называем повара, имя не влияет на тесты. В моем варианте — это Amigo : )

 Сверим выводы в консоль. Пример моего вывода:
 Your order: [Soup] of Tablet{number=5}
 Start cooking - Your order: [Soup] of Tablet{number=5}
 */
public class Restaurant {
    public static void main(String[] args) throws IOException {
        Tablet tablet = new Tablet(5);
        Cook cook = new Cook("Gordon Ramsay");
        Waiter waiter = new Waiter();
        tablet.addObserver(cook);
        cook.addObserver(waiter);
        tablet.createOrder();
    }
}
