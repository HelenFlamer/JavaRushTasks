package com.javarush.task.task32.task3209.actions;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by elena.slinkova on 07.06.2017.
 * 12.2. Напиши реализацию класса UndoAction по аналогии с RedoAction.
 12.3. Изучи реализацию класса StrikeThroughAction, которую ты получил вместе с заданием и реализуй аналогичным образом классы:
 12.3.1. SubscriptAction
 12.3.2. SuperscriptAction
 Запусти программу и убедись, что пункты меню Подстрочный знак, Надстрочный знак и Зачеркнутый работают.
 Пункты, отвечающие за отмену и возврат действия пока не подключены к контроллеру и мы не сможем их проверить.
 */
public class UndoAction extends AbstractAction {
    private View view;
    public UndoAction(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        view.undo();
    }
}
