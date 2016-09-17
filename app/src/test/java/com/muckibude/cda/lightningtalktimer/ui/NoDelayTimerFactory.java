package com.muckibude.cda.lightningtalktimer.ui;

import com.muckibude.cda.lightningtalktimer.ui.gestures.TimerFactory;

import java.util.Timer;
import java.util.TimerTask;

public class NoDelayTimerFactory extends TimerFactory {
    @Override
    public Timer createTimer() {
        return new Timer() {
            @Override
            public void schedule(TimerTask task, long delay) {
                task.run();
            }

            @Override
            public void schedule(TimerTask task, long delay, long period) {
                task.run();
            }
        };
    }

}
