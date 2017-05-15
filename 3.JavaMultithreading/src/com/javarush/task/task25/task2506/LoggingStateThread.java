package com.javarush.task.task25.task2506;


/**
 * Created by elena.slinkova on 15.03.2017.
 */
public class LoggingStateThread extends Thread {

    private final Thread target;

    @Override
    public synchronized void run() {
        State state = target.getState();
        System.out.println(state.toString());

        while (state != State.TERMINATED) {
            if (target.getState() != state) {
                state = target.getState();
                System.out.println(state.toString());
            }
        }
    }

    public LoggingStateThread(Thread target) {

        this.target = target;
        setDaemon(true);
    }
}
