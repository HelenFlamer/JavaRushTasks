package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable{
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        int newX = getX() - getWidth() / 2;
        int newY = getY() - getHeight() / 2;
        graphics.drawOval(newX, newY, this.getWidth(), this.getHeight());
        graphics.fillOval(newX, newY, this.getWidth(), this.getHeight());
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }
}
