package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

/**
 * Created by elena.slinkova on 23.06.2017.
 * <p>
 * Ну а теперь, самое главное! Для того чтобы иметь возможность обрабатывать пользовательский ввод, необходимо переопределить метод keyPressed с одним параметром типа KeyEvent.
 * <p>
 * Логика метода должна быть следующей:
 * 1. Если была нажата клавиша ESC — вызови метод resetGame.
 * 2. Если метод canMove модели возвращает false — установи флаг isGameLost в true.
 * 3. Если оба флага isGameLost и isGameWon равны false — обработай варианты движения:
 * а) для клавиши KeyEvent.VK_LEFT вызови метод left у модели;
 * б) для клавиши KeyEvent.VK_RIGHT вызови метод right у модели;
 * в) для клавиши KeyEvent.VK_UP вызови метод up у модели;
 * г) для клавиши KeyEvent.VK_DOWN вызови метод down у модели.
 * 4. Если поле maxTile у модели стало равно WINNING_TILE, установи флаг isGameWon в true.
 * 5. В самом конце, вызови метод repaint у view.
 * <p>
 * P.S. Для получения кода нажатой клавиши используй метод getKeyCode класса KeyEvent.
 */
public class Controller extends KeyAdapter {
    private Model model;
    private View view;
    private static int WINNING_TILE = 2048;

    public void resetGame() {
        model.score = 0;
        view.isGameWon = false;
        view.isGameLost = false;
        model.resetGameTiles();
    }

    public View getView() {
        return view;
    }

    public Controller(Model model) {

        this.model = model;
        this.view = new View(this);
    }

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public int getScore() {
        return model.score;
    }

    public void keyPressed(KeyEvent event){
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
            resetGame();
        if (!model.canMove())
            view.isGameLost = true;
        if (!view.isGameLost && !view.isGameWon){
            if (event.getKeyCode() == KeyEvent.VK_LEFT)
                model.left();
            else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                model.right();
            else if (event.getKeyCode() == KeyEvent.VK_UP)
                model.up();
            else if (event.getKeyCode() == KeyEvent.VK_DOWN)
                model.down();
            else if (event.getKeyCode() == KeyEvent.VK_Z)
                model.rollback();
        }
        if (model.maxTile == WINNING_TILE)
            view.isGameWon = true;
        view.repaint();
    }
}
