package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by elena.slinkova on 05.06.2017.
 * MyThread должен:
 * 2. Приоритет у трэдов должен проставляться циклично от минимального значения до максимального значения.
 * 3. если у трэда установлена трэд-группа(ThreadGroup), то приоритет трэда не может быть больше максимального приоритета его трэд-группы.
 */
public class MyThread extends Thread {
    static AtomicInteger num = new AtomicInteger(1);

    public MyThread() {
        if (num.get() < 10)
            this.setPriority(num.getAndIncrement());
        else {
            this.setPriority(10);
            num.set(1);
        }
    }

    public MyThread(Runnable target) {
        super(target);
        if (num.get() < 10)
            this.setPriority(num.getAndIncrement());
        else {
            this.setPriority(10);
            num.set(1);
        }
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        if (num.get() < 10) {
            if (num.get() < group.getMaxPriority()) {
                this.setPriority(num.getAndIncrement());
            } else {
                this.setPriority(group.getMaxPriority());
                num.getAndIncrement();
            }
        } else {
            if (num.get() > group.getMaxPriority())
                this.setPriority(group.getMaxPriority());
            else
               this.setPriority(10);
            num.set(1);
        }

    }

    public MyThread(String name) {
        super(name);
        if (num.get() < 10)
            this.setPriority(num.getAndIncrement());
        else {
            this.setPriority(10);
            num.set(1);
        }
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        if (num.get() < 10) {
            if (num.get() < group.getMaxPriority()) {
               this.setPriority(num.getAndIncrement());
            } else {
                this.setPriority(group.getMaxPriority());
                num.getAndIncrement();
            }
        } else {
            if (num.get() > group.getMaxPriority())
                this.setPriority(group.getMaxPriority());
            else
                this.setPriority(10);
            num.set(1);
        }
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        if (num.get() < 10)
            this.setPriority(num.getAndIncrement());
        else {
            this.setPriority(10);
            num.set(1);
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        if (num.get() < 10) {
            if (num.get() < group.getMaxPriority()) {
                this.setPriority(num.getAndIncrement());
            } else {
                this.setPriority(group.getMaxPriority());
                num.getAndIncrement();
            }
        } else {
            if (num.get() > group.getMaxPriority())
                this.setPriority(group.getMaxPriority());
            else
                this.setPriority(10);
            num.set(1);
        }
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        if (num.get() < 10) {
            if (num.get() < group.getMaxPriority()) {
                this.setPriority(num.getAndIncrement());
            } else {
                this.setPriority(group.getMaxPriority());
                num.getAndIncrement();
            }
        } else {
            if (num.get() > group.getMaxPriority())
                this.setPriority(group.getMaxPriority());
            else
                this.setPriority(10);
            num.set(1);
        }
    }
}
