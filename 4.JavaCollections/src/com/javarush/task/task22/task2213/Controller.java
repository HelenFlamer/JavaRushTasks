package com.javarush.task.task22.task2213;



public class Controller {

    private Figure figure;
    private Field field;

    public Figure getFigure() {
        return figure;
    }

    public Field getField() {
        return field;
    }

    public Controller(Figure figure, Field field) {
        this.figure = figure;
        this.field = field;
    }


}
