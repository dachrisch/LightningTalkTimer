package com.muckibude.cda.lightningtalktimer.domain;

public class NotStartableCountdownTimer implements PausableCountdownTimer {
    @Override
    public PausableCountdownTimer start() {
        return this;
    }

    @Override
    public void pause() {
        // pass
    }

    @Override
    public void resume() {
        throw new IllegalStateException("not startable");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
