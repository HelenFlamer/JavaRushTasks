package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by elena.slinkova on 09.06.2017.
 4. Перепиши метод toString в классе Order. Пусть он возвращает пустую строку, если нет блюд в заказе,
 иначе вывод должен быть аналогичным примеру в порядке добавления блюд. Используй ConsoleHelper.
 */
public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString() {
        return !dishes.isEmpty() ? String.format("Your order: %s of %s", dishes, tablet) : "";
    }

    public int getTotalCookingTime(){
        int totalTime = 0;
        for (Dish dish : dishes){
            totalTime += dish.getDuration();
        }
        return totalTime;
    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }
}
