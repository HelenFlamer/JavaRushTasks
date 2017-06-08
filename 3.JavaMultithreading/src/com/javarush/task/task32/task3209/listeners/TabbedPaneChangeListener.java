package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by elena.slinkova on 07.06.2017.
 * 5.1.1. Конструктор, принимающий представление в виде параметра и сохраняющий во внутреннее поле view класса.
 * 5.1.2. Переопредели метод из интерфейса ChangeListener, он должен вызывать метод selectedTabChanged() у представления.
 * Последнего метода еще нет, сделай для него заглушку.
 * 5.2. Объяви класс ExceptionHandler. Это будет наш обработчик исключительных ситуаций, который ты в дальнейшем сможешь переопределить.
 * Пока добавь в него статический метод log(Exception e), который будет выводить в консоль краткое описание проблемы
 * (используй метод toString у переданного исключения).
 */
public class TabbedPaneChangeListener implements ChangeListener {
    private View view;

    public TabbedPaneChangeListener(View view) {
        this.view = view;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        view.selectedTabChanged();
    }
}
