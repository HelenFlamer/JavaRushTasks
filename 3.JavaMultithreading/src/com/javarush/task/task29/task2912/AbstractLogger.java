package com.javarush.task.task29.task2912;

/**
 * Created by elena.slinkova on 09.06.2017.
 */
public abstract class AbstractLogger implements Logger{
    int level;
    Logger next;

    public void inform(String message, int level) {
        if (this.level <= level) {
            info(message);
        }
        if (next != null) {
            next.inform(message, level);
        }
    }

    public void setNext(Logger next) {
        this.next = next;
    }
}
