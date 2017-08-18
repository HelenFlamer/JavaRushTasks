package com.javarush.task.task35.task3513;

import javax.swing.*;

/**
 * Created by elena.slinkova on 23.06.2017.
 P.S. Результатом выполнения этого задания будет рабочая версия игры 2048, если у тебя вдруг что-то не работает, или работает не так как ожидалось, обязательно разберись и исправь прежде чем переходить к следующим задачам.
 */
public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        JFrame game = new JFrame();

        game.setTitle("2048");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(450, 500);
        game.setResizable(false);

        game.add(controller.getView());


        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}
