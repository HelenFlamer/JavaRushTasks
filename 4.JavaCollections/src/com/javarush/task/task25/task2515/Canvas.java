package com.javarush.task.task25.task2515;

/**
 * Еще Canvas понадобится два метода, напиши их.
 * а) метод clear();
 * Этот метод будет очищать матрицу, чтобы на ней снова можно было рисовать.
 * Например заменить все символы матрицы на пробелы.
 * <p>
 * б) метод print();
 * Этот метод отрисовывает матрицу на экран.
 * Тут уже ты должен сам разобраться: вывести набор символов не так уж и сложно.
 * Не забудь добавить пару пустых строк в конце, чтобы матрицы выведенные в разное время не слипались.
 */
public class Canvas {

    public void print() {

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                System.out.print(matrix[i][j]);
            }

            System.out.println();
        }
        System.out.println();
    }

    public void clear() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                setPoint(j, i, ' ');
            }
        }
    }

    public void setPoint(double x, double y, char c) {
        int a = (int) Math.round(x);
        int b = (int) Math.round(y);

        if (a >= 0 && b >= 0 && b < matrix.length && a < matrix[0].length)
            matrix[b][a] = c;
    }

    public void drawMatrix(double x, double y, int[][] matrix, char c) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0)
                    setPoint(x + j, y + i, c);
            }
        }
    }

    private int width;
    private int height;
    private char[][] matrix;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new char[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMatrix() {
        return matrix;
    }
}
