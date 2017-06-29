package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    protected int score = 0;
    protected int maxTile = 2;
    private Stack previousStates = new Stack();
    private Stack previousScores = new Stack();
    private boolean isSaveNeeded = true;

    private void saveState(Tile[][] tile){
        Tile[][] newTile = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for(int i = 0; i < tile.length; i++){
            for(int j = 0; j < tile[i].length; j++){
                newTile[i][j] = new Tile(tile[i][j].value);
            }
        }
        previousStates.push(newTile);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback(){
        if (!previousStates.empty() && !previousScores.empty()) {
            gameTiles = (Tile[][]) previousStates.pop();
            score = (int) previousScores.pop();
        }
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

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
        if (isSaveNeeded)
        saveState(gameTiles);
        boolean isCompressed = false;
        boolean isMerged = false;

        for (Tile[] tile : gameTiles){
            isCompressed = compressTiles(tile);
            isMerged= mergeTiles(tile);
        }
        if (isCompressed || isMerged)
            addTile();
        isSaveNeeded = true;
    }

    public void up(){
        saveState(gameTiles);
        turnLeft();
        left();
        turnLeft();
        turnLeft();
        turnLeft();
    }

    public void right(){
        saveState(gameTiles);
        turnLeft();
        turnLeft();
        left();
        turnLeft();
        turnLeft();
    }

    public void down(){
        saveState(gameTiles);
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

    public boolean canMove(){
        if (!getEmptyTiles().isEmpty())
            return true;
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j - 1].value)
                    return true;
            }
        }
        for (int j = 0; j < gameTiles.length; j++) {
            for (int i = 1; i < gameTiles.length; i++) {
                if (gameTiles[i][j].value == gameTiles[i - 1][j].value)
                    return true;
            }
        }
        return false;
    }

}
