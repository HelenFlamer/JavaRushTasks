package com.javarush.task.task28.task2805;


/**
 * Created by elena.slinkova on 31.05.2017.
 * MyThread должен:
 1. Иметь возможность быть созданным используя любой конструктор супер-класса (Alt+Insert).
 2. Приоритет у трэдов должен проставляться циклично от минимального значения до максимального значения.
 3. если у трэда установлена трэд-группа(ThreadGroup), то приоритет трэда не может быть больше максимального приоритета его трэд-группы.


 */
public class MyThread extends Thread {
    private int priority = 0;

    public MyThread() {
    }

    public MyThread(Runnable target) {
        super(target);
        if (priority == 10)
            priority = 0;
        Thread.currentThread().setPriority(priority++);
        if (Thread.currentThread().getThreadGroup() != null)
            Thread.currentThread().setPriority(currentThread().getThreadGroup().getMaxPriority());
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        if (priority == 10)
            priority = 0;
        Thread.currentThread().setPriority(priority++);
        if (Thread.currentThread().getThreadGroup() != null)
            Thread.currentThread().setPriority(currentThread().getThreadGroup().getMaxPriority());
    }

    public MyThread(String name) {
        super(name);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }
}
