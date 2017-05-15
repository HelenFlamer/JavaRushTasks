package com.javarush.task.task25.task2508;
/*
Требования:
        1. Класс TaskManipulator должен реализовывать интерфейсы Runnable и CustomThreadManipulator.
        2. Метод run должен каждые 100 секунд выводить имя исполняемой нити в консоль.
        3. Класс TaskManipulator должен иметь внутреннее поле типа Thread.
        4. Метод start должен создавать, сохранять во внутреннее поле и запускать нить с именем, которое передано через аргумент метода.
        5. Метод stop должен прерывать последнюю созданную классом TaskManipulator нить.*/


public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread field;
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());

        while (true)
        {
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName());

            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public void start(String threadName) {
        field = new Thread(threadName);
        field.start();

    }

    @Override
    public void stop() {
        field.interrupt();
    }
}
