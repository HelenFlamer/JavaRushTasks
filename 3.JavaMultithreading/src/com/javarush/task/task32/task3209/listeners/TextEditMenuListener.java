package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

/**
 * Created by elena.slinkova on 07.06.2017.
 * Реализуем класс TextEditMenuListener в пакет listeners.
 * Этот класс будет работать аналогично классу UndoMenuListener только для других пунктов меню.
 * Пункты меню, отвечающие за стиль, шрифт, цвет и т.д. должны быть доступны только тогда, когда в нашем редакторе выбрана первая вкладка.
 13.1. Добавь в представление метод boolean isHtmlTabSelected(). Он должен возвращать true, если выбрана вкладка,
 отображающая html в панели вкладок (подсказка: ее индекс 0).
 */
public class TextEditMenuListener implements MenuListener {
    private View view;
    public TextEditMenuListener(View view){
        this.view = view;
    }

    @Override
    public void menuSelected(MenuEvent menuEvent) {
        JMenu selectedMenu = (JMenu) menuEvent.getSource();
        for (Component menuItem : selectedMenu.getMenuComponents())
            menuItem.setEnabled(view.isHtmlTabSelected());
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

}
