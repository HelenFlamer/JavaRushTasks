package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import javafx.beans.InvalidationListener;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by elena.slinkova on 09.06.2017.
 * 5. У нас все завязано на работу с консолью. Однако, при возникновении исключений, наше приложение умрет.
 * Чтобы узнать причину — добавим в Tablet статическое поле logger типа java.util.logging.Logger, инициализированное именем класса (Logger.getLogger(Tablet.class.getName())).
 * <p>
 * 6. В методе createOrder класса Tablet обработаем исключения ввода-вывода.
 * Запишем в лог «Console is unavailable.«. Уровень лога — SEVERE — это самый серьезный уровень, мы не можем работать.
 * Также в методе createOrder класса Tablet должен быть создан новый заказ.
 * <p>
 * 7. Надо начинать тестировать наше приложение.
 * Добавьте в main создание планшета и создание заказа — new Tablet(5).createOrder();
 * <p>
 * 4. Не забудь сразу после создания заказа и вывода информации о нем в консоль (найдите это место в коде) сделать следующее:
 * 4.1. Установить флаг setChanged()
 * 4.2. Отправить обсерверу заказ — notifyObservers(order);
 * <p>
 * 5. Также внесем небольшое изменение. Сделай так чтобы метод createOrder возвращал текущий заказ или null, если заказ создать не удалось.
 */
public class Tablet extends Observable {
    final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("Tablet{number=%d}", number);
    }

    public Order createOrder() {
        Order order = null;
        try {
            order = new Order(this);
            if (!order.isEmpty()) {
                ConsoleHelper.writeMessage(order.toString());
                setChanged();
                notifyObservers(order);
                new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return null;
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
        return order;

    }
}
