package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Model {
    public static int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("D:\\IdeaProjects\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3410\\res\\levels.txt"));


    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {

        this.gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction))
            return;
        if (checkBoxCollisionAndMoveIfAvaliable(direction))
            return;
        int step = FIELD_CELL_SIZE;
        switch (direction) {
            case LEFT:
                player.move(-step, 0);
                break;
            case RIGHT:
                player.move(step, 0);
                break;
            case UP:
                player.move(0, -step);
                break;
            case DOWN:
                player.move(0, step);
                break;
        }
        checkCompletion();
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {
        Player player = gameObjects.getPlayer();
        GameObject barrier = null;
        for (GameObject gameObject : gameObjects.getAll()) {
            if (!(gameObject instanceof Player) && !(gameObject instanceof Home) && player.isCollision(gameObject, direction))
                barrier = gameObject;
        }
        if (barrier == null) return false;
        if (barrier instanceof Box) {
            Box box = (Box) barrier;
            if (checkWallCollision(box, direction)) return true;
            for (Box b : gameObjects.getBoxes()) {
                if (box.isCollision(b, direction)) return true;
            }
            switch (direction) {
                case LEFT:
                    box.move(-FIELD_CELL_SIZE, 0);
                    break;
                case RIGHT:
                    box.move(FIELD_CELL_SIZE, 0);
                    break;
                case UP:
                    box.move(0, -FIELD_CELL_SIZE);
                    break;
                case DOWN:
                    box.move(0, FIELD_CELL_SIZE);
            }
        }
        return false;
    }

    public void checkCompletion() {
        Set<Home> homes = this.getGameObjects().getHomes();
        Set<Box> boxes = this.getGameObjects().getBoxes();
        int count = 0;
        for (Home home : homes){
            for (Box box : boxes){
                if (home.getX() == box.getX() && home.getY() == box.getY())
                    count++;
            }
        }
        if (count == homes.size())
            eventListener.levelCompleted(currentLevel);

    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        Set<Wall> walls = this.getGameObjects().getWalls();
        for (Wall wall : walls) {
            if (gameObject.isCollision(wall, direction))
                return true;
        }
        return false;
    }
}
