package com.muckibude.cda.lightningtalktimer.domain;

import com.muckibude.cda.lightningtalktimer.data.CountdownEntity;

public class PausableCountdownTimer {
    private final CountdownEntity countdownEntity;
    private boolean isRunning = false;
    private TickGiver tickGiver;

    public PausableCountdownTimer(CountdownEntity countdownEntity) {
        this.countdownEntity = countdownEntity;
    }

    public void start() {
        isRunning = true;
        while (countdownEntity.inMillis() > 0) {
            tickGiver.waitOneSecond();
            onSecond();
        }
    }

    protected void onSecond() {
        if (isRunning) {
            countdownEntity.decrementSeconds(1);
        }
    }

    public void pause() {
        isRunning = false;
    }

    public void resume() {
        isRunning = true;
    }

    public void setTickGiver(TickGiver tickGiver) {
        this.tickGiver = tickGiver;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
