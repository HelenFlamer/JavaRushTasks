package com.javarush.task.task27.task2712.kitchen;

/**
 * Created by elena.slinkova on 09.06.2017.
 */
public enum  Dish {
    Fish, Steak, Soup, Juice, Water;

    public static String allDishesToString() {
        String str = "";
        for (Dish dish : Dish.values())
            str = str + dish.name() + ", ";
        return str.substring(0, str.length() - 2);
    }

}