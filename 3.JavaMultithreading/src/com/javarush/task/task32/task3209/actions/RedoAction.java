package com.javarush.task.task32.task3209.actions;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by elena.slinkova on 07.06.2017.
 * HTML Editor (12)
 12.1. Напиши реализацию класса RedoAction:
 12.1.1. Добавь в класс поле View. Добавь его инициализацию в конструкторе.
 12.1.2. Реализуй метод actionPerformed(ActionEvent actionEvent), он должен вызывать метод redo() у представления.

 */
public class RedoAction extends AbstractAction {

    private View view;
    public RedoAction(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.redo();
    }
}
