package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

/**
 * Created by elena.slinkova on 23.06.2017.
 */
public class BinaryRepresentationTask extends RecursiveTask<String> {
    int num;
    public BinaryRepresentationTask(int x) {
        this.num = x;
    }

    @Override
    protected String compute() {
        int a = num % 2;
        int b = num / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            BinaryRepresentationTask task = new BinaryRepresentationTask(b);
            task.fork();
            return task.join() + result;
        }
        return result;
    }
}
