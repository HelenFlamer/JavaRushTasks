package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by elena.slinkova on 03.07.2017.
 */
public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void update(Observable o, Object arg) {

        ConsoleHelper.writeMessage(String.format("Start cooking - %s, cooking time %dmin", arg, ((Order) arg).getTotalCookingTime()));
        setChanged();
        notifyObservers(arg);
    }
}
