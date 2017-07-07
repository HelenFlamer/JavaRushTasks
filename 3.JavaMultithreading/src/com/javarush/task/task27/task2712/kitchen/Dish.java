package com.javarush.task.task27.task2712.kitchen;

/**
 * Created by elena.slinkova on 09.06.2017.
 */
public enum  Dish {
    Fish(25), Steak(30), Soup(15), Juice(5), Water(3);

    Dish(int duration) {
        this.duration = duration;
    }

    private int duration;

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        String str = "";
        for (Dish dish : Dish.values())
            str = str + dish.name() + ", ";
        return str.substring(0, str.length() - 2);
    }

}