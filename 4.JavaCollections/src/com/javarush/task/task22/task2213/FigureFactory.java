package com.javarush.task.task22.task2213;

/**
 * Клсс FigureFactory отвечает за создание объектов-фигурок.
 */
public class FigureFactory {
    /**
     * Набор из шести шаблонов для фигурок
     */
    public static final int[][][] BRICKS = {{
            //all levels
            {1, 1, 0},                          //   X X
            {0, 1, 1},                          //     X X
            {0, 0, 0}}, {                       //

            {1, 0, 0},                          //   X
            {1, 1, 0},                          //   X X
            {0, 1, 0}}, {                       //     X

            {0, 1, 0},                          //   X
            {0, 1, 0},                          //   X
            {0, 1, 0}}, {                       //   X

            {1, 1, 0},                          //   X X
            {1, 1, 0},                          //   X X
            {0, 0, 0}}, {                       //

            {0, 1, 0},                          //     X
            {1, 1, 1},                          //   X X X
            {0, 0, 0}}, {                       //

            {0, 0, 1},                          //       X
            {1, 1, 1},                          //   X X X
            {0, 0, 0}}, {                       //

            {1, 0, 0},                          //   X
            {1, 1, 1},                          //   X X X
            {0, 0, 0}}, {                       //
            //hard + medium

            {1, 1, 1},                          //   X X X
            {0, 1, 0},                          //     X
            {0, 1, 0}}, {                       //     X

            {1, 0, 0},                          //   X
            {1, 0, 0},                          //   X
            {1, 1, 1}}, {                       //   X X X

            {0, 0, 1},                          //       X
            {0, 0, 1},                          //       X
            {1, 1, 1}}, {                       //   X X X

            {1, 0, 1},                          //   X   X
            {1, 1, 1},                          //   X X X
            {0, 0, 0}}, {                       //

            //only hard

            {1, 0, 0},                          //   X
            {1, 1, 0},                          //   X X
            {0, 1, 1}}, {                       //     X X

            {0, 0, 1},                          //       X
            {0, 1, 1},                          //     X X
            {1, 1, 0}}, {                       //   X X

            {0, 1, 0},                          //     X
            {1, 1, 1},                          //   X X X
            {0, 1, 0}}, {                       //     X

            {1, 1, 0},                          //   X X
            {0, 1, 0},                          //     X
            {0, 1, 1}}, {                       //     X X

            {0, 1, 1},                          //     X X
            {0, 1, 0},                          //     X
            {1, 1, 0}}                          //   X X

    };

    /**
     * Метод выбирает случайный шаблон и создает с ним новую фигурку.
     */
    public static Figure createRandomFigure(int x, int y) {
        int index;
        if (Tetris.LEVEL.equals(GameLevel.LEVEL_EASY))
            index = (int) (Math.random() * 7);
        else if (Tetris.LEVEL.equals(GameLevel.LEVEL_MEDIUM))
            index = (int) (Math.random() * 11);
        else
            index = (int) (Math.random() * 15);

        return new Figure(x, y, BRICKS[index]);
    }
}
