package com.javarush.task.task35.task3513;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by elena.slinkova on 23.06.2017.
 * Для каждого ряда или столбца, происходят на самом деле две вещи:
 * а) Сжатие плиток, таким образом, чтобы все пустые плитки были справа, т.е. ряд {4, 2, 0, 4} становится рядом {4, 2, 4, 0}
 * б) Слияние плиток одного номинала, т.е. ряд {4, 4, 2, 0} становится рядом {8, 2, 0, 0}.
 * <p>
 * Создай методы compressTiles(Tile[] tiles) и mergeTiles(Tile[] tiles), которые будут реализовывать пункты а) и б) соответственно.
 * Использовать мы их будем только внутри класса Model, поэтому уровень доступа сделай максимально узким.
 * <p>
 * Также добавь поля score и maxTile типа int, которые должны хранить текущий счет и максимальный вес плитки на игровом поле.
 * Счет увеличивается после каждого слияния, например если текущий счет 20 и было выполнено слияние ряда {4, 4, 4, 0}, счет должен увеличиться на 8.
 * Уровень доступа к полям должен быть шире приватного.
 * Проще всего организовать обновление значений этих полей в методе mergeTiles, например так:
 * 1. Если выполняется условие слияния плиток, проверяем является ли новое значения больше максимального и при необходимости меняем значение поля maxTile.
 * 2. Увеличиваем значение поля score на величину веса плитки образовавшейся в результате слияния.
 * <p>
 * P.S. Когда мы будем реализовывать методы движения, сжатие будет всегда выполнено перед слиянием, таким образом можешь считать, что в метод mergeTiles всегда передается массив плиток без пустых в середине.
 */
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    protected int score = 0;
    protected int maxTile = 2;


    public Model() {
        resetGameTiles();
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (!emptyTiles.isEmpty()) {
            int tileNum = (int) (emptyTiles.size() * Math.random());
            emptyTiles.get(tileNum).value = (Math.random() < 0.9 ? 2 : 4);
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    emptyTiles.add(gameTiles[i][j]);
                }
            }
        }
        return emptyTiles;
    }

    public void resetGameTiles() {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private boolean compressTiles(Tile[] tiles) {

        boolean change = false;
        for (int i = 1; i < tiles.length; i++) {
            for (int j = 1; j < tiles.length; j++) {
                if (tiles[j - 1].isEmpty() && !tiles[j].isEmpty()) {
                    change = true;
                    tiles[j - 1] = tiles[j];
                    tiles[j] = new Tile();
                }
            }

        }
        return change;

    }

    private boolean mergeTiles(Tile[] tiles) {

        boolean change = false;
        for (int i = 1; i < tiles.length; i++) {
            if ((tiles[i - 1].value == tiles[i].value) && !tiles[i - 1].isEmpty()) {
                change = true;
                tiles[i - 1].value *= 2;
                score += tiles[i - 1].value;
                maxTile = maxTile > tiles[i - 1].value ? maxTile : tiles[i - 1].value;
                tiles[i] = new Tile();
                compressTiles(tiles);
            }
        }
        return change;

    }

    public void left(){
        boolean isCompressed = false;
        boolean isMerged = false;

        for (Tile[] tile : gameTiles){
            isCompressed = compressTiles(tile);
            isMerged= mergeTiles(tile);
        }
        if (isCompressed || isMerged)
            addTile();
    }

    public void up(){
        turnLeft();
        left();
        turnLeft();
        turnLeft();
        turnLeft();
    }

    public void right(){
        turnLeft();
        turnLeft();
        left();
        turnLeft();
        turnLeft();
    }

    public void down(){
        turnLeft();
        turnLeft();
        turnLeft();
        left();
        turnLeft();
    }

    public void turnLeft(){
        Tile[][] newTile = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                newTile[i][j] = gameTiles[j][FIELD_WIDTH - 1 - i];
            }
        }
        gameTiles = newTile;



    }

}
