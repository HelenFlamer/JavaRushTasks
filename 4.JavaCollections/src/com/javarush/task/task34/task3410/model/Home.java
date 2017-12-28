package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y);
        this.setHeight(2);
        this.setWidth(2);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.green);
        graphics.drawOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());

    }
}
