package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevelLoader {

    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        int currentLevel = level % 60 == 0 ? 60 : level % 60;
        String levelInFile = "Maze: " + currentLevel;
        List<String> levelInfo = new ArrayList<>();
        List<String> gameField = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()));

            String line = "";
            while (!line.equals(levelInFile)) {
                line = reader.readLine();
            }
            for (int i = 0; i < 6; i++)
                levelInfo.add(reader.readLine());

            line = reader.readLine();

            while (!"".equals(line)) {
                gameField.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Set<Box> boxes = new HashSet<>();
        Set<Wall> walls = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        for (int i = 0; i < gameField.size(); i++) {
            String wight = gameField.get(i);
            for (int j = 0; j < wight.length(); j++) {
                int x = (j == 0) ? Model.FIELD_CELL_SIZE / 2 : (Model.FIELD_CELL_SIZE / 2) + j * Model.FIELD_CELL_SIZE;
                int y = (i == 0) ? Model.FIELD_CELL_SIZE / 2 : (Model.FIELD_CELL_SIZE / 2) + i * Model.FIELD_CELL_SIZE;
                char symbol = wight.charAt(j);
                switch (symbol) {
                    case 'X':
                        walls.add(new Wall(x, y));
                        break;
                    case '*':
                        boxes.add(new Box(x, y));
                        break;
                    case '.':
                        homes.add(new Home(x, y));
                        break;
                    case '&':
                        boxes.add(new Box(x, y));
                        homes.add(new Home(x, y));
                        break;
                    case '@':
                        player = new Player(x, y);
                        break;
                }
            }
        }
        return new GameObjects(walls, boxes, homes, player);


        /*Set<Wall> walls = new HashSet<>();
        walls.add(new Wall(10, 10));
        walls.add(new Wall(10,30));
        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(30, 30));
        Set<Home> homes = new HashSet<>();
        homes.add(new Home(50, 10));
        Player player = new Player(30, 10);
        GameObjects objects = new GameObjects(walls, boxes, homes, player);
        return objects;*/
    }
}
