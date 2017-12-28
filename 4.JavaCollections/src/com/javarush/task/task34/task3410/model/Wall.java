package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.CYAN);
        int newX = getX() - getWidth() / 2;
        int newY = getY() - getHeight() / 2;
        graphics.drawRect(newX, newY, this.getWidth(), this.getHeight());

    }
}
