package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by elena.slinkova on 05.06.2017.
 *  3.2. Добавь класс FrameListener в пакет listeners. Он должен:
 3.2.1. Быть унаследован от WindowAdapter.
 3.2.2. Иметь поле View view.
 3.2.3. В конструкторе принимать View и инициализировать внутреннее поле.
 3.2.4. Иметь переопределенный метод windowClosing(WindowEvent windowEvent), который должен вызывать exit() у представления.
 */
public class FrameListener extends WindowAdapter {
    private View view;

    public FrameListener(View view) {
        this.view = view;
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        view.exit();
    }
}
